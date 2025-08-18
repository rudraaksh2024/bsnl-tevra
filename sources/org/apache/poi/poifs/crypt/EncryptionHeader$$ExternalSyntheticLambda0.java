package org.apache.poi.poifs.crypt;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncryptionHeader$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ EncryptionHeader f$0;

    public /* synthetic */ EncryptionHeader$$ExternalSyntheticLambda0(EncryptionHeader encryptionHeader) {
        this.f$0 = encryptionHeader;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getFlags());
    }
}
