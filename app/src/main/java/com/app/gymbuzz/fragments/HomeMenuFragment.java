package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Guideline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.TitleBar;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/24/2018.
 */

public class HomeMenuFragment extends BaseFragment {

    @BindView(R.id.ivNotification)
    ImageView ivNotification;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.glLogo)
    Guideline glLogo;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.glskeletons)
    Guideline glskeletons;
    @BindView(R.id.btnMembership)
    AnyTextView btnMembership;
    @BindView(R.id.btnWrkout)
    AnyTextView btnWrkout;
    @BindView(R.id.btnLog)
    AnyTextView btnLog;
    @BindView(R.id.btnGuide)
    AnyTextView btnGuide;
    Unbinder unbinder;

    public static HomeMenuFragment newInstance() {
        return new HomeMenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_menu, container, false);


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
        titleBar.hideTitleBar();
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.view_profile));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.ivNotification, R.id.btnMembership, R.id.btnWrkout, R.id.btnLog, R.id.btnGuide, R.id.btnBMI, R.id.btnSetting})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ivNotification:
                getDockActivity().replaceDockableFragment(NotificationsFragment.newInstance(), "NotificationsFragment");
                break;

            case R.id.btnMembership:

                openQRFragment(false);


                break;

            case R.id.btnWrkout:

                openQRFragment(true);
                break;

            case R.id.btnLog:
                getDockActivity().replaceDockableFragment(UserLogFragment.newInstance(), UserLogFragment.class.getSimpleName());
                break;

            case R.id.btnGuide:
                UIHelper.showShortToastInCenter(getMainActivity(), getString(R.string.will_be_imp_beta));
                break;
            case R.id.btnBMI:
                getDockActivity().replaceDockableFragment(CalculateBmiFragment.newInstance(), CalculateBmiFragment.class.getSimpleName());
                break;

            case R.id.btnSetting:
                getDockActivity().replaceDockableFragment(SettingFragment.newInstance(), SettingFragment.class.getSimpleName());
                break;
        }
    }

    private void openQRFragment(boolean workout) {
        AndPermission.with(getMainActivity())
                .runtime()
                .permission(Permission.CAMERA)
                .onGranted(permissions -> {
                    ScanQRFragment scanQRFragment = ScanQRFragment.newInstance();
                    scanQRFragment.isWorkout = workout;
                    getDockActivity().replaceDockableFragment(scanQRFragment, ScanQRFragment.class.getSimpleName());
                })
                .onDenied(permissions -> {
                    UIHelper.showShortToastInCenter(getDockActivity(), "Permission is required to access this feature");
                })
                .start();

    }
}
