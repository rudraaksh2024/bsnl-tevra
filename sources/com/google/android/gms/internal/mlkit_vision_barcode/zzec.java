package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public abstract class zzec<V> extends zzez implements zzev<V> {
    static final boolean zza;
    private static final Logger zzb;
    /* access modifiers changed from: private */
    public static final zza zzc;
    private static final Object zzd = new Object();
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile zzd listeners;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile Object value;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile zzj waiters;

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
    abstract class zza {
        /* synthetic */ zza(zzeb zzeb) {
        }

        /* access modifiers changed from: package-private */
        public abstract zzd zza(zzec zzec, zzd zzd);

        /* access modifiers changed from: package-private */
        public abstract zzj zzb(zzec zzec, zzj zzj);

        /* access modifiers changed from: package-private */
        public abstract void zzc(zzj zzj, @CheckForNull zzj zzj2);

        /* access modifiers changed from: package-private */
        public abstract void zzd(zzj zzj, Thread thread);

        /* access modifiers changed from: package-private */
        public abstract boolean zze(zzec zzec, @CheckForNull zzd zzd, zzd zzd2);

        /* access modifiers changed from: package-private */
        public abstract boolean zzf(zzec zzec, @CheckForNull Object obj, Object obj2);

        /* access modifiers changed from: package-private */
        public abstract boolean zzg(zzec zzec, @CheckForNull zzj zzj, @CheckForNull zzj zzj2);
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
    final class zzb {
        @CheckForNull
        static final zzb zza;
        @CheckForNull
        static final zzb zzb;
        final boolean zzc;
        @CheckForNull
        final Throwable zzd;

        static {
            if (zzec.zza) {
                zzb = null;
                zza = null;
                return;
            }
            zzb = new zzb(false, (Throwable) null);
            zza = new zzb(true, (Throwable) null);
        }

        zzb(boolean z, @CheckForNull Throwable th) {
            this.zzc = z;
            this.zzd = th;
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
    final class zzc {
        static final zzc zza = new zzc(new Throwable("Failure occurred while trying to finish a future.") {
            public final synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable zzb;

        zzc(Throwable th) {
            th.getClass();
            this.zzb = th;
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
    final class zzd {
        static final zzd zza = new zzd();
        @CheckForNull
        zzd next;
        @CheckForNull
        final Runnable zzb;
        @CheckForNull
        final Executor zzc;

        zzd() {
            this.zzb = null;
            this.zzc = null;
        }

        zzd(Runnable runnable, Executor executor) {
            this.zzb = runnable;
            this.zzc = executor;
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
    final class zze extends zza {
        final AtomicReferenceFieldUpdater<zzj, Thread> zza;
        final AtomicReferenceFieldUpdater<zzj, zzj> zzb;
        final AtomicReferenceFieldUpdater<zzec, zzj> zzc;
        final AtomicReferenceFieldUpdater<zzec, zzd> zzd;
        final AtomicReferenceFieldUpdater<zzec, Object> zze;

        zze(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super((zzeb) null);
            this.zza = atomicReferenceFieldUpdater;
            this.zzb = atomicReferenceFieldUpdater2;
            this.zzc = atomicReferenceFieldUpdater3;
            this.zzd = atomicReferenceFieldUpdater4;
            this.zze = atomicReferenceFieldUpdater5;
        }

        /* access modifiers changed from: package-private */
        public final zzd zza(zzec zzec, zzd zzd2) {
            return this.zzd.getAndSet(zzec, zzd2);
        }

        /* access modifiers changed from: package-private */
        public final zzj zzb(zzec zzec, zzj zzj) {
            return this.zzc.getAndSet(zzec, zzj);
        }

        /* access modifiers changed from: package-private */
        public final void zzc(zzj zzj, @CheckForNull zzj zzj2) {
            this.zzb.lazySet(zzj, zzj2);
        }

        /* access modifiers changed from: package-private */
        public final void zzd(zzj zzj, Thread thread) {
            this.zza.lazySet(zzj, thread);
        }

        /* access modifiers changed from: package-private */
        public final boolean zze(zzec zzec, @CheckForNull zzd zzd2, zzd zzd3) {
            return zzed.zza(this.zzd, zzec, zzd2, zzd3);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zzec zzec, @CheckForNull Object obj, Object obj2) {
            return zzed.zza(this.zze, zzec, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzg(zzec zzec, @CheckForNull zzj zzj, @CheckForNull zzj zzj2) {
            return zzed.zza(this.zzc, zzec, zzj, zzj2);
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
    final class zzf<V> implements Runnable {
        final zzec<V> zza;
        final zzev<? extends V> zzb;

        public final void run() {
            throw null;
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
    final class zzg extends zza {
        private zzg() {
            super((zzeb) null);
        }

        /* synthetic */ zzg(zzee zzee) {
            super((zzeb) null);
        }

        /* access modifiers changed from: package-private */
        public final zzd zza(zzec zzec, zzd zzd) {
            zzd zzb;
            synchronized (zzec) {
                zzb = zzec.listeners;
                if (zzb != zzd) {
                    zzec.listeners = zzd;
                }
            }
            return zzb;
        }

        /* access modifiers changed from: package-private */
        public final zzj zzb(zzec zzec, zzj zzj) {
            zzj zzc;
            synchronized (zzec) {
                zzc = zzec.waiters;
                if (zzc != zzj) {
                    zzec.waiters = zzj;
                }
            }
            return zzc;
        }

        /* access modifiers changed from: package-private */
        public final void zzc(zzj zzj, @CheckForNull zzj zzj2) {
            zzj.next = zzj2;
        }

        /* access modifiers changed from: package-private */
        public final void zzd(zzj zzj, Thread thread) {
            zzj.thread = thread;
        }

        /* access modifiers changed from: package-private */
        public final boolean zze(zzec zzec, @CheckForNull zzd zzd, zzd zzd2) {
            synchronized (zzec) {
                if (zzec.listeners != zzd) {
                    return false;
                }
                zzec.listeners = zzd2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zzec zzec, @CheckForNull Object obj, Object obj2) {
            synchronized (zzec) {
                if (zzec.value != obj) {
                    return false;
                }
                zzec.value = obj2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean zzg(zzec zzec, @CheckForNull zzj zzj, @CheckForNull zzj zzj2) {
            synchronized (zzec) {
                if (zzec.waiters != zzj) {
                    return false;
                }
                zzec.waiters = zzj2;
                return true;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
    interface zzh<V> extends zzev<V> {
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
    final class zzi extends zza {
        static final Unsafe zza;
        static final long zzb;
        static final long zzc;
        static final long zzd;
        static final long zze;
        static final long zzf;

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x005e, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x006a, code lost:
            throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:3:?, code lost:
            r0 = (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.android.gms.internal.mlkit_vision_barcode.zzec.zzi.AnonymousClass1());
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
        static {
            /*
                sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
                goto L_0x0010
            L_0x0005:
                com.google.android.gms.internal.mlkit_vision_barcode.zzec$zzi$1 r0 = new com.google.android.gms.internal.mlkit_vision_barcode.zzec$zzi$1     // Catch:{ PrivilegedActionException -> 0x005e }
                r0.<init>()     // Catch:{ PrivilegedActionException -> 0x005e }
                java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x005e }
                sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x005e }
            L_0x0010:
                java.lang.Class<com.google.android.gms.internal.mlkit_vision_barcode.zzec> r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzec.class
                java.lang.String r2 = "waiters"
                java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                long r2 = r0.objectFieldOffset(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zzc = r2     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                java.lang.String r2 = "listeners"
                java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                long r2 = r0.objectFieldOffset(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zzb = r2     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                java.lang.String r2 = "value"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zzd = r1     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                java.lang.Class<com.google.android.gms.internal.mlkit_vision_barcode.zzec$zzj> r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzec.zzj.class
                java.lang.String r2 = "thread"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zze = r1     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                java.lang.Class<com.google.android.gms.internal.mlkit_vision_barcode.zzec$zzj> r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzec.zzj.class
                java.lang.String r2 = "next"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zzf = r1     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zza = r0     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                return
            L_0x0055:
                r0 = move-exception
                throw r0
            L_0x0057:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                r1.<init>(r0)
                throw r1
            L_0x005e:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                java.lang.String r2 = "Could not initialize intrinsics"
                java.lang.Throwable r0 = r0.getCause()
                r1.<init>(r2, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzec.zzi.<clinit>():void");
        }

        private zzi() {
            super((zzeb) null);
        }

        /* synthetic */ zzi(zzeg zzeg) {
            super((zzeb) null);
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        final com.google.android.gms.internal.mlkit_vision_barcode.zzec.zzd zza(com.google.android.gms.internal.mlkit_vision_barcode.zzec r3, com.google.android.gms.internal.mlkit_vision_barcode.zzec.zzd r4) {
            /*
                r2 = this;
            L_0x0000:
                com.google.android.gms.internal.mlkit_vision_barcode.zzec$zzd r0 = r3.listeners
                if (r4 != r0) goto L_0x0007
                return r0
            L_0x0007:
                boolean r1 = r2.zze(r3, r0, r4)
                if (r1 == 0) goto L_0x0000
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzec.zzi.zza(com.google.android.gms.internal.mlkit_vision_barcode.zzec, com.google.android.gms.internal.mlkit_vision_barcode.zzec$zzd):com.google.android.gms.internal.mlkit_vision_barcode.zzec$zzd");
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        final com.google.android.gms.internal.mlkit_vision_barcode.zzec.zzj zzb(com.google.android.gms.internal.mlkit_vision_barcode.zzec r3, com.google.android.gms.internal.mlkit_vision_barcode.zzec.zzj r4) {
            /*
                r2 = this;
            L_0x0000:
                com.google.android.gms.internal.mlkit_vision_barcode.zzec$zzj r0 = r3.waiters
                if (r4 != r0) goto L_0x0007
                return r0
            L_0x0007:
                boolean r1 = r2.zzg(r3, r0, r4)
                if (r1 == 0) goto L_0x0000
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzec.zzi.zzb(com.google.android.gms.internal.mlkit_vision_barcode.zzec, com.google.android.gms.internal.mlkit_vision_barcode.zzec$zzj):com.google.android.gms.internal.mlkit_vision_barcode.zzec$zzj");
        }

        /* access modifiers changed from: package-private */
        public final void zzc(zzj zzj, @CheckForNull zzj zzj2) {
            zza.putObject(zzj, zzf, zzj2);
        }

        /* access modifiers changed from: package-private */
        public final void zzd(zzj zzj, Thread thread) {
            zza.putObject(zzj, zze, thread);
        }

        /* access modifiers changed from: package-private */
        public final boolean zze(zzec zzec, @CheckForNull zzd zzd2, zzd zzd3) {
            return zzef.zza(zza, zzec, zzb, zzd2, zzd3);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zzec zzec, @CheckForNull Object obj, Object obj2) {
            return zzef.zza(zza, zzec, zzd, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzg(zzec zzec, @CheckForNull zzj zzj, @CheckForNull zzj zzj2) {
            return zzef.zza(zza, zzec, zzc, zzj, zzj2);
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
    final class zzj {
        static final zzj zza = new zzj(false);
        @CheckForNull
        volatile zzj next;
        @CheckForNull
        volatile Thread thread;

        zzj() {
            zzec.zzc.zzd(this, Thread.currentThread());
        }

        zzj(boolean z) {
        }
    }

    static {
        boolean z;
        Throwable th;
        Throwable th2;
        zza zza2;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        zza = z;
        Class<zzec> cls = zzec.class;
        zzb = Logger.getLogger(cls.getName());
        try {
            zza2 = new zzi((zzeg) null);
            th2 = null;
            th = null;
        } catch (Error | RuntimeException e) {
            try {
                th2 = e;
                zza2 = new zze(AtomicReferenceFieldUpdater.newUpdater(zzj.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(zzj.class, zzj.class, "next"), AtomicReferenceFieldUpdater.newUpdater(cls, zzj.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(cls, zzd.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "value"));
                th = null;
            } catch (Error | RuntimeException e2) {
                th = e2;
                th2 = e;
                zza2 = new zzg((zzee) null);
            }
        }
        zzc = zza2;
        if (th != null) {
            Logger logger = zzb;
            logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    protected zzec() {
    }

    private static Object zzo(zzev zzev) {
        Throwable zzf2;
        if (zzev instanceof zzh) {
            Object obj = ((zzec) zzev).value;
            if (obj instanceof zzb) {
                zzb zzb2 = (zzb) obj;
                if (zzb2.zzc) {
                    Throwable th = zzb2.zzd;
                    obj = th != null ? new zzb(false, th) : zzb.zzb;
                }
            }
            obj.getClass();
            return obj;
        } else if ((zzev instanceof zzez) && (zzf2 = ((zzez) zzev).zzf()) != null) {
            return new zzc(zzf2);
        } else {
            boolean isCancelled = zzev.isCancelled();
            if ((!zza) && isCancelled) {
                zzb zzb3 = zzb.zzb;
                zzb3.getClass();
                return zzb3;
            }
            try {
                Object zzp = zzp(zzev);
                if (!isCancelled) {
                    return zzp == null ? zzd : zzp;
                }
                String valueOf = String.valueOf(zzev);
                return new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + valueOf));
            } catch (ExecutionException e) {
                if (isCancelled) {
                    return new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(String.valueOf(zzev))), e));
                }
                return new zzc(e.getCause());
            } catch (CancellationException e2) {
                if (!isCancelled) {
                    return new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(String.valueOf(zzev))), e2));
                }
                return new zzb(false, e2);
            } catch (Error | RuntimeException e3) {
                return new zzc(e3);
            }
        }
    }

    private static Object zzp(Future future) throws ExecutionException {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    private final void zzq(StringBuilder sb) {
        try {
            Object zzp = zzp(this);
            sb.append("SUCCESS, result=[");
            if (zzp == null) {
                sb.append("null");
            } else if (zzp == this) {
                sb.append("this future");
            } else {
                sb.append(zzp.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(zzp)));
            }
            sb.append("]");
        } catch (ExecutionException e) {
            sb.append("FAILURE, cause=[");
            sb.append(e.getCause());
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        }
    }

    private final void zzr(StringBuilder sb) {
        String str;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.value;
        if (obj instanceof zzf) {
            sb.append(", setFuture=[");
            zzs(sb, ((zzf) obj).zzb);
            sb.append("]");
        } else {
            try {
                str = zzbd.zza(zze());
            } catch (RuntimeException | StackOverflowError e) {
                str = "Exception thrown from implementation: ".concat(String.valueOf(String.valueOf(e.getClass())));
            }
            if (str != null) {
                sb.append(", info=[");
                sb.append(str);
                sb.append("]");
            }
        }
        if (isDone()) {
            sb.delete(length, sb.length());
            zzq(sb);
        }
    }

    private final void zzs(StringBuilder sb, @CheckForNull Object obj) {
        if (obj == this) {
            try {
                sb.append("this future");
            } catch (RuntimeException | StackOverflowError e) {
                sb.append("Exception thrown from implementation: ");
                sb.append(e.getClass());
            }
        } else {
            sb.append(obj);
        }
    }

    private static void zzt(zzec<V> zzec, boolean z) {
        zzd zzd2 = null;
        while (true) {
            for (zzj zzb2 = zzc.zzb(zzec, zzj.zza); zzb2 != null; zzb2 = zzb2.next) {
                Thread thread = zzb2.thread;
                if (thread != null) {
                    zzb2.thread = null;
                    LockSupport.unpark(thread);
                }
            }
            zzec.zzk();
            zzd zzd3 = zzd2;
            zzd zza2 = zzc.zza(zzec, zzd.zza);
            zzd zzd4 = zzd3;
            while (zza2 != null) {
                zzd zzd5 = zza2.next;
                zza2.next = zzd4;
                zzd4 = zza2;
                zza2 = zzd5;
            }
            while (true) {
                if (zzd4 != null) {
                    zzd2 = zzd4.next;
                    Runnable runnable = zzd4.zzb;
                    runnable.getClass();
                    if (runnable instanceof zzf) {
                        zzf zzf2 = (zzf) runnable;
                        zzec = zzf2.zza;
                        if (zzec.value == zzf2) {
                            if (zzc.zzf(zzec, zzf2, zzo(zzf2.zzb))) {
                            }
                        } else {
                            continue;
                        }
                    } else {
                        Executor executor = zzd4.zzc;
                        executor.getClass();
                        zzu(runnable, executor);
                    }
                    zzd4 = zzd2;
                } else {
                    return;
                }
            }
        }
    }

    private static void zzu(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = zzb;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + valueOf + " with executor " + valueOf2, e);
        }
    }

    private final void zzv(zzj zzj2) {
        zzj2.thread = null;
        while (true) {
            zzj zzj3 = this.waiters;
            if (zzj3 != zzj.zza) {
                zzj zzj4 = null;
                while (zzj3 != null) {
                    zzj zzj5 = zzj3.next;
                    if (zzj3.thread != null) {
                        zzj4 = zzj3;
                    } else if (zzj4 != null) {
                        zzj4.next = zzj5;
                        if (zzj4.thread == null) {
                        }
                    } else if (!zzc.zzg(this, zzj3, zzj5)) {
                    }
                    zzj3 = zzj5;
                }
                return;
            }
            return;
        }
    }

    private static final Object zzw(Object obj) throws ExecutionException {
        if (obj instanceof zzb) {
            Throwable th = ((zzb) obj).zzd;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof zzc) {
            throw new ExecutionException(((zzc) obj).zzb);
        } else if (obj == zzd) {
            return null;
        } else {
            return obj;
        }
    }

    public final boolean cancel(boolean z) {
        zzb zzb2;
        Object obj = this.value;
        if (!(obj instanceof zzf) && !(obj == null)) {
            return false;
        }
        if (zza) {
            zzb2 = new zzb(z, new CancellationException("Future.cancel() was called."));
        } else {
            if (z) {
                zzb2 = zzb.zza;
            } else {
                zzb2 = zzb.zzb;
            }
            zzb2.getClass();
        }
        boolean z2 = false;
        while (true) {
            if (zzc.zzf(this, obj, zzb2)) {
                zzt(this, z);
                if (!(obj instanceof zzf)) {
                    break;
                }
                zzev<? extends V> zzev = ((zzf) obj).zzb;
                if (!(zzev instanceof zzh)) {
                    zzev.cancel(z);
                    break;
                }
                this = (zzec) zzev;
                obj = this.value;
                if (!(obj == null) && !(obj instanceof zzf)) {
                    break;
                }
                z2 = true;
            } else {
                obj = this.value;
                if (!(obj instanceof zzf)) {
                    return z2;
                }
            }
        }
        return true;
    }

    public final Object get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                return zzw(obj2);
            }
            zzj zzj2 = this.waiters;
            if (zzj2 != zzj.zza) {
                zzj zzj3 = new zzj();
                do {
                    zza zza2 = zzc;
                    zza2.zzc(zzj3, zzj2);
                    if (zza2.zzg(this, zzj2, zzj3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                zzv(zzj3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof zzf))));
                        return zzw(obj);
                    }
                    zzj2 = this.waiters;
                } while (zzj2 != zzj.zza);
            }
            Object obj3 = this.value;
            obj3.getClass();
            return zzw(obj3);
        }
        throw new InterruptedException();
    }

    public final boolean isCancelled() {
        return this.value instanceof zzb;
    }

    public final boolean isDone() {
        Object obj = this.value;
        return (obj != null) & (!(obj instanceof zzf));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (this.value instanceof zzb) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zzq(sb);
        } else {
            zzr(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public String zze() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
        return "remaining delay=[" + delay + " ms]";
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final Throwable zzf() {
        if (!(this instanceof zzh)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof zzc) {
            return ((zzc) obj).zzb;
        }
        return null;
    }

    public final void zzj(Runnable runnable, Executor executor) {
        zzd zzd2;
        if (executor == null) {
            throw new NullPointerException("Executor was null.");
        } else if (isDone() || (zzd2 = this.listeners) == zzd.zza) {
            zzu(runnable, executor);
        } else {
            zzd zzd3 = new zzd(runnable, executor);
            do {
                zzd3.next = zzd2;
                if (!zzc.zze(this, zzd2, zzd3)) {
                    zzd2 = this.listeners;
                } else {
                    return;
                }
            } while (zzd2 != zzd.zza);
            zzu(runnable, executor);
        }
    }

    /* access modifiers changed from: protected */
    public void zzk() {
    }

    /* access modifiers changed from: protected */
    public final boolean zzl(Throwable th) {
        if (!zzc.zzf(this, (Object) null, new zzc(th))) {
            return false;
        }
        zzt(this, false);
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean zzn() {
        Object obj = this.value;
        return (obj instanceof zzb) && ((zzb) obj).zzc;
    }

    /* access modifiers changed from: protected */
    public final boolean zzm(zzev zzev) {
        zzev.getClass();
        Object obj = this.value;
        if (obj == null) {
            if (!zzc.zzf(this, (Object) null, zzo(zzev))) {
                return false;
            }
            zzt(this, false);
            return true;
        }
        if (obj instanceof zzb) {
            zzb zzb2 = (zzb) obj;
        }
        return false;
    }

    public final Object get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        long nanos = timeUnit2.toNanos(j2);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            boolean z = true;
            if ((obj != null) && (!(obj instanceof zzf))) {
                return zzw(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= 1000) {
                zzj zzj2 = this.waiters;
                if (zzj2 != zzj.zza) {
                    zzj zzj3 = new zzj();
                    do {
                        zza zza2 = zzc;
                        zza2.zzc(zzj3, zzj2);
                        if (zza2.zzg(this, zzj2, zzj3)) {
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                                        return zzw(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    zzv(zzj3);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            zzv(zzj3);
                        } else {
                            zzj2 = this.waiters;
                        }
                    } while (zzj2 != zzj.zza);
                }
                Object obj3 = this.value;
                obj3.getClass();
                return zzw(obj3);
            }
            while (nanos > 0) {
                Object obj4 = this.value;
                if ((obj4 != null) && (!(obj4 instanceof zzf))) {
                    return zzw(obj4);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String zzec = toString();
            String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
            String str = "Waited " + j2 + " " + timeUnit.toString().toLowerCase(Locale.ROOT);
            if (nanos + 1000 < 0) {
                String concat = str.concat(" (plus ");
                long j3 = -nanos;
                long convert = timeUnit2.convert(j3, TimeUnit.NANOSECONDS);
                long nanos2 = j3 - timeUnit2.toNanos(convert);
                int i = (convert > 0 ? 1 : (convert == 0 ? 0 : -1));
                if (i != 0 && nanos2 <= 1000) {
                    z = false;
                }
                if (i > 0) {
                    String str2 = concat + convert + " " + lowerCase;
                    if (z) {
                        str2 = str2.concat(",");
                    }
                    concat = str2.concat(" ");
                }
                if (z) {
                    concat = concat + nanos2 + " nanoseconds ";
                }
                str = concat.concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(str.concat(" but future completed as timeout expired"));
            }
            throw new TimeoutException(str + " for " + zzec);
        }
        throw new InterruptedException();
    }
}
