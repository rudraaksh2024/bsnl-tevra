package org.apache.poi.poifs.crypt;

import java.util.function.Supplier;
import javax.crypto.SecretKey;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Decryptor$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ SecretKey f$0;

    public /* synthetic */ Decryptor$$ExternalSyntheticLambda1(SecretKey secretKey) {
        this.f$0 = secretKey;
    }

    public final Object get() {
        return this.f$0.getEncoded();
    }
}
