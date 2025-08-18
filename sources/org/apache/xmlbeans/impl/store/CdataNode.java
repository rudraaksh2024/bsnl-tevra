package org.apache.xmlbeans.impl.store;

import org.w3c.dom.CDATASection;

class CdataNode extends TextNode implements CDATASection {
    public String name() {
        return "#cdata-section";
    }

    public int nodeType() {
        return 4;
    }

    CdataNode(Locale locale) {
        super(locale);
    }
}
