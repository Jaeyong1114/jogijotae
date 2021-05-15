package com.examples.jogijotae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstMain extends AppCompatActivity implements View.OnClickListener {
    Button btn01,btn02,btn03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_main);
    btn01=findViewById(R.id.button);
    btn02=findViewById(R.id.button2);
    btn03=findViewById(R.id.button3);
    btn01.setOnClickListener(this);
    btn02.setOnClickListener(this);
    btn03.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.button) {


        }
        if(v.getId()==R.id.button2){
            Intent intent01 = new Intent(this, LoginActivity.class);
            startActivity(intent01);
        }
        if(v.getId()==R.id.button3){
            Intent intent02 = new Intent(this, RegisterActivity.class);
            startActivity(intent02);
        }
    }
}