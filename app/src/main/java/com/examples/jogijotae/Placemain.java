package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Placemain extends AppCompatActivity implements AdapterView.OnItemClickListener{
    int i = 0;
    //EditText text01;
    private ListView list;
    private   List<String> data =new ArrayList<>();
    private static final String TAG = "Placemain";
    // ArrayList<String> divi;
    String[] newposition = new String[50];
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placemain);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        list =findViewById(R.id.listview);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);


        //text01 = findViewById(R.id.restaurantmain_text);

        // divi = new ArrayList<String>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        db.collection("Place")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());




                                if (document.getString("division").equals("P")) {
                                    data.add (document.getString("name"));
                                    newposition[i++]=document.getString("position");
                                    adapter.notifyDataSetChanged();
                                }
                            }


                        } else {
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });








    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String[] position2 = new String[50];
        position2[position] = newposition[position];

        Log.d(TAG,"테스트2"+newposition[position]);
        Intent intent01 = new Intent(Placemain.this,Place_detail.class);
        intent01.putExtra("position",newposition[position]);
        startActivity(intent01);
    }
}
