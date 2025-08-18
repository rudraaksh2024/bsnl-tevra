package com.google.mlkit.vision.barcode.internal;

import android.os.SystemClock;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzfs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzft;
import com.google.android.gms.internal.mlkit_vision_barcode.zzfv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzol;
import com.google.android.gms.internal.mlkit_vision_barcode.zzop;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoq;
import com.google.android.gms.internal.mlkit_vision_barcode.zzow;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpi;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpx;
import com.google.android.gms.internal.mlkit_vision_barcode.zztm;
import com.google.android.gms.internal.mlkit_vision_barcode.zztu;
import com.google.android.gms.internal.mlkit_vision_barcode.zztx;
import com.google.android.gms.internal.mlkit_vision_barcode.zztz;
import com.google.android.gms.internal.mlkit_vision_barcode.zzua;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.BitmapInStreamingChecker;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzk extends MLTask {
    static boolean zza = true;
    private static final ImageUtils zzb = ImageUtils.getInstance();
    private final BarcodeScannerOptions zzc;
    private final zzl zzd;
    private final zztx zze;
    private final zztz zzf;
    private final BitmapInStreamingChecker zzg = new BitmapInStreamingChecker();
    private boolean zzh;

    public zzk(MlKitContext mlKitContext, BarcodeScannerOptions barcodeScannerOptions, zzl zzl, zztx zztx) {
        Preconditions.checkNotNull(mlKitContext, "MlKitContext can not be null");
        Preconditions.checkNotNull(barcodeScannerOptions, "BarcodeScannerOptions can not be null");
        this.zzc = barcodeScannerOptions;
        this.zzd = zzl;
        this.zze = zztx;
        this.zzf = zztz.zza(mlKitContext.getApplicationContext());
    }

    private final void zzf(zzpj zzpj, long j, InputImage inputImage, List list) {
        zzcs zzcs = new zzcs();
        zzcs zzcs2 = new zzcs();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Barcode barcode = (Barcode) it.next();
                zzcs.zzd(zzb.zza(barcode.getFormat()));
                zzcs2.zzd(zzb.zzb(barcode.getValueType()));
            }
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.zze.zzf(new zzi(this, elapsedRealtime, zzpj, zzcs, zzcs2, inputImage), zzpk.ON_DEVICE_BARCODE_DETECT);
        zzft zzft = new zzft();
        zzft.zze(zzpj);
        zzft.zzf(Boolean.valueOf(zza));
        zzft.zzg(zzb.zzc(this.zzc));
        zzft.zzc(zzcs.zzf());
        zzft.zzd(zzcs2.zzf());
        MLTaskExecutor.workerThreadExecutor().execute(new zztu(this.zze, zzpk.AGGREGATED_ON_DEVICE_BARCODE_DETECTION, zzft.zzh(), elapsedRealtime, new zzj(this)));
        long currentTimeMillis = System.currentTimeMillis();
        this.zzf.zzc(true != this.zzh ? 24301 : 24302, zzpj.zza(), currentTimeMillis - elapsedRealtime, currentTimeMillis);
    }

    public final synchronized void load() throws MlKitException {
        this.zzh = this.zzd.zzc();
    }

    public final synchronized void release() {
        this.zzd.zzb();
        zza = true;
        zztx zztx = this.zze;
        zzpl zzpl = new zzpl();
        zzpl.zze(this.zzh ? zzpi.TYPE_THICK : zzpi.TYPE_THIN);
        zzpx zzpx = new zzpx();
        zzpx.zzi(zzb.zzc(this.zzc));
        zzpl.zzg(zzpx.zzj());
        zztx.zzd(zzua.zzf(zzpl), zzpk.ON_DEVICE_BARCODE_CLOSE);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zztm zzc(long j, zzpj zzpj, zzcs zzcs, zzcs zzcs2, InputImage inputImage) {
        zzoq zzoq;
        zzpx zzpx = new zzpx();
        zzow zzow = new zzow();
        zzow.zzc(Long.valueOf(j));
        zzow.zzd(zzpj);
        zzow.zze(Boolean.valueOf(zza));
        zzow.zza(true);
        zzow.zzb(true);
        zzpx.zzh(zzow.zzf());
        zzpx.zzi(zzb.zzc(this.zzc));
        zzpx.zze(zzcs.zzf());
        zzpx.zzf(zzcs2.zzf());
        int format = inputImage.getFormat();
        int mobileVisionImageSize = zzb.getMobileVisionImageSize(inputImage);
        zzop zzop = new zzop();
        if (format == -1) {
            zzoq = zzoq.BITMAP;
        } else if (format == 35) {
            zzoq = zzoq.YUV_420_888;
        } else if (format == 842094169) {
            zzoq = zzoq.YV12;
        } else if (format == 16) {
            zzoq = zzoq.NV16;
        } else if (format != 17) {
            zzoq = zzoq.UNKNOWN_FORMAT;
        } else {
            zzoq = zzoq.NV21;
        }
        zzop.zza(zzoq);
        zzop.zzb(Integer.valueOf(mobileVisionImageSize));
        zzpx.zzg(zzop.zzd());
        zzpl zzpl = new zzpl();
        zzpl.zze(this.zzh ? zzpi.TYPE_THICK : zzpi.TYPE_THIN);
        zzpl.zzg(zzpx.zzj());
        return zzua.zzf(zzpl);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zztm zzd(zzfv zzfv, int i, zzol zzol) {
        zzpl zzpl = new zzpl();
        zzpl.zze(this.zzh ? zzpi.TYPE_THICK : zzpi.TYPE_THIN);
        zzfs zzfs = new zzfs();
        zzfs.zza(Integer.valueOf(i));
        zzfs.zzc(zzfv);
        zzfs.zzb(zzol);
        zzpl.zzd(zzfs.zze());
        return zzua.zzf(zzpl);
    }

    /* renamed from: zze */
    public final synchronized List run(InputImage inputImage) throws MlKitException {
        zzpj zzpj;
        List zza2;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.zzg.check(inputImage);
        try {
            zza2 = this.zzd.zza(inputImage);
            zzf(zzpj.NO_ERROR, elapsedRealtime, inputImage, zza2);
            zza = false;
        } catch (MlKitException e) {
            MlKitException mlKitException = e;
            if (mlKitException.getErrorCode() == 14) {
                zzpj = zzpj.MODEL_NOT_DOWNLOADED;
            } else {
                zzpj = zzpj.UNKNOWN_ERROR;
            }
            zzf(zzpj, elapsedRealtime, inputImage, (List) null);
            throw mlKitException;
        }
        return zza2;
    }
}
