package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MyAdapter;
import com.example.myapplication.R;
import com.example.myapplication.ui.home.dummy.DummyContent;


public class HomeFragment extends Fragment {
    private MyAdapter mAdapter;

    //private List<DummyContent> plantsList = new ArrayList<>();
    //private HomeViewModel homeViewModel;
    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fx = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fx.getBackStackEntryCount(); ++i) {
            fx.popBackStack();


    }*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        FragmentManager fx = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fx.getBackStackEntryCount(); ++i) {
            fx.popBackStack();
        }


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
        mAdapter = new MyAdapter(getActivity(), DummyContent.ITEMS, x);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);

        setHasOptionsMenu(true);
        return rootView;

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRestaurants:
                // User chose the "Settings" item, show the app settings UI...
                mAdapter.getFilter().filter("restaurant");
                return true;

            case R.id.menuCafes:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                mAdapter.getFilter().filter("cafe");
                return true;

            case R.id.menuEvents:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                mAdapter.getFilter().filter("event");
                return true;
            case R.id.menuAll:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                mAdapter.getFilter().filter("all");
                return true;

            default:
                mAdapter.getFilter().filter("all");
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
                //return true;
        }
        //
    }
}
