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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Place_detail extends AppCompatActivity {
    private static final String TAG = "Place_detail";
    EditText place_detail;
    ImageView ivImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        place_detail=findViewById(R.id.placedetail_text);








        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        db.collection("Place")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Intent data_receive = getIntent();
                        String position = data_receive.getStringExtra("position");
                        Log.d(TAG,"이거는"+position);


                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());


                                if (document.getString("position").equals(""+position+"")) {

                                    place_detail.append("이름:  "+document.getString("name")+"\n\n");
                                    place_detail.append("주소:  "+document.getString("address")+"\n\n");
                                    place_detail.append("전화번호:  "+document.getString("phone")+"\n\n");
                                    place_detail.append("소개:  "+document.getString("ex")+"\n")
                                    ;

                                    ivImage=findViewById(R.id.iv_Image6);
                                    String imageUrl =document.getString("image");
                                    Glide.with(Place_detail.this).load(imageUrl).into(ivImage);
                                }
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }


                    }
                });


    }





}

