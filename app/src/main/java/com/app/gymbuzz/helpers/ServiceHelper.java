package com.app.gymbuzz.helpers;

import android.util.Log;


import com.app.gymbuzz.activities.DockActivity;
import com.app.gymbuzz.entities.ResponseWrapper;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.interfaces.webServiceResponseLisener;
import com.app.gymbuzz.retrofit.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 7/17/2017.
 */

public class ServiceHelper<T> {
    private webServiceResponseLisener serviceResponseLisener;
    private DockActivity context;
    private WebService webService;

    public ServiceHelper(webServiceResponseLisener serviceResponseLisener, DockActivity conttext, WebService webService) {
        this.serviceResponseLisener = serviceResponseLisener;
        this.context = conttext;
        this.webService = webService;
    }
    public void enqueueCall(Call<ResponseWrapper<T>> call, final String tag) {
        if (InternetHelper.CheckInternetConectivityandShowToast(context)) {
            context.onLoadingStarted();
            call.enqueue(new Callback<ResponseWrapper<T>>() {
                @Override
                public void onResponse(Call<ResponseWrapper<T>> call, Response<ResponseWrapper<T>> response) {
                    context.onLoadingFinished();
                    if (response.body() != null) {
                        if (response.body().getResponse().equalsIgnoreCase( WebServiceConstants.SUCCESS_RESPONSE_CODE)){
                            serviceResponseLisener.ResponseSuccess(response.body().getResult(), tag);
                        } else {
                            serviceResponseLisener.ResponseFailure(tag);
                            UIHelper.showShortToastInCenter(context, response.body().getMessage());
                        }
                    } else {
                        serviceResponseLisener.ResponseFailure(tag);
                        UIHelper.showShortToastInCenter(context, response.message()+"");
//                        UIHelper.showShortToastInCenter(context, context.getResources().getString(R.string.server_response_error));
                    }

                }

                @Override
                public void onFailure(Call<ResponseWrapper<T>> call, Throwable t) {
                    context.onLoadingFinished();
                    serviceResponseLisener.ResponseFailure(tag);
                    t.printStackTrace();
                    Log.e(ServiceHelper.class.getSimpleName()+" by tag: " + tag, t.toString());
                }
            });
        }
    }

}
