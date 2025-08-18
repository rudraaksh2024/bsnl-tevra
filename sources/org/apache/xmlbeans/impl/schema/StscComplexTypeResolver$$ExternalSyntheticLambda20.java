package org.apache.xmlbeans.impl.schema;

import java.util.function.Function;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StscComplexTypeResolver$$ExternalSyntheticLambda20 implements Function {
    public final /* synthetic */ SchemaType f$0;

    public /* synthetic */ StscComplexTypeResolver$$ExternalSyntheticLambda20(SchemaType schemaType) {
        this.f$0 = schemaType;
    }

    public final Object apply(Object obj) {
        return StscComplexTypeResolver.buildUseProperty((SchemaLocalAttribute) obj, this.f$0);
    }
}
