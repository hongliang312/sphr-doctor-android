package com.lightheart.sphr.doctor.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckContentUtil {

    // ^[0-9a-zA-Z]{6,20}$
    public static boolean checkContext(String content) {
        if (TextUtils.isEmpty(content)) {
            return false;
        } else {
            String pattern = "^[0-9a-zA-Z]{6,20}$";
            return content.matches(pattern);
        }
    }

    public static boolean checkPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        } else {
            String pattern = "^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$";
            return phone.matches(pattern);
        }
    }

    // 是否是数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static boolean isSMPhone() {
        String mobile_model = android.os.Build.MANUFACTURER + android.os.Build.MODEL;
        return mobile_model.contains("SM");
    }

}
