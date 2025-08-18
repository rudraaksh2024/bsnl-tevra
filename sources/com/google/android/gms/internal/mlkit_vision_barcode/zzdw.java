package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
abstract class zzdw implements Iterator {
    final Iterator zza;

    zzdw(Iterator it) {
        it.getClass();
        this.zza = it;
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final Object next() {
        return zza(this.zza.next());
    }

    public final void remove() {
        this.zza.remove();
    }

    /* access modifiers changed from: package-private */
    public abstract Object zza(Object obj);
}
