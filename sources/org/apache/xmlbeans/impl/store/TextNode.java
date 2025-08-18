package org.apache.xmlbeans.impl.store;

import org.w3c.dom.Text;

class TextNode extends CharNode implements Text {
    public String name() {
        return "#text";
    }

    public int nodeType() {
        return 3;
    }

    TextNode(Locale locale) {
        super(locale);
    }

    public Text splitText(int i) {
        return DomImpl._text_splitText(this, i);
    }

    public String getWholeText() {
        return DomImpl._text_getWholeText(this);
    }

    public boolean isElementContentWhitespace() {
        return DomImpl._text_isElementContentWhitespace(this);
    }

    public Text replaceWholeText(String str) {
        return DomImpl._text_replaceWholeText(this, str);
    }
}
