package org.apache.poi.util;

import javax.xml.stream.XMLOutputFactory;
import org.apache.poi.util.XMLHelper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda9 implements XMLHelper.SecurityFeature {
    public final /* synthetic */ XMLOutputFactory f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda9(XMLOutputFactory xMLOutputFactory) {
        this.f$0 = xMLOutputFactory;
    }

    public final void accept(String str, boolean z) {
        this.f$0.setProperty(str, Boolean.valueOf(z));
    }
}
