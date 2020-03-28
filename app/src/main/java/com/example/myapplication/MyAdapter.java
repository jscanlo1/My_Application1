package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ui.home.dummy.DummyContent;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<DummyContent.DummyItem> itemsData;

    public MyAdapter(List<DummyContent.DummyItem> mValues) {
        this.itemsData = mValues;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.mIdView.setText(itemsData.get(position).id);
        viewHolder.mContentView.setText(itemsData.get(position).content);

        viewHolder.itemView.setTag(itemsData.get(position));


    }
// inner class to hold a reference to each item of RecyclerView
static class ViewHolder extends RecyclerView.ViewHolder {
    final TextView mIdView;
    final TextView mContentView;

    ViewHolder(View view) {
        super(view);
        mIdView = view.findViewById(R.id.id_text);
        mContentView = view.findViewById(R.id.content);
    }
}

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsData.size();
    }
}