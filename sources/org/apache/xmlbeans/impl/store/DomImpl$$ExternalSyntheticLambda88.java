package org.apache.xmlbeans.impl.store;

import java.util.function.Supplier;
import org.w3c.dom.DocumentType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DomImpl$$ExternalSyntheticLambda88 implements Supplier {
    public final /* synthetic */ Locale f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ DocumentType f$3;

    public /* synthetic */ DomImpl$$ExternalSyntheticLambda88(Locale locale, String str, String str2, DocumentType documentType) {
        this.f$0 = locale;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = documentType;
    }

    public final Object get() {
        return DomImpl.domImplementation_createDocument(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
