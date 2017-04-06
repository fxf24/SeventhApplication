package com.example.qudqj_000.sixth_week_application;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5, t6, t7;
    ImageView iv1;
    Restaurants rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main33);
        program();
    }

    void program(){
        t1 = (TextView)findViewById(R.id.resName);
        t2 = (TextView)findViewById(R.id.menu1);
        t3 = (TextView)findViewById(R.id.menu2);
        t4 = (TextView)findViewById(R.id.menu3);
        t5 = (TextView)findViewById(R.id.phoneNum);
        t6 = (TextView)findViewById(R.id.homepageUrl);
        t7 = (TextView)findViewById(R.id.RegDate);

        iv1 = (ImageView)findViewById(R.id.img);

        Intent intent = getIntent();
        rs = intent.getParcelableExtra("show_Data");
        String date = intent.getStringExtra("date");

        t1.setText(rs.getName());
        t2.setText(rs.getMenu1());
        t3.setText(rs.getMenu2());
        t4.setText(rs.getMenu3());
        t5.setText(rs.getPhoneNum());
        t6.setText(rs.getHomepage());
        t7.setText(date);

        if(rs.getCategory() == 1){
            iv1.setImageResource(R.drawable.chicken);
        }
        else if(rs.getCategory() == 2){
            iv1.setImageResource(R.drawable.pizza);
        }
        else{
            iv1.setImageResource(R.drawable.spaghetti);
        }


    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.imageView2:
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/" + rs.getPhoneNum()));
                startActivity(call);
                break;
            case R.id.imageView3:
                Intent view = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+rs.getHomepage()));
                startActivity(view);
                break;
        }

    }
}
