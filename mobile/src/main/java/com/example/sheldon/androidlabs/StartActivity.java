package com.example.sheldon.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    protected final String ACTIVITY_NAME = "StartActivity";
    private Button but;
    private Button butChat;
    private Button toolbar;
    private  CharSequence text;
    private Toast toast;
    private int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        but = (Button) findViewById(R.id.imabutton);
        butChat = (Button) findViewById(R.id.chatBut);
        toolbar = (Button) findViewById(R.id.toolbar);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent,5);
            }
        });
        butChat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME,"User clicked Start Chat");
                Intent i = new Intent(StartActivity.this,ChatWindow.class);
                startActivity(i);
            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartActivity.this,TestToolBar.class);
                startActivity(i);
            }
        });
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data){
        String messagePassed = data.getStringExtra("Response");
        String info = data.getStringExtra("My information to share");

        if((requestCode == 5)&& (messagePassed.equals(Activity.RESULT_OK) )){
            Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
            text = "ListItemsActivity passed: " + info;
            duration = Toast.LENGTH_LONG;
        }else{
            text = "ListItemsActivity Failed";
            duration = Toast.LENGTH_LONG;
        }

        toast = Toast.makeText(StartActivity.this , text, duration);
        toast.show();
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
