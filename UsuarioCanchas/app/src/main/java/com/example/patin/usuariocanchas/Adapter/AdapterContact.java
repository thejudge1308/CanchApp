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
import com.example.patin.usuariocanchas.R;

import java.util.ArrayList;

public class AdapterContact extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<ContactItem> contacts;

    public AdapterContact(Activity activity, ArrayList<ContactItem> contacts){
        this.activity = activity;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return this.contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contacts.get(position);
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
            v = inf.inflate(R.layout.list_view_contact, null);
        }

        ContactItem contactItem = contacts.get(position);

        TextView title = (TextView) v.findViewById(R.id.name_list_view_contact_item);
        title.setText(contactItem.getName());

        TextView description = (TextView) v.findViewById(R.id.email_list_view_contact_item);
        description.setText(contactItem.getEmail());

        ImageView imagen = (ImageView) v.findViewById(R.id.img_list_view_contact_item);
        imagen.setImageDrawable(contactItem.getImagen());

        return v;
    }
}
