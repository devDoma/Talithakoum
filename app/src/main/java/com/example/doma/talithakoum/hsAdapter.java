package com.example.doma.talithakoum;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.doma.talithakoum.R.id.imageButton_alarm;

/**
 * Created by doma on 2017-04-27.
 */

public class hsAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> groupList = null;
    private ArrayList<data_habit> childList = null;
    private ChildListViewHolder mChildListViewHolder;

    private Context mContext;
    private HashMap<String, ArrayList<data_habit>> mChildHashMap;

    //    private ViewHolder viewHolder = null;
    private boolean switchColor = false;

    public hsAdapter(Context c, ArrayList<String> groupList,
                     HashMap<String, ArrayList<data_habit>> childHashMap) {
        super();
        this.mContext = c;
        this.groupList = groupList;
        this.childList = childList;
        this.mChildHashMap = childHashMap;
    }


//    @Override
//    public View getView(int position, View convertView, final ViewGroup parent) {
//
//        final Context context = parent.getContext();
//
//        if (convertView == null) {
//
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.childlist_item, parent, false);
//
//            final ImageButton imageButton_alarm = (ImageButton) convertView.findViewById(R.id.imageButton_alarm);
//            imageButton_alarm.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (switchColor) {
//                        imageButton_alarm.setColorFilter(ContextCompat.getColor(context, R.color.black));
//                        switchColor = !switchColor;
//                    } else {
//                        final CustomDialog customDialog = new CustomDialog(context);
//                        customDialog.show();
//                        customDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                            @RequiresApi(api = Build.VERSION_CODES.N)
//                            @Override
//                            public void onDismiss(DialogInterface dialog) {
//                                if (customDialog.save) {
//                                    imageButton_alarm.setColorFilter(ContextCompat.getColor(context, R.color.red));
//                                    switchColor = !switchColor;
//
//
//                                }
//                            }
//                        });
//
//                    }
//
//                }
//            });
//
//        }
//
//
//        return convertView;
//    }
//
//    public void addItem(int hour, int minute, String content) {
//        data_habit item = new data_habit();// 이러면 계속 새로운 데이터 로그 객체를 생성하는데 그건 괜차늠? ㅇㅇ 임시라서 괜차늠
//        item.setHour(hour);
//        item.setMinute(minute);
//        item.setContent(content);
//        childList.add(item);
//    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildHashMap.get(this.groupList.get(groupPosition)).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public data_habit getChild(int groupPosition, int childPosition) {
        return this.mChildHashMap.get(this.groupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater groupInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = groupInfla.inflate(R.layout.grouplist_item, parent, false);
        }


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        data_habit dataHabit = (data_habit) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater childInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childInfla.inflate(R.layout.childlist_item, null);

            mChildListViewHolder = new ChildListViewHolder();
            mChildListViewHolder.imageButton_alarm = (ImageButton) convertView.findViewById(imageButton_alarm);
            mChildListViewHolder.editText = (EditText) convertView.findViewById(R.id.editText_list_title);
            convertView.setTag(mChildListViewHolder);

        }else{
            mChildListViewHolder = (ChildListViewHolder) convertView.getTag();
        }

        mChildListViewHolder.editText.setText(getChild(groupPosition,childPosition).getGoal());

        mChildListViewHolder.imageButton_alarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (switchColor) {
                        mChildListViewHolder.imageButton_alarm.setColorFilter(ContextCompat.getColor(mContext, R.color.black));
                        switchColor = !switchColor;
                    } else {
                        final CustomDialog customDialog = new CustomDialog(mContext);
                        customDialog.show();
                        customDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                if (customDialog.save) {
                                    mChildListViewHolder.imageButton_alarm.setColorFilter(ContextCompat.getColor(mContext, R.color.red));
                                    switchColor = !switchColor;


                                }
                            }
                        });

                    }

                }
            });



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void add_empty() {
        String empty_str = null;
        groupList.add(empty_str);
    }


}

class ChildListViewHolder {
    EditText editText;
    ImageButton imageButton_alarm;

}


