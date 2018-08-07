package com.app.gymbuzz.ui.binders

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import com.app.gymbuzz.R
import com.app.gymbuzz.interfaces.ItemClickListener
import com.app.gymbuzz.ui.viewbinders.abstracts.RecyclerViewBinder
import com.nostra13.universalimageloader.core.ImageLoader

/**
 * Created on 5/23/2018.
 */


class BinderGymDetail constructor(itemClickListener: ItemClickListener) : RecyclerViewBinder<String>() {

     var imageLoader: ImageLoader
     var itemClickListener: ItemClickListener? = null

    init {
        this.itemClickListener = itemClickListener
        imageLoader = ImageLoader.getInstance()
    }


    override fun createViewHolder(view: View): RecyclerViewBinder.BaseViewHolder {
        return ViewHolder(view)
    }

    override fun bindView(path: String, position: Int, viewHolder: Any, context: Context) {
        val holder = viewHolder as ViewHolder

        imageLoader.displayImage(path, holder.ivGym)

        holder.ivGym.setOnClickListener {
            itemClickListener?.itemClicked(path,position)

        }

    }

    override fun bindItemId(position: Int) {

    }

    class ViewHolder(view: View) : BaseViewHolder(view) {

         val ivGym: ImageView

        init {
            ivGym = view.findViewById(R.id.ivGym) as ImageView
        }
    }

}