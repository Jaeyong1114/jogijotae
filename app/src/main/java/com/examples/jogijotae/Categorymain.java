package com.examples.jogijotae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Categorymain extends AppCompatActivity implements View.OnClickListener {

    TextView resinterest_text1, resinterest_text2, resinterest_text3, resinterest_text4;
    TextView placeinterest_text1, placeinterest_text2, placeinterest_text3, placeinterest_text4;
    TextView placeinterest2_text1,placeinterest2_text2, gps_category_text, gps_category_text2;
private static final String TAG = "Categorymain"; //로그 찍기위한 태그
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorymain);

        resinterest_text1=findViewById(R.id.resinterest_text1);
        resinterest_text2=findViewById(R.id.resinterest_text2);
        resinterest_text3=findViewById(R.id.resinterest_text3);
        resinterest_text4=findViewById(R.id.resinterest_text4);
        placeinterest_text1=findViewById(R.id.placeinterest_text1);
        placeinterest_text2=findViewById(R.id.placeinterest_text2);
        placeinterest_text3=findViewById(R.id.placeinterest_text3);
        placeinterest_text4=findViewById(R.id.placeinterest_text4);
        placeinterest2_text1=findViewById(R.id.placeinterest2_text1);
        placeinterest2_text2=findViewById(R.id.placeinterest2_text2);
        gps_category_text=findViewById(R.id.gps_category_text);
        gps_category_text2=findViewById(R.id.gps_category_text2);

        resinterest_text1.setOnClickListener(this);
        resinterest_text2.setOnClickListener(this);
        resinterest_text3.setOnClickListener(this);
        resinterest_text4.setOnClickListener(this);

        placeinterest_text1.setOnClickListener(this);
        placeinterest_text2.setOnClickListener(this);
        placeinterest_text3.setOnClickListener(this);
        placeinterest_text4.setOnClickListener(this);

        placeinterest2_text1.setOnClickListener(this);
        placeinterest2_text2.setOnClickListener(this);

        gps_category_text.setOnClickListener(this);
        gps_category_text2.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.resinterest_text1) {  // 사용자가 한식 카테고리를 클릭했을시
String k = "한식";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);              // k에 해당 값을 넣고 인텐트로 보내서 사용자가 누른 버튼을 알려줌
            startActivity(intent01);
        }
        if(v.getId()==R.id.resinterest_text2) { // 사용자가 중식 카테고리를 클릭했을시
            String k = "중식";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);     // k에 해당 값을 넣고 인텐트로 보내서 사용자가 누른 버튼을 알려줌
            startActivity(intent01);
        }
        if(v.getId()==R.id.resinterest_text3) { // 사용자가 일식 카테고리를 클릭했을시
            String k = "일식";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);       // k에 해당 값을 넣고 인텐트로 보내서 사용자가 누른 버튼을 알려줌
            startActivity(intent01);
        }
        if(v.getId()==R.id.resinterest_text4) {// 사용자가 양식 카테고리를 클릭했을시
            String k = "양식";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);      // k에 해당 값을 넣고 인텐트로 보내서 사용자가 누른 버튼을 알려줌
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest_text1) {// 사용자가 가족여행 카테고리를 클릭했을시
            String k = "가족여행";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);        // k에 해당 값을 넣고 인텐트로 보내서 사용자가 누른 버튼을 알려줌
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest_text2) {// 사용자가 아이와여행 카테고리를 클릭했을시
            String k = "아이와여행";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);        // k에 해당 값을 넣고 인텐트로 보내서 사용자가 누른 버튼을 알려줌
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest_text3) {// 사용자가 친구와여행 카테고리를 클릭했을시
            String k = "친구와여행";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);        // k에 해당 값을 넣고 인텐트로 보내서 사용자가 누른 버튼을 알려줌
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest_text4) {// 사용자가 혼자여행 카테고리를 클릭했을시
            String k = "혼자여행";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);        // k에 해당 값을 넣고 인텐트로 보내서 사용자가 누른 버튼을 알려줌
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest2_text1) {// 사용자가 걷기좋은곳 카테고리를 클릭했을시
            String k = "걷기좋은곳";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);        // k에 해당 값을 넣고 인텐트로 보내서 사용자가 누른 버튼을 알려줌
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest2_text2) {// 사용자가 인스타용 카테고리를 클릭했을시
            String k = "인스타용";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);       // k에 해당 값을 넣고 인텐트로 보내서 사용자가 누른 버튼을 알려줌
            startActivity(intent01);
        }

        if(v.getId()==R.id.gps_category_text) { // 사용자가 가까운 맛집  카테고리를 클릭했을시

            Intent intent02 = getIntent();
            double latitude = intent02.getDoubleExtra("latitude", 0);    // 사용자의 위도를 인텐트로 받아옴
            double longitude = intent02.getDoubleExtra("longitude", 0);  // 사용자의 경도를 인텐트로 받아옴
            Intent intent01 = new Intent(Categorymain.this, Category_Gps_Res.class);
            intent01.putExtra("latitude", latitude);       //인텐트로 받은 위도를 Category_Gps_Res 로 보내줌
            intent01.putExtra("longitude", longitude);     //인텐트로 받은 경도를 Category_Gps_Res 로 보내줌

            Log.d(TAG,"내위치는"+latitude+","+longitude); //사용자 위치 잘받았는지 로그 확인

            startActivity(intent01);

        }
            if (v.getId() == R.id.gps_category_text2) {// 사용자가 가까운 관광지 카테고리를 클릭했을시
                Intent intent02 = getIntent();
                double latitude = intent02.getDoubleExtra("latitude", 0);     // 사용자의 위도를 인텐트로 받아옴
                double longitude = intent02.getDoubleExtra("longitude", 0);   // 사용자의 경도를 인텐트로 받아옴
            Intent intent01 = new Intent(Categorymain.this, Category_Gps_Place.class);
                intent01.putExtra("latitude", latitude);        //인텐트로 받은 위도를 Category_Gps_Place 로 보내줌
                intent01.putExtra("longitude", longitude);      //인텐트로 받은 경도를 Category_Gps_Place 로 보내줌

                Log.d(TAG,"내위치는"+latitude+","+longitude); //사용자 위치 잘받았는지 로그 확인
            startActivity(intent01);

            }

        }
    }
