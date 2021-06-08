package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Interest_detail extends AppCompatActivity {
    private static final String TAG = "Interest_detail";
    ImageView ivImage; // 이미지를 보여주기 위해 이미지뷰 선언
    TextView interestplacedetail_text;  //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_detail);

        interestplacedetail_text = findViewById(R.id.interestplacedetail_text);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        db.collection("Place")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Intent data_receive = getIntent();
                        String position = data_receive.getStringExtra("position");

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                if (document.getString("position").equals("" + position + "")) {
                                    interestplacedetail_text.append("이름:  "+document.getString("name")+"\n\n");
                                    interestplacedetail_text.append("주소:  "+document.getString("address")+"\n\n");
                                    interestplacedetail_text.append("전화번호:  "+document.getString("phone")+"\n\n");
                                    interestplacedetail_text.append("소개:  "+document.getString("ex")+"\n")
                                    ;


                                    ivImage=findViewById(R.id.ivImage5);
                                    String imageUrl =document.getString("image");
                                    Glide.with(Interest_detail.this).load(imageUrl).into(ivImage);
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

}
