package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.RunnableFuture;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzey extends zzei implements RunnableFuture {
    @CheckForNull
    private volatile zzeu zzb;

    zzey(zzup zzup) {
        this.zzb = new zzex(this, zzup);
    }

    public final void run() {
        zzeu zzeu = this.zzb;
        if (zzeu != null) {
            zzeu.run();
        }
        this.zzb = null;
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final String zze() {
        zzeu zzeu = this.zzb;
        if (zzeu == null) {
            return super.zze();
        }
        String obj = zzeu.toString();
        return "task=[" + obj + "]";
    }

    /* access modifiers changed from: protected */
    public final void zzk() {
        zzeu zzeu;
        if (zzn() && (zzeu = this.zzb) != null) {
            zzeu.zze();
        }
        this.zzb = null;
    }
}
