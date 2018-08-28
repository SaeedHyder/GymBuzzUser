package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.ui.views.AnyEditTextView;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.TitleBar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.hbb20.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SignupFragment extends BaseFragment {
    @BindView(R.id.edtName)
    AnyEditTextView edtName;
    @BindView(R.id.edtEmail)
    AnyEditTextView edtEmail;
    @BindView(R.id.edtMobile)
    AnyEditTextView edtMobile;
    @BindView(R.id.edtPassword)
    AnyEditTextView edtPassword;
    @BindView(R.id.edtConfirmPassword)
    AnyEditTextView edtConfirmPassword;
    Unbinder unbinder;
    @BindView(R.id.btnPhoneCountryPicker)
    AnyTextView btnPhoneCountryPicker;
    @BindView(R.id.phoneCountryPicker)
    CountryCodePicker phoneCountryPicker;
    private CountryCodePicker.OnCountryChangeListener countrySelectListener = this::setCountryNames;

    public static SignupFragment newInstance() {
        Bundle args = new Bundle();

        SignupFragment fragment = new SignupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    private void setCountryNames() {
        btnPhoneCountryPicker.setText(String.format("%s ( %s ) ", phoneCountryPicker.getSelectedCountryNameCode(), phoneCountryPicker.getSelectedCountryCodeWithPlus()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phoneCountryPicker.setOnCountryChangeListener(countrySelectListener);
        phoneCountryPicker.registerCarrierNumberEditText(edtMobile);
        setCountryNames();
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private boolean isValidated() {
        if (edtName.getText() == null || (edtName.getText().toString().isEmpty())) {
            if (edtName.requestFocus()) {
                setEditTextFocus(edtName);
            }
            edtName.setError(getString(R.string.enter_FullName));
            return false;
        } else if (edtEmail.getText() == null || (edtEmail.getText().toString().isEmpty()) ||
                (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches())) {
            if (edtEmail.requestFocus()) {
                setEditTextFocus(edtEmail);
            }
            edtEmail.setError(getString(R.string.enter_valid_email));
            return false;
        } else if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError(getString(R.string.enter_password));
            if (edtPassword.requestFocus()) {
                setEditTextFocus(edtPassword);
            }
            return false;
        } else if (edtPassword.getText().toString().length() < 6) {
            edtPassword.setError(getString(R.string.passwordLength));
            if (edtPassword.requestFocus()) {
                setEditTextFocus(edtPassword);
            }
            return false;
        } else if (!edtConfirmPassword.getText().toString().equals(edtPassword.getText().toString())) {
            edtConfirmPassword.setError(getString(R.string.confirm_password_error));
            if (edtConfirmPassword.requestFocus()) {
                setEditTextFocus(edtConfirmPassword);
            }
            return false;
        } else if (!edtMobile.getText().toString().equals("") && !phoneCountryPicker.isValidFullNumber()) {
            edtMobile.setError(getString(R.string.invalid_phone_number));
            if (edtMobile.requestFocus()) {
                setEditTextFocus(edtMobile);
            }
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag) {
            case WebServiceConstants.REGISTER:
                getDockActivity().replaceDockableFragment(VerifyEmailFragment.newInstance(edtEmail.getText().toString()),"VerifyEmailFragment");
                break;
        }
    }

    @OnClick({R.id.btnLogin, R.id.btnSignUp, R.id.btnPhoneCountryPicker})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(LoginFragment.newInstance(), LoginFragment.class.getSimpleName());
                break;
            case R.id.btnSignUp:
                if (isValidated()) {
                    boolean isPhoneAvailable = !edtMobile.getText().toString().equals("");
                    serviceHelper.enqueueCall(webService.registerUser(edtName.getText().toString(), edtEmail.getText().toString(),
                            isPhoneAvailable ? edtMobile.getText().toString() : null, isPhoneAvailable ? phoneCountryPicker.getSelectedCountryCodeAsInt() : 0,
                            edtConfirmPassword.getText().toString(), WebServiceConstants.ROLE_ID,
                            FirebaseInstanceId.getInstance().getToken(), WebServiceConstants.DEVICE_TYPE,
                            WebServiceConstants.LANGUAGE_CODE), WebServiceConstants.REGISTER);
                }
                break;
            case R.id.btnPhoneCountryPicker:
                phoneCountryPicker.launchCountrySelectionDialog();
                break;
        }
    }
}