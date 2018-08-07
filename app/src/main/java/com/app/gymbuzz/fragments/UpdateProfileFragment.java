package com.app.gymbuzz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.app.gymbuzz.R;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.helpers.CameraHelper;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.interfaces.ImageSetter;
import com.app.gymbuzz.ui.views.AnyEditTextView;
import com.app.gymbuzz.ui.views.TitleBar;
import com.bumptech.glide.Glide;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.utils.Orientation;

/**
 * Created on 5/24/2018.
 */

public class UpdateProfileFragment extends BaseFragment {

    @BindView(R.id.civProfilePic1)
    CircleImageView civProfilePic1;
    @BindView(R.id.civProfilePic)
    CircleImageView civProfilePic;
    @BindView(R.id.btnAdd)
    ImageView btnAdd;
    @BindView(R.id.ll_ProfileImage)
    RelativeLayout llProfileImage;
    @BindView(R.id.edtFullName)
    AnyEditTextView edtFullName;
    @BindView(R.id.edtEmail)
    AnyEditTextView edtEmail;
    @BindView(R.id.spGender)
    Spinner spGender;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.llGender)
    LinearLayout llGender;
    @BindView(R.id.edtAge)
    AnyEditTextView edtAge;
    @BindView(R.id.tilAge)
    TextInputLayout tilAge;
    @BindView(R.id.edtHeight)
    AnyEditTextView edtHeight;
    @BindView(R.id.tilFullName)
    TextInputLayout tilFullName;
    @BindView(R.id.edtWeight)
    AnyEditTextView edtWeight;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    Unbinder unbinder;

    ArrayList genderList = new ArrayList() ;
    ArrayAdapter<String> genderAdapter;

    File profilePic;
    String profilePath = "";

    ArrayList<String> imagesCollection;

    public static UpdateProfileFragment newInstance() {
        return new UpdateProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_profile, container, false);

        //getMainActivity().setImageSetter(this);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();
        setGenderData();

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);

        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.update_profile));
    }

    private void setData(){

        edtFullName.setText("Jhon Doe");
        edtEmail.setText("jhon@gmail.com");
        edtAge.setText("26");
        spGender.setSelection(0);
        edtHeight.setText("69");
        edtWeight.setText("81");

    }

    private boolean isValidated(){


        if(edtFullName.getText().toString().length() > 0){
            if(edtHeight.getText().toString().length() > 0){
                if(edtWeight.getText().toString().length() > 0){
                    return true;
                }else{
                    edtWeight.setError(getString(R.string.please_enter_weight));
                }
            }
            else{
                edtHeight.setError(getString(R.string.please_enter_height));
            }
        }else{
            edtFullName.setError(getString(R.string.please_enter_fullname));
        }

        return false;
    }

    private void setGenderData() {

        genderList = new ArrayList<String>();

        genderList.add("Male");
        genderList.add("Female");

        genderAdapter = new ArrayAdapter<String>(getDockActivity(), R.layout.spinner_item, genderList);
        genderAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spGender.setAdapter(genderAdapter);
        spGender.setSelection(0);
        genderAdapter.notifyDataSetChanged();

    }


    @OnClick({R.id.civProfilePic, R.id.btnUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.civProfilePic:
                checkPermission();
                break;

            case R.id.btnUpdate:

                if(isValidated()){
                    UIHelper.showLongToastInCenter(getMainActivity(),getString(R.string.will_be_imp_beta));
                }

                break;

        }
    }

    public void checkPermission(){

        AndPermission.with(this)
                .runtime()
                .permission(Permission.READ_EXTERNAL_STORAGE, Permission.CAMERA)
                .onGranted(permissions -> {

                    FilePickerBuilder.getInstance().setMaxCount(1)
                            .enableCameraSupport(true)
                            .enableVideoPicker(false)
                            .enableDocSupport(false)
                            .enableSelectAll(false)
                            .showGifs(false)
                            .withOrientation(Orientation.PORTRAIT_ONLY)
                            .showFolderView(false)
                            .setActivityTheme(R.style.AppTheme)
                            .pickPhoto(UpdateProfileFragment.this);

                    // CameraHelper.uploadPhotoDialog(getMainActivity());

                })
                .onDenied(permissions -> {
                    UIHelper.showShortToastInCenter(getDockActivity(), "Permission is required to access this feature");
                })
                .start();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FilePickerConst.REQUEST_CODE_PHOTO) {
            if (data != null) {
                imagesCollection = new ArrayList<>();
                imagesCollection.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));

                profilePath = imagesCollection.get(0);

                Glide.with(getDockActivity())
                        .load("file:///" + profilePath)
                        .into(civProfilePic);

            }

        }
    }


}
