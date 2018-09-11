package com.app.gymbuzz.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.CMSEnt;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.helpers.DialogHelper;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.TitleBar;
import com.google.firebase.iid.FirebaseInstanceId;

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
    @BindView(R.id.txtRateUs)
    AnyTextView txtRateUs;
    @BindView(R.id.txtVersionNumber)
    AnyTextView txtVersionNumber;
    @BindView(R.id.btnFacebook)
    FrameLayout btnFacebook;
    @BindView(R.id.btnInsta)
    FrameLayout btnInsta;
    @BindView(R.id.btnSnapchat)
    FrameLayout btnSnapchat;
    @BindView(R.id.btnTwitter)
    FrameLayout btnTwitter;
    @BindView(R.id.btnYoutube)
    FrameLayout btnYoutube;

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
        try {
            PackageInfo pInfo = getDockActivity().getPackageManager().getPackageInfo(getDockActivity().getPackageName(), 0);
            txtVersionNumber.setText(pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (prefHelper.getUser() != null && prefHelper.getUser().getNotification() != null)
            toggleNotification.setChecked(prefHelper.getUser().getNotification().equals("1"));
        toggleNotification.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            serviceHelper.enqueueCall(webService.toggleNotifications(isChecked ? "1" : "0", prefHelper.getUserToken()), WebServiceConstants.TOGGLE_NOTIFICATIONS);
        });

        serviceHelper.enqueueCall(webService.getGymCMS(prefHelper.getUser().getGymID(), prefHelper.getUserToken()), WebServiceConstants.GET_GYM_CMS);
        /*if (prefHelper.getCMS() == null) {
        } else {
            bindData(prefHelper.getCMS());
        }*/

    }

    private void launchMarket() {
        Uri uri = Uri.parse("market://details?id=" + getDockActivity().getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.something_error));
        }
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag) {
            case WebServiceConstants.GET_GYM_CMS:
                prefHelper.putUserCMS((CMSEnt) result);
                bindData((CMSEnt) result);
                break;
            case WebServiceConstants.LOGOUT:
                prefHelper.setLoginStatus(false);
                prefHelper.putUserCMS(null);
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().addDockableFragment(LoginFragment.newInstance(), "LoginFragment");
                break;
        }
    }

    private void bindData(CMSEnt result) {
        btnFacebook.setTag(result.getFacebookurl());
        btnInsta.setTag(result.getInstagramurl());
        btnSnapchat.setTag(result.getSnapChatURL());
        btnTwitter.setTag(result.getTwitterurl());
        btnYoutube.setTag(result.getYoutubeurl());
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.settings));
    }


    @OnClick({R.id.txtViewProfile, R.id.txtChangePassword, R.id.txtAboutUs, R.id.txtContactUs, R.id.txtLogout, R.id.txtRateUs, R.id.btnFacebook, R.id.btnInsta, R.id.btnSnapchat, R.id.btnTwitter, R.id.btnYoutube})
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
            case R.id.txtRateUs:
                launchMarket();
                break;
            case R.id.btnFacebook:
                openSocialMediaURL(btnFacebook);
                break;
            case R.id.btnInsta:
                openSocialMediaURL(btnInsta);
                break;
            case R.id.btnSnapchat:
                openSocialMediaURL(btnSnapchat);
                break;
            case R.id.btnTwitter:
                openSocialMediaURL(btnTwitter);
                break;
            case R.id.btnYoutube:
                openSocialMediaURL(btnYoutube);
                break;
            case R.id.txtLogout:
                final DialogHelper dialogHelper = new DialogHelper(getMainActivity());
                dialogHelper.initlogout(R.layout.dialog_logout, v -> {

                    dialogHelper.hideDialog();
                    serviceHelper.enqueueCall(webService.logout(prefHelper.getUserToken(), WebServiceConstants.DEVICE_TYPE, FirebaseInstanceId.getInstance().getToken()), WebServiceConstants.LOGOUT);

                }, v -> dialogHelper.hideDialog());

                dialogHelper.showDialog();


                break;
        }
    }

    private void openSocialMediaURL(FrameLayout btnSocialMedia) {
        try {
            if (btnSocialMedia.getTag() == null) {
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.something_error));
                return;
            }
            if (Patterns.WEB_URL.matcher((String) btnSocialMedia.getTag()).matches()) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse((String) btnSocialMedia.getTag())));
            } else {
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.invalidURL));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
