package com.app.gymbuzz.fragments.OldKotlinClasses

import android.os.Bundle
import android.util.SparseArray
import android.view.*
import com.app.gymbuzz.R
import com.app.gymbuzz.fragments.GymDetailFragment
import com.app.gymbuzz.fragments.abstracts.BaseFragment
import com.app.gymbuzz.helpers.UIHelper
import com.app.gymbuzz.ui.views.TitleBar
import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic
import com.google.android.gms.vision.barcode.Barcode
import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever

/**
 * Created on 5/23/2018.
 */


class ScanQRFragmentKotlin:BaseFragment(), BarcodeRetriever {

    private lateinit var barcodeCapture : BarcodeCapture
    private var viewParent: View? = null
    private val isGymDetail = false

    companion object {
        val Tag: String = "ScanQRFragmentKotlin"
        fun newInstance(): ScanQRFragmentKotlin {
            val fragment = ScanQRFragmentKotlin()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //return inflater.inflate(R.layout.fragment_scan_id,container,false)
        if (viewParent != null) {
            val parent = viewParent!!.getParent() as ViewGroup
            parent?.removeView(viewParent)
        }
        try {
            viewParent = inflater.inflate(R.layout.fragment_scan_id, container, false)
        } catch (e: InflateException) {
            dockActivity.popFragment()
        }

        return viewParent

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        barcodeCapture = childFragmentManager.findFragmentById(R.id.barcode) as BarcodeCapture
        barcodeCapture.setRetrieval(this)
        barcodeCapture.isShowDrawRect = true
        barcodeCapture.isShouldShowText = true

    }

    override fun setTitleBar(titleBar: TitleBar?) {
        super.setTitleBar(titleBar)

        titleBar?.hideButtons()
        titleBar?.showBackButton()
        titleBar?.setSubHeading(getString(R.string.scan_code))

    }

    override fun onRetrieved(barcode: Barcode?) {


        mainActivity.runOnUiThread {
            UIHelper.showShortToastInCenter(dockActivity, getString(R.string.scanned_complete))
            if(isGymDetail) {
                dockActivity.replaceDockableFragment(GymDetailFragment.newInstance(), "GymDetailFragment")
            }else{
                dockActivity.replaceDockableFragment(GymDetailFragment.newInstance(), "GymDetailFragment")
            }
        }


    }

    override fun onRetrievedMultiple(closetToClick: Barcode?, barcode: MutableList<BarcodeGraphic>?) {

    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>?) {

    }

    override fun onRetrievedFailed(reason: String?) {
        UIHelper.showShortToastInCenter(dockActivity, reason)
    }

    override fun onPermissionRequestDenied() {
        UIHelper.showShortToastInCenter(dockActivity, getString(R.string.user_cancel_permission))
        dockActivity.popFragment()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

}