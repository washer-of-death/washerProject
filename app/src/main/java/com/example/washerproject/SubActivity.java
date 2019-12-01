package com.example.washerproject;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SubActivity extends AppCompatActivity {
    AlertDialog alertDialog;
    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;
    SharedPreferences shared;
    ImageView button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11,txt12,txt13;
    String userid;
    String washer_num= "0";
    String myJSON;
    int [] washerSTATE = new int[13];
    String[] w_userID = new String[13];
    String[] washerEND = new String[13];
    String url2;
    Button logout;

    SwipeRefreshLayout layout;
    WebView webView;
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_sub);
        layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //새로고침 작업 실행...
                getData(url2);
                showList();
                layout.setRefreshing(false);
            }
        });

        shared = getSharedPreferences("Mypref", MODE_PRIVATE);
        userid = shared.getString("userid", "");

        button1 = (ImageView)findViewById(R.id.button1);
        button2 = (ImageView)findViewById(R.id.button2);
        button3 = (ImageView)findViewById(R.id.button3);
        button4 = (ImageView)findViewById(R.id.button4);
        button5 = (ImageView)findViewById(R.id.button5);
        button6 = (ImageView)findViewById(R.id.button6);
        button7 = (ImageView)findViewById(R.id.button7);
        button8 = (ImageView)findViewById(R.id.button8);
        button9 = (ImageView)findViewById(R.id.button9);
        button10 = (ImageView)findViewById(R.id.button10);
        button11 = (ImageView)findViewById(R.id.button11);
        button12 = (ImageView)findViewById(R.id.button12);
        button13 = (ImageView)findViewById(R.id.button13);
        txt1 = (TextView)findViewById(R.id.txt1);
        txt2 = (TextView)findViewById(R.id.txt2);
        txt3 = (TextView)findViewById(R.id.txt3);
        txt4 = (TextView)findViewById(R.id.txt4);
        txt5 = (TextView)findViewById(R.id.txt5);
        txt6 = (TextView)findViewById(R.id.txt6);
        txt7 = (TextView)findViewById(R.id.txt7);
        txt8 = (TextView)findViewById(R.id.txt8);
        txt9 = (TextView)findViewById(R.id.txt9);
        txt10 = (TextView)findViewById(R.id.txt10);
        txt11 = (TextView)findViewById(R.id.txt11);
        txt12 = (TextView)findViewById(R.id.txt12);
        txt13 = (TextView)findViewById(R.id.txt13);

        logout = (Button)findViewById(R.id.logout);

        url2 ="https://scv0319.cafe24.com/songi/washer_DefaultState.php";
        getData(url2);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'세탁기 6번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button1.setImageResource(R.drawable.whasherusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button1.setImageResource(R.drawable.my);
                        washer_num = "1";
                        button1.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'세탁기 7번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button2.setImageResource(R.drawable.whasherusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button2.setImageResource(R.drawable.my);
                        washer_num = "2";
                        button2.setEnabled(false);
                        change_state(userid, washer_num);

                    }
                });
                alertDialog.show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'세탁기 8번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button3.setImageResource(R.drawable.whasherusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button3.setImageResource(R.drawable.my);
                        washer_num = "3";
                        button3.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'세탁기 5번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button4.setImageResource(R.drawable.whasherusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button4.setImageResource(R.drawable.my);
                        washer_num = "4";
                        button4.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'세탁기 4번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button5.setImageResource(R.drawable.whasherusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button5.setImageResource(R.drawable.my);
                        washer_num = "5";
                        button5.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'세탁기 3번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button6.setImageResource(R.drawable.whasherusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button6.setImageResource(R.drawable.my);
                        washer_num = "6";
                        button6.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'세탁기 2번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button7.setImageResource(R.drawable.whasherusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button7.setImageResource(R.drawable.my);
                        washer_num = "7";
                        button7.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'세탁기 1번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button8.setImageResource(R.drawable.whasherusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button8.setImageResource(R.drawable.my);
                        washer_num = "8";
                        button8.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'건조기 1번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button9.setImageResource(R.drawable.dryusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button9.setImageResource(R.drawable.my);
                        washer_num = "9";
                        button9.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'건조기 2번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button10.setImageResource(R.drawable.dryusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button10.setImageResource(R.drawable.my);
                        washer_num = "10";
                        button10.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'건조기 3번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button11.setImageResource(R.drawable.dryusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button11.setImageResource(R.drawable.my);
                        washer_num = "11";
                        button11.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'건조기 4번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button12.setImageResource(R.drawable.dryusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button12.setImageResource(R.drawable.my);
                        washer_num = "12";
                        button12.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new AlertDialog.Builder(SubActivity.this).create();

                alertDialog.setTitle("사용 등록");
                alertDialog.setMessage("'건조기 5번'을 사용하시겠습니까?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button13.setImageResource(R.drawable.dryusi);
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button13.setImageResource(R.drawable.my);
                        washer_num = "13";
                        button13.setEnabled(false);
                        change_state(userid, washer_num);
                    }
                });
                alertDialog.show();
            }
        });
    }

    //버튼을 눌렀을때 팝업 액티비티를 호출하는 메소드
    public void mOnPopupClick(View v){
        Intent intent = new Intent(this, PopupActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onBackPressed() {

        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(15);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            //finish();
            ActivityCompat.finishAffinity(this);
            System.runFinalization();
            System.exit(0);
            toast.cancel();
        }
    }

    public void change_state(final String userid, final String washer_num){
        RequestQueue requestQueue = Volley.newRequestQueue(SubActivity.this);
        String url = "https://scv0319.cafe24.com/songi/change_state.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Hitesh",""+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Hitesh",""+error);
                Toast.makeText(SubActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("userid",userid);
                stringMap.put("washer_num", washer_num);
                return stringMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void washer_complete(final String washer_num){
        RequestQueue requestQueue = Volley.newRequestQueue(SubActivity.this);
        String url = "https://scv0319.cafe24.com/songi/washer_Complete.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Hitesh",""+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Hitesh",""+error);
                Toast.makeText(SubActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("washer_num", washer_num);
                return stringMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void showList(){
        try{
            JSONObject jsonObject = new JSONObject(myJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            int count = 0;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);
                washerSTATE[count] = object.getInt("washerSTATE");
                w_userID[count]=object.getString("userID");
                washerEND[count]=object.getString("washerEND");
                //washerEND[count]가 현재 시간보다 이전일 때
                Date washer_end = dateFormat.parse(washerEND[count]);
                if(now.getTime() >= washer_end.getTime()) {
                    //washerSTATE[count]를 0으로 바꾼다.
                    washerSTATE[count] = 0;
                    washer_complete(Integer.toString(count+1));
                }
                count++;
            }
            //1번 세탁기 상태
            if(washerSTATE[0]==0){
                button1.setImageResource(R.drawable.whasherusi);
            }
            else{
                if(w_userID[0].equals(userid)){
                    button1.setImageResource(R.drawable.my);
                }
                else {
                    button1.setImageResource(R.drawable.washeru);
                }
                txt1.setText(washerEND[0].substring(5,16));
                button1.setEnabled(false);
            }

            //2번 세탁기 상태
            if(washerSTATE[1]==0){
                button2.setImageResource(R.drawable.whasherusi);
                txt2.setText("");
            }
            else{
                if(w_userID[1].equals(userid)){
                    button2.setImageResource(R.drawable.my);
                }
                else {
                    button2.setImageResource(R.drawable.washeru);
                }
                txt2.setText(washerEND[1].substring(5,16));
                button2.setEnabled(false);
            }

            //3번 세탁기 상태
            if(washerSTATE[2]==0){
                button3.setImageResource(R.drawable.whasherusi);
                txt3.setText("");
            }
            else{
                if(w_userID[2].equals(userid)){
                    button3.setImageResource(R.drawable.my);
                }
                else {
                    button3.setImageResource(R.drawable.washeru);
                }
                txt3.setText(washerEND[2].substring(5,16));
                button3.setEnabled(false);
            }

            //4번 세탁기 상태
            if(washerSTATE[3]==0){
                button4.setImageResource(R.drawable.whasherusi);
                txt4.setText("");
            }
            else{
                if(w_userID[3].equals(userid)){
                    button4.setImageResource(R.drawable.my);
                }
                else {
                    button4.setImageResource(R.drawable.washeru);
                }
                txt4.setText(washerEND[3].substring(5,16));
                button4.setEnabled(false);
            }

            //5번 세탁기 상태
            if(washerSTATE[4]==0){
                button5.setImageResource(R.drawable.whasherusi);
                txt5.setText("");
            }
            else{
                if(w_userID[4].equals(userid)){
                    button5.setImageResource(R.drawable.my);
                }
                else {
                    button5.setImageResource(R.drawable.washeru);
                }
                txt5.setText(washerEND[4].substring(5,16));
                button5.setEnabled(false);
            }

            //6번 세탁기 상태
            if(washerSTATE[5]==0){
                button6.setImageResource(R.drawable.whasherusi);
                txt6.setText("");
            }
            else{
                if(w_userID[5].equals(userid)){
                    button6.setImageResource(R.drawable.my);
                }
                else {
                    button6.setImageResource(R.drawable.washeru);
                }
                txt6.setText(washerEND[5].substring(5,16));
                button6.setEnabled(false);
            }
            //7번 세탁기 상태
            if(washerSTATE[6]==0){
                button7.setImageResource(R.drawable.whasherusi);
                txt7.setText("");
            }
            else{
                if(w_userID[6].equals(userid)){
                    button7.setImageResource(R.drawable.my);
                }
                else {
                    button7.setImageResource(R.drawable.washeru);
                }
                txt7.setText(washerEND[6].substring(5,16));
                button7.setEnabled(false);
            }
            //8번 세탁기 상태
            if(washerSTATE[7]==0){
                button8.setImageResource(R.drawable.whasherusi);
                txt8.setText("");
            }
            else{
                if(w_userID[7].equals(userid)){
                    button8.setImageResource(R.drawable.my);
                }
                else {
                    button8.setImageResource(R.drawable.washeru);
                }
                txt8.setText(washerEND[7].substring(5,16));
                button8.setEnabled(false);
            }
            //9번 세탁기 상태

            if(washerSTATE[8]==0){
                button9.setImageResource(R.drawable.dryusi);
                txt9.setText("");
            }
            else{
                if(w_userID[8].equals(userid)){
                    button9.setImageResource(R.drawable.my);
                }
                else {
                    button9.setImageResource(R.drawable.dryu);
                }
                txt9.setText(washerEND[8].substring(5,16));
                button9.setEnabled(false);
            }
            //10번 세탁기 상태
            if(washerSTATE[9]==0){
                button10.setImageResource(R.drawable.dryusi);
                txt10.setText("");
            }
            else{
                if(w_userID[9].equals(userid)){
                    button10.setImageResource(R.drawable.my);
                }
                else {
                    button10.setImageResource(R.drawable.dryu);
                }
                txt10.setText(washerEND[9].substring(5,16));
                button10.setEnabled(false);
            }
            //11번 세탁기 상태
            if(washerSTATE[10]==0){
                button11.setImageResource(R.drawable.dryusi);
                txt11.setText("");
            }
            else{
                if(w_userID[10].equals(userid)){
                    button11.setImageResource(R.drawable.my);
                }
                else {
                    button11.setImageResource(R.drawable.dryu);
                }
                txt11.setText(washerEND[10].substring(5,16));
                button11.setEnabled(false);
            }
            //12번 세탁기 상태
            if(washerSTATE[11]==0){
                button12.setImageResource(R.drawable.dryusi);
                txt12.setText("");
            }
            else{
                if(w_userID[11].equals(userid)){
                    button12.setImageResource(R.drawable.my);
                }
                else {
                    button12.setImageResource(R.drawable.dryu);
                }
                txt12.setText(washerEND[11].substring(5,16));
                button12.setEnabled(false);
            }
            //13번 세탁기 상태
            if(washerSTATE[12]==0){
                button13.setImageResource(R.drawable.dryusi);
                txt13.setText("");
            }
            else{
                if(w_userID[12].equals(userid)){
                    button13.setImageResource(R.drawable.my);
                }
                else {
                    button13.setImageResource(R.drawable.dryu);
                }
                txt13.setText(washerEND[12].substring(5,16));
                button13.setEnabled(false);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}