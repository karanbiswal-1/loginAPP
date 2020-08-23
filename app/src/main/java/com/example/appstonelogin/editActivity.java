package com.example.appstonelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final EditText mEteditusername = findViewById(R.id.et_editusername);
        final EditText mEteditpassword = findViewById(R.id.et_editpassword);
        Button mBtnupdate = findViewById(R.id.btn_update);
        Button mBtnback = findViewById(R.id.btn_back);
        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED",MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefManager.edit();

        Bundle intentdata =getIntent().getExtras();

        if(intentdata != null){
            String username = intentdata.getString("USERNAME");
            String password = intentdata.getString("PASSWORD");
            Boolean isLoggedin = intentdata.getBoolean("ISLOGGEDIN");

            mEteditusername.setText(username);
            mEteditpassword.setText(password);
        }

        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(editActivity.this,homeActivity.class));
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        mBtnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedusername = mEteditusername.getText().toString();
                String editedpassword = mEteditpassword.getText().toString();
                editor.putString("USERNAME",editedusername);
                editor.putString("PASSWORD",editedpassword);

                Intent returnIntent = new Intent(editActivity.this,homeActivity.class);
                returnIntent.putExtra("EDITEDUSERNAME",editedusername);
                returnIntent.putExtra("EDITEDPASSWORD",editedpassword);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}