package org.apache.xmlbeans.impl.schema;

import java.util.function.Consumer;
import org.apache.xmlbeans.SchemaAnnotation;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SchemaTypeSystemImpl$$ExternalSyntheticLambda7 implements Consumer {
    public final /* synthetic */ SchemaContainer f$0;

    public /* synthetic */ SchemaTypeSystemImpl$$ExternalSyntheticLambda7(SchemaContainer schemaContainer) {
        this.f$0 = schemaContainer;
    }

    public final void accept(Object obj) {
        this.f$0.addAnnotation((SchemaAnnotation) obj);
    }
}
