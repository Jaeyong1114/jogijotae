package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;


import android.widget.ListView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

import java.util.List;

public class Restaurantmain extends AppCompatActivity implements AdapterView.OnItemClickListener {
    int i = 0; // 포지션에 값을주기위해사용

    private ListView list;

    String[] newposition = new String[50];  //새로운 포지션을 받기위해  배열로 선언
    private static final String TAG = "Restaurantmain"; // 로그찍기위한 태그

    private List<String> data = new ArrayList<>(); //  스트링배열 데이터 만들어서 arrayList 에서 받는값들  데이터에 넣기위해 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantmain);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data); //리스트뷰 사용위한 ArrayAdapter
        list = findViewById(R.id.listview);
        list.setAdapter(adapter);
list.setOnItemClickListener(this);


        FirebaseFirestore db = FirebaseFirestore.getInstance(); //파이어베이스 접근



        db.collection("Place")   //데이터베이스에 Place 라는곳에 접근하여  아래조건수행
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());


                                if (document.getString("division").equals("R")) {  // division 이 R ( 관광지를 제외한 음식점)들의 데이터베이스를 불러옴
                                    data.add(document.getString("name"));         // 리스트뷰의 아이템에  불러온 음식점 데이터베이스들의 이름을 넣음
                                    newposition[i++]=document.getString("position");        // 불러온 데이터베이스의 포지션값을 새로 저장
                                    adapter.notifyDataSetChanged();
                                }
                            }


                        } else {
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }


                    }
                });


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {     // 리스트뷰의 해당아이템을 클릭했을시

        /*     위에서 포지션받은것을 다음 동작할 액티비티로 넘겨주면서 액티비티 전환 */
        Intent intent01 = new Intent(Restaurantmain.this,Place_detail.class);
        intent01.putExtra("position",newposition[position]);
        startActivity(intent01);
    }
}
