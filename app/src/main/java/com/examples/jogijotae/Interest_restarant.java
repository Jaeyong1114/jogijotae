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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Interest_restarant extends AppCompatActivity implements AdapterView.OnItemClickListener {
    int i = 0;
    String restarant01 = "x", restarant02 = "x", restarant03 = "x", restarant04 = "x";
    private ListView list;
    private List<String> data =new ArrayList<>(); // 리스트뷰를 사용하기 위해 선언
    private static final String TAG = "Interest_restarant";

    String[] newposition = new String[50];  // "position"값을 넣을 배열 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_restarant);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        list =findViewById(R.id.listview);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        Intent data_recevie = getIntent();
        restarant01 = data_recevie.getStringExtra("restarant01");
        restarant02 = data_recevie.getStringExtra("restarant02");
        restarant03 = data_recevie.getStringExtra("restarant03");
        restarant04 = data_recevie.getStringExtra("restarant04");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // DB에서 "Place" 값을 받아옴
        db.collection("Place")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                if (document.getString("category").equals(""+restarant01+"") ||
                                        document.getString("category").equals(""+restarant02+"") ||
                                        document.getString("category").equals(""+restarant03+"") ||
                                        document.getString("category").equals(""+restarant04+"")){

                                    data.add (document.getString("name")); // 유저 취향 음식점에 이름을 리스트뷰에 추가 함
                                    newposition[i++]=document.getString("position"); // 리스트뷰를 클릭했을 때 어떤 음식점이 클릭되었는지 확인하기위해 position 값을 배열에 넣음
                                    adapter.notifyDataSetChanged();
                                }
                            }

                        } else {
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override // 리스트뷰 아이템이 클릭 되었을시
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent01 = new Intent(Interest_restarant.this, Interest_detail.class);
        intent01.putExtra("position",newposition[position]); // 클릭한 음식점 정보를 출력하기위해 클릭한 음식점 position값을 담아서 Interest_detail 실행
        startActivity(intent01);
    }
}