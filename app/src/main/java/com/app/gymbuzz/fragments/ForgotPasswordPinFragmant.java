package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.UserModel;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.retrofit.WebService;
import com.app.gymbuzz.ui.views.PinEntryEditText;
import com.app.gymbuzz.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ForgotPasswordPinFragmant extends BaseFragment {
    @BindView(R.id.txt_pin_code)
    PinEntryEditText txtPinCode;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    Unbinder unbinder;
    private String emailAddress;

    public static ForgotPasswordPinFragmant newInstance(String email) {
        Bundle args = new Bundle();

        ForgotPasswordPinFragmant fragment = new ForgotPasswordPinFragmant();
        fragment.setArguments(args);
        fragment.setEmailAddress(email);
        return fragment;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password_pin, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.forgotPassword));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSubmit.setEnabled(false);
        txtPinCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() >= 4) {
                    btnSubmit.setEnabled(true);
                } else {
                    btnSubmit.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag){
            case WebServiceConstants.FORGOT_PASSWORD_VERIFY_CODE:
                UserModel user = (UserModel) result;
                prefHelper.putUser(user);
                prefHelper.setUserToken(WebServiceConstants.TOKEN_TYPE + user.getAuthtoken());
                getDockActivity().replaceDockableFragment(ChangeForgotPasswordFragment.newInstance(), "ChangeForgotPasswordFragment");
                break;
        }
    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {
        serviceHelper.enqueueCall(webService.verifyUserForgot(emailAddress,txtPinCode.getText().toString()), WebServiceConstants.FORGOT_PASSWORD_VERIFY_CODE);
    }
}