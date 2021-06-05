

package com.examples.jogijotae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Category_detail5 extends AppCompatActivity {

    private static final String TAG ="Category_detail5";

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    //RecyclerViewAdapter class
    RecyclerViewAdapter recyclerViewAdapter;



    private final static Comparator<Person> sortByTotalCall = new Comparator<Person>() {

        @Override
        public int compare(Person object1, Person object2){
            return Double.compare(object1.direction, object2.direction);
        }


    };




    private double distance(double lat1, double lon1, double lat2, double lon2 ) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +
                Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                        Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist *60 *1.1515;

        dist = dist * 1.609344;

        return (dist);
    }

    private double deg2rad(double deg) {
        return(deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad / Math.PI * 180.0) ;
    }

// 거리계산



    //change





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_test);

        //activity_main.xml의 recyclerview id
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);

        //recyclerview 항목들 사이에 구분선 추가
        //수평, 수직의 스크롤 리스트 / getOrientation을 이용하여 스크롤 방향 설정
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

        //지정된 레이아웃매니저를 RecyclerView에 Set
        recyclerView.setLayoutManager(linearLayoutManager);



        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);




        Log.d(TAG,"내위치는"+latitude+","+longitude);
        // ArrayList에 person 객체(이름과 번호) 넣기
        List<Person> person = new ArrayList<>();
        person.add(new Person("롯데월드", distance(latitude,longitude,37.511239,127.0958334),37.511239,127.0958334));
        person.add(new Person("잠실 한강공원", distance(latitude,longitude,37.51126,127.0892405),37.51126,127.0892405));
        person.add(new Person("석촌호수", distance(latitude,longitude,37.5080243,127.0918423),37.5080243,127.0918423));
        person.add(new Person("제2 롯데타워", distance(latitude,longitude,37.5233607,127.0916439),37.5233607,127.0916439));
        person.add(new Person("키자니아", distance(latitude,longitude,37.510873,127.0942724),37.510873,127.0942724));
        person.add(new Person("롯데월드 아쿠아리움", distance(latitude,longitude,37.5108815,127.0942616),37.5108815,127.0942616));
        person.add(new Person("삼전도비", distance(latitude,longitude,37.50923323071838,127.0991518687846),37.50923323071838,127.0991518687846));
        person.add(new Person("서울올림픽기념관점", distance(latitude,longitude,37.52031527829416,127.11557246926829),37.52031527829416,127.11557246926829));
        person.add(new Person("아시아공원", distance(latitude,longitude,37.51015409975009,127.07676126714965),37.51015409975009,127.07676126714965));
        person.add(new Person("올림픽공원", distance(latitude,longitude,37.52089250985871,127.12160148713528),37.52089250985871,127.12160148713528));
        person.add(new Person("평화의광장", distance(latitude,longitude,37.51887978173245,127.11688305744877),37.51887978173245,127.11688305744877));
        person.add(new Person("한양공원", distance(latitude,longitude,37.50409106940423,127.11552103919591),37.50409106940423,127.11552103919591));



        Collections.sort(person,sortByTotalCall);


        // Adapter생성
        recyclerViewAdapter = new RecyclerViewAdapter(this, person);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
