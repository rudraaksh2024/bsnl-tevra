package org.apache.xmlbeans.impl.values;

import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XmlComplexContentImpl$$ExternalSyntheticLambda25 implements BiConsumer {
    public final /* synthetic */ long[] f$0;

    public /* synthetic */ XmlComplexContentImpl$$ExternalSyntheticLambda25(long[] jArr) {
        this.f$0 = jArr;
    }

    public final void accept(Object obj, Object obj2) {
        ((XmlObjectBase) obj).setLongValue(this.f$0[((Integer) obj2).intValue()]);
    }
}
