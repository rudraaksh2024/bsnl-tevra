package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GroupImpl$$ExternalSyntheticLambda26 implements BiConsumer {
    public final /* synthetic */ GroupImpl f$0;

    public /* synthetic */ GroupImpl$$ExternalSyntheticLambda26(GroupImpl groupImpl) {
        this.f$0 = groupImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setChoiceArray(((Integer) obj).intValue(), (ExplicitGroup) obj2);
    }
}
