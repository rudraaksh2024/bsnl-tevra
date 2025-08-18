package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzmw implements ObjectEncoder {
    static final zzmw zza = new zzmw();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("xMin");
        zzfc zzfc = new zzfc();
        zzfc.zza(1);
        zzb = builder.withProperty(zzfc.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("yMin");
        zzfc zzfc2 = new zzfc();
        zzfc2.zza(2);
        zzc = builder2.withProperty(zzfc2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("xMax");
        zzfc zzfc3 = new zzfc();
        zzfc3.zza(3);
        zzd = builder3.withProperty(zzfc3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("yMax");
        zzfc zzfc4 = new zzfc();
        zzfc4.zza(4);
        zze = builder4.withProperty(zzfc4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("confidenceScore");
        zzfc zzfc5 = new zzfc();
        zzfc5.zza(5);
        zzf = builder5.withProperty(zzfc5.zzb()).build();
    }

    private zzmw() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzse zzse = (zzse) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzse.zzc());
        objectEncoderContext.add(zzc, (Object) zzse.zze());
        objectEncoderContext.add(zzd, (Object) zzse.zzb());
        objectEncoderContext.add(zze, (Object) zzse.zzd());
        objectEncoderContext.add(zzf, (Object) zzse.zza());
    }
}
