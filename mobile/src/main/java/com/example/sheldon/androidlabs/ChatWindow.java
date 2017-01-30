package com.example.sheldon.androidlabs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private ListView ls;
    private EditText et;
    private Button butSend;
    private ArrayList<String> chatLog;

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

        butSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatLog.add(et.getText().toString());
                messageAdapter.notifyDataSetChanged();
                et.setText("");
            }
        });
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

