package com.examples.jogijotae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button place_btn, res_btn, category_btn , interest_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        place_btn=findViewById(R.id.place_btn);
        res_btn=findViewById(R.id.res_btn);
        category_btn=findViewById(R.id.category_btn);
        interest_btn=findViewById(R.id.interest_btn);


        place_btn.setOnClickListener(this);
        res_btn.setOnClickListener(this);
        category_btn.setOnClickListener(this);
        interest_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.place_btn) {
            Intent intent01 = new Intent(this, Placemain.class);
            startActivity(intent01);
        }


            if(v.getId()==R.id.res_btn) {
                Intent data_recevie = getIntent();
                String email = data_recevie.getStringExtra("email");
                Intent intent01 = new Intent(this, Restaurantmain.class);
                intent01.putExtra("email", email);
                startActivity(intent01);
            }
        if(v.getId()==R.id.category_btn) {
            Intent intent01 = new Intent(this, Categorymain.class);
            startActivity(intent01);

        }
        if(v.getId()==R.id.interest_btn){
            Intent intent01 = new Intent(this,Interestmain.class);
            startActivity(intent01);


        }

    }
}