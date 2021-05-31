package com.examples.jogijotae;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class FirstMain extends AppCompatActivity implements View.OnClickListener {
    Button btn01,btn02,btn03;

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
            Intent intent01 = new Intent(this, LoginActivity.class);
            startActivity(intent01);
        }
        if(v.getId()==R.id.button3){
            Intent intent02 = new Intent(this, RegisterActivity.class);
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

                    Toast.makeText(mContext, "name : " + name + " email : " + email + " gender : " + gender + " birthyear : " + birthyear
                            + " mobile : " + mobile, Toast.LENGTH_LONG).show();

                    Intent interestCheck = new Intent(FirstMain.this, User_interest.class);
                    interestCheck.putExtra("name", name);
                    interestCheck.putExtra("email", email);
                    interestCheck.putExtra("gender", gender);
                    interestCheck.putExtra("birthyear", birthyear);
                    interestCheck.putExtra("mobile", mobile);
                    startActivity(interestCheck);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
