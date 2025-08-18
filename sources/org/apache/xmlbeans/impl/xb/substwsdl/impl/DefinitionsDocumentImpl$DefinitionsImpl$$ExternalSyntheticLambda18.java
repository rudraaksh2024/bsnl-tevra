package org.apache.xmlbeans.impl.xb.substwsdl.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.substwsdl.TImport;
import org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda18 implements BiConsumer {
    public final /* synthetic */ DefinitionsDocumentImpl.DefinitionsImpl f$0;

    public /* synthetic */ DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda18(DefinitionsDocumentImpl.DefinitionsImpl definitionsImpl) {
        this.f$0 = definitionsImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setImportArray(((Integer) obj).intValue(), (TImport) obj2);
    }
}
