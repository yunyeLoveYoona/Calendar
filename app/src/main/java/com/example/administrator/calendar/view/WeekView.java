package com.example.administrator.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.calendar.R;

/**
 * Created by Administrator on 15-9-22.
 */
public class WeekView extends LinearLayout {
    private int width;
    private int height;
    private float fontSize;

    public WeekView(Context context) {
        super(context);
        fontSize = context.getResources().getDimensionPixelSize(R.dimen.
                week_text);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        height = width = wm.getDefaultDisplay().getWidth() / 7;
        setOrientation(HORIZONTAL);
        addView(getWeekDay("一",context));
        addView(getWeekDay("二",context));
        addView(getWeekDay("三",context));
        addView(getWeekDay("四",context));
        addView(getWeekDay("五",context));
        addView(getWeekDay("六",context));
        addView(getWeekDay("日",context));
    }

    public WeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private TextView getWeekDay(String name,Context context){
        TextView day = new TextView(context);
        day.setLayoutParams(new LayoutParams(width,height));
        day.setText(name);
        day.setTextSize(fontSize);
        day.setGravity(Gravity.CENTER);
        day.setTextColor(context.getResources().getColor(R.color.default_text));
        return day;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width*7,height);
    }
}
