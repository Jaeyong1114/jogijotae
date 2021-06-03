package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Categorymain extends AppCompatActivity implements View.OnClickListener {

    TextView resinterest_text1, resinterest_text2, resinterest_text3, resinterest_text4;
    TextView placeinterest_text1, placeinterest_text2, placeinterest_text3, placeinterest_text4;
    TextView placeinterest2_text1,placeinterest2_text2;
private static final String TAG = Categorymain.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorymain);

        resinterest_text1=findViewById(R.id.resinterest_text1);
        resinterest_text2=findViewById(R.id.resinterest_text2);
        resinterest_text3=findViewById(R.id.resinterest_text3);
        resinterest_text4=findViewById(R.id.resinterest_text4);
        placeinterest_text1=findViewById(R.id.placeinterest_text1);
        placeinterest_text2=findViewById(R.id.placeinterest_text2);
        placeinterest_text3=findViewById(R.id.placeinterest_text3);
        placeinterest_text4=findViewById(R.id.placeinterest_text4);
        placeinterest2_text1=findViewById(R.id.placeinterest2_text1);
        placeinterest2_text2=findViewById(R.id.placeinterest2_text2);

        resinterest_text1.setOnClickListener(this);
        resinterest_text2.setOnClickListener(this);
        resinterest_text3.setOnClickListener(this);
        resinterest_text4.setOnClickListener(this);

        placeinterest_text1.setOnClickListener(this);
        placeinterest_text2.setOnClickListener(this);
        placeinterest_text3.setOnClickListener(this);
        placeinterest_text4.setOnClickListener(this);

        placeinterest2_text1.setOnClickListener(this);
        placeinterest2_text2.setOnClickListener(this);







FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.resinterest_text1) {
String k = "한식";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);
            startActivity(intent01);
        }
        if(v.getId()==R.id.resinterest_text2) {
            String k = "중식";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);
            startActivity(intent01);
        }
        if(v.getId()==R.id.resinterest_text3) {
            String k = "일식";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);
            startActivity(intent01);
        }
        if(v.getId()==R.id.resinterest_text4) {
            String k = "양식";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest_text1) {
            String k = "가족여행";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest_text2) {
            String k = "아이와여행";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest_text3) {
            String k = "친구와여행";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest_text4) {
            String k = "혼자여행";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest2_text1) {
            String k = "걷기좋은곳";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);
            startActivity(intent01);
        }
        if(v.getId()==R.id.placeinterest2_text2) {
            String k = "인스타용";

            Intent intent01 = new Intent(Categorymain.this, Category_detail.class);
            intent01.putExtra("k",k);
            startActivity(intent01);
        }
    }
}