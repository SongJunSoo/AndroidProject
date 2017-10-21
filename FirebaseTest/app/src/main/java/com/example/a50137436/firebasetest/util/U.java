package com.example.a50137436.firebasetest.util;


import android.util.Log;

import com.example.a50137436.firebasetest.WebMainActivity;

/**
 *  싱글톤 클래스이고 유틸리티 기능을 모두 총괄한다.
 *  여기서는 앱이 구동중인지 체크하는 객체를 담는 그릇의 용도로 사용.
 * */

public class U {
    // ========================================================================== //
    private static final U ourInstance = new U();
    public static U getInstance() {
        return ourInstance;
    }
    private U() {
    }
    // ========================================================================== //
    public void log(String tag, String msg)
    {
        // 디버그 모드 일때만 노출된다.
        //if(BuildConfig.DEBUG)
            Log.i(""+tag, ""+msg);
    }
    // ========================================================================== //
    // 푸시가 와서 알리 띄울때 WebMainActivity 객체가 존재하면 그냥 확인, 없으면 앱을 띄움
    WebMainActivity  webMainActivity;

    public WebMainActivity getWebMainActivity() {
        return webMainActivity;
    }

    public void setWebMainActivity(WebMainActivity webMainActivity) {
        this.webMainActivity = webMainActivity;
    }
}
