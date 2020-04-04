package com.example.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MyAdapter;
import com.example.myapplication.R;
import com.example.myapplication.ui.home.dummy.DummyContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    //private List<DummyContent> plantsList = new ArrayList<>();
    //private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        //int x = 1000147;
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list_of_stuff);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FragmentManager fm = getFragmentManager();


        FrameLayout contentView = (FrameLayout) getActivity().findViewById(R.id.nav_host_fragment);
        int x = contentView.getId();
        //int x = ((ViewGroup)(getView().getParent())).getId();
        // 3. create an adapter
        MyAdapter mAdapter = new MyAdapter(DummyContent.ITEMS, x);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
