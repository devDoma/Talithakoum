package com.example.doma.talithakoum;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by doma on 2017-04-27.
 */

public class CustomDialog  extends Dialog {

    private Context mContext;
    boolean switchcolor =false;
    boolean save = false;
    int hour;
    int minute;


    public CustomDialog(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cutom_dialog_view);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;//흐릿한 정도

        //다이얼로그 크기 설정

        lpWindow.copyFrom(getWindow().getAttributes());
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        DisplayMetrics dm = getWindow().getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        lpWindow.width = (int) Math.round(width*0.9);
        lpWindow.height = (int) Math.round( height*0.75);
        getWindow().setAttributes(lpWindow);



        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minutei) {
                hour = hourOfDay;
                minute = minutei;
            }
        });
        ImageButton imageButton_check = (ImageButton) findViewById(R.id.imageButton_check);
        ImageButton imageButton_close = (ImageButton) findViewById(R.id.imageButton_close);

        imageButton_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save = true;

               dismiss();
            }
        });

        imageButton_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save = false;
                dismiss();
            }
        });



    }

    void wdaybuttonSetter(final ImageButton imageButton, final Context context){
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchcolor){
                    imageButton.setColorFilter(ContextCompat.getColor(context,R.color.white));
                    switchcolor = !switchcolor;
                }else{
                    imageButton.setColorFilter(ContextCompat.getColor(context,R.color.black));
                    switchcolor = !switchcolor;
                }
            }
        });
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }
}