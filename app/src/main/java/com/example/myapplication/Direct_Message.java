package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Direct_Message extends AppCompatActivity {

    EditText tex_message;
    EditText text_number;
    EditText text_count;
    Button btn_manual;
    Button btn_sms;
    Button btn_whatsapp;
    Button btn_choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_message);

        tex_message = findViewById(R.id.editText1);
        text_number = findViewById(R.id.editText2);
        text_count = findViewById(R.id.editText3);

        btn_manual = findViewById(R.id.button1);
        btn_sms = findViewById(R.id.button2);
        btn_whatsapp = findViewById(R.id.button3);
        btn_choose = findViewById(R.id.button4);
    }
}