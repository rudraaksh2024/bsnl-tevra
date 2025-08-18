package bsnl.bsnl_teevra;

import android.app.Dialog;
import android.os.Handler;
import bsnl.bsnl_teevra.FMS_Provision_Activity;
import bsnl.bsnl_teevra.utils.BarcodeScannerHelper;
import com.google.android.material.textfield.TextInputLayout;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FMS_Provision_Activity$7$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ FMS_Provision_Activity.AnonymousClass7.AnonymousClass2 f$0;
    public final /* synthetic */ Handler f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ TextInputLayout f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ BarcodeScannerHelper[] f$5;
    public final /* synthetic */ Dialog f$6;

    public /* synthetic */ FMS_Provision_Activity$7$2$$ExternalSyntheticLambda0(FMS_Provision_Activity.AnonymousClass7.AnonymousClass2 r1, Handler handler, Runnable runnable, TextInputLayout textInputLayout, String str, BarcodeScannerHelper[] barcodeScannerHelperArr, Dialog dialog) {
        this.f$0 = r1;
        this.f$1 = handler;
        this.f$2 = runnable;
        this.f$3 = textInputLayout;
        this.f$4 = str;
        this.f$5 = barcodeScannerHelperArr;
        this.f$6 = dialog;
    }

    public final void run() {
        this.f$0.m248lambda$onBarcodeDetected$0$bsnlbsnl_teevraFMS_Provision_Activity$7$2(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
