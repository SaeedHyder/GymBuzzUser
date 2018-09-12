package com.app.gymbuzz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
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
import com.app.gymbuzz.entities.UserModel;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.AppConstants;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.helpers.DateHelper;
import com.app.gymbuzz.helpers.DatePickerHelper;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.views.AnyEditTextView;
import com.app.gymbuzz.ui.views.TitleBar;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.utils.Orientation;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    ArrayList genderList = new ArrayList();
    ArrayAdapter<String> genderAdapter;

    File profilePic;
    String profilePath = "";

    ArrayList<String> imagesCollection;
    @BindView(R.id.edtAbout)
    AnyEditTextView edtAbout;
    private String dob = "";

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

    private void setData() {
        UserModel user = prefHelper.getUser();
        ImageLoader.getInstance().displayImage(user.getProfileimagepath(), civProfilePic);
        edtFullName.setText(user.getFullname());
        edtEmail.setText(user.getEmail());
        edtHeight.setText(user.getHeight() + "");
        edtWeight.setText(user.getWeight() + "");
        edtAbout.setText(user.getAbout() == null ? "-" : user.getAbout());
        spGender.setSelection(user.getGender());
        edtAge.setText(user.getUserAge());
        edtAge.setInputType(InputType.TYPE_NULL);
        edtAge.setOnClickListener(v -> {
            initDatePicker(edtAge);
        });
        dob = user.getDob();

    }

    private void initDatePicker(final AnyEditTextView textView) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 1);

        Calendar calendar = Calendar.getInstance();
        final DatePickerHelper datePickerHelper = new DatePickerHelper();
        datePickerHelper.initDateDialog(
                getDockActivity(),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
                , (view, year, month, dayOfMonth) -> {
                    //textView.setText(datePickerHelper.getStringDate(year, month, dayOfMonth));

                    month = month + 1;

                    String _month = "";

                    if (month < 10) {
                        _month = "0" + month;
                    } else {
                        _month = month + "";
                    }

                    dob = dayOfMonth + "/" + _month + "/" + year;


                    textView.setText(DateHelper.getAge(AppConstants.LOG_DATE_FORMAT, dob));

                }, "PreferredDate", gc.getTime(), null);

        datePickerHelper.showDate();
    }

    private boolean isValidated() {


        if (edtFullName.getText().toString().length() > 0) {
            if (edtHeight.getText().toString().length() > 0) {
                if (edtWeight.getText().toString().length() > 0) {
                    return true;
                } else {
                    edtWeight.setError(getString(R.string.please_enter_weight));
                }
            } else {
                edtHeight.setError(getString(R.string.please_enter_height));
            }
        } else {
            edtFullName.setError(getString(R.string.please_enter_fullname));
        }

        return false;
    }

    private void setGenderData() {

        genderList = new ArrayList<String>();

        genderList.add(getString(R.string.male));
        genderList.add(getString(R.string.female));

        genderAdapter = new ArrayAdapter<String>(getDockActivity(), R.layout.spinner_item, genderList);
        genderAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spGender.setAdapter(genderAdapter);
        spGender.setSelection(0);
        genderAdapter.notifyDataSetChanged();

    }

    @Override
    public void ResponseFailure(String tag) {
        switch (tag) {
            case WebServiceConstants.EDIT_PROFILE:
                btnUpdate.setEnabled(true);
                break;
        }
    }

    @OnClick({R.id.civProfilePic, R.id.btnUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.civProfilePic:
                checkPermission();
                break;

            case R.id.btnUpdate:

                if (isValidated()) {
                    MultipartBody.Part filePart = null;
                    if (profilePic != null) {
                        filePart = MultipartBody.Part.createFormData("file", profilePic.getName(), RequestBody.create(MediaType.parse("image"), profilePic));
                    }

                    RequestBody fullname = RequestBody.create(MediaType.parse("text/plain"), edtFullName.getText().toString() + "");
                    RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), spGender.getSelectedItemPosition() == 1 ? "0" : "1");
                    RequestBody dob1 = RequestBody.create(MediaType.parse("text/plain"), dob + "");
                    RequestBody height = RequestBody.create(MediaType.parse("text/plain"), edtHeight.getText().toString() + "");
                    RequestBody weight = RequestBody.create(MediaType.parse("text/plain"), edtWeight.getText().toString() + "");
                    RequestBody about = RequestBody.create(MediaType.parse("text/plain"), edtAbout.getText().toString() + "");
                    serviceHelper.enqueueCall(webService.editProfile(prefHelper.getUserToken(), fullname, gender, dob1, height, weight, about, filePart != null ? filePart : null), WebServiceConstants.EDIT_PROFILE);

                    btnUpdate.setEnabled(false);
                    break;

                }
        }
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag) {
            case WebServiceConstants.EDIT_PROFILE:
                btnUpdate.setEnabled(true);
                UserModel user = (UserModel) result;
                prefHelper.putUser(user);
                prefHelper.setUserToken(WebServiceConstants.TOKEN_TYPE + " " + user.getAuthtoken());
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.changes_saved));
                getDockActivity().popFragment();
                break;
        }
    }

    public void checkPermission() {

        AndPermission.with(UpdateProfileFragment.this)
                .runtime()
                .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE, Permission.CAMERA)
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
                try {
                    profilePic = new Compressor(getDockActivity()).compressToFile(new File(profilePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Glide.with(getDockActivity())
                        .load("file:///" + profilePath)
                        .into(civProfilePic);

            }

        }
    }


}
