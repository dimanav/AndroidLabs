package com.example.laba2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private static final String FileName = "FILENAME";
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    int theme = 2;
    DatabaseHandler db = new DatabaseHandler(this);
   // EditText et = (EditText) findViewById(R.id.editTextTextPersonName);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences(FileName, MODE_PRIVATE);
        editor = sharedPref.edit();
        theme = sharedPref.getInt("theme", 1);
        if (theme == 1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }
        if (theme == 2){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }


    }


    @Override
    protected void onStart() {
        super.onStart();

        Toast toast = Toast.makeText(MainActivity.this,
                "Активити запущена!", Toast.LENGTH_SHORT);
        toast.show();

    }
    @Override
    protected void onStop(){
        super.onStop();
        String Name = ((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString();
        editor.putString("name", Name);
        editor.apply();

    }

    @Override
    protected  void onResume(){
        super.onResume();
        ((EditText)findViewById(R.id.editTextTextPersonName)).setText(sharedPref.getString("name",""));
    }

    public void onClick(View v) {
        boolean check;
        switch (v.getId()) {
            case R.id.btn_add:

                check = true;
                String Password = ((EditText)findViewById(R.id.editTextTextPassword)).getText().toString();
                List<User> users = db.getAllUsers();
                if((Password.matches("^[a-zA-Z\\d]+$")) && (Password.length() > 4) && (Password.length() < 20)) {
                    for (User usr : users) {
                        if ((((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString().equals(usr.getLogin()))){
                            Toast toast = Toast.makeText(MainActivity.this,
                                    "Это имя уже занято!", Toast.LENGTH_SHORT);
                            toast.show();
                            check = false;

                        }
                    }
                    if(check){
                        db.addUser(new User(((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString(), ((EditText)findViewById(R.id.editTextTextPassword)).getText().toString()));
                        Toast addUser = Toast.makeText(MainActivity.this,
                                "Пользователь добавлен", Toast.LENGTH_SHORT);
                        addUser.show();
                    }
                }
                else {
                    Toast err = Toast.makeText(MainActivity.this,
                            "Недопустимый пароль", Toast.LENGTH_SHORT);
                    err.show();
                    break;
                }

                break;
            case R.id.btn_load:
              //  new Thread(new Runnable() {
                //    @Override
                  //  public void run() {
                        FragmentManager manager = getSupportFragmentManager();
                        MyDialogFragment myDialogFragment = new MyDialogFragment();
                        myDialogFragment.show(manager, "myDialog");
                    //}
                //}).start();



                break;
            case R.id.btn_del:
                check = false;
                List<User> user = db.getAllUsers();
                for (User usr : user) {
                    if ((((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString().equals(usr.getLogin()))){
                        check = true;
                        break;
                    }

                }
                if(check)
                {
                    db.delUser(((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString());
                    Toast addUser = Toast.makeText(MainActivity.this,
                            "Пользователь удалён", Toast.LENGTH_SHORT);
                    addUser.show();
                    break;
                }
                else {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Пользователя не существует", Toast.LENGTH_SHORT);
                    toast.show();
                }

                break;

            default:
                break;
        }
    }

    public void startNewActivity(View v){
        Intent intent = new Intent(this, SecondActivity.class);
        String Name = ((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString();
        String Pass = ((EditText)findViewById(R.id.editTextTextPassword)).getText().toString();
        List<User> users = db.getAllUsers();

        intent.putExtra("name", Name);

        for (User usr : users) {
            if ((Name.equals(usr.getLogin())) && (Pass.equals(usr.getPass()))) {
                intent.putExtra("user_name", usr.getLogin());
                startActivity(intent);
                break;
            }

        }

    }




}



