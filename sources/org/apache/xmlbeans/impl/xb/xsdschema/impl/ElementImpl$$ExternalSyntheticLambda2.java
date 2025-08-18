package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.KeyrefDocument;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ElementImpl$$ExternalSyntheticLambda2 implements BiConsumer {
    public final /* synthetic */ ElementImpl f$0;

    public /* synthetic */ ElementImpl$$ExternalSyntheticLambda2(ElementImpl elementImpl) {
        this.f$0 = elementImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setKeyrefArray(((Integer) obj).intValue(), (KeyrefDocument.Keyref) obj2);
    }
}
