package com.app.gymbuzz.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.app.gymbuzz.R;
import com.app.gymbuzz.ui.viewbinders.abstracts.RecyclerViewBinder;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2/2/2018.
 */

public class FilterBinder extends RecyclerViewBinder<String> {
    private ArrayList<String> filterCheckIDs;
    private boolean isAllClear = true;

    public FilterBinder() {
        super(R.layout.row_item_filter_exercise);
        filterCheckIDs = new ArrayList<>();
    }

    @Override
    public RecyclerViewBinder.BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(final String entity, int position, Object viewHolder, Context context) {
        ViewHolder holder = (ViewHolder) viewHolder;
        if (filterCheckIDs.contains(entity)) {
            holder.chkGenre.setChecked(true);
        } else {
            holder.chkGenre.setChecked(false);
        }
        holder.chkGenre.setText(entity + "");
        holder.chkGenre.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!filterCheckIDs.contains(entity)) {
                    isAllClear = false;
                    filterCheckIDs.add(entity);
                }
            } else {
                if (filterCheckIDs.contains(entity)) {
                    filterCheckIDs.remove(entity);
                    if (filterCheckIDs.isEmpty()) {
                        isAllClear = true;
                    }
                }
            }
        });
    }

    public boolean isAllClear() {
        return isAllClear;
    }

    public void clearFilterIDs() {
        if (filterCheckIDs != null) {
            filterCheckIDs.clear();
            isAllClear = true;
        }
    }

    public String getFilterCheckIDs() {

        return StringUtils.join(filterCheckIDs, ',');
    }


    static class ViewHolder extends RecyclerViewBinder.BaseViewHolder {
        @BindView(R.id.chk_genre)
        CheckBox chkGenre;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

