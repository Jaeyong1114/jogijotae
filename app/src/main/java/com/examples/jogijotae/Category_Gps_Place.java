

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

public class Category_Gps_Place extends AppCompatActivity {

    private static final String TAG ="Category_Gps_Place"; // 로그찍기위한 태그

    RecyclerView recyclerView;            //리사이클러뷰 사용
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



        Intent intent = getIntent();                                           // 인텐트로 위도와 경도 받아옴
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);




        Log.d(TAG,"내위치는"+latitude+","+longitude);
        /* Arraylist에 들어갈 관광지 데이터(이름,거리) 넣기 */
        List<Category_class> categoryclasses = new ArrayList<>();                    /* 이름, 거리계산결과, 위도,경도   */
        categoryclasses.add(new Category_class("롯데월드", distance(latitude,longitude,37.511239,127.0958334),37.511239,127.0958334));
        categoryclasses.add(new Category_class("잠실 한강공원", distance(latitude,longitude,37.51126,127.0892405),37.51126,127.0892405));
        categoryclasses.add(new Category_class("석촌호수", distance(latitude,longitude,37.5080243,127.0918423),37.5080243,127.0918423));
        categoryclasses.add(new Category_class("제2 롯데타워", distance(latitude,longitude,37.5233607,127.0916439),37.5233607,127.0916439));
        categoryclasses.add(new Category_class("키자니아", distance(latitude,longitude,37.510873,127.0942724),37.510873,127.0942724));
        categoryclasses.add(new Category_class("롯데월드 아쿠아리움", distance(latitude,longitude,37.5108815,127.0942616),37.5108815,127.0942616));
        categoryclasses.add(new Category_class("삼전도비", distance(latitude,longitude,37.50923323071838,127.0991518687846),37.50923323071838,127.0991518687846));
        categoryclasses.add(new Category_class("서울올림픽기념관", distance(latitude,longitude,37.52031527829416,127.11557246926829),37.52031527829416,127.11557246926829));
        categoryclasses.add(new Category_class("아시아공원", distance(latitude,longitude,37.51015409975009,127.07676126714965),37.51015409975009,127.07676126714965));
        categoryclasses.add(new Category_class("올림픽공원", distance(latitude,longitude,37.52089250985871,127.12160148713528),37.52089250985871,127.12160148713528));
        categoryclasses.add(new Category_class("평화의광장", distance(latitude,longitude,37.51887978173245,127.11688305744877),37.51887978173245,127.11688305744877));
        categoryclasses.add(new Category_class("한양공원", distance(latitude,longitude,37.50409106940423,127.11552103919591),37.50409106940423,127.11552103919591));


        /*거리순 정렬 */
        Collections.sort(categoryclasses,sortByTotalCall);


        // Adapter생성
        categoryAdapter = new Category_Adapter(this, categoryclasses);
        recyclerView.setAdapter(categoryAdapter);

    }
}
