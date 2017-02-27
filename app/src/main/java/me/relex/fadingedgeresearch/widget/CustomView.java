package me.relex.fadingedgeresearch.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CustomView extends View {

    private StaticLayout mLayout;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        /* 1. Enable FadingEdge */
        setHorizontalFadingEdgeEnabled(true);

        String text =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus augue turpis, ornare sit amet consectetur nec, pellentesque sed magna.";
        TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14,
                context.getResources().getDisplayMetrics()));
        int width = (int) Layout.getDesiredWidth(text, paint);
        mLayout = new StaticLayout(text, paint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, true);
    }

    /* 2. Show Left FadingEdge */
    @Override protected float getLeftFadingEdgeStrength() {
        return 1.0f;
    }

    /* 3. Show Right FadingEdge */
    @Override protected float getRightFadingEdgeStrength() {
        return 1.0f;
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), mLayout.getHeight());
    }

    @Override protected void onDraw(Canvas canvas) {
        mLayout.draw(canvas);
    }
}
