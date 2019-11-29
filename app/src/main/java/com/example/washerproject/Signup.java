package com.example.washerproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import com.example.*;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;

public class Signup extends Activity {
    AlertDialog alertdialog;
    EditText userid, userpassword, username, userage, phonenumber;
    Button signup, idcheck;
    Spinner bank;
    int num=0;
    private boolean validate = false;
    String url = "https://scv0319.cafe24.com/songi/signup.php";
    String user_id, user_id2;
    String text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userid= (EditText) findViewById(R.id.userid);
        userpassword= (EditText) findViewById(R.id.userpassword);
        username= (EditText) findViewById(R.id.username);
        idcheck = (Button) findViewById(R.id.idcheck);
        signup = (Button) findViewById(R.id.signup);

        idcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id= userid.getText().toString();
                System.out.println(user_id);
                user_id2 = user_id;
                if (user_id.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                    alertdialog = builder.setMessage("ID is empty")
                            .setPositiveButton("확인", null)
                            .create();
                    alertdialog.show();
                }
                else{
                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try{
                                Toast.makeText(Signup.this, response, Toast.LENGTH_LONG).show();
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if(success){//사용할 수 있는 아이디라면
                                    num=1;
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                                    alertdialog = builder.setMessage("you can use ID")
                                            .setPositiveButton("OK", null)
                                            .create();
                                    alertdialog.show();
                                    //System.out.println(num);
                                    validate = true;//검증완료
                                }else{//사용할 수 없는 아이디라면`
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                                    alertdialog = builder.setMessage("already used ID")
                                            .setNegativeButton("OK", null)
                                            .create();
                                    alertdialog.show();
                                    userid.setText("");
                                }

                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    };
                    ValidateRequest ValidateRequest = new ValidateRequest(user_id, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Signup.this);
                    queue.add(ValidateRequest);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sid = userid.getText().toString();
                String spassword = userpassword.getText().toString();
                String sname = username.getText().toString();

                if(sid.isEmpty() ||spassword.isEmpty()||sname.isEmpty()){
                    Toast.makeText(Signup.this, "Fill all details", Toast.LENGTH_SHORT).show();
                }
                else if(num==0){
                    System.out.println(num);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                    alertdialog = builder.setMessage("아이디 중복검사를 해주세요.")
                            .setPositiveButton("OK", null)
                            .create();
                    alertdialog.show();
                }
                else if(!user_id.equals(sid)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                    alertdialog = builder.setMessage("아이디 중복검사를 해주세요.")
                            .setPositiveButton("OK", null)
                            .create();
                    alertdialog.show();
                }
                else {
                    signup(sid, spassword, sname);
                    SharedPreferences preferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.putString("userid",sid);
                    editor.putString("userpassword",spassword);
                    editor.putString("username",sname);
                    editor.commit();
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                    alertdialog = builder.setMessage("회원가입을 축하합니다.")
                            .setPositiveButton("OK", null)
                            .create();
                    alertdialog.show();
                    show();
                }
            }
        });
    }

    public void show(){
        Intent intent = new Intent(Signup.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void signup(final String userid, final String userpassword,final String username){
        RequestQueue requestQueue = Volley.newRequestQueue(Signup.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Hitesh",""+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("2");
                Log.i("Hitesh",""+error);
                Toast.makeText(Signup.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("userid",userid);
                stringMap.put("userpassword",userpassword);
                stringMap.put("username",username);
                return stringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}