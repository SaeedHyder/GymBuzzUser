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
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.views.PinEntryEditText;
import com.app.gymbuzz.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VerifyEmailFragment extends BaseFragment {
    @BindView(R.id.txt_pin_code)
    PinEntryEditText txtPinCode;
    Unbinder unbinder;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    private String emailAddress = "";

    public static VerifyEmailFragment newInstance(String emailAddress) {
        Bundle args = new Bundle();

        VerifyEmailFragment fragment = new VerifyEmailFragment();
        fragment.setArguments(args);
        fragment.setEmailAddress(emailAddress);
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
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.verify_email_title));
        titleBar.showBackButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verify_email, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
        switch (Tag) {
            case WebServiceConstants.VERIFY_CODE_SIGNUP:
                UserModel user = (UserModel) result;
                prefHelper.putUser(user);
                prefHelper.setUserToken(WebServiceConstants.TOKEN_TYPE +" "+ user.getAuthtoken());
                prefHelper.setLoginStatus(true);
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(HomeMenuFragment.newInstance(), HomeMenuFragment.class.getSimpleName());
                break;
            case WebServiceConstants.RESEND_CODE:
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.email_code_send_message));
                break;
        }
    }

    @OnClick({R.id.btnSubmit, R.id.btnResentCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                serviceHelper.enqueueCall(webService.verifyUser(emailAddress, txtPinCode.getText().toString()), WebServiceConstants.VERIFY_CODE_SIGNUP);
                break;
            case R.id.btnResentCode:
                serviceHelper.enqueueCall(webService.resendCode(emailAddress, WebServiceConstants.RESEND_CODE_TYPE_SIGNUP), WebServiceConstants.RESEND_CODE);
                break;
        }
    }
}