package com.google.mlkit.vision.barcode.internal;

import android.util.SparseArray;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzte;
import com.google.android.gms.internal.mlkit_vision_barcode.zztf;
import com.google.android.gms.internal.mlkit_vision_barcode.zzth;
import com.google.android.gms.internal.mlkit_vision_barcode.zztx;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzb {
    static final AtomicReference zza = new AtomicReference();
    private static final SparseArray zzb;
    private static final SparseArray zzc;
    private static final Map zzd;

    static {
        SparseArray sparseArray = new SparseArray();
        zzb = sparseArray;
        SparseArray sparseArray2 = new SparseArray();
        zzc = sparseArray2;
        sparseArray.put(-1, zzpv.FORMAT_UNKNOWN);
        sparseArray.put(1, zzpv.FORMAT_CODE_128);
        sparseArray.put(2, zzpv.FORMAT_CODE_39);
        sparseArray.put(4, zzpv.FORMAT_CODE_93);
        sparseArray.put(8, zzpv.FORMAT_CODABAR);
        sparseArray.put(16, zzpv.FORMAT_DATA_MATRIX);
        sparseArray.put(32, zzpv.FORMAT_EAN_13);
        sparseArray.put(64, zzpv.FORMAT_EAN_8);
        sparseArray.put(128, zzpv.FORMAT_ITF);
        sparseArray.put(256, zzpv.FORMAT_QR_CODE);
        sparseArray.put(512, zzpv.FORMAT_UPC_A);
        sparseArray.put(1024, zzpv.FORMAT_UPC_E);
        sparseArray.put(2048, zzpv.FORMAT_PDF417);
        sparseArray.put(4096, zzpv.FORMAT_AZTEC);
        sparseArray2.put(0, zzpw.TYPE_UNKNOWN);
        sparseArray2.put(1, zzpw.TYPE_CONTACT_INFO);
        sparseArray2.put(2, zzpw.TYPE_EMAIL);
        sparseArray2.put(3, zzpw.TYPE_ISBN);
        sparseArray2.put(4, zzpw.TYPE_PHONE);
        sparseArray2.put(5, zzpw.TYPE_PRODUCT);
        sparseArray2.put(6, zzpw.TYPE_SMS);
        sparseArray2.put(7, zzpw.TYPE_TEXT);
        sparseArray2.put(8, zzpw.TYPE_URL);
        sparseArray2.put(9, zzpw.TYPE_WIFI);
        sparseArray2.put(10, zzpw.TYPE_GEO);
        sparseArray2.put(11, zzpw.TYPE_CALENDAR_EVENT);
        sparseArray2.put(12, zzpw.TYPE_DRIVER_LICENSE);
        HashMap hashMap = new HashMap();
        zzd = hashMap;
        hashMap.put(1, zzte.CODE_128);
        hashMap.put(2, zzte.CODE_39);
        hashMap.put(4, zzte.CODE_93);
        hashMap.put(8, zzte.CODABAR);
        hashMap.put(16, zzte.DATA_MATRIX);
        hashMap.put(32, zzte.EAN_13);
        hashMap.put(64, zzte.EAN_8);
        hashMap.put(128, zzte.ITF);
        hashMap.put(256, zzte.QR_CODE);
        hashMap.put(512, zzte.UPC_A);
        hashMap.put(1024, zzte.UPC_E);
        hashMap.put(2048, zzte.PDF417);
        hashMap.put(4096, zzte.AZTEC);
    }

    public static zzpv zza(int i) {
        zzpv zzpv = (zzpv) zzb.get(i);
        return zzpv == null ? zzpv.FORMAT_UNKNOWN : zzpv;
    }

    public static zzpw zzb(int i) {
        zzpw zzpw = (zzpw) zzc.get(i);
        return zzpw == null ? zzpw.TYPE_UNKNOWN : zzpw;
    }

    public static zzth zzc(BarcodeScannerOptions barcodeScannerOptions) {
        int zza2 = barcodeScannerOptions.zza();
        zzcs zzcs = new zzcs();
        if (zza2 == 0) {
            zzcs.zze(zzd.values());
        } else {
            for (Map.Entry entry : zzd.entrySet()) {
                if ((((Integer) entry.getKey()).intValue() & zza2) != 0) {
                    zzcs.zzd((zzte) entry.getValue());
                }
            }
        }
        zztf zztf = new zztf();
        zztf.zzb(zzcs.zzf());
        return zztf.zzc();
    }

    public static String zzd() {
        return true != zzf() ? "play-services-mlkit-barcode-scanning" : "barcode-scanning";
    }

    static void zze(zztx zztx, zzpj zzpj) {
        zztx.zzf(new zza(zzpj), zzpk.ON_DEVICE_BARCODE_LOAD);
    }

    static boolean zzf() {
        AtomicReference atomicReference = zza;
        if (atomicReference.get() != null) {
            return ((Boolean) atomicReference.get()).booleanValue();
        }
        boolean zzd2 = zzn.zzd(MlKitContext.getInstance().getApplicationContext());
        atomicReference.set(Boolean.valueOf(zzd2));
        return zzd2;
    }
}
