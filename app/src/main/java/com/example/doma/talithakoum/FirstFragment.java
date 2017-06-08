package com.example.doma.talithakoum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by doma on 2017-05-06.
 */
public class FirstFragment extends Fragment
{
    public FirstFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_first, container, false);

        ImageButton imageButton = (ImageButton) layout.findViewById(R.id.imageButton_to_add_habit);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layout.getContext(), add_bigHabit.class);
                startActivity(intent);
            }
        });

        return layout;
    }
}