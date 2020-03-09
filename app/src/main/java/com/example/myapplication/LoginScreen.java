package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                else if (!userE.equals(fakeE)){
                    message = "Account does not exist" ;
                    showToast(message);


                }
                else if (!userP.equals(fakeP)){
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


}
