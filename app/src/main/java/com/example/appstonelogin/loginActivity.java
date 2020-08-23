package com.example.appstonelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {

    private EditText mEtusername;
    private EditText mEtpassword;
    private CheckBox mRememberme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtusername = findViewById(R.id.et_username);;
        mEtpassword = findViewById(R.id.et_password);
        mRememberme = findViewById(R.id.chk_remember);
        Button mBtnLogin = findViewById(R.id.btn_login);

        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED",MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefManager.edit();



        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String username = mEtusername.getText().toString();
               String password = mEtpassword.getText().toString();
               Boolean isRememberme = mRememberme.isChecked();

               if(isRememberme){
                   editor.putString("USERNAME",username);
                   editor.putString("PASSWORD",password);
                   editor.putBoolean("ISREMEMBERME",isRememberme);
                   editor.apply();
               }
               moveToHome();
            }
        });
        Boolean isLoggedIn = prefManager.getBoolean("ISREMEMBERME",false);
        if(isLoggedIn){
            moveToHome();
        }
    }
    private void moveToHome(){
        startActivity(new Intent(loginActivity.this,homeActivity.class));
        finish();
    }
}