package androidx.camera.core.impl;

public class CameraConfigs {
    private static final CameraConfig EMPTY_CONFIG = new EmptyCameraConfig();

    public static CameraConfig emptyConfig() {
        return EMPTY_CONFIG;
    }

    static final class EmptyCameraConfig implements CameraConfig {
        private final Identifier mIdentifier = Identifier.create(new Object());

        EmptyCameraConfig() {
        }

        public Identifier getCompatibilityId() {
            return this.mIdentifier;
        }

        public Config getConfig() {
            return OptionsBundle.emptyBundle();
        }
    }

    private CameraConfigs() {
    }
}
