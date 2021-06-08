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

        // 회원가입이 눌리면
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
                startActivity(intent02); // 일반유저가 회원가입 할 수 있는 RegisterActivity로 이동
            }
        });

        // 비밀번호 재설정이 눌리면
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
                startActivity(intent02); // 비밀번호를 재설정 할 수 있는 password_reset로 이동
            }
        });


        //로그인 버튼이 눌리면
        btn01.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = edit01.getText().toString().trim();
                String pwd = edit02.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (user == null) { // 데이터베이스에 유저에 정보가 등록이 되어있지 않다면
                                        Intent intent01 = getIntent();
                                        double latitude = intent01.getDoubleExtra("latitude", 0);
                                        double longitude = intent01.getDoubleExtra("longitude", 0);
                                        Intent intent = new Intent(LoginActivity.this, Userinformation.class);
                                        intent.putExtra("latitude", latitude);
                                        intent.putExtra("longitude", longitude);
                                        startActivity(intent);
                                        finish(); // 유저의 기본정보를 작성하는 Userinformation 으로 이동
                                    } else {
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        DocumentReference docRef = db.collection("users").document(user.getEmail()); // 유저의 이메일 값을 가져옴
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document != null) { // 유저의 정보가 데이터베이스에 존재한다면
                                                        if (document.exists()) {
                                                            Intent intent01 = getIntent();
                                                            double latitude = intent01.getDoubleExtra("latitude", 0);
                                                            double longitude = intent01.getDoubleExtra("longitude", 0);
                                                            Log.d(TAG, "DocumentSnapshot data:" + document.getData());
                                                            Intent intent = new Intent(LoginActivity.this,  MainActivity.class); // 데이터베이스에 정상적으로 유저의 정보가 있으므로 메인으로 넘어감
                                                            intent.putExtra("latitude", latitude);
                                                            intent.putExtra("longitude", longitude);
                                                            intent.putExtra("email", email); // 어느 유저가 로그인 했는지 확인하기 위해 email 값을 넘겨줌
                                                            startActivity(intent);
                                                            startToast(email + " 님 환영합니다.");
                                                            finish(); // MainActivity로 이동

                                                        } else {
                                                            Intent intent01 = getIntent();
                                                            double latitude = intent01.getDoubleExtra("latitude", 0);
                                                            double longitude = intent01.getDoubleExtra("longitude", 0);
                                                            Log.d(TAG, "No such document");
                                                            Intent intent = new Intent(LoginActivity.this, Userinformation.class); // 유저의 정보를 입력받기위해 Userinformation 으로 전환
                                                            intent.putExtra("latitude", latitude);
                                                            intent.putExtra("longitude", longitude);
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
                                    Toast.makeText(LoginActivity.this, "아이디 또는 패스워드를 확인해주세요", Toast.LENGTH_SHORT).show(); //아이디와 비밀번호를 맞게 입력하지않았을경우 토스트 메세지
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