package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

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

    private TextView get_response_text,post_response_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        takeEmail = (EditText) findViewById(R.id.takeEmail);
        takePassword = (EditText) findViewById(R.id.takePassword);
//
//        get_response_text=findViewById(R.id.get_respone_data);
//        post_response_text=findViewById(R.id.post_respone_data);
//        get_request_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendGetRequest();
//            }
//        });


        Login = (Button) findViewById(R.id.buttonLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userE = takeEmail.getText().toString();
                userP = takePassword.getText().toString();
                sendGetRequest();

                Intent createAccount = new Intent (LoginScreen.this, mainHomePage.class);

                if(TextUtils.isEmpty(userE) || TextUtils.isEmpty(userP) ){
                    message = "There is a missing field";
                    showToast(message);


                }

                else if (!userE.equals(fakeE)){
                    message = "Account does not exist" ;
                    showToast(message);


                }
                else if (!userP.equals(fakeP)){
                    message = "Incorrect Password";
                    showToast(message);

                }
                else{
                    try {
                        int d = loginServer("s","f");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    message = "Welcome";
                    showToast(message);
                    startActivity(createAccount);
                }


            }
        });
    }

    private void copyInputStreamToOutputStream(InputStream in, PrintStream out) {

    }

    private void showToast(String text) {
        Toast.makeText(LoginScreen.this,text,Toast.LENGTH_SHORT).show();
    }

    private int loginServer(String username, String password) throws MalformedURLException {
//        final TextView textView = (TextView) findViewById(R.id.text);
//        int serverResponse = 0;
//// ...
//// Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="https://localhost:44379/api/Users/LogIn?userName=dylanStorey&password=dsd4t4n6ioiejfajeo4330hjobcnaknsrouetnshg4";
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                   // @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: "+ response.substring(0,500));
//
//                    }
//                }, new Response.ErrorListener() {
//           // @Override
//                    public void onErrorResponse(VolleyError error) {
//                        textView.setText("That didn't work!");
//                    }
//        });
//        String responseAsString = textView.getText().toString();
//        serverResponse = Integer.parseInt(responseAsString);
//        return serverResponse;
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);
//        };
        URL url = new URL("http://localhost:44379/api/Users/LogIn?userName=dylanStorey&password=dsd4t4n6ioiejfajeo4330hjobcnaknsrouetnshg4");
        int result = 0;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream in = null;
            try {
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = readStream(in);
        } finally {
            urlConnection.disconnect();
        }
    return result;
    }

    private void sendGetRequest() {
        //get working now
        //let's try post and send some data to server
        RequestQueue queue= Volley.newRequestQueue(LoginScreen.this);
        String url="http://localhost:44379/api/Users/LogIn?userName=dylanStorey&password=dsd4t4n6ioiejfajeo4330hjobcnaknsrouetnshg4";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                get_response_text.setText("Data : "+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    get_response_text.setText("Data 1 :"+jsonObject.getString("data_1")+"\n");
                    get_response_text.append("Data 2 :"+jsonObject.getString("data_2")+"\n");
                    get_response_text.append("Data 3 :"+jsonObject.getString("data_3")+"\n");
                }
                catch (Exception e){
                    e.printStackTrace();
                    get_response_text.setText("Failed to Parse Json");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                get_response_text.setText("Data : Response Failed");
            }
        });

        queue.add(stringRequest);
    }

    private int readStream(InputStream in) {
    return 1;
    }
    }

