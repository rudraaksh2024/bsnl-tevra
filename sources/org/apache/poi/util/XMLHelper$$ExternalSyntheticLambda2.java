package org.apache.poi.util;

import org.apache.poi.util.XMLHelper;
import org.xml.sax.XMLReader;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda2 implements XMLHelper.SecurityProperty {
    public final /* synthetic */ XMLReader f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda2(XMLReader xMLReader) {
        this.f$0 = xMLReader;
    }

    public final void accept(String str, Object obj) {
        this.f$0.setProperty(str, obj);
    }
}
