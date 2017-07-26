package com.example.doma.talithakoum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class showHowTo extends AppCompatActivity {

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_how_to);

        final Animation anima_showing = AnimationUtils.loadAnimation(this, R.anim.appearing);

        final ArrayList<String> scriptList = new ArrayList<>();
        scriptList.add("작은 성공부터 시작하라. 성공에 익숙해지면 무슨 목표든지 할 수 있다는 자신감이 생긴다." +
                "- 데일 카네기");
        scriptList.add("아무말이나");
        scriptList.add("집어넣어 봅시다.");

        scriptList.add("집어넣어 봅시다라라라ㅏㄹ.");
        scriptList.add("집어넣어 봅시다.1123");
        scriptList.add("시작해볼까요?");

        final TextView textView_show = (TextView) findViewById(R.id.textView_show);

        textView_show.setText(scriptList.get(0));
        textView_show.startAnimation(anima_showing);

        View showView = (View) findViewById(R.id.show);
        showView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                i++;
                if (i < scriptList.size() ) {

                    textView_show.setText(scriptList.get(i));
                    textView_show.startAnimation(anima_showing);
                }

                if (i == scriptList.size()) {

                    i = 0;
                    Intent intent = new Intent(showHowTo.this, add_bigHabit.class);
                    startActivity(intent);
                }


                return false;
            }
        });
        TextView Textview_skip = (TextView) findViewById(R.id.imageButton_skip);
        Textview_skip.startAnimation(anima_showing);
        Textview_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showHowTo.this, add_bigHabit.class);
                startActivity(intent);
            }
        });
    }
}
