package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AttributeGroupImpl$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ AttributeGroupImpl f$0;

    public /* synthetic */ AttributeGroupImpl$$ExternalSyntheticLambda1(AttributeGroupImpl attributeGroupImpl) {
        this.f$0 = attributeGroupImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setAttributeGroupArray(((Integer) obj).intValue(), (AttributeGroupRef) obj2);
    }
}
