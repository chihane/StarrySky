package chihane.starrysky;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.util.AttributeSet;
import android.view.View;

public class Star extends View {
    static final int MAGNITUDE_MAX = -6;
    static final int MAGNITUDE_MIN = 6;

    private static final int ALPHA_MAX = 0xff;
    private static final int ALPHA_MIN = 0x33;

    private static final long TWINKLING_DURATION_MIN = 3000;
    private static final long TWINKLING_DURATION_MAX = 6000;

    private static final int STYLE_NORMAL = 0;

    @IntDef(value = {STYLE_NORMAL})
    @interface StarStyle {}

    @IntRange(from = MAGNITUDE_MAX, to = MAGNITUDE_MIN)
    int magnitude;

    float latitude;
    float longitude;

    @StarStyle
    int style;

    @ColorInt
    int color;

    @FloatRange(from = 0, to = 20)
    float size;

    private Paint paint;

    private float dimmedPercent = 1;

    public Star(Context context) { this(context, null); }
    public Star(Context context, AttributeSet attrs) { this(context, attrs, 0); }
    public Star(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        magnitude = MAGNITUDE_MAX;
        latitude = 0;
        longitude = 0;
        style = STYLE_NORMAL;
        color = Color.WHITE;
        size = 4;

        paint = new Paint();
    }
    void twinkle() {
        dimmedPercent = (float) Math.random();

        boolean direction = Math.random() >= 0.5;
        ValueAnimator animator = ValueAnimator.ofFloat(dimmedPercent, direction ? 1 : 0, direction ? 0 : 1);
        animator.setDuration(TWINKLING_DURATION_MIN + (int) (Math.random() * (TWINKLING_DURATION_MAX - TWINKLING_DURATION_MIN)));
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if (dimmedPercent == value) {
                    return;
                }

                dimmedPercent = value;
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) size, (int) size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initPaint();
        canvas.drawCircle(size / 2, size / 2, size / 2, paint);
    }

    private void initPaint() {
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        paint.setColor(color);
        paint.setAlpha((int) (magnitude2Alpha(magnitude) * dimmedPercent));
    }

    private int magnitude2Alpha(int magnitude) {
        int k = (ALPHA_MAX - ALPHA_MIN) / (MAGNITUDE_MAX - MAGNITUDE_MIN);
        int b = ALPHA_MAX - k * MAGNITUDE_MAX;
        return k * magnitude + b;
    }
}
