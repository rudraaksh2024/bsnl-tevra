package bsnl.bsnl_teevra;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;

public class SquareView extends LinearLayout {
    public SquareView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        double d = (double) size;
        int i3 = (int) (d + (0.3d * d));
        setMeasuredDimension(size, i3);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i3, BasicMeasure.EXACTLY));
    }
}
