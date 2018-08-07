package com.app.gymbuzz.fragments.OldKotlinClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gymbuzz.R
import com.app.gymbuzz.fragments.ScanQRFragment
import com.app.gymbuzz.fragments.abstracts.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_menu.*

/**
 * Created on 5/21/2018.
 */

class HomeMenuFragmentKotlin : BaseFragment() {

    /*private val btnMembership: AnyTextView? by bindView(R.id.btnMembership)
    private val btnWrkout: AnyTextView? by bindView(R.id.btnWrkout)
    private val btnLog: AnyTextView? by bindView(R.id.btnLog)
    private val btnGuide: AnyTextView? by bindView(R.id.btnGuide)*/


    companion object {
        val Tag: String = "HomeMenuFragmentKotlin"
        fun newInstance(): HomeMenuFragmentKotlin {
            val fragment = HomeMenuFragmentKotlin()
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnMembership?.setOnClickListener() { v ->
            dockActivity.replaceDockableFragment(ScanQRFragment.newInstance(), "ScanQRFragment")
        }

        btnWrkout?.setOnClickListener() { v ->
            dockActivity.replaceDockableFragment(UpdateProfileFragmentKotlin.newInstance(), "UpdateProfileFragmentKotlin")
        }

        btnLog?.setOnClickListener() { v ->
            dockActivity.replaceDockableFragment(UpdateProfileFragmentKotlin.newInstance(), "UpdateProfileFragmentKotlin")
        }

        btnGuide?.setOnClickListener() { v ->
            dockActivity.replaceDockableFragment(UpdateProfileFragmentKotlin.newInstance(), "UpdateProfileFragmentKotlin")
        }


    }


}