package com.app.gymbuzz.fragments.OldKotlinClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.gymbuzz.R
import com.app.gymbuzz.fragments.abstracts.BaseFragment
import com.app.gymbuzz.ui.views.TitleBar
import kotlinx.android.synthetic.main.fragment_compute_bmi.*

/**
 * Created on 5/16/2018.
 */

class CalculateBmiFragmentKotlin:BaseFragment() {

    companion object {
        val Tag: String = "CalculateBmiFragmentKotlin"
        fun newInstance(): CalculateBmiFragmentKotlin {
            val fragment = CalculateBmiFragmentKotlin()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_compute_bmi,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnComputeBmi.setOnClickListener(View.OnClickListener {

            if(validate()){

                val weight: Double
                val height: Double
                val bMI: Double

                weight = edtWeight.text.toString().toDouble()
                height = edtHeight.text.toString().toDouble()

                bMI = (weight * 703) / (height * height)

                txtBmi.text = bMI.toString()


                edtWeight.text.clear()
                edtHeight.text.clear()

            }

        })

    }

    fun validate():Boolean{

        if(edtHeight.text.toString().length > 0){
            if(edtWeight.text.toString().length > 0){
                return true
            }else{
                edtWeight.error = getString(R.string.please_enter_weight);
            }
        }else{
            edtHeight.error = getString(R.string.please_enter_height);
        }

        return false
    }

    override fun setTitleBar(titleBar: TitleBar?) {
        super.setTitleBar(titleBar)

        titleBar?.hideButtons()
        titleBar?.showBackButton()
        titleBar?.setSubHeading(getString(R.string.calculate_bmi))
    }

}