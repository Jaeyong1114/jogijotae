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



    private static final String TAG = "Userinformation";
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


        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // 체크된 라디오 버튼의 값을 str_result 변수의 넣는다. 어느 성별을 골랐는지 체크하기 위함
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
            case R.id.checkButton: // 확인 버튼 클릭시
                profileUpdate();
                break;
        }
    };

    public void profileUpdate() {
        Intent intent01 = getIntent();
        double latitude = intent01.getDoubleExtra("latitude", 0);
        double longitude = intent01.getDoubleExtra("longitude", 0);

        // 데이터베이스에 저장할 유저의 정보를 변수에 넣는다.
        String birthyear = ((EditText) findViewById(R.id.birth_editText)).getText().toString();
        String mobile = ((EditText) findViewById(R.id.phone_editText)).getText().toString();
        String gender = str_result;
        String name = ((EditText) findViewById(R.id.name_editText)).getText().toString();
        // 이름의 값이 0 보다 크고 출생년도 값이 3보다 크고 5보다 작고 전화번호의 길이가 13이면
        if (name.length() > 0 && birthyear.length() > 3 && birthyear.length() < 5 && mobile.length()> 12 && mobile.length() < 14) {

            Intent interestCheck2 = new Intent(Userinformation.this, User_interest_app.class);
            interestCheck2.putExtra("latitude", latitude);
            interestCheck2.putExtra("longitude", longitude);
            interestCheck2.putExtra("name", name);
            interestCheck2.putExtra("gender", gender);
            interestCheck2.putExtra("birthyear", birthyear);
            interestCheck2.putExtra("mobile", mobile);
            startActivity(interestCheck2); // 유저의 기본정보 값을 담아서 취향을 선택 할 User_interest_app으로 이동
            finish();

        }
else{
    startToast("회원정보를 입력해주세요");

        }

    }
    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}






