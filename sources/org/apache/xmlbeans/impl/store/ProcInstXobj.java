package org.apache.xmlbeans.impl.store;

import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;

class ProcInstXobj extends NodeXobj implements ProcessingInstruction {
    public Node getFirstChild() {
        return null;
    }

    public int getLength() {
        return 0;
    }

    ProcInstXobj(Locale locale, String str) {
        super(locale, 5, 7);
        this._name = this._locale.makeQName((String) null, str);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new ProcInstXobj(locale, this._name.getLocalPart());
    }

    public String getData() {
        return DomImpl._processingInstruction_getData(this);
    }

    public String getTarget() {
        return DomImpl._processingInstruction_getTarget(this);
    }

    public void setData(String str) {
        DomImpl._processingInstruction_setData(this, str);
    }
}
