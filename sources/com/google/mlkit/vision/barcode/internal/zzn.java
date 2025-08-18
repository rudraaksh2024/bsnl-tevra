package com.google.mlkit.vision.barcode.internal;

import android.content.Context;
import android.media.Image;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.barcode.ModuleDescriptor;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpj;
import com.google.android.gms.internal.mlkit_vision_barcode.zztx;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvt;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwc;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzn implements zzl {
    private static final zzcv zza = zzcv.zzh(OptionalModuleUtils.BARCODE_MODULE_ID, OptionalModuleUtils.TFLITE_DYNAMITE_MODULE_ID);
    private boolean zzb;
    private boolean zzc;
    private boolean zzd;
    private final Context zze;
    private final BarcodeScannerOptions zzf;
    private final zztx zzg;
    private zzvt zzh;

    zzn(Context context, BarcodeScannerOptions barcodeScannerOptions, zztx zztx) {
        this.zze = context;
        this.zzf = barcodeScannerOptions;
        this.zzg = zztx;
    }

    static boolean zzd(Context context) {
        return DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID) > 0;
    }

    public final List zza(InputImage inputImage) throws MlKitException {
        if (this.zzh == null) {
            zzc();
        }
        zzvt zzvt = (zzvt) Preconditions.checkNotNull(this.zzh);
        if (!this.zzb) {
            try {
                zzvt.zze();
                this.zzb = true;
            } catch (RemoteException e) {
                throw new MlKitException("Failed to init barcode scanner.", 13, e);
            }
        }
        int width = inputImage.getWidth();
        if (inputImage.getFormat() == 35) {
            width = ((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()))[0].getRowStride();
        }
        try {
            List<zzvj> zzd2 = zzvt.zzd(ImageUtils.getInstance().getImageDataWrapper(inputImage), new zzwc(inputImage.getFormat(), width, inputImage.getHeight(), CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees()), SystemClock.elapsedRealtime()));
            ArrayList arrayList = new ArrayList();
            for (zzvj zzm : zzd2) {
                arrayList.add(new Barcode(new zzm(zzm), inputImage.getCoordinatesMatrix()));
            }
            return arrayList;
        } catch (RemoteException e2) {
            throw new MlKitException("Failed to run barcode scanner.", 13, e2);
        }
    }

    public final void zzb() {
        zzvt zzvt = this.zzh;
        if (zzvt != null) {
            try {
                zzvt.zzf();
            } catch (RemoteException e) {
                Log.e("DecoupledBarcodeScanner", "Failed to release barcode scanner.", e);
            }
            this.zzh = null;
            this.zzb = false;
        }
    }

    public final boolean zzc() throws MlKitException {
        if (this.zzh != null) {
            return this.zzc;
        }
        if (zzd(this.zze)) {
            this.zzc = true;
            try {
                this.zzh = zze(DynamiteModule.PREFER_LOCAL, ModuleDescriptor.MODULE_ID, "com.google.mlkit.vision.barcode.bundled.internal.ThickBarcodeScannerCreator");
            } catch (DynamiteModule.LoadingException e) {
                throw new MlKitException("Failed to load the bundled barcode module.", 13, e);
            } catch (RemoteException e2) {
                throw new MlKitException("Failed to create thick barcode scanner.", 13, e2);
            }
        } else {
            this.zzc = false;
            if (!OptionalModuleUtils.areAllRequiredModulesAvailable(this.zze, (List<String>) zza)) {
                if (!this.zzd) {
                    OptionalModuleUtils.requestDownload(this.zze, (List<String>) zzcv.zzh(OptionalModuleUtils.BARCODE, OptionalModuleUtils.TFLITE_DYNAMITE));
                    this.zzd = true;
                }
                zzb.zze(this.zzg, zzpj.OPTIONAL_MODULE_NOT_AVAILABLE);
                throw new MlKitException("Waiting for the barcode module to be downloaded. Please wait.", 14);
            }
            try {
                this.zzh = zze(DynamiteModule.PREFER_REMOTE, OptionalModuleUtils.BARCODE_MODULE_ID, "com.google.android.gms.vision.barcode.mlkit.BarcodeScannerCreator");
            } catch (RemoteException | DynamiteModule.LoadingException e3) {
                zzb.zze(this.zzg, zzpj.OPTIONAL_MODULE_INIT_ERROR);
                throw new MlKitException("Failed to create thin barcode scanner.", 13, e3);
            }
        }
        zzb.zze(this.zzg, zzpj.NO_ERROR);
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final zzvt zze(DynamiteModule.VersionPolicy versionPolicy, String str, String str2) throws DynamiteModule.LoadingException, RemoteException {
        zzvw zza2 = zzvv.zza(DynamiteModule.load(this.zze, versionPolicy, str).instantiate(str2));
        IObjectWrapper wrap = ObjectWrapper.wrap(this.zze);
        int zza3 = this.zzf.zza();
        boolean z = true;
        if (!this.zzf.zzd() && this.zzf.zzb() == null) {
            z = false;
        }
        return zza2.zzd(wrap, new zzvl(zza3, z));
    }
}
