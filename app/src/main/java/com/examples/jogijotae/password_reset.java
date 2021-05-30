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

public class password_reset extends AppCompatActivity {

private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_emailsend).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = (v) ->{
        switch(v.getId()){
            case R.id.btn_emailsend:
                  send();
            break;
        }
    };
    private void send(){
        String email = ((EditText)findViewById(R.id.et_id2)).getText().toString();


        if(email.length() > 0){

            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        startToast("이메일을 보냈습니다");

                    }

                    }

            });
        } else {
            startToast("이메일을 입력해 주세요");
        }
    }

    private void startToast(String msg) {Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();}
    }

