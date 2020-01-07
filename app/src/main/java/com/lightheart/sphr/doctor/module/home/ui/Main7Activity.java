package com.lightheart.sphr.doctor.module.home.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guwu.common.address.AddressPickerView;
import com.guwu.common.utils.ToastUtil;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class Main7Activity extends BaseActivity {


    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    private int mYear = 1994;
    private int mMonth = 1;
    private int mDay = 21;
    private String mProvince = "";
    private String mCity = "";
    private String mDistrict = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main7 ;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.rl_nickname, R.id.rl_sex, R.id.rl_birthday, R.id.rl_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_birthday:
                showDatePickerDialog(this, tvBirthday);
                break;
            case R.id.rl_address:
                showAddressDialog();
                break;
        }
    }

    private void showAddressDialog() {
        final BottomSheetDialog dialog = new BottomSheetDialog(this);

        View rootView = LayoutInflater.from(this).inflate(R.layout.pop_address_picker, null, false);

        AddressPickerView addressView = rootView.findViewById(R.id.apvAddress);
        addressView.setPickerType(0);
        if (!TextUtils.isEmpty(mProvince)) {
            addressView.setPreData(mProvince, mCity, mDistrict);
        }

        addressView.setOnAddressPickerSure((province, city, district, provinceCode, cityCode, districtCode) -> {
            tvAddress.setText(province + "-" + city + "-" + district);
            mProvince = province;
            mCity = city;
            mDistrict = district;
            dialog.dismiss();
        });

        dialog.setContentView(rootView);
        dialog.show();
    }

    public void showDatePickerDialog(final Activity activity, final TextView tv) {
        // Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(activity, (view, year, monthOfYear, dayOfMonth) -> {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String month = String.format("%02d", monthOfYear + 1);
            String day = String.format("%02d", dayOfMonth);
            tv.setText(year + "/" + month + "/" + day);

        }, mYear, mMonth, mDay);


        dialog.getDatePicker().setMaxDate(new Date().getTime());

        dialog.show();
    }

}
