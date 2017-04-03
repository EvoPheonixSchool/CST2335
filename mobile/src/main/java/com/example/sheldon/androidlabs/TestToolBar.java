package com.example.sheldon.androidlabs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolBar extends AppCompatActivity {
    private String snacktext;
    AlertDialog.Builder builder;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tool_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        snacktext = "This is a tasty snackbar";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, snacktext, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu,m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        String option = mi.getTitle().toString();
        Log.i("toolbat",option);
        switch (option){
            case "Bot":
                Log.i("Toolbar","Bot");
                Snackbar.make(this.findViewById(android.R.id.content), "You selected item 1", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case "Hand":
                Log.i("Toolbar","Hand");
                builder = new AlertDialog.Builder(TestToolBar.this);
                builder.setTitle("Do you wish to return?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case "Ghast":
                Log.i("Toolbar","Ghast");
                builder = new AlertDialog.Builder(TestToolBar.this);
                LayoutInflater inflater = this.getLayoutInflater();
                View v = inflater.inflate(R.layout.activity_custom_dialog,null);
                builder.setView(v);
                et = (EditText) v.findViewById(R.id.snacktext);
                builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        snacktext = et.getText().toString();
                        Log.i("edit text"," " + et.getText().toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                builder.create();
                builder.show();
                break;
            case "About":
                Log.i("Toolbar","About");
                Toast toast = Toast.makeText(TestToolBar.this,"Version 1.0, by Sheldon McGrath",Toast.LENGTH_LONG);
                toast.show();
                break;
        }
        return true;
    }

}
