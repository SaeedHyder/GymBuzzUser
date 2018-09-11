package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.GymDetailModel;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.ui.binders.GymImageBinder;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.CustomRecyclerView;
import com.app.gymbuzz.ui.views.TitleBar;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class GymDetailFragment extends BaseFragment {
    @BindView(R.id.rvGymImages)
    CustomRecyclerView rvGymImages;
    Unbinder unbinder;
    @BindView(R.id.civProfilePic)
    CircleImageView civProfilePic;
    @BindView(R.id.txtGymName)
    AnyTextView txtGymName;
    @BindView(R.id.txtGymAddress)
    AnyTextView txtGymAddress;
    @BindView(R.id.txtStatus)
    AnyTextView txtStatus;
    @BindView(R.id.txtAboutUs)
    AnyTextView txtAboutUs;
    private GymDetailModel gymDetailModel;

    public static GymDetailFragment newInstance(GymDetailModel gymDetailModel) {
        Bundle args = new Bundle();

        GymDetailFragment fragment = new GymDetailFragment();
        fragment.setArguments(args);
        fragment.setGymDetailModel(gymDetailModel);
        return fragment;
    }

    public void setGymDetailModel(GymDetailModel gymDetailModel) {
        this.gymDetailModel = gymDetailModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gym_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindGymDate();
    }

    private void bindGymDate() {
        if (gymDetailModel != null) {
            ImageLoader.getInstance().displayImage(gymDetailModel.getLogopath(), civProfilePic);
            txtGymName.setText(gymDetailModel.getGymname() + "");
            txtGymAddress.setText(gymDetailModel.getAddress() + " " + gymDetailModel.getCity());
            txtAboutUs.setText(gymDetailModel.getDescription() + "");
            rvGymImages.bindRecyclerView(new GymImageBinder(), gymDetailModel.getGymimages(),
                    new LinearLayoutManager(getDockActivity(), LinearLayoutManager.HORIZONTAL, false), new DefaultItemAnimator());
            new LinearSnapHelper().attachToRecyclerView(rvGymImages);
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.gym_detail));
        titleBar.showNotificationButton(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}