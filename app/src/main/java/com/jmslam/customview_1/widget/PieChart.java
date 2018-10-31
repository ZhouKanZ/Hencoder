package com.jmslam.customview_1.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jmslam.customview_1.Utils;

/**
 * @author wgzho
 * @Date 2018/10/22
 */

public class PieChart extends View {


    private static final float RADIUS = Utils.dp2px(150);
    private static final int OFFSET = 20;
    private static final int INDEX_PULLED_ARC = 1;

    RectF arcArea;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int[] colors = {Color.parseColor("#CC6699"),
            Color.parseColor("#0066CC"),
            Color.parseColor("#FF6600"),
            Color.parseColor("#33CC33"),};
    int[] angles = {40, 90, 170, 60};
    private int currentAngle = 0;


    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        arcArea = new RectF(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < angles.length; i++) {

            canvas.save();
            if (i == INDEX_PULLED_ARC) {
                canvas.translate(OFFSET * (float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2)),
                        OFFSET * (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2)));
            }
            paint.setColor(colors[i]);
            canvas.drawArc(arcArea, currentAngle, angles[i], true, paint);
            canvas.restore();
            currentAngle += angles[i];
        }
    }
}
