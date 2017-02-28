package me.relex.fadingedgeresearch.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;

public class DrawFadingEdgeView extends View {

    private StaticLayout mLayout;
    private FadingEdgeHelper mFadingEdgeHelper;

    public DrawFadingEdgeView(Context context) {
        super(context);
        init(context);
    }

    public DrawFadingEdgeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrawFadingEdgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawFadingEdgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        String text =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus augue turpis, ornare sit amet consectetur nec, pellentesque sed magna.";
        TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14,
                context.getResources().getDisplayMetrics()));
        int width = (int) Layout.getDesiredWidth(text, paint);
        mLayout = new StaticLayout(text, paint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, true);

        mFadingEdgeHelper = new FadingEdgeHelper(context);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), mLayout.getHeight());
    }

    @SuppressLint("MissingSuperCall") @Override public void draw(Canvas canvas) {

        mFadingEdgeHelper.saveCanvasLayer(canvas, getWidth(), getWidth());

        mLayout.draw(canvas);

        mFadingEdgeHelper.drawHorizontal(canvas, getWidth(), getWidth());
        mFadingEdgeHelper.restoreCanvas(canvas);
    }

    private static class FadingEdgeHelper {

        private int fadingEdgeLength;
        private final Paint paint;
        private final Matrix matrix;
        private final Shader shader;
        private int saveCount;

        FadingEdgeHelper(Context context) {
            fadingEdgeLength = ViewConfiguration.get(context).getScaledFadingEdgeLength();

            paint = new Paint();
            matrix = new Matrix();
            shader = new LinearGradient(0, 0, 0, 1, Color.BLACK, Color.TRANSPARENT,
                    Shader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        }

        public void saveCanvasLayer(Canvas canvas, int width, int height) {
            saveCount = canvas.getSaveCount();
            canvas.saveLayer(0, 0, fadingEdgeLength, height, null,
                    Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
            canvas.saveLayer(width - fadingEdgeLength, 0, width, height, null,
                    Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        }

        public void restoreCanvas(Canvas canvas) {
            canvas.restoreToCount(saveCount);
        }

        public void drawHorizontal(Canvas canvas, int width, int height) {
            matrix.setScale(1, fadingEdgeLength);
            matrix.postRotate(-90);
            matrix.postTranslate(0, 0);
            shader.setLocalMatrix(matrix);
            paint.setShader(shader);
            canvas.drawRect(0, 0, fadingEdgeLength, height, paint);

            matrix.setScale(1, fadingEdgeLength);
            matrix.postRotate(90);
            matrix.postTranslate(width, 0);
            shader.setLocalMatrix(matrix);
            paint.setShader(shader);
            canvas.drawRect(width - fadingEdgeLength, 0, width, height, paint);
        }
    }
}
