package com.google.android.gms.internal.mlkit_common;

import android.system.OsConstants;
import android.system.StructStat;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class zzq {
    final long zza;
    final long zzb;
    final boolean zzc;

    private zzq(long j, long j2, boolean z) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = z;
    }

    static zzq zza(FileDescriptor fileDescriptor) throws IOException {
        StructStat structStat = (StructStat) zzc(new zzl(fileDescriptor));
        return new zzq(structStat.st_dev, structStat.st_ino, OsConstants.S_ISLNK(structStat.st_mode));
    }

    static zzq zzb(String str) throws IOException {
        StructStat structStat = (StructStat) zzc(new zzk(str));
        return new zzq(structStat.st_dev, structStat.st_ino, OsConstants.S_ISLNK(structStat.st_mode));
    }

    private static Object zzc(Callable callable) throws IOException {
        try {
            return callable.call();
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }
}
