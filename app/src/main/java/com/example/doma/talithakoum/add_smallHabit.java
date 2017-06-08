package com.example.doma.talithakoum;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class add_smallHabit extends AppCompatActivity {

    public ArrayList<String> parentList; // ExpandableListView의 Parent 항목이 될 List 변수 선언
    public ArrayList<data_habit> fruit; // ExpandableListView의 Child 항목이 될 List를 각각 선언
    public ArrayList<data_habit> vegetables;
    public ArrayList<data_habit> etc;
    public HashMap<String, ArrayList<data_habit>> childList; // 위 ParentList와 ChildList를 연결할 HashMap 변수 선언


    hsAdapter hsadapter;
    ExpandableListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_smallhabit);


        parentList = new ArrayList<String>();
        parentList.add("과일");
        data_habit apple = new data_habit("바보", 1, 20);
        data_habit pair = new data_habit("멍청이", 2, 30);
        data_habit persimmon = new data_habit("감", 3, 20);
        fruit = new ArrayList<data_habit>();
        fruit.add(apple);
        fruit.add(pair);
        fruit.add(persimmon);


        childList = new HashMap<String, ArrayList<data_habit>>();
        childList.put(parentList.get(0), fruit);

        listView = (ExpandableListView) findViewById(R.id.list_habit_specify);
        hsadapter = new hsAdapter(this, parentList, childList);
        listView.setAdapter(hsadapter);


        ImageButton imageButton_add = (ImageButton) findViewById(R.id.imageButton_h_specify_add);
        imageButton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hsadapter.add_empty();

                hsadapter.notifyDataSetChanged();
            }
        });

        ImageButton imageButton_save = (ImageButton) findViewById(R.id.imageButton_h_specify_save);
        imageButton_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }


}
