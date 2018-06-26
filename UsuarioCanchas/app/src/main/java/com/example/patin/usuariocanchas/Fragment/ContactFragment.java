package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.patin.usuariocanchas.R;

public class ContactFragment extends Fragment{

    private  FloatingActionButton floatingActionButton;
    private ListView contactListView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact, container,false);


        this.floatingActionButton =  (FloatingActionButton)rootView.findViewById(R.id.addcontact_fragment_contact);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("f","S");
            }
        });

        this.contactListView = (ListView)rootView.findViewById(R.id.contact_listview_fragment_contact);

        return rootView;

    }

}
