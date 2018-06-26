package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.patin.usuariocanchas.Activities.CreateMatchActivity;
import com.example.patin.usuariocanchas.R;


public class HomeFragment extends Fragment {

    private Button newMatch;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container,false);


        this.newMatch = (Button) rootView.findViewById(R.id.reservarcancha_button_fragmenthome);
        this.newMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CreateMatchActivity.class);
                startActivity(intent);
            }
        });




        return rootView;




    }


}
