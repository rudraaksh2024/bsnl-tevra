package org.apache.xmlbeans.impl.values;

import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XmlComplexContentImpl$$ExternalSyntheticLambda9 implements BiConsumer {
    public final /* synthetic */ double[] f$0;

    public /* synthetic */ XmlComplexContentImpl$$ExternalSyntheticLambda9(double[] dArr) {
        this.f$0 = dArr;
    }

    public final void accept(Object obj, Object obj2) {
        ((XmlObjectBase) obj).setDoubleValue(this.f$0[((Integer) obj2).intValue()]);
    }
}
