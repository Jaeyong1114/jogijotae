package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* 비밀번호 재설정 */

public class password_reset extends AppCompatActivity {

private FirebaseAuth mAuth; //유저 정보  받기위해
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        mAuth = FirebaseAuth.getInstance();  //유저정보 연결

        findViewById(R.id.btn_emailsend).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = (v) ->{ //버튼 눌렀을시
        switch(v.getId()){
            case R.id.btn_emailsend:
                  send();
            break;
        }
    };
    private void send(){
        String email = ((EditText)findViewById(R.id.et_id2)).getText().toString();


        if(email.length() > 0){
//이메일이 정상적으로 입력되었을경우
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() { //패스워드 재설정이메일보냄
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        startToast("이메일을 보냈습니다"); //토스트메세지 출력

                    }

                    }

            });
        } else { //이메일 입력이 되지않았을경우
            startToast("이메일을 입력해 주세요"); //토스트메세지 출력
        }
    }

    private void startToast(String msg) {Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();}
    }

