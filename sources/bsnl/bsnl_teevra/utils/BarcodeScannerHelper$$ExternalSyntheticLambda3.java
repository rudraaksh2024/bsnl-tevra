package bsnl.bsnl_teevra.utils;

import androidx.camera.core.ImageProxy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BarcodeScannerHelper$$ExternalSyntheticLambda3 implements OnCompleteListener {
    public final /* synthetic */ ImageProxy f$0;

    public /* synthetic */ BarcodeScannerHelper$$ExternalSyntheticLambda3(ImageProxy imageProxy) {
        this.f$0 = imageProxy;
    }

    public final void onComplete(Task task) {
        this.f$0.close();
    }
}
