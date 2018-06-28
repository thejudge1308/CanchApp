package com.example.patin.usuariocanchas.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patin.usuariocanchas.Item.ServiceItem;
import com.example.patin.usuariocanchas.Item.SportItem;
import com.example.patin.usuariocanchas.Model.Service;
import com.example.patin.usuariocanchas.R;

import java.util.ArrayList;

public class AdapterService extends BaseAdapter {
    private Activity activity;
    private ArrayList<ServiceItem> serviceItems;

    public AdapterService(Activity activity, ArrayList<ServiceItem> serviceItems) {
        this.activity = activity;
        this.serviceItems = serviceItems;
    }

    @Override
    public int getCount() {
        return this.serviceItems.size();
    }

    @Override
    public Object getItem(int position) {
        return this.serviceItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.list_view_service, null);
        }

        ServiceItem serviceItem = serviceItems.get(position);

        
        return v;
    }
}
