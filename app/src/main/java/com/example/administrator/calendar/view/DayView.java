package com.example.administrator.calendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.calendar.R;

/**
 * 日历中显示天的UI
 * Created by Administrator on 15-9-21.
 */
public class DayView extends LinearLayout {
    private float commonEraFontSize = 0;
    private float chineseCalendarFontSize = 0;
    private int selectTextColor = 0;
    private int defaultTextColor = 0;
    private int selectBackGround = 0;
    private int hyalineColor = 0;
    private Paint paint;
    public int width = 0;
    private int height = 0;
    private TextView commonEraDay;
    private TextView chineseCalendarDay;
    public boolean isChecked = false;
    private boolean isToday = false;
    private static final  int PADDING = 2;

    public DayView(Context context) {
        super(context);
        commonEraFontSize = context.getResources().getDimensionPixelSize(R.dimen.
                common_era_font_size);
        chineseCalendarFontSize = context.getResources().getDimensionPixelSize(R.dimen
                .chinese_calendar_font_size);
        selectTextColor = context.getResources().getColor(R.color.select_text);
        defaultTextColor = context.getResources().getColor(R.color.default_text);
        selectBackGround = context.getResources().getColor(R.color.select_background);
        hyalineColor = context.getResources().getColor(R.color.hyaline);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        height = width = wm.getDefaultDisplay().getWidth() / 7;
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        commonEraDay = new TextView(context);
        commonEraDay.setTextSize(commonEraFontSize);
        TextPaint tp = commonEraDay.getPaint();
        tp.setFakeBoldText(true);
        commonEraDay.setTextColor(defaultTextColor);
        chineseCalendarDay = new TextView(context);
        chineseCalendarDay.setTextSize(chineseCalendarFontSize);
        chineseCalendarDay.setTextColor(defaultTextColor);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(hyalineColor);
        paint.setStrokeWidth(3f);
        paint.setAntiAlias(true);
        LinearLayout.LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.
                WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        commonEraDay.setLayoutParams(layoutParams);
        chineseCalendarDay.setLayoutParams(layoutParams);
        addView(commonEraDay);
        addView(chineseCalendarDay);
        setBackgroundColor(hyalineColor);
        setPadding(PADDING, PADDING, PADDING, PADDING);
    }

    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectf = new RectF(PADDING, PADDING, width-PADDING, height-PADDING);
        canvas.drawOval(rectf, paint);
    }

    public void setChecked() {
        if (!isChecked) {
            commonEraDay.setTextColor(selectTextColor);
            chineseCalendarDay.setTextColor(selectTextColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(selectBackGround);
        } else {
            commonEraDay.setTextColor(defaultTextColor);
            chineseCalendarDay.setTextColor(defaultTextColor);
            paint.setStyle(Paint.Style.STROKE);
            if (!isToday) {
                paint.setColor(hyalineColor);
            }

        }
        isChecked = !isChecked;
        invalidate();
    }

    public void setToday() {
        isToday = true;
        paint.setColor(selectBackGround);
        invalidate();
    }

    public void setText(String commonEradayStr, String chineseDayStr) {
        commonEraDay.setText(commonEradayStr);
        chineseCalendarDay.setText(chineseDayStr);
    }


}
