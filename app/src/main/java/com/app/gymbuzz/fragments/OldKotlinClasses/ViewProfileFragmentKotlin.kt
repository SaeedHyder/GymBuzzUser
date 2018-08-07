package com.app.gymbuzz.fragments.OldKotlinClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gymbuzz.R
import com.app.gymbuzz.fragments.abstracts.BaseFragment
import com.app.gymbuzz.ui.views.TitleBar

/**
 * Created on 5/22/2018.
 */


class ViewProfileFragmentKotlin : BaseFragment() {

    companion object {
        val Tag: String = "ViewProfileFragmentKotlin"
        fun newInstance(): ViewProfileFragmentKotlin {
            val fragment = ViewProfileFragmentKotlin()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_profile,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setTitleBar(titleBar: TitleBar?) {
        super.setTitleBar(titleBar)

        titleBar?.hideButtons()
        titleBar?.showBackButton()
        titleBar?.setSubHeading(getString(R.string.view_profile))
        titleBar?.showRightButton(R.drawable.edit,true, View.OnClickListener {

            dockActivity.replaceDockableFragment(UpdateProfileFragmentKotlin.newInstance(), "UpdateProfileFragmentKotlin")

        })

    }



}