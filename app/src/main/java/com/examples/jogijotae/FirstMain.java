package com.examples.jogijotae;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

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
                        redirectSignup();

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

    public void redirectSignup() {
        Intent intent03 = new Intent(FirstMain.this, MainActivity.class);
        startActivity(intent03);
    }

}