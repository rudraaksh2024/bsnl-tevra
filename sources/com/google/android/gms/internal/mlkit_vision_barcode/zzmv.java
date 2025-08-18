package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzmv implements ObjectEncoder {
    static final zzmv zza = new zzmv();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("appName");
        zzfc zzfc = new zzfc();
        zzfc.zza(1);
        zzb = builder.withProperty(zzfc.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("sessionId");
        zzfc zzfc2 = new zzfc();
        zzfc2.zza(2);
        zzc = builder2.withProperty(zzfc2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("startZoomLevel");
        zzfc zzfc3 = new zzfc();
        zzfc3.zza(3);
        zzd = builder3.withProperty(zzfc3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("endZoomLevel");
        zzfc zzfc4 = new zzfc();
        zzfc4.zza(4);
        zze = builder4.withProperty(zzfc4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("durationMs");
        zzfc zzfc5 = new zzfc();
        zzfc5.zza(5);
        zzf = builder5.withProperty(zzfc5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("predictedArea");
        zzfc zzfc6 = new zzfc();
        zzfc6.zza(6);
        zzg = builder6.withProperty(zzfc6.zzb()).build();
    }

    private zzmv() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzsg zzsg = (zzsg) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzsg.zze());
        objectEncoderContext.add(zzc, (Object) zzsg.zzf());
        objectEncoderContext.add(zzd, (Object) zzsg.zzc());
        objectEncoderContext.add(zze, (Object) zzsg.zzb());
        objectEncoderContext.add(zzf, (Object) zzsg.zzd());
        objectEncoderContext.add(zzg, (Object) zzsg.zza());
    }
}
