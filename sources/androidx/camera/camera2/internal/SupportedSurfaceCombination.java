package androidx.camera.camera2.internal;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.util.Rational;
import android.util.Size;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.internal.compat.workaround.ExcludedSupportedSizesContainer;
import androidx.camera.camera2.internal.compat.workaround.ExtraSupportedSurfaceCombinationsContainer;
import androidx.camera.camera2.internal.compat.workaround.ResolutionCorrector;
import androidx.camera.camera2.internal.compat.workaround.TargetAspectRatio;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.SurfaceCombination;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.SurfaceSizeDefinition;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class SupportedSurfaceCombination {
    private static final String TAG = "SupportedSurfaceCombination";
    private final CamcorderProfileHelper mCamcorderProfileHelper;
    private final String mCameraId;
    private final CameraCharacteristicsCompat mCharacteristics;
    private final DisplayInfoManager mDisplayInfoManager;
    private final Map<Integer, List<Size>> mExcludedSizeListCache = new HashMap();
    private final ExcludedSupportedSizesContainer mExcludedSupportedSizesContainer;
    private final ExtraSupportedSurfaceCombinationsContainer mExtraSupportedSurfaceCombinationsContainer;
    private final int mHardwareLevel;
    private boolean mIsBurstCaptureSupported;
    private boolean mIsRawSupported;
    private final boolean mIsSensorLandscapeResolution;
    private final Map<Integer, Size> mMaxSizeCache = new HashMap();
    private Map<Integer, Size[]> mOutputSizesCache;
    private final ResolutionCorrector mResolutionCorrector;
    private final List<SurfaceCombination> mSurfaceCombinations = new ArrayList();
    SurfaceSizeDefinition mSurfaceSizeDefinition;

    private void checkCustomization() {
    }

    SupportedSurfaceCombination(Context context, String str, CameraManagerCompat cameraManagerCompat, CamcorderProfileHelper camcorderProfileHelper) throws CameraUnavailableException {
        this.mIsRawSupported = false;
        this.mIsBurstCaptureSupported = false;
        this.mOutputSizesCache = new HashMap();
        this.mResolutionCorrector = new ResolutionCorrector();
        String str2 = (String) Preconditions.checkNotNull(str);
        this.mCameraId = str2;
        this.mCamcorderProfileHelper = (CamcorderProfileHelper) Preconditions.checkNotNull(camcorderProfileHelper);
        this.mExcludedSupportedSizesContainer = new ExcludedSupportedSizesContainer(str);
        this.mExtraSupportedSurfaceCombinationsContainer = new ExtraSupportedSurfaceCombinationsContainer();
        this.mDisplayInfoManager = DisplayInfoManager.getInstance(context);
        try {
            CameraCharacteristicsCompat cameraCharacteristicsCompat = cameraManagerCompat.getCameraCharacteristicsCompat(str2);
            this.mCharacteristics = cameraCharacteristicsCompat;
            Integer num = (Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            this.mHardwareLevel = num != null ? num.intValue() : 2;
            this.mIsSensorLandscapeResolution = isSensorLandscapeResolution();
            int[] iArr = (int[]) cameraCharacteristicsCompat.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
            if (iArr != null) {
                for (int i : iArr) {
                    if (i == 3) {
                        this.mIsRawSupported = true;
                    } else if (i == 6) {
                        this.mIsBurstCaptureSupported = true;
                    }
                }
            }
            generateSupportedCombinationList();
            generateSurfaceSizeDefinition();
            checkCustomization();
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    /* access modifiers changed from: package-private */
    public String getCameraId() {
        return this.mCameraId;
    }

    /* access modifiers changed from: package-private */
    public boolean isRawSupported() {
        return this.mIsRawSupported;
    }

    /* access modifiers changed from: package-private */
    public boolean isBurstCaptureSupported() {
        return this.mIsBurstCaptureSupported;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0007 A[LOOP:0: B:1:0x0007->B:4:0x0017, LOOP_START, PHI: r0 
      PHI: (r0v1 boolean) = (r0v0 boolean), (r0v5 boolean) binds: [B:0:0x0000, B:4:0x0017] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean checkSupported(java.util.List<androidx.camera.core.impl.SurfaceConfig> r3) {
        /*
            r2 = this;
            java.util.List<androidx.camera.core.impl.SurfaceCombination> r2 = r2.mSurfaceCombinations
            java.util.Iterator r2 = r2.iterator()
            r0 = 0
        L_0x0007:
            boolean r1 = r2.hasNext()
            if (r1 == 0) goto L_0x0019
            java.lang.Object r0 = r2.next()
            androidx.camera.core.impl.SurfaceCombination r0 = (androidx.camera.core.impl.SurfaceCombination) r0
            boolean r0 = r0.isSupported(r3)
            if (r0 == 0) goto L_0x0007
        L_0x0019:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.SupportedSurfaceCombination.checkSupported(java.util.List):boolean");
    }

    /* access modifiers changed from: package-private */
    public SurfaceConfig transformSurfaceConfig(int i, Size size) {
        return SurfaceConfig.transformSurfaceConfig(i, size, this.mSurfaceSizeDefinition);
    }

    /* access modifiers changed from: package-private */
    public Map<UseCaseConfig<?>, Size> getSuggestedResolutions(List<AttachedSurfaceInfo> list, List<UseCaseConfig<?>> list2) {
        HashMap hashMap;
        refreshPreviewSize();
        ArrayList arrayList = new ArrayList();
        for (AttachedSurfaceInfo surfaceConfig : list) {
            arrayList.add(surfaceConfig.getSurfaceConfig());
        }
        for (UseCaseConfig<?> inputFormat : list2) {
            arrayList.add(SurfaceConfig.transformSurfaceConfig(inputFormat.getInputFormat(), new Size(640, 480), this.mSurfaceSizeDefinition));
        }
        if (checkSupported(arrayList)) {
            List<Integer> useCasesPriorityOrder = getUseCasesPriorityOrder(list2);
            ArrayList arrayList2 = new ArrayList();
            for (Integer intValue : useCasesPriorityOrder) {
                arrayList2.add(getSupportedOutputSizes(list2.get(intValue.intValue())));
            }
            Iterator<List<Size>> it = getAllPossibleSizeArrangements(arrayList2).iterator();
            while (true) {
                if (!it.hasNext()) {
                    hashMap = null;
                    break;
                }
                List next = it.next();
                ArrayList arrayList3 = new ArrayList();
                for (AttachedSurfaceInfo surfaceConfig2 : list) {
                    arrayList3.add(surfaceConfig2.getSurfaceConfig());
                }
                for (int i = 0; i < next.size(); i++) {
                    arrayList3.add(SurfaceConfig.transformSurfaceConfig(list2.get(useCasesPriorityOrder.get(i).intValue()).getInputFormat(), (Size) next.get(i), this.mSurfaceSizeDefinition));
                }
                if (checkSupported(arrayList3)) {
                    hashMap = new HashMap();
                    for (UseCaseConfig next2 : list2) {
                        hashMap.put(next2, (Size) next.get(useCasesPriorityOrder.indexOf(Integer.valueOf(list2.indexOf(next2)))));
                    }
                }
            }
            if (hashMap != null) {
                return hashMap;
            }
            throw new IllegalArgumentException("No supported surface combination is found for camera device - Id : " + this.mCameraId + " and Hardware level: " + this.mHardwareLevel + ". May be the specified resolution is too large and not supported. Existing surfaces: " + list + " New configs: " + list2);
        }
        throw new IllegalArgumentException("No supported surface combination is found for camera device - Id : " + this.mCameraId + ".  May be attempting to bind too many use cases. Existing surfaces: " + list + " New configs: " + list2);
    }

    private Rational getTargetAspectRatio(ImageOutputConfig imageOutputConfig) {
        Rational rational;
        int i = new TargetAspectRatio().get(this.mCameraId, this.mCharacteristics);
        if (i == 0) {
            rational = this.mIsSensorLandscapeResolution ? AspectRatioUtil.ASPECT_RATIO_4_3 : AspectRatioUtil.ASPECT_RATIO_3_4;
        } else if (i == 1) {
            rational = this.mIsSensorLandscapeResolution ? AspectRatioUtil.ASPECT_RATIO_16_9 : AspectRatioUtil.ASPECT_RATIO_9_16;
        } else if (i == 2) {
            Size fetchMaxSize = fetchMaxSize(256);
            return new Rational(fetchMaxSize.getWidth(), fetchMaxSize.getHeight());
        } else if (i != 3) {
            return null;
        } else {
            Size targetSize = getTargetSize(imageOutputConfig);
            if (imageOutputConfig.hasTargetAspectRatio()) {
                int targetAspectRatio = imageOutputConfig.getTargetAspectRatio();
                if (targetAspectRatio == 0) {
                    rational = this.mIsSensorLandscapeResolution ? AspectRatioUtil.ASPECT_RATIO_4_3 : AspectRatioUtil.ASPECT_RATIO_3_4;
                } else if (targetAspectRatio != 1) {
                    Logger.e(TAG, "Undefined target aspect ratio: " + targetAspectRatio);
                    return null;
                } else {
                    rational = this.mIsSensorLandscapeResolution ? AspectRatioUtil.ASPECT_RATIO_16_9 : AspectRatioUtil.ASPECT_RATIO_9_16;
                }
            } else if (targetSize != null) {
                return new Rational(targetSize.getWidth(), targetSize.getHeight());
            } else {
                return null;
            }
        }
        return rational;
    }

    private Size fetchMaxSize(int i) {
        Size size = this.mMaxSizeCache.get(Integer.valueOf(i));
        if (size != null) {
            return size;
        }
        Size maxOutputSizeByFormat = getMaxOutputSizeByFormat(i);
        this.mMaxSizeCache.put(Integer.valueOf(i), maxOutputSizeByFormat);
        return maxOutputSizeByFormat;
    }

    private List<Integer> getUseCasesPriorityOrder(List<UseCaseConfig<?>> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (UseCaseConfig<?> surfaceOccupancyPriority : list) {
            int surfaceOccupancyPriority2 = surfaceOccupancyPriority.getSurfaceOccupancyPriority(0);
            if (!arrayList2.contains(Integer.valueOf(surfaceOccupancyPriority2))) {
                arrayList2.add(Integer.valueOf(surfaceOccupancyPriority2));
            }
        }
        Collections.sort(arrayList2);
        Collections.reverse(arrayList2);
        for (Integer intValue : arrayList2) {
            int intValue2 = intValue.intValue();
            for (UseCaseConfig next : list) {
                if (intValue2 == next.getSurfaceOccupancyPriority(0)) {
                    arrayList.add(Integer.valueOf(list.indexOf(next)));
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<Size> getSupportedOutputSizes(UseCaseConfig<?> useCaseConfig) {
        int inputFormat = useCaseConfig.getInputFormat();
        ImageOutputConfig imageOutputConfig = (ImageOutputConfig) useCaseConfig;
        Size[] customizedSupportSizesFromConfig = getCustomizedSupportSizesFromConfig(inputFormat, imageOutputConfig);
        if (customizedSupportSizesFromConfig == null) {
            customizedSupportSizesFromConfig = getAllOutputSizesByFormat(inputFormat);
        }
        ArrayList arrayList = new ArrayList();
        Size maxResolution = imageOutputConfig.getMaxResolution((Size) null);
        Size maxOutputSizeByFormat = getMaxOutputSizeByFormat(inputFormat);
        if (maxResolution == null || SizeUtil.getArea(maxOutputSizeByFormat) < SizeUtil.getArea(maxResolution)) {
            maxResolution = maxOutputSizeByFormat;
        }
        Arrays.sort(customizedSupportSizesFromConfig, new CompareSizesByArea(true));
        Size targetSize = getTargetSize(imageOutputConfig);
        Size size = SizeUtil.RESOLUTION_VGA;
        int area = SizeUtil.getArea(SizeUtil.RESOLUTION_VGA);
        if (SizeUtil.getArea(maxResolution) < area) {
            size = SizeUtil.RESOLUTION_ZERO;
        } else if (targetSize != null && SizeUtil.getArea(targetSize) < area) {
            size = targetSize;
        }
        for (Size size2 : customizedSupportSizesFromConfig) {
            if (SizeUtil.getArea(size2) <= SizeUtil.getArea(maxResolution) && SizeUtil.getArea(size2) >= SizeUtil.getArea(size) && !arrayList.contains(size2)) {
                arrayList.add(size2);
            }
        }
        if (!arrayList.isEmpty()) {
            Rational targetAspectRatio = getTargetAspectRatio(imageOutputConfig);
            if (targetSize == null) {
                targetSize = imageOutputConfig.getDefaultResolution((Size) null);
            }
            ArrayList arrayList2 = new ArrayList();
            new HashMap();
            if (targetAspectRatio == null) {
                arrayList2.addAll(arrayList);
                if (targetSize != null) {
                    removeSupportedSizesByTargetSize(arrayList2, targetSize);
                }
            } else {
                Map<Rational, List<Size>> groupSizesByAspectRatio = groupSizesByAspectRatio(arrayList);
                if (targetSize != null) {
                    for (Rational rational : groupSizesByAspectRatio.keySet()) {
                        removeSupportedSizesByTargetSize(groupSizesByAspectRatio.get(rational), targetSize);
                    }
                }
                ArrayList<Rational> arrayList3 = new ArrayList<>(groupSizesByAspectRatio.keySet());
                Collections.sort(arrayList3, new AspectRatioUtil.CompareAspectRatiosByDistanceToTargetRatio(targetAspectRatio));
                for (Rational rational2 : arrayList3) {
                    for (Size size3 : groupSizesByAspectRatio.get(rational2)) {
                        if (!arrayList2.contains(size3)) {
                            arrayList2.add(size3);
                        }
                    }
                }
            }
            return this.mResolutionCorrector.insertOrPrioritize(SurfaceConfig.getConfigType(useCaseConfig.getInputFormat()), arrayList2);
        }
        throw new IllegalArgumentException("Can not get supported output size under supported maximum for the format: " + inputFormat);
    }

    private Size getTargetSize(ImageOutputConfig imageOutputConfig) {
        return flipSizeByRotation(imageOutputConfig.getTargetResolution((Size) null), imageOutputConfig.getTargetRotation(0));
    }

    private Size flipSizeByRotation(Size size, int i) {
        return (size == null || !isRotationNeeded(i)) ? size : new Size(size.getHeight(), size.getWidth());
    }

    private boolean isRotationNeeded(int i) {
        Integer num = (Integer) this.mCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        Preconditions.checkNotNull(num, "Camera HAL in bad state, unable to retrieve the SENSOR_ORIENTATION");
        int surfaceRotationToDegrees = CameraOrientationUtil.surfaceRotationToDegrees(i);
        Integer num2 = (Integer) this.mCharacteristics.get(CameraCharacteristics.LENS_FACING);
        Preconditions.checkNotNull(num2, "Camera HAL in bad state, unable to retrieve the LENS_FACING");
        int relativeImageRotation = CameraOrientationUtil.getRelativeImageRotation(surfaceRotationToDegrees, num.intValue(), 1 == num2.intValue());
        if (relativeImageRotation == 90 || relativeImageRotation == 270) {
            return true;
        }
        return false;
    }

    private boolean isSensorLandscapeResolution() {
        Size size = (Size) this.mCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
        if (size == null || size.getWidth() >= size.getHeight()) {
            return true;
        }
        return false;
    }

    private Map<Rational, List<Size>> groupSizesByAspectRatio(List<Size> list) {
        HashMap hashMap = new HashMap();
        hashMap.put(AspectRatioUtil.ASPECT_RATIO_4_3, new ArrayList());
        hashMap.put(AspectRatioUtil.ASPECT_RATIO_16_9, new ArrayList());
        for (Size next : list) {
            Rational rational = null;
            for (Rational rational2 : hashMap.keySet()) {
                if (AspectRatioUtil.hasMatchingAspectRatio(next, rational2)) {
                    List list2 = (List) hashMap.get(rational2);
                    if (!list2.contains(next)) {
                        list2.add(next);
                    }
                    rational = rational2;
                }
            }
            if (rational == null) {
                hashMap.put(new Rational(next.getWidth(), next.getHeight()), new ArrayList(Collections.singleton(next)));
            }
        }
        return hashMap;
    }

    private void removeSupportedSizesByTargetSize(List<Size> list, Size size) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            int i = -1;
            int i2 = 0;
            while (true) {
                int i3 = i2;
                int i4 = i;
                i = i3;
                if (i >= list.size()) {
                    break;
                }
                Size size2 = list.get(i);
                if (size2.getWidth() < size.getWidth() || size2.getHeight() < size.getHeight()) {
                    break;
                }
                if (i4 >= 0) {
                    arrayList.add(list.get(i4));
                }
                i2 = i + 1;
            }
            list.removeAll(arrayList);
        }
    }

    private List<List<Size>> getAllPossibleSizeArrangements(List<List<Size>> list) {
        int i = 1;
        for (List<Size> size : list) {
            i *= size.size();
        }
        if (i != 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(new ArrayList());
            }
            int size2 = i / list.get(0).size();
            int i3 = i;
            for (int i4 = 0; i4 < list.size(); i4++) {
                List list2 = list.get(i4);
                for (int i5 = 0; i5 < i; i5++) {
                    ((List) arrayList.get(i5)).add((Size) list2.get((i5 % i3) / size2));
                }
                if (i4 < list.size() - 1) {
                    i3 = size2;
                    size2 /= list.get(i4 + 1).size();
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("Failed to find supported resolutions.");
    }

    private Size[] excludeProblematicSizes(Size[] sizeArr, int i) {
        List<Size> fetchExcludedSizes = fetchExcludedSizes(i);
        ArrayList arrayList = new ArrayList(Arrays.asList(sizeArr));
        arrayList.removeAll(fetchExcludedSizes);
        return (Size[]) arrayList.toArray(new Size[0]);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.util.Size[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Size[] getCustomizedSupportSizesFromConfig(int r4, androidx.camera.core.impl.ImageOutputConfig r5) {
        /*
            r3 = this;
            r0 = 0
            java.util.List r5 = r5.getSupportedResolutions(r0)
            if (r5 == 0) goto L_0x0026
            java.util.Iterator r5 = r5.iterator()
        L_0x000b:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0026
            java.lang.Object r1 = r5.next()
            android.util.Pair r1 = (android.util.Pair) r1
            java.lang.Object r2 = r1.first
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            if (r2 != r4) goto L_0x000b
            java.lang.Object r5 = r1.second
            r0 = r5
            android.util.Size[] r0 = (android.util.Size[]) r0
        L_0x0026:
            if (r0 == 0) goto L_0x0035
            android.util.Size[] r0 = r3.excludeProblematicSizes(r0, r4)
            androidx.camera.core.impl.utils.CompareSizesByArea r3 = new androidx.camera.core.impl.utils.CompareSizesByArea
            r4 = 1
            r3.<init>(r4)
            java.util.Arrays.sort(r0, r3)
        L_0x0035:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.SupportedSurfaceCombination.getCustomizedSupportSizesFromConfig(int, androidx.camera.core.impl.ImageOutputConfig):android.util.Size[]");
    }

    private Size[] getAllOutputSizesByFormat(int i) {
        Size[] sizeArr = this.mOutputSizesCache.get(Integer.valueOf(i));
        if (sizeArr != null) {
            return sizeArr;
        }
        Size[] doGetAllOutputSizesByFormat = doGetAllOutputSizesByFormat(i);
        this.mOutputSizesCache.put(Integer.valueOf(i), doGetAllOutputSizesByFormat);
        return doGetAllOutputSizesByFormat;
    }

    private Size[] doGetAllOutputSizesByFormat(int i) {
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(i);
            if (outputSizes != null) {
                Size[] excludeProblematicSizes = excludeProblematicSizes(outputSizes, i);
                Arrays.sort(excludeProblematicSizes, new CompareSizesByArea(true));
                return excludeProblematicSizes;
            }
            throw new IllegalArgumentException("Can not get supported output size for the format: " + i);
        }
        throw new IllegalArgumentException("Can not retrieve SCALER_STREAM_CONFIGURATION_MAP");
    }

    /* access modifiers changed from: package-private */
    public Size getMaxOutputSizeByFormat(int i) {
        return (Size) Collections.max(Arrays.asList(getAllOutputSizesByFormat(i)), new CompareSizesByArea());
    }

    private void generateSupportedCombinationList() {
        this.mSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.generateSupportedCombinationList(this.mHardwareLevel, this.mIsRawSupported, this.mIsBurstCaptureSupported));
        this.mSurfaceCombinations.addAll(this.mExtraSupportedSurfaceCombinationsContainer.get(this.mCameraId, this.mHardwareLevel));
    }

    private void generateSurfaceSizeDefinition() {
        this.mSurfaceSizeDefinition = SurfaceSizeDefinition.create(new Size(640, 480), this.mDisplayInfoManager.getPreviewSize(), getRecordSize());
    }

    private void refreshPreviewSize() {
        this.mDisplayInfoManager.refresh();
        if (this.mSurfaceSizeDefinition == null) {
            generateSurfaceSizeDefinition();
            return;
        }
        this.mSurfaceSizeDefinition = SurfaceSizeDefinition.create(this.mSurfaceSizeDefinition.getAnalysisSize(), this.mDisplayInfoManager.getPreviewSize(), this.mSurfaceSizeDefinition.getRecordSize());
    }

    private Size getRecordSize() {
        try {
            int parseInt = Integer.parseInt(this.mCameraId);
            CamcorderProfile camcorderProfile = this.mCamcorderProfileHelper.hasProfile(parseInt, 1) ? this.mCamcorderProfileHelper.get(parseInt, 1) : null;
            if (camcorderProfile != null) {
                return new Size(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
            }
            return getRecordSizeByHasProfile(parseInt);
        } catch (NumberFormatException unused) {
            return getRecordSizeFromStreamConfigurationMap();
        }
    }

    private Size getRecordSizeFromStreamConfigurationMap() {
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(MediaRecorder.class);
            if (outputSizes == null) {
                return SizeUtil.RESOLUTION_480P;
            }
            Arrays.sort(outputSizes, new CompareSizesByArea(true));
            for (Size size : outputSizes) {
                if (size.getWidth() <= SizeUtil.RESOLUTION_1080P.getWidth() && size.getHeight() <= SizeUtil.RESOLUTION_1080P.getHeight()) {
                    return size;
                }
            }
            return SizeUtil.RESOLUTION_480P;
        }
        throw new IllegalArgumentException("Can not retrieve SCALER_STREAM_CONFIGURATION_MAP");
    }

    private Size getRecordSizeByHasProfile(int i) {
        CamcorderProfile camcorderProfile;
        Size size = SizeUtil.RESOLUTION_480P;
        if (this.mCamcorderProfileHelper.hasProfile(i, 10)) {
            camcorderProfile = this.mCamcorderProfileHelper.get(i, 10);
        } else if (this.mCamcorderProfileHelper.hasProfile(i, 8)) {
            camcorderProfile = this.mCamcorderProfileHelper.get(i, 8);
        } else if (this.mCamcorderProfileHelper.hasProfile(i, 12)) {
            camcorderProfile = this.mCamcorderProfileHelper.get(i, 12);
        } else if (this.mCamcorderProfileHelper.hasProfile(i, 6)) {
            camcorderProfile = this.mCamcorderProfileHelper.get(i, 6);
        } else if (this.mCamcorderProfileHelper.hasProfile(i, 5)) {
            camcorderProfile = this.mCamcorderProfileHelper.get(i, 5);
        } else {
            camcorderProfile = this.mCamcorderProfileHelper.hasProfile(i, 4) ? this.mCamcorderProfileHelper.get(i, 4) : null;
        }
        return camcorderProfile != null ? new Size(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight) : size;
    }

    private List<Size> fetchExcludedSizes(int i) {
        List<Size> list = this.mExcludedSizeListCache.get(Integer.valueOf(i));
        if (list != null) {
            return list;
        }
        List<Size> list2 = this.mExcludedSupportedSizesContainer.get(i);
        this.mExcludedSizeListCache.put(Integer.valueOf(i), list2);
        return list2;
    }
}
