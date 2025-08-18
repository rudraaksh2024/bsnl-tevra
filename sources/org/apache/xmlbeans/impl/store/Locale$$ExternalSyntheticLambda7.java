package org.apache.xmlbeans.impl.store;

import java.io.InputStream;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Locale;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Locale$$ExternalSyntheticLambda7 implements Locale.SyncWrapFun {
    public final /* synthetic */ XmlOptions f$0;
    public final /* synthetic */ InputStream f$1;
    public final /* synthetic */ SchemaType f$2;

    public /* synthetic */ Locale$$ExternalSyntheticLambda7(XmlOptions xmlOptions, InputStream inputStream, SchemaType schemaType) {
        this.f$0 = xmlOptions;
        this.f$1 = inputStream;
        this.f$2 = schemaType;
    }

    public final Object parse(Locale locale) {
        return Locale.lambda$parseToXmlObject$3(this.f$0, this.f$1, this.f$2, locale);
    }
}
