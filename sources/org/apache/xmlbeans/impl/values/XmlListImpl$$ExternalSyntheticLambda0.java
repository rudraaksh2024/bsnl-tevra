package org.apache.xmlbeans.impl.values;

import java.util.function.Function;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.common.ValidationContext;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XmlListImpl$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ SchemaType f$0;
    public final /* synthetic */ ValidationContext f$1;

    public /* synthetic */ XmlListImpl$$ExternalSyntheticLambda0(SchemaType schemaType, ValidationContext validationContext) {
        this.f$0 = schemaType;
        this.f$1 = validationContext;
    }

    public final Object apply(Object obj) {
        return XmlListImpl.lambda$lex$0(this.f$0, this.f$1, (String) obj);
    }
}
