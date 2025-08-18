package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.FloatRect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class MotionKeyTrigger extends MotionKey {
    public static final String CROSS = "CROSS";
    public static final int KEY_TYPE = 5;
    public static final String NEGATIVE_CROSS = "negativeCross";
    public static final String POSITIVE_CROSS = "positiveCross";
    public static final String POST_LAYOUT = "postLayout";
    private static final String TAG = "KeyTrigger";
    public static final String TRIGGER_COLLISION_ID = "triggerCollisionId";
    public static final String TRIGGER_COLLISION_VIEW = "triggerCollisionView";
    public static final String TRIGGER_ID = "triggerID";
    public static final String TRIGGER_RECEIVER = "triggerReceiver";
    public static final String TRIGGER_SLACK = "triggerSlack";
    public static final int TYPE_CROSS = 312;
    public static final int TYPE_NEGATIVE_CROSS = 310;
    public static final int TYPE_POSITIVE_CROSS = 309;
    public static final int TYPE_POST_LAYOUT = 304;
    public static final int TYPE_TRIGGER_COLLISION_ID = 307;
    public static final int TYPE_TRIGGER_COLLISION_VIEW = 306;
    public static final int TYPE_TRIGGER_ID = 308;
    public static final int TYPE_TRIGGER_RECEIVER = 311;
    public static final int TYPE_TRIGGER_SLACK = 305;
    public static final int TYPE_VIEW_TRANSITION_ON_CROSS = 301;
    public static final int TYPE_VIEW_TRANSITION_ON_NEGATIVE_CROSS = 303;
    public static final int TYPE_VIEW_TRANSITION_ON_POSITIVE_CROSS = 302;
    public static final String VIEW_TRANSITION_ON_CROSS = "viewTransitionOnCross";
    public static final String VIEW_TRANSITION_ON_NEGATIVE_CROSS = "viewTransitionOnNegativeCross";
    public static final String VIEW_TRANSITION_ON_POSITIVE_CROSS = "viewTransitionOnPositiveCross";
    FloatRect mCollisionRect = new FloatRect();
    private String mCross = null;
    private int mCurveFit = -1;
    private boolean mFireCrossReset = true;
    private float mFireLastPos;
    private boolean mFireNegativeReset = true;
    private boolean mFirePositiveReset = true;
    private float mFireThreshold = Float.NaN;
    private String mNegativeCross = null;
    private String mPositiveCross = null;
    private boolean mPostLayout = false;
    FloatRect mTargetRect = new FloatRect();
    private int mTriggerCollisionId = UNSET;
    private int mTriggerID = UNSET;
    private int mTriggerReceiver = UNSET;
    float mTriggerSlack = 0.1f;
    int mViewTransitionOnCross = UNSET;
    int mViewTransitionOnNegativeCross = UNSET;
    int mViewTransitionOnPositiveCross = UNSET;

    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    public void conditionallyFire(float f, MotionWidget motionWidget) {
    }

    public void getAttributeNames(HashSet<String> hashSet) {
    }

    public MotionKeyTrigger() {
        this.mType = 5;
        this.mCustom = new HashMap();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getId(java.lang.String r2) {
        /*
            r1 = this;
            r2.hashCode()
            int r1 = r2.hashCode()
            r0 = -1
            switch(r1) {
                case -1594793529: goto L_0x0082;
                case -966421266: goto L_0x0077;
                case -786670827: goto L_0x006c;
                case -648752941: goto L_0x0061;
                case -638126837: goto L_0x0056;
                case -76025313: goto L_0x004b;
                case -9754574: goto L_0x0040;
                case 364489912: goto L_0x0035;
                case 1301930599: goto L_0x0028;
                case 1401391082: goto L_0x001b;
                case 1535404999: goto L_0x000e;
                default: goto L_0x000b;
            }
        L_0x000b:
            r1 = r0
            goto L_0x008c
        L_0x000e:
            java.lang.String r1 = "triggerReceiver"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0017
            goto L_0x000b
        L_0x0017:
            r1 = 10
            goto L_0x008c
        L_0x001b:
            java.lang.String r1 = "postLayout"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0024
            goto L_0x000b
        L_0x0024:
            r1 = 9
            goto L_0x008c
        L_0x0028:
            java.lang.String r1 = "viewTransitionOnCross"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0031
            goto L_0x000b
        L_0x0031:
            r1 = 8
            goto L_0x008c
        L_0x0035:
            java.lang.String r1 = "triggerSlack"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x003e
            goto L_0x000b
        L_0x003e:
            r1 = 7
            goto L_0x008c
        L_0x0040:
            java.lang.String r1 = "viewTransitionOnNegativeCross"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0049
            goto L_0x000b
        L_0x0049:
            r1 = 6
            goto L_0x008c
        L_0x004b:
            java.lang.String r1 = "triggerCollisionView"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0054
            goto L_0x000b
        L_0x0054:
            r1 = 5
            goto L_0x008c
        L_0x0056:
            java.lang.String r1 = "negativeCross"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x005f
            goto L_0x000b
        L_0x005f:
            r1 = 4
            goto L_0x008c
        L_0x0061:
            java.lang.String r1 = "triggerID"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x006a
            goto L_0x000b
        L_0x006a:
            r1 = 3
            goto L_0x008c
        L_0x006c:
            java.lang.String r1 = "triggerCollisionId"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0075
            goto L_0x000b
        L_0x0075:
            r1 = 2
            goto L_0x008c
        L_0x0077:
            java.lang.String r1 = "viewTransitionOnPositiveCross"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x0080
            goto L_0x000b
        L_0x0080:
            r1 = 1
            goto L_0x008c
        L_0x0082:
            java.lang.String r1 = "positiveCross"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L_0x008b
            goto L_0x000b
        L_0x008b:
            r1 = 0
        L_0x008c:
            switch(r1) {
                case 0: goto L_0x00ae;
                case 1: goto L_0x00ab;
                case 2: goto L_0x00a8;
                case 3: goto L_0x00a5;
                case 4: goto L_0x00a2;
                case 5: goto L_0x009f;
                case 6: goto L_0x009c;
                case 7: goto L_0x0099;
                case 8: goto L_0x0096;
                case 9: goto L_0x0093;
                case 10: goto L_0x0090;
                default: goto L_0x008f;
            }
        L_0x008f:
            return r0
        L_0x0090:
            r1 = 311(0x137, float:4.36E-43)
            return r1
        L_0x0093:
            r1 = 304(0x130, float:4.26E-43)
            return r1
        L_0x0096:
            r1 = 301(0x12d, float:4.22E-43)
            return r1
        L_0x0099:
            r1 = 305(0x131, float:4.27E-43)
            return r1
        L_0x009c:
            r1 = 303(0x12f, float:4.25E-43)
            return r1
        L_0x009f:
            r1 = 306(0x132, float:4.29E-43)
            return r1
        L_0x00a2:
            r1 = 310(0x136, float:4.34E-43)
            return r1
        L_0x00a5:
            r1 = 308(0x134, float:4.32E-43)
            return r1
        L_0x00a8:
            r1 = 307(0x133, float:4.3E-43)
            return r1
        L_0x00ab:
            r1 = 302(0x12e, float:4.23E-43)
            return r1
        L_0x00ae:
            r1 = 309(0x135, float:4.33E-43)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.key.MotionKeyTrigger.getId(java.lang.String):int");
    }

    public MotionKeyTrigger copy(MotionKey motionKey) {
        super.copy(motionKey);
        MotionKeyTrigger motionKeyTrigger = (MotionKeyTrigger) motionKey;
        this.mCurveFit = motionKeyTrigger.mCurveFit;
        this.mCross = motionKeyTrigger.mCross;
        this.mTriggerReceiver = motionKeyTrigger.mTriggerReceiver;
        this.mNegativeCross = motionKeyTrigger.mNegativeCross;
        this.mPositiveCross = motionKeyTrigger.mPositiveCross;
        this.mTriggerID = motionKeyTrigger.mTriggerID;
        this.mTriggerCollisionId = motionKeyTrigger.mTriggerCollisionId;
        this.mTriggerSlack = motionKeyTrigger.mTriggerSlack;
        this.mFireCrossReset = motionKeyTrigger.mFireCrossReset;
        this.mFireNegativeReset = motionKeyTrigger.mFireNegativeReset;
        this.mFirePositiveReset = motionKeyTrigger.mFirePositiveReset;
        this.mFireThreshold = motionKeyTrigger.mFireThreshold;
        this.mFireLastPos = motionKeyTrigger.mFireLastPos;
        this.mPostLayout = motionKeyTrigger.mPostLayout;
        this.mCollisionRect = motionKeyTrigger.mCollisionRect;
        this.mTargetRect = motionKeyTrigger.mTargetRect;
        return this;
    }

    public MotionKey clone() {
        return new MotionKeyTrigger().copy((MotionKey) this);
    }

    private void fireCustom(String str, MotionWidget motionWidget) {
        CustomVariable customVariable;
        boolean z = str.length() == 1;
        if (!z) {
            str = str.substring(1).toLowerCase(Locale.ROOT);
        }
        for (String str2 : this.mCustom.keySet()) {
            String lowerCase = str2.toLowerCase(Locale.ROOT);
            if ((z || lowerCase.matches(str)) && (customVariable = (CustomVariable) this.mCustom.get(str2)) != null) {
                customVariable.applyToWidget(motionWidget);
            }
        }
    }

    public boolean setValue(int i, int i2) {
        if (i == 307) {
            this.mTriggerCollisionId = i2;
            return true;
        } else if (i == 308) {
            this.mTriggerID = toInt(Integer.valueOf(i2));
            return true;
        } else if (i != 311) {
            switch (i) {
                case 301:
                    this.mViewTransitionOnCross = i2;
                    return true;
                case 302:
                    this.mViewTransitionOnPositiveCross = i2;
                    return true;
                case 303:
                    this.mViewTransitionOnNegativeCross = i2;
                    return true;
                default:
                    return super.setValue(i, i2);
            }
        } else {
            this.mTriggerReceiver = i2;
            return true;
        }
    }

    public boolean setValue(int i, float f) {
        if (i != 305) {
            return super.setValue(i, f);
        }
        this.mTriggerSlack = f;
        return true;
    }

    public boolean setValue(int i, String str) {
        if (i == 309) {
            this.mPositiveCross = str;
            return true;
        } else if (i == 310) {
            this.mNegativeCross = str;
            return true;
        } else if (i != 312) {
            return super.setValue(i, str);
        } else {
            this.mCross = str;
            return true;
        }
    }

    public boolean setValue(int i, boolean z) {
        if (i != 304) {
            return super.setValue(i, z);
        }
        this.mPostLayout = z;
        return true;
    }
}
