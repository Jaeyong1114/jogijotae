package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nhn.android.naverlogin.OAuthLogin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    Button place_btn, res_btn, category_btn , interest_btn;
    Button main_btn_logout, main_btn_infoChange;
    String place01 = "x", place02 = "x", place03 = "x", place04 = "x", place05 = "x", place06 = "x";
    String restarant01 = "x", restarant02 = "x", restarant03 = "x", restarant04 = "x";

    OAuthLogin mOAuthLoginModule;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        place_btn=findViewById(R.id.place_btn);
        res_btn=findViewById(R.id.res_btn);
        category_btn=findViewById(R.id.category_btn);
        interest_btn=findViewById(R.id.interest_btn);
        main_btn_logout = findViewById(R.id.main_btn_logout);
        main_btn_infoChange = findViewById(R.id.main_btn_infoChange);

        place_btn.setOnClickListener(this);
        res_btn.setOnClickListener(this);
        category_btn.setOnClickListener(this);
        interest_btn.setOnClickListener(this);
        main_btn_logout.setOnClickListener(this);
        main_btn_infoChange.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent data_recevie = getIntent();
        String email = data_recevie.getStringExtra("email");
        if (email.contains("naver")) {
            Intent intent = new Intent(MainActivity.this, FirstMain.class);
            startToast("네이버 로그아웃 되었습니다.");
            mOAuthLoginModule.getInstance().logout(mContext);
            startActivity(intent);
            finish();
        }
        else {
            startToast("로그아웃 되었습니다.");
            finish();
        }
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
        if (v.getId() == R.id.main_btn_logout) {
            Intent data_recevie = getIntent();
            String email = data_recevie.getStringExtra("email");
            if (email.contains("naver")) {
                Intent intent = new Intent(MainActivity.this, FirstMain.class);
                startToast("네이버 로그아웃 되었습니다.");
                mOAuthLoginModule.getInstance().logout(mContext);
                startActivity(intent);
                finish();
            }
            else {
                startToast("로그아웃 되었습니다.");
                finish();
            }
        }
        if (v.getId() == R.id.main_btn_infoChange) {
            Intent data_recevie = getIntent();
            String email = data_recevie.getStringExtra("email");

            Intent intent = new Intent(MainActivity.this, User_interest.class);
            intent.putExtra("check", "체크");
            intent.putExtra("email", email);
            startActivity(intent);
        }

    }
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}