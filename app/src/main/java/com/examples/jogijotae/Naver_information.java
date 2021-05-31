package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Naver_information extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final String TAG = "naver_login";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naver_information);

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


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        naver_login naver_login = new naver_login(name, gender, birthyear, mobile);

        if (user != null) {

            db.collection("users").document(email).set(naver_login)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void aVoid) {
                            //startToast("회원정보 등록을 성공하였습니다.");
                            Intent intent = new Intent(Naver_information.this, User_interest.class);
                            intent.putExtra("email", email);
                            intent.putExtra("birthyear", birthyear);
                            intent.putExtra("gender", gender);
                            intent.putExtra("name", name);
                            intent.putExtra("mobile", mobile);
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

    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}



