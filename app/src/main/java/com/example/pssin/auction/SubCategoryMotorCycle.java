package com.example.pssin.auction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SubCategoryMotorCycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_motor_cycle);

        final Button salebtn = (Button)findViewById(R.id.salebtn);

        salebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saleIntent = new Intent(SubCategoryMotorCycle.this,NoticeBoard.class);
                SubCategoryMotorCycle.this.startActivity(saleIntent);
            }
        });
    }
    }

