package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ui.home.ItemDetailFragment;
import com.example.myapplication.ui.home.TestFragment;
import com.example.myapplication.ui.home.dummy.DummyContent;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<DummyContent.DummyItem> itemsData;
    private int container;
    //private final mainHomePage mParentActivity;

    public MyAdapter(List<DummyContent.DummyItem> mValues, int x) {
        this.itemsData = mValues;
        this.container = x;
    }

    private LayoutInflater layoutInflater;
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                   int viewType) {



        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);


    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();

            /*
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            mParentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

             */


            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);

            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            //TestFragment fragment = new TestFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit();

            //R.id.nav_host_fragment
        }
    };




    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.mIdView.setText(itemsData.get(position).id);
        viewHolder.mContentView.setText(itemsData.get(position).content);

        viewHolder.itemView.setTag(itemsData.get(position));
        viewHolder.itemView.setOnClickListener(mOnClickListener);


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