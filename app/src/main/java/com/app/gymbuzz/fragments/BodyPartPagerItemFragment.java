package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.WebServiceConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BodyPartPagerItemFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.imgBody)
    ImageView imgBody;
    @BindView(R.id.ivBodyPart)
    ImageView ivBodyPart;

    private boolean isFrontShowing = true;
    private String bodyPartID = "";

    public static BodyPartPagerItemFragment newInstance(String bodyPartID) {
        Bundle args = new Bundle();

        BodyPartPagerItemFragment fragment = new BodyPartPagerItemFragment();
        fragment.setArguments(args);
        fragment.setBodyPartID(bodyPartID);
        return fragment;
    }

    public void setBodyPartID(String bodyPartID) {
        this.bodyPartID = bodyPartID;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    private int getFrontBodyPartResourceFromID(String id) {
        switch (id) {
            case WebServiceConstants.BODY_PART_TYPE_BICEP:
                return R.drawable.bicep_front;
            case WebServiceConstants.BODY_PART_TYPE_CHEST:
                return R.drawable.chest_front;
            case WebServiceConstants.BODY_PART_TYPE_SHOULDER:
                return R.drawable.shoulder_front;
            default:
                return R.drawable.full_front;


        }
    }

    private int getBackBodyPartResourceFromID(String id) {
        switch (id) {
            case WebServiceConstants.BODY_PART_TYPE_BICEP:
                return R.drawable.bicep_back;
            case WebServiceConstants.BODY_PART_TYPE_CHEST:
                return R.drawable.chest_front;
            case WebServiceConstants.BODY_PART_TYPE_SHOULDER:
                return R.drawable.shoulder_front;
            default:
                return R.drawable.full_front;


        }
    }

    private void setBodyResourceOnImageView() {
        if (isFrontShowing) {
            ivBodyPart.setImageResource(getBackBodyPartResourceFromID(bodyPartID));
            imgBody.setImageResource(R.drawable.full_front);
        } else {
            ivBodyPart.setImageResource(getFrontBodyPartResourceFromID(bodyPartID));
            imgBody.setImageResource(R.drawable.full_back);
        }

        imgBody.invalidate();
        ivBodyPart.invalidate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_part_pager_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((WorkoutSummaryFragment) getParentFragment()).setOnBodyPartViewChangeListener(() -> {
            isFrontShowing = !isFrontShowing;
            setBodyResourceOnImageView();
        });
     /*   isFrontShowing = !isFrontShowing;
      */
        setBodyResourceOnImageView();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}