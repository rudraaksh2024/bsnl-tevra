package org.apache.poi.util;

import javax.xml.validation.SchemaFactory;
import org.apache.poi.util.XMLHelper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda4 implements XMLHelper.SecurityFeature {
    public final /* synthetic */ SchemaFactory f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda4(SchemaFactory schemaFactory) {
        this.f$0 = schemaFactory;
    }

    public final void accept(String str, boolean z) {
        this.f$0.setFeature(str, z);
    }
}
