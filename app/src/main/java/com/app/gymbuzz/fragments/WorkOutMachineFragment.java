package com.app.gymbuzz.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.WorkoutModel;
import com.app.gymbuzz.entities.WorkoutServerModel;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.interfaces.RecyclerItemListener;
import com.app.gymbuzz.ui.binders.WorkoutSetBinder;
import com.app.gymbuzz.ui.views.AnySpinner;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.CustomRecyclerView;
import com.app.gymbuzz.ui.views.TitleBar;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 5/25/2018.
 */

@SuppressLint({"SetTextI18n", "UseSparseArrays", "unchecked"})
public class WorkOutMachineFragment extends BaseFragment {


    private final int REFRESH_RATE = 1;
    @BindView(R.id.spExcerciseType)
    AnySpinner spExcerciseType;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.imgMachine)
    RoundedImageView imgMachine;
    @BindView(R.id.txtMachineName)
    AnyTextView txtMachineName;
    @BindView(R.id.txtHour)
    AnyTextView txtHour;
    @BindView(R.id.txtMinute)
    AnyTextView txtMinute;
    @BindView(R.id.txtSeconds)
    AnyTextView txtSeconds;
    @BindView(R.id.txtMS)
    AnyTextView txtMS;
    @BindView(R.id.rvSets)
    CustomRecyclerView rvSets;
    @BindView(R.id.btnAdd)
    ImageView btnAdd;
    @BindView(R.id.cbSaveReps)
    CheckBox cbSaveReps;
    @BindView(R.id.btnSkip)
    Button btnSkip;
    @BindView(R.id.btnFinish)
    Button btnFinish;

    ArrayAdapter<String> exerciseAdapter;
    private ArrayList<WorkoutModel.UserExerciseDetailModel> setCollections;
    private WorkoutModel machineDetail;
    private HashMap<Integer, ArrayList<WorkoutModel.UserExerciseDetailModel>> machineSavedExercises;

    private Handler mHandler = new Handler();
    private long startTime;
    private long elapsedTime;
    private boolean stopped = false;

    private RecyclerItemListener itemClickListener = ((ent, position, id) -> {
        switch (id) {
            case R.id.btnDelete:
                setCollections.remove(position);
                rvSets.notifyItemRemoved(position);
                break;
        }
    });
    private Runnable startTimer = new Runnable() {
        public void run() {
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);
            mHandler.postDelayed(this, REFRESH_RATE);
        }
    };

    public static WorkOutMachineFragment newInstance(WorkoutModel machineDetail) {
        WorkOutMachineFragment fragment = new WorkOutMachineFragment();
        fragment.setMachineDetail(machineDetail);
        return fragment;
    }

    public void setMachineDetail(WorkoutModel machineDetail) {
        this.machineDetail = machineDetail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_machine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtMachineName.setText(machineDetail.getMachineName() + "");
        ImageLoader.getInstance().displayImage(machineDetail.getImagePath(), imgMachine);
        setExerciseData();
        StartTimmer();

        if (machineSavedExercises == null || setCollections == null) {
            machineSavedExercises = new HashMap<>();
            setCollections = new ArrayList<>();
            btnAdd.setVisibility(View.GONE);
        }

        rvSets.bindRecyclerView(new WorkoutSetBinder(itemClickListener, machineDetail.getMinweight(), machineDetail.getMaxweight(), machineDetail.getMinrep(), machineDetail.getMaxrep()),
                setCollections, new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false), new DefaultItemAnimator());
        new LinearSnapHelper().attachToRecyclerView(rvSets);
        rvSets.setNestedScrollingEnabled(false);
        rvSets.getItemAnimator().setChangeDuration(0);

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.workout_));
        titleBar.showNotificationButton(0);
        titleBar.showCallForHelpButton(view -> {
            if (spExcerciseType.getSelectedItemPosition() == 0) {
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.supportWrongSelectionError));
                return;
            }
            WorkoutModel.MachineExerciseDetailModel machineExerciseDetail = machineDetail.getExercises().get(spExcerciseType.getSelectedItemPosition() - 1);
            serviceHelper.enqueueCall(webService.requestForSupport(machineExerciseDetail.getMachineExerciseId(), machineDetail.getFloorID(), String.valueOf(machineDetail.getGymMachineId()), prefHelper.getUserToken()),
                    WebServiceConstants.REQUEST_FOR_SUPPORT);
        });
    }

    private void setExerciseData() {
        ArrayList<String> exerciseCollection = new ArrayList<>();

        exerciseCollection.add(getString(R.string.muscleSelection));
        for (WorkoutModel.MachineExerciseDetailModel machineDetail : machineDetail.getExercises()
                ) {
            exerciseCollection.add(machineDetail.getExerciseName());
        }

        exerciseAdapter = new ArrayAdapter<String>(getDockActivity()
                , android.R.layout.simple_spinner_item, exerciseCollection) {
            @Override
            public boolean isEnabled(int position) {
                return !(position == 0);
            }


            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                tv.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }

        };

        spExcerciseType.setAdapter(exerciseAdapter);
        spExcerciseType.setSelection(0);
        exerciseAdapter.notifyDataSetChanged();
        spExcerciseType.setCustomItemSelectListener(position -> {
            position = position - 1;
            Integer machineExerciseID = machineDetail.getExercises().get(position).getMachineExerciseId();
            btnAdd.setVisibility(View.VISIBLE);
            findAndLoadSelectedSets(machineExerciseID);
        });
    }

    private void findAndLoadSelectedSets(Integer machineExerciseID) {
        setCollections = new ArrayList<>();

        if (machineSavedExercises.containsKey(machineExerciseID)) {
            setCollections.addAll(machineSavedExercises.get(machineExerciseID));
        } else {
            if (machineDetail.getUserExercises() != null) {
                for (WorkoutModel.UserExerciseModel exerciseModel : machineDetail.getUserExercises()
                        ) {
                    if (exerciseModel.getMachineExerciseID().equals(machineExerciseID)) {
                        setCollections.addAll(exerciseModel.getExerciseDetails());
                    }
                }
            }
        }

        if (setCollections.size() == 0) {
            setCollections.add(new WorkoutModel.UserExerciseDetailModel(machineDetail.getMinrep() + "", machineDetail.getMinweight() + "", 1));
        }

        machineSavedExercises.put(machineExerciseID, setCollections);
        rvSets.getAdapter().changeList(setCollections);
        rvSets.notifyDataSetChanged();
    }

    public void StartTimmer() {

        if (stopped) {
            startTime = System.currentTimeMillis() - elapsedTime;
        } else {
            startTime = System.currentTimeMillis();
        }
        mHandler.removeCallbacks(startTimer);
        mHandler.postDelayed(startTimer, 0);

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mHandler != null)
            mHandler.removeCallbacks(startTimer);

        stopped = true;

    }

    @OnClick({R.id.btnAdd, R.id.btnSkip, R.id.btnFinish})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.btnAdd:
                if (setCollections.size() < AppConstants.MAX_WORKOUT_SETS_LIMIT) {
                    setCollections.add(new WorkoutModel.UserExerciseDetailModel(machineDetail.getMinrep() + "", machineDetail.getMinweight() + "", setCollections.size()));
                    rvSets.notifyItemRangeChanged(((LinearLayoutManager) rvSets.getLayoutManager()).findLastVisibleItemPosition(), 1);
                } else {
                    UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.maxSetsError));
                }
                break;

            case R.id.btnSkip:
                serviceHelper.enqueueCall(webService.sendExerciseDetails(getWorkOutDataForServer(), prefHelper.getUserToken()), WebServiceConstants.SEND_EXERCISE_DETAIL);
                break;

            case R.id.btnFinish:
                if (spExcerciseType.getSelectedItemPosition() == 0) {
                    UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.finishWrongSelectionError));
                    return;
                }
                serviceHelper.enqueueCall(webService.sendExerciseDetails(getWorkOutDataForServer(), prefHelper.getUserToken()), WebServiceConstants.SEND_FINAL_EXERCISE_DETAIL);

                break;

        }
    }

    @NonNull
    private WorkoutServerModel.WorkoutServerWrapper getWorkOutDataForServer() {
        ArrayList<WorkoutServerModel> dataForServer = new ArrayList<>();

        for (Map.Entry<Integer, ArrayList<WorkoutModel.UserExerciseDetailModel>> entry : machineSavedExercises.entrySet()) {
            dataForServer.add(new WorkoutServerModel.Builder()
                    .gymMachineID(machineDetail.getGymMachineId())
                    .isSaveForFuture(cbSaveReps.isChecked())
                    .machineExerciseID(entry.getKey())
                    .userExerciseDetails(entry.getValue())
                    .timeSpent(String.format("%s:%s:%s:%s", txtHour.getText().toString(), txtMinute.getText().toString(), txtSeconds.getText().toString(), txtMS.getText().toString()))
                    .build());
        }
        return new WorkoutServerModel.WorkoutServerWrapper(dataForServer);
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag) {
            case WebServiceConstants.SEND_EXERCISE_DETAIL:
                AndPermission.with(getMainActivity())
                        .runtime()
                        .permission(Permission.CAMERA)
                        .onGranted(permissions -> {
                            getDockActivity().popBackStackTillEntry(1);
                            getDockActivity().replaceDockableFragment(ScanQRFragment.newInstance(), ScanQRFragment.class.getSimpleName());
                        })
                        .onDenied(permissions -> {
                            UIHelper.showShortToastInCenter(getDockActivity(), "Permission is required to access this feature");
                        })
                        .start();
                break;
            case WebServiceConstants.SEND_FINAL_EXERCISE_DETAIL:
                getDockActivity().popBackStackTillEntry(1);
                getDockActivity().replaceDockableFragment(WorkoutSummaryFragment.newInstance(), WorkoutSummaryFragment.class.getSimpleName());
                break;
            case WebServiceConstants.REQUEST_FOR_SUPPORT:
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.supportMessage));
                break;
        }
    }

    private void updateTimer(float time) {
        long secs = (long) (time / 1000);
        long mins = (long) ((time / 1000) / 60);
        long hrs = (long) (((time / 1000) / 60) / 60);

        /* Convert the seconds to String
         * and format to ensure it has
         * a leading zero when required
         */
        secs = secs % 60;
        String seconds = String.valueOf(secs);
        if (secs == 0) {
            seconds = "00";
        }
        if (secs < 10 && secs > 0) {
            seconds = "0" + seconds;
        }

        /* Convert the minutes to String and format the String */

        mins = mins % 60;
        String minutes = String.valueOf(mins);
        if (mins == 0) {
            minutes = "00";
        }
        if (mins < 10 && mins > 0) {
            minutes = "0" + minutes;
        }

        /* Convert the hours to String and format the String */

        String hours = String.valueOf(hrs);
        if (hrs == 0) {
            hours = "00";
        }
        if (hrs < 10 && hrs > 0) {
            hours = "0" + hours;
        }

        /* Although we are not using milliseconds on the timer in this example
         * I included the code in the event that you wanted to include it on your own
         */
        String milliseconds = String.valueOf((long) time);
        //milliseconds = System.currentTimeMillis() % 1000+"";
        if (milliseconds.length() == 2) {
            milliseconds = "0" + milliseconds;
        }
        if (milliseconds.length() <= 1) {
            milliseconds = "00";
        }

        milliseconds = milliseconds.substring(milliseconds.length() - 3, milliseconds.length() - 2) + "00";


        /* Setting the timer text to the elapsed time */
        txtHour.setText(hours);
        txtMinute.setText(minutes);
        txtSeconds.setText(seconds);
        txtMS.setText(milliseconds);

    }//end Update Timer

}
