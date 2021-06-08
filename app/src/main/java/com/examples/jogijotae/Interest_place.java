package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Interest_place extends AppCompatActivity implements AdapterView.OnItemClickListener {
    int i = 0;
    String place01 = "x", place02 = "x", place03 = "x", place04 = "x", place05 = "x", place06 = "x";
    private ListView list;
    private List<String> data =new ArrayList<>();
    private static final String TAG = "Interest_place";

    String[] newposition = new String[50];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_place);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        list =findViewById(R.id.listview);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        Intent data_recevie = getIntent();
        place01 = data_recevie.getStringExtra("place01");
        place02 = data_recevie.getStringExtra("place02");
        place03 = data_recevie.getStringExtra("place03");
        place04 = data_recevie.getStringExtra("place04");
        place05 = data_recevie.getStringExtra("place05");
        place06 = data_recevie.getStringExtra("place06");


        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("Place")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                if (document.getString("category2").equals(""+place01+"") ||
                                        document.getString("category2").equals(""+place02+"") ||
                                        document.getString("category2").equals(""+place03+"") ||
                                        document.getString("category2").equals(""+place04+"") ||
                                        document.getString("category3").equals(""+place05+"") ||
                                        document.getString("category3").equals(""+place06+"")){

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


        Intent intent01 = new Intent(Interest_place.this, Interest_detail.class);
        intent01.putExtra("position",newposition[position]);
        startActivity(intent01);
    }
}