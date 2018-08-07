package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.ui.adapters.AdapterEventImages;
import com.app.gymbuzz.ui.views.ExtendedViewPager;
import com.app.gymbuzz.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 5/29/2018.
 */

public class ImageViewFragment extends BaseFragment {

    @BindView(R.id.vpGym)
    ExtendedViewPager vpGym;
    Unbinder unbinder;

    public static ImageViewFragment newInstance() {
        return new ImageViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image_view, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int []imageList = {R.drawable.gym_floor_1, R.drawable.gym_floor_2, R.drawable.gym_floor_1};

        AdapterEventImages adapterEventImages = new AdapterEventImages(getMainActivity(), imageList);
        if (adapterEventImages != null)
        {
            vpGym.setAdapter(adapterEventImages);
            adapterEventImages.notifyDataSetChanged();
        }
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
}
