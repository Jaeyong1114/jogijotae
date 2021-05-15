package com.examples.jogijotae;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
Button btn01;
    @Override

    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn01=findViewById(R.id.btn_register);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_register){

        }
    }
}