package com.example.sheldon.androidlabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Log;

public class ListItemsActivity extends AppCompatActivity {
    protected final String ACTIVITY_NAME = "ListItems";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
    }

    public void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"is onResume");
    }

    public void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME,"is onStart");
    }

    public void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME,"is onPause");
    }

    public void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME,"is onStop");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"is onDestroy");
    }
}
