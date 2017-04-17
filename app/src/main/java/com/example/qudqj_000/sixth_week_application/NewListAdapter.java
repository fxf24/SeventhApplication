package com.example.qudqj_000.sixth_week_application;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    private Context context;
    private ArrayList<Restaurants> listDatas = new ArrayList<>();
    private ArrayList<Restaurants> filteredItemList = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    Filter listFilter;

    public NewListAdapter(Context context, ArrayList<Restaurants> listDatas) {
        this.context = context;
        this.listDatas = listDatas;
        this.filteredItemList = listDatas;
    }

    @Override
    public int getCount() {
        return filteredItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position);
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

        t1.setText(filteredItemList.get(position).getName());
        t2.setText(filteredItemList.get(position).getPhoneNum());
        i1.setImageResource(filteredItemList.get(position).getCategory());
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
            if (o1.getCategory() < o2.getCategory())
                return -1;
            else if (o1.getCategory() > o2.getCategory())
                return 1;
            else
                return 0;
        }
    };

    public void setNameAsc() {
        Collections.sort(filteredItemList, nameAsc);
        this.notifyDataSetChanged();
    }

    public void setCategoryAsc() {
        Collections.sort(filteredItemList, categoryAsc);
        this.notifyDataSetChanged();
    }

    public void setCheckBox() {

    }

    public int goneCheckBox(int index) {
        if (checkBoxes.get(index).isChecked()) {
            checkBoxes.remove(index);
            return index;
        } else {
            checkBoxes.get(index).setVisibility(View.GONE);
            return -1;
        }
    }

    public int getSize() {
        return checkBoxes.size();
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = listDatas;
                results.count = listDatas.size();
            } else {
                ArrayList<Restaurants> itemList = new ArrayList<>();
                for (Restaurants item : listDatas) {
                    if (item.getName().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        itemList.add(item);
                    }
                }
                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredItemList = (ArrayList<Restaurants>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
