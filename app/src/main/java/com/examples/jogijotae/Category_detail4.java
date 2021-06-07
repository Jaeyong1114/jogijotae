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

public class Category_detail4 extends AppCompatActivity {
    EditText gps_detailtext;
    ImageView ivImage;
    private static final String TAG = "Category_detail4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail4);
        gps_detailtext=findViewById(R.id.gpsdetail_text);




        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        db.collection("Place")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Intent data_receive = getIntent();
                        //  int position = data_receive.getIntExtra("position",1);
                        String name = data_receive.getStringExtra("name");



                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());


                                if (document.getString("name").equals(""+name+"")   ){


                                    gps_detailtext.append("이름:  "+document.getString("name")+"\n\n");
                                    gps_detailtext.append("주소:  "+document.getString("address")+"\n\n");
                                    gps_detailtext.append("전화번호:  "+document.getString("phone")+"\n\n");
                                    gps_detailtext.append("소개:  "+document.getString("ex")+"\n")
                                    ;
                                    ivImage=findViewById(R.id.ivImage2);
                                    String imageUrl =document.getString("image");
                                    Glide.with(Category_detail4.this).load(imageUrl).into(ivImage);

                                }
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }


                    }
                });


    }





}

