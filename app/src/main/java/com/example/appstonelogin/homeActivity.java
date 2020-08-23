package com.example.appstonelogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homeActivity extends AppCompatActivity {
    TextView mTvusername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTvusername = findViewById(R.id.tv_username);
        Button mBtnlogout = findViewById(R.id.btn_logout);
        Button mBtnedit = findViewById(R.id.btn_edit);

        SharedPreferences prefManager = getApplicationContext().getSharedPreferences("SHARED",MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefManager.edit();

        final String username = prefManager.getString("USERNAME","");
        final String password = prefManager.getString("PASSWORD","");
        mTvusername.setText(username);

        mBtnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("USERNAME","");
                editor.putString("PASSWORD","");
                editor.putBoolean("ISREMEMBERME",false);
                editor.apply();

                startActivity(new Intent(homeActivity.this,loginActivity.class));
                finish();
            }
        });

        mBtnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent editIntent = new Intent(homeActivity.this,editActivity.class);
            editIntent.putExtra("USERNAME",username);
            editIntent.putExtra("PASSWORD",password);
            editIntent.putExtra("ISLOGGEDIN",true);
            startActivityForResult(editIntent,1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                String editedusername = data.getExtras().getString("EDITEDUSERNAME");
                String editedpassword = data.getExtras().getString("EDITEDPASSWORD");
                mTvusername.setText(editedusername);
                //editor.putString("USERNAME",editedusername);
               // editor.putString("PASSWORD",editedpassword);
               // editor.apply();
            }
        }
    }
}