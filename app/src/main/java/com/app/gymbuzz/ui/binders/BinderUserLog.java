package com.app.gymbuzz.ui.binders;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.app.gymbuzz.R;
import com.app.gymbuzz.activities.DockActivity;
import com.app.gymbuzz.entities.ExserciseLogEnt;
import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.ui.viewbinders.abstracts.ExpandableListViewBinder;
import com.app.gymbuzz.ui.views.AnyTextView;

/**
 * Created on 5/28/2018.
 */

public class BinderUserLog  extends ExpandableListViewBinder<String,ExserciseLogEnt> {

    public DockActivity context;
    private Integer IN_STOCK;
    private Integer NEW_RELEASE;
    public BinderUserLog(DockActivity context) {

        super(R.layout.fragment_user_log_parent_item, R.layout.fragment_user_log_child_item);
        this.context = context;
    }

    @Override
    public BaseGroupViewHolder createGroupViewHolder(View view) {
        return new BinderUserLog.ViewHolder2(view);
    }

    @Override
    public BaseChildViewHolder createChildViewHolder(View view) {
        return new BinderUserLog.ViewHolder(view);
    }

    @Override
    public void bindGroupView(String entity, int position, int grpPosition, View view, Activity activity, boolean isExpanded) {

        BinderUserLog.ViewHolder2 viewHolder2 = (BinderUserLog.ViewHolder2) view.getTag();

        viewHolder2.tv_header.setText(entity);

        if(AppConstants.isShow){
            viewHolder2.ivBody.setVisibility(View.VISIBLE);
        }else{
            viewHolder2.ivBody.setVisibility(View.GONE);
        }

        if(isExpanded){
            viewHolder2.ivIndicator.setImageResource(R.drawable.downarrow);
        }else{
            viewHolder2.ivIndicator.setImageResource(R.drawable.rightarrow);
        }

    }

    public static class ViewHolder2 extends BaseGroupViewHolder {

        AnyTextView tv_header;
        ImageView ivBody;
        ImageView ivIndicator;

        public ViewHolder2(View view) {
            tv_header = (AnyTextView) view.findViewById(R.id.txtHeadder);
            ivBody = (ImageView) view.findViewById(R.id.ivBody);
            ivIndicator = (ImageView) view.findViewById(R.id.ivIndicator);
        }
    }

    @Override
    public void bindChildView(final ExserciseLogEnt entity, int position, int grpPosition, View view, Activity activity) {

        final BinderUserLog.ViewHolder viewHolder = (BinderUserLog.ViewHolder) view.getTag();

        viewHolder.txtName.setText(entity.getExcerciseSubType());
        viewHolder.txtReps.setText(entity.getReps());
        viewHolder.txtKgs.setText(entity.getKgs());
        viewHolder.txtSets.setText(entity.getSets());
    }

    public static class ViewHolder extends BaseChildViewHolder{

        AnyTextView txtName;
        AnyTextView txtReps;
        AnyTextView txtKgs;
        AnyTextView txtSets;


        public ViewHolder(View view) {
            txtName = (AnyTextView) view.findViewById(R.id.txtName);
            txtReps = (AnyTextView) view.findViewById(R.id.txtReps);
            txtKgs = (AnyTextView) view.findViewById(R.id.txtKgs);
            txtSets = (AnyTextView) view.findViewById(R.id.txtSets);
        }
    }

}
