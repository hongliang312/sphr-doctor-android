package com.lightheart.sphr.doctor.module.my.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lightheart.sphr.doctor.BuildConfig;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.lightheart.sphr.doctor.bean.DoctorBean;
import com.lightheart.sphr.doctor.bean.DoctorModel;
import com.lightheart.sphr.doctor.bean.HospitalsModel;
import com.lightheart.sphr.doctor.bean.TitlesModel;
import com.lightheart.sphr.doctor.module.home.ui.ClipImageActivity;
import com.lightheart.sphr.doctor.module.my.contract.MyPersonalInfoContract;
import com.lightheart.sphr.doctor.module.my.presenter.MyPersonalInfoPresenter;
import com.lightheart.sphr.doctor.utils.FileUtil;
import com.lightheart.sphr.doctor.utils.ImageLoaderUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.lightheart.sphr.doctor.app.Constant.RC_CAMERA_PERM;
import static com.lightheart.sphr.doctor.app.Constant.RC_READ_EXTERNAL_STORAGE;
import static com.lightheart.sphr.doctor.app.Constant.REQUEST_CAPTURE;
import static com.lightheart.sphr.doctor.app.Constant.REQUEST_CROP_PHOTO;
import static com.lightheart.sphr.doctor.app.Constant.REQUEST_DEPARTMENT;
import static com.lightheart.sphr.doctor.app.Constant.REQUEST_DISTRACT;
import static com.lightheart.sphr.doctor.app.Constant.REQUEST_PICK;
import static com.lightheart.sphr.doctor.utils.FileUtil.getRealFilePathFromUri;

/**
 * Created by fucp on 2018-5-19.
 * Description : 个人资料页面
 */

