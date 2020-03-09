package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {

    private Button submit;
    String email;
    String passOne;
    String passTwo;

    EditText emailID;
    EditText NewPassword;
    EditText NewPasswordTwo;
    String message ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        emailID = (EditText) findViewById(R.id.emailID);
        NewPassword = (EditText) findViewById(R.id.NewPassword);
        NewPasswordTwo = (EditText) findViewById(R.id.NewPasswordTwo);

        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailID.getText().toString();
                passOne = NewPassword.getText().toString();
                passTwo = NewPasswordTwo.getText().toString();


                Intent createAccount = new Intent (SignUpPage.this, MainActivity.class);

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(passOne) || TextUtils.isEmpty(passTwo)){
                    message = "There is a missing field";
                    showToast(message);


                }

                else if (!passOne.equals(passTwo)){
                    message = "Passwords do not match" ;
                    showToast(message);


                }
                else if (passOne.equals(passTwo)){
                    message = "Account created!";
                    showToast(message);
                    startActivity(createAccount);

                }


            }
        });


    }

    private void showToast(String text) {
        Toast.makeText(SignUpPage.this,text,Toast.LENGTH_SHORT).show();
    }





}
