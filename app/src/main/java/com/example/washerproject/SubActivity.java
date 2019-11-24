package com.example.washerproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_sub);
    }

    //버튼을 눌렀을때 데이터를 담아서 팝업 액티비티를 호출하는 메소드
    public void mOnPopupClick(View v){
        Intent intent = new Intent(this, PopupActivity.class);
        //intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 1);
    }
}