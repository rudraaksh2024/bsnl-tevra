package com.github.mikephil.charting.animation;

import android.animation.TimeInterpolator;

public class Easing {
    private static final float DOUBLE_PI = 6.2831855f;
    public static final EasingFunction EaseInBack = new EasingFunction() {
        public float getInterpolation(float f) {
            return f * f * ((f * 2.70158f) - 1.70158f);
        }
    };
    public static final EasingFunction EaseInBounce = new EasingFunction() {
        public float getInterpolation(float f) {
            return 1.0f - Easing.EaseOutBounce.getInterpolation(1.0f - f);
        }
    };
    public static final EasingFunction EaseInCirc = new EasingFunction() {
        public float getInterpolation(float f) {
            return -(((float) Math.sqrt((double) (1.0f - (f * f)))) - 1.0f);
        }
    };
    public static final EasingFunction EaseInCubic = new EasingFunction() {
        public float getInterpolation(float f) {
            return (float) Math.pow((double) f, 3.0d);
        }
    };
    public static final EasingFunction EaseInElastic = new EasingFunction() {
        public float getInterpolation(float f) {
            if (f == 0.0f) {
                return 0.0f;
            }
            if (f == 1.0f) {
                return 1.0f;
            }
            float f2 = f - 1.0f;
            return -(((float) Math.pow(2.0d, (double) (10.0f * f2))) * ((float) Math.sin((double) (((f2 - (0.047746483f * ((float) Math.asin(1.0d)))) * Easing.DOUBLE_PI) / 0.3f))));
        }
    };
    public static final EasingFunction EaseInExpo = new EasingFunction() {
        public float getInterpolation(float f) {
            if (f == 0.0f) {
                return 0.0f;
            }
            return (float) Math.pow(2.0d, (double) ((f - 1.0f) * 10.0f));
        }
    };
    public static final EasingFunction EaseInOutBack = new EasingFunction() {
        public float getInterpolation(float f) {
            float f2 = f * 2.0f;
            if (f2 < 1.0f) {
                return f2 * f2 * ((3.5949094f * f2) - 2.5949094f) * 0.5f;
            }
            float f3 = f2 - 2.0f;
            return ((f3 * f3 * ((3.5949094f * f3) + 2.5949094f)) + 2.0f) * 0.5f;
        }
    };
    public static final EasingFunction EaseInOutBounce = new EasingFunction() {
        public float getInterpolation(float f) {
            if (f < 0.5f) {
                return Easing.EaseInBounce.getInterpolation(f * 2.0f) * 0.5f;
            }
            return (Easing.EaseOutBounce.getInterpolation((f * 2.0f) - 1.0f) * 0.5f) + 0.5f;
        }
    };
    public static final EasingFunction EaseInOutCirc = new EasingFunction() {
        public float getInterpolation(float f) {
            float sqrt;
            float f2;
            float f3 = f * 2.0f;
            if (f3 < 1.0f) {
                sqrt = ((float) Math.sqrt((double) (1.0f - (f3 * f3)))) - 1.0f;
                f2 = -0.5f;
            } else {
                float f4 = f3 - 2.0f;
                sqrt = ((float) Math.sqrt((double) (1.0f - (f4 * f4)))) + 1.0f;
                f2 = 0.5f;
            }
            return sqrt * f2;
        }
    };
    public static final EasingFunction EaseInOutCubic = new EasingFunction() {
        public float getInterpolation(float f) {
            float f2 = f * 2.0f;
            if (f2 < 1.0f) {
                return ((float) Math.pow((double) f2, 3.0d)) * 0.5f;
            }
            return (((float) Math.pow((double) (f2 - 2.0f), 3.0d)) + 2.0f) * 0.5f;
        }
    };
    public static final EasingFunction EaseInOutElastic = new EasingFunction() {
        public float getInterpolation(float f) {
            if (f == 0.0f) {
                return 0.0f;
            }
            float f2 = f * 2.0f;
            if (f2 == 2.0f) {
                return 1.0f;
            }
            float asin = ((float) Math.asin(1.0d)) * 0.07161972f;
            if (f2 < 1.0f) {
                float f3 = f2 - 1.0f;
                return ((float) Math.pow(2.0d, (double) (10.0f * f3))) * ((float) Math.sin((double) (((f3 * 1.0f) - asin) * Easing.DOUBLE_PI * 2.2222223f))) * -0.5f;
            }
            float f4 = f2 - 1.0f;
            return (((float) Math.pow(2.0d, (double) (-10.0f * f4))) * 0.5f * ((float) Math.sin((double) (((f4 * 1.0f) - asin) * Easing.DOUBLE_PI * 2.2222223f)))) + 1.0f;
        }
    };
    public static final EasingFunction EaseInOutExpo = new EasingFunction() {
        public float getInterpolation(float f) {
            float f2;
            if (f == 0.0f) {
                return 0.0f;
            }
            if (f == 1.0f) {
                return 1.0f;
            }
            float f3 = f * 2.0f;
            if (f3 < 1.0f) {
                f2 = (float) Math.pow(2.0d, (double) ((f3 - 1.0f) * 10.0f));
            } else {
                f2 = (-((float) Math.pow(2.0d, (double) ((f3 - 1.0f) * -10.0f)))) + 2.0f;
            }
            return f2 * 0.5f;
        }
    };
    public static final EasingFunction EaseInOutQuad = new EasingFunction() {
        public float getInterpolation(float f) {
            float f2 = f * 2.0f;
            if (f2 < 1.0f) {
                return 0.5f * f2 * f2;
            }
            float f3 = f2 - 1.0f;
            return ((f3 * (f3 - 2.0f)) - 1.0f) * -0.5f;
        }
    };
    public static final EasingFunction EaseInOutQuart = new EasingFunction() {
        public float getInterpolation(float f) {
            float f2 = f * 2.0f;
            if (f2 < 1.0f) {
                return ((float) Math.pow((double) f2, 4.0d)) * 0.5f;
            }
            return (((float) Math.pow((double) (f2 - 2.0f), 4.0d)) - 2.0f) * -0.5f;
        }
    };
    public static final EasingFunction EaseInOutSine = new EasingFunction() {
        public float getInterpolation(float f) {
            return (((float) Math.cos(((double) f) * 3.141592653589793d)) - 1.0f) * -0.5f;
        }
    };
    public static final EasingFunction EaseInQuad = new EasingFunction() {
        public float getInterpolation(float f) {
            return f * f;
        }
    };
    public static final EasingFunction EaseInQuart = new EasingFunction() {
        public float getInterpolation(float f) {
            return (float) Math.pow((double) f, 4.0d);
        }
    };
    public static final EasingFunction EaseInSine = new EasingFunction() {
        public float getInterpolation(float f) {
            return (-((float) Math.cos(((double) f) * 1.5707963267948966d))) + 1.0f;
        }
    };
    public static final EasingFunction EaseOutBack = new EasingFunction() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * ((f2 * 2.70158f) + 1.70158f)) + 1.0f;
        }
    };
    public static final EasingFunction EaseOutBounce = new EasingFunction() {
        public float getInterpolation(float f) {
            if (f < 0.36363637f) {
                return 7.5625f * f * f;
            }
            if (f < 0.72727275f) {
                float f2 = f - 0.54545456f;
                return (7.5625f * f2 * f2) + 0.75f;
            } else if (f < 0.90909094f) {
                float f3 = f - 0.8181818f;
                return (7.5625f * f3 * f3) + 0.9375f;
            } else {
                float f4 = f - 0.95454544f;
                return (7.5625f * f4 * f4) + 0.984375f;
            }
        }
    };
    public static final EasingFunction EaseOutCirc = new EasingFunction() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (float) Math.sqrt((double) (1.0f - (f2 * f2)));
        }
    };
    public static final EasingFunction EaseOutCubic = new EasingFunction() {
        public float getInterpolation(float f) {
            return ((float) Math.pow((double) (f - 1.0f), 3.0d)) + 1.0f;
        }
    };
    public static final EasingFunction EaseOutElastic = new EasingFunction() {
        public float getInterpolation(float f) {
            if (f == 0.0f) {
                return 0.0f;
            }
            if (f == 1.0f) {
                return 1.0f;
            }
            return (((float) Math.pow(2.0d, (double) (-10.0f * f))) * ((float) Math.sin((double) (((f - (0.047746483f * ((float) Math.asin(1.0d)))) * Easing.DOUBLE_PI) / 0.3f)))) + 1.0f;
        }
    };
    public static final EasingFunction EaseOutExpo = new EasingFunction() {
        public float getInterpolation(float f) {
            if (f == 1.0f) {
                return 1.0f;
            }
            return -((float) Math.pow(2.0d, (double) ((f + 1.0f) * -10.0f)));
        }
    };
    public static final EasingFunction EaseOutQuad = new EasingFunction() {
        public float getInterpolation(float f) {
            return (-f) * (f - 2.0f);
        }
    };
    public static final EasingFunction EaseOutQuart = new EasingFunction() {
        public float getInterpolation(float f) {
            return -(((float) Math.pow((double) (f - 1.0f), 4.0d)) - 1.0f);
        }
    };
    public static final EasingFunction EaseOutSine = new EasingFunction() {
        public float getInterpolation(float f) {
            return (float) Math.sin(((double) f) * 1.5707963267948966d);
        }
    };
    public static final EasingFunction Linear = new EasingFunction() {
        public float getInterpolation(float f) {
            return f;
        }
    };

    public interface EasingFunction extends TimeInterpolator {
        float getInterpolation(float f);
    }
}
