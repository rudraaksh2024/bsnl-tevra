package androidx.camera.camera2.internal.compat.workaround;

import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.StillCaptureFlashStopRepeatingQuirk;

public class StillCaptureFlow {
    private final boolean mShouldStopRepeatingBeforeStillCapture;

    public StillCaptureFlow() {
        this.mShouldStopRepeatingBeforeStillCapture = ((StillCaptureFlashStopRepeatingQuirk) DeviceQuirks.get(StillCaptureFlashStopRepeatingQuirk.class)) != null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0012  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldStopRepeatingBeforeCapture(java.util.List<android.hardware.camera2.CaptureRequest> r2, boolean r3) {
        /*
            r1 = this;
            boolean r1 = r1.mShouldStopRepeatingBeforeStillCapture
            r0 = 0
            if (r1 == 0) goto L_0x002c
            if (r3 != 0) goto L_0x0008
            goto L_0x002c
        L_0x0008:
            java.util.Iterator r1 = r2.iterator()
        L_0x000c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x002c
            java.lang.Object r2 = r1.next()
            android.hardware.camera2.CaptureRequest r2 = (android.hardware.camera2.CaptureRequest) r2
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE
            java.lang.Object r2 = r2.get(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r3 = 2
            if (r2 == r3) goto L_0x002a
            r3 = 3
            if (r2 != r3) goto L_0x000c
        L_0x002a:
            r1 = 1
            return r1
        L_0x002c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.compat.workaround.StillCaptureFlow.shouldStopRepeatingBeforeCapture(java.util.List, boolean):boolean");
    }
}
