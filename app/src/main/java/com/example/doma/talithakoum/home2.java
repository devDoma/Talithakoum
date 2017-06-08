package com.example.doma.talithakoum;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class home2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home22);



        ImageButton imageButton_to_add_habit = (ImageButton) findViewById(R.id.imageButton_to_add_habit);
        imageButton_to_add_habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, add_bigHabit.class);
                startActivity(intent);
            }
        });
    }


}
