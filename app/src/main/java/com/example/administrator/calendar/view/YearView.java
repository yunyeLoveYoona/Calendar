package com.example.administrator.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.calendar.CalendarModel;
import com.example.administrator.calendar.R;

import java.util.Calendar;

/**
 * Created by Administrator on 15-9-22.
 */
public class YearView extends LinearLayout{
    private TextView year;
    private MouthView mouthView;
    private CheckYearAlertDialog alertDialog;
    private CalendarModel calendarModel;
    public YearView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public YearView(Context context) {
        super(context);
        init(context);
    }
    private void init(Context context){
        year = new TextView(context);
        alertDialog = new CheckYearAlertDialog(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                wm.getDefaultDisplay().getWidth() / 7);
        year.setLayoutParams(layoutParams);
        year.setGravity(Gravity.CENTER);
        year.setBackgroundColor(context.getResources().getColor(R.color.select_background));
        year.setTextColor(context.getResources().getColor(R.color.select_text));
        year.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.year_text));
        Calendar  today = Calendar.getInstance();
        year.setText(today.get(Calendar.YEAR)+"."+(today.get(Calendar.MONTH) + 1 < 10?"0"+
                (today.get(Calendar.MONTH) + 1):(today.get(Calendar.MONTH) + 1+"") ));
        addView(year);
        setOrientation(VERTICAL);
        calendarModel = new CalendarModel();
        calendarModel.month = today.get(Calendar.MONTH) + 1;
        calendarModel.year = today.get(Calendar.YEAR);
        mouthView = new MouthView(context);
        addView(mouthView);
        year.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show(calendarModel);
            }
        });
    }
    public void setCalendar(CalendarModel calendarModel,Context context){
        year.setText(calendarModel.year+"."+(calendarModel.month < 10?"0"+calendarModel.month:calendarModel.month ));
        mouthView.setCalendar(calendarModel,context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        alertDialog.load();
        alertDialog.setConfirmListenter(new OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                calendarModel = alertDialog.getSelect();
                setCalendar(calendarModel,alertDialog.getContext());
            }
        });
    }
}
