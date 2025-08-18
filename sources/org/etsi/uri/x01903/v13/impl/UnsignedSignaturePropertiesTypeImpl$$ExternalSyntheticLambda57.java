package org.etsi.uri.x01903.v13.impl;

import java.util.function.BiConsumer;
import org.etsi.uri.x01903.v13.CounterSignatureType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda57 implements BiConsumer {
    public final /* synthetic */ UnsignedSignaturePropertiesTypeImpl f$0;

    public /* synthetic */ UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda57(UnsignedSignaturePropertiesTypeImpl unsignedSignaturePropertiesTypeImpl) {
        this.f$0 = unsignedSignaturePropertiesTypeImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setCounterSignatureArray(((Integer) obj).intValue(), (CounterSignatureType) obj2);
    }
}
