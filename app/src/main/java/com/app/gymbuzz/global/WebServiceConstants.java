package com.app.gymbuzz.global;

public class WebServiceConstants {
    public static final String SERVICE_URL = "http://gymbuzz.ingicweb.com/api/api/";
    public static final String Local_SERVICE_URL = "http://10.1.18.99/treatz-asia/api/";
    public static final int DEVICE_TYPE = 1;
    public static final String SUCCESS_RESPONSE_CODE = "2000";
    public static final String TOKEN_TYPE = "Bearer";
    public static final String LANGUAGE_CODE = "en";

    //region User Module
    public static final int ROLE_ID = 4;
    public static final int RESEND_CODE_TYPE_SIGNUP = 1;
    public static final int RESEND_CODE_TYPE_FORGOT = 2;
    public static final String LOGIN = "LOGIN";
    public static final String REGISTER = "REGISTER";
    public static final String VERIFY_CODE_SIGNUP = "VERIFY_CODE_SIGNUP";
    public static final String FORGOT_PASSWORD = "FORGOT_PASSWORD";
    public static final String FORGOT_PASSWORD_VERIFY_CODE = "FORGOT_PASSWORD_VERIFY_CODE";
    public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
    public static final String EDIT_PROFILE = "EDIT_PROFILE";
    public static final String RESEND_CODE = "RESEND_CODE";
    //endregion

    //region Log Module
    public static final String GET_ALL_EXERCISE_LOGS = "GET_ALL_EXERCISE_LOGS";
    public static final String GET_ALL_EXERCISE_LOGS_PAGED = "GET_ALL_EXERCISE_LOGS_PAGED";
    //endregion

}
