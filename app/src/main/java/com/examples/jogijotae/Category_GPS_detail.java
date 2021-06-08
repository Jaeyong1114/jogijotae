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

public class Category_GPS_detail extends AppCompatActivity {
    EditText gps_detailtext;   // 상세설명을위한 에딧텍스트
    ImageView ivImage;  // 해당정보에 이미지를 넣기위한 이미지뷰
    private static final String TAG = "Category_GPS_detail";  // 로그찍기위한 태그
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail4);
        gps_detailtext=findViewById(R.id.gpsdetail_text);




        FirebaseFirestore db = FirebaseFirestore.getInstance();  //파이어베이스 접근

        //Place 데이터베이스 접근
        db.collection("Place")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Intent data_receive = getIntent();

                        String name = data_receive.getStringExtra("name");            // 인텐트로 비교할 값 받아옴



                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());


                                if (document.getString("name").equals(""+name+"")   ){           //데이터베이스안에 이름 값이 받아온  값과 같다면

                                                    /*   에딧텍스트에 이름 , 주소, 전화번호, 소개 넣음    */
                                    gps_detailtext.append("이름:  "+document.getString("name")+"\n\n");
                                    gps_detailtext.append("주소:  "+document.getString("address")+"\n\n");
                                    gps_detailtext.append("전화번호:  "+document.getString("phone")+"\n\n");
                                    gps_detailtext.append("소개:  "+document.getString("ex")+"\n")
                                    ;
                                    ivImage=findViewById(R.id.ivImage2);                //이미지뷰 연결하여 데이터베이스에서 이미지 토큰받아서 해당 이미지띄움
                                    String imageUrl =document.getString("image");
                                    Glide.with(Category_GPS_detail.this).load(imageUrl).into(ivImage);

                                }
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }


                    }
                });


    }





}

