package com.example.patin.usuariocanchas.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patin.usuariocanchas.Item.ContactItem;
import com.example.patin.usuariocanchas.Item.SportItem;
import com.example.patin.usuariocanchas.R;

import java.util.ArrayList;

public class AdapterSport extends BaseAdapter {
    private Activity activity;
    private ArrayList<SportItem> sportItems;

    public AdapterSport(Activity activity, ArrayList<SportItem> sportItems) {
        this.activity = activity;
        this.sportItems = sportItems;
    }

    @Override
    public int getCount() {
        return sportItems.size();
    }

    @Override
    public Object getItem(int position) {
        return this.sportItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.list_view_sport, null);
        }

       SportItem sportItem = sportItems.get(position);

        TextView title = (TextView) v.findViewById(R.id.name_list_view_sport_item);
        title.setText(sportItem.getName());

        ImageView imagen = (ImageView) v.findViewById(R.id.img_list_view_sport_item);
        imagen.setImageDrawable(sportItem.getImagen());

        return v;
    }
}
