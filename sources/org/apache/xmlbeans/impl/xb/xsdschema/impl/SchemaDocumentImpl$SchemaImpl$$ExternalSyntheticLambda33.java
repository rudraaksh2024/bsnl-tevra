package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda33 implements Consumer {
    public final /* synthetic */ SchemaDocumentImpl.SchemaImpl f$0;

    public /* synthetic */ SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda33(SchemaDocumentImpl.SchemaImpl schemaImpl) {
        this.f$0 = schemaImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeNotation(((Integer) obj).intValue());
    }
}
