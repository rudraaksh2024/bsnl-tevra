package org.apache.xmlbeans.impl.store;

import java.util.function.Function;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.w3c.dom.Element;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda67 implements Function {
    public final /* synthetic */ Element f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ QName f$2;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda67(Element element, boolean z, QName qName) {
        this.f$0 = element;
        this.f$1 = z;
        this.f$2 = qName;
    }

    public final Object apply(Object obj) {
        return DomImpl.impl_saajCallback_importSoapElement((DomImpl.Dom) obj, this.f$0, this.f$1, this.f$2);
    }
}
