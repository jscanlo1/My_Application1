package com.example.myapplication.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.LoginScreen;
import com.example.myapplication.MyAdapter;
import com.example.myapplication.R;
import com.example.myapplication.ui.home.dummy.DummyContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    private MyFavouriteAdapter mAdapter;
    private List<DummyContent.DummyItem> itemsData = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 1; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();

        }*/
        /*
        DummyContent.DummyItem temp;
        for(int i = 0; i < DummyContent.ITEMS.size(); i++){
            temp = DummyContent.ITEMS.get(i);
            if(temp.favourite = TRUE){
                itemsData.add(temp);
            }
        }

         */

        //String myValue = this.getArguments().getString("message");
        //return inflater.inflate(R.layout.fragment_account, container, false);

        for (DummyContent.DummyItem row : DummyContent.ITEMS) {

            // name match condition. this might differ depending on your requirement
            // here we are looking for name or phone number match
            if (row.favourite == TRUE) {
                itemsData.add(row);
            }
        }

       // LoginScreen activity = (LoginScreen) getActivity();
        //String myValue = activity.getMyData();



        View root = inflater.inflate(R.layout.fragment_account, container, false);
        TextView textView = root.findViewById(R.id.text_account);
        textView.setText("Welcome!");


        TextView textView1 = (TextView) root.findViewById(R.id.favourite_intro);
        textView1.setText("Here are some of your favourites:");

        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.favouriteList);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FrameLayout contentView = (FrameLayout) getActivity().findViewById(R.id.nav_host_fragment);
        int x = contentView.getId();

        // 3. create an adapter
        mAdapter = new MyFavouriteAdapter(getActivity(), itemsData, x);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);



        setHasOptionsMenu(true);
        return root;
       //return inflater.inflate(R.layout.fragment_account, container, false);
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
