package com.app.gymbuzz.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.ExserciseLogEnt;
import com.app.gymbuzz.entities.GetExerciseLogAppModel;
import com.app.gymbuzz.entities.UserLogEnt;
import com.app.gymbuzz.entities.UserLogExerciseDetail;
import com.app.gymbuzz.entities.UserLogExerciseDetails;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.helpers.DateHelper;
import com.app.gymbuzz.helpers.DatePickerHelper;
import com.app.gymbuzz.interfaces.OnFilterSetListener;
import com.app.gymbuzz.ui.adapters.ArrayListExpandableAdapter;
import com.app.gymbuzz.ui.binders.BinderUserLog;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.CustomExpandableListView;
import com.app.gymbuzz.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 5/28/2018.
 */

public class UserLogFragment extends BaseFragment implements OnFilterSetListener {

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
    @BindView(R.id.ivBody)
    ImageView ivBody;
    @BindView(R.id.imgPrevious)
    ImageView imgPrevious;
    @BindView(R.id.txtItemHeading)
    AnyTextView txtItemHeading;
    @BindView(R.id.imgNext)
    ImageView imgNext;
    @BindView(R.id.parentScrollView)
    NestedScrollView parentScrollView;
    @BindView(R.id.imgBodyBackground)
    ImageView imgBodyBackground;
    @BindView(R.id.containerBody)
    FrameLayout containerBody;
    Unbinder unbinder;

    String startDate = "";
    String endDate = "";
    private int dataLimit = 10;
    private int currentPageNumber = 1;
    private boolean loadMore = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean isFilterByBodyPart;
    private int currentHeadingIndex = 0;

