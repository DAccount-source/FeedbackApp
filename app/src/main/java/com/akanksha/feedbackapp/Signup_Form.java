package com.akanksha.feedbackapp;

import android.annotation.SuppressLint;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup_Form extends AppCompatActivity {

    DBHelper mydb;
    EditText txtfullname,txtusername,txtphoneno,txtemail,txtpwd,txtconfirmpwd;
    Button btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Signup Form");
        mydb=new DBHelper(this);

        txtfullname=(EditText) findViewById(R.id.FullnameTxt);
        txtusername=(EditText) findViewById(R.id.UsernameTxt);
        txtphoneno=(EditText) findViewById(R.id.PhoneTxt);
        txtemail=(EditText) findViewById(R.id.EmailTxt);
        txtpwd=(EditText) findViewById(R.id.PwdTxt);
        txtconfirmpwd=(EditText) findViewById(R.id.Pwd2Txt);

        btnreg=(Button)findViewById(R.id.RegBtn);

        insertData();
    }

    private void insertData() {
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=mydb.SignupInsertData(txtfullname.getText().toString(),txtusername.getText().toString(),
                        txtphoneno.getText().toString(),
                        txtemail.getText().toString(),txtpwd.getText().toString(),txtconfirmpwd.getText().toString());
                if (isInserted=true) {
                    Toast.makeText(Signup_Form.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(Signup_Form.this, "Data not Inserted", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
