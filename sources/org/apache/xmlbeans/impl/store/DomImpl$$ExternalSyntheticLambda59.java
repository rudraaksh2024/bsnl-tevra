package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.store.DomImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda59 implements Function {
    public final /* synthetic */ QName f$0;
    public final /* synthetic */ QName f$1;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda59(QName qName, QName qName2) {
        this.f$0 = qName;
        this.f$1 = qName2;
    }

    public final Object apply(Object obj) {
        return DomImpl.impl_saajCallback_createSoapElement((DomImpl.Dom) obj, this.f$0, this.f$1);
    }
}
