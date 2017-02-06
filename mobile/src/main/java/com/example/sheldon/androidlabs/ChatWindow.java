package com.example.sheldon.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    protected final String ACTIVITY_NAME = "ChatWindow";
    private ListView ls;
    private EditText et;
    private Button butSend;
    private ArrayList<String> chatLog;
    private ChatDatabaseHelper cdb;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues cv;
    private String[] sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        ls = (ListView) findViewById(R.id.chatList);
        et = (EditText) findViewById(R.id.chatText);
        butSend = (Button)findViewById(R.id.sendBut);
        chatLog = new ArrayList<>();
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        ls.setAdapter(messageAdapter);
        sa = new String[]{cdb.KEY_ID, cdb.KEY_MESSAGE};
        cv = new ContentValues();
        cdb = new ChatDatabaseHelper(getApplicationContext());
        db = cdb.getWritableDatabase();
        cursor = db.query(cdb.TABLE_NAME,sa,null,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString( cursor.getColumnIndex( cdb.KEY_MESSAGE) ) );
            Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cursor.getColumnCount() );
            chatLog.add(cursor.getString(cursor.getColumnIndex(cdb.KEY_MESSAGE)));
            cursor.moveToNext();
        }

        if(cursor.getColumnCount() > 0) {
            for (int i = 1; i < cursor.getColumnCount(); i++) {
                Log.i(ACTIVITY_NAME, cursor.getColumnName(i));
            }
        }

        cursor.close();


        butSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.put(cdb.KEY_MESSAGE,et.getText().toString());
                chatLog.add(et.getText().toString());
                db.insert(cdb.TABLE_NAME,cdb.KEY_MESSAGE,cv);
                messageAdapter.notifyDataSetChanged();
                et.setText("");

            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        db.close();
        cdb.close();

    }


    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context cnt) {
            super(cnt, 0);
        }

        public int getCount(){
            return chatLog.size();
        }

        public String getItem(int pos){
            return chatLog.get(pos);
        }

        public View getView(int pos, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            View result = null;
            if(pos%2 == 0){
                result = inflater.inflate(R.layout.chat_row_incoming,null);
            }else{
                result = inflater.inflate(R.layout.chat_row_outgoing,null);
            }

            TextView message = (TextView) result.findViewById(R.id.messageText);
            message.setText(getItem(pos));

            return result;
        }
    }

}

