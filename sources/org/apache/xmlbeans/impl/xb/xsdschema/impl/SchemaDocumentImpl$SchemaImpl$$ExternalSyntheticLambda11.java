package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda11 implements BiConsumer {
    public final /* synthetic */ SchemaDocumentImpl.SchemaImpl f$0;

    public /* synthetic */ SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda11(SchemaDocumentImpl.SchemaImpl schemaImpl) {
        this.f$0 = schemaImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setNotationArray(((Integer) obj).intValue(), (NotationDocument.Notation) obj2);
    }
}
