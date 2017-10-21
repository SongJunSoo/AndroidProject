package com.example.a50137436.myfirstmobileproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 *  화면 한개를 담당!
 */ 
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // XML로 구성된 화면을 생성하며 액티비티의 화면으로 설정
        // 그 화면을 유저가 보게 된다.
        setContentView(R.layout.activity_main);
        Log.i("SH", "로그");
    }
}
