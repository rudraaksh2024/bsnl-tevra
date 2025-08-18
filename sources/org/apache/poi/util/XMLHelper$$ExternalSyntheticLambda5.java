package org.apache.poi.util;

import javax.xml.validation.SchemaFactory;
import org.apache.poi.util.XMLHelper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda5 implements XMLHelper.SecurityProperty {
    public final /* synthetic */ SchemaFactory f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda5(SchemaFactory schemaFactory) {
        this.f$0 = schemaFactory;
    }

    public final void accept(String str, Object obj) {
        this.f$0.setProperty(str, obj);
    }
}
