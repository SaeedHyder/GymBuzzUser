package com.app.gymbuzz.fragments.OldKotlinClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gymbuzz.R
import com.app.gymbuzz.fragments.HomeFragment
import com.app.gymbuzz.fragments.abstracts.BaseFragment
import com.app.gymbuzz.ui.views.TitleBar
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragmentKotlin : BaseFragment(){

    /*private val edtEmail: AnyEditTextView by bindView(R.id.edtEmail)
    private val edtPassword: AnyEditTextView by bindView(R.id.edtPassword)
    private val btnLogin: Button? by bindView(R.id.btnLogin)
    private val btnForgotPassword: AnyTextView? by bindView(R.id.btn_forgot_password)*/



    companion object {
        val Tag: String = "LoginFragmentKotlin"
        fun newInstance(): LoginFragmentKotlin {
            val fragment = LoginFragmentKotlin()
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_login,container,false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin?.setOnClickListener() { v ->
            if(isValidated()){
                dockActivity.replaceDockableFragment(HomeFragment.newInstance(), "HomeFragment")
            }
        }

        btn_forgot_password?.setOnClickListener(View.OnClickListener {

        })



    }


    fun isValidated():Boolean{

        if(edtEmail?.testValidity()!!){
            if(edtPassword?.text.toString().length >= 6){
                return true
            }
            else{
                edtPassword?.error = getString(R.string.password_length);
            }
        }

        return false
    }

    override fun setTitleBar(titleBar: TitleBar?) {
        super.setTitleBar(titleBar)

        titleBar?.hideTitleBar()

    }


}