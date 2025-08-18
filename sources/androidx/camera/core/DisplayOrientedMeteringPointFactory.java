package androidx.camera.core;

import android.view.Display;
import androidx.camera.core.impl.CameraInfoInternal;

public final class DisplayOrientedMeteringPointFactory extends MeteringPointFactory {
    private final CameraInfo mCameraInfo;
    private final Display mDisplay;
    private final float mHeight;
    private final float mWidth;

    public DisplayOrientedMeteringPointFactory(Display display, CameraInfo cameraInfo, float f, float f2) {
        this.mWidth = f;
        this.mHeight = f2;
        this.mDisplay = display;
        this.mCameraInfo = cameraInfo;
    }

    private Integer getLensFacing() {
        CameraInfo cameraInfo = this.mCameraInfo;
        if (cameraInfo instanceof CameraInfoInternal) {
            return ((CameraInfoInternal) cameraInfo).getLensFacing();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.PointF convertPoint(float r8, float r9) {
        /*
            r7 = this;
            float r0 = r7.mWidth
            float r1 = r7.mHeight
            java.lang.Integer r2 = r7.getLensFacing()
            if (r2 == 0) goto L_0x0012
            int r2 = r2.intValue()
            if (r2 != 0) goto L_0x0012
            r2 = 1
            goto L_0x0013
        L_0x0012:
            r2 = 0
        L_0x0013:
            int r7 = r7.getRelativeCameraOrientation(r2)
            r3 = 270(0x10e, float:3.78E-43)
            r4 = 90
            if (r7 == r4) goto L_0x0026
            if (r7 != r3) goto L_0x0020
            goto L_0x0026
        L_0x0020:
            r5 = r9
            r9 = r8
            r8 = r5
            r6 = r1
            r1 = r0
            r0 = r6
        L_0x0026:
            if (r7 == r4) goto L_0x0034
            r4 = 180(0xb4, float:2.52E-43)
            if (r7 == r4) goto L_0x0032
            if (r7 == r3) goto L_0x002f
            goto L_0x0036
        L_0x002f:
            float r9 = r1 - r9
            goto L_0x0036
        L_0x0032:
            float r9 = r1 - r9
        L_0x0034:
            float r8 = r0 - r8
        L_0x0036:
            if (r2 == 0) goto L_0x003a
            float r9 = r1 - r9
        L_0x003a:
            float r9 = r9 / r1
            float r8 = r8 / r0
            android.graphics.PointF r7 = new android.graphics.PointF
            r7.<init>(r9, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.DisplayOrientedMeteringPointFactory.convertPoint(float, float):android.graphics.PointF");
    }

    private int getRelativeCameraOrientation(boolean z) {
        try {
            int sensorRotationDegrees = this.mCameraInfo.getSensorRotationDegrees(this.mDisplay.getRotation());
            return z ? (360 - sensorRotationDegrees) % 360 : sensorRotationDegrees;
        } catch (Exception unused) {
            return 0;
        }
    }
}
