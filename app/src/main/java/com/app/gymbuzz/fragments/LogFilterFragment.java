package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.helpers.DateHelper;
import com.app.gymbuzz.helpers.DatePickerHelper;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.interfaces.OnFilterSetListener;
import com.app.gymbuzz.ui.binders.FilterBinder;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.CustomRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LogFilterFragment extends BaseFragment {
    @BindView(R.id.txtSatartDate)
    AnyTextView txtSatartDate;
    @BindView(R.id.txtEndDate)
    AnyTextView txtEndDate;
    @BindView(R.id.rvExercise)
    CustomRecyclerView rvExercise;
    Unbinder unbinder;
    String startDate = "";
    String endDate = "";
    boolean isFilterByBodyPart = false;
    private ArrayList<String> exerciseCollection;
    private FilterBinder filterBinder;
    private OnFilterSetListener filterSetListener;
    private OnFilterSetListener.onFilterCheckListener checkChangeListener = (isChecked -> {
        isFilterByBodyPart = true;
        txtSatartDate.setText(R.string.start_date);
        txtEndDate.setText(R.string.end_date);
        startDate = null;
        endDate = null;
    });


    public void setFilterSetListener(OnFilterSetListener filterSetListener) {
        this.filterSetListener = filterSetListener;
    }

    public static LogFilterFragment newInstance() {
        Bundle args = new Bundle();

        LogFilterFragment fragment = new LogFilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_filter, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void setExerciseCollection(ArrayList<String> exerciseCollection) {
        this.exerciseCollection.clear();
        this.exerciseCollection.addAll(exerciseCollection);
        if (rvExercise != null) {
            rvExercise.notifyDataSetChanged();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filterBinder = new FilterBinder(checkChangeListener);
        exerciseCollection = new ArrayList<>();
        rvExercise.bindRecyclerView(filterBinder, exerciseCollection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false),
                new DefaultItemAnimator());
        rvExercise.setNestedScrollingEnabled(false);
    }

    private void initDatePicker(final AnyTextView textView, final boolean isStartDate) {
        if (filterBinder != null) {
            filterBinder.clearFilterIDs();
            rvExercise.notifyDataSetChanged();
            isFilterByBodyPart = false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 1);
        Date maxDate = new Date();
        Date minDate = null;
        if (isStartDate) {
            maxDate = new Date();
        } else {
            minDate = DateHelper.stringToDate(startDate, AppConstants.LOG_SEARCH_DATE_FORMAT);
        }
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
                        startDate = year + "-" + _month + "-" + dayOfMonth;
                        endDate = null;
                        txtEndDate.setText(R.string.end_date);
                    } else {
                        endDate = year + "-" + _month + "-" + dayOfMonth;
                    }

                    textView.setText(dayOfMonth + "-" + _month + "-" + year);
                }, "PreferredDate", maxDate, minDate);

        datePickerHelper.showDate();
    }

    void applyFilters() {
        if (filterSetListener != null && filterBinder != null) {
            if (isFilterByBodyPart && !filterBinder.isAllClear()) {
                filterSetListener.onFilterChange(startDate, endDate, filterBinder.getFilterCheckIDs(), isFilterByBodyPart);
            } else if (!stringNullOrEmpty(startDate) && !stringNullOrEmpty(endDate)) {
                filterSetListener.onFilterChange(startDate, endDate, filterBinder.getFilterCheckIDs(), isFilterByBodyPart);
            }else {
                UIHelper.showShortToastInCenter(getDockActivity(),getString(R.string.selectOptionFilter));
                return;
            }

        }
        getMainActivity().closeDrawer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtSatartDate, R.id.txtEndDate, R.id.btnApply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtSatartDate:
                initDatePicker(txtSatartDate, true);
                break;
            case R.id.txtEndDate:
                initDatePicker(txtEndDate, false);
                break;
            case R.id.btnApply:
                applyFilters();
                break;
        }
    }
}
