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

public class Category_test extends AppCompatActivity {

    private static final String TAG ="Category_test";

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
            person.add(new Person("화덕 고깃간", distance(latitude,longitude,37.51503457185153,127.0809924799709),37.51503457185153,127.0809924799709));
            person.add(new Person("오쓰세이로무시", distance(latitude,longitude,37.510456219235586,127.10848473837537),37.510456219235586,127.10848473837537));
            person.add(new Person("갓잇", distance(latitude,longitude,37.50963693550107,127.11129700952512),37.50963693550107,127.11129700952512));
            person.add(new Person("봉피양", distance(latitude,longitude,37.510235972088594,127.12599594662692),37.510235972088594,127.12599594662692));
            person.add(new Person("시그니엘서울호텔 더라운지", distance(latitude,longitude,37.51273425024574,127.10253010444927),37.51273425024574,127.10253010444927));
            person.add(new Person("청와옥 본점", distance(latitude,longitude,37.51560569957713,127.11742589811357),37.51560569957713,127.11742589811357));
            person.add(new Person("비채나", distance(latitude,longitude,37.512758228371,127.10262156233735),37.512758228371,127.10262156233735));
            person.add(new Person("경복궁 방이점", distance(latitude,longitude,37.5159805692721,127.10909291344765),37.5159805692721,127.10909291344765));
            person.add(new Person("럭키살롱", distance(latitude,longitude,37.47462786620894,127.14166590468696),37.47462786620894,127.14166590468696));
            person.add(new Person("놀부유황오리진흙구이 잠실점", distance(latitude,longitude,37.51155298284621,127.10987660593777),37.51155298284621,127.10987660593777));
            person.add(new Person("이성원쉐프의 청년감자탕 본점", distance(latitude,longitude,37.51050577004984,127.11317854228334),37.51050577004984,127.11317854228334));
            person.add(new Person("바이킹스워프 롯데월드몰점", distance(latitude,longitude,37.513309772013585,127.10391692693996),37.513309772013585,127.10391692693996));
            person.add(new Person("몬스타 쉐프의 무한곱창", distance(latitude,longitude,37.51377176334603,127.1088485422833),37.51377176334603,127.1088485422833));
            person.add(new Person("STAY", distance(latitude,longitude,37.51286760576775,127.10246541447317),37.51286760576775,127.10246541447317));
            person.add(new Person("제이스 아메리칸", distance(latitude,longitude,37.51773541374575,127.10362864228348),37.51773541374575,127.10362864228348));
            person.add(new Person("르빵 송파점", distance(latitude,longitude,37.507363842130616,127.10832991529841),37.507363842130616,127.10832991529841));
            person.add(new Person("온더보더 롯데월드몰점", distance(latitude,longitude,37.51384484480129,127.10378018461162),37.51384484480129,127.10378018461162));
            person.add(new Person("치즈룸 X 테이스팅룸 롯데월드몰 점", distance(latitude,longitude,37.514207462451765,127.104525157627),37.514207462451765,127.104525157627));
            person.add(new Person("다운타우너", distance(latitude,longitude,37.511318795545655,127.11129315445727),37.511318795545655,127.11129315445727));
            person.add(new Person("벽제갈비", distance(latitude,longitude,37.51672394839505,127.12914586163197),37.51672394839505,127.12914586163197));
            person.add(new Person("별미곱창", distance(latitude,longitude,37.51463543352229,127.10866369261997),37.51463543352229,127.10866369261997));
            person.add(new Person("젠", distance(latitude,longitude,37.51154948350426,127.11114628892848),37.51154948350426,127.1111462889284));
            person.add(new Person("훅트포케 롯데월드몰점", distance(latitude,longitude,37.51277331686877,127.10254291416662),37.51277331686877,127.10254291416662));
            person.add(new Person("벼락가우리", distance(latitude,longitude,37.50855,127.0809419),37.50855,127.0809419));
            person.add(new Person("취영루", distance(latitude,longitude,37.4925815,127.1227534),37.4925815,127.1227534));
            person.add(new Person("차이나플레인", distance(latitude,longitude,37.5048039,127.0508334),37.5048039,127.0508334));
            person.add(new Person("일일향 잠실점", distance(latitude,longitude,37.5110741,127.1095527),37.5110741,127.1095527));


            Collections.sort(person,sortByTotalCall);


            // Adapter생성
            recyclerViewAdapter = new RecyclerViewAdapter(this, person);
            recyclerView.setAdapter(recyclerViewAdapter);

        }
    }