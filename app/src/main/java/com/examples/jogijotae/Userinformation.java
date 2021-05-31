package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private RadioGroup rg_gender;
    private RadioButton rb_man, rb_woman;
    private String str_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinformation);

        mAuth = FirebaseAuth.getInstance();
        rg_gender = findViewById(R.id.rg_gender); //라디오 버튼들을 담고있는 그룹
        rb_man = findViewById(R.id.rb_man); // 라디오 버튼
        rb_woman = findViewById(R.id.rb_woman); // 라디오 버튼

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);


        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i == R.id.rb_man) {
                    str_result = rb_man.getText().toString();
                } else if (i == R.id.rb_woman) {
                    str_result = rb_woman.getText().toString();
                }

            }
        });
    }


    View.OnClickListener onClickListener = (v) -> {
        switch (v.getId()) {
            case R.id.checkButton:
                profileUpdate();
                break;
        }
    };

    public void profileUpdate() {
        String birthyear = ((EditText) findViewById(R.id.birth_editText)).getText().toString();
        String mobile = ((EditText) findViewById(R.id.phone_editText)).getText().toString();
        String gender = str_result;
        String name = ((EditText) findViewById(R.id.name_editText)).getText().toString();


        Intent interestCheck2 = new Intent(Userinformation.this, User_interest_app.class);
        interestCheck2.putExtra("name", name);
        interestCheck2.putExtra("gender", gender);
        interestCheck2.putExtra("birthyear", birthyear);
        interestCheck2.putExtra("mobile", mobile);
        startActivity(interestCheck2);





        }


    }






