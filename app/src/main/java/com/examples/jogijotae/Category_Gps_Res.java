package com.examples.jogijotae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Category_Gps_Res extends AppCompatActivity {

    private static final String TAG ="Category_Gps_Res";

        RecyclerView recyclerView;           //리사이클러뷰 사용
        LinearLayoutManager linearLayoutManager;

        Category_Adapter categoryAdapter;


    /* 장소의 거리들을 비교하여 정렬하기위해 사용 */
    private final static Comparator<Category_class> sortByTotalCall = new Comparator<Category_class>() {

        @Override
        public int compare(Category_class object1, Category_class object2){
            return Double.compare(object1.direction, object2.direction);
        }


    };



    /*첫번 째 지점 (위도,경도) 에서 두번째 지점(위도,경도) 까지의 거리계산 함수 생성 */

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





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_category_test);


            recyclerView = findViewById(R.id.recyclerView);
            linearLayoutManager = new LinearLayoutManager(this);

            //recyclerview 항목들 사이에 구분선 추가
            //수평, 수직의 스크롤 리스트 / getOrientation을 이용하여 스크롤 방향 설정
            recyclerView.addItemDecoration(
                    new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

            //지정된 레이아웃매니저를 RecyclerView에 Set
            recyclerView.setLayoutManager(linearLayoutManager);



            Intent intent = getIntent();               // 인텐트로 위도와 경도 받아옴
            double latitude = intent.getDoubleExtra("latitude", 0);
            double longitude = intent.getDoubleExtra("longitude", 0);




Log.d(TAG,"내위치는"+latitude+","+longitude); // 사용자의 위치 잘받아오는지 로그 확인
           /* Arraylist에 들어갈 음식점 데이터(이름,거리) 넣기 */
            List<Category_class> categoryclasses = new ArrayList<>();                     /* 이름, 거리계산결과, 위도,경도   */
            categoryclasses.add(new Category_class("화덕 고깃간", distance(latitude,longitude,37.51503457185153,127.0809924799709),37.51503457185153,127.0809924799709));
            categoryclasses.add(new Category_class("오쓰세이로무시", distance(latitude,longitude,37.510456219235586,127.10848473837537),37.510456219235586,127.10848473837537));
            categoryclasses.add(new Category_class("갓잇", distance(latitude,longitude,37.50963693550107,127.11129700952512),37.50963693550107,127.11129700952512));
            categoryclasses.add(new Category_class("봉피양", distance(latitude,longitude,37.510235972088594,127.12599594662692),37.510235972088594,127.12599594662692));
            categoryclasses.add(new Category_class("시그니엘서울호텔 더라운지", distance(latitude,longitude,37.51273425024574,127.10253010444927),37.51273425024574,127.10253010444927));
            categoryclasses.add(new Category_class("청와옥 본점", distance(latitude,longitude,37.51560569957713,127.11742589811357),37.51560569957713,127.11742589811357));
            categoryclasses.add(new Category_class("비채나", distance(latitude,longitude,37.512758228371,127.10262156233735),37.512758228371,127.10262156233735));
            categoryclasses.add(new Category_class("경복궁 방이점", distance(latitude,longitude,37.5159805692721,127.10909291344765),37.5159805692721,127.10909291344765));
            categoryclasses.add(new Category_class("럭키살롱", distance(latitude,longitude,37.47462786620894,127.14166590468696),37.47462786620894,127.14166590468696));
            categoryclasses.add(new Category_class("놀부유황오리진흙구이 잠실점", distance(latitude,longitude,37.51155298284621,127.10987660593777),37.51155298284621,127.10987660593777));
            categoryclasses.add(new Category_class("이성원쉐프의 청년감자탕 본점", distance(latitude,longitude,37.51050577004984,127.11317854228334),37.51050577004984,127.11317854228334));
            categoryclasses.add(new Category_class("바이킹스워프 롯데월드몰점", distance(latitude,longitude,37.513309772013585,127.10391692693996),37.513309772013585,127.10391692693996));
            categoryclasses.add(new Category_class("몬스타 쉐프의 무한곱창", distance(latitude,longitude,37.51377176334603,127.1088485422833),37.51377176334603,127.1088485422833));
            categoryclasses.add(new Category_class("STAY", distance(latitude,longitude,37.51286760576775,127.10246541447317),37.51286760576775,127.10246541447317));
            categoryclasses.add(new Category_class("제이스 아메리칸", distance(latitude,longitude,37.51773541374575,127.10362864228348),37.51773541374575,127.10362864228348));
            categoryclasses.add(new Category_class("르빵 송파점", distance(latitude,longitude,37.507363842130616,127.10832991529841),37.507363842130616,127.10832991529841));
            categoryclasses.add(new Category_class("온더보더 롯데월드몰점", distance(latitude,longitude,37.51384484480129,127.10378018461162),37.51384484480129,127.10378018461162));
            categoryclasses.add(new Category_class("치즈룸 X 테이스팅룸 롯데월드몰 점", distance(latitude,longitude,37.514207462451765,127.104525157627),37.514207462451765,127.104525157627));
            categoryclasses.add(new Category_class("다운타우너", distance(latitude,longitude,37.511318795545655,127.11129315445727),37.511318795545655,127.11129315445727));
            categoryclasses.add(new Category_class("벽제갈비", distance(latitude,longitude,37.51672394839505,127.12914586163197),37.51672394839505,127.12914586163197));
            categoryclasses.add(new Category_class("별미곱창", distance(latitude,longitude,37.51463543352229,127.10866369261997),37.51463543352229,127.10866369261997));
            categoryclasses.add(new Category_class("젠", distance(latitude,longitude,37.51154948350426,127.11114628892848),37.51154948350426,127.1111462889284));
            categoryclasses.add(new Category_class("훅트포케 롯데월드몰점", distance(latitude,longitude,37.51277331686877,127.10254291416662),37.51277331686877,127.10254291416662));
            categoryclasses.add(new Category_class("벼락가우리", distance(latitude,longitude,37.50855,127.0809419),37.50855,127.0809419));
            categoryclasses.add(new Category_class("취영루", distance(latitude,longitude,37.4925815,127.1227534),37.4925815,127.1227534));
            categoryclasses.add(new Category_class("차이나플레인", distance(latitude,longitude,37.5048039,127.0508334),37.5048039,127.0508334));
            categoryclasses.add(new Category_class("일일향 잠실점", distance(latitude,longitude,37.5110741,127.1095527),37.5110741,127.1095527));

        /*거리순 정렬 */
            Collections.sort(categoryclasses,sortByTotalCall);


            // Adapter생성
            categoryAdapter = new Category_Adapter(this, categoryclasses);
            recyclerView.setAdapter(categoryAdapter);

        }
    }
