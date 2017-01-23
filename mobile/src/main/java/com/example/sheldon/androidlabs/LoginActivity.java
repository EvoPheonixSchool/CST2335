package com.example.sheldon.androidlabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import static com.example.sheldon.androidlabs.R.string.email;

public class LoginActivity extends AppCompatActivity {
    protected final String ACTIVITY_NAME = "Login";
    private Button lbut;
    private EditText et;
    private SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
    private SharedPreferences.Editor edit = sp.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        lbut = (Button) findViewById(R.id.LoginButton);
        et = (EditText) findViewById(R.id.emailtext);

        lbut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                edit.putString("defaultemail" , et.getText().toString());
                edit.commit();
            }
        });
    }

    public void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"is onResume");
    }

    public void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME,"is onStart");

        et.setText(sp.getString("defaultemail","Email@domain.tech"));


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
