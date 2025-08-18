package org.apache.poi.poifs.crypt.dsig;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignaturePart$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ SignatureConfig f$0;

    public /* synthetic */ SignaturePart$$ExternalSyntheticLambda2(SignatureConfig signatureConfig) {
        this.f$0 = signatureConfig;
    }

    public final void accept(Object obj) {
        this.f$0.setSignatureDescription((String) obj);
    }
}
