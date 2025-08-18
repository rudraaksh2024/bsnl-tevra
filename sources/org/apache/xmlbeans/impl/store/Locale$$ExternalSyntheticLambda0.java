package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Locale;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Locale$$ExternalSyntheticLambda0 implements Locale.SyncWrapFun {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ XmlOptions f$1;
    public final /* synthetic */ SchemaType f$2;

    public /* synthetic */ Locale$$ExternalSyntheticLambda0(String str, XmlOptions xmlOptions, SchemaType schemaType) {
        this.f$0 = str;
        this.f$1 = xmlOptions;
        this.f$2 = schemaType;
    }

    public final Object parse(Locale locale) {
        return Locale.lambda$parseToXmlObject$1(this.f$0, this.f$1, this.f$2, locale);
    }
}
