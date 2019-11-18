package com.example.washerproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_sub);
        TextView nameText = (TextView) findViewById(R.id.nameText);
        Intent intent = getIntent();
        nameText.setText(intent.getStringExtra("nameText").toString());
    }

}