package com.huatec.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.huatec.hiot_cloud.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp框架测试类
 */
public class TestOKHttpActivity extends AppCompatActivity {

    // private static final String basUrl = "http://220.181.38.148";
    private static final String basUrl = "http://114.67.88.191:8080";
    private static final String TAG ="TestOkHttpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_okhttp);

        //execute的方法测试
        Button btnExecute = findViewById(R.id.btn_okhttp_execute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testExecute();

            }
        });

        //enqueue的方法测试
        Button btnEnqueue = findViewById(R.id.btn_okhttp_enqueue);
        btnEnqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testEnqueue();

            }
        });

        //登录测试
        Button btnLogin = findViewById(R.id.btn_okhttp_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("zwr","abc123","app");
            }
        });

        //获取用户信息
        final Button btnGetUserInfo = findViewById(R.id.btn_okhttp_userinfo);
        btnGetUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo("47d597d6ad0f41448bc05848e2bd9f0c_65248ec1cfb649369e49e35754c4624e_use");

            }
        });

        //修改邮箱
        final Button btnUpdateEmail = findViewById(R.id.btn_okhttp_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail("47d597d6ad0f41448bc05848e2bd9f0c_65248ec1cfb649369e49e35754c4624e_use","zwr@qq.com");
            }
        });
    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void updateEmail(String authorization,String email) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = basUrl + "/user/email?email=" + email;
        Request request = new Request.Builder().put(body).url(url)
                .header("Authorization",authorization).build();
        callEnqueue(okHttpClient, request);
    }

    private void callEnqueue(OkHttpClient okHttpClient, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailue:" + e.getMessage(), e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse:" + response.body().string());
            }
        });
    }

    /**
     *
     *获取用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = basUrl + "/user/one";
        Request request = new Request.Builder().get().url(url)
                .header("Authorization",authorization).build();
        callEnqueue(okHttpClient, request);

    }

    /**
     *登录
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login(String userName, String password, String loginCode) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = basUrl + String.format("/auth/login?username=%s&password=%s&loginCode=%s",
                userName,password,loginCode);
        Request request = new Request.Builder().post(body).url(url).build();
        callEnqueue(okHttpClient, request);
    }

    /**
     * 测试execute方式
     */
    private void testExecute() {

        new Thread(){
            @Override
            public void run(){
                super.run();
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(basUrl).build();
                callEnqueue(okHttpClient,request);
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.d(TAG,"run:" + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "testExcecute" + e.getMessage(), e);
                }
            }
        }.start();
    }

    /**
     * 测试enqueue方式
     */
    private void testEnqueue() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(basUrl).build();
        callEnqueue(okHttpClient, request);
    }
}
