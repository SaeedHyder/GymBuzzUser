package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.UserModel;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.TitleBar;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created on 5/24/2018.
 */

public class ViewProfileFragment extends BaseFragment {


    @BindView(R.id.civProfilePic)
    CircleImageView civProfilePic;
    @BindView(R.id.txtName)
    AnyTextView txtName;
    @BindView(R.id.txtEmail)
    AnyTextView txtEmail;
    @BindView(R.id.txtAgeValue)
    AnyTextView txtAgeValue;
    @BindView(R.id.txtWeightValue)
    AnyTextView txtWeightValue;
    @BindView(R.id.txtHeightValue)
    AnyTextView txtHeightValue;
    Unbinder unbinder;
    @BindView(R.id.txtAboutUs)
    AnyTextView txtAboutUs;

    public static ViewProfileFragment newInstance() {
        return new ViewProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserModel user = prefHelper.getUser();
        ImageLoader.getInstance().displayImage(user.getProfileimagepath(), civProfilePic);
        txtName.setText(user.getFullname());
        txtEmail.setText(user.getEmail());
        txtHeightValue.setText(user.getHeight() + " lb");
        txtWeightValue.setText(user.getWeight() + " cm");
        txtAboutUs.setText(user.getAbout() == null ? "-" : user.getAbout());
        txtAgeValue.setText(user.getUserAge());

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.view_profile));
        titleBar.showRightButton(R.drawable.edit, view -> getDockActivity().replaceDockableFragment(UpdateProfileFragment.newInstance(), UpdateProfileFragment.class.getSimpleName()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
