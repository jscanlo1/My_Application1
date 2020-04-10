package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.ui.home.dummy.DummyContent;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class LoginScreen extends AppCompatActivity {

    private Button Login;
    String userE;
    String userP;

    EditText takeEmail;
    EditText takePassword;
    String message;

    String fakeE = "bob";
    String fakeP = "123" +
            "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        takeEmail = (EditText) findViewById(R.id.takeEmail);
        takePassword = (EditText) findViewById(R.id.takePassword);


        Login = (Button) findViewById(R.id.buttonLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userE = takeEmail.getText().toString();
                userP = takePassword.getText().toString();


                Intent createAccount = new Intent (LoginScreen.this, mainHomePage.class);

                if(TextUtils.isEmpty(userE) || TextUtils.isEmpty(userP) ){
                    message = "There is a missing field";
                    showToast(message);


                }

                else if (!checkUsernames(userE)){
                    message = "Account does not exist" ;
                    showToast(message);


                }
                else if (!checkPasswords(userP)){
                    message = "Incorrect Password";
                    showToast(message);

                }
                else{
                    message = "Welcome";
                    showToast(message);
                    startActivity(createAccount);
                }


            }
        });


    }
    private void showToast(String text) {
        Toast.makeText(LoginScreen.this,text,Toast.LENGTH_SHORT).show();
    }

    public boolean checkUsernames(String userE) {
        String json;
        try {


            InputStream is = getAssets().open("accounts.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("email").equals(userE)) {
                    return true;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return false;
    }

    public boolean checkPasswords (String userP){
        String json2;
        try {

            InputStream is = getAssets().open("accounts.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json2 = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json2);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("passwordHash").equals(userP)) {
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    return false;
    }

}

