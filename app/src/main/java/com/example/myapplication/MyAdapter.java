package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ui.home.dummy.DummyContent;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<DummyContent.DummyItem> itemsData;

    public MyAdapter(List<DummyContent.DummyItem> mValues) {
        this.itemsData = mValues;
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
        /*
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemListContentBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_list_content, parent, false);
        return new ViewHolder(binding);*/

    }



    /*
    private View.OnClickListener createOnClickListener(final String plantId) {
        return v -> Navigation.findNavController(v).navigate(
                HomeFragmentDirections.actionPlantListFragmentToPlantDetailFragment(plantId));

    }*/

    /*
    private View.OnClickListener createOnClickListener(final String plantId) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionPlantListFragmentToPlantDetailFragment direction =
                        HomeFragmentDirections.actionPlantListFragmentToPlantDetailFragment(plantId);
                Navigation.findNavController(view).navigate(direction);

            }
        };
    }*/



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        /*
        DummyContent.DummyItem clickedItem = itemsData.get(position);

        viewHolder.bind(clickedItem, createOnClickListener(clickedItem.id));
        viewHolder.itemView.setTag(itemsData.get(position));
        */


        viewHolder.mIdView.setText(itemsData.get(position).id);
        viewHolder.mContentView.setText(itemsData.get(position).content);

        viewHolder.itemView.setTag(itemsData.get(position));
        //viewHolder.bind(createOnClickListener(plant.getPlantId()), plant);



    }
// inner class to hold a reference to each item of RecyclerView
static class ViewHolder extends RecyclerView.ViewHolder {
    final TextView mIdView;
    final TextView mContentView;

    //List binding;
    //ItemListContentBinding binding;


    ViewHolder(View view) {
        super(view);
        mIdView = view.findViewById(R.id.id_text);
        mContentView = view.findViewById(R.id.content);

        /*
        ViewHolder(@NonNull ItemListContentBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(DummyContent.DummyItem plant, View.OnClickListener clickListener) {
        binding.setClickListener(clickListener);
        binding.setPlant(plant);

        binding.executePendingBindings();
        */


    }
}

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsData.size();
    }
}