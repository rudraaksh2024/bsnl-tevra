package org.apache.poi.util;

import javax.xml.transform.TransformerFactory;
import org.apache.poi.util.XMLHelper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda11 implements XMLHelper.SecurityFeature {
    public final /* synthetic */ TransformerFactory f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda11(TransformerFactory transformerFactory) {
        this.f$0 = transformerFactory;
    }

    public final void accept(String str, boolean z) {
        this.f$0.setFeature(str, z);
    }
}
