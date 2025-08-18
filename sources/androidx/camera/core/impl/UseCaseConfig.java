package androidx.camera.core.impl;

import android.util.Range;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExtendableBuilder;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.UseCaseEventConfig;

public interface UseCaseConfig<T extends UseCase> extends TargetConfig<T>, UseCaseEventConfig, ImageInputConfig {
    public static final Config.Option<CameraSelector> OPTION_CAMERA_SELECTOR = Config.Option.create("camerax.core.useCase.cameraSelector", CameraSelector.class);
    public static final Config.Option<CaptureConfig.OptionUnpacker> OPTION_CAPTURE_CONFIG_UNPACKER = Config.Option.create("camerax.core.useCase.captureConfigUnpacker", CaptureConfig.OptionUnpacker.class);
    public static final Config.Option<CaptureConfig> OPTION_DEFAULT_CAPTURE_CONFIG = Config.Option.create("camerax.core.useCase.defaultCaptureConfig", CaptureConfig.class);
    public static final Config.Option<SessionConfig> OPTION_DEFAULT_SESSION_CONFIG = Config.Option.create("camerax.core.useCase.defaultSessionConfig", SessionConfig.class);
    public static final Config.Option<SessionConfig.OptionUnpacker> OPTION_SESSION_CONFIG_UNPACKER = Config.Option.create("camerax.core.useCase.sessionConfigUnpacker", SessionConfig.OptionUnpacker.class);
    public static final Config.Option<Integer> OPTION_SURFACE_OCCUPANCY_PRIORITY = Config.Option.create("camerax.core.useCase.surfaceOccupancyPriority", Integer.TYPE);
    public static final Config.Option<Range<Integer>> OPTION_TARGET_FRAME_RATE = Config.Option.create("camerax.core.useCase.targetFrameRate", CameraSelector.class);
    public static final Config.Option<Boolean> OPTION_ZSL_DISABLED = Config.Option.create("camerax.core.useCase.zslDisabled", Boolean.TYPE);

    public interface Builder<T extends UseCase, C extends UseCaseConfig<T>, B> extends TargetConfig.Builder<T, B>, ExtendableBuilder<T>, UseCaseEventConfig.Builder<B> {
        C getUseCaseConfig();

        B setCameraSelector(CameraSelector cameraSelector);

        B setCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker);

        B setDefaultCaptureConfig(CaptureConfig captureConfig);

        B setDefaultSessionConfig(SessionConfig sessionConfig);

        B setSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker);

        B setSurfaceOccupancyPriority(int i);

        B setZslDisabled(boolean z);
    }

    SessionConfig getDefaultSessionConfig(SessionConfig sessionConfig) {
        return (SessionConfig) retrieveOption(OPTION_DEFAULT_SESSION_CONFIG, sessionConfig);
    }

    SessionConfig getDefaultSessionConfig() {
        return (SessionConfig) retrieveOption(OPTION_DEFAULT_SESSION_CONFIG);
    }

    CaptureConfig getDefaultCaptureConfig(CaptureConfig captureConfig) {
        return (CaptureConfig) retrieveOption(OPTION_DEFAULT_CAPTURE_CONFIG, captureConfig);
    }

    CaptureConfig getDefaultCaptureConfig() {
        return (CaptureConfig) retrieveOption(OPTION_DEFAULT_CAPTURE_CONFIG);
    }

    SessionConfig.OptionUnpacker getSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker) {
        return (SessionConfig.OptionUnpacker) retrieveOption(OPTION_SESSION_CONFIG_UNPACKER, optionUnpacker);
    }

    SessionConfig.OptionUnpacker getSessionOptionUnpacker() {
        return (SessionConfig.OptionUnpacker) retrieveOption(OPTION_SESSION_CONFIG_UNPACKER);
    }

    CaptureConfig.OptionUnpacker getCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker) {
        return (CaptureConfig.OptionUnpacker) retrieveOption(OPTION_CAPTURE_CONFIG_UNPACKER, optionUnpacker);
    }

    CaptureConfig.OptionUnpacker getCaptureOptionUnpacker() {
        return (CaptureConfig.OptionUnpacker) retrieveOption(OPTION_CAPTURE_CONFIG_UNPACKER);
    }

    int getSurfaceOccupancyPriority(int i) {
        return ((Integer) retrieveOption(OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(i))).intValue();
    }

    int getSurfaceOccupancyPriority() {
        return ((Integer) retrieveOption(OPTION_SURFACE_OCCUPANCY_PRIORITY)).intValue();
    }

    CameraSelector getCameraSelector(CameraSelector cameraSelector) {
        return (CameraSelector) retrieveOption(OPTION_CAMERA_SELECTOR, cameraSelector);
    }

    CameraSelector getCameraSelector() {
        return (CameraSelector) retrieveOption(OPTION_CAMERA_SELECTOR);
    }

    Range<Integer> getTargetFramerate(Range<Integer> range) {
        return (Range) retrieveOption(OPTION_TARGET_FRAME_RATE, range);
    }

    Range<Integer> getTargetFramerate() {
        return (Range) retrieveOption(OPTION_TARGET_FRAME_RATE);
    }

    boolean isZslDisabled(boolean z) {
        return ((Boolean) retrieveOption(OPTION_ZSL_DISABLED, Boolean.valueOf(z))).booleanValue();
    }
}
