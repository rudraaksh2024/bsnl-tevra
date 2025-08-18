package org.apache.poi.poifs.crypt.dsig;

import java.security.cert.X509Certificate;
import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignatureConfig$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ SignatureConfig$$ExternalSyntheticLambda1(String str) {
        this.f$0 = str;
    }

    public final boolean test(Object obj) {
        return this.f$0.equalsIgnoreCase(((X509Certificate) obj).getSubjectX500Principal().getName());
    }
}
