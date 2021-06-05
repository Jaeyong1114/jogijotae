package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


               /*         구현중        */
public class Category_detail6 extends AppCompatActivity {
    EditText gps_detailtext2;
    private static final String TAG = "Category_detail4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail6);
        gps_detailtext2=findViewById(R.id.gpsdetail2_text);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        db.collection("Place")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Intent data_receive = getIntent();
                        //  int position = data_receive.getIntExtra("position",1);
                        String position = data_receive.getStringExtra("position");



                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                               /* if ((document.getString("category").equals("한식"))  ||(document.getString("category2").equals("가족여행"))
                                ||document.getString("category3").equals("가족여행")){*/
                                if (document.getString("position").equals(""+position+"")   ){


                                    gps_detailtext2.append("이름:  "+document.getString("name")+"\n\n");
                                    gps_detailtext2.append("주소:  "+document.getString("address")+"\n\n");
                                    gps_detailtext2.append("전화번호:  "+document.getString("phone")+"\n\n");
                                    gps_detailtext2.append("소개:  "+document.getString("ex")+"\n")
                                    ;

                                }
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }


                    }
                });


    }





}

