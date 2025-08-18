package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzcl extends AbstractMap implements Serializable {
    /* access modifiers changed from: private */
    public static final Object zzd = new Object();
    @CheckForNull
    transient int[] zza;
    @CheckForNull
    transient Object[] zzb;
    @CheckForNull
    transient Object[] zzc;
    @CheckForNull
    private transient Object zze;
    /* access modifiers changed from: private */
    public transient int zzf;
    /* access modifiers changed from: private */
    public transient int zzg;
    @CheckForNull
    private transient Set zzh;
    @CheckForNull
    private transient Set zzi;
    @CheckForNull
    private transient Collection zzj;

    zzcl() {
        zzm(3);
    }

    static /* synthetic */ Object zzh(zzcl zzcl) {
        Object obj = zzcl.zze;
        obj.getClass();
        return obj;
    }

    /* access modifiers changed from: private */
    public final int zzp() {
        return (1 << (this.zzf & 31)) - 1;
    }

    /* access modifiers changed from: private */
    public final int zzq(@CheckForNull Object obj) {
        if (zzo()) {
            return -1;
        }
        int zza2 = zzcn.zza(obj);
        int zzp = zzp();
        Object obj2 = this.zze;
        obj2.getClass();
        int zzc2 = zzcm.zzc(obj2, zza2 & zzp);
        if (zzc2 != 0) {
            int i = ~zzp;
            int i2 = zza2 & i;
            do {
                int i3 = zzc2 - 1;
                int[] iArr = this.zza;
                iArr.getClass();
                int i4 = iArr[i3];
                if ((i4 & i) == i2) {
                    Object[] objArr = this.zzb;
                    objArr.getClass();
                    if (zzay.zza(obj, objArr[i3])) {
                        return i3;
                    }
                }
                zzc2 = i4 & zzp;
            } while (zzc2 != 0);
        }
        return -1;
    }

    private final int zzr(int i, int i2, int i3, int i4) {
        int i5 = i2 - 1;
        Object zzd2 = zzcm.zzd(i2);
        if (i4 != 0) {
            zzcm.zze(zzd2, i3 & i5, i4 + 1);
        }
        Object obj = this.zze;
        obj.getClass();
        int[] iArr = this.zza;
        iArr.getClass();
        for (int i6 = 0; i6 <= i; i6++) {
            int zzc2 = zzcm.zzc(obj, i6);
            while (zzc2 != 0) {
                int i7 = zzc2 - 1;
                int i8 = iArr[i7];
                int i9 = ((~i) & i8) | i6;
                int i10 = i9 & i5;
                int zzc3 = zzcm.zzc(zzd2, i10);
                zzcm.zze(zzd2, i10, zzc2);
                iArr[i7] = ((~i5) & i9) | (zzc3 & i5);
                zzc2 = i8 & i;
            }
        }
        this.zze = zzd2;
        zzt(i5);
        return i5;
    }

    /* access modifiers changed from: private */
    public final Object zzs(@CheckForNull Object obj) {
        if (zzo()) {
            return zzd;
        }
        int zzp = zzp();
        Object obj2 = this.zze;
        obj2.getClass();
        int[] iArr = this.zza;
        iArr.getClass();
        Object[] objArr = this.zzb;
        objArr.getClass();
        int zzb2 = zzcm.zzb(obj, (Object) null, zzp, obj2, iArr, objArr, (Object[]) null);
        if (zzb2 == -1) {
            return zzd;
        }
        Object[] objArr2 = this.zzc;
        objArr2.getClass();
        Object obj3 = objArr2[zzb2];
        zzn(zzb2, zzp);
        this.zzg--;
        zzl();
        return obj3;
    }

    private final void zzt(int i) {
        this.zzf = ((32 - Integer.numberOfLeadingZeros(i)) & 31) | (this.zzf & -32);
    }

    public final void clear() {
        if (!zzo()) {
            zzl();
            Map zzj2 = zzj();
            if (zzj2 == null) {
                Object[] objArr = this.zzb;
                objArr.getClass();
                Arrays.fill(objArr, 0, this.zzg, (Object) null);
                Object[] objArr2 = this.zzc;
                objArr2.getClass();
                Arrays.fill(objArr2, 0, this.zzg, (Object) null);
                Object obj = this.zze;
                obj.getClass();
                if (obj instanceof byte[]) {
                    Arrays.fill((byte[]) obj, (byte) 0);
                } else if (obj instanceof short[]) {
                    Arrays.fill((short[]) obj, 0);
                } else {
                    Arrays.fill((int[]) obj, 0);
                }
                int[] iArr = this.zza;
                iArr.getClass();
                Arrays.fill(iArr, 0, this.zzg, 0);
                this.zzg = 0;
                return;
            }
            this.zzf = zzdz.zza(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            zzj2.clear();
            this.zze = null;
            this.zzg = 0;
        }
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        Map zzj2 = zzj();
        if (zzj2 != null) {
            return zzj2.containsKey(obj);
        }
        return zzq(obj) != -1;
    }

    public final boolean containsValue(@CheckForNull Object obj) {
        Map zzj2 = zzj();
        if (zzj2 != null) {
            return zzj2.containsValue(obj);
        }
        for (int i = 0; i < this.zzg; i++) {
            Object[] objArr = this.zzc;
            objArr.getClass();
            if (zzay.zza(obj, objArr[i])) {
                return true;
            }
        }
        return false;
    }

    public final Set entrySet() {
        Set set = this.zzi;
        if (set != null) {
            return set;
        }
        zzcf zzcf = new zzcf(this);
        this.zzi = zzcf;
        return zzcf;
    }

    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        Map zzj2 = zzj();
        if (zzj2 != null) {
            return zzj2.get(obj);
        }
        int zzq = zzq(obj);
        if (zzq == -1) {
            return null;
        }
        Object[] objArr = this.zzc;
        objArr.getClass();
        return objArr[zzq];
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    public final Set keySet() {
        Set set = this.zzh;
        if (set != null) {
            return set;
        }
        zzci zzci = new zzci(this);
        this.zzh = zzci;
        return zzci;
    }

    @CheckForNull
    public final Object put(Object obj, Object obj2) {
        int min;
        Object obj3 = obj;
        Object obj4 = obj2;
        if (zzo()) {
            zzbc.zze(zzo(), "Arrays already allocated");
            int i = this.zzf;
            int max = Math.max(i + 1, 2);
            int highestOneBit = Integer.highestOneBit(max);
            if (max > ((int) ((double) highestOneBit)) && (highestOneBit = highestOneBit + highestOneBit) <= 0) {
                highestOneBit = BasicMeasure.EXACTLY;
            }
            int max2 = Math.max(4, highestOneBit);
            this.zze = zzcm.zzd(max2);
            zzt(max2 - 1);
            this.zza = new int[i];
            this.zzb = new Object[i];
            this.zzc = new Object[i];
        }
        Map zzj2 = zzj();
        if (zzj2 != null) {
            return zzj2.put(obj3, obj4);
        }
        int[] iArr = this.zza;
        iArr.getClass();
        Object[] objArr = this.zzb;
        objArr.getClass();
        Object[] objArr2 = this.zzc;
        objArr2.getClass();
        int i2 = this.zzg;
        int i3 = i2 + 1;
        int zza2 = zzcn.zza(obj);
        int zzp = zzp();
        int i4 = zza2 & zzp;
        Object obj5 = this.zze;
        obj5.getClass();
        int zzc2 = zzcm.zzc(obj5, i4);
        if (zzc2 != 0) {
            int i5 = ~zzp;
            int i6 = zza2 & i5;
            int i7 = 0;
            while (true) {
                int i8 = zzc2 - 1;
                int i9 = iArr[i8];
                int i10 = i9 & i5;
                if (i10 != i6 || !zzay.zza(obj3, objArr[i8])) {
                    int i11 = i9 & zzp;
                    i7++;
                    if (i11 != 0) {
                        zzc2 = i11;
                    } else if (i7 >= 9) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap(zzp() + 1, 1.0f);
                        for (int zze2 = zze(); zze2 >= 0; zze2 = zzf(zze2)) {
                            Object[] objArr3 = this.zzb;
                            objArr3.getClass();
                            Object obj6 = objArr3[zze2];
                            Object[] objArr4 = this.zzc;
                            objArr4.getClass();
                            linkedHashMap.put(obj6, objArr4[zze2]);
                        }
                        this.zze = linkedHashMap;
                        this.zza = null;
                        this.zzb = null;
                        this.zzc = null;
                        zzl();
                        return linkedHashMap.put(obj3, obj4);
                    } else if (i3 > zzp) {
                        zzp = zzr(zzp, zzcm.zza(zzp), zza2, i2);
                    } else {
                        iArr[i8] = (i3 & zzp) | i10;
                    }
                } else {
                    Object obj7 = objArr2[i8];
                    objArr2[i8] = obj4;
                    return obj7;
                }
            }
        } else if (i3 > zzp) {
            zzp = zzr(zzp, zzcm.zza(zzp), zza2, i2);
        } else {
            Object obj8 = this.zze;
            obj8.getClass();
            zzcm.zze(obj8, i4, i3);
        }
        int[] iArr2 = this.zza;
        iArr2.getClass();
        int length = iArr2.length;
        if (i3 > length && (min = Math.min(LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            int[] iArr3 = this.zza;
            iArr3.getClass();
            this.zza = Arrays.copyOf(iArr3, min);
            Object[] objArr5 = this.zzb;
            objArr5.getClass();
            this.zzb = Arrays.copyOf(objArr5, min);
            Object[] objArr6 = this.zzc;
            objArr6.getClass();
            this.zzc = Arrays.copyOf(objArr6, min);
        }
        int[] iArr4 = this.zza;
        iArr4.getClass();
        iArr4[i2] = (~zzp) & zza2;
        Object[] objArr7 = this.zzb;
        objArr7.getClass();
        objArr7[i2] = obj3;
        Object[] objArr8 = this.zzc;
        objArr8.getClass();
        objArr8[i2] = obj4;
        this.zzg = i3;
        zzl();
        return null;
    }

    @CheckForNull
    public final Object remove(@CheckForNull Object obj) {
        Map zzj2 = zzj();
        if (zzj2 != null) {
            return zzj2.remove(obj);
        }
        Object zzs = zzs(obj);
        if (zzs == zzd) {
            return null;
        }
        return zzs;
    }

    public final int size() {
        Map zzj2 = zzj();
        return zzj2 != null ? zzj2.size() : this.zzg;
    }

    public final Collection values() {
        Collection collection = this.zzj;
        if (collection != null) {
            return collection;
        }
        zzck zzck = new zzck(this);
        this.zzj = zzck;
        return zzck;
    }

    /* access modifiers changed from: package-private */
    public final int zze() {
        return isEmpty() ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public final int zzf(int i) {
        int i2 = i + 1;
        if (i2 < this.zzg) {
            return i2;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public final Map zzj() {
        Object obj = this.zze;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void zzl() {
        this.zzf += 32;
    }

    /* access modifiers changed from: package-private */
    public final void zzm(int i) {
        this.zzf = zzdz.zza(12, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    /* access modifiers changed from: package-private */
    public final void zzn(int i, int i2) {
        Object obj = this.zze;
        obj.getClass();
        int[] iArr = this.zza;
        iArr.getClass();
        Object[] objArr = this.zzb;
        objArr.getClass();
        Object[] objArr2 = this.zzc;
        objArr2.getClass();
        int size = size() - 1;
        if (i < size) {
            Object obj2 = objArr[size];
            objArr[i] = obj2;
            objArr2[i] = objArr2[size];
            objArr[size] = null;
            objArr2[size] = null;
            iArr[i] = iArr[size];
            iArr[size] = 0;
            int zza2 = zzcn.zza(obj2) & i2;
            int zzc2 = zzcm.zzc(obj, zza2);
            int i3 = size + 1;
            if (zzc2 != i3) {
                while (true) {
                    int i4 = zzc2 - 1;
                    int i5 = iArr[i4];
                    int i6 = i5 & i2;
                    if (i6 != i3) {
                        zzc2 = i6;
                    } else {
                        iArr[i4] = ((~i2) & i5) | ((i + 1) & i2);
                        return;
                    }
                }
            } else {
                zzcm.zze(obj, zza2, i + 1);
            }
        } else {
            objArr[i] = null;
            objArr2[i] = null;
            iArr[i] = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzo() {
        return this.zze == null;
    }

    zzcl(int i) {
        zzm(12);
    }
}
