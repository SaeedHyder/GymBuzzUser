package com.app.gymbuzz.fragments.OldKotlinClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gymbuzz.R
import com.app.gymbuzz.fragments.abstracts.BaseFragment
import com.app.gymbuzz.helpers.UIHelper
import com.app.gymbuzz.ui.views.TitleBar
import kotlinx.android.synthetic.main.fragment_change_password.*


class ChangePasswordFragmentKotlin : BaseFragment() {

    companion object {
        val Tag: String = "ChangePasswordFragmentKotlin"
        fun newInstance(): ChangePasswordFragmentKotlin {
            val fragment = ChangePasswordFragmentKotlin()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_change_password,container,false)

    }

    fun validate():Boolean{

        if(edtOldPassword.text.toString().length >= 6){
            if(edtNewPassword.text.toString().length >= 6){
                if(edtConfirmPassword.text.toString().length >= 6){
                    if(edtConfirmPassword.text.toString().equals(edtConfirmPassword.text.toString(),true)){
                        return true
                    }else{
                        UIHelper.showShortToastInCenter(mainActivity,getString(R.string.password_not_equal))
                    }
                }
                else{
                    edtConfirmPassword.error = getString(R.string.password_length)
                }
            }else{
                edtConfirmPassword.error = getString(R.string.password_length)
            }
        }else{
            edtConfirmPassword.error = getString(R.string.password_length)
        }

        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSave.setOnClickListener() {v ->

            if(validate()) {
                mainActivity.popFragment()
                UIHelper.showShortToastInCenter(mainActivity,"Will be implemented in beta")
            }

        }

    }

    override fun setTitleBar(titleBar: TitleBar?) {
        super.setTitleBar(titleBar)

        titleBar?.hideButtons()
        titleBar?.showBackButton()
        titleBar?.setSubHeading(getString(R.string.change_password))

    }

}