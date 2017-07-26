package com.example.doma.talithakoum;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class add_bigHabit extends AppCompatActivity {

    String howto;
    String frequency;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bighabit);

        final EditText editText_name =(EditText) findViewById(R.id.editText_addh_name);


        ImageButton imageButton_next =(ImageButton) findViewById(R.id.imageButton_addbh_next);
        imageButton_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_bigHabit.this, add_smallHabit.class);
                startActivity(intent);
            }
        });

        switchVisibility(editText_name);
        switchVisibility(imageButton_next);

    }

    public void switchVisibility(View view){
        if(view.getVisibility() == View.VISIBLE){
        view.setVisibility(View.INVISIBLE);}else{
            view.setVisibility(View.VISIBLE);
        }

    }




}
