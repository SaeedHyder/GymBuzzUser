package com.app.gymbuzz.fragments;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.ExserciseLogEnt;
import com.app.gymbuzz.entities.UserLogEnt;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.helpers.DatePickerHelper;
import com.app.gymbuzz.ui.adapters.ArrayListExpandableAdapter;
import com.app.gymbuzz.ui.binders.BinderUserLog;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.CustomExpandableListView;
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
    CustomExpandableListView elLog;
    Unbinder unbinder;

    String startDate = "";
    String endDate = "";
    @BindView(R.id.ivBody)
    ImageView ivBody;
    @BindView(R.id.imgPrevious)
    ImageView imgPrevious;
    @BindView(R.id.txtItemHeading)
    AnyTextView txtItemHeading;
    @BindView(R.id.imgNext)
    ImageView imgNext;

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
        DisplayMetrics metrics = new DisplayMetrics();
        getMainActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            elLog.setIndicatorBounds(width - GetPixelFromDips(35), width - GetPixelFromDips(5));
        } else {
            elLog.setIndicatorBoundsRelative(width - GetPixelFromDips(35), width - GetPixelFromDips(5));
        }

        serviceHelper.enqueueCall(webService.getAllExerciseLogs("2018-1-1","2018-8-30",10,1,"bearer STc2WVpRRw=="), WebServiceConstants.GET_ALL_EXERCISE_LOGS);

    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag){
            case WebServiceConstants.GET_ALL_EXERCISE_LOGS:
                filterAndBindLogData((ArrayList<UserLogEnt>)result);
                break;
        }
    }

    private void filterAndBindLogData(ArrayList<UserLogEnt> result) {
        for (int i = 0; i < result.size(); i++) {

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
        titleBar.showFilterButton();
        titleBar.showRightButton(R.drawable.nav, false, view -> {

            if (AppConstants.isShow) {
                AppConstants.isShow = false;
                titleBar.updateRightButton(R.drawable.nav);
                ivBody.setVisibility(View.GONE);
            } else {
                AppConstants.isShow = true;
                titleBar.updateRightButton(R.drawable.builder);
                ivBody.setVisibility(View.VISIBLE);
            }


        });

    }

    private void setLogData() {

        collectionGroup = new ArrayList<>();
        listDataChild = new HashMap<>();

        collectionGroup.add("Chest");
        collectionGroup.add("legs");
        collectionGroup.add("Chest1");
        collectionGroup.add("legs2");
        collectionGroup.add("Chest3");
        collectionGroup.add("legs4");
        collectionGroup.add("Chest5");
        collectionGroup.add("legs6");
        collectionGroup.add("Chest7");
        collectionGroup.add("legs8");

        ArrayList<ExserciseLogEnt> list1 = new ArrayList<>();
        list1.add(new ExserciseLogEnt("Chest Fly", "1", "55", "02"));
        list1.add(new ExserciseLogEnt("Bench Press", "2", "55", "02"));

        ArrayList<ExserciseLogEnt> list2 = new ArrayList<>();
        list2.add(new ExserciseLogEnt("Chest Fly", "3", "55", "02"));
        list2.add(new ExserciseLogEnt("Bench Press", "4", "55", "02"));
        ArrayList<ExserciseLogEnt> list3 = new ArrayList<>();
        list3.add(new ExserciseLogEnt("Chest Fly", "5", "55", "02"));
        list3.add(new ExserciseLogEnt("Bench Press", "5", "55", "02"));
        ArrayList<ExserciseLogEnt> list4 = new ArrayList<>();
        list4.add(new ExserciseLogEnt("Chest Fly", "7", "55", "02"));
        list4.add(new ExserciseLogEnt("Bench Press", "8", "55", "02"));
        ArrayList<ExserciseLogEnt> list5 = new ArrayList<>();
        list5.add(new ExserciseLogEnt("Chest Fly", "9", "55", "02"));
        list5.add(new ExserciseLogEnt("Bench Press", "10", "55", "02"));
        ArrayList<ExserciseLogEnt> list6 = new ArrayList<>();
        list6.add(new ExserciseLogEnt("Chest Fly", "11", "55", "02"));
        list6.add(new ExserciseLogEnt("Bench Press", "12", "55", "02"));
        ArrayList<ExserciseLogEnt> list7 = new ArrayList<>();
        list7.add(new ExserciseLogEnt("Chest Fly", "13", "55", "02"));
        list7.add(new ExserciseLogEnt("Bench Press", "14", "55", "02"));
        ArrayList<ExserciseLogEnt> list8 = new ArrayList<>();
        list8.add(new ExserciseLogEnt("Chest Fly", "15", "55", "02"));
        list8.add(new ExserciseLogEnt("Bench Press", "16", "55", "02"));
        ArrayList<ExserciseLogEnt> list9 = new ArrayList<>();
        list9.add(new ExserciseLogEnt("Chest Fly", "17", "55", "02"));
        list9.add(new ExserciseLogEnt("Bench Press", "18", "55", "02"));
        ArrayList<ExserciseLogEnt> list10 = new ArrayList<>();
        list10.add(new ExserciseLogEnt("Chest Fly", "19", "55", "02"));
        list10.add(new ExserciseLogEnt("Bench Press", "20", "55", "02"));

        listDataChild.put(collectionGroup.get(0), list1);
        listDataChild.put(collectionGroup.get(1), list2);
        listDataChild.put(collectionGroup.get(2), list3);
        listDataChild.put(collectionGroup.get(3), list4);
        listDataChild.put(collectionGroup.get(4), list5);
        listDataChild.put(collectionGroup.get(5), list6);
        listDataChild.put(collectionGroup.get(6), list7);
        listDataChild.put(collectionGroup.get(7), list8);
        listDataChild.put(collectionGroup.get(8), list9);
        listDataChild.put(collectionGroup.get(9), list10);


        bindDataUserInfo();

    }


    private void initDatePicker(final AnyTextView textView, final boolean isStartDate) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 1);

        Calendar calendar = Calendar.getInstance();
        final DatePickerHelper datePickerHelper = new DatePickerHelper();
        datePickerHelper.initDateDialog(
                getDockActivity(),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
                , (view, year, month, dayOfMonth) -> {
                    //textView.setText(datePickerHelper.getStringDate(year, month, dayOfMonth));

                    month = month + 1;

                    String _month = "";

                    if (month < 10) {
                        _month = "0" + month;
                    } else {
                        _month = month + "";
                    }

                    if (isStartDate) {
                        startDate = dayOfMonth + "-" + _month + "-" + year;
                    } else {
                        endDate = dayOfMonth + "-" + _month + "-" + year;
                    }

                    textView.setText(dayOfMonth + "-" + _month + "-" + year);

                }, "PreferredDate", gc.getTime(), gc.getTime());

        datePickerHelper.showDate();
    }

    public void bindDataUserInfo() {

        BinderUserLog binder = new BinderUserLog(getDockActivity());
        adapter = new ArrayListExpandableAdapter<>(getDockActivity(), collectionGroup, listDataChild, binder, elLog);
        elLog.setExpanded(true);
        elLog.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++)
            elLog.expandGroup(i);
        elLog.setOnGroupClickListener((parent, v, groupPosition, id) -> true);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtSatartDate, R.id.txtEndDate,R.id.imgPrevious, R.id.txtItemHeading, R.id.imgNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtSatartDate:

                initDatePicker(txtSatartDate, true);

                break;
            case R.id.txtEndDate:

                initDatePicker(txtEndDate, false);

                break;
            case R.id.imgPrevious:
                break;
            case R.id.txtItemHeading:
                initDatePicker(txtItemHeading, true);
                break;
            case R.id.imgNext:
                break;
        }
    }


}
