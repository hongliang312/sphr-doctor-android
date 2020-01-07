package com.lightheart.sphr.doctor.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.lightheart.sphr.doctor.R;

public class ProgressBar extends DialogFragment {
    @SuppressLint("StaticFieldLeak")
    private static ProgressBar instance;
    private TextView mContent;
    private String content;

    /*@NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                // On backpress, do your stuff here.
            }
        };
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View v = inflater.inflate(R.layout.pop_progress_loading, container, false);
        mContent = (TextView) v.findViewById(R.id.tv_content);
        if (!TextUtils.isEmpty(content)) {
            mContent.setText(content);
        }
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
        return v;
    }

    @Override
    public Dialog getDialog() {
        return super.getDialog();
    }

    public static void show(FragmentManager fm) {
        if (instance != null && instance.getFragmentManager() != null) {
            instance.dismissAllowingStateLoss();
        }
        instance = new ProgressBar();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(instance, "ProgressBar");
        fragmentTransaction.commitAllowingStateLoss();// 这里直接调用show方法会报java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        //因为show方法中是通过commit进行的提交(通过查看源码)
        //这里为了修复这个问题，使用commitAllowingStateLoss()方法
//        instance.show(fm, "ProgressBar");
    }

    public static void updateText(String text) {
        if (instance != null) {
            instance.content = text;
            if (instance.mContent != null) {
                instance.mContent.setText(text);
            }
        }
    }

    public static void dis() {
        if (instance != null && instance.getFragmentManager() != null)
            instance.dismissAllowingStateLoss();
        instance = null;
    }

    public static void setCancelAble(boolean flag) {
        if (instance != null) {
            instance.setCancelable(flag);
        }
    }

}
