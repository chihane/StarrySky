package chihane.starrysky;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class StarrySky extends ViewGroup {
    private StarMaker starMaker = new StarMaker(getContext());

    public StarrySky(Context context) { this(context, null); }
    public StarrySky(Context context, AttributeSet attrs) { this(context, attrs, 0); }
    public StarrySky(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StarrySky(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    protected void init() {
    }

    public void dominateBy(StarMaker starMaker) {
        this.starMaker = starMaker;
        refresh();
    }

    public void refresh() {
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);

        generateStarsWithin(width, height);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof Star) {
                measureChild(child,
                        MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof Star) {
                Star star = (Star) child;
                star.layout((int) star.longitude, (int) star.latitude, (int) (star.longitude + star.size), (int) (star.latitude + star.size));
            }
        }
    }

    private void generateStarsWithin(int width, int height) {
        removeAllViews();

        List<Star> galaxy = starMaker.星たちよーこの造星者の名のもとにー現れーーー(width, height);
        for (Star star : galaxy) {
            addView(star);
        }
    }
}
