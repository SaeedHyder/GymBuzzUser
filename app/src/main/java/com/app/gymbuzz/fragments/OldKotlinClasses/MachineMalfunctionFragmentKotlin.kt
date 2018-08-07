package com.app.gymbuzz.fragments.OldKotlinClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gymbuzz.R
import com.app.gymbuzz.fragments.abstracts.BaseFragment
import com.app.gymbuzz.helpers.UIHelper
import kotlinx.android.synthetic.main.fragment_machine_malfunction.*

/**
 * Created on 5/22/2018.
 */

class MachineMalfunctionFragmentKotlin: BaseFragment() {

    companion object {
        val Tag:String = "MachineMalfunctionFragmentKotlin"
        fun newInstance(): MachineMalfunctionFragmentKotlin {
            val fragment= MachineMalfunctionFragmentKotlin()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_machine_malfunction,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSubmit.setOnClickListener {

            if(isValidated()){
                edtEmail.text.clear()
                edtFullName.text.clear()
                UIHelper.showShortToastInCenter(mainActivity,getString(R.string.will_be_imp_beta))
            }

        }

    }

    fun isValidated():Boolean{

        if(edtEmail.testValidity()!!){
            if(edtFullName.testValidity()!!){
                return true
            }
        }

        return false
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}