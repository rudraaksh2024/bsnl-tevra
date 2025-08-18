package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class zzjd implements ObjectEncoder {
    static final zzjd zza = new zzjd();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;
    private static final FieldDescriptor zzk;
    private static final FieldDescriptor zzl;
    private static final FieldDescriptor zzm;
    private static final FieldDescriptor zzn;
    private static final FieldDescriptor zzo;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("appId");
        zzbk zzbk = new zzbk();
        zzbk.zza(1);
        zzb = builder.withProperty(zzbk.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("appVersion");
        zzbk zzbk2 = new zzbk();
        zzbk2.zza(2);
        zzc = builder2.withProperty(zzbk2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzbk zzbk3 = new zzbk();
        zzbk3.zza(3);
        zzd = builder3.withProperty(zzbk3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzbk zzbk4 = new zzbk();
        zzbk4.zza(4);
        zze = builder4.withProperty(zzbk4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzbk zzbk5 = new zzbk();
        zzbk5.zza(5);
        zzf = builder5.withProperty(zzbk5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzbk zzbk6 = new zzbk();
        zzbk6.zza(6);
        zzg = builder6.withProperty(zzbk6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("apiKey");
        zzbk zzbk7 = new zzbk();
        zzbk7.zza(7);
        zzh = builder7.withProperty(zzbk7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzbk zzbk8 = new zzbk();
        zzbk8.zza(8);
        zzi = builder8.withProperty(zzbk8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzbk zzbk9 = new zzbk();
        zzbk9.zza(9);
        zzj = builder9.withProperty(zzbk9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzbk zzbk10 = new zzbk();
        zzbk10.zza(10);
        zzk = builder10.withProperty(zzbk10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzbk zzbk11 = new zzbk();
        zzbk11.zza(11);
        zzl = builder11.withProperty(zzbk11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzbk zzbk12 = new zzbk();
        zzbk12.zza(12);
        zzm = builder12.withProperty(zzbk12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzbk zzbk13 = new zzbk();
        zzbk13.zza(13);
        zzn = builder13.withProperty(zzbk13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzbk zzbk14 = new zzbk();
        zzbk14.zza(14);
        zzo = builder14.withProperty(zzbk14.zzb()).build();
    }

    private zzjd() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzon zzon = (zzon) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzon.zzg());
        objectEncoderContext.add(zzc, (Object) zzon.zzh());
        objectEncoderContext.add(zzd, (Object) null);
        objectEncoderContext.add(zze, (Object) zzon.zzj());
        objectEncoderContext.add(zzf, (Object) zzon.zzk());
        objectEncoderContext.add(zzg, (Object) null);
        objectEncoderContext.add(zzh, (Object) null);
        objectEncoderContext.add(zzi, (Object) zzon.zza());
        objectEncoderContext.add(zzj, (Object) zzon.zzi());
        objectEncoderContext.add(zzk, (Object) zzon.zzb());
        objectEncoderContext.add(zzl, (Object) zzon.zzd());
        objectEncoderContext.add(zzm, (Object) zzon.zzc());
        objectEncoderContext.add(zzn, (Object) zzon.zze());
        objectEncoderContext.add(zzo, (Object) zzon.zzf());
    }
}
