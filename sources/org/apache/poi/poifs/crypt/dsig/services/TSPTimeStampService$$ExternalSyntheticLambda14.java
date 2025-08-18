package org.apache.poi.poifs.crypt.dsig.services;

import java.security.cert.X509Certificate;
import java.util.List;
import java.util.function.Function;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TSPTimeStampService$$ExternalSyntheticLambda14 implements Function {
    public final /* synthetic */ TSPTimeStampService f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ X509Certificate f$2;
    public final /* synthetic */ SignatureConfig f$3;

    public /* synthetic */ TSPTimeStampService$$ExternalSyntheticLambda14(TSPTimeStampService tSPTimeStampService, List list, X509Certificate x509Certificate, SignatureConfig signatureConfig) {
        this.f$0 = tSPTimeStampService;
        this.f$1 = list;
        this.f$2 = x509Certificate;
        this.f$3 = signatureConfig;
    }

    public final Object apply(Object obj) {
        return this.f$0.m2229lambda$retrieveCRL$9$orgapachepoipoifscryptdsigservicesTSPTimeStampService(this.f$1, this.f$2, this.f$3, (String) obj);
    }
}
