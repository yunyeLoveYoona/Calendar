package com.example.administrator.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.calendar.CalendarModel;
import com.example.administrator.calendar.Lunar;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 15-9-22.
 */
public class MouthView extends FlowLayout {
    private Calendar today;
    private OnClickListener onClickListener;

    public MouthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MouthView(Context context){
        super(context);
        CalendarModel calendarModel = new CalendarModel();
        today = Calendar.getInstance();
        calendarModel.year = today.get(Calendar.YEAR);
        calendarModel.month = today.get(Calendar.MONTH) + 1;
        calendarModel.day = today.get(Calendar.DATE);
        setCalendar(calendarModel, context);
    }

    public void setCalendar(CalendarModel calendarModel, Context context) {
        removeAllViews();
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(MarginLayoutParams
                .WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT);
        WeekView weekView = new WeekView(context);
        weekView.setLayoutParams(marginLayoutParams);
        addView(weekView);
        int days = getMonthLastDay(calendarModel.year, calendarModel.month);
        for (int i = 1; i <= days; i++) {
            DayView dayView = new DayView(context);
            marginLayoutParams = new MarginLayoutParams(MarginLayoutParams
                    .WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT);
            if (i == 1) {
                int week = getWeek(calendarModel);
                marginLayoutParams.setMargins((week-1)* dayView.width, 0, 0, 0);
            }
            Calendar cal = Calendar.getInstance();
            cal.set(calendarModel.year, calendarModel.month, i);
            Lunar lunar = new Lunar(cal);
            dayView.setLayoutParams(marginLayoutParams);
            dayView.setText(i + "", lunar.getChinaDayString(lunar.day));
            if(calendarModel.year ==  today.get(Calendar.YEAR)
                    &&calendarModel.month ==  today.get(Calendar.MONTH)+1
                    &&i ==  today.get(Calendar.DATE)){
                dayView.setToday();
                dayView.setChecked();
            }
            dayView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetDayView();
                    ((DayView)v).setChecked();
                    if(onClickListener!=null){
                        onClickListener.onClick(v);
                    }
                }
            });
            addView(dayView);

        }
    }

    public int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public int getWeek(CalendarModel calendarModel) {
        Calendar cal = Calendar.getInstance();
        cal.set(calendarModel.year, calendarModel.month-1, 1);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index <= 0) {
            week_index = 6;
        }
        return week_index;
    }
    public void setOnDayClickListenter(OnClickListener onDayClickListenter){
        this.onClickListener = onDayClickListenter;

    }
    private void resetDayView(){
        for(int i =0; i < getChildCount();i++){
            if(getChildAt(i) instanceof DayView){
                DayView dayView = (DayView) getChildAt(i);
                if(dayView.isChecked){
                    dayView.setChecked();
                }
            }
        }
    }
}
