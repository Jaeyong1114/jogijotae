package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class User_interest_app extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "User_interest_app";

    CheckBox UI_check_place01, UI_check_place02, UI_check_place03, UI_check_place04, UI_check_place05, UI_check_place06;
    CheckBox UI_check_restarant01, UI_check_restarant02, UI_check_restarant03, UI_check_restarant04;
    Button UI_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interest_app);

        UI_check_place01 = findViewById(R.id.UI_check_place01);
        UI_check_place02 = findViewById(R.id.UI_check_place02);
        UI_check_place03 = findViewById(R.id.UI_check_place03);
        UI_check_place04 = findViewById(R.id.UI_check_place04);
        UI_check_place05 = findViewById(R.id.UI_check_place05);
        UI_check_place06 = findViewById(R.id.UI_check_place06);


        UI_check_restarant01 = findViewById(R.id.UI_check_restarant01);
        UI_check_restarant02 = findViewById(R.id.UI_check_restarant02);
        UI_check_restarant03 = findViewById(R.id.UI_check_restarant03);
        UI_check_restarant04 = findViewById(R.id.UI_check_restarant04);

        UI_btn = findViewById(R.id.UI_btn);
        UI_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent data_receive = getIntent();


        String name = data_receive.getStringExtra("name");
        String birthyear = data_receive.getStringExtra("birthyear");
        String gender = data_receive.getStringExtra("gender");
        String mobile = data_receive.getStringExtra("mobile");
        String email = user.getEmail();
        //Log.d(TAG,"이메일"+user.getEmail());


        String place01 = null, place02 = null, place03 = null, place04 = null,place05 = null, place06 = null,
                restarant01 = null, restarant02 = null, restarant03 = null, restarant04 = null;



        if (UI_check_place01.isChecked()) {
            place01 = UI_check_place01.getText().toString();
        }
        if (UI_check_place02.isChecked()) {
            place02 = UI_check_place02.getText().toString();
        }
        if (UI_check_place03.isChecked()) {
            place03 = UI_check_place03.getText().toString();
        }
        if (UI_check_place04.isChecked()) {
            place04 = UI_check_place04.getText().toString();
        }
        if (UI_check_place05.isChecked()) {
            place05 = UI_check_place05.getText().toString();
        }
        if (UI_check_place06.isChecked()) {
            place06 = UI_check_place06.getText().toString();
        }



        if (UI_check_restarant01.isChecked()) {
            restarant01 = UI_check_restarant01.getText().toString();
        }
        if (UI_check_restarant02.isChecked()) {
            restarant02 = UI_check_restarant02.getText().toString();
        }
        if (UI_check_restarant03.isChecked()) {
            restarant03 = UI_check_restarant03.getText().toString();
        }
        if (UI_check_restarant04.isChecked()) {
            restarant04 = UI_check_restarant04.getText().toString();
        }



        Interest Interest = new Interest(place01, place02, place03, place04,place05,place06, restarant01, restarant02, restarant03, restarant04, name, gender, birthyear, mobile,email);
        db.collection("users").document(user.getEmail()).set(Interest)
                .addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("회원정보 등록을 완료하였습니다.");
                        Intent intent = new Intent(User_interest_app.this, MainActivity.class);
                        startActivity(intent);
                        finish();
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
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}