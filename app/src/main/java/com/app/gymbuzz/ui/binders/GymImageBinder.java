package com.app.gymbuzz.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.GymDetailModel;
import com.app.gymbuzz.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GymImageBinder extends RecyclerViewBinder<GymDetailModel.GymImages> {
    private ImageLoader imageLoader;

    public GymImageBinder() {
        super(R.layout.gym_detail_item);
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(GymDetailModel.GymImages entity, int position, Object viewHolder, Context context) {
        ViewHolder holder = (ViewHolder) viewHolder;
        imageLoader.displayImage(entity.getImagepath(), holder.ivGym);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.ivGym)
        ImageView ivGym;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
