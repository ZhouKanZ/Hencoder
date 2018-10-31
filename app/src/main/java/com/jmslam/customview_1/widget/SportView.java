package com.jmslam.customview_1.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jmslam.customview_1.Utils;

/**
 * @author wgzho
 * @Date 2018/10/26
 * @desc Draw a sport circle and present time .
 */

public class SportView extends View {


    private static final String TAG = "SportView";
    private static final float RADIUS = Utils.dp2px(150);
    private static final float CIRCLE_WIDTH = Utils.dp2px(10);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF rectF;
    Rect rect = new Rect();
    String text = "abfw";
    Paint.FontMetrics pf;

    public SportView(Context context) {
        super(context);
    }

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(CIRCLE_WIDTH);
        paint.setColor(Color.parseColor("#45bfd6"));
        paint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        rectF = new RectF(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);

        paint.setColor(Color.parseColor("#00ff00"));
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rectF, 20, 100, false, paint);

        // drawText
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(Utils.sp2px(30));

        pf = paint.getFontMetrics();
        paint.getTextBounds(text, 0, text.length(), rect);

        int offset = (rect.bottom + rect.top) / 2;
        paint.setStrokeWidth(5);
        canvas.drawLine(0, getHeight() / 2 - (rect.bottom - rect.top), getWidth(), getHeight() / 2 - (rect.bottom - rect.top), paint);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
        Log.d(TAG, "onDraw: " + offset);
        canvas.drawText(text, getWidth() / 2, getHeight() / 2 - offset, paint);
        paint.setColor(Color.parseColor("#ff0000"));

        Log.d(TAG, "onDraw: " + (pf.ascent + pf.descent) / 2);
        canvas.drawText(text, getWidth() / 2, getHeight() / 2 - ((pf.ascent + pf.descent) / 2), paint);
    }
}
