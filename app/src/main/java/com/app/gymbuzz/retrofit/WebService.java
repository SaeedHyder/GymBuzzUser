package com.app.gymbuzz.retrofit;


import com.app.gymbuzz.entities.ResponseWrapper;
import com.app.gymbuzz.entities.UserLogEnt;
import com.app.gymbuzz.entities.UserModel;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface WebService {

    //region Exercise Logs Module
    @GET("Exercise/GetUserExerciseLog")
    Call<ResponseWrapper<ArrayList<UserLogEnt>>> getAllExerciseLogs(@Query("fromDate") String fromDate,
                                                                    @Query("toDate") String toDate,
                                                                    @Query("limit") int limit,
                                                                    @Query("pagenumber") int pagenumber,
                                                                    @Header("Authorization") String Authorization);
    //endregion

    //region User Module
    @FormUrlEncoded
    @POST("User/ValidateUser")
    Call<ResponseWrapper<UserModel>> loginUser(@Field("email") String email, @Field("password") String password,
                                               @Field("deviceID") String deviceID, @Field("deviceType") int deviceType);

    @FormUrlEncoded
    @POST("User/Signup")
    Call<ResponseWrapper<UserModel>> registerUser(@Field("FullName") String FullName, @Field("Email") String Email,
                                                  @Field("PhoneNumber") String PhoneNumber, @Field("CountryCode") int CountryCode,
                                                  @Field("Password") String Password, @Field("RoleID") int RoleID,
                                                  @Field("DeviceID") String deviceID, @Field("DeviceType") int deviceType,
                                                  @Field("LanguageCode") String LanguageCode);


    @GET("User/ResendCode")
    Call<ResponseWrapper> resendCode(@Query("email") String email, @Query("type") int type);


    @GET("User/ForgotPassword")
    Call<ResponseWrapper> forgotPassword(@Query("email") String email);

    @GET("User/VerifyUser")
    Call<ResponseWrapper<UserModel>> verifyUser(@Query("email") String email, @Query("verificationCode") String verificationCode);

    @GET("User/VerifyOTPcodeByEmail")
    Call<ResponseWrapper<UserModel>> verifyUserForgot(@Query("email") String email, @Query("otpcode") String otpcode);


    @FormUrlEncoded
    @POST("User/ChangePassword")
    Call<ResponseWrapper> changePassword(@Query("oldpassword") String oldpassword, @Query("newpassword") String newpassword,
                                         @Header("Authorization") String Authorization);

    @Multipart
    @POST("User/EditUserProfile")
    Call<ResponseWrapper<UserModel>> editProfile(@Part("FullName") RequestBody FullName,
                                                 @Part("Gender") RequestBody Gender,
                                                 @Part("DOB") RequestBody DOB,
                                                 @Part("Height") RequestBody Height,
                                                 @Part("Weight") RequestBody Weight,
                                                 @Part MultipartBody.Part userprofileImage,
                                                 @Header("Authorization") String Authorization);

    //endregion
}