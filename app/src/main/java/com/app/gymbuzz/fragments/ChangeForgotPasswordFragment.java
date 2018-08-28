package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.views.AnyEditTextView;
import com.app.gymbuzz.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChangeForgotPasswordFragment extends BaseFragment {
    @BindView(R.id.edtNewPassword)
    AnyEditTextView edtNewPassword;
    @BindView(R.id.edtConfirmPassword)
    AnyEditTextView edtConfirmPassword;
    @BindView(R.id.btnSave)
    Button btnSave;
    Unbinder unbinder;

    public static ChangeForgotPasswordFragment newInstance() {
        Bundle args = new Bundle();

        ChangeForgotPasswordFragment fragment = new ChangeForgotPasswordFragment();
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
        View view = inflater.inflate(R.layout.fragment_forgot_change_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.forgotPassword));
    }

    public boolean isvalidated() {

        if (edtNewPassword.getText().toString().length() >= 6) {
            if (edtConfirmPassword.getText().toString().length() >= 6) {
                if (edtNewPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
                    return true;
                } else {
                    UIHelper.showShortToastInCenter(getMainActivity(), getString(R.string.password_not_equal));
                }
            } else {
                edtConfirmPassword.setError(getString(R.string.password_length));
            }
        } else {
            edtNewPassword.setError(getString(R.string.password_length));
        }
        return false;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag){
            case WebServiceConstants.CHANGE_PASSWORD:
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(HomeMenuFragment.newInstance(), "HomeMenuFragment");
                break;
        }
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
       serviceHelper.enqueueCall(webService.changePassword("",edtConfirmPassword.getText().toString(),prefHelper.getUserToken()), WebServiceConstants.CHANGE_PASSWORD);
    }
}