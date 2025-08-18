package org.apache.poi.poifs.crypt.dsig;

import java.util.function.BiConsumer;
import org.w3c.dom.Element;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignatureMarshalDefaultListener$$ExternalSyntheticLambda0 implements BiConsumer {
    public final /* synthetic */ Element f$0;

    public /* synthetic */ SignatureMarshalDefaultListener$$ExternalSyntheticLambda0(Element element) {
        this.f$0 = element;
    }

    public final void accept(Object obj, Object obj2) {
        SignatureMarshalDefaultListener.setXmlns(this.f$0, (String) obj2, (String) obj);
    }
}
