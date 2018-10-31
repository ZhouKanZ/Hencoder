package com.jmslam.customview_1.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jmslam.customview_1.Utils;

/**
 * @author wgzho
 * @Date 2018/10/26
 */

public class ImageTextView extends View {


    private static final int IMAGE_WIDTH = (int) Utils.dp2px(100);
    private static final float IMAGE_OFFSET = Utils.dp2px(80);
    private static final float TEXTOFFSET_Y = Utils.dp2px(5);

    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float[] cutWidth = new float[1];
    Paint.FontMetrics pf = new Paint.FontMetrics();


    String text = "这个方法老实说我从没用过，也始终没有搞懂它是什么意思" +
            "，就不强行装逼了。把文档中的解释照搬过来，各位自己研究吧。" +
            "Helper for setFlags(), setting or clearing the LINEARTEXTFLAG" +
            " bit上面这句中提到的 LINEAR_TEXT_FLAG:Paint flag that enables smooth " +
            "linear scaling of text.Enabling this flag does not actually scale text, but r" +
            "ather adjusts text draw operations to deal gracefully with smooth adjustmen" +
            "t of scale. When this flag is enabled, font hinting is disabled to prevent " +
            "shape deformation between scale factors, and glyph caching is disabled due to" +
            " the large number of glyph images that will be generated.SUBPIXELTEXTFLAG should" +
            " be used in conjunction with this flag to prevent glyph positions from snapping to" +
            " whole pixel values as scale factor is adjusted.以上就是 Paint 的对文字的显示效果设置类" +
            "方法。下面介绍它的第二类方法：测量文字尺寸类。";


    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = Utils.getAvatar(getResources(), IMAGE_WIDTH);
        paint.setTextSize(Utils.sp2px(14));
        paint.getFontMetrics(pf);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, IMAGE_OFFSET, paint);

        // 剩余字数
        int lenght = text.length();
        // 行数
        int raw = 1;

        int start = 0;
        int end = 0;
        int count;
        int measureWidth;
        int startX;

        // 直到文字被绘制完
        while (end < lenght) {
            // 1.判断文字和图片是否同行
            if (unSameRaw(raw, paint, IMAGE_OFFSET, IMAGE_OFFSET + IMAGE_WIDTH)) {
                measureWidth = getWidth();
                startX = 0;
            } else {
                // 在同一行
                measureWidth = getWidth() - IMAGE_WIDTH;
                startX = IMAGE_WIDTH;
            }

            count = paint.breakText(text, start, lenght, true, measureWidth, cutWidth);
            end = start + count;
            canvas.drawText(text, start, end, startX, TEXTOFFSET_Y + raw * paint.getFontSpacing(), paint);
            start += count;
            raw++;
        }

    }


    private boolean unSameRaw(int raw, Paint paint, float imageTop, float imageBottom) {

        return TEXTOFFSET_Y + raw * paint.getFontSpacing() < imageTop ||
                TEXTOFFSET_Y + (raw - 1) * paint.getFontSpacing() > imageBottom;

    }
}
