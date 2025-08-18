package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda29 implements BiConsumer {
    public final /* synthetic */ UnsignedSignaturePropertiesTypeImpl f$0;

    public /* synthetic */ UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda29(UnsignedSignaturePropertiesTypeImpl unsignedSignaturePropertiesTypeImpl) {
        this.f$0 = unsignedSignaturePropertiesTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setRefsOnlyTimeStampArray(((Integer) obj).intValue(), (XAdESTimeStampType) obj2);
    }
}
