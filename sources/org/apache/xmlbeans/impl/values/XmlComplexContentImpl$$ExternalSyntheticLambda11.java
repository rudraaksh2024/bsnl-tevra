package org.apache.xmlbeans.impl.values;

import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XmlComplexContentImpl$$ExternalSyntheticLambda11 implements BiConsumer {
    public final /* synthetic */ int[] f$0;

    public /* synthetic */ XmlComplexContentImpl$$ExternalSyntheticLambda11(int[] iArr) {
        this.f$0 = iArr;
    }

    public final void accept(Object obj, Object obj2) {
        ((XmlObjectBase) obj).setIntValue(this.f$0[((Integer) obj2).intValue()]);
    }
}
