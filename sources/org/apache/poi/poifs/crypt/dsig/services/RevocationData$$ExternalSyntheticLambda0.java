package org.apache.poi.poifs.crypt.dsig.services;

import java.util.Arrays;
import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RevocationData$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ byte[] f$0;

    public /* synthetic */ RevocationData$$ExternalSyntheticLambda0(byte[] bArr) {
        this.f$0 = bArr;
    }

    public final boolean test(Object obj) {
        return Arrays.equals((byte[]) obj, this.f$0);
    }
}
