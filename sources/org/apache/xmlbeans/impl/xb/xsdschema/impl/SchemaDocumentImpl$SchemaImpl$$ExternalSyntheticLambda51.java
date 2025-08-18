package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda51 implements BiConsumer {
    public final /* synthetic */ SchemaDocumentImpl.SchemaImpl f$0;

    public /* synthetic */ SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda51(SchemaDocumentImpl.SchemaImpl schemaImpl) {
        this.f$0 = schemaImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setComplexTypeArray(((Integer) obj).intValue(), (TopLevelComplexType) obj2);
    }
}
