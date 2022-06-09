package com.example.giffy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Form extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().setTitle("LogIn Form");
    }

    public void btn_Signup(View view) {
        Intent intent2 = new Intent(Login_Form.this, SignUp_Form.class);
        startActivity(intent2);
//        startActivity(new Intent(getApplicationContext(),SignUp_Form.class));
    }
}
