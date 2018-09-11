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
    public static final String LOGOUT = "LOGOUT";
    public static final String REGISTER = "REGISTER";
    public static final String VERIFY_CODE_SIGNUP = "VERIFY_CODE_SIGNUP";
    public static final String FORGOT_PASSWORD = "FORGOT_PASSWORD";
    public static final String FORGOT_PASSWORD_VERIFY_CODE = "FORGOT_PASSWORD_VERIFY_CODE";
    public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
    public static final String EDIT_PROFILE = "EDIT_PROFILE";
    public static final String RESEND_CODE = "RESEND_CODE";
    //endregion


    // region Notificaiton Module
    public static final int NOTIFICATION_ACTION_JOB_COMPLETE = 4;

    public static final String GET_ALL_NOTIFICATIONS = "GET_ALL_NOTIFICATIONS";
    public static final String TOGGLE_NOTIFICATIONS = "TOGGLE_NOTIFICATIONS";
    //endregion

    // region Gym Module
    public static final String GET_GYM_CMS = "GET_GYM_CMS";
    public static final String GET_GYM_DETAILS = "GET_GYM_DETAILS";
    //endregion

    // region Exercise Module
    public static final String BODY_PART_TYPE_BICEP = "1";
    public static final String BODY_PART_TYPE_CHEST = "2";
    public static final String BODY_PART_TYPE_SHOULDER = "3";

    public static final String GET_MACHINE_DETAILS = "GET_MACHINE_DETAILS";
    public static final String GET_EXERCISED_BODY_PARTS = "GET_EXERCISED_BODY_PARTS";
    public static final String GET_ALL_EXERCISE_LOGS = "GET_ALL_EXERCISE_LOGS";
    public static final String GET_ALL_EXERCISE_LOGS_PAGED = "GET_ALL_EXERCISE_LOGS_PAGED";
    public static final String REQUEST_FOR_SUPPORT = "REQUEST_FOR_SUPPORT";
    public static final String SUBMIT_MACHINE_FEEDBACK = "SUBMIT_MACHINE_FEEDBACK";
    public static final String SEND_EXERCISE_DETAIL = "SEND_EXERCISE_DETAIL";
    public static final String SEND_FINAL_EXERCISE_DETAIL = "SEND_FINAL_EXERCISE_DETAIL";
    //endregion

}
