package com.examples.jogijotae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Interestmain extends AppCompatActivity implements View.OnClickListener {

    Button interest_btn_place, interest_btn_res;
    private static final String TAG = "Interestmain";

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
            Intent intent01 = getIntent();
            double latitude = intent01.getDoubleExtra("latitude", 0);
            double longitude = intent01.getDoubleExtra("longitude", 0);
            String place01 = data_recevie.getStringExtra("place01");
            String place02 = data_recevie.getStringExtra("place02");
            String place03 = data_recevie.getStringExtra("place03");
            String place04 = data_recevie.getStringExtra("place04");
            String place05 = data_recevie.getStringExtra("place05");
            String place06 = data_recevie.getStringExtra("place06");

            Intent interest_place = new Intent(Interestmain.this, Interest_place.class);
            interest_place.putExtra("latitude", latitude);
            interest_place.putExtra("longitude", longitude);
            interest_place.putExtra("place01", place01);
            interest_place.putExtra("place02", place02);
            interest_place.putExtra("place03", place03);
            interest_place.putExtra("place04", place04);
            interest_place.putExtra("place05", place05);
            interest_place.putExtra("place06", place06);
            startActivity(interest_place); // 유저의 관광지 취향정보를 넘기면서 Interest_place로 이동
        }
        if (v.getId() == R.id.interest_btn_res)
        {
            Intent data_recevie = getIntent();
            Intent intent01 = getIntent();
            double latitude = intent01.getDoubleExtra("latitude", 0);
            double longitude = intent01.getDoubleExtra("longitude", 0);
            String restarant01 = data_recevie.getStringExtra("restarant01");
            String restarant02 = data_recevie.getStringExtra("restarant02");
            String restarant03 = data_recevie.getStringExtra("restarant03");
            String restarant04 = data_recevie.getStringExtra("restarant04");



            Intent interest_res = new Intent(Interestmain.this, Interest_restarant.class);
            interest_res.putExtra("latitude", latitude);
            interest_res.putExtra("longitude", longitude);
            interest_res.putExtra("restarant01", restarant01);
            interest_res.putExtra("restarant02", restarant02);
            interest_res.putExtra("restarant03", restarant03);
            interest_res.putExtra("restarant04", restarant04);

            startActivity(interest_res); // 유저의 음식점 취향정보를 넘기면서 Interest_restarant로 이동
        }
    }
}