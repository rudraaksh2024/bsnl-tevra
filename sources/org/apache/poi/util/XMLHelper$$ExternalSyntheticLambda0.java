package org.apache.poi.util;

import javax.xml.parsers.SAXParserFactory;
import org.apache.poi.util.XMLHelper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda0 implements XMLHelper.SecurityFeature {
    public final /* synthetic */ SAXParserFactory f$0;

    public /* synthetic */ XMLHelper$$ExternalSyntheticLambda0(SAXParserFactory sAXParserFactory) {
        this.f$0 = sAXParserFactory;
    }

    public final void accept(String str, boolean z) {
        this.f$0.setFeature(str, z);
    }
}
