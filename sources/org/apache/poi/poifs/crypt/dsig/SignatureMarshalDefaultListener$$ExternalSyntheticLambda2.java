package org.apache.poi.poifs.crypt.dsig;

import java.util.Map;
import java.util.function.Consumer;
import org.w3c.dom.Element;
import org.w3c.dom.traversal.DocumentTraversal;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignatureMarshalDefaultListener$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ SignatureMarshalDefaultListener f$0;
    public final /* synthetic */ DocumentTraversal f$1;
    public final /* synthetic */ Map f$2;
    public final /* synthetic */ Map f$3;

    public /* synthetic */ SignatureMarshalDefaultListener$$ExternalSyntheticLambda2(SignatureMarshalDefaultListener signatureMarshalDefaultListener, DocumentTraversal documentTraversal, Map map, Map map2) {
        this.f$0 = signatureMarshalDefaultListener;
        this.f$1 = documentTraversal;
        this.f$2 = map;
        this.f$3 = map2;
    }

    public final void accept(Object obj) {
        this.f$0.m2221lambda$null$1$orgapachepoipoifscryptdsigSignatureMarshalDefaultListener(this.f$1, this.f$2, this.f$3, (Element) obj);
    }
}
