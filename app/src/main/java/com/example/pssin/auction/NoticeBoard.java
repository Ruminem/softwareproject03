package com.example.pssin.auction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class NoticeBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        final EditText a2 = (EditText) findViewById(R.id.a2);
        final EditText c2 = (EditText) findViewById(R.id.c2);
        final EditText d2 = (EditText) findViewById(R.id.d2);
        final EditText f2 = (EditText) findViewById(R.id.f2);

        final Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent NoticeBoardIntent = new Intent(NoticeBoard.this, NoticeBoard2.class);
                NoticeBoard.this.startActivity(NoticeBoardIntent);
            }
        });
    }
}

