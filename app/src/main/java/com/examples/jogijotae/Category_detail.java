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

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

import java.util.List;

public class Category_detail extends AppCompatActivity implements AdapterView.OnItemClickListener{
int i = 0; // 포지션에 값을주기위해사용

    private ListView list; //리스트뷰 사용
    private   List<String> data =new ArrayList<>(); //  스트링배열 데이터 만들어서 arrayList 에서 받는값들  데이터에 넣기위해 선언
    private static final String TAG = "Category_detail"; // 로그찍기위한 태그

String[] newposition = new String[50]; //새로운포지션을 배열로 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data); //리스트뷰 사용위한 ArrayAdapter
        list =findViewById(R.id.listview);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);


        FirebaseFirestore db = FirebaseFirestore.getInstance();                //파이어베이스 접근
       Intent receive_data= getIntent();
       String k= receive_data.getStringExtra("k");                     // Category_main 에서 인텐트로 넘긴 카테고리 정보 받아옴

        db.collection("Place")                    //데이터베이스에 Place 라는곳을 들어가서  아래조건수행
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());



                                if ((document.getString("category").equals(""+k+""))  ||(document.getString("category2").equals(""+k+""))
                                        ||document.getString("category3").equals(""+k+"")){
                                    data.add (document.getString("name"));          //  Category_main 에서 인텐트로 받아온값으로 맞는 카테고리에 데이터베이스 리스트뷰에 넣음

                                    newposition[i++]=document.getString("position");        // 각 리스트뷰에 아이템들을 사용하기위해 해당 포지션을 배열에 넣어줌
                                    Log.d(TAG,"테스트"+newposition);                      // 포지션 잘불러왔는지 테스트 로그


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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  //리스트뷰에  아이템을 클릭했을시

 /*     위에서 포지션받은것을 다음 동작할 액티비티로 넘겨주면서 액티비티 전환 */

        Intent intent01 = new Intent(Category_detail.this, Category_detail_more.class);
        intent01.putExtra("position",newposition[position]);
        startActivity(intent01);

    }
}