package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.UnionDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ UnionDocumentImpl.UnionImpl f$0;

    public /* synthetic */ UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda1(UnionDocumentImpl.UnionImpl unionImpl) {
        this.f$0 = unionImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setSimpleTypeArray(((Integer) obj).intValue(), (LocalSimpleType) obj2);
    }
}
