package org.apache.poi.util;

import org.apache.poi.util.XMLHelper;
import org.xml.sax.XMLReader;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda1 implements XMLHelper.SecurityFeature {
    public final /* synthetic */ XMLReader f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda1(XMLReader xMLReader) {
        this.f$0 = xMLReader;
    }

    public final void accept(String str, boolean z) {
        this.f$0.setFeature(str, z);
    }
}
