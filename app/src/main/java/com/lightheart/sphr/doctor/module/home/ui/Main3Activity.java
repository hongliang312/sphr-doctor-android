package com.lightheart.sphr.doctor.module.home.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lightheart.sphr.doctor.BuildConfig;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.utils.FileUtil;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import static com.lightheart.sphr.doctor.app.Constant.RC_CAMERA_PERM;
import static com.lightheart.sphr.doctor.app.Constant.RC_READ_EXTERNAL_STORAGE;
import static com.lightheart.sphr.doctor.app.Constant.REQUEST_CAPTURE;
import static com.lightheart.sphr.doctor.app.Constant.REQUEST_PICK;
import static com.lightheart.sphr.doctor.utils.FileUtil.getRealFilePathFromUri;

public class Main3Activity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.clvHeadImage)
    ImageView clvHeadImage;
    private File tempFile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main3;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        tv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadHeadImage();
            }
        } );
    }

    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        View view = LayoutInflater.from( this ).inflate( R.layout.layout_photo_select, null );
        View btnCamera = view.findViewById( R.id.btn_camera );
        View btnPhoto = view.findViewById( R.id.btn_photo );
        View btnCancel = view.findViewById( R.id.btn_cancel );
        final PopupWindow popupWindow = new PopupWindow( view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        popupWindow.setBackgroundDrawable( getResources().getDrawable( android.R.color.transparent ) );
        popupWindow.setOutsideTouchable( true );
        View parent = LayoutInflater.from( this ).inflate( R.layout.activity_main, null );
        popupWindow.showAtLocation( parent, Gravity.BOTTOM, 0, 0 );
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes( params );
        popupWindow.setOnDismissListener( new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes( params );
            }
        } );
        btnCamera.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                cameraTask();
                popupWindow.dismiss();
            }
        } );
        btnPhoto.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                photoAlbum();
                popupWindow.dismiss();
            }
        } );
        btnCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        } );
    }

    @AfterPermissionGranted(RC_READ_EXTERNAL_STORAGE)
    public void photoAlbum() {
        if (hasReadStoragePermission()) {
            // Have permission, do the thing!
            gotoPhoto();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                    this,
                    getString( R.string.rationale_camera ),
                    RC_READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE );
        }
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {
        if (hasCameraPermission()) {
            // Have permission, do the thing!
            gotoCamera();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                    this,
                    getString( R.string.rationale_camera ),
                    RC_CAMERA_PERM,
                    Manifest.permission.CAMERA );
        }
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d( "evan", "*****************打开图库********************" );
        //跳转到调用系统图库
        Intent intent = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
        startActivityForResult( Intent.createChooser( intent, "请选择图片" ), REQUEST_PICK );
    }

    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d( "evan", "*****************打开相机********************" );
        //创建拍照存储的图片文件
        tempFile = new File( FileUtil.checkDirPath( Environment.getExternalStorageDirectory().getPath() + "/image/" ), System.currentTimeMillis() + ".jpg" );
        //跳转到调用系统相机
        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags( Intent.FLAG_GRANT_WRITE_URI_PERMISSION );
            Uri contentUri = FileProvider.getUriForFile( Main3Activity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile );
            intent.putExtra( MediaStore.EXTRA_OUTPUT, contentUri );
        } else {
            intent.putExtra( MediaStore.EXTRA_OUTPUT, Uri.fromFile( tempFile ) );
        }
        startActivityForResult( intent, REQUEST_CAPTURE );
    }

    /**
     * 裁剪图片
     *
     * @param sourceUri
     */
    private void startUCrop(Uri sourceUri) {
        UCrop.Options options = new UCrop.Options();
        //裁剪后图片保存在文件夹中
        Uri destinationUri = Uri.fromFile(new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory() + "/upload/"), "uCrop.jpg"));
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);//第一个参数是裁剪前的uri,第二个参数是裁剪后的uri
        options.setAllowedGestures( com.yalantis.ucrop.UCropActivity.SCALE, com.yalantis.ucrop.UCropActivity.ROTATE, com.yalantis.ucrop.UCropActivity.ALL );
        //设置隐藏底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor( ActivityCompat.getColor( this, R.color.colorPrimary ) );
        //设置状态栏颜色
        options.setStatusBarColor( ActivityCompat.getColor( this, R.color.colorPrimary ) );
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled( true );
        uCrop.withOptions( options );
        uCrop.start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系相机返回
                if (resultCode == RESULT_OK) {
                    Uri uri = Uri.fromFile( tempFile );
                    Log.d( "uri_uri", "" + uri );
                    if (uri != null) {
                        startUCrop( uri );
                    }
                } else {
                    Toast.makeText( this, "取消", Toast.LENGTH_SHORT ).show();
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    if (uri != null) {
                        startUCrop( uri );
                    }
                }
                break;
            case UCrop.REQUEST_CROP: //剪切图片返回
                Uri uri = UCrop.getOutput( data );
                Log.d( "uri_uri", "" + uri );
                if (uri != null) {
                    String cropImagePath = getRealFilePathFromUri( getApplicationContext(), uri );
                    Bitmap bitMap = BitmapFactory.decodeFile( cropImagePath );
                    clvHeadImage.setImageBitmap( bitMap );
                    Log.d( "bitMap_bitMap", "" + bitMap );
                }
                break;
        }
    }

    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions( this, Manifest.permission.CAMERA );
    }

    private boolean hasReadStoragePermission() {
        return EasyPermissions.hasPermissions( this, Manifest.permission.READ_EXTERNAL_STORAGE );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult( requestCode, permissions, grantResults, this );
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied( this, perms )) {
            new AppSettingsDialog.Builder( this ).build().show();
        }
    }

}
