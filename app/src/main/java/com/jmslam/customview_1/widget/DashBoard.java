package com.jmslam.customview_1.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jmslam.customview_1.Utils;

/**
 * @author wgzho
 * @Date 2018/10/22
 * @desc 仪表盘
 */

public class DashBoard extends View {

    private static final float RADIUS = Utils.dp2px(150);
    private static final float LENGTH = Utils.dp2px(100);
    private static final int ANGLE = 120;
    private static final int NUM_SCALE = 20;
    private static final int INDEX_SCALE = 5;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF arcArea;
    Path dashPath = new Path();
    Path p = new Path();
    PathEffect pathEffect;
    PathMeasure pathMeasure = new PathMeasure();

    public DashBoard(Context context) {
        super(context);
    }

    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(2));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        arcArea = new RectF(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);

        dashPath.addRect(0, 0, 5, 50, Path.Direction.CW);
        p.addArc(arcArea, 90 + ANGLE / 2, 360 - ANGLE);

        pathMeasure.setPath(p, false);
        // ADVANCE 间距
        // PHASE   表示开始间隔
        pathEffect = new PathDashPathEffect(dashPath, (pathMeasure.getLength() - 5) / NUM_SCALE, 0, PathDashPathEffect.Style.ROTATE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 1.drawArc
        canvas.drawArc(arcArea, 90 + ANGLE / 2, 360 - ANGLE, false, paint);
        // 2.drawDash
        paint.setPathEffect(pathEffect);
         canvas.drawArc(arcArea, 90 + ANGLE / 2, 360 - ANGLE, false, paint);
        paint.setPathEffect(null);
        // 3.drawLine
        canvas.drawLine(getWidth()/2,getHeight()/2,
                (float) (getWidth()/2 + Math.cos(Math.toRadians(getAngle(INDEX_SCALE))) * LENGTH),
                (float)(getHeight()/2 + Math.sin(Math.toRadians(getAngle(INDEX_SCALE))) * LENGTH),
                paint);
    }

    /**
     *  get index position angle
     * @param index
     * @return
     */
    private float getAngle(int index){
        Log.d("xxx", "getAngle: " + ((float) ANGLE/2 + 90) + ((360 - ANGLE)/NUM_SCALE)*index);
        return ((float) ANGLE/2 + 90) + ((360 - ANGLE)/NUM_SCALE)*index;
    }
}
