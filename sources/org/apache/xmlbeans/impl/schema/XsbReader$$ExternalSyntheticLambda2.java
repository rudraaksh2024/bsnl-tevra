package org.apache.xmlbeans.impl.schema;

import java.util.function.Consumer;
import javax.xml.namespace.QName;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XsbReader$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ XsbReader f$0;

    public /* synthetic */ XsbReader$$ExternalSyntheticLambda2(XsbReader xsbReader) {
        this.f$0 = xsbReader;
    }

    public final void accept(Object obj) {
        this.f$0.writeQName((QName) obj);
    }
}
