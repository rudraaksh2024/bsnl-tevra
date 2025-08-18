package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzjm implements ObjectEncoder {
    static final zzjm zza = new zzjm();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("maxMs");
        zzfc zzfc = new zzfc();
        zzfc.zza(1);
        zzb = builder.withProperty(zzfc.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("minMs");
        zzfc zzfc2 = new zzfc();
        zzfc2.zza(2);
        zzc = builder2.withProperty(zzfc2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("avgMs");
        zzfc zzfc3 = new zzfc();
        zzfc3.zza(3);
        zzd = builder3.withProperty(zzfc3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("firstQuartileMs");
        zzfc zzfc4 = new zzfc();
        zzfc4.zza(4);
        zze = builder4.withProperty(zzfc4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("medianMs");
        zzfc zzfc5 = new zzfc();
        zzfc5.zza(5);
        zzf = builder5.withProperty(zzfc5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("thirdQuartileMs");
        zzfc zzfc6 = new zzfc();
        zzfc6.zza(6);
        zzg = builder6.withProperty(zzfc6.zzb()).build();
    }

    private zzjm() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzol zzol = (zzol) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzol.zzc());
        objectEncoderContext.add(zzc, (Object) zzol.zze());
        objectEncoderContext.add(zzd, (Object) zzol.zza());
        objectEncoderContext.add(zze, (Object) zzol.zzb());
        objectEncoderContext.add(zzf, (Object) zzol.zzd());
        objectEncoderContext.add(zzg, (Object) zzol.zzf());
    }
}
