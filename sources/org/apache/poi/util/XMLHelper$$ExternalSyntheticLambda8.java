package org.apache.poi.util;

import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.poi.util.XMLHelper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda8 implements XMLHelper.SecurityFeature {
    public final /* synthetic */ DocumentBuilderFactory f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda8(DocumentBuilderFactory documentBuilderFactory) {
        this.f$0 = documentBuilderFactory;
    }

    public final void accept(String str, boolean z) {
        this.f$0.setXIncludeAware(z);
    }
}
