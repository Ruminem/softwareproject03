package com.example.pssin.auction;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class RegisterUserActivity extends AppCompatActivity {
//--------------------이메일변수------------------
    LayoutInflater dialog; //LayoutInflater
    View dialogLayout; //layout을 담을 View
    Dialog authDialog; //dialog 객체

    TextView time_counter; //시간을 보여주는 TextView
    EditText emailAuth_number; //인증 번호를 입력 하는 칸
    Button emailAuth_btn; // 인증버튼
    CountDownTimer countDownTimer;
    final int MILLISINFUTURE = 300 * 1000; //총 시간 (300초 = 5분)
    final int COUNT_DOWN_INTERVAL = 1000; //onTick 메소드를 호출할 간격 (1초)
//-----------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText phoneText = (EditText) findViewById(R.id.phoneText);
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        //--------------------이메일 등록확인을 위한 버튼------------
        Button authBtn = (Button) findViewById(R.id.authBtn);

        //---------------------------------------------------------

        Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accountID = idText.getText().toString();
                String accountPassword = passwordText.getText().toString();
                String accountName = nameText.getText().toString();
                String accountPhone = phoneText.getText().toString();
                String accountEmail = emailText.getText().toString();
                String accountClass = "0";
                String accountCompanyName = "";
                String accountCompanyLocaiton = "";

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            Log.i(this.getClass().getName(),"시도" + response);
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.optBoolean("success", false);

                            if(success) {
                                Log.i(this.getClass().getName(),"성공!!");

                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUserActivity.this);
                                builder.setMessage("회원 등록에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();

                                Intent Intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
                                RegisterUserActivity.this.startActivity(Intent);
                            }
                            else
                            {
                                Log.i(this.getClass().getName(),"실패 ㅜㅜ");

                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUserActivity.this);
                                builder.setMessage("회원 등록에 실패했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        }
                        catch(JSONException e)
                        {
                            Log.e(this.getClass().getName(),"에러");
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequestUser = new RegisterRequest(accountID, accountPassword, accountName, accountPhone, accountEmail,
                                                                    accountClass, accountCompanyName, accountCompanyLocaiton,  responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterUserActivity.this);
                queue.add(registerRequestUser);
            }
        });

        //----------------------------- 이메일 인증버튼을 눌렀을때 함수 ------------
        authBtn.setOnClickListener(new View.OnClickListener() {
            String authentication = numberGen(6,1);//난수를 여기서 생성해야지 한번 만듬 oncreate만드니 버툰누를때마다 바뀜
            //난수 생성해서 인증번호에 넣음 이인증번호를 php로 메일로 싸줄거임
            //authEmail 로 이메일과 난수를 보내는거 구현하면됨
            //String email = authEmail.getText().toString();
            /////
            /////
            // php로 이메일과 난수를 싸주는거 구현


            /////////////////////////
            //////////////////////////
            public void countDownTimer() { //카운트 다운 메소드

                time_counter = (TextView) dialogLayout.findViewById(R.id.emailAuth_time_counter);
                //줄어드는 시간을 나타내는 TextView
                emailAuth_number = (EditText) dialogLayout.findViewById(R.id.emailAuth_number);
                //사용자 인증 번호 입력창
                emailAuth_btn = (Button) dialogLayout.findViewById(R.id.emailAuth_btn);
                //인증하기 버튼


                countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
                    @Override
                    public void onTick(long millisUntilFinished) { //(300초에서 1초 마다 계속 줄어듬)

                        long emailAuthCount = millisUntilFinished / 1000;
                        Log.d("Alex", emailAuthCount + "");

                        if ((emailAuthCount - ((emailAuthCount / 60) * 60)) >= 10) { //초가 10보다 크면 그냥 출력
                            time_counter.setText((emailAuthCount / 60) + " : " + (emailAuthCount - ((emailAuthCount / 60) * 60)));
                        } else { //초가 10보다 작으면 앞에 '0' 붙여서 같이 출력. ex) 02,03,04...
                            time_counter.setText((emailAuthCount / 60) + " : 0" + (emailAuthCount - ((emailAuthCount / 60) * 60)));
                        }

                        //emailAuthCount은 종료까지 남은 시간임. 1분 = 60초 되므로,
                        // 분을 나타내기 위해서는 종료까지 남은 총 시간에 60을 나눠주면 그 몫이 분이 된다.
                        // 분을 제외하고 남은 초를 나타내기 위해서는, (총 남은 시간 - (분*60) = 남은 초) 로 하면 된다.

                    }


                    @Override
                    public void onFinish() { //시간이 다 되면 다이얼로그 종료

                        authDialog.cancel();

                    }
                }.start();

                emailAuth_btn.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
//////////////////////


/////////////////////

                switch (v.getId()) {

                    case R.id.authBtn:

                        dialog = LayoutInflater.from(RegisterUserActivity.this);
                        dialogLayout = dialog.inflate(R.layout.auth_dialog, null); // LayoutInflater를 통해 XML에 정의된 Resource들을 View의 형태로 반환 시켜 줌
                        authDialog = new Dialog(RegisterUserActivity.this); //Dialog 객체 생성
                        authDialog.setContentView(dialogLayout); //Dialog에 inflate한 View를 탑재 하여줌
                        authDialog.setCanceledOnTouchOutside(false); //Dialog 바깥 부분을 선택해도 닫히지 않게 설정함.
                        authDialog.setOnCancelListener(null); //다이얼로그를 닫을 때 일어날 일을 정의하기 위해 onCancelListener 설정
                        authDialog.show(); //Dialog를 나타내어 준다.
                        countDownTimer();
                        /////////////////////////////////
                        Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try
                                {
                                    Log.i(this.getClass().getName(), "이메일 인증 시도");
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if(success) {
                                        //   Toast.makeText(RegisterActivity.this, "이메일보내기성공", Toast.LENGTH_SHORT).show();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUserActivity.this);
                                        Toast.makeText(RegisterUserActivity.this, "이메일을 성공적으로 보냈습니다", Toast.LENGTH_SHORT).show();
                                        //finish();
                                    }
                                    else
                                    {
                                        //Toast.makeText(RegisterActivity.this, "이메일보내기실패", Toast.LENGTH_SHORT).show();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUserActivity.this);
                                        builder.setMessage("이메일보내는것을 실패했습니다.")
                                                .setNegativeButton("다시 시도", null)
                                                .create()
                                                .show();
                                    }
                                }
                                catch(JSONException e)
                                {
                                    //  Toast.makeText(RegisterActivity.this, "에러", Toast.LENGTH_SHORT).show();
                                    Log.e(this.getClass().getName(), "이메일 인증 에러");

                                    e.printStackTrace();
                                }
                            }
                        };
                        String email = emailText.getText().toString();
                        ConfirmRequest confirmRequest = new ConfirmRequest(email, authentication, responseListener2);
                        RequestQueue queue2 = Volley.newRequestQueue(RegisterUserActivity.this);
                        queue2.add(confirmRequest);
                        ////////////////////////////////
                        break;

                    case R.id.emailAuth_btn : //다이얼로그 내의 인증번호 인증 버튼을 눌렀을 시
                        String email2 = emailText.getText().toString();
                        String user_answer = emailAuth_number.getText().toString();
                        //emailAuth_number.setText(email2); //이건 테스트할려고 버튼누를때마다 값머나오는지 확인할려고 쓴거임
                        if(user_answer.equals(authentication)){
                            Toast.makeText(RegisterUserActivity.this, "이메일 인증 성공", Toast.LENGTH_SHORT).show();
                            authDialog.cancel();//인증성공하면 다이얼로그창 닫기해준다^^
                        }else{

                            Toast.makeText(RegisterUserActivity.this, "인증번호가 틀렸습니다. 다시입력해주세요", Toast.LENGTH_SHORT).show();
                            //인증실패하면 다이얼로그창안꺼지고 다시입력하라고한다
                        }
                        break;



                }
            }

            public void onCancel(DialogInterface dialog) {
                Toast.makeText(RegisterUserActivity.this, "시간이완료되었습니다.", Toast.LENGTH_SHORT);
                countDownTimer.cancel();
            } //다이얼로그 닫을 때 카운트 다운 타이머의 cancel()메소드 호출

        });
        //-----------------------------------------------------------------------

    }
    //--------------------난수발생함수------------------
    public static String numberGen(int len, int dupCd ) {

        Random rand = new Random();
        String numStr = ""; //난수가 저장될 변수

        for(int i=0;i<len;i++) {

            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));

            if(dupCd==1) {
                //중복 허용시 numStr에 append
                numStr += ran;
            }else if(dupCd==2) {
                //중복을 허용하지 않을시 중복된 값이 있는지 검사한다
                if(!numStr.contains(ran)) {
                    //중복된 값이 없으면 numStr에 append
                    numStr += ran;
                }else {
                    //생성된 난수가 중복되면 루틴을 다시 실행한다
                    i-=1;
                }
            }
        }
        return numStr;
    }
    //-------------------------------------------------
}

