package com.google.mlkit.vision.barcode.internal;

import android.graphics.Point;
import com.google.android.gms.common.Feature;
import com.google.android.gms.internal.mlkit_vision_barcode.zzew;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpi;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpx;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpz;
import com.google.android.gms.internal.mlkit_vision_barcode.zztx;
import com.google.android.gms.internal.mlkit_vision_barcode.zzua;
import com.google.android.gms.internal.mlkit_vision_barcode.zzus;
import com.google.android.gms.internal.mlkit_vision_barcode.zzuv;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public class BarcodeScannerImpl extends MobileVisionBase<List<Barcode>> implements BarcodeScanner {
    public static final /* synthetic */ int zzc = 0;
    /* access modifiers changed from: private */
    public static final BarcodeScannerOptions zzd = new BarcodeScannerOptions.Builder().build();
    @Nullable
    final zzus zzb;
    private final boolean zze;
    private final BarcodeScannerOptions zzf;
    private int zzg;
    private boolean zzh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BarcodeScannerImpl(BarcodeScannerOptions barcodeScannerOptions, zzk zzk, Executor executor, zztx zztx, MlKitContext mlKitContext) {
        super(zzk, executor);
        zzus zzus;
        ZoomSuggestionOptions zzb2 = barcodeScannerOptions.zzb();
        if (zzb2 == null) {
            zzus = null;
        } else {
            zzus = zzus.zzd(mlKitContext.getApplicationContext(), mlKitContext.getApplicationContext().getPackageName());
            zzus.zzo(new zzf(zzb2), zzew.zza());
            if (zzb2.zza() >= 1.0f) {
                zzus.zzk(zzb2.zza());
            }
            zzus.zzm();
        }
        this.zzf = barcodeScannerOptions;
        boolean zzf2 = zzb.zzf();
        this.zze = zzf2;
        zzpx zzpx = new zzpx();
        zzpx.zzi(zzb.zzc(barcodeScannerOptions));
        zzpz zzj = zzpx.zzj();
        zzpl zzpl = new zzpl();
        zzpl.zze(zzf2 ? zzpi.TYPE_THICK : zzpi.TYPE_THIN);
        zzpl.zzg(zzj);
        zztx.zzd(zzua.zzg(zzpl, 1), zzpk.ON_DEVICE_BARCODE_CREATE);
        this.zzb = zzus;
    }

    private final Task zzf(Task task, int i, int i2) {
        return task.onSuccessTask(new zze(this, i, i2));
    }

    public final synchronized void close() {
        zzus zzus = this.zzb;
        if (zzus != null) {
            zzus.zzn(this.zzh);
            this.zzb.zzj();
        }
        super.close();
    }

    public final int getDetectorType() {
        return 1;
    }

    public final Feature[] getOptionalFeatures() {
        if (this.zze) {
            return OptionalModuleUtils.EMPTY_FEATURES;
        }
        return new Feature[]{OptionalModuleUtils.FEATURE_BARCODE};
    }

    public final Task<List<Barcode>> process(MlImage mlImage) {
        return zzf(super.processBase(mlImage), mlImage.getWidth(), mlImage.getHeight());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task zzd(int i, int i2, List list) throws Exception {
        if (this.zzb == null) {
            return Tasks.forResult(list);
        }
        boolean z = true;
        this.zzg++;
        List arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Barcode barcode = (Barcode) it.next();
            if (barcode.getFormat() == -1) {
                arrayList2.add(barcode);
            } else {
                arrayList.add(barcode);
            }
        }
        if (arrayList.isEmpty()) {
            int size = arrayList2.size();
            int i3 = 0;
            while (i3 < size) {
                Point[] cornerPoints = ((Barcode) arrayList2.get(i3)).getCornerPoints();
                if (cornerPoints != null) {
                    zzus zzus = this.zzb;
                    int i4 = this.zzg;
                    int i5 = i;
                    int i6 = i2;
                    int i7 = 0;
                    int i8 = 0;
                    for (Point point : Arrays.asList(cornerPoints)) {
                        i5 = Math.min(i5, point.x);
                        i6 = Math.min(i6, point.y);
                        i7 = Math.max(i7, point.x);
                        i8 = Math.max(i8, point.y);
                    }
                    float f = (float) i;
                    float f2 = (float) i2;
                    zzus.zzi(i4, zzuv.zzg((((float) i5) + 0.0f) / f, (((float) i6) + 0.0f) / f2, (((float) i7) + 0.0f) / f, (((float) i8) + 0.0f) / f2, 0.0f));
                } else {
                    int i9 = i;
                    int i10 = i2;
                }
                i3++;
                z = true;
            }
        } else {
            this.zzh = true;
        }
        if (z == this.zzf.zzd()) {
            arrayList = list;
        }
        return Tasks.forResult(arrayList);
    }

    public final Task<List<Barcode>> process(InputImage inputImage) {
        return zzf(super.processBase(inputImage), inputImage.getWidth(), inputImage.getHeight());
    }
}
