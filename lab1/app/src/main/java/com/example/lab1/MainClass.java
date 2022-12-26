package com.example.lab1;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainClass extends Activity {
    int button_clicks1 = 0;
    int button_clicks2 = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloact);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        TextView tv = findViewById(R.id.tv1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setText("НАЖАТО!");
                button_clicks1++;
                tv.setText("Кнопка 1: " +button_clicks1 + " Кнопка 2: " + button_clicks2);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_clicks2++;
                tv.setText("Кнопка 1: " + button_clicks1 + " Кнопка 2: " + button_clicks2);
            }
        });


    }

}
