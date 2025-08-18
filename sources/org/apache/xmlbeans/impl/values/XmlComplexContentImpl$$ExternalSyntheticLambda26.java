package org.apache.xmlbeans.impl.values;

import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XmlComplexContentImpl$$ExternalSyntheticLambda26 implements BiConsumer {
    public final /* synthetic */ byte[] f$0;

    public /* synthetic */ XmlComplexContentImpl$$ExternalSyntheticLambda26(byte[] bArr) {
        this.f$0 = bArr;
    }

    public final void accept(Object obj, Object obj2) {
        ((XmlObjectBase) obj).setByteValue(this.f$0[((Integer) obj2).intValue()]);
    }
}
