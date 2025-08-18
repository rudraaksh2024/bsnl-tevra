package org.apache.poi.util;

import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.poi.util.XMLHelper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda7 implements XMLHelper.SecurityProperty {
    public final /* synthetic */ DocumentBuilderFactory f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda7(DocumentBuilderFactory documentBuilderFactory) {
        this.f$0 = documentBuilderFactory;
    }

    public final void accept(String str, Object obj) {
        this.f$0.setAttribute(str, obj);
    }
}
