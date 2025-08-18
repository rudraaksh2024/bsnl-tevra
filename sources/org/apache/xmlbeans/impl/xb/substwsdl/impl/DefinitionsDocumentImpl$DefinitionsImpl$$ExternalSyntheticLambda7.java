package org.apache.xmlbeans.impl.xb.substwsdl.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda7 implements BiConsumer {
    public final /* synthetic */ DefinitionsDocumentImpl.DefinitionsImpl f$0;

    public /* synthetic */ DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda7(DefinitionsDocumentImpl.DefinitionsImpl definitionsImpl) {
        this.f$0 = definitionsImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setServiceArray(((Integer) obj).intValue(), (XmlObject) obj2);
    }
}
