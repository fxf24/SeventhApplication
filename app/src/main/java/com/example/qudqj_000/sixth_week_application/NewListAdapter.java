package com.example.qudqj_000.sixth_week_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by qudqj_000 on 2017-04-13.
 */

public class NewListAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Restaurants> listDatas = new ArrayList<>();
    ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    public NewListAdapter(Context context, ArrayList<Restaurants> listDatas) {
        this.context = context;
        this.listDatas = listDatas;
    }

    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        }
        TextView t1 = (TextView) convertView.findViewById(R.id.resName);
        TextView t2 = (TextView) convertView.findViewById(R.id.phone_num);
        ImageView i1 = (ImageView) convertView.findViewById(R.id.img);
        CheckBox c1 = (CheckBox)convertView.findViewById(R.id.checkbox1);
        checkBoxes.add(c1);

        t1.setText(listDatas.get(position).getName());
        t2.setText(listDatas.get(position).getPhoneNum());
        i1.setImageResource(listDatas.get(position).getCategory());
        return convertView;
    }

    Comparator<Restaurants> nameAsc = new Comparator<Restaurants>() {
        @Override
        public int compare(Restaurants o1, Restaurants o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    Comparator<Restaurants> categoryAsc = new Comparator<Restaurants>() {
        @Override
        public int compare(Restaurants o1, Restaurants o2) {
            if(o1.getCategory()<o2.getCategory())
                return -1;
            else if(o1.getCategory()>o2.getCategory())
                return 1;
            else
                return 0;
        }
    };

    public void setNameAsc(){
        Collections.sort(listDatas,nameAsc);
        this.notifyDataSetChanged();
    }

    public void setCategoryAsc(){
        Collections.sort(listDatas,categoryAsc);
        this.notifyDataSetChanged();
    }

    public void setCheckBox(){
        int size = checkBoxes.size();
        for(int i=0; i<size;i++){
            checkBoxes.get(i).setVisibility(View.VISIBLE);
        }
        this.notifyDataSetChanged();
    }

    public void goneCheckBox(){
        int size = checkBoxes.size();
        for(int i=size-1; i>=0;i--){
            if(checkBoxes.get(i).isChecked()){
                listDatas.remove(i);
                this.notifyDataSetChanged();
            }
            checkBoxes.get(i).setVisibility(View.GONE);
        }
        this.notifyDataSetChanged();
    }

    public void delete(){

    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
