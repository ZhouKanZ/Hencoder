package com.jmslam.customview_1.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.jmslam.customview_1.R;
import com.jmslam.customview_1.Utils;

/**
 * @author wgzho
 * @Date 2018/10/22
 */

public class AvatarView extends View {

    private static final float WIDTH = Utils.dp2px(300);
    private static final float OFFSET = Utils.dp2px(100);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF rectF,circleRectf;
    Bitmap avatar;
    Xfermode xfermode;

    public AvatarView(Context context) {
        super(context);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF = new RectF(OFFSET,OFFSET,Utils.dp2px(300),Utils.dp2px(300));
        circleRectf = new RectF(OFFSET - 20,OFFSET - 20,Utils.dp2px(300) +  20,Utils.dp2px(300) +  20);
        avatar = getAvatar();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.parseColor("#FFB6C1"));
        canvas.drawOval(circleRectf,paint);
        int saveId = canvas.saveLayer(rectF,paint);

        canvas.drawOval(rectF,paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(avatar,OFFSET,OFFSET,paint);

        canvas.restoreToCount(saveId);

    }

    private Bitmap getAvatar() {

        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.avatar);
        op.inDensity = (int) WIDTH;
        op.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(),R.mipmap.avatar);

    }
}
