package com.jmslam.customview_1.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.jmslam.customview_1.Utils;

public class ScaleImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final int IAMGE_WIDTH = (int) Utils.dp2px(300);
    private static final float OVER_SCALE_FACTOR = 1.5f;

    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float originalOffsetX;
    float originalOffsetY;
    float offsetX;
    float offsetY;
    float smallScale;
    float bigScale;
    float scaleFraction; // 0 - 1
    boolean isBig;
    ObjectAnimator scaleAnimator;

    GestureDetectorCompat detector;

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvatar(getResources(), IAMGE_WIDTH);
        detector = new GestureDetectorCompat(context,this);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originalOffsetX = (getWidth() - bitmap.getWidth())/2f;
        originalOffsetY = (getHeight() - bitmap.getHeight())/2f;
        smallScale = Math.min((float)getHeight()/bitmap.getHeight(),(float)getWidth() / bitmap.getWidth());
        bigScale= Math.max((float)getHeight()/bitmap.getHeight(),(float)getWidth() / bitmap.getWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(offsetX,offsetY);
        float scale  = smallScale + (bigScale -smallScale) * scaleFraction* OVER_SCALE_FACTOR;
        canvas.scale(scale ,scale ,getWidth()/2,getHeight()/2);
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY,paint);
    }

    public ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null){
            scaleAnimator = ObjectAnimator.ofFloat(this,"scaleFraction",0,1);
        }
        return scaleAnimator;
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent down, MotionEvent event, float distanceX, float distanceY) {
        if (isBig){
            offsetX -= distanceX;
            offsetX  = Math.min(offsetX,(bitmap.getWidth()*bigScale* OVER_SCALE_FACTOR - getWidth())/2f);
            offsetX  = Math.max(offsetX,-(bitmap.getWidth()*bigScale* OVER_SCALE_FACTOR - getWidth())/2f);
            offsetY -= distanceY;
            offsetY  = Math.min(offsetY ,(bitmap.getHeight()*bigScale* OVER_SCALE_FACTOR - getHeight())/2f);
            offsetY  = Math.max(offsetY ,-(bitmap.getHeight()*bigScale* OVER_SCALE_FACTOR - getHeight())/2f);
            invalidate();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    /**
     *  滑动
     * @param motionEvent
     * @param motionEvent1
     * @param v
     * @param v1
     * @return
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {

        if (isBig){
            getScaleAnimator().reverse();
            offsetX = 0;
            offsetY = 0;
        }else {
            getScaleAnimator().start();
        }
        isBig = !isBig;
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}
