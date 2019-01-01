package com.app.gymbuzz.ui.binders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aigestudio.wheelpicker.WheelPicker;
import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.WorkoutModel;
import com.app.gymbuzz.interfaces.RecyclerItemListener;
import com.app.gymbuzz.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.app.gymbuzz.ui.views.AnyTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class WorkoutSetBinder extends RecyclerViewBinder<WorkoutModel.UserExerciseDetailModel> {
    private RecyclerItemListener itemListener;
    private int minReps;
    private int minWeight;
    private int maxReps;
    private int maxWeight;

    public WorkoutSetBinder(RecyclerItemListener itemListener, int minWeight, int maxWeight, int minReps, int maxReps) {
        super(R.layout.row_item_workout_set);
        this.itemListener = itemListener;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.minReps = minReps;
        this.maxReps = maxReps;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void bindView(WorkoutModel.UserExerciseDetailModel entity, int position, Object viewHolder, Context context) {
        ViewHolder holder = (ViewHolder) viewHolder;

        entity.setSetNumber(position + 1);
        holder.rbReps.setMax(maxReps);
        holder.rbReps.setProgress(entity.getReps());
        holder.txtReps.setText(Math.round(holder.rbReps.getProgress()) + " REPS");
        holder.btnDecreaseReps.setOnClickListener((view -> {
            if (Math.round(holder.rbReps.getProgress()) > minReps) {
                holder.rbReps.setProgress(Math.round(holder.rbReps.getProgress()) - 1);
                entity.setReps(Math.round(holder.rbReps.getProgress()) + "");
                holder.txtReps.setText(entity.getReps() + " REPS");
            }
        }));
        holder.btnIncreaseReps.setOnClickListener(view -> {
            if (Math.round(holder.rbReps.getProgress()) < maxReps) {
                holder.rbReps.setProgress(Math.round(holder.rbReps.getProgress()) + 1);
                entity.setReps(Math.round(holder.rbReps.getProgress()) + "");
                holder.txtReps.setText(entity.getReps() + " REPS");
            }
        });
        holder.rbReps.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                if (fromUser) {
                    if (Math.round(progress) < minReps) {
                        holder.rbReps.setProgress(Math.round(minReps));
                    }
                    entity.setReps(Math.round(holder.rbReps.getProgress()) + "");
                    holder.txtReps.setText(entity.getReps() + " REPS");
                }
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });

        holder.rbWeight.setMax(maxWeight);
        holder.rbWeight.setProgress(entity.getWeight());
        holder.txtWeight.setText(Math.round(holder.rbWeight.getProgress()) + " KGS");
        holder.btnDecreaseWeight.setOnClickListener((view -> {
            if (Math.round(holder.rbWeight.getProgress()) > minWeight) {
                holder.rbWeight.setProgress(Math.round(holder.rbWeight.getProgress()) - 1);
                entity.setWeight(Math.round(holder.rbWeight.getProgress()) + "");
                holder.txtWeight.setText(entity.getWeight() + " KGS");
            }
        }));
        holder.btnIncreaseWeight.setOnClickListener(view -> {
            if (Math.round(holder.rbWeight.getProgress()) < maxWeight) {
                holder.rbWeight.setProgress(Math.round(holder.rbWeight.getProgress()) + 1);
                entity.setWeight(Math.round(holder.rbWeight.getProgress()) + "");
                holder.txtWeight.setText(entity.getWeight() + " KGS");
            }
        });
        holder.rbWeight.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                if (fromUser) {
                    if (Math.round(progress) < minWeight) {
                        holder.rbWeight.setProgress(Math.round(minWeight));
                    }
                    entity.setWeight(Math.round(holder.rbWeight.getProgress()) + "");
                    holder.txtWeight.setText(entity.getWeight() + " KGS");
                }
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });

        holder.btnDelete.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
        holder.btnDelete.setOnClickListener(view -> {
            if (itemListener != null) {
                itemListener.onItemClicked(entity, position, holder.btnDelete.getId());
            }
        });

        ArrayList<String> weightCollection = new ArrayList<>(maxWeight);
        for (int i = 1; i <= maxWeight; i++) {
            if (i > 1)
                weightCollection.add(i + " KGS");
            else {
                weightCollection.add(i + " KG");
            }
        }
        holder.wheelWeight.setData(weightCollection);
        holder.wheelWeight.setSelectedItemPosition(entity.getWeight() - 1);
        holder.wheelWeight.setOnItemSelectedListener((picker, data, currentPosition) -> {
            if (currentPosition <= minWeight) {
                currentPosition = minWeight - 1;
                holder.wheelWeight.setSelectedItemPosition(currentPosition);
            }
            entity.setWeight(currentPosition + 1 + "");
        });

        ArrayList<String> repsCollection = new ArrayList<>(maxWeight);
        for (int i = 1; i <= maxReps; i++) {
            if (i > 1)
                repsCollection.add(i + " REPS");
            else {
                repsCollection.add(i + " REP");
            }
        }
        holder.wheelReps.setData(repsCollection);
        holder.wheelReps.setSelectedItemPosition(entity.getReps() - 1);
        holder.wheelReps.setOnItemSelectedListener((picker, data, currentPosition) -> {

            if (currentPosition <= minReps) {
                currentPosition = minReps - 1;
                holder.wheelReps.setSelectedItemPosition(currentPosition);
            }
            entity.setReps(currentPosition + 1 + "");
        });
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.btnDelete)
        ImageView btnDelete;
        @BindView(R.id.btnDecreaseWeight)
        ImageView btnDecreaseWeight;
        @BindView(R.id.txtWeight)
        AnyTextView txtWeight;
        @BindView(R.id.btnIncreaseWeight)
        ImageView btnIncreaseWeight;
        @BindView(R.id.rbWeight)
        CircularSeekBar rbWeight;
        @BindView(R.id.containerWeight)
        RelativeLayout containerWeight;
        @BindView(R.id.btnDecreaseReps)
        ImageView btnDecreaseReps;
        @BindView(R.id.txtReps)
        AnyTextView txtReps;
        @BindView(R.id.btnIncreaseReps)
        ImageView btnIncreaseReps;
        @BindView(R.id.rbReps)
        CircularSeekBar rbReps;
        @BindView(R.id.containerReps)
        RelativeLayout containerReps;
        @BindView(R.id.containerSet)
        RelativeLayout containerSet;
        @BindView(R.id.wheelWeight)
        WheelPicker wheelWeight;
        @BindView(R.id.wheelReps)
        WheelPicker wheelReps;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
