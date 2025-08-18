package org.apache.poi.util;

import javax.xml.stream.XMLInputFactory;
import org.apache.poi.util.XMLHelper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda3 implements XMLHelper.SecurityFeature {
    public final /* synthetic */ XMLInputFactory f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda3(XMLInputFactory xMLInputFactory) {
        this.f$0 = xMLInputFactory;
    }

    public final void accept(String str, boolean z) {
        this.f$0.setProperty(str, Boolean.valueOf(z));
    }
}
