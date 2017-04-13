package com.example.qudqj_000.sixth_week_application;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Restaurants> restaurants = new ArrayList<>();
    NewListAdapter adapter;
    final int REQUEST_RESTAURANT = 1;
    String date;
    Button b1;
    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.select);
        et1 = (EditText)findViewById(R.id.search);

        setListView();
        textFilter();
    }

    void setListView() {
        listView = (ListView) findViewById(R.id.list);
        adapter = new NewListAdapter(this, restaurants);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("show_Data", restaurants.get(position));
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }

    void textFilter(){
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = s.toString();
                if(search.length()>0)
                    listView.setFilterText(search);
                else
                    listView.clearTextFilter();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RESTAURANT && resultCode == RESULT_OK) {
            Restaurants rs = data.getParcelableExtra("All_data");
            date = data.getStringExtra("date");
            restaurants.add(rs);
            adapter.notifyDataSetChanged();
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.add) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivityForResult(intent, REQUEST_RESTAURANT);
        }
        else if (v.getId() == R.id.name_sort) {
            adapter.setNameAsc();
        }
        else if (v.getId() == R.id.category) {
            adapter.setCategoryAsc();
        }
        else if (v.getId() == R.id.select) {
            if (b1.getText().toString().equals("선택")) {
                b1.setText("삭제");
                adapter.setCheckBox();
            }
            else {
                b1.setText("선택");
                adapter.delete();
                adapter.goneCheckBox();
            }
        }
    }

}
