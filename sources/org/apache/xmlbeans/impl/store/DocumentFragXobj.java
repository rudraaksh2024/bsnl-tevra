package org.apache.xmlbeans.impl.store;

import org.w3c.dom.DocumentFragment;

class DocumentFragXobj extends NodeXobj implements DocumentFragment {
    DocumentFragXobj(Locale locale) {
        super(locale, 1, 11);
    }

    /* access modifiers changed from: package-private */
    public Xobj newNode(Locale locale) {
        return new DocumentFragXobj(locale);
    }
}
