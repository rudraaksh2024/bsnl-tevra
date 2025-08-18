package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzlq implements ObjectEncoder {
    static final zzlq zza = new zzlq();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzfc zzfc = new zzfc();
        zzfc.zza(1);
        zzb = builder.withProperty(zzfc.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("options");
        zzfc zzfc2 = new zzfc();
        zzfc2.zza(2);
        zzc = builder2.withProperty(zzfc2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("imageInfo");
        zzfc zzfc3 = new zzfc();
        zzfc3.zza(3);
        zzd = builder3.withProperty(zzfc3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("labelCount");
        zzfc zzfc4 = new zzfc();
        zzfc4.zza(4);
        zze = builder4.withProperty(zzfc4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("highestConfidence");
        zzfc zzfc5 = new zzfc();
        zzfc5.zza(5);
        zzf = builder5.withProperty(zzfc5.zzb()).build();
    }

    private zzlq() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzra zzra = (zzra) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
