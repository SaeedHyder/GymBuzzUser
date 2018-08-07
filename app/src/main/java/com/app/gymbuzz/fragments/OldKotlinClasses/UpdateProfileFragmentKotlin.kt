package com.app.gymbuzz.fragments.OldKotlinClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.app.gymbuzz.R
import com.app.gymbuzz.fragments.abstracts.BaseFragment
import com.app.gymbuzz.ui.views.TitleBar
import kotlinx.android.synthetic.main.fragment_update_profile.*
import java.util.ArrayList

/**
 * Created on 5/19/2018.
 */

class UpdateProfileFragmentKotlin: BaseFragment() {

    var genderList = ArrayList<String>()
    lateinit var genderAdapter: ArrayAdapter<String>

    companion object {
        val Tag: String = "UpdateProfileFragmentKotlin"
        fun newInstance(): UpdateProfileFragmentKotlin {
            val fragment = UpdateProfileFragmentKotlin()
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_update_profile,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setGenderData()
        setData()

        btnAdd.setOnClickListener(View.OnClickListener {


        })

        btnUpdate.setOnClickListener(View.OnClickListener {

            if(isValidated()){

            }

        })

    }

    private fun setData(){

        edtFullName.setText("Jhon Doe")
        edtEmail.setText("jhon@gmail.com")
        edtAge.setText("26")
        spGender.setSelection(0)
        edtHeight.setText("69")
        edtWeight.setText("81")

    }

    private fun isValidated():Boolean{


        if(edtFullName.text.toString().length > 0){
            if(edtHeight.text.toString().length > 0){
                if(edtWeight.text.toString().length > 0){
                    return true
                }else{
                    edtWeight.error = getString(R.string.please_enter_weight);
                }
            }
            else{
                edtHeight.error = getString(R.string.please_enter_height);
            }
        }else{
            edtFullName.error = getString(R.string.please_enter_fullname);
        }

        return false
    }

    override fun setTitleBar(titleBar: TitleBar?) {
        super.setTitleBar(titleBar)

        titleBar?.hideButtons()
        titleBar?.setSubHeading(getString(R.string.update_profile))
        titleBar?.showBackButton()

    }


    private fun setGenderData() {

        genderList = ArrayList<String>()

        genderList.run {
            add("Male")
            add("Female")
        }

        genderAdapter = ArrayAdapter<String>(dockActivity, R.layout.spinner_item, genderList)
        genderAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spGender.setAdapter(genderAdapter)
        spGender.setSelection(0)
        genderAdapter.notifyDataSetChanged()

    }

}