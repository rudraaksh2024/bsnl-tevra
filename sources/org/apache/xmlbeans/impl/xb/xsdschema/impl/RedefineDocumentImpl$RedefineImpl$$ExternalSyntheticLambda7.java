package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda7 implements BiConsumer {
    public final /* synthetic */ RedefineDocumentImpl.RedefineImpl f$0;

    public /* synthetic */ RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda7(RedefineDocumentImpl.RedefineImpl redefineImpl) {
        this.f$0 = redefineImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setGroupArray(((Integer) obj).intValue(), (NamedGroup) obj2);
    }
}
