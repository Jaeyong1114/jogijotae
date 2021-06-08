package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class User_interest extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "User_interest";

    CheckBox UI_check_place01, UI_check_place02, UI_check_place03, UI_check_place04, UI_check_place05, UI_check_place06;
    CheckBox UI_check_restarant01, UI_check_restarant02, UI_check_restarant03, UI_check_restarant04;
    Button UI_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_interest);

        UI_check_place01 = findViewById(R.id.UI_check_place01);
        UI_check_place02 = findViewById(R.id.UI_check_place02);
        UI_check_place03 = findViewById(R.id.UI_check_place03);
        UI_check_place04 = findViewById(R.id.UI_check_place04);
        UI_check_place05 = findViewById(R.id.UI_check_place05);
        UI_check_place06 = findViewById(R.id.UI_check_place06);

        UI_check_restarant01 = findViewById(R.id.UI_check_restarant01);
        UI_check_restarant02 = findViewById(R.id.UI_check_restarant02);
        UI_check_restarant03 = findViewById(R.id.UI_check_restarant03);
        UI_check_restarant04 = findViewById(R.id.UI_check_restarant04);

        UI_btn = findViewById(R.id.UI_btn);
        UI_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent data_recevie = getIntent(); // // 디비에 저장해야할 유저의 기본정보 값을 받음 or 취향정보를 변경할 때 데이터베이스에 기본 유저정보값이 null 값으로 바뀌지 않게 하기 위해 기본 유저 정보 값을 받음

        String email = data_recevie.getStringExtra("email");
        String name = data_recevie.getStringExtra("name");
        String birthyear = data_recevie.getStringExtra("birthyear");
        String gender = data_recevie.getStringExtra("gender");
        String mobile = data_recevie.getStringExtra("mobile");
        String check = data_recevie.getStringExtra("check");

        String place01 = "", place02 = "", place03 = "", place04 = "", place05 = "", place06 = "",
                restarant01 = "", restarant02 = "", restarant03 = "", restarant04 = "";

        if (UI_check_place01.isChecked()) { // 체크박스가 체크되어 있으면 값을 넣는다
            place01 = UI_check_place01.getText().toString();
        }
        if (UI_check_place02.isChecked()) {
            place02 = UI_check_place02.getText().toString();
        }
        if (UI_check_place03.isChecked()) {
            place03 = UI_check_place03.getText().toString();
        }
        if (UI_check_place04.isChecked()) {
            place04 = UI_check_place04.getText().toString();
        }
        if (UI_check_place05.isChecked()) {
            place05 = UI_check_place05.getText().toString();
        }
        if (UI_check_place06.isChecked()) {
            place06 = UI_check_place06.getText().toString();
        }
        if (UI_check_restarant01.isChecked()) {
            restarant01 = UI_check_restarant01.getText().toString();
        }
        if (UI_check_restarant02.isChecked()) {
            restarant02 = UI_check_restarant02.getText().toString();
        }
        if (UI_check_restarant03.isChecked()) {
            restarant03 = UI_check_restarant03.getText().toString();
        }
        if (UI_check_restarant04.isChecked()) {
            restarant04 = UI_check_restarant04.getText().toString();
        }

        Interest Interest = new Interest(place01, place02, place03, place04, place05, place06, restarant01, restarant02, restarant03, restarant04, name, gender, birthyear, mobile,email);
        // Interest 메소드에 데이터베이스에 들어갈 유저의 값을 세팅한다
        db.collection("users").document(email).set(Interest)// 데이터베이스에 "useres"에 현재 받아온 email 정보에 Interest에 있는 유저의 기본 정보 값을 넣어서 등록한다.
                .addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void aVoid) {
                        //startToast("회원정보 등록을 완료하였습니다.");
                        Intent intent01 = getIntent();
                        double latitude = intent01.getDoubleExtra("latitude", 0);
                        double longitude = intent01.getDoubleExtra("longitude", 0);




                        if(!TextUtils.isEmpty(data_recevie.getStringExtra("check"))) { //인텐트로 받아온 체크에 값이 있을경우(회원정보를 수정 한 경우)

                            startToast("회원정보 수정을 완료하였습니다.");

                            Intent intent = new Intent(User_interest.this, MainActivity.class);
                            intent.putExtra("latitude", latitude);
                            intent.putExtra("longitude", longitude);
                            intent.putExtra("email", email);
                            intent.putExtra("name", name);
                            intent.putExtra("birthyear", birthyear);
                            intent.putExtra("gender", gender);
                            intent.putExtra("mobile", mobile);
                            startActivity(intent); // 유저의 기본정보 값과 함께 MainActivity로 이동
                            finish();
                        }
                        else {
                            startToast("회원정보 등록을 완료하였습니다."); //회원등록 (처음 등록하는경우)
                            Intent intent = new Intent(User_interest.this, MainActivity.class);

                            intent.putExtra("latitude", latitude);
                            intent.putExtra("longitude", longitude);
                            intent.putExtra("email", email);
                            intent.putExtra("name", name);
                            intent.putExtra("birthyear", birthyear);
                            intent.putExtra("gender", gender);
                            intent.putExtra("mobile", mobile);

                            startActivity(intent); // 유저의 기본정보 값과 함께 MainActivity로 이동
                            finish();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("회원정보 등록에 실패하였습니다.");
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}