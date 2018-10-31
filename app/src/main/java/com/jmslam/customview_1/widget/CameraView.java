package com.jmslam.customview_1.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jmslam.customview_1.Utils;

/**
 * @author wgzho
 * @Date 2018/10/26
 */

public class CameraView extends View {

    private static final int IMAGE_WIDTH = (int) Utils.dp2px(200);
    private static final int OFFSET = (int) Utils.dp2px(50);

    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();
    Rect top = new Rect();
    Rect bottom = new Rect();

    public CameraView(Context context) {
        super(context);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = Utils.getAvatar(getResources(), IMAGE_WIDTH);
        camera.setLocation(0, 0, Utils.getZForCamera());
        camera.rotateX(30);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        top.left = OFFSET;
//        top.top = OFFSET;
//        top.right = OFFSET + IMAGE_WIDTH;
//        top.bottom = OFFSET + (IMAGE_WIDTH) / 2;
//        // desc :
//        canvas.save();
//        canvas.clipRect(top);
//        canvas.drawBitmap(bitmap, OFFSET, OFFSET, paint);
//        canvas.restore();

        // desc :
        bottom.left = OFFSET;
        bottom.top = OFFSET + (IMAGE_WIDTH) / 2;
        bottom.right = OFFSET + IMAGE_WIDTH;
        bottom.bottom = OFFSET + IMAGE_WIDTH;

        canvas.save();

//        canvas.rotate(30);
        canvas.clipRect(bottom);
//        canvas.translate((IMAGE_WIDTH + OFFSET) / 2, (IMAGE_WIDTH + OFFSET) / 2);
//        canvas.rotate(-0);
//        camera.applyToCanvas(canvas);
//        canvas.rotate(0);
//        canvas.translate(-(IMAGE_WIDTH + OFFSET) / 2, -(IMAGE_WIDTH + OFFSET) / 2);
//        canvas.clipRect(bottom);
        camera.applyToCanvas(canvas);
        canvas.drawBitmap(bitmap, OFFSET, OFFSET, paint);
        canvas.restore();
    }
}
