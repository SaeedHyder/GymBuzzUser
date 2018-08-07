package com.app.gymbuzz.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.ExserciseLogEnt;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.helpers.DatePickerHelper;
import com.app.gymbuzz.ui.adapters.ArrayListExpandableAdapter;
import com.app.gymbuzz.ui.adapters.ArrayListExpandableAdapterOld;
import com.app.gymbuzz.ui.binders.BinderUserLog;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/28/2018.
 */

public class UserLogFragment extends BaseFragment {

    @BindView(R.id.txtDateRange)
    AnyTextView txtDateRange;
    @BindView(R.id.txtSatartDate)
    AnyTextView txtSatartDate;
    @BindView(R.id.txtEndDate)
    AnyTextView txtEndDate;
    @BindView(R.id.llHeadder)
    LinearLayout llHeadder;
    @BindView(R.id.elLog)
    ExpandableListView elLog;
    Unbinder unbinder;

    String startDate = "";
    String endDate = "";

    private ArrayListExpandableAdapter<String, ExserciseLogEnt> adapter;
    private ArrayList<String> collectionGroup;

    private HashMap<String, ArrayList<ExserciseLogEnt>> listDataChild;

    public static UserLogFragment newInstance() {
        return new UserLogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_log, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setLogData();

        getMainActivity().setBackground(R.color.colorLogItemBg);

        DisplayMetrics metrics = new DisplayMetrics();
        getMainActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            elLog.setIndicatorBounds(width-GetPixelFromDips(35), width-GetPixelFromDips(5));
        } else {
            elLog.setIndicatorBoundsRelative(width-GetPixelFromDips(35), width-GetPixelFromDips(5));
        }

    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public void setTitleBar(final TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.log));

        titleBar.showRightButton(R.drawable.nav, true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (AppConstants.isShow) {
                    AppConstants.isShow = false;
                    titleBar.updateRightButton(R.drawable.nav);
                }
                else {
                    AppConstants.isShow = true;
                    titleBar.updateRightButton(R.drawable.builder);
                }

                adapter.notifyDataSetChanged();
            }
        });

    }

    private void setLogData() {

        collectionGroup = new ArrayList<>();
        listDataChild = new HashMap<>();

        collectionGroup.add("Chest");
        collectionGroup.add("legs");

        ArrayList<ExserciseLogEnt> list1 = new ArrayList<>();
        list1.add(new ExserciseLogEnt("Chest Fly", "10", "55", "02"));
        list1.add(new ExserciseLogEnt("Bench Press", "10", "55", "02"));

        ArrayList<ExserciseLogEnt> list2 = new ArrayList<>();
        list2.add(new ExserciseLogEnt("Chest Fly", "10", "55", "02"));
        list2.add(new ExserciseLogEnt("Bench Press", "10", "55", "02"));

        listDataChild.put(collectionGroup.get(0), list1);
        listDataChild.put(collectionGroup.get(1), list2);


        bindDataUserInfo();

    }

    private void initDatePicker(final AnyTextView textView,final boolean isStartDate) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 1);

        Calendar calendar = Calendar.getInstance();
        final DatePickerHelper datePickerHelper = new DatePickerHelper();
        datePickerHelper.initDateDialog(
                getDockActivity(),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
                , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //textView.setText(datePickerHelper.getStringDate(year, month, dayOfMonth));

                        month = month + 1;

                        String _month = "";

                        if(month<10){
                            _month = "0"+month;
                        }else{
                            _month = month+"";
                        }

                        if(isStartDate) {
                            startDate = dayOfMonth + "-" + _month + "-" + year;
                        }else{
                            endDate = dayOfMonth + "-" + _month + "-" + year;
                        }

                        textView.setText(dayOfMonth + "-" + _month + "-" + year);

                    }
                }, "PreferredDate", gc.getTime(), gc.getTime());

        datePickerHelper.showDate();
    }

    public void bindDataUserInfo() {

        BinderUserLog binder = new BinderUserLog(getDockActivity());
        adapter = new ArrayListExpandableAdapter<>(getDockActivity(), collectionGroup, listDataChild, binder, elLog);
        elLog.setAdapter(adapter);
        elLog.isGroupExpanded(0);
       /* elLog.expandGroup(0);
        elLog.expandGroup(1);*/
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtSatartDate, R.id.txtEndDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtSatartDate:

                initDatePicker(txtSatartDate,true);

                break;
            case R.id.txtEndDate:

                initDatePicker(txtEndDate,false);

                break;
        }
    }
}
