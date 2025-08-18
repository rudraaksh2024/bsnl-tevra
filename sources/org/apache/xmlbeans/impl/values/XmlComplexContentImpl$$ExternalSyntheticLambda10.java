package org.apache.xmlbeans.impl.values;

import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XmlComplexContentImpl$$ExternalSyntheticLambda10 implements BiConsumer {
    public final /* synthetic */ short[] f$0;

    public /* synthetic */ XmlComplexContentImpl$$ExternalSyntheticLambda10(short[] sArr) {
        this.f$0 = sArr;
    }

    public final void accept(Object obj, Object obj2) {
        ((XmlObjectBase) obj).setShortValue(this.f$0[((Integer) obj2).intValue()]);
    }
}
