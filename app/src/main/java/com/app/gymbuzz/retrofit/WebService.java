package com.app.gymbuzz.retrofit;


import com.app.gymbuzz.entities.ResponseWrapper;
import com.app.gymbuzz.entities.UserLogEnt;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface WebService {

    //region Exercise Logs Module
    @GET("GetAllWalkThrough")
    Call<ResponseWrapper<ArrayList<UserLogEnt>>> getAllExerciseLogs(@Query("fromDate") String fromDate,
                                                                    @Query("toDate") String toDate,
                                                                    @Query("limit") int limit,
                                                                    @Query("pagenumber") int pagenumber,
                                                                    @Header("Authorization") String Authorization);
    //endregion


}