package bsnl.bsnl_teevra.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import bsnl.bsnl_teevra.R;
import java.util.Objects;

public class CustomAnimationUtils {
    public static void fadeIn(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
    }

    public static void fadeOut(Context context, View view, final Runnable runnable) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                Runnable runnable = runnable;
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
        view.startAnimation(loadAnimation);
    }

    public static void fadeInDialog(Context context, Dialog dialog) {
        fadeIn(context, dialog.findViewById(16908290));
    }

    public static void fadeOutDialog(Context context, Dialog dialog) {
        View findViewById = dialog.findViewById(16908290);
        Objects.requireNonNull(dialog);
        fadeOut(context, findViewById, new CustomAnimationUtils$$ExternalSyntheticLambda0(dialog));
    }
}
