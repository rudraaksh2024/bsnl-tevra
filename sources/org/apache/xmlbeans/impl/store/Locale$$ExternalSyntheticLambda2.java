package org.apache.xmlbeans.impl.store;

import java.io.Reader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Locale;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Locale$$ExternalSyntheticLambda2 implements Locale.SyncWrapFun {
    public final /* synthetic */ XmlOptions f$0;
    public final /* synthetic */ Reader f$1;
    public final /* synthetic */ SchemaType f$2;

    public /* synthetic */ Locale$$ExternalSyntheticLambda2(XmlOptions xmlOptions, Reader reader, SchemaType schemaType) {
        this.f$0 = xmlOptions;
        this.f$1 = reader;
        this.f$2 = schemaType;
    }

    public final Object parse(Locale locale) {
        return Locale.lambda$parseToXmlObject$4(this.f$0, this.f$1, this.f$2, locale);
    }
}
