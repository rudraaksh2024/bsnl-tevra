package org.apache.poi.util;

import javax.xml.transform.TransformerFactory;
import org.apache.poi.util.XMLHelper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda12 implements XMLHelper.SecurityProperty {
    public final /* synthetic */ TransformerFactory f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda12(TransformerFactory transformerFactory) {
        this.f$0 = transformerFactory;
    }

    public final void accept(String str, Object obj) {
        this.f$0.setAttribute(str, obj);
    }
}
