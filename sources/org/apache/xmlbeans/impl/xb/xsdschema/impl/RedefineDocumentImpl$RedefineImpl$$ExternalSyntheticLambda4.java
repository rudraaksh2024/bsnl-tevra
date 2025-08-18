package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ RedefineDocumentImpl.RedefineImpl f$0;

    public /* synthetic */ RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda4(RedefineDocumentImpl.RedefineImpl redefineImpl) {
        this.f$0 = redefineImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeAttributeGroup(((Integer) obj).intValue());
    }
}
