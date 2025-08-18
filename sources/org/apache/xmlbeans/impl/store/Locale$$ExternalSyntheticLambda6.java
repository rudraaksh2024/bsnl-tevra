package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Locale;
import org.w3c.dom.Node;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Locale$$ExternalSyntheticLambda6 implements Locale.SyncWrapFun {
    public final /* synthetic */ XmlOptions f$0;
    public final /* synthetic */ Node f$1;
    public final /* synthetic */ SchemaType f$2;

    public /* synthetic */ Locale$$ExternalSyntheticLambda6(XmlOptions xmlOptions, Node node, SchemaType schemaType) {
        this.f$0 = xmlOptions;
        this.f$1 = node;
        this.f$2 = schemaType;
    }

    public final Object parse(Locale locale) {
        return Locale.lambda$parseToXmlObject$5(this.f$0, this.f$1, this.f$2, locale);
    }
}
