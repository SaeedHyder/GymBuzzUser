package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.views.AnyEditTextView;
import com.app.gymbuzz.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/24/2018.
 */

public class RequestSupportFragment extends BaseFragment {

    ArrayList priorityList = new ArrayList() ;
    ArrayAdapter<String> priorityAdapter;

    @BindView(R.id.edtFullName)
    AnyEditTextView edtFullName;
    @BindView(R.id.tilFullName)
    TextInputLayout tilFullName;
    @BindView(R.id.edtExtercise)
    AnyEditTextView edtExtercise;
    @BindView(R.id.tilExtercise)
    TextInputLayout tilExtercise;
    @BindView(R.id.edtLocation)
    AnyEditTextView edtLocation;
    @BindView(R.id.tilLocation)
    TextInputLayout tilLocation;
    @BindView(R.id.spPriority)
    Spinner spPriority;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.edtFeedBack)
    AnyEditTextView edtFeedBack;
    @BindView(R.id.llFeedback)
    LinearLayout llFeedback;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    Unbinder unbinder;

    private String priority;


    public static RequestSupportFragment newInstance() {
        return new RequestSupportFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_request_support, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setPriorityData();

        spPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position){

                    case 0:
                        priority = "";
                    break;

                    case 1:
                        priority = "Low";
                    break;

                    case 3:
                        priority = "Medium";
                    break;

                    case 4:
                        priority = "Heigh";
                    break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.req_support));
    }

    private void setPriorityData() {

        priorityList = new ArrayList<String>();

        priorityList.add("Priority");
        priorityList.add("Low");
        priorityList.add("Medium");
        priorityList.add("Heigh");

        priorityAdapter = new ArrayAdapter<String>(getDockActivity(), R.layout.spinner_item, priorityList);
        priorityAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spPriority.setAdapter(priorityAdapter);
        spPriority.setSelection(0);
        priorityAdapter.notifyDataSetChanged();

    }

    boolean isValidated(){

        if(edtFullName.testValidity()){
            if(edtExtercise.testValidity()){
                if(edtLocation.testValidity()) {
                    if(priority.length() > 0) {
                        if(edtFeedBack.testValidity()) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // unbinder.unbind();
    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {

        if(isValidated()){
            edtFullName.setText("");
            edtExtercise.setText("");
            edtLocation.setText("");
            priority = "";
            spPriority.setSelection(0);
            edtFeedBack.setText("");
            UIHelper.showShortToastInCenter(getMainActivity(),getString(R.string.will_be_imp_beta));
        }

    }
}
