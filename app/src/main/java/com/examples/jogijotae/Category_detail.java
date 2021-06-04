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

public class Category_detail extends AppCompatActivity implements AdapterView.OnItemClickListener{
int i = 0;
    //EditText text01;
    private ListView list;
    private   List<String> data =new ArrayList<>();
    private static final String TAG = "Category_detail";
    // ArrayList<String> divi;
String[] newposition = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        list =findViewById(R.id.listview);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);


        //text01 = findViewById(R.id.restaurantmain_text);

        // divi = new ArrayList<String>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       Intent receive_data= getIntent();
       String k= receive_data.getStringExtra("k");

        db.collection("Place")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());




                                if ((document.getString("category").equals(""+k+""))  ||(document.getString("category2").equals(""+k+""))
                                        ||document.getString("category3").equals(""+k+"")){
                                    data.add (document.getString("name"));

                                    newposition[i++]=document.getString("position");
                                    Log.d(TAG,"테스트"+newposition);



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
        Intent intent01 = new Intent(Category_detail.this,Category_detail2.class);
        intent01.putExtra("position",newposition[position]);
        startActivity(intent01);
       /* Log.d(TAG,"확인바람"+data.get(position));
        Intent intent01 = new Intent(Placemain.this,Place_detail.class);
        intent01.putExtra("position",position);
        startActivity(intent01);
        Log.d(TAG,"플레이스");*/
    }
}