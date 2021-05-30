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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class InterestCheck extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final String TAG = "naver_login";
    private FirebaseAuth mAuth;

    EditText interest_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_check);

       Intent data_recevie = getIntent();

        String name = data_recevie.getStringExtra("name");
        String email = data_recevie.getStringExtra("email");
        String gender = data_recevie.getStringExtra("gender");
        String birthyear = data_recevie.getStringExtra("birthyear");
        String mobile = data_recevie.getStringExtra("mobile");

        interest_editText = findViewById(R.id.interest_editText);

        interest_editText.setText(name + "\n" + email + "\n" + gender + "\n" + birthyear + "\n" + mobile);

        profileUpdate();
    }

    // DB에 user 값 저장

    private void profileUpdate() {
        Intent data_recevie = getIntent();

        String name = data_recevie.getStringExtra("name");
        String email = data_recevie.getStringExtra("email");
        String gender = data_recevie.getStringExtra("gender");
        String birthyear = data_recevie.getStringExtra("birthyear");
        String mobile = data_recevie.getStringExtra("mobile");



        if (name.length() >1 ) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            naver_login naver_login= new naver_login(name,gender,birthyear,mobile);

            if (user != null) {

                db.collection("users").document(email).set(naver_login)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {

                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("회원정보 등록을 성공하였습니다.");
                                Intent intent = new Intent(InterestCheck.this, MainActivity.class);
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



