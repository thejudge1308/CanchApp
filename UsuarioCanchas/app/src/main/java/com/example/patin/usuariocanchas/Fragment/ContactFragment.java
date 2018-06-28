package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.Activities.AddFriendActivity;
import com.example.patin.usuariocanchas.Activities.CreateMatchActivity;
import com.example.patin.usuariocanchas.Activities.HomeActivity;
import com.example.patin.usuariocanchas.Activities.LoginActivity;
import com.example.patin.usuariocanchas.Adapter.AdapterContact;
import com.example.patin.usuariocanchas.Item.ContactItem;
import com.example.patin.usuariocanchas.R;

import java.util.ArrayList;

public class ContactFragment extends Fragment{

    private  FloatingActionButton floatingActionButton;
    private ListView contactListView;
    private ArrayList<ContactItem> contactItems;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact, container,false);


        this.floatingActionButton =  (FloatingActionButton)rootView.findViewById(R.id.addcontact_fragment_contact);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddFriendActivity.class);
                startActivity(intent);

            }
        });

        this.contactListView = (ListView)rootView.findViewById(R.id.contact_listview_fragment_contact);

        Drawable image  = ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.ic_group);

        this.contactItems = new ArrayList<>();
        this.contactItems.add(new ContactItem("1", "2",image));
        this.contactItems.add(new ContactItem("1", "2",image));
        this.contactItems.add(new ContactItem("1", "2",image));
        AdapterContact adapterContact = new AdapterContact(getActivity(),this.contactItems);
        this.contactListView.setAdapter(adapterContact);

        this.contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Toast.makeText(getActivity().getApplicationContext(),pos+"",Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;

    }

}
