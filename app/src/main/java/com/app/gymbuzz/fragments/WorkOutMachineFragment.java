package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.tankery.lib.circularseekbar.CircularSeekBar;

/**
 * Created on 5/25/2018.
 */


public class WorkOutMachineFragment extends BaseFragment {


    @BindView(R.id.spExcerciseType)
    Spinner spExcerciseType;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.txtHour)
    AnyTextView txtHour;
    @BindView(R.id.txtMinute)
    AnyTextView txtMinute;
    @BindView(R.id.txtSeconds)
    AnyTextView txtSeconds;
    @BindView(R.id.txtMS)
    AnyTextView txtMS;
    @BindView(R.id.btnMinusKg1)
    ImageView btnMinusKg1;
    @BindView(R.id.txtKg1)
    AnyTextView txtKg1;
    @BindView(R.id.btnPlusKg1)
    ImageView btnPlusKg1;

    @BindView(R.id.rlpbKg1)
    RelativeLayout rlpbKg1;
    @BindView(R.id.btnMinusReps1)
    ImageView btnMinusReps1;
    @BindView(R.id.txtReps1)
    AnyTextView txtReps1;
    @BindView(R.id.btnPlusReps1)
    ImageView btnPlusReps1;

    @BindView(R.id.rlPbReps1)
    RelativeLayout rlPbReps1;
    @BindView(R.id.rlBlock1)
    RelativeLayout rlBlock1;
    @BindView(R.id.btnMinusBlock2)
    ImageView btnMinusBlock2;
    @BindView(R.id.btnMinusKg2)
    ImageView btnMinusKg2;
    @BindView(R.id.txtKg2)
    AnyTextView txtKg2;
    @BindView(R.id.btnPlusKg2)
    ImageView btnPlusKg2;

    @BindView(R.id.rlpbKg2)
    RelativeLayout rlpbKg2;
    @BindView(R.id.btnMinusReps2)
    ImageView btnMinusReps2;
    @BindView(R.id.txtReps2)
    AnyTextView txtReps2;
    @BindView(R.id.btnPlusReps2)
    ImageView btnPlusReps2;

    @BindView(R.id.rlPbReps2)
    RelativeLayout rlPbReps2;
    @BindView(R.id.rlBlock2)
    RelativeLayout rlBlock2;
    @BindView(R.id.btnMinusBlock3)
    ImageView btnMinusBlock3;
    @BindView(R.id.btnMinusKg3)
    ImageView btnMinusKg3;
    @BindView(R.id.txtKg3)
    AnyTextView txtKg3;
    @BindView(R.id.btnPlusKg3)
    ImageView btnPlusKg3;

    @BindView(R.id.rlpbKg3)
    RelativeLayout rlpbKg3;
    @BindView(R.id.btnMinusReps3)
    ImageView btnMinusReps3;
    @BindView(R.id.txtReps3)
    AnyTextView txtReps3;
    @BindView(R.id.btnPlusReps3)
    ImageView btnPlusReps3;

    @BindView(R.id.rlPbReps3)
    RelativeLayout rlPbReps3;
    @BindView(R.id.rlBlock3)
    RelativeLayout rlBlock3;
    Unbinder unbinder;
    @BindView(R.id.btnAdd)
    ImageView btnAdd;
    @BindView(R.id.cbSaveReps)
    CheckBox cbSaveReps;
    @BindView(R.id.btnSkip)
    Button btnSkip;
    @BindView(R.id.btnFinish)
    Button btnFinish;
    @BindView(R.id.csbKg1)
    CircularSeekBar csbKg1;
    @BindView(R.id.csbReps1)
    CircularSeekBar csbReps1;
    @BindView(R.id.csbKg2)
    CircularSeekBar csbKg2;
    @BindView(R.id.csbReps2)
    CircularSeekBar csbReps2;
    @BindView(R.id.csbKg3)
    CircularSeekBar csbKg3;
    @BindView(R.id.csbReps3)
    CircularSeekBar csbReps3;

    private Handler mHandler = new Handler();
    private long startTime;
    private long elapsedTime;
    private final int REFRESH_RATE = 1;
    private String hours, minutes, seconds, milliseconds;
    private long secs, mins, hrs, msecs;
    private boolean stopped = false;

    ArrayList exerciseList = new ArrayList();
    ArrayAdapter<String> exerciseAdapter;

    int kg1 = 0;
    int reps1 = 0;

    int kg2 = 0;
    int reps2 = 0;

    int kg3 = 0;
    int reps3 = 0;

    public static WorkOutMachineFragment newInstance() {
        return new WorkOutMachineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workout_machine, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setExerciseData();
        StartTimmer();

        csbKg1.setProgress(35);
        kg1 = 35;
        txtKg1.setText(35+" KG");

        csbKg2.setProgress(35);
        kg2 = 35;
        txtKg2.setText(35+" KG");

        csbKg3.setProgress(35);
        kg3 = 35;
        txtKg3.setText(35+" KG");

        csbReps1.setProgress(35);
        reps1 = 35;
        txtReps1.setText(35+" REPS");

        csbReps2.setProgress(35);
        reps2 = 35;
        txtReps2.setText(35+" REPS");

        csbReps3.setProgress(35);
        reps3 = 35;
        txtReps3.setText(35+" REPS");

        csbKg1.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                if(fromUser){
                    kg1 = Math.round(csbKg1.getProgress());
                    txtKg1.setText(kg1+" KG");
                }
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });

        csbKg2.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                if(fromUser){
                    kg2 = Math.round(csbKg2.getProgress());
                    txtKg2.setText(kg2+" KG");
                }
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });

        csbKg3.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                if(fromUser){
                    kg3 = Math.round(csbKg3.getProgress());
                    txtKg3.setText(kg3+" KG");
                }
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });

        csbReps1.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                if(fromUser){
                    reps1 = Math.round(csbReps1.getProgress());
                    txtReps1.setText(reps1+" REPS");
                }
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });


        csbReps2.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                if(fromUser){
                    reps2 = Math.round(csbReps2.getProgress());
                    txtReps2.setText(reps2+" REPS");
                }
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });

        csbReps3.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                if(fromUser){
                    reps3 = Math.round(csbReps3.getProgress());
                    txtReps3.setText(reps3+" REPS");
                }
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.workout_));
        titleBar.showRightButton(R.drawable.aboutus, true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDockActivity().replaceDockableFragment(MachineMalfunctionFragment.newInstance(), MachineMalfunctionFragment.class.getSimpleName());
            }
        });
    }

    private void setExerciseData() {

        exerciseList = new ArrayList<String>();

        exerciseList.add("Select Exercise");
        exerciseList.add("Exercise 1");
        exerciseList.add("Exercise 2");

        exerciseAdapter = new ArrayAdapter<String>(getDockActivity(), R.layout.spinner_item, exerciseList);
        exerciseAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spExcerciseType.setAdapter(exerciseAdapter);
        spExcerciseType.setSelection(0);
        exerciseAdapter.notifyDataSetChanged();

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
        unbinder.unbind();
    }

    @OnClick({R.id.btnAdd, R.id.btnSkip, R.id.btnFinish, R.id.btnMinusKg1, R.id.btnPlusKg1, R.id.btnMinusReps1, R.id.btnPlusReps1, R.id.btnMinusBlock2, R.id.btnMinusKg2, R.id.btnPlusKg2, R.id.btnMinusReps2, R.id.btnPlusReps2, R.id.btnMinusBlock3, R.id.btnMinusKg3, R.id.btnPlusKg3, R.id.btnMinusReps3, R.id.btnPlusReps3})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.btnAdd:

                if (rlBlock2.getVisibility() == View.GONE) {
                    btnMinusBlock2.setVisibility(View.VISIBLE);
                    rlBlock2.setVisibility(View.VISIBLE);
                } else if (rlBlock3.getVisibility() == View.GONE) {
                    btnAdd.setVisibility(View.GONE);
                    btnMinusBlock3.setVisibility(View.VISIBLE);
                    rlBlock3.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.btnSkip:

                getDockActivity().replaceDockableFragment(ScanQRFragment.newInstance(), ScanQRFragment.class.getSimpleName());

                break;

            case R.id.btnFinish:

                getDockActivity().replaceDockableFragment(WorkoutSummaryFragment.newInstance(), WorkoutSummaryFragment.class.getSimpleName());

                break;

            case R.id.btnMinusKg1:

                if (kg1 > 0) {
                    kg1--;
                    txtKg1.setText(kg1 + " KG");
                    csbKg1.setProgress(kg1);
                }

                break;

            case R.id.btnPlusKg1:

                if (kg1 < csbKg1.getMax()) {
                    kg1++;
                    txtKg1.setText(kg1 + " KG");
                    csbKg1.setProgress(kg1);
                }

                break;

            case R.id.btnMinusReps1:

                if (reps1 > 0) {
                    reps1--;
                    txtReps1.setText(reps1 + " REPS");
                    csbReps1.setProgress(reps1);
                }

                break;
            case R.id.btnPlusReps1:

                if (reps1 < csbReps1.getMax()) {
                    reps1++;
                    txtReps1.setText(reps1 + " REPS");
                    csbReps1.setProgress(reps1);
                }

                break;

            case R.id.btnMinusBlock2:

                if (rlBlock2.getVisibility() == View.VISIBLE) {
                    btnAdd.setVisibility(View.VISIBLE);
                    btnMinusBlock2.setVisibility(View.GONE);
                    rlBlock2.setVisibility(View.GONE);
                }

                break;

            case R.id.btnMinusKg2:

                if (kg2 > 0) {
                    kg2--;
                    txtKg2.setText(kg2 + " KG");
                    csbKg2.setProgress(kg2);
                }

                break;

            case R.id.btnPlusKg2:

                if (kg2 < csbKg2.getMax()) {
                    kg2++;
                    txtKg2.setText(kg2 + " KG");
                    csbKg2.setProgress(kg2);
                }

                break;

            case R.id.btnMinusReps2:

                if (reps2 > 0) {
                    reps2--;
                    txtReps2.setText(reps2 + " REPS");
                    csbReps2.setProgress(reps2);
                }

                break;

            case R.id.btnPlusReps2:

                if (reps2 < csbReps2.getMax()) {
                    reps2++;
                    txtReps2.setText(reps2 + " REPS");
                    csbReps2.setProgress(reps2);
                }


                break;

            case R.id.btnMinusBlock3:

                if (rlBlock3.getVisibility() == View.VISIBLE) {
                    btnAdd.setVisibility(View.VISIBLE);
                    btnMinusBlock3.setVisibility(View.GONE);
                    rlBlock3.setVisibility(View.GONE);
                }

                break;

            case R.id.btnMinusKg3:

                if (kg3 > 0) {
                    kg3--;
                    txtKg2.setText(kg3 + " KG");
                    csbKg3.setProgress(kg3);
                }


                break;

            case R.id.btnPlusKg3:

                if (kg3 < csbKg3.getMax()) {
                    kg3++;
                    txtKg3.setText(kg3 + " KG");
                    csbKg3.setProgress(kg3);
                }

                break;

            case R.id.btnMinusReps3:

                if (reps3 > 0) {
                    reps3--;
                    txtReps3.setText(reps3 + " REPS");
                    csbReps3.setProgress(reps3);
                }

                break;

            case R.id.btnPlusReps3:

                if (reps3 < csbReps3.getMax()) {
                    reps3++;
                    txtReps3.setText(reps3 + " REPS");
                    csbReps3.setProgress(reps3);
                }

                break;

        }
    }


    private void updateTimer(float time) {
        secs = (long) (time / 1000);
        mins = (long) ((time / 1000) / 60);
        hrs = (long) (((time / 1000) / 60) / 60);

		/* Convert the seconds to String
         * and format to ensure it has
		 * a leading zero when required
		 */
        secs = secs % 60;
        seconds = String.valueOf(secs);
        if (secs == 0) {
            seconds = "00";
        }
        if (secs < 10 && secs > 0) {
            seconds = "0" + seconds;
        }

		/* Convert the minutes to String and format the String */

        mins = mins % 60;
        minutes = String.valueOf(mins);
        if (mins == 0) {
            minutes = "00";
        }
        if (mins < 10 && mins > 0) {
            minutes = "0" + minutes;
        }

    	/* Convert the hours to String and format the String */

        hours = String.valueOf(hrs);
        if (hrs == 0) {
            hours = "00";
        }
        if (hrs < 10 && hrs > 0) {
            hours = "0" + hours;
        }

    	/* Although we are not using milliseconds on the timer in this example
         * I included the code in the event that you wanted to include it on your own
    	 */
        milliseconds = String.valueOf((long) time);
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

    private Runnable startTimer = new Runnable() {
        public void run() {
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);
            mHandler.postDelayed(this, REFRESH_RATE);
        }
    };

}
