package org.apache.poi.poifs.crypt;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncryptionInfo$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ EncryptionInfo f$0;

    public /* synthetic */ EncryptionInfo$$ExternalSyntheticLambda1(EncryptionInfo encryptionInfo) {
        this.f$0 = encryptionInfo;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getVersionMajor());
    }
}
