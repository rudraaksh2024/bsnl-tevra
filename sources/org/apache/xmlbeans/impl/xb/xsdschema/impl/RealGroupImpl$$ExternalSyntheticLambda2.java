package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RealGroupImpl$$ExternalSyntheticLambda2 implements BiConsumer {
    public final /* synthetic */ RealGroupImpl f$0;

    public /* synthetic */ RealGroupImpl$$ExternalSyntheticLambda2(RealGroupImpl realGroupImpl) {
        this.f$0 = realGroupImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setChoiceArray(((Integer) obj).intValue(), (ExplicitGroup) obj2);
    }
}
