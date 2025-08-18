package org.apache.poi.poifs.crypt.dsig.services;

import java.security.cert.X509Certificate;
import java.util.function.Predicate;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TSPTimeStampService$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ TSPTimeStampService f$0;
    public final /* synthetic */ X509Certificate f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ TSPTimeStampService$$ExternalSyntheticLambda0(TSPTimeStampService tSPTimeStampService, X509Certificate x509Certificate, String str) {
        this.f$0 = tSPTimeStampService;
        this.f$1 = x509Certificate;
        this.f$2 = str;
    }

    public final boolean test(Object obj) {
        return this.f$0.m2227lambda$null$7$orgapachepoipoifscryptdsigservicesTSPTimeStampService(this.f$1, this.f$2, (SignatureConfig.CRLEntry) obj);
    }
}
