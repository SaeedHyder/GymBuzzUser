package com.app.gymbuzz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.gymbuzz.R;
import com.app.gymbuzz.entities.NotificationEnt;
import com.app.gymbuzz.fragments.abstracts.BaseFragment;
import com.app.gymbuzz.global.WebServiceConstants;
import com.app.gymbuzz.helpers.DialogHelper;
import com.app.gymbuzz.helpers.UIHelper;
import com.app.gymbuzz.ui.adapters.ArrayListAdapter;
import com.app.gymbuzz.ui.binders.BinderNotification;
import com.app.gymbuzz.ui.views.AnyTextView;
import com.app.gymbuzz.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by khan_muhammad on 9/15/2017.
 */

public class NotificationsFragment extends BaseFragment {

    @BindView(R.id.lv_notification)
    ListView lvNotification;
    Unbinder unbinder;
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    private List<NotificationEnt> notificationCollection;
    private ArrayListAdapter<NotificationEnt> adapter;

    public static NotificationsFragment newInstance() {
        return new NotificationsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayListAdapter<>(getDockActivity(), new BinderNotification(getDockActivity(), prefHelper));
    }

    @Override
    public void ResponseSuccess(Object result, String Tag) {
        switch (Tag) {
            case WebServiceConstants.GET_ALL_NOTIFICATIONS:
                bindData((ArrayList<NotificationEnt>) result);
                break;
            case WebServiceConstants.SUBMIT_MACHINE_FEEDBACK:
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.feedbackSuccessMessage));
                break;
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showBackButton();
        titleBar.setSubHeading(getString(R.string.notification));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefHelper.setNotificationCount(0);
        //serviceHelper.enqueueCall(webService.getNotificationCount(prefHelper.getMerchantId()), WebServiceConstants.NotificaitonCount);
        //bindData();
        serviceHelper.enqueueCall(webService.getAllNotifications(prefHelper.getUserToken()), WebServiceConstants.GET_ALL_NOTIFICATIONS);
        lvNotification.setOnItemClickListener((parent, view1, position, id) -> {
            if (notificationCollection.get(position).getNotification().getActiontype() == WebServiceConstants.NOTIFICATION_ACTION_JOB_COMPLETE) {
                DialogHelper dialogHelper = new DialogHelper(getDockActivity());
                dialogHelper.initRatingDialog((feedback, rating) -> {
                    serviceHelper.enqueueCall(webService.submitSupportFeedback(feedback, rating, notificationCollection.get(position).getNotification().getActionid(), prefHelper.getUserToken()),
                            WebServiceConstants.SUBMIT_MACHINE_FEEDBACK);
                    dialogHelper.hideDialog();
                });
                dialogHelper.setCancelable(true);
                dialogHelper.showDialog();
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    public void bindData(ArrayList<NotificationEnt> result) {

        notificationCollection = new ArrayList<>();

        if (result.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lvNotification.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            lvNotification.setVisibility(View.VISIBLE);

        }
        notificationCollection.addAll(result);
        adapter.clearList();
        lvNotification.setAdapter(adapter);
        adapter.addAll(notificationCollection);
        adapter.notifyDataSetChanged();

    }

}