    private ArrayListExpandableAdapter<String, ExserciseLogEnt> adapter;
    private ArrayList<String> collectionGroup;
    private ArrayList<String> bodyPartsCollection;
    private ArrayList<String> filteredBodyPartsCollection;
    private ArrayList<String> bodyImageCollection;
    private ArrayList<String> exerciseDatesCollection;
    private ArrayList<UserLogEnt> results;
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
        DisplayMetrics metrics = new DisplayMetrics();
        getMainActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        getMainActivity().filterFragment.setFilterSetListener(this);
        int width = metrics.widthPixels;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            elLog.setIndicatorBounds(width - GetPixelFromDips(35), width - GetPixelFromDips(5));
        } else {
            elLog.setIndicatorBoundsRelative(width - GetPixelFromDips(35), width - GetPixelFromDips(5));
        }
        initAndLoadData();

    }

    private void initAndLoadData() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        currentPageNumber = 1;
        startDate = DateHelper.getDateInStringFormat(calendar.getTime(), AppConstants.LOG_SEARCH_DATE_FORMAT);
        endDate = DateHelper.getDateInStringFormat(new Date(), AppConstants.LOG_SEARCH_DATE_FORMAT);
       /* startDate = "2018-1-30";
        endDate = "2018-8-30";*/
        callAndGetLogData(WebServiceConstants.GET_ALL_EXERCISE_LOGS);

        setScrollListner();
    }

    private void callAndGetLogData(String getAllExerciseLogs) {
        serviceHelper.enqueueCall(webService.getAllExerciseLogs(startDate,
                endDate, dataLimit, currentPageNumber, prefHelper.getUserToken()), getAllExerciseLogs);
    }

    private void setScrollListner() {
        parentScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (v.getChildAt(v.getChildCount() - 1) != null) {
                if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                        scrollY > oldScrollY) {

                    visibleItemCount = elLog.getChildCount();
                    totalItemCount = elLog.getCheckedItemCount();
                    pastVisiblesItems = elLog.getFirstVisiblePosition();
                    if (loadMore) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            currentPageNumber = currentPageNumber + 1;
                            callAndGetLogData(WebServiceConstants.GET_ALL_EXERCISE_LOGS_PAGED);

                            loadMore = false;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag) {
            case WebServiceConstants.GET_ALL_EXERCISE_LOGS:
                filterAndBindLogData((ArrayList<UserLogEnt>) result);
                break;
            case WebServiceConstants.GET_ALL_EXERCISE_LOGS_PAGED:
                filterAndBindPagedLogData((ArrayList<UserLogEnt>) result);
                break;
        }
    }

    private void filterAndBindPagedLogData(ArrayList<UserLogEnt> result) {
        loadMore = true;
        this.results.addAll(result);
        for (int i = 0; i < results.size(); i++) {
            UserLogEnt logItem = results.get(i);
            addItemToCollection(logItem);
        }
        adapter.notifyDataSetChanged();
    }

    private void addItemToCollection(UserLogEnt logItem) {
        bodyImageCollection.add(logItem.getBodypartid() + "");
        if (!bodyPartsCollection.contains(logItem.getBodypartname() + ""))
            bodyPartsCollection.add(logItem.getBodypartname());
        if (!exerciseDatesCollection.contains(logItem.getExerciseDate() + ""))
            exerciseDatesCollection.add(logItem.getExerciseDate());
    }

    private void filterAndBindLogData(ArrayList<UserLogEnt> result) {
        bodyImageCollection = new ArrayList<>();
        bodyPartsCollection = new ArrayList<>();
        exerciseDatesCollection = new ArrayList<>();
        results = result;
        for (int i = 0; i < result.size(); i++) {
            UserLogEnt logItem = result.get(i);
            if (i == 0) {
                txtItemHeading.setText(result.get(i).getExerciseDate());
            }
            addItemToCollection(logItem);
        }
        getMainActivity().filterFragment.setExerciseCollection(bodyPartsCollection);
        showSelectedHeaderItemList();

    }

    private void showSelectedHeaderItemList() {
        collectionGroup = new ArrayList<>();
        listDataChild = new HashMap<>();
        for (UserLogEnt logItem :
                results) {
            ArrayList<ExserciseLogEnt> children = new ArrayList<>();
            String itemNameToLook = isFilterByBodyPart ? logItem.getBodypartname() : logItem.getExerciseDate();
            if (itemNameToLook.equals(txtItemHeading.getText().toString())) {
                ivBody.setImageResource(getBodyPartResourceFromID(String.valueOf(logItem.getBodypartid())));
                for (GetExerciseLogAppModel appModel : logItem.getGetexerciselogappmodel()
                        ) {
                    for (UserLogExerciseDetails details : appModel.getUserexercisedetails()
                            ) {
                        UserLogExerciseDetail setsDetails = details.getUserexercisedetail();
                        children.add(new ExserciseLogEnt(logItem.getBodypartname(), setsDetails.getReps() + "", setsDetails.getWeight() + "", setsDetails.getSetnumber() + ""));
                    }

                }
                if (isFilterByBodyPart) {
                    collectionGroup.add(logItem.getExerciseDate());
                    listDataChild.put(logItem.getExerciseDate(), children);
                } else {
                    collectionGroup.add(logItem.getBodypartname());
                    listDataChild.put(logItem.getBodypartname(), children);
                }
            }
        }

        bindDataUserInfo();
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
        titleBar.showRightButton(R.drawable.nav, view -> {

            if (AppConstants.isShow) {
                AppConstants.isShow = false;
                titleBar.updateRightButton(R.drawable.nav);
                containerBody.setVisibility(View.GONE);
            } else {
                AppConstants.isShow = true;
                titleBar.updateRightButton(R.drawable.builder);
                containerBody.setVisibility(View.VISIBLE);
            }


        });

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
                        startDate = year + "-" + _month + "-" + dayOfMonth;
                    } else {
                        endDate = year + "-" + _month + "-" + dayOfMonth;
                    }

                    textView.setText(year + "-" + _month + "-" + dayOfMonth);

                }, "PreferredDate", gc.getTime(), gc.getTime());

        datePickerHelper.showDate();
    }

    private int getBodyPartResourceFromID(String id) {
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

    private void setNextItem() {
        try {
            if (isFilterByBodyPart && filteredBodyPartsCollection != null) {
                changeHeadingIndex(filteredBodyPartsCollection.size(), true);
                txtItemHeading.setText(filteredBodyPartsCollection.get(currentHeadingIndex));
            } else {
                changeHeadingIndex(exerciseDatesCollection.size(), true);
                txtItemHeading.setText(exerciseDatesCollection.get(currentHeadingIndex));
            }
            showSelectedHeaderItemList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setPreviousItem() {
        try {
            if (isFilterByBodyPart && filteredBodyPartsCollection != null) {
                changeHeadingIndex(filteredBodyPartsCollection.size(), false);
                txtItemHeading.setText(filteredBodyPartsCollection.get(currentHeadingIndex));
            } else {
                changeHeadingIndex(exerciseDatesCollection.size(), false);
                txtItemHeading.setText(exerciseDatesCollection.get(currentHeadingIndex));
            }
            showSelectedHeaderItemList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeHeadingIndex(int size, boolean doIncrement) {
        size = size - 1;
        if (doIncrement) {
            if (currentHeadingIndex < size) {
                currentHeadingIndex = currentHeadingIndex + 1;
            }
        } else {
            if (currentHeadingIndex > 0) {
                currentHeadingIndex = currentHeadingIndex - 1;
            }
        }
    }

    @OnClick({R.id.txtSatartDate, R.id.txtEndDate, R.id.imgPrevious, R.id.txtItemHeading, R.id.imgNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtSatartDate:
                initDatePicker(txtSatartDate, true);

                break;
            case R.id.txtEndDate:
                initDatePicker(txtEndDate, false);
                break;
            case R.id.txtItemHeading:
                // TODO: 8/31/18 Show Data to Selected Date 
               /* if (!isFilterByBodyPart) {
                    initDatePicker(txtItemHeading, true);
                }*/
                break;
            case R.id.imgPrevious:
                setPreviousItem();
                break;
            case R.id.imgNext:
                setNextItem();
                break;
        }
    }


    @Override
    public void onFilterChange(String startDate, String endDate, ArrayList<String> filters, boolean isFilterByBodyPart) {
        this.isFilterByBodyPart = isFilterByBodyPart;
        currentHeadingIndex = 0;
        if (isFilterByBodyPart) {
            filteredBodyPartsCollection = new ArrayList<>();
            filteredBodyPartsCollection.addAll(filters);
            txtItemHeading.setText(filteredBodyPartsCollection.get(0));
            showSelectedHeaderItemList();
        } else {
            this.startDate = startDate;
            this.endDate = endDate;
            currentPageNumber = 1;
            callAndGetLogData(WebServiceConstants.GET_ALL_EXERCISE_LOGS);
        }

        Log.d("LogFragment ", "onFilterChange: StartDate : " + startDate + " EndDate : " + endDate + " Filters : " + filters.toArray().toString());
    }
}
