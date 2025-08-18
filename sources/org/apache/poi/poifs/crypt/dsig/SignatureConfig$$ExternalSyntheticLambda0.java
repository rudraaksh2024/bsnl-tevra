package org.apache.poi.poifs.crypt.dsig;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignatureConfig$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Class f$0;

    public /* synthetic */ SignatureConfig$$ExternalSyntheticLambda0(Class cls) {
        this.f$0 = cls;
    }

    public final Object apply(Object obj) {
        return (X509Certificate) this.f$0.cast((Certificate) obj);
    }
}
