package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Locale;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Locale$$ExternalSyntheticLambda1 implements Locale.SyncWrapFun {
    public final /* synthetic */ SchemaType f$0;
    public final /* synthetic */ XmlOptions f$1;

    public /* synthetic */ Locale$$ExternalSyntheticLambda1(SchemaType schemaType, XmlOptions xmlOptions) {
        this.f$0 = schemaType;
        this.f$1 = xmlOptions;
    }

    public final Object parse(Locale locale) {
        return Locale.lambda$newSaxHandler$6(this.f$0, this.f$1, locale);
    }
}
