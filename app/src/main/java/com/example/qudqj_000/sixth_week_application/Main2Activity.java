package com.example.qudqj_000.sixth_week_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    EditText et1, et2, et3, et4, et5, et6;
    RadioButton rb2, rb1, rb3;
    int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        program();
    }

    void program() {
        et1 = (EditText) findViewById(R.id.resName);
        et2 = (EditText) findViewById(R.id.phoneNum);
        et3 = (EditText) findViewById(R.id.menu1);
        et4 = (EditText) findViewById(R.id.menu2);
        et5 = (EditText) findViewById(R.id.menu3);
        et6 = (EditText) findViewById(R.id.homepageUrl);

        rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            if (rb1.isChecked()) {
                category = R.drawable.chicken;
            } else if (rb2.isChecked()) {
                category = R.drawable.pizza;
            } else {
                category = R.drawable.spaghetti;
            }
            Restaurants rs = new Restaurants(et1.getText().toString(), category, et2.getText().toString(),
                    et3.getText().toString(), et4.getText().toString(), et5.getText().toString(), et6.getText().toString());
            Intent intent = getIntent();
            intent.putExtra("All_data", rs);
            String date = getTime();
            intent.putExtra("date", date);
            setResult(RESULT_OK,intent);
            finish();
        }
    }


    String getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(date);
        return format;
    }
}
