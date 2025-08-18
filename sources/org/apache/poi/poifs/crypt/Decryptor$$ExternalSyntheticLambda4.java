package org.apache.poi.poifs.crypt;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Decryptor$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ Decryptor f$0;

    public /* synthetic */ Decryptor$$ExternalSyntheticLambda4(Decryptor decryptor) {
        this.f$0 = decryptor;
    }

    public final Object get() {
        return this.f$0.getIntegrityHmacValue();
    }
}
