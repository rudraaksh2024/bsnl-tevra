package com.google.mlkit.vision.barcode.bundled.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.Image;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzao;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzap;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaq;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzar;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzas;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzau;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzav;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzax;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzay;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaz;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzba;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbc;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbk;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbu;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcd;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.libraries.barhopper.BarhopperV3;
import com.google.android.libraries.barhopper.RecognitionOptions;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.barhopper.deeplearning.zze;
import com.google.barhopper.deeplearning.zzf;
import com.google.barhopper.deeplearning.zzh;
import com.google.barhopper.deeplearning.zzi;
import com.google.barhopper.deeplearning.zzk;
import com.google.barhopper.deeplearning.zzm;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import com.google.photos.vision.barhopper.zzac;
import com.google.photos.vision.barhopper.zzad;
import com.google.photos.vision.barhopper.zzae;
import com.google.photos.vision.barhopper.zzag;
import com.google.photos.vision.barhopper.zzaj;
import com.google.photos.vision.barhopper.zzc;
import com.google.photos.vision.barhopper.zzn;
import com.google.photos.vision.barhopper.zzp;
import com.google.photos.vision.barhopper.zzr;
import com.google.photos.vision.barhopper.zzt;
import com.google.photos.vision.barhopper.zzy;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzb extends zzbk {
    private static final int[] zza = {5, 7, 7, 7, 5, 5};
    private static final double[][] zzb = {new double[]{0.075d, 1.0d}, new double[]{0.1d, 1.0d}, new double[]{0.125d, 1.0d}, new double[]{0.2d, 2.0d}, new double[]{0.2d, 0.5d}, new double[]{0.15d, 1.0d}, new double[]{0.2d, 1.0d}, new double[]{0.25d, 1.0d}, new double[]{0.35d, 2.0d}, new double[]{0.35d, 0.5d}, new double[]{0.35d, 3.0d}, new double[]{0.35d, 0.3333d}, new double[]{0.3d, 1.0d}, new double[]{0.4d, 1.0d}, new double[]{0.5d, 1.0d}, new double[]{0.5d, 2.0d}, new double[]{0.5d, 0.5d}, new double[]{0.5d, 3.0d}, new double[]{0.5d, 0.3333d}, new double[]{0.6d, 1.0d}, new double[]{0.8d, 1.0d}, new double[]{1.0d, 1.0d}, new double[]{0.65d, 2.0d}, new double[]{0.65d, 0.5d}, new double[]{0.65d, 3.0d}, new double[]{0.65d, 0.3333d}, new double[]{1.0d, 1.0d}, new double[]{0.8d, 2.0d}, new double[]{0.8d, 0.5d}, new double[]{0.8d, 3.0d}, new double[]{0.8d, 0.3333d}, new double[]{1.0d, 1.0d}, new double[]{0.95d, 2.0d}, new double[]{0.95d, 0.5d}, new double[]{0.95d, 3.0d}, new double[]{0.95d, 0.3333d}};
    private final Context zzc;
    private final RecognitionOptions zzd;
    private BarhopperV3 zze;

    zzb(Context context, zzbc zzbc) {
        RecognitionOptions recognitionOptions = new RecognitionOptions();
        this.zzd = recognitionOptions;
        this.zzc = context;
        recognitionOptions.setBarcodeFormats(zzbc.zza());
        recognitionOptions.setOutputUnrecognizedBarcodes(zzbc.zzb());
    }

    private static zzap zze(zzn zzn, String str, String str2) {
        String str3 = null;
        if (zzn == null || str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile(str2).matcher(str);
        int zzf = zzn.zzf();
        int zzd2 = zzn.zzd();
        int zza2 = zzn.zza();
        int zzb2 = zzn.zzb();
        int zzc2 = zzn.zzc();
        int zze2 = zzn.zze();
        boolean zzj = zzn.zzj();
        if (matcher.find()) {
            str3 = matcher.group(1);
        }
        return new zzap(zzf, zzd2, zza2, zzb2, zzc2, zze2, zzj, str3);
    }

    private final BarhopperProto$BarhopperResponse zzf(ByteBuffer byteBuffer, zzbu zzbu) {
        BarhopperV3 barhopperV3 = (BarhopperV3) Preconditions.checkNotNull(this.zze);
        if (((ByteBuffer) Preconditions.checkNotNull(byteBuffer)).isDirect()) {
            return barhopperV3.recognize(zzbu.zzd(), zzbu.zza(), byteBuffer, this.zzd);
        }
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            return barhopperV3.recognize(zzbu.zzd(), zzbu.zza(), byteBuffer.array(), this.zzd);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return barhopperV3.recognize(zzbu.zzd(), zzbu.zza(), bArr, this.zzd);
    }

    public final List zzb(IObjectWrapper iObjectWrapper, zzbu zzbu) {
        BarhopperProto$BarhopperResponse barhopperProto$BarhopperResponse;
        zzat zzat;
        zzaw zzaw;
        zzax zzax;
        zzaz zzaz;
        zzay zzay;
        zzau zzau;
        zzaq zzaq;
        Iterator it;
        zzar zzar;
        int i;
        zzas zzas;
        int i2;
        Point[] pointArr;
        int i3;
        int i4;
        int i5;
        zzaw[] zzawArr;
        zzat[] zzatArr;
        zzao[] zzaoArr;
        zzbu zzbu2 = zzbu;
        int zzb2 = zzbu.zzb();
        int i6 = -1;
        int i7 = 0;
        if (zzb2 != -1) {
            if (zzb2 != 17) {
                if (zzb2 == 35) {
                    barhopperProto$BarhopperResponse = zzf(((Image) Preconditions.checkNotNull((Image) ObjectWrapper.unwrap(iObjectWrapper))).getPlanes()[0].getBuffer(), zzbu2);
                } else if (zzb2 != 842094169) {
                    throw new IllegalArgumentException("Unsupported image format: " + zzbu.zzb());
                }
            }
            barhopperProto$BarhopperResponse = zzf((ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper), zzbu2);
        } else {
            barhopperProto$BarhopperResponse = ((BarhopperV3) Preconditions.checkNotNull(this.zze)).recognize((Bitmap) ObjectWrapper.unwrap(iObjectWrapper), this.zzd);
        }
        ArrayList arrayList = new ArrayList();
        Matrix uprightRotationMatrix = ImageUtils.getInstance().getUprightRotationMatrix(zzbu.zzd(), zzbu.zza(), zzbu.zzc());
        Iterator it2 = barhopperProto$BarhopperResponse.zzc().iterator();
        while (it2.hasNext()) {
            zzc zzc2 = (zzc) it2.next();
            if (zzc2.zza() > 0 && uprightRotationMatrix != null) {
                float[] fArr = new float[8];
                List zzo = zzc2.zzo();
                int zza2 = zzc2.zza();
                for (int i8 = i7; i8 < zza2; i8++) {
                    int i9 = i8 + i8;
                    fArr[i9] = (float) ((zzae) zzo.get(i8)).zza();
                    fArr[i9 + 1] = (float) ((zzae) zzo.get(i8)).zzb();
                }
                uprightRotationMatrix.mapPoints(fArr);
                int zzc3 = zzbu.zzc();
                for (int i10 = i7; i10 < zza2; i10++) {
                    com.google.photos.vision.barhopper.zzb zzb3 = (com.google.photos.vision.barhopper.zzb) zzc2.zzG();
                    int i11 = i10 + i10;
                    zzad zzc4 = zzae.zzc();
                    zzc4.zza((int) fArr[i11]);
                    zzc4.zzb((int) fArr[i11 + 1]);
                    zzb3.zza((i10 + zzc3) % zza2, (zzae) zzc4.zzj());
                    zzc2 = (zzc) zzb3.zzj();
                }
            }
            if (zzc2.zzt()) {
                zzy zzh = zzc2.zzh();
                zzat = new zzat(zzh.zzf() + i6, zzh.zzc(), zzh.zze(), zzh.zzd());
            } else {
                zzat = null;
            }
            if (zzc2.zzv()) {
                zzci zzb4 = zzc2.zzb();
                zzaw = new zzaw(zzb4.zzd() + i6, zzb4.zzc());
            } else {
                zzaw = null;
            }
            if (zzc2.zzw()) {
                zzag zzj = zzc2.zzj();
                zzax = new zzax(zzj.zzc(), zzj.zzd());
            } else {
                zzax = null;
            }
            if (zzc2.zzy()) {
                com.google.photos.vision.barhopper.zzao zzl = zzc2.zzl();
                zzaz = new zzaz(zzl.zzd(), zzl.zzc(), zzl.zze() + i6);
            } else {
                zzaz = null;
            }
            if (zzc2.zzx()) {
                zzaj zzk = zzc2.zzk();
                zzay = new zzay(zzk.zzc(), zzk.zzd());
            } else {
                zzay = null;
            }
            if (zzc2.zzu()) {
                zzac zzi = zzc2.zzi();
                zzau = new zzau(zzi.zza(), zzi.zzb());
            } else {
                zzau = null;
            }
            if (zzc2.zzq()) {
                zzp zzd2 = zzc2.zzd();
                zzaq = new zzaq(zzd2.zzj(), zzd2.zze(), zzd2.zzf(), zzd2.zzh(), zzd2.zzi(), zze(zzd2.zzb(), zzc2.zzm().zzn() ? zzc2.zzm().zzu() : null, "DTSTART:([0-9TZ]*)"), zze(zzd2.zza(), zzc2.zzm().zzn() ? zzc2.zzm().zzu() : null, "DTEND:([0-9TZ]*)"));
            } else {
                zzaq = null;
            }
            if (zzc2.zzr()) {
                zzr zze2 = zzc2.zze();
                zzcd zza3 = zze2.zza();
                zzav zzav = zza3 != null ? new zzav(zza3.zzd(), zza3.zzi(), zza3.zzh(), zza3.zzc(), zza3.zzf(), zza3.zze(), zza3.zzj()) : null;
                String zzd3 = zze2.zzd();
                String zze3 = zze2.zze();
                List zzi2 = zze2.zzi();
                if (zzi2.isEmpty()) {
                    zzawArr = null;
                } else {
                    zzawArr = new zzaw[zzi2.size()];
                    for (int i12 = i7; i12 < zzi2.size(); i12++) {
                        zzawArr[i12] = new zzaw(((zzci) zzi2.get(i12)).zzd() + i6, ((zzci) zzi2.get(i12)).zzc());
                    }
                }
                List zzh2 = zze2.zzh();
                if (zzh2.isEmpty()) {
                    it = it2;
                    zzatArr = null;
                } else {
                    zzat[] zzatArr2 = new zzat[zzh2.size()];
                    int i13 = 0;
                    while (i13 < zzh2.size()) {
                        zzatArr2[i13] = new zzat(((zzy) zzh2.get(i13)).zzf() + i6, ((zzy) zzh2.get(i13)).zzc(), ((zzy) zzh2.get(i13)).zze(), ((zzy) zzh2.get(i13)).zzd());
                        i13++;
                        zzbu zzbu3 = zzbu;
                        it2 = it2;
                        i6 = -1;
                    }
                    it = it2;
                    zzatArr = zzatArr2;
                }
                String[] strArr = (String[]) zze2.zzj().toArray(new String[0]);
                List zzf = zze2.zzf();
                if (zzf.isEmpty()) {
                    zzaoArr = null;
                } else {
                    zzaoArr = new zzao[zzf.size()];
                    int i14 = 0;
                    while (i14 < zzf.size()) {
                        zzaoArr[i14] = new zzao(((zzcb) zzf.get(i14)).zzc() - 1, (String[]) ((zzcb) zzf.get(i14)).zzb().toArray(new String[0]));
                        i14++;
                        zzf = zzf;
                    }
                }
                i = 0;
                zzar = new zzar(zzav, zzd3, zze3, zzawArr, zzatArr, strArr, zzaoArr);
            } else {
                it = it2;
                i = i7;
                zzar = null;
            }
            if (zzc2.zzs()) {
                zzt zzf2 = zzc2.zzf();
                zzas = new zzas(zzf2.zzi(), zzf2.zzk(), zzf2.zzq(), zzf2.zzo(), zzf2.zzl(), zzf2.zze(), zzf2.zzc(), zzf2.zzd(), zzf2.zzf(), zzf2.zzp(), zzf2.zzm(), zzf2.zzj(), zzf2.zzh(), zzf2.zzn());
            } else {
                zzas = null;
            }
            switch (zzc2.zzz() - 1) {
                case 0:
                    i2 = i;
                    break;
                case 1:
                    i2 = 1;
                    break;
                case 2:
                    i2 = 2;
                    break;
                case 3:
                    i2 = 4;
                    break;
                case 4:
                    i2 = 8;
                    break;
                case 5:
                    i5 = 16;
                    break;
                case 6:
                    i5 = 32;
                    break;
                case 7:
                    i5 = 64;
                    break;
                case 8:
                    i5 = 128;
                    break;
                case 9:
                    i5 = 256;
                    break;
                case 10:
                    i5 = 512;
                    break;
                case 11:
                    i5 = 1024;
                    break;
                case 12:
                    i5 = 2048;
                    break;
                case 13:
                    i5 = 4096;
                    break;
                default:
                    i2 = -1;
                    break;
            }
            i2 = i5;
            String zzn = zzc2.zzn();
            String zzu = zzc2.zzm().zzn() ? zzc2.zzm().zzu() : null;
            byte[] zzx = zzc2.zzm().zzx();
            List zzo2 = zzc2.zzo();
            if (zzo2.isEmpty()) {
                pointArr = null;
            } else {
                pointArr = new Point[zzo2.size()];
                for (int i15 = i; i15 < zzo2.size(); i15++) {
                    pointArr[i15] = new Point(((zzae) zzo2.get(i15)).zza(), ((zzae) zzo2.get(i15)).zzb());
                }
            }
            switch (zzc2.zzA() - 1) {
                case 1:
                    i3 = 1;
                    continue;
                case 2:
                    i3 = 2;
                    continue;
                case 3:
                    i4 = 3;
                    break;
                case 4:
                    i3 = 4;
                    continue;
                case 5:
                    i4 = 5;
                    break;
                case 6:
                    i4 = 6;
                    break;
                case 7:
                    i4 = 7;
                    break;
                case 8:
                    i3 = 8;
                    continue;
                case 9:
                    i4 = 9;
                    break;
                case 10:
                    i4 = 10;
                    break;
                case 11:
                    i4 = 11;
                    break;
                case 12:
                    i4 = 12;
                    break;
                default:
                    i3 = i;
                    continue;
            }
            i3 = i4;
            arrayList.add(new zzba(i2, zzn, zzu, zzx, pointArr, i3, zzat, zzaw, zzax, zzaz, zzay, zzau, zzaq, zzar, zzas));
            zzbu zzbu4 = zzbu;
            i7 = i;
            i6 = -1;
            it2 = it;
        }
        return arrayList;
    }

    public final void zzc() {
        InputStream open;
        if (this.zze == null) {
            this.zze = new BarhopperV3();
            zzh zza2 = zzi.zza();
            zze zza3 = zzf.zza();
            int i = 16;
            int i2 = 0;
            for (int i3 = 0; i3 < 6; i3++) {
                com.google.barhopper.deeplearning.zzb zza4 = com.google.barhopper.deeplearning.zzc.zza();
                zza4.zzc(i);
                zza4.zzd(i);
                for (int i4 = 0; i4 < zza[i3]; i4++) {
                    double[] dArr = zzb[i2];
                    float sqrt = (float) Math.sqrt(dArr[1]);
                    float f = (float) (dArr[0] * 320.0d);
                    zza4.zza(f / sqrt);
                    zza4.zzb(f * sqrt);
                    i2++;
                }
                i += i;
                zza3.zza(zza4);
            }
            zza2.zza(zza3);
            try {
                InputStream open2 = this.zzc.getAssets().open("mlkit_barcode_models/barcode_ssd_mobilenet_v1_dmp25_quant.tflite");
                try {
                    InputStream open3 = this.zzc.getAssets().open("mlkit_barcode_models/oned_auto_regressor_mobile.tflite");
                    try {
                        open = this.zzc.getAssets().open("mlkit_barcode_models/oned_feature_extractor_mobile.tflite");
                        zzk zza5 = BarhopperV3Options.zza();
                        zza2.zzb(zzdb.zzs(open2));
                        zza5.zza(zza2);
                        zzm zza6 = com.google.barhopper.deeplearning.zzn.zza();
                        zza6.zza(zzdb.zzs(open3));
                        zza6.zzb(zzdb.zzs(open));
                        zza5.zzb(zza6);
                        ((BarhopperV3) Preconditions.checkNotNull(this.zze)).create((BarhopperV3Options) zza5.zzj());
                        if (open != null) {
                            open.close();
                        }
                        if (open3 != null) {
                            open3.close();
                        }
                        if (open2 != null) {
                            open2.close();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        if (open3 != null) {
                            open3.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    if (open2 != null) {
                        open2.close();
                    }
                    throw th2;
                }
            } catch (IOException e) {
                throw new IllegalStateException("Failed to open Barcode models", e);
            } catch (Throwable th3) {
                zza.zza(th2, th3);
            }
        } else {
            return;
        }
        throw th;
    }

    public final void zzd() {
        BarhopperV3 barhopperV3 = this.zze;
        if (barhopperV3 != null) {
            barhopperV3.close();
            this.zze = null;
        }
    }
}
