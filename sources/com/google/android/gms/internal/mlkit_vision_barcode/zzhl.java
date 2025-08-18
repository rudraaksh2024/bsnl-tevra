package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzhl implements ObjectEncoder {
    static final zzhl zza = new zzhl();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("errorCode");
        zzfc zzfc = new zzfc();
        zzfc.zza(1);
        zzb = builder.withProperty(zzfc.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("imageInfo");
        zzfc zzfc2 = new zzfc();
        zzfc2.zza(2);
        zzc = builder2.withProperty(zzfc2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isColdCall");
        zzfc zzfc3 = new zzfc();
        zzfc3.zza(3);
        zzd = builder3.withProperty(zzfc3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("options");
        zzfc zzfc4 = new zzfc();
        zzfc4.zza(4);
        zze = builder4.withProperty(zzfc4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("documentPresenceConfidence");
        zzfc zzfc5 = new zzfc();
        zzfc5.zza(5);
        zzf = builder5.withProperty(zzfc5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("documentCornerConfidence");
        zzfc zzfc6 = new zzfc();
        zzfc6.zza(6);
        zzg = builder6.withProperty(zzfc6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("documentRotationSuggestionDegrees");
        zzfc zzfc7 = new zzfc();
        zzfc7.zza(7);
        zzh = builder7.withProperty(zzfc7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("documentRotationSuggestionConfidence");
        zzfc zzfc8 = new zzfc();
        zzfc8.zza(8);
        zzi = builder8.withProperty(zzfc8.zzb()).build();
    }

    private zzhl() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzga zzga = (zzga) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
