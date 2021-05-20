package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
//수정란

    Button btn01;
    TextView Registertxt;

    EditText edit01,edit02;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth =  FirebaseAuth.getInstance();
        //버튼 등록하기
        Registertxt = findViewById(R.id.registertxt);
        btn01 = findViewById(R.id.btn_login);
        edit01 = findViewById(R.id.et_id);
        edit02 = findViewById(R.id.et_pw);

        //가입 버튼이 눌리면
        Registertxt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //intent함수를 통해 register액티비티 함수를 호출한다.
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

            }
        });

        //로그인 버튼이 눌리면
        btn01.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = edit01.getText().toString().trim();
                String pwd = edit02.getText().toString().trim();
           
                firebaseAuth.signInWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);

                                }else{
                                    Toast.makeText(LoginActivity.this,"아이디 또는 패스워드를 확인해주세요",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}