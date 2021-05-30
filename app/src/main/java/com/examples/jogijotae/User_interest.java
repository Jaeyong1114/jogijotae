package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    CheckBox UI_check_place01, UI_check_place02, UI_check_place03, UI_check_place04;
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

        Intent data_recevie = getIntent();

        String email = data_recevie.getStringExtra("email");
        String name = data_recevie.getStringExtra("name");
        String birthyear = data_recevie.getStringExtra("birthyear");
        String gender = data_recevie.getStringExtra("gender");
        String mobile = data_recevie.getStringExtra("mobile");

        String place01, place02, place03, place04, restarant01, restarant02, restarant03, restarant04 = "";

            place01 = UI_check_place01.getText().toString();


            place02 = UI_check_place02.getText().toString();


            place03 = UI_check_place03.getText().toString();


            place04 = UI_check_place04.getText().toString();


            restarant01 = UI_check_restarant01.getText().toString();


            restarant02 = UI_check_restarant02.getText().toString();


            restarant03 = UI_check_restarant03.getText().toString();


            restarant04 = UI_check_restarant04.getText().toString();


        Interest Interest = new Interest(place01, place02, place03, place04, restarant01, restarant02, restarant03, restarant04, name, gender, birthyear, mobile);
        db.collection("users").document(email).set(Interest)
                .addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("회원정보 등록을 성공하였습니다.");
                        Intent intent = new Intent(User_interest.this, User_interest.class);
                        startActivity(intent);
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