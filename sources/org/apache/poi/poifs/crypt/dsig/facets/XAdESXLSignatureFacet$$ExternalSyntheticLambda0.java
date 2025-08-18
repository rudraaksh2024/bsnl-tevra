package org.apache.poi.poifs.crypt.dsig.facets;

import java.security.cert.X509Certificate;
import java.util.function.Consumer;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.etsi.uri.x01903.v13.CertIDListType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XAdESXLSignatureFacet$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ CertIDListType f$0;
    public final /* synthetic */ SignatureConfig f$1;

    public /* synthetic */ XAdESXLSignatureFacet$$ExternalSyntheticLambda0(CertIDListType certIDListType, SignatureConfig signatureConfig) {
        this.f$0 = certIDListType;
        this.f$1 = signatureConfig;
    }

    public final void accept(Object obj) {
        XAdESSignatureFacet.setCertID(this.f$0.addNewCert(), this.f$1, false, (X509Certificate) obj);
    }
}
