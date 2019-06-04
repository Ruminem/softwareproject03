package com.example.pssin.auction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategorymanagementActivity extends AppCompatActivity {
    //상속받아본다 만들어둔 클래스를
    private ListView listView;
    private categoryListAdapter adapter;
    private List<Category> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_category);
        Intent intent = getIntent();
        //초기화를 해줘야지 실행이된다
        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<Category>();

       // userList.add(new Category("1","2","3","4","5","6","7"));
       // userList.add(new Category("1","2","3","4","5","6","7"));
        adapter = new categoryListAdapter(getApplicationContext(),userList);
        listView.setAdapter(adapter);
        //userList.add(new Category("1","2","3","4","5","6","7"));

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            String a = String.valueOf(jsonArray.length());
            int lengthJS = jsonArray.length();
            int count =0;

            String boardNumber,id,title,category,tradeLocation,auctionStartTime,auctionTimeLimit;
            while(count<lengthJS)
            {

                JSONObject object = jsonArray.getJSONObject(count);

                boardNumber = object.getString("boardNumber");
                id = object.getString("id");
                title = object.getString("title");
                category = object.getString("category");
                tradeLocation = object.getString("tradeLocation");
                auctionStartTime = object.getString("auctionStartTime");
                auctionTimeLimit = object.getString("auctionTimeLimit");
                Category user = new Category(boardNumber,id,title,category,tradeLocation,auctionStartTime,auctionTimeLimit);

                userList.add(user);
                count++;

            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
