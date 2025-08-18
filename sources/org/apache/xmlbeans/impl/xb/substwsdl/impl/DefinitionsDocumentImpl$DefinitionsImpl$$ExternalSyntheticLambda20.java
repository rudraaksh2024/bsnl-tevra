package org.apache.xmlbeans.impl.xb.substwsdl.impl;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda20 implements Consumer {
    public final /* synthetic */ DefinitionsDocumentImpl.DefinitionsImpl f$0;

    public /* synthetic */ DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda20(DefinitionsDocumentImpl.DefinitionsImpl definitionsImpl) {
        this.f$0 = definitionsImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeImport(((Integer) obj).intValue());
    }
}
