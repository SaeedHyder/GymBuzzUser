package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.helpers.DatePickerHelper;
import com.app.gymbuzz.ui.binders.BinderGymDetail;
import com.app.gymbuzz.ui.binders.FilterBinder;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.CustomRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> exerciseCollection = new ArrayList<>();
        exerciseCollection.add("Chest");
        exerciseCollection.add("Leg");
        exerciseCollection.add("Nest");
        exerciseCollection.add("Arm");
        exerciseCollection.add("Stomach");
        exerciseCollection.add("Collar");
        rvExercise.BindRecyclerView(new FilterBinder(), exerciseCollection,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false),
                new DefaultItemAnimator());
        rvExercise.setNestedScrollingEnabled(false);
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txtSatartDate, R.id.txtEndDate, R.id.btnApply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtSatartDate:
                initDatePicker(txtSatartDate,true);
                break;
            case R.id.txtEndDate:
                initDatePicker(txtSatartDate,false);
                break;
            case R.id.btnApply:
                getMainActivity().closeDrawer();
                break;
        }
    }
}