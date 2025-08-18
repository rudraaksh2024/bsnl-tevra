package bsnl.bsnl_teevra;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class HorizontalScrollViewForViewPager extends HorizontalScrollView {
    float old_x;
    float old_y;

    public HorizontalScrollViewForViewPager(Context context) {
        super(context);
    }

    public HorizontalScrollViewForViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HorizontalScrollViewForViewPager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public HorizontalScrollViewForViewPager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.old_x = motionEvent.getX();
            this.old_y = motionEvent.getY();
        } else if (actionMasked == 2) {
            float x = motionEvent.getX() - this.old_x;
            if (Math.abs(x) > Math.abs(motionEvent.getY() - this.old_y)) {
                if (x > 0.0f && getScrollX() == 0) {
                    return false;
                }
                int width = getChildAt(0).getWidth();
                if (x < 0.0f && getScrollX() + getWidth() >= width) {
                    return false;
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
