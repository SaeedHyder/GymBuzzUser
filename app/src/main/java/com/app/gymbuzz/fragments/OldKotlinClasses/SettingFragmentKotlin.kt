package com.app.gymbuzz.fragments.OldKotlinClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.app.gymbuzz.R
import com.app.gymbuzz.fragments.ViewProfileFragment
import com.app.gymbuzz.fragments.abstracts.BaseFragment
import com.app.gymbuzz.helpers.DialogHelper
import com.app.gymbuzz.ui.views.TitleBar
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * Created on 5/17/2018.
 */


class SettingFragmentKotlin : BaseFragment() {

  /*  private val txtViewProfile: AnyTextView by bindView(R.id.txtViewProfile)
    private val txtUnits: AnyTextView by bindView(R.id.txtUnits)
    private val cbLbs: CheckBox by bindView(R.id.cbLbs)
    private val cbKgs: CheckBox by bindView(R.id.cbKgs)
    private val toggleNotification: ToggleButton by bindView(R.id.toggleNotification)
    private val txtChangePassword: AnyTextView by bindView(R.id.txtChangePassword)
    private val txtAboutUs: AnyTextView by bindView(R.id.txtAboutUs)
    private val txtContactUs: AnyTextView by bindView(R.id.txtContactUs)
    private val txtLogout: AnyTextView by bindView(R.id.txtLogout)*/

    companion object {
        val Tag: String = "SettingFragmentKotlin"
        fun newInstance(): SettingFragmentKotlin {
            val fragment = SettingFragmentKotlin()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_setting,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if( prefHelper.isUnitKg){
            cbKgs.isChecked = true
            cbLbs.isChecked = false
        }else{
            cbLbs.isChecked = true
            cbKgs.isChecked = false
        }

        cbKgs.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                cbLbs.isChecked = false
                prefHelper.isUnitKg = isChecked
            }

        })

        cbLbs.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                cbKgs.isChecked = false
                prefHelper.isUnitKg = false
            }

        })

        txtViewProfile.setOnClickListener(View.OnClickListener {

            dockActivity.replaceDockableFragment(ViewProfileFragment.newInstance(), "ViewProfileFragmentKotlin")


        })


        txtChangePassword.setOnClickListener(View.OnClickListener {

            dockActivity.replaceDockableFragment(ChangePasswordFragmentKotlin.newInstance(), "ChangePasswordFragmentKotlin")

        })

        txtAboutUs.setOnClickListener(View.OnClickListener {

            dockActivity.replaceDockableFragment(AboutUsFragmentKotlin.newInstance(), "AboutUsFragmentKotlin")

        })

        txtContactUs.setOnClickListener(View.OnClickListener {

            dockActivity.replaceDockableFragment(AboutUsFragmentKotlin.newInstance(), "ContactusFragmentKotlin")

        })

        txtLogout.setOnClickListener(View.OnClickListener {



            val logoutdialog = DialogHelper(dockActivity)

            logoutdialog.initlogout(R.layout.dialog_logout, View.OnClickListener {

                logoutdialog.hideDialog()
                prefHelper.setLoginStatus(false)
                dockActivity.popBackStackTillEntry(0)
                dockActivity.replaceDockableFragment(LoginFragmentKotlin.newInstance(), "LoginFragmentKotlin")

            }, View.OnClickListener {
                logoutdialog.hideDialog()
            })
            logoutdialog.setCancelable(false)
            logoutdialog.showDialog()

        })

    }


    override fun setTitleBar(titleBar: TitleBar?) {
        super.setTitleBar(titleBar)

        titleBar?.hideButtons()
        titleBar?.showBackButton()
        titleBar?.setSubHeading(getString(R.string.settings))

    }
}