public class MyPersonalInfoActivity extends BaseActivity<MyPersonalInfoPresenter> implements MyPersonalInfoContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_sub)
    Button mBtSub;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.llHeaderImage)
    LinearLayout llHeaderImage;
    @BindView(R.id.clvHeadImage)
    CircleImageView clvHeadImage;
    @BindView(R.id.etName)
    TextInputEditText etName;
    @BindView(R.id.etSex)
    TextInputEditText etSex;
    @BindView(R.id.etBirth)
    TextInputEditText etBirth;
    @BindView(R.id.etDistract)
    TextInputEditText etDistract;
    @BindView(R.id.etHospital)
    TextInputEditText etHospital;
    @BindView(R.id.etDepartment)
    TextInputEditText etDepartment;
    @BindView(R.id.etTitle)
    TextInputEditText etTitle;
    @BindView(R.id.etMajor)
    TextInputEditText etMajor;
    @BindView(R.id.etPersonalIntroduce)
    TextInputEditText etPersonalIntroduce;
    @BindView(R.id.etOutsideNum)
    TextInputEditText etOutsideNum;
    @BindView(R.id.etOperationNum)
    TextInputEditText etOperationNum;
    @BindView(R.id.tILSex)
    TextInputLayout tILSex;
    @BindView(R.id.tILBirth)
    TextInputLayout tILBirth;
    @BindView(R.id.tILDistract)
    TextInputLayout tILDistract;
    @BindView(R.id.tILHospital)
    TextInputLayout tILHospital;
    @BindView(R.id.tILDepartment)
    TextInputLayout tILDepartment;
    @BindView(R.id.tILTitle)
    TextInputLayout tILTitle;
    private File tempFile;
    private OptionsPickerView sexSelect;
    private ArrayList<String> sex = new ArrayList<>();
    private DoctorModel info;
    private TimePickerView birthOption;
    private OptionsPickerView titleOption;
    private ArrayList<String> title = new ArrayList<>();
    private OptionsPickerView hospitalOption;
    private ArrayList<String> hospital = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        initToolbar(mToolbar, mTitleTv, mBtSub, R.string.personal_info, true, R.string.complete);

        DoctorBean doctorBean = (DoctorBean) getIntent().getSerializableExtra("info");
        info = new DoctorModel();
        // 转化
        info.setBirth(doctorBean.getBirth());
        info.setDepartmentId(doctorBean.getDepartmentId());
        info.setDepartmentName(doctorBean.getDepartmentName());
        info.setDistrictId(doctorBean.getDistrictId());
        info.setDistrictName(doctorBean.getDistrictName());
        info.setHospitalId(doctorBean.getHospitalId());
        info.setHospitalName(doctorBean.getHospitalName());
        info.setId(doctorBean.getId());
        info.setImgUrl(doctorBean.getImgUrl());
        info.setMajorIn(doctorBean.getMajorIn());
        info.setOperationNum(doctorBean.getOperationNum());
        info.setOutpatientNum(doctorBean.getOutpatientNum());
        info.setRealName(doctorBean.getRealName());
        info.setSex(doctorBean.getSex());
        info.setSummary(doctorBean.getSummary());
        info.setTitleId(doctorBean.getTitleId());
        info.setTitleName(doctorBean.getTitleName());

        sex.add(getString(R.string.male));
        sex.add(getString(R.string.female));

        assert mPresenter != null;
        mPresenter.loadTitleData();
        mPresenter.loadHospitalData(info.getDistrictId());

        // 初始化性别选择器
        initSexSelect();
        // 初始化时间选择器
        initTimeOption();

        if (info != null) {
            ImageLoaderUtils.display(this, clvHeadImage, info.getImgUrl());
            etName.setText(TextUtils.isEmpty(info.getRealName()) ? "" : info.getRealName());
            etSex.setText(TextUtils.equals("M", info.getSex()) ? getString(R.string.male) : TextUtils.equals("F", info.getSex()) ? getString(R.string.female) : "");
            etBirth.setText(info.getBirth() == 0 ? "" : TimeUtils.millis2String(info.getBirth(), new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)));
            etDistract.setText(TextUtils.isEmpty(info.getDistrictName()) ? "" : info.getDistrictName());
            etHospital.setText(TextUtils.isEmpty(info.getHospitalName()) ? "" : info.getHospitalName());
            etDepartment.setText(TextUtils.isEmpty(info.getDepartmentName()) ? "" : info.getDepartmentName());
            etTitle.setText(TextUtils.isEmpty(info.getTitleName()) ? "" : info.getTitleName());
            etMajor.setText(TextUtils.isEmpty(info.getMajorIn()) ? "" : info.getMajorIn());
            etPersonalIntroduce.setText(TextUtils.isEmpty(info.getSummary()) ? "" : info.getSummary());
            etOutsideNum.setText(info.getOutpatientNum() == 0 ? "0" : String.valueOf(info.getOutpatientNum()));
            etOperationNum.setText(info.getOperationNum() == 0 ? "0" : String.valueOf(info.getOperationNum()));

        }
    }

    /**
     * 选择性别
     */
    private void initSexSelect() {
        sexSelect = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                etSex.setText(sex.get(options1));
                info.setSex(TextUtils.equals("男", sex.get(options1)) ? "M" : "F");
            }
        }).build();
        sexSelect.setNPicker(sex, null, null);
    }

    /**
     * 生日时间选择
     */
    private void initTimeOption() {
        Calendar nowDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1);
        birthOption = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                etBirth.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date));
                info.setBirth(date.getTime());
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setContentSize(16)
                .setDate(nowDate)
                .setRangDate(startDate, nowDate)
                .setLayoutRes(R.layout.pickerview_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView tv_cancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                birthOption.returnData();
                                birthOption.dismiss();
                            }
                        });
                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                birthOption.dismiss();
                            }
                        });
                    }
                })
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
    }

    @Override
    public void setTitles(final List<TitlesModel> titles) {
        for (TitlesModel titlesModel : titles) {
            title.add(titlesModel.title);
        }
        // 职称选择器
        titleOption = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                etTitle.setText(title.get(options1));
                info.setTitleId(titles.get(options1).id);
                info.setTitleName(titles.get(options1).title);
            }
        }).build();
        titleOption.setNPicker(title, null, null);
    }

    @Override
    public void setHospitals(final List<HospitalsModel> hospitals) {
        hospital.clear();
        for (HospitalsModel hospitalsModel : hospitals) {
            hospital.add(hospitalsModel.getName());
        }
        // 医院选择器
        hospitalOption = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                etHospital.setText(hospital.get(options1));
                info.setHospitalId(hospitals.get(options1).getId());
                info.setHospitalName(hospitals.get(options1).getName());
            }
        }).build();
        hospitalOption.setNPicker(hospital, null, null);
    }

    @Override
    public void successUploadImage(String url) {
        if (!TextUtils.isEmpty(url)) info.setImgUrl(url);
    }

    @Override
    public void updateSuccess() {
        ToastUtils.showShort("您成功更新了个人资料！");
        this.finish();
    }

    @OnClick({R.id.bt_sub, R.id.llHeaderImage, R.id.tILSex, R.id.tILBirth, R.id.tILDistract, R.id.tILHospital, R.id.tILDepartment, R.id.tILTitle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sub:
                check2UpdatePersonalInfo();
                break;
            case R.id.llHeaderImage:
                uploadHeadImage();
                break;
            case R.id.tILSex:
                if (sexSelect != null) sexSelect.show();
                break;
            case R.id.tILBirth:
                if (birthOption != null) birthOption.show();
                break;
            case R.id.tILDistract:
                startActivityForResult(new Intent(MyPersonalInfoActivity.this, AreaActivity.class), REQUEST_DISTRACT);
                break;
            case R.id.tILHospital:
                if (info.getDistrictId() != 0) {
                    if (hospitalOption != null) hospitalOption.show();
                } else ToastUtils.showShort(R.string.hospital);
                break;
            case R.id.tILDepartment:
                startActivityForResult(new Intent(MyPersonalInfoActivity.this, DepartmentActivity.class), REQUEST_DEPARTMENT);
                break;
            case R.id.tILTitle:
                if (titleOption != null) titleOption.show();
                break;
        }
    }

    /**
     * 校验并上传更新个人信息
     */
    private void check2UpdatePersonalInfo() {
        if (TextUtils.isEmpty(etName.getText().toString().trim())) {
            ToastUtils.showShort("请填写您的姓名...");
            return;
        }
        if (TextUtils.isEmpty(etSex.getText().toString().trim()))
            info.setSex("U");// 第一次没有个人资料时，没填性别时，性别为U
        assert mPresenter != null;
        mPresenter.updatePersonalInfo(info);
    }

    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_photo_select, null);
        TextView btnCamera = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                cameraTask();
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                photoAlbum();
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
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
                    getString(R.string.rationale_camera),
                    RC_CAMERA_PERM,
                    Manifest.permission.CAMERA);
        }
    }

    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MyPersonalInfoActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
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
                    getString(R.string.rationale_camera),
                    RC_READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == Activity.RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == Activity.RESULT_OK) {
                    final Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    clvHeadImage.setImageBitmap(bitMap);
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //TODO
                    assert mPresenter != null;
                    mPresenter.uploadHeadImage(new File(cropImagePath));
                }
                break;
            case REQUEST_DISTRACT:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    int distractId = data.getIntExtra("distractId", 0);
                    String distractName = data.getStringExtra("distractName");
                    if (distractId != 0 && !TextUtils.isEmpty(distractName)) {
                        etDistract.setText(distractName);
                        info.setDistrictId(distractId);
                        info.setDistrictName(distractName);
                        assert mPresenter != null;
                        mPresenter.loadHospitalData(info.getDistrictId());
                        // 更换地区后，原来的医院名称和医院的id清空，需要重新选择地区对应的医院
                        etHospital.setText(getString(R.string.empty));
                        info.setHospitalName(getString(R.string.empty));
                        info.setHospitalId(0);
                    }
                }
                break;
            case REQUEST_DEPARTMENT:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    int departmentId = data.getIntExtra("departmentId", 0);
                    String departmentName = data.getStringExtra("departmentName");
                    if (departmentId != 0 && !TextUtils.isEmpty(departmentName)) {
                        etDepartment.setText(departmentName);
                        info.setDepartmentId(departmentId);
                        info.setDepartmentName(departmentName);
                    }
                }
                break;
        }
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA);
    }

    private boolean hasReadStoragePermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

}
