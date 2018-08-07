package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.views.AnyEditTextView;
import com.app.gymbuzz.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/24/2018.
 */

public class ChangePasswordFragment extends BaseFragment {


    @BindView(R.id.edtOldPassword)
    AnyEditTextView edtOldPassword;
    @BindView(R.id.tilFullName)
    TextInputLayout tilFullName;
    @BindView(R.id.edtNewPassword)
    AnyEditTextView edtNewPassword;
    @BindView(R.id.tilNewPassword)
    TextInputLayout tilNewPassword;
    @BindView(R.id.edtConfirmPassword)
    AnyEditTextView edtConfirmPassword;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.btnSave)
    Button btnSave;
    Unbinder unbinder;

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_password, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.change_password));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public boolean isvalidated(){

        if(edtOldPassword.getText().toString().length() >= 6){
            if(edtNewPassword.getText().toString().length() >= 6){
                if(edtConfirmPassword.getText().toString().length() >= 6){
                    if(edtNewPassword.getText().toString().equals(edtConfirmPassword.getText().toString())){
                        return true;
                    }else{
                        UIHelper.showShortToastInCenter(getMainActivity(),getString(R.string.password_not_equal));
                    }
                }
                else{
                    edtConfirmPassword.setError(getString(R.string.password_length));
                }
            }else{
                edtNewPassword.setError(getString(R.string.password_length));
            }
        }else{
            edtOldPassword.setError(getString(R.string.password_length));
        }

        return false;
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {

        if(isvalidated()){
            UIHelper.showShortToastInCenter(getMainActivity(),getString(R.string.will_be_imp_beta));
        }

    }
}
