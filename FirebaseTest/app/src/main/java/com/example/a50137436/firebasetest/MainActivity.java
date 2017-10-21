package com.example.a50137436.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.a50137436.firebasetest.util.U;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity {
    String nameStr;
    ProgressBar progressBar;
    String pushMsg;

    public static String PUSH_SEVER_KEY;
    public static String SHIN_MAIN_URL;

    // 원격구성
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 원격구성 초기화
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        fetchWelcome();

        // 푸쉬를 통해 알람을 눌러서 진입했을 경우
        // 액티비티로 보낸 데이터를 추출하기
        pushMsg = getIntent().getStringExtra("push");
        U.getInstance().log("SH", "main => " + pushMsg);
        // xml로 만들어진 View를 자바 코드에서 참조하기 위해
        // 가져오는 코드 => 데이터 바인딩
        TextView name = (TextView)findViewById(R.id.name);
        //name.setText(nameStr);
        //Log.i("SH", nameStr);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        // 0번 상황을 메시지로 던진다.


        // 최초 체크!!
        // 망체크!! => 연결된 망이 없으면 안내후 종료
        // 해외인지 체크!! => 접속한 클라이언트의 IP를 확인하여 진단
        //                => 내부 시나리오에 의해 처리
        // 버전체크 => 업데이트 내역이 존재하는가?(하이브리드기준)
        //          => 네이티브 경우 자동 업데이트, 혹은, 강제업데이트유도

        // 중복 단말 (로그인시도시) 체크
    }

     public void fetchWelcome()
     {
         long cacheExpiration = 3600; // 1 hour in seconds.
         if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
             cacheExpiration = 0;
         }

         // 실제 원격 구성 데이터를 가져오는 메소드
         mFirebaseRemoteConfig.fetch(cacheExpiration)
                 .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         // 비동기 작업이 완료되서 성공했다면
                         if (task.isSuccessful()) {
                             // 실제로 데이터를 획득해라
                             mFirebaseRemoteConfig.activateFetched();
                             PUSH_SEVER_KEY = mFirebaseRemoteConfig.getString("PUSH_SEVER_KEY");
                             SHIN_MAIN_URL = mFirebaseRemoteConfig.getString("SHIN_MAIN_URL");
                             handler.sendEmptyMessageDelayed(0, 1000*1);
                         } else {
                             // 획득실패 => 종료
                         }
                     }
         });


     }



    // Loop라는 메시지를 관리하는 큐를 핸들링하는 요소로
    // 손쉽게 화면처리및 다양한 기능을 제어할수 있다
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if( msg.what == 0 ){
                // 최초 체크!!
                progressBar.setVisibility(View.VISIBLE);

                // 망체크!! => 연결된 망이 없으면 안내후 종료
                // 해외인지 체크!! => 접속한 클라이언트의 IP를 확인하여 진단
                //                => 내부 시나리오에 의해 처리
                // 버전체크 => 업데이트 내역이 존재하는가?(하이브리드기준)
                //          => 네이티브 경우 자동 업데이트, 혹은, 강제업데이트유도

                // 중복 단말 (로그인시도시) 체크

                // 신한뱅킹으로 이동 !! => 화면전환 => Intent
                Intent intent = new Intent(MainActivity.this, WebMainActivity.class);
                // 푸쉬데이터가 존재할 경우만 데이터를 보낸다.
                if(pushMsg != null)
                        intent.putExtra("push",pushMsg);
                startActivity(intent);
                finish();
            }
        }
    };
}




