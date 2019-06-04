package com.example.pssin.auction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SubMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_main);

        final Button subcategoryMotorCycle = (Button)findViewById(R.id.subCategoryButtonMotorCycle);

        subcategoryMotorCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent motorIntent = new Intent(SubMainActivity.this,SubCategoryMotorCycle.class);
                SubMainActivity.this.startActivity(motorIntent);
            }
        });
    }
}
