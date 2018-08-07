package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.helpers.DialogHelper;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/24/2018.
 */

public class SettingFragment extends BaseFragment {


    @BindView(R.id.txtViewProfile)
    AnyTextView txtViewProfile;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.txtUnits)
    AnyTextView txtUnits;
    @BindView(R.id.cbLbs)
    CheckBox cbLbs;
    @BindView(R.id.cbKgs)
    CheckBox cbKgs;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.txtNotification)
    AnyTextView txtNotification;
    @BindView(R.id.toggleNotification)
    ToggleButton toggleNotification;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.txtChangePassword)
    AnyTextView txtChangePassword;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.txtAboutUs)
    AnyTextView txtAboutUs;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.txtContactUs)
    AnyTextView txtContactUs;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.txtLogout)
    AnyTextView txtLogout;
    Unbinder unbinder;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);


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
        titleBar.setSubHeading(getString(R.string.settings));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }

    @OnClick({R.id.txtViewProfile, R.id.txtChangePassword, R.id.txtAboutUs, R.id.txtContactUs, R.id.txtLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.txtViewProfile:
                getDockActivity().replaceDockableFragment(ViewProfileFragment.newInstance(), ViewProfileFragment.class.getSimpleName());
                break;

            case R.id.txtChangePassword:
                getDockActivity().replaceDockableFragment(ChangePasswordFragment.newInstance(), ChangePasswordFragment.class.getSimpleName());
                break;

            case R.id.txtAboutUs:
                getDockActivity().replaceDockableFragment(AboutUsFragment.newInstance(), AboutUsFragment.class.getSimpleName());
                break;

            case R.id.txtContactUs:
                getDockActivity().replaceDockableFragment(ContactusFragment.newInstance(), ContactusFragment.class.getSimpleName());
                break;

            case R.id.txtLogout:


                final DialogHelper dialogHelper = new DialogHelper(getMainActivity());
                dialogHelper.initlogout(R.layout.dialog_logout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialogHelper.hideDialog();
                        prefHelper.setLoginStatus(false);
                        getDockActivity().popBackStackTillEntry(0);
                        getDockActivity().addDockableFragment(LoginFragment.newInstance(), "LoginFragment");

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogHelper.hideDialog();
                    }
                });

                dialogHelper.showDialog();


                break;
        }
    }
}
