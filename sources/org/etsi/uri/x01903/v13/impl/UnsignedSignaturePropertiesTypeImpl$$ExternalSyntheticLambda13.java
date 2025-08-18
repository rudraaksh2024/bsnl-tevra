package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.CompleteCertificateRefsType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda13 implements BiConsumer {
    public final /* synthetic */ UnsignedSignaturePropertiesTypeImpl f$0;

    public /* synthetic */ UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda13(UnsignedSignaturePropertiesTypeImpl unsignedSignaturePropertiesTypeImpl) {
        this.f$0 = unsignedSignaturePropertiesTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setCompleteCertificateRefsArray(((Integer) obj).intValue(), (CompleteCertificateRefsType) obj2);
    }
}
