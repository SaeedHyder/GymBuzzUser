package com.app.gymbuzz.helpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.app.gymbuzz.R;
import com.app.gymbuzz.ui.views.AnyEditTextView;
import com.app.gymbuzz.ui.views.CustomRatingBar;

public class DialogHelper {
    private Dialog dialog;
    private Context context;

    public DialogHelper(Context context) {
        this.context = context;
        this.dialog = new Dialog(context);
    }

    public Dialog initRatingDialog(FeedbackDoneListener feedbackDoneListener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(R.layout.dialog_feedback);
        Button okbutton = dialog.findViewById(R.id.btn_submit);
        AnyEditTextView editTextView = dialog.findViewById(R.id.txt_feedback);
        final CustomRatingBar rating = dialog.findViewById(R.id.rbAddRating);
        okbutton.setOnClickListener(v -> {
            if (editTextView.getText().toString().trim().equals("")) {
                editTextView.setError(dialog.getContext().getResources().getString(R.string.feedbackMessage));
                return;
            }
            if (feedbackDoneListener != null)
                feedbackDoneListener.onSubmitFeedback(editTextView.getText().toString(), Math.round(rating.getScore()));
        });


        rating.setOnScoreChanged(score -> {
            if (score < 1.0f)
                rating.setScore(1.0f);
        });
        return this.dialog;
    }

    public Dialog initlogout(int layoutID, View.OnClickListener onokclicklistener, View.OnClickListener oncancelclicklistener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(layoutID);
        Button okbutton = (Button) dialog.findViewById(R.id.btn_yes);
        okbutton.setOnClickListener(onokclicklistener);
        Button cancelbutton = (Button) dialog.findViewById(R.id.btn_No);
        cancelbutton.setOnClickListener(oncancelclicklistener);
        return this.dialog;
    }
    /*public Dialog initForgotPasswordDialog(int layoutID, View.OnClickListener onclicklistener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        this.dialog.setContentView(layoutID);
        AnyTextView btnOk = (AnyTextView) dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(onclicklistener);
        return this.dialog;
    }*/

   /* public Dialog initAddReviewDialog(int layoutID, View.OnClickListener onokclicklistener, View.OnClickListener oncancelclicklistener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        this.dialog.setContentView(layoutID);

        final AnyEditTextView edtReview = (AnyEditTextView) dialog.findViewById(R.id.edtReview);

        Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(onokclicklistener);

        ImageView btnCLose = (ImageView) dialog.findViewById(R.id.btnCLose);
        btnCLose.setOnClickListener(oncancelclicklistener);

        return this.dialog;
    }*/

    /*public Dialog initLoginAlertDialog(int layoutID, View.OnClickListener onokclicklistener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        this.dialog.setContentView(layoutID);

        Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(onokclicklistener);

        return this.dialog;
    }*/

  /*  public Dialog initCountryCodeDialog(int layoutID, View.OnClickListener onokclicklistener, View.OnClickListener oncancelclicklistener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        this.dialog.setContentView(layoutID);
        AnyTextView btnSubmit = (AnyTextView) dialog.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(onokclicklistener);
        AnyTextView btnCancel = (AnyTextView) dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(oncancelclicklistener);

        return this.dialog;
    }*/



   /* public Dialog initLogoutDialog(int layoutID, View.OnClickListener onyesclicklistener, View.OnClickListener onnoclicklistener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        this.dialog.setContentView(layoutID);
        Button btnYes = (Button) dialog.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(onyesclicklistener);
        Button btnNo = (Button) dialog.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(onnoclicklistener);

        return this.dialog;
    }*/

    public void showDialog() {
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void setCancelable(boolean isCancelable) {
        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(isCancelable);
    }

    public void hideDialog() {
        dialog.dismiss();
    }

    public interface FeedbackDoneListener {
        void onSubmitFeedback(String feedback, int rating);
    }

}
