package com.example.washerproject;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView txtResult;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_sub);
        TextView nameText = (TextView) findViewById(R.id.nameText);
        Intent intent = getIntent();
        nameText.setText(intent.getStringExtra("nameText").toString());

        txtResult = (TextView)findViewById(R.id.txtResult);
    }

    //버튼을 눌렀을때 데이터를 담아서 팝업 액티비티를 호출하는 메소드
    public void mOnPopupClick(View v){
        Intent intent = new Intent(this, PopupActivity.class);
        intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");
                txtResult.setText(result);
            }
        }
    }
}