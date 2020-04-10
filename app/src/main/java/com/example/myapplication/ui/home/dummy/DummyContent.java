package com.example.myapplication.ui.home.dummy;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<>();

    //private static final int COUNT = 25;
    private static int COUNT;





    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void create(Context context) {
        String json;
        String favouriteJson;
        Boolean favourite;
        try {

            InputStream is = context.getAssets().open("restaurants.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            COUNT = jsonArray.length();

            InputStream isF = context.getAssets().open("favourite.json");
            int sizeF = isF.available();
            byte[] bufferF = new byte[sizeF];
            isF.read(bufferF);
            isF.close();

            favouriteJson = new String(bufferF, StandardCharsets.UTF_8);
            JSONArray favouriteJsonArray = new JSONArray(favouriteJson);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONObject UserFavouriteObj = favouriteJsonArray.getJSONObject(i);
                if(UserFavouriteObj.getInt("favourite") == 1){
                    favourite = TRUE;
                } else{
                    favourite = FALSE;
                }

                addItem(createDummyItem(obj.getString("ID"),obj.getString("restaurantName"),
                        obj.getString("category"),obj.getString("description"),
                                obj.getString("longitude"),obj.getString("latitude"),
                                        obj.getString("details"), obj.getString("imageUrl"), favourite));

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

        // Add some sample items.



    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    //private static DummyItem createDummyItem(int position) {
    private static DummyItem createDummyItem(String Id, String name, String type, String description,
                                             String longitude, String latitude, String details, String imageUrl, Boolean favourite ) {
        //return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position),
        //        "https://upload.wikimedia.org/wikipedia/commons/5/55/Apple_orchard_in_Tasmania.jpg", maketype(position));

        return new DummyItem(Id, name, type, description, longitude, latitude, details, imageUrl, favourite);


    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String name;
        public final String type;
        public final String description;
        final String longitude;
        final String latitude;
        public final String details;
        public final String imageUrl;
        public LatLng location;
        public boolean favourite;




        DummyItem(String id, String name, String type, String description,
                  String longitude, String latitude, String details, String imageUrl, Boolean favourite) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.description = description;
            this.longitude = longitude;
            this.latitude = latitude;
            this.details = details;
            this.imageUrl = imageUrl;
            this.favourite = favourite;
            //this.location  = new LatLng(Integer.parseInt(longitude), Integer.parseInt(latitude));

            try {
                this.location  = new LatLng(Double.parseDouble(longitude), Double.parseDouble(latitude));
            } catch(NumberFormatException nfe) {
                this.location = new LatLng(0,0);

            }



        }

        @NonNull
        @Override
        public String toString() {
            return description;
        }
    }

    /*
    @BindingAdapter("imageFromUrl")
    public static void imageFromUrl(ImageView view, String imageUrl) {
        if (imageUrl !=  null && !imageUrl.isEmpty())
            Glide.with(view.getContext()).load(imageUrl).into(view);
    }

     */
}
