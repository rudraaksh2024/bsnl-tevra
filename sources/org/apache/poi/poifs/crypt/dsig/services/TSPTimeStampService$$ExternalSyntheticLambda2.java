package org.apache.poi.poifs.crypt.dsig.services;

import java.util.function.Function;
import org.bouncycastle.cert.X509CertificateHolder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TSPTimeStampService$$ExternalSyntheticLambda2 implements Function {
    public final Object apply(Object obj) {
        return ((X509CertificateHolder) obj).getSubject().toString();
    }
}
