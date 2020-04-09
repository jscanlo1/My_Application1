package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.ui.home.ItemDetailFragment;
import com.example.myapplication.ui.home.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
implements Filterable {
    private List<DummyContent.DummyItem> itemsData;
    private List<DummyContent.DummyItem> itemListFiltered;
    private int container;
    private Context context;



    public MyAdapter(Context context,List<DummyContent.DummyItem> mValues, int x) {
        this.itemsData = mValues;
        this.itemListFiltered = mValues;
        this.container = x;
        this.context = context;
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




            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);

            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            //TestFragment fragment = new TestFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit();
            //activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

            //R.id.nav_host_fragment
        }
    };




    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final DummyContent.DummyItem item = itemListFiltered.get(position);
        viewHolder.mIdView.setText(item.name);
        viewHolder.mContentView.setText(item.description);

        viewHolder.itemView.setTag(item);
        viewHolder.itemView.setOnClickListener(mOnClickListener);
        Glide.with(context)
                .load(item.imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.mImageView);


    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.equals("all")) {
                    itemListFiltered = itemsData;
                } else {
                    List<DummyContent.DummyItem> filteredList = new ArrayList<>();
                    for (DummyContent.DummyItem row : itemsData) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.type.contentEquals(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    itemListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = itemListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                itemListFiltered = (ArrayList<DummyContent.DummyItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
// inner class to hold a reference to each item of RecyclerView
static class ViewHolder extends RecyclerView.ViewHolder {
    final TextView mIdView;
    final TextView mContentView;
    final ImageView mImageView;


    ViewHolder(View view) {
        super(view);
        /*
        mIdView = view.findViewById(R.id.id_text);
        mContentView = view.findViewById(R.id.content);
        */
        mIdView = view.findViewById(R.id.name);
        mContentView = view.findViewById(R.id.phone);
        mImageView = view.findViewById(R.id.thumbnail);

    }
}

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemListFiltered.size();
    }
}