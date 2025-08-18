package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ElementImpl$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ ElementImpl f$0;

    public /* synthetic */ ElementImpl$$ExternalSyntheticLambda4(ElementImpl elementImpl) {
        this.f$0 = elementImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeKeyref(((Integer) obj).intValue());
    }
}
