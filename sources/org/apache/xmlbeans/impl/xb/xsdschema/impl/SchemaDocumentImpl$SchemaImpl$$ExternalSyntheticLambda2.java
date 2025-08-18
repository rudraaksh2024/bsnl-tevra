package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda2 implements BiConsumer {
    public final /* synthetic */ SchemaDocumentImpl.SchemaImpl f$0;

    public /* synthetic */ SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda2(SchemaDocumentImpl.SchemaImpl schemaImpl) {
        this.f$0 = schemaImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setGroupArray(((Integer) obj).intValue(), (NamedGroup) obj2);
    }
}
