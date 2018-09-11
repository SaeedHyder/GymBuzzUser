package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.helpers.DateHelper;
import com.app.gymbuzz.interfaces.OnBodyPartViewChangeListener;
import com.app.gymbuzz.ui.adapters.ViewPagerAdapter;
import com.app.gymbuzz.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/24/2018.
 */

public class WorkoutSummaryFragment extends BaseFragment {


    @BindView(R.id.btnChangeView)
    Button btnChangeView;
    @BindView(R.id.viewPagerImage)
    ViewPager viewPagerImage;
    Unbinder unbinder;

    private ArrayList<BaseFragment> pagesArray;
    private ViewPagerAdapter adapter;
    private ArrayList<OnBodyPartViewChangeListener> onBodyPartViewChangeListener;


    public static WorkoutSummaryFragment newInstance() {
        return new WorkoutSummaryFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout_summary, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBodyPartViewChangeListener = new ArrayList<>();
        serviceHelper.enqueueCall(webService.getExercisedBodyParts(DateHelper.getFormattedDate(new Date(), AppConstants.LOG_SEARCH_DATE_FORMAT), prefHelper.getUserToken()),
                WebServiceConstants.GET_EXERCISED_BODY_PARTS);
    }

    public void setOnBodyPartViewChangeListener(OnBodyPartViewChangeListener onBodyPartViewChangeListener) {
        this.onBodyPartViewChangeListener.add(onBodyPartViewChangeListener);
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag) {
            case WebServiceConstants.GET_EXERCISED_BODY_PARTS:
                initViewPager(Arrays.asList(((String) result).split("\\s*,\\s*")));
                break;
        }
    }

    private void initViewPager(List<String> result) {
        btnChangeView.setVisibility(View.GONE);
        pagesArray = new ArrayList<>();
        for (String aResult : result) {
            pagesArray.add(BodyPartPagerItemFragment.newInstance(aResult));
        }
        if (result.size() > 0) {
            if (canChageViewFromID(result.get(0))) {
                btnChangeView.setVisibility(View.VISIBLE);
            } else {
                btnChangeView.setVisibility(View.GONE);
            }
        }
        adapter = new ViewPagerAdapter(getChildFragmentManager(), pagesArray);
        viewPagerImage.setAdapter(adapter);
        viewPagerImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (canChageViewFromID(result.get(position))) {
                    btnChangeView.setVisibility(View.VISIBLE);
                } else {
                    btnChangeView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private boolean canChageViewFromID(String id) {
        switch (id) {
            case WebServiceConstants.BODY_PART_TYPE_BICEP:
                return true;
            case WebServiceConstants.BODY_PART_TYPE_CHEST:
                return false;
            case WebServiceConstants.BODY_PART_TYPE_SHOULDER:
                return false;
            default:
                return false;


        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.you_did_great));
        titleBar.showRightTextButton(getString(R.string.done_), view -> {
            getDockActivity().popBackStackTillEntry(0);
            getDockActivity().replaceDockableFragment(HomeMenuFragment.newInstance(), HomeMenuFragment.class.getSimpleName());
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnChangeView)
    public void onViewClicked() {
        if (onBodyPartViewChangeListener != null) {
            onBodyPartViewChangeListener.get(viewPagerImage.getCurrentItem()).onChangeOfBodyPartView();
        }
    }
}
