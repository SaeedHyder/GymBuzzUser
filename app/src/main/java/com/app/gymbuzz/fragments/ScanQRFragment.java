package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.GymDetailModel;
import com.app.gymbuzz.entities.WorkoutModel;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.views.TitleBar;
import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created on 5/24/2018.
 */

public class ScanQRFragment extends BaseFragment implements BarcodeRetriever {

    private BarcodeCapture barcodeCapture;
    private View viewParent;
    private Vibrator myVib;
    public static boolean isWorkout;

   /* public void setIsWorkout(boolean isWorkout) {
        this.isWorkout = isWorkout;
    }*/

    public static ScanQRFragment newInstance() {
        return new ScanQRFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        myVib = (Vibrator) getDockActivity().getSystemService(VIBRATOR_SERVICE);
        barcodeCapture = (BarcodeCapture) getChildFragmentManager().findFragmentById(R.id.barcode);
        barcodeCapture.setRetrieval(this);
        barcodeCapture.setShowDrawRect(true);
        barcodeCapture.setShouldShowText(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        if (viewParent != null) {
            ViewGroup parent = (ViewGroup) viewParent.getParent();
            if (parent != null)
                parent.removeView(viewParent);
        }
        try {
            viewParent = inflater.inflate(R.layout.fragment_scan_id, container, false);
        } catch (InflateException e) {
            getDockActivity().popFragment();
        }
        return viewParent;

    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.scan_code));
    }

    @Override
    public void onRetrieved(final Barcode barcode) {

        getMainActivity().runOnUiThread(() -> {
            myVib.vibrate(50);
            Log.d("ScanQRFragment", "onRetrieved: " + barcode.toString());
            // UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.scanned_complete));
            if (isWorkout) {
                serviceHelper.enqueueCall(webService.getMachineDetail(barcode.displayValue, prefHelper.getUserToken()), WebServiceConstants.GET_MACHINE_DETAILS);
            } else {
                serviceHelper.enqueueCall(webService.getGymDetail(barcode.displayValue, prefHelper.getUserToken()), WebServiceConstants.GET_GYM_DETAILS);
            }
        });

    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag) {
            case WebServiceConstants.GET_GYM_DETAILS:
                barcodeCapture.stopScanning();
                getDockActivity().replaceDockableFragment(GymDetailFragment.newInstance((GymDetailModel) result), GymDetailFragment.class.getSimpleName());
                break;
            case WebServiceConstants.GET_MACHINE_DETAILS:
                barcodeCapture.stopScanning();
                getDockActivity().replaceDockableFragment(WorkOutMachineFragment.newInstance((WorkoutModel)result), WorkOutMachineFragment.class.getSimpleName());
                break;
        }
    }

    @Override
    public void onRetrievedMultiple(Barcode closetToClick, List<BarcodeGraphic> barcode) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onRetrievedFailed(String reason) {
        UIHelper.showShortToastInCenter(getDockActivity(), reason);
    }

    @Override
    public void onPermissionRequestDenied() {
        UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.user_cancel_permission));
        getDockActivity().popFragment();
    }

    @Override
    public void onPause() {
        barcodeCapture.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        barcodeCapture.onResume();
    }
}