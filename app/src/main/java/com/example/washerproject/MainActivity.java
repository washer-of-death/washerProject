package com.example.washerproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameText, passText;
    Button nameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = (EditText) findViewById(R.id.nameText);
        passText = (EditText) findViewById(R.id.passwordText);
        nameButton = (Button) findViewById(R.id.nameButton);

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                login();
            }

        });
    }
    public void login() {
        String username = nameText.getText().toString().trim();
        String pass = passText.getText().toString().trim();
        if(username.equals("2018038017") && pass.equals("1234567")){
            Toast.makeText(this, "Login Success!",Toast.LENGTH_LONG).show();
            String name = nameText.getText().toString();
            Intent intent = new Intent(getApplicationContext(), SubActivity.class);
            intent.putExtra("nameText",name);
            startActivity(intent);
        } else{
            Toast.makeText(this, "Login failed!", Toast.LENGTH_LONG).show();
        }
    }
}