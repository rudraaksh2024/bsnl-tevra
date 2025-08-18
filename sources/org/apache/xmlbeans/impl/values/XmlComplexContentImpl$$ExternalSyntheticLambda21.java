package org.apache.xmlbeans.impl.values;

import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XmlComplexContentImpl$$ExternalSyntheticLambda21 implements BiConsumer {
    public final /* synthetic */ boolean[] f$0;

    public /* synthetic */ XmlComplexContentImpl$$ExternalSyntheticLambda21(boolean[] zArr) {
        this.f$0 = zArr;
    }

    public final void accept(Object obj, Object obj2) {
        ((XmlObjectBase) obj).setBooleanValue(this.f$0[((Integer) obj2).intValue()]);
    }
}
