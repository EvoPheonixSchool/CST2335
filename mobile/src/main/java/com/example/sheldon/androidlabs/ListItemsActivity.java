package com.example.sheldon.androidlabs;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.data;

public class ListItemsActivity extends AppCompatActivity {
    protected final String ACTIVITY_NAME = "ListItems";
    private ImageButton imageB;
    private Switch switch1;
    private  CharSequence text;
    private Toast toast;
    private int duration;
    private CheckBox cb;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        imageB = (ImageButton) findViewById(R.id.imageButton);
        switch1 = (Switch) findViewById(R.id.switch1);
        cb = (CheckBox) findViewById(R.id.checkBox2);


        imageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);

                }

            }

        });

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = "";
                duration = Toast.LENGTH_LONG;

                if(switch1.isChecked()){
                    text = "Switch is On";
                    duration = Toast.LENGTH_SHORT;
                }else{
                    text = "Switch is Off";
                    duration = Toast.LENGTH_LONG;
                }


                toast = Toast.makeText(ListItemsActivity.this , text, duration); //this is the ListActivity
                toast.show(); //display your message box
            }
        });

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(ListItemsActivity.this);
                builder.setMessage(R.string.dialogmessage);
                builder.setTitle("Make a choice");
                builder.setPositiveButton(R.string.posmes,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent resultIntent = new Intent(  );
                        resultIntent.putExtra("Response", "My information to share");
                        setResult(ListItemsActivity.RESULT_OK, resultIntent);
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.negmes, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(ACTIVITY_NAME,"OK clicked");
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageB.setImageBitmap(imageBitmap);
        }
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
