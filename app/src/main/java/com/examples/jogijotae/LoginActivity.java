package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";


    Button btn01;
    TextView Registertxt;
    TextView PWreset;
    EditText edit01, edit02;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        //버튼 등록하기
        PWreset = findViewById(R.id.pw_resettext);
        Registertxt = findViewById(R.id.registertxt);
        btn01 = findViewById(R.id.btn_login);
        edit01 = findViewById(R.id.et_id);
        edit02 = findViewById(R.id.et_pw);

        //가입 버튼이 눌리면
        Registertxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent01 = getIntent();
                double latitude = intent01.getDoubleExtra("latitude", 0);
                double longitude = intent01.getDoubleExtra("longitude", 0);
                //intent함수를 통해 register액티비티 함수를 호출한다.
                Intent intent02 = new Intent(LoginActivity.this, RegisterActivity.class);
                intent02.putExtra("latitude", latitude);
                intent02.putExtra("longitude", longitude);
                startActivity(intent02);
            }
        });


        PWreset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent01 = getIntent();
                double latitude = intent01.getDoubleExtra("latitude", 0);
                double longitude = intent01.getDoubleExtra("longitude", 0);
                //intent함수를 통해 password_reset액티비티 함수를 호출한다.
                Intent intent02=new Intent(LoginActivity.this, password_reset.class);
                intent02.putExtra("latitude", latitude);
                intent02.putExtra("longitude", longitude);
                startActivity(intent02);
            }
        });


        //로그인 버튼이 눌리면
        btn01.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = edit01.getText().toString().trim();
                String pwd = edit02.getText().toString().trim();
                Intent data_recevie = getIntent();

                String name = data_recevie.getStringExtra("name");
                String birthyear = data_recevie.getStringExtra("birthyear");
                String gender = data_recevie.getStringExtra("gender");
                String mobile = data_recevie.getStringExtra("mobile");

                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (user == null) {
                                        Log.d(TAG,"유저가 널이다");
                                        Intent intent01 = getIntent();
                                        double latitude = intent01.getDoubleExtra("latitude", 0);
                                        double longitude = intent01.getDoubleExtra("longitude", 0);
                                        Intent intent = new Intent(LoginActivity.this, Userinformation.class);
                                        intent.putExtra("latitude", latitude);
                                        intent.putExtra("longitude", longitude);
                                        intent.putExtra("name",name);
                                        intent.putExtra("birthyear",birthyear);
                                        intent.putExtra("gender",gender);
                                        intent.putExtra("mobile",mobile);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        DocumentReference docRef = db.collection("users").document(user.getEmail());
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document != null) {
                                                        if (document.exists()) {
                                                            Intent intent01 = getIntent();
                                                            double latitude = intent01.getDoubleExtra("latitude", 0);
                                                            double longitude = intent01.getDoubleExtra("longitude", 0);
                                                            Log.d(TAG, "DocumentSnapshot data:" + document.getData());
                                                            Intent intent = new Intent(LoginActivity.this,  MainActivity.class);
                                                            intent.putExtra("latitude", latitude);
                                                            intent.putExtra("longitude", longitude);
                                                            intent01.putExtra("name",name);
                                                            intent01.putExtra("birthyear",birthyear);
                                                            intent01.putExtra("gender",gender);
                                                            intent01.putExtra("mobile",mobile);
                                                            intent.putExtra("email", email);
                                                            startActivity(intent);
                                                            startToast(email + " 님 환영합니다.");
                                                            finish();
                                                        } else {
                                                            Log.d(TAG,"유저가 널이다2222");
                                                            Intent intent01 = getIntent();
                                                            double latitude = intent01.getDoubleExtra("latitude", 0);
                                                            double longitude = intent01.getDoubleExtra("longitude", 0);
                                                            Log.d(TAG, "No such document");
                                                            Intent intent = new Intent(LoginActivity.this, Userinformation.class);
                                                            intent.putExtra("latitude", latitude);
                                                            intent.putExtra("longitude", longitude);
                                                            intent01.putExtra("name",name);
                                                            intent01.putExtra("birthyear",birthyear);
                                                            intent01.putExtra("gender",gender);
                                                            intent01.putExtra("mobile",mobile);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                } else {
                                                    Log.d(TAG, "get failed with", task.getException());
                                                }
                                            }
                                        });
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "아이디 또는 패스워드를 확인해주세요", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
            }
        });
    }
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}