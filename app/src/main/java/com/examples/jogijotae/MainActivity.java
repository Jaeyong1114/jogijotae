package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    Button place_btn, res_btn, category_btn , interest_btn;
    String place01 = "x", place02 = "x", place03 = "x", place04 = "x", place05 = "x", place06 = "x";
    String restarant01 = "x", restarant02 = "x", restarant03 = "x", restarant04 = "x";

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        place_btn=findViewById(R.id.place_btn);
        res_btn=findViewById(R.id.res_btn);
        category_btn=findViewById(R.id.category_btn);
        interest_btn=findViewById(R.id.interest_btn);


        place_btn.setOnClickListener(this);
        res_btn.setOnClickListener(this);
        category_btn.setOnClickListener(this);
        interest_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.place_btn) {
            Intent intent01 = new Intent(this, Placemain.class);
            Intent intent02 = getIntent();
            double latitude = intent02.getDoubleExtra("latitude", 0);
            double longitude = intent02.getDoubleExtra("longitude", 0);
            intent01.putExtra("latitude", latitude);
            intent01.putExtra("longitude", longitude);
            startActivity(intent01);
        }
        if(v.getId()==R.id.res_btn) {
                Intent intent01 = new Intent(this, Restaurantmain.class);
            Intent intent02 = getIntent();
            double latitude = intent02.getDoubleExtra("latitude", 0);
            double longitude = intent02.getDoubleExtra("longitude", 0);
            intent01.putExtra("latitude", latitude);
            intent01.putExtra("longitude", longitude);
                startActivity(intent01);
            }
        if(v.getId()==R.id.category_btn) {
            Intent intent01 = new Intent(this, Categorymain.class);
            Intent intent02 = getIntent();
            double latitude = intent02.getDoubleExtra("latitude", 0);
            double longitude = intent02.getDoubleExtra("longitude", 0);
            intent01.putExtra("latitude", latitude);
            intent01.putExtra("longitude", longitude);
            startActivity(intent01);

        }
        if(v.getId()==R.id.interest_btn){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            Intent data_recevie = getIntent();
            Intent intent02 = getIntent();
            double latitude = intent02.getDoubleExtra("latitude", 0);
            double longitude = intent02.getDoubleExtra("longitude", 0);

            String email = data_recevie.getStringExtra("email");

            db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            if ((document.getString("place01").equals("가족여행")) && (document.getString("email").equals(""+email+""))){
                                place01 = "가족여행";
                            }
                            if ((document.getString("place02").equals("아이와여행")) && (document.getString("email").equals(""+email+""))){
                                place02 = "아이와여행";
                            }
                            if ((document.getString("place03").equals("친구와여행")) && (document.getString("email").equals(""+email+""))){
                                place03 = "친구와여행";
                            }
                            if ((document.getString("place04").equals("혼자여행")) && (document.getString("email").equals(""+email+""))){
                                place04 = "혼자여행";
                            }
                            if ((document.getString("place05").equals("걷기좋은곳")) && (document.getString("email").equals(""+email+""))){
                                place05 = "걷기좋은곳";
                            }
                            if ((document.getString("place06").equals("인스타용")) && (document.getString("email").equals(""+email+""))){
                                place06 = "인스타용";
                            }
                            if ((document.getString("restarant01").equals("한식")) && (document.getString("email").equals(""+email+""))){
                                restarant01 = "한식";
                            }
                            if ((document.getString("restarant02").equals("중식")) && (document.getString("email").equals(""+email+""))){
                                restarant02 = "중식";
                            }
                            if ((document.getString("restarant03").equals("일식")) && (document.getString("email").equals(""+email+""))){
                                restarant03 = "일식";
                            }
                            if ((document.getString("restarant04").equals("양식")) && (document.getString("email").equals(""+email+""))){
                                restarant04 = "양식";
                            }
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }

                    Intent intent01 = new Intent(MainActivity.this, Interestmain.class);
                    intent01.putExtra("latitude", latitude);
                    intent01.putExtra("longitude", longitude);
                    intent01.putExtra("place01", place01);
                    intent01.putExtra("place02", place02);
                    intent01.putExtra("place03", place03);
                    intent01.putExtra("place04", place04);
                    intent01.putExtra("place05", place05);
                    intent01.putExtra("place06", place06);
                    intent01.putExtra("restarant01", restarant01);
                    intent01.putExtra("restarant02", restarant02);
                    intent01.putExtra("restarant03", restarant03);
                    intent01.putExtra("restarant04", restarant04);
                    intent01.putExtra("email", email);
                    startActivity(intent01);
                }

            });

        }

    }
}