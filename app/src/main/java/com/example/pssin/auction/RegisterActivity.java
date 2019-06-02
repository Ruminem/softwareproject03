package com.example.pssin.auction;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final RadioButton userRadioButton = (RadioButton) findViewById(R.id.userRadioButton);
        final RadioButton ownerRadioButton = (RadioButton) findViewById(R.id.ownerRadioButton);
        final Button selectButton = (Button) findViewById(R.id.selectButton);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userRadioButton.isChecked())
                {
                    Log.i(this.getClass().getName(), "사용자 radio 버튼을 누름");
                    Intent Intent = new Intent(RegisterActivity.this, RegisterUserActivity.class);
                    RegisterActivity.this.startActivity(Intent);
                }
                else if(ownerRadioButton.isChecked())
                {
                    Log.i(this.getClass().getName(), "사업자 radio 버튼을 누름");
                    Intent Intent = new Intent(RegisterActivity.this, RegisterOwnerActivity.class);
                    RegisterActivity.this.startActivity(Intent);
                }
            }
        });
    }
}

