package org.apache.xmlbeans.impl.values;

import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XmlComplexContentImpl$$ExternalSyntheticLambda18 implements BiConsumer {
    public final /* synthetic */ float[] f$0;

    public /* synthetic */ XmlComplexContentImpl$$ExternalSyntheticLambda18(float[] fArr) {
        this.f$0 = fArr;
    }

    public final void accept(Object obj, Object obj2) {
        ((XmlObjectBase) obj).setFloatValue(this.f$0[((Integer) obj2).intValue()]);
    }
}
