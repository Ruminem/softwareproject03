package com.example.pssin.auction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SubMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_main);

        final ImageButton subcategoryMotorCycle = (ImageButton)findViewById(R.id.subCategoryButtonMotorCycle);

        subcategoryMotorCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent MainIntent = getIntent();

                Intent motorIntent = new Intent(SubMainActivity.this,SubCategoryMotorCycle.class);
                motorIntent.putExtra("accountID", MainIntent.getStringExtra("accountID"));
                SubMainActivity.this.startActivity(motorIntent);
            }
        });
    }
}
