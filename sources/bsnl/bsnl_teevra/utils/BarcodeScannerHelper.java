package bsnl.bsnl_teevra.utils;

import android.content.Context;
import android.util.Size;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarcodeScannerHelper {
    private final ExecutorService cameraExecutor = Executors.newSingleThreadExecutor();
    private ProcessCameraProvider cameraProvider;
    private final Context context;
    private final LifecycleOwner lifecycleOwner;
    private final OnBarcodeDetectedListener listener;
    private Preview preview;
    private final PreviewView previewView;
    private final BarcodeScanner scanner = BarcodeScanning.getClient(new BarcodeScannerOptions.Builder().setBarcodeFormats(0, new int[0]).build());

    public interface OnBarcodeDetectedListener {
        void onBarcodeDetected(String str);
    }

    public BarcodeScannerHelper(Context context2, LifecycleOwner lifecycleOwner2, PreviewView previewView2, OnBarcodeDetectedListener onBarcodeDetectedListener) {
        this.context = context2;
        this.lifecycleOwner = lifecycleOwner2;
        this.previewView = previewView2;
        this.listener = onBarcodeDetectedListener;
    }

    public void startCamera() {
        ListenableFuture<ProcessCameraProvider> instance = ProcessCameraProvider.getInstance(this.context);
        instance.addListener(new BarcodeScannerHelper$$ExternalSyntheticLambda4(this, instance), ContextCompat.getMainExecutor(this.context));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startCamera$1$bsnl-bsnl_teevra-utils-BarcodeScannerHelper  reason: not valid java name */
    public /* synthetic */ void m252lambda$startCamera$1$bsnlbsnl_teevrautilsBarcodeScannerHelper(ListenableFuture listenableFuture) {
        try {
            this.cameraProvider = (ProcessCameraProvider) listenableFuture.get();
            this.preview = new Preview.Builder().build();
            CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
            ImageAnalysis build = new ImageAnalysis.Builder().setTargetResolution(new Size(1280, 720)).setBackpressureStrategy(0).build();
            build.setAnalyzer(this.cameraExecutor, new BarcodeScannerHelper$$ExternalSyntheticLambda0(this));
            this.cameraProvider.unbindAll();
            this.cameraProvider.bindToLifecycle(this.lifecycleOwner, cameraSelector, this.preview, build);
            this.preview.setSurfaceProvider(this.previewView.getSurfaceProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startCamera$0$bsnl-bsnl_teevra-utils-BarcodeScannerHelper  reason: not valid java name */
    public /* synthetic */ void m251lambda$startCamera$0$bsnlbsnl_teevrautilsBarcodeScannerHelper(ImageProxy imageProxy) {
        processImageProxy(this.scanner, imageProxy);
    }

    private void processImageProxy(BarcodeScanner barcodeScanner, ImageProxy imageProxy) {
        barcodeScanner.process(InputImage.fromMediaImage(imageProxy.getImage(), imageProxy.getImageInfo().getRotationDegrees())).addOnSuccessListener(new BarcodeScannerHelper$$ExternalSyntheticLambda1(this)).addOnFailureListener(new BarcodeScannerHelper$$ExternalSyntheticLambda2()).addOnCompleteListener(new BarcodeScannerHelper$$ExternalSyntheticLambda3(imageProxy));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$processImageProxy$2$bsnl-bsnl_teevra-utils-BarcodeScannerHelper  reason: not valid java name */
    public /* synthetic */ void m249lambda$processImageProxy$2$bsnlbsnl_teevrautilsBarcodeScannerHelper(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String rawValue = ((Barcode) it.next()).getRawValue();
            if (rawValue != null) {
                this.listener.onBarcodeDetected(rawValue);
                return;
            }
        }
    }

    public void shutdown() {
        ProcessCameraProvider processCameraProvider = this.cameraProvider;
        if (processCameraProvider != null) {
            processCameraProvider.unbindAll();
            this.cameraProvider = null;
        }
        Preview preview2 = this.preview;
        if (preview2 != null) {
            preview2.setSurfaceProvider((Preview.SurfaceProvider) null);
            this.preview = null;
        }
        this.previewView.post(new BarcodeScannerHelper$$ExternalSyntheticLambda5(this));
        this.cameraExecutor.shutdown();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$shutdown$4$bsnl-bsnl_teevra-utils-BarcodeScannerHelper  reason: not valid java name */
    public /* synthetic */ void m250lambda$shutdown$4$bsnlbsnl_teevrautilsBarcodeScannerHelper() {
        this.previewView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.previewView.setVisibility(8);
        this.previewView.invalidate();
    }
}
