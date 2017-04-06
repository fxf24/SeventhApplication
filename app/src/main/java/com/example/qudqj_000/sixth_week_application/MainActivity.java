package com.example.qudqj_000.sixth_week_application;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> resName = new ArrayList<>();
    ArrayList<Restaurants> restaurants = new ArrayList<>();
    ArrayAdapter<String> adapter;
    final int REQUEST_RESTAURANT = 1;
    String date;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListView();
    }

    void setListView(){
        t1 = (TextView)findViewById(R.id.list_title);
        listView = (ListView)findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resName);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("show_Data",restaurants.get(position));
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int index = position;

                AlertDialog.Builder dlg = new AlertDialog.Builder(parent.getContext());
                dlg.setTitle("삭제확인")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("선택한 맛집을 정말 삭제할거에요?")
                        .setNegativeButton("취소", null)
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                resName.remove(index);
                                restaurants.remove(index);
                                adapter.notifyDataSetChanged();
                                int size = resName.size();
                                t1.setText("맛집 리스트("+String.valueOf(size)+"개)");
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_RESTAURANT && resultCode==RESULT_OK){
            Restaurants rs = data.getParcelableExtra("All_data");
            date = data.getStringExtra("date");
            restaurants.add(rs);
            resName.add(rs.getName());
            adapter.notifyDataSetChanged();
            int size = resName.size();
            t1.setText("맛집 리스트("+String.valueOf(size)+"개)");
        }
    }

    public void onClick(View v){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, REQUEST_RESTAURANT);
    }

}
