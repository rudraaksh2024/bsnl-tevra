package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class AnimatedViewPortJob extends ViewPortJob implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    protected ObjectAnimator animator;
    protected float phase;
    protected float xOrigin;
    protected float yOrigin;

    public void onAnimationRepeat(Animator animator2) {
    }

    public void onAnimationStart(Animator animator2) {
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
    }

    public abstract void recycleSelf();

    public AnimatedViewPortJob(ViewPortHandler viewPortHandler, float f, float f2, Transformer transformer, View view, float f3, float f4, long j) {
        super(viewPortHandler, f, f2, transformer, view);
        this.xOrigin = f3;
        this.yOrigin = f4;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, TypedValues.CycleType.S_WAVE_PHASE, new float[]{0.0f, 1.0f});
        this.animator = ofFloat;
        ofFloat.setDuration(j);
        this.animator.addUpdateListener(this);
        this.animator.addListener(this);
    }

    public void run() {
        this.animator.start();
    }

    public float getPhase() {
        return this.phase;
    }

    public void setPhase(float f) {
        this.phase = f;
    }

    public float getXOrigin() {
        return this.xOrigin;
    }

    public float getYOrigin() {
        return this.yOrigin;
    }

    /* access modifiers changed from: protected */
    public void resetAnimator() {
        this.animator.removeAllListeners();
        this.animator.removeAllUpdateListeners();
        this.animator.reverse();
        this.animator.addUpdateListener(this);
        this.animator.addListener(this);
    }

    public void onAnimationEnd(Animator animator2) {
        try {
            recycleSelf();
        } catch (IllegalArgumentException unused) {
        }
    }

    public void onAnimationCancel(Animator animator2) {
        try {
            recycleSelf();
        } catch (IllegalArgumentException unused) {
        }
    }
}
