package com.examples.jogijotae;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class FirstMain extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "FirstMain";

    Button btn01,btn02,btn03;
ImageView ivImage;
    OAuthLogin mOAuthLoginModule;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_main);

        mContext = getApplicationContext();
    btn01=findViewById(R.id.button);
    btn02=findViewById(R.id.button2);
    btn03=findViewById(R.id.button3);
    btn01.setOnClickListener(this);
    btn02.setOnClickListener(this);
    btn03.setOnClickListener(this);
ivImage=findViewById(R.id.iv_image);
        String imageUrl ="https://firebasestorage.googleapis.com/v0/b/jogijotae-b10e1.appspot.com/o/Resturant_images%2Fjogijotae.PNG?alt=media&token=92964853-6083-48ae-bb76-f2f053d7e609";
      Glide.with(this).load(imageUrl).into(ivImage);




    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.button) {
            mOAuthLoginModule = OAuthLogin.getInstance();
            mOAuthLoginModule.init(
                    mContext
                    ,getString(R.string.naver_client_id)
                    ,getString(R.string.naver_client_secret)
                    ,getString(R.string.naver_client_name)
                    //,OAUTH_CALLBACK_INTENT
                    // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
            );

            @SuppressLint("HandlerLeak")
            OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
                @Override
                public void run(boolean success) {
                    if (success) {
                        String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                        String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
                        long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
                        String tokenType = mOAuthLoginModule.getTokenType(mContext);

                        Log.i("LoginData","accessToken : "+ accessToken);
                        Log.i("LoginData","refreshToken : "+ refreshToken);
                        Log.i("LoginData","expiresAt : "+ expiresAt);
                        Log.i("LoginData","tokenType : "+ tokenType);

                        RequestApiTask task = new RequestApiTask(mContext, mOAuthLoginModule);
                        task.execute();

                    } else {
                        String errorCode = mOAuthLoginModule
                                .getLastErrorCode(mContext).getCode();
                        String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                        Toast.makeText(mContext, "errorCode:" + errorCode
                                + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                    }
                };
            };

            mOAuthLoginModule.startOauthLoginActivity(FirstMain.this, mOAuthLoginHandler);
        }

        if(v.getId()==R.id.button2){

            Intent intent = getIntent();
            double latitude = intent.getDoubleExtra("latitude", 0);
            double longitude = intent.getDoubleExtra("longitude", 0);

            Intent intent01 = new Intent(this, LoginActivity.class);
            intent01.putExtra("latitude", latitude);
            intent01.putExtra("longitude", longitude);
            startActivity(intent01);
        }
        if(v.getId()==R.id.button3){
            Intent intent02 = new Intent(this, RegisterActivity.class);
            Intent intent = getIntent();
            double latitude = intent.getDoubleExtra("latitude", 0);
            double longitude = intent.getDoubleExtra("longitude", 0);
            intent02.putExtra("latitude", latitude);
            intent02.putExtra("longitude", longitude);
            startActivity(intent02);
        }
    }

    // 네이버 로그인시 사용자 정보 받기

    public class RequestApiTask extends AsyncTask<Void, Void, String> {
        private final Context mContext;
        private final OAuthLogin mOAuthLoginModule;
        public RequestApiTask(Context mContext, OAuthLogin mOAuthLoginModule) {
            this.mContext = mContext;
            this.mOAuthLoginModule = mOAuthLoginModule;
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(Void... params) {
            String url = "https://openapi.naver.com/v1/nid/me";
            String at = mOAuthLoginModule.getAccessToken(mContext);
            return mOAuthLoginModule.requestApi(mContext, at, url);
        }

        public void onPostExecute(String content) {
            try {
                JSONObject loginResult = new JSONObject(content);
                if (loginResult.getString("resultcode").equals("00")){
                    JSONObject response = loginResult.getJSONObject("response");
                    String name = response.getString("name");
                    String email = response.getString("email");
                    String gender = response.getString("gender");
                    String birthyear = response.getString("birthyear");
                    String mobile = response.getString("mobile");

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference docRef = db.collection("users").document(email);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document != null) {
                                    if (document.exists()) {
                                        Log.d(TAG, "DocumentSnapshot data:" + document.getData());
                                       Intent intent01 = getIntent();
                                        double latitude = intent01.getDoubleExtra("latitude", 0);
                                        double longitude = intent01.getDoubleExtra("longitude", 0);
                                        Intent intent = new Intent(FirstMain.this, MainActivity.class);
                                        intent.putExtra("latitude", latitude);
                                        intent.putExtra("longitude", longitude);
                                        startToast(email + " 님 환영합니다.");
                                        intent.putExtra("email", email);
                                        Log.d(TAG,"내위치는"+latitude+","+longitude);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Log.d(TAG, "No such document");
                                        Intent intent01 = getIntent();
                                        double latitude = intent01.getDoubleExtra("latitude", 0);
                                        double longitude = intent01.getDoubleExtra("longitude", 0);
                                        Intent interestCheck = new Intent(FirstMain.this, User_interest.class);
                                        interestCheck.putExtra("latitude", latitude);
                                        interestCheck.putExtra("longitude", longitude);
                                        interestCheck.putExtra("name", name);
                                        interestCheck.putExtra("email", email);
                                        interestCheck.putExtra("gender", gender);
                                        interestCheck.putExtra("birthyear", birthyear);
                                        interestCheck.putExtra("mobile", mobile);
                                        Log.d(TAG,"내위치는"+latitude+","+longitude);
                                        startActivity(interestCheck);
                                    }
                                } else {
                                    Log.d(TAG, "get failed with", task.getException());
                                }
                            }
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        }
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    }


