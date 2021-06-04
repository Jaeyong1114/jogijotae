package com.examples.jogijotae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Interestmain extends AppCompatActivity implements View.OnClickListener {

    Button interest_btn_place, interest_btn_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interestmain);

        interest_btn_place = findViewById(R.id.interest_btn_place);
        interest_btn_place.setOnClickListener(this);

        interest_btn_res = findViewById(R.id.interest_btn_res);
        interest_btn_res.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.interest_btn_place)
        {
            Intent data_recevie = getIntent();
            String email = data_recevie.getStringExtra("email");
            Intent interest_place = new Intent(Interestmain.this, Interest_place.class);
            interest_place.putExtra("email", email);
            startActivity(interest_place);
        }
        if (v.getId() == R.id.interest_btn_res)
        {

        }
    }
}