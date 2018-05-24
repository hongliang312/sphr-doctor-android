package com.lightheart.sphr.doctor.app;

/**
 * Created by fucp on 2018-4-10.
 * Description :
 */

public class Constant {
    // 测试服务器
//     public static final String BASE_URL = "http://172.61.1.63:8889/shdr-service-basic/";
    // 鲁有志
//    public static final String BASE_URL = "http://172.61.1.51:8889/shdr-service-basic/";
    // 宇清
    public static final String BASE_URL = "http://172.61.1.85:8889/shdr-service-basic/";

    // 文件地址
    public static final String BASE_FILE_URL = "http://172.61.1.63:5085/shdr-file-boot/";

    /**
     * 每页数量
     */
    public static final int PAGE_SIZE = 10;
    /**
     * url key
     */
    public static final String CONTENT_URL_KEY = "url";
    /**
     * title key
     */
    public static final String CONTENT_TITLE_KEY = "title";
    /**
     * id key
     */
    public static final String CONTENT_ID_KEY = "id";
    /**
     * cid key
     */
    public static final String CONTENT_CID_KEY = "cid";
    public static final String CONTENT_AUTHOR_KEY = "author";
    /**
     * childrenData key
     */
    public static final String CONTENT_CHILDREN_DATA_KEY = "childrenData";
    /**
     * hotFriend key
     */
    public static final String CONTENT_HOT_FRIEND_KEY = "hotFriend";
    /**
     * hot key
     */
    public static final String CONTENT_HOT_KEY = "hotKey";
    /**
     * hot key
     */
    public static final String CONTENT_HOT_NAME_KEY = "hotNameKey";

    public static final String SAVE_USER_LOGIN_KEY = "user/login";
    public static final String SAVE_USER_REGISTER_KEY = "user/register";
    public static final String SET_COOKIE_KEY = "set-cookie";

    public static final String SHARED_NAME = "_preferences";
    public static final String MOBILE_KEY = "mobile";
    public static final String PASSWORD_KEY = "password";
    public static final String LOGIN_KEY = "login";
    public static final String USER_KEY = "user";
    public static final String BANNER_KEY = "banner";
    public static final String ARTICLE_KEY = "article";

    // 权限
    public static final int RC_CAMERA_PERM = 123;
    public static final int RC_READ_EXTERNAL_STORAGE = 124;
    //请求相机
    public static final int REQUEST_CAPTURE = 100;
    //请求相册
    public static final int REQUEST_PICK = 101;
    //请求截图
    public static final int REQUEST_CROP_PHOTO = 102;
    // 请求地区
    public static final int REQUEST_DISTRACT = 103;
    // 请求科室
    public static final int REQUEST_DEPARTMENT = 104;
}
