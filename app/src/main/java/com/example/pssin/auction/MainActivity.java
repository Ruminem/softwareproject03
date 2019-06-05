package com.example.pssin.auction;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton categoryFixButton = (ImageButton) findViewById(R.id.categoryFixButton);
        final ImageButton categoryUsedButton = (ImageButton) findViewById(R.id.categoryUsedButton);
        final ImageButton categoryNewButton = (ImageButton) findViewById(R.id.categoryNewButton);

        categoryFixButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent loginIntent = getIntent();
                Intent fixIntent = new Intent(MainActivity.this, SubMainActivity.class);
                fixIntent.putExtra("accountID", loginIntent.getStringExtra("accountID"));
                MainActivity.this.startActivity(fixIntent);
            }
        });
    }
}