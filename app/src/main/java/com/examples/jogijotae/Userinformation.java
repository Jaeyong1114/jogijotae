package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Userinformation extends AppCompatActivity {
    //다시수정한다 씨팔
    private static final String TAG = "Memberinfo";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinformation);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = (v) -> {
        switch (v.getId()) {
            case R.id.checkButton:
                profileUpdate();
                break;
        }
    };

    private void profileUpdate() {
        String birthyear = ((EditText) findViewById(R.id.birth_editText)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone_editText)).getText().toString();
        String gender= ((EditText) findViewById(R.id.gender_editText)).getText().toString();
        String name = ((EditText) findViewById(R.id.name_editText)).getText().toString();

        if (birthyear.length() >3 && birthyear.length() <5 && phone.length() > 12 ) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Memberinfo memberInfo = new Memberinfo(birthyear,phone,gender,name);

            if (user != null) {

                db.collection("users").document(user.getEmail()).set(memberInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {

                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("회원정보 등록을 성공하였습니다.");
                                Intent intent = new Intent(Userinformation.this, User_interest.class);
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

            } else {
                startToast("올바른 회원정보를 입력해 주세요.");
            }
        }

        private void startToast(String msg){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }




