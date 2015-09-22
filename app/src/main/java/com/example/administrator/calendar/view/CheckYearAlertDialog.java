package com.example.administrator.calendar.view;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.calendar.CalendarModel;
import com.example.administrator.calendar.R;
import com.example.administrator.calendar.TextAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 15-9-22.
 */
public class CheckYearAlertDialog {
    private AlertDialog alertDialog;
    private Button confirm, cancel;
    private ListView yearList, monthList;
    private static final int CHILD_HEIGHT = 100;

    private ScrollView yearScroll, monthScroll;
    private View contentView;
    private boolean isLoad = false;
    private Calendar calendar;
    private TextAdapter yearAdapter, monthAdapter;


    public CheckYearAlertDialog(Context context) {
        alertDialog = new AlertDialog.Builder(context).create();
    }

    public void load() {
        contentView = LayoutInflater.from(alertDialog.getContext()).inflate(R.layout.check_year_alert
                , null, false);
        confirm = (Button) contentView.findViewById(R.id.confirm);
        cancel = (Button) contentView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        yearList = (ListView) contentView.findViewById(R.id.year_list);
        monthList = (ListView) contentView.findViewById(R.id.month_list);
        yearScroll = (ScrollView) contentView.findViewById(R.id.year_scroll);
        monthScroll = (ScrollView) contentView.findViewById(R.id.month_scroll);
        calendar = Calendar.getInstance();
        ScrollView.LayoutParams listLayoutParams = (ScrollView.LayoutParams) yearList.getLayoutParams();
        listLayoutParams.height = (5 * CHILD_HEIGHT);
        yearList.setLayoutParams(listLayoutParams);
        monthList.setLayoutParams(listLayoutParams);
        List<String> yearText = new ArrayList<String>();
        for (int i = 1901; i <= calendar.get(Calendar.YEAR) + 30; i++) {
            yearText.add(i + "年");
        }
        yearAdapter = new TextAdapter(yearText);
        yearList.setAdapter(yearAdapter);
        List<String> monthText = new ArrayList<String>();
        for (int i = 1; i <= 12 * 3; i++) {
            monthText.add((i % 12 == 0 ? 12 : i % 12) + "月");
        }
        monthAdapter = new TextAdapter(monthText);
        monthList.setAdapter(monthAdapter);
    }

    public void show(CalendarModel calendarModel) {
        alertDialog.show();
        if (!isLoad) {
            isLoad = true;
            alertDialog.getWindow().setContentView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            alertDialog.getWindow().setGravity(Gravity.BOTTOM);
            alertDialog.setCanceledOnTouchOutside(true);
        }
        yearList.setSelection(calendarModel.year - 1901);
        monthList.setSelection(calendarModel.month - 1);
    }

    public void dismiss() {
        if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    public Context getContext() {
        return alertDialog.getContext();
    }

    public void setConfirmListenter(View.OnClickListener onClickListener) {
        confirm.setOnClickListener(onClickListener);
    }

    public CalendarModel getSelect() {
        CalendarModel calendarModel = new CalendarModel();
        String yearStr = ((String) yearAdapter.
                getItem(yearList.getLastVisiblePosition()));
        calendarModel.year = Integer.parseInt(yearStr.substring(0, yearStr.indexOf("年")));
        String monthStr = ((String) monthAdapter.
                getItem(monthList.getLastVisiblePosition()));
        calendarModel.month = Integer.parseInt(monthStr.substring(0, monthStr.indexOf("月")));
        return calendarModel;
    }
}
