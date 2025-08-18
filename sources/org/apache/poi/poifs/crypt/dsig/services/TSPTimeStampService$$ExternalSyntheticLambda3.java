package org.apache.poi.poifs.crypt.dsig.services;

import java.math.BigInteger;
import java.util.function.Predicate;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TSPTimeStampService$$ExternalSyntheticLambda3 implements Predicate {
    public final /* synthetic */ X500Name f$0;
    public final /* synthetic */ BigInteger f$1;

    public /* synthetic */ TSPTimeStampService$$ExternalSyntheticLambda3(X500Name x500Name, BigInteger bigInteger) {
        this.f$0 = x500Name;
        this.f$1 = bigInteger;
    }

    public final boolean test(Object obj) {
        return TSPTimeStampService.lambda$timeStamp$1(this.f$0, this.f$1, (X509CertificateHolder) obj);
    }
}
