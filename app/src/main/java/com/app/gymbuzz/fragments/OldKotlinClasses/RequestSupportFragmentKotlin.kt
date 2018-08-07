package com.app.gymbuzz.fragments.OldKotlinClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.app.gymbuzz.R
import com.app.gymbuzz.fragments.abstracts.BaseFragment
import com.app.gymbuzz.helpers.UIHelper
import com.app.gymbuzz.ui.views.TitleBar
import kotlinx.android.synthetic.main.fragment_request_support.*
import java.util.ArrayList

/**
 * Created on 5/22/2018.
 */

class RequestSupportFragmentKotlin: BaseFragment() {

    var priorityList = ArrayList<String>()
    lateinit var priorityAdapter: ArrayAdapter<String>
    var priority:String = ""

    companion object {
        val Tag:String = "RequestSupportFragmentKotlin"
        fun newInstance(): RequestSupportFragmentKotlin {
            val fragment = RequestSupportFragmentKotlin()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_request_support,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPriorityData()

        btnSubmit.setOnClickListener {

            if(isValidated()){
                edtFullName.text.clear()
                edtExtercise.text.clear()
                edtLocation.text.clear()
                priority = ""
                spPriority.setSelection(0)
                edtFeedBack.text.clear()
                UIHelper.showShortToastInCenter(mainActivity,getString(R.string.will_be_imp_beta))
            }

        }


        spPriority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {
                    1 -> priority = "Low";
                    2 -> priority = "Medium";
                    3 -> priority = "Heigh";
                    else -> {
                        priority = "";
                    }
                }

            }

        }


    }

    fun isValidated():Boolean{

        if(edtFullName.testValidity()!!){
            if(edtExtercise.testValidity()!!){
                if(edtLocation.testValidity()!!) {
                    if(priority.length > 0) {
                        if(edtFeedBack.testValidity()!!) {
                            return true
                        }
                    }
                }
            }
        }

        return false
    }

    private fun setPriorityData() {

        priorityList = ArrayList<String>()

        priorityList.run {
            add("Priority")
            add("Low")
            add("Medium")
            add("Heigh")
        }

        priorityAdapter = ArrayAdapter<String>(dockActivity, R.layout.spinner_item, priorityList)
        priorityAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spPriority.setAdapter(priorityAdapter)
        spPriority.setSelection(0)
        priorityAdapter.notifyDataSetChanged()

    }

    override fun setTitleBar(titleBar: TitleBar?) {
        super.setTitleBar(titleBar)

        titleBar?.hideButtons()
        titleBar?.showBackButton()
        titleBar?.setSubHeading(getString(R.string.req_support))

    }

}