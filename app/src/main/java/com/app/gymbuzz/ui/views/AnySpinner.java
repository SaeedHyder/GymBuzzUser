package com.app.gymbuzz.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created on 6/14/2017.
 */

public class AnySpinner extends android.support.v7.widget.AppCompatSpinner {
    OnItemSelectedListener listener;
    private SpinnerItemSelectListener customListener;

    public AnySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnySpinner(Context context) {
        super(context);

    }


    public AnySpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public void setCustomItemSelectListener(SpinnerItemSelectListener customListener) {
        this.customListener = customListener;
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
        if (listener != null)
            listener.onItemSelected(this, getSelectedView(), position, getSelectedItemId());
        if (customListener != null)
            customListener.onSpinnerItemSelected(position);
    }


    public void setOnItemSelectedEvenIfUnchangedListener(
            OnItemSelectedListener listener) {
        this.listener = listener;
    }

    public interface SpinnerItemSelectListener {
        void onSpinnerItemSelected(int position);
    }
}

