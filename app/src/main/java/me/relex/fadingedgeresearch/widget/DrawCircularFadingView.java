package me.relex.fadingedgeresearch.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import me.relex.fadingedgeresearch.R;

public class DrawCircularFadingView extends View {

    private Paint mCirclePaint;
    private Paint mFadingPaint;

    public DrawCircularFadingView(Context context) {
        super(context);
        init(context);
    }

    public DrawCircularFadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrawCircularFadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawCircularFadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mFadingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @SuppressLint("MissingSuperCall") @Override public void draw(Canvas canvas) {
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, getWidth() / 2f, mCirclePaint);
        canvas.drawPaint(mFadingPaint);
        canvas.restoreToCount(saveCount);
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        RadialGradient shader =
                new RadialGradient(w / 2f, h / 2f, w / 2f, Color.TRANSPARENT, Color.BLACK,
                        Shader.TileMode.CLAMP);
        mFadingPaint.setShader(shader);
        mFadingPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }
}
