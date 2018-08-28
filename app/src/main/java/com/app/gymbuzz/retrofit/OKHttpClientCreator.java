package com.app.gymbuzz.retrofit;


import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.app.gymbuzz.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKHttpClientCreator {

    private static NotificationManager mNotifyManager;
    private static NotificationCompat.Builder mBuilder;

    public static OkHttpClient createCustomInterceptorClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Accept-Encoding", "identity").build();
                return chain.proceed(request);
            }
        });
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new CustomInterceptor(progressListener));
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        /*OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CustomInterceptor(progressListener))
                .build();*/


        return builder.build();


    }
    public static OkHttpClient createDefaultInterceptorClient(Context context) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse.newBuilder()
                                .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                                .build();
                    }
                })
                .build();


        return client;

    }

    final static ProgressResponseBody.ProgressListener progressListener = (bytesRead, contentLength, done) -> {
       // int percent = (int) ((100 * bytesRead) / contentLength);
    };

}
