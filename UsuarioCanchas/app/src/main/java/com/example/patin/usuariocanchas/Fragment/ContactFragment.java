package com.example.patin.usuariocanchas.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patin.usuariocanchas.R;

public class ContactFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("Fragment","iNICIALIZO");
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
}
