package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.ui.views.AnyEditTextView;
import com.app.gymbuzz.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ForgotPasswordEmailFragment extends BaseFragment {
    @BindView(R.id.edtEmail)
    AnyEditTextView edtEmail;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    Unbinder unbinder;

    public static ForgotPasswordEmailFragment newInstance() {
        Bundle args = new Bundle();

        ForgotPasswordEmailFragment fragment = new ForgotPasswordEmailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password_email, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.forgotPassword));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag) {
            case WebServiceConstants.FORGOT_PASSWORD:
                getDockActivity().replaceDockableFragment(ForgotPasswordPinFragmant.newInstance(edtEmail.getText().toString()), "ForgotPasswordPinFragmant");
                break;
        }
    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {
        if (edtEmail.testValidity()) {
            serviceHelper.enqueueCall(webService.forgotPassword(edtEmail.getText().toString(), WebServiceConstants.ROLE_ID), WebServiceConstants.FORGOT_PASSWORD);

        }
    }
}