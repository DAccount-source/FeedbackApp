package com.akanksha.feedbackapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Form extends AppCompatActivity {

    DBHelper mydb;
    EditText txtemail,txtpwd;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        txtemail= findViewById(R.id.email_text);
        txtpwd= findViewById(R.id.LPwdTxt);
        mydb=new DBHelper(this);
        btnlogin= findViewById(R.id.LoginBtn);
        getSupportActionBar().setTitle("Login Form");
        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String emailId= txtemail.getText().toString();
                String password= txtpwd.getText().toString();
                String storedPassword=mydb.getSingleEntry(emailId);
                if (password.equals(storedPassword)) {
                    Toast.makeText(Login_Form.this, "Congrats: Login Successfull!!!", Toast.LENGTH_SHORT).show();
                    mydb.LoginInsertData(emailId,password);
                    startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                }
                else {
                    Toast.makeText(Login_Form.this, "Email or Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void btn_signupForm(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
}
