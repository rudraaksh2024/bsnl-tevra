package org.apache.poi.poifs.crypt.dsig;

import org.w3c.dom.Document;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public interface SignatureMarshalListener {
    void handleElement(SignatureInfo signatureInfo, Document document, EventTarget eventTarget, EventListener eventListener);
}
