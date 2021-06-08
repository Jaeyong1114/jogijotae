package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Category_detail_more extends AppCompatActivity {
    ImageView ivImage;   // 해당정보에 이미지를 넣기위한 이미지뷰
    EditText category_detailtext;      // 상세설명을위한 에딧텍스트
    private static final String TAG = "Category_detail_more"; // 로그찍기위한 태그
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail2);
        category_detailtext=findViewById(R.id.interestplacedetail_text);

        FirebaseFirestore db = FirebaseFirestore.getInstance();  //파이어베이스 접근


        //Place 데이터베이스 접근
        db.collection("Place")          //데이터베이스에 Place 라는곳에 접근   아래조건수행
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Intent data_receive = getIntent();

                        String position = data_receive.getStringExtra("position"); // position 이라는 변수에 인텐트로 받은 포지션을 받아서 각 위치 확인
                        Log.d(TAG,"이거는"+position); // 포지션 잘받아오는지확인하기위한 로그


                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());


                                if (document.getString("position").equals(""+position+"")   ){  //데이터베이스안에 포지션의값이 받아온 값과 같다면

                                                                                     //에딧텍스트에 이름 , 주소, 전화번호, 소개 넣음
                                    category_detailtext.append("이름:  "+document.getString("name")+"\n\n");
                                    category_detailtext.append("주소:  "+document.getString("address")+"\n\n");
                                    category_detailtext.append("전화번호:  "+document.getString("phone")+"\n\n");
                                    category_detailtext.append("소개:  "+document.getString("ex")+"\n")
                                    ;
                                    ivImage=findViewById(R.id.ivImage3);   //이미지뷰 연결하여 데이터베이스에서 이미지 토큰받아서 해당  이미지띄움
                                    String imageUrl =document.getString("image");
                                    Glide.with(Category_detail_more.this).load(imageUrl).into(ivImage);

                                }
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }


                    }
                });


    }





}

