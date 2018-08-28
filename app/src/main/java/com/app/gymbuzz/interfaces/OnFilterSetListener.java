package com.app.gymbuzz.interfaces;

import java.util.ArrayList;

public interface OnFilterSetListener {
    void onFilterChange(String startDate, String endDate, ArrayList<String> filters,boolean isFilterByBodyPart);
     interface onFilterCheckListener {
        void onCheckChangeListener(boolean isChecked);
    }
}

