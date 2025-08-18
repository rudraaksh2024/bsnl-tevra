package androidx.camera.camera2.internal.compat.workaround;

import android.hardware.camera2.CameraCharacteristics;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.FlashAvailabilityBufferUnderflowQuirk;
import androidx.camera.core.Logger;
import java.nio.BufferUnderflowException;

public final class FlashAvailabilityChecker {
    private static final String TAG = "FlashAvailability";

    public static boolean isFlashAvailable(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        if (DeviceQuirks.get(FlashAvailabilityBufferUnderflowQuirk.class) == null) {
            return checkFlashAvailabilityNormally(cameraCharacteristicsCompat);
        }
        Class<FlashAvailabilityBufferUnderflowQuirk> cls = FlashAvailabilityBufferUnderflowQuirk.class;
        Logger.d(TAG, "Device has quirk FlashAvailabilityBufferUnderflowQuirk. Checking for flash availability safely...");
        return checkFlashAvailabilityWithPossibleBufferUnderflow(cameraCharacteristicsCompat);
    }

    private static boolean checkFlashAvailabilityWithPossibleBufferUnderflow(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        try {
            return checkFlashAvailabilityNormally(cameraCharacteristicsCompat);
        } catch (BufferUnderflowException unused) {
            return false;
        }
    }

    private static boolean checkFlashAvailabilityNormally(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        Boolean bool = (Boolean) cameraCharacteristicsCompat.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        if (bool == null) {
            Logger.w(TAG, "Characteristics did not contain key FLASH_INFO_AVAILABLE. Flash is not available.");
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private FlashAvailabilityChecker() {
    }
}
