package com.example.laba2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.List;

public class MyDialogFragment extends DialogFragment {


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatabaseHandler db = new DatabaseHandler(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Список User");
        List<User> users = db.getAllUsers();
        String log = "";
        for (User usr : users) {
            log += "Id: "+usr.getID()+" ,Login: " + usr.getLogin() + " ,Password: " + usr.getPass() + "\n";
        }
        builder.setMessage(log);
        builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Закрываем диалоговое окно
                dialog.cancel();
            }
        });
        return builder.create();
    }

}
