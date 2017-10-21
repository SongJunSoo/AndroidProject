package com.example.a50137436.uitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *  가장 먼저 구동되는 화면(근거 필터)
 */
public class MainActivity extends AppCompatActivity {

    EditText uid;
    EditText upw;
    // 화면을 구성 !!
    // 많은 연산을 수행하면 않된다. ! => 유저가 앱의 화면을 늦게 보게됨
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML에 배치된 위젯(요소들)을 자바 코드에서 접근하기 위해
        // 아이디를 넣어서 해당 객체를 획득한다.
        uid = (EditText)findViewById(R.id.uid);
        upw = (EditText)findViewById(R.id.upw);
    }

    // 공인 인증서 로그인 클릭하면 호출
    // onAuthLogin

    public void onAuthLogin(View view)
    {
        Toast.makeText(this, "공인인증서 로그인 클릭했다", Toast.LENGTH_SHORT).show();
    }

    // 아이디 비번으로 로그인하는 버튼 클릭
    public void onLogin(View view)
    {
        // 1. 값 체크

        // 이용자아이디는 영문,숫자,대소문자 구분 없이 4~16자 입니다.
        if(TextUtils.isEmpty(uid.getText().toString()))
        {
            uid.setError("이용자아이디는 영문,숫자,대소문자 구분 없이 4~16자 입니다.");
            return;
        }

        // 이용자아이디는 영문,숫자,대소문자 구분 없이 4~16자 입니다.
        if(TextUtils.isEmpty(upw.getText().toString()))
        {
            upw.setError("비밀번호를 입력해주세요");
            return;
        }

        // 2. 전문 구성 => 로그인 통신 => 이상없으면 => 서비스이동
        Toast.makeText(this, "로그인 클릭했다", Toast.LENGTH_SHORT).show();
    }
}
