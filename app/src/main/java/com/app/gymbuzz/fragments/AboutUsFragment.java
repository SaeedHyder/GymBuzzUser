package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.CMSEnt;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 5/24/2018.
 */

public class AboutUsFragment extends BaseFragment {

    @BindView(R.id.txtAboutUs)
    AnyTextView txtAboutUs;
    Unbinder unbinder;

    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (prefHelper.getCMS()==null){
            serviceHelper.enqueueCall(webService.getGymCMS(prefHelper.getUser().getGymID(),prefHelper.getUserToken()), WebServiceConstants.GET_GYM_CMS);
        }else {
            bindData(prefHelper.getCMS());
        }
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag) {
            case WebServiceConstants.GET_GYM_CMS:
                prefHelper.putUserCMS((CMSEnt) result);
                bindData((CMSEnt) result);
                break;
        }
    }

    private void bindData(CMSEnt result) {
        txtAboutUs.setText(result.getAbout() + "");
        txtAboutUs.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.about_us));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
