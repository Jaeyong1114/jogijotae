package com.examples.jogijotae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Calendar;
import java.util.Date;

public class InterestCheck extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference databaseReference = database.getReference();

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

        addLoginInfo(name, email, gender, birthyear, mobile);
    }

    // DB에 user 값 저장

    public void addLoginInfo(String name, String email, String gender, String birthyear, String mobile) {
        naver_login naver_login = new naver_login(name, email, gender, birthyear, mobile);

        databaseReference.child("user").child(name).setValue(naver_login);
    }
}