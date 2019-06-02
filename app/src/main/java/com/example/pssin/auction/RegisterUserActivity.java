package com.example.pssin.auction;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText phoneText = (EditText) findViewById(R.id.phoneText);
        final EditText emailText = (EditText) findViewById(R.id.emailText);

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
    }
}

