package com.example.administrator.calendar;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 15-9-22.
 */
public class TextAdapter extends BaseAdapter{
    private static final int TEXT_PADDING = 10;
    private List<String> textList;
    private ListView.LayoutParams layoutParams;
    public static final int CHILD_HEIGHT = 100;
    public TextAdapter( List<String> textList) {
        this.textList = textList;
         layoutParams = new ListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, CHILD_HEIGHT);
    }

    @Override
    public int getCount() {
        return textList.size();
    }

    @Override
    public Object getItem(int position) {
        return textList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = new TextView(parent.getContext());
            ((TextView)convertView).setGravity(Gravity.CENTER);
            convertView.setPadding(0, TEXT_PADDING, 0, TEXT_PADDING);
            ((TextView)convertView).setTextColor(parent.getContext().getResources().getColor(R.color.year_text));
            ((TextView)convertView).setTextSize(parent.getContext().getResources().getDimensionPixelSize(R.dimen.year_text));
        }
        convertView.setLayoutParams(layoutParams);
        ((TextView)convertView).setText(textList.get(position));
        return convertView;
    }
}
