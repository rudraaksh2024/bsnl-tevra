package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.UnionDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ UnionDocumentImpl.UnionImpl f$0;

    public /* synthetic */ UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda3(UnionDocumentImpl.UnionImpl unionImpl) {
        this.f$0 = unionImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeSimpleType(((Integer) obj).intValue());
    }
}
