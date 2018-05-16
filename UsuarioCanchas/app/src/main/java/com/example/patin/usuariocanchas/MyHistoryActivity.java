package com.example.patin.usuariocanchas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MyHistoryActivity extends AppCompatActivity {
    private ListView historyListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);
        this.historyListView = (ListView)findViewById(R.id.history_listview_myhistoryactivity);
        
    }
}
