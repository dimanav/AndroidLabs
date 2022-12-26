package com.example.laba2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Settings extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private static final String FileName = "FILENAME";

    DatabaseHandler db = new DatabaseHandler(this);
    String UserName;

    int theme = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Bundle arguments_in = getIntent().getExtras();
        UserName = arguments_in.get("user_name").toString();
        sharedPref = getSharedPreferences(FileName, MODE_PRIVATE);
        editor = sharedPref.edit();

        Switch themeSwitch = findViewById(R.id.switch1);
        theme = sharedPref.getInt("theme", 1);

        if (theme == 1){
            themeSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }
        if (theme == 2){
            themeSwitch.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    theme = 1;
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                } else {
                    theme = 2;
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        editor = sharedPref.edit();
        editor.putInt("theme", theme);
        editor.apply();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bth_chg_pass:
                db.updPass(((EditText)findViewById(R.id.editTextTextPassword2)).getText().toString(), UserName);
                Toast toast = Toast.makeText(Settings.this,
                        "Пароль изменён", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }
}