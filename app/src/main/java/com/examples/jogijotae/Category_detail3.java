package com.examples.jogijotae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category_detail3 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView list;


    private List<String> data =new ArrayList<>();
    private static final String TAG = "Category_detail3";
    String[] directionlati = new String[50];
    String[] directionlongi = new String[50];
    String[] newposition = new String[50];


    int i=0; int y=0; int z=0;






    private double distance(double lat1, double lon1, double lat2, double lon2 ) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +
                Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                        Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist *60 *1.1515;

        dist = dist * 1.609344;

 return (dist);
}

    private double deg2rad(double deg) {
        return(deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad / Math.PI * 180.0) ;
    }

// 거리계산














    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_detail3);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        list =findViewById(R.id.listview);

        list.setAdapter(adapter);



        list.setOnItemClickListener(this);





        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);


        //location.setText("위도=" + latitude + ", 경도=" + longitude);


        db.collection("Place")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {




                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                if ((document.getString("division").equals("R"))) {



                                    directionlati[i++] = document.getString("lati");
                                    directionlongi[y++] = document.getString("longi");


                           int i=Integer.parseInt(document.getString("position"));
//int형변환

                                    newposition[i] = document.getString("position");
                                    Log.d(TAG,"확인해보세요"+newposition[0]);



                                    String placelongi = directionlongi[i];
                                    String placelati = directionlati[i];

                                    // Log.d(TAG,"placelongi가뭐냐"+placelongi);
                                    double newplacelati = Double.parseDouble(placelati);
                                    double newplacelongi = Double.parseDouble(placelongi);
                                    // Log.d(TAG,"더블형변환"+newplacelongi);

                                    Log.d(TAG ,"경로테스트"+directionlati[i]+","+directionlongi[i]);

                                    Log.d(TAG,"계산식"+distance(latitude, longitude,newplacelati,newplacelongi));

                                    //location.append(""+distance(latitude, longitude,newplacelati,newplacelongi) + "\n");



                                int newdistance = Integer.parseInt(String.valueOf(Math.round(distance(latitude, longitude,newplacelati,newplacelongi))));
//int로받기위해 double형 형변환



                                        data.add("(" + newdistance + "km" + ")" + "\n" + document.getString("name") );






                                //거리순 리스트뷰 정렬

                                    Collections.sort(data);
                                    //정렬

                                    adapter.notifyDataSetChanged();

                                }




                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

              Log.d(TAG,"현재 내위치"+latitude+","+longitude);
                    }
                });




    }






    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        String[] position2 = new String[50];
        position2[position] = newposition[position];


        Log.d(TAG,"테스트해보자한번"+newposition[position]);
        Intent intent01 = new Intent(Category_detail3.this,Category_detail4.class);
        intent01.putExtra("position",newposition[position]);
        startActivity(intent01);

    }



}

