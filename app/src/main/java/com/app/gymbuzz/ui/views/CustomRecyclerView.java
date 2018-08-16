package com.app.gymbuzz.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.app.gymbuzz.ui.adapters.RecyclerViewAdapter;
import com.app.gymbuzz.ui.viewbinders.abstracts.RecyclerViewBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 8/10/2017.
 */

public class CustomRecyclerView<T> extends RecyclerView {
    //    private ArrayList<T> userCollection;
    private RecyclerViewAdapter<T> mRecyclerViewAdapter;
    private RecyclerViewBinder<T> viewBinder;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void BindRecyclerView(RecyclerViewBinder<T> viewBinder,
                                 ArrayList<T> dataCollection, LayoutManager layoutManager, ItemAnimator animator) {
//        this.userCollection = new ArrayList<>();
//        this.userCollection.addAll(dataCollection);
        mRecyclerViewAdapter = new RecyclerViewAdapter<>
                (dataCollection, viewBinder, getContext());
        this.viewBinder = viewBinder;
        this.setLayoutManager(layoutManager);
        this.setItemAnimator(animator);
        this.setAdapter(mRecyclerViewAdapter);
    }

    public T getItemFromList(int index) {
        if (mRecyclerViewAdapter != null) {
            return this.mRecyclerViewAdapter.getItemFromList(index);
        } else {
            return null;
        }
    }

    public List<T> getList() {
        if (mRecyclerViewAdapter != null) {
            return this.mRecyclerViewAdapter.getList();
        } else {
            return null;
        }
    }

    /**
     * Clears the internal list
     */
    public void clearList() {
        if (this.mRecyclerViewAdapter != null) {
            this.mRecyclerViewAdapter.clearList();
            this.mRecyclerViewAdapter.notifyDataSetChanged();

        }
    }

    public void notifyDataSetChanged() {
        if (mRecyclerViewAdapter != null) {
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Adds a entity to the list and calls {@link #notifyDataSetChanged()}.
     * Should not be used if lots of NotificationDummy are added.
     *
     * @see #addAll(List)
     */
    public void add(T entity) {
        if (this.mRecyclerViewAdapter != null) {
            this.mRecyclerViewAdapter.add(entity);

        }
    }

    /**
     * Adds a NotificationDummy to the list and calls
     * {@link #notifyDataSetChanged()}. Can be used {
     * {@link List#subList(int, int)}.
     *
     * @see #addAll(List)
     */
    public void addAll(List<T> entityList) {
        if (this.mRecyclerViewAdapter != null) {
            this.mRecyclerViewAdapter.addAll(entityList);
        }

    }

    public void notifyItemChanged(int i) {
        if (mRecyclerViewAdapter != null)
            mRecyclerViewAdapter.notifyItemChanged(i);
    }

    public void notifyItemRangeChanged(int position, int count) {
        if (mRecyclerViewAdapter != null)
            mRecyclerViewAdapter.notifyItemRangeChanged(position, count);
    }

    public void notifyItemRemoved(int i) {
        if (mRecyclerViewAdapter != null) {
            mRecyclerViewAdapter.notifyItemRemoved(i);
        }
    }

    public void notifyItemInserted(int i) {
        if (mRecyclerViewAdapter != null)
            mRecyclerViewAdapter.notifyItemInserted(i);
    }

    public RecyclerViewAdapter getAdapter() {
        return mRecyclerViewAdapter;
    }
}
