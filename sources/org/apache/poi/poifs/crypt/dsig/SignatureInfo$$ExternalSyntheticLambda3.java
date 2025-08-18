package org.apache.poi.poifs.crypt.dsig;

import org.w3c.dom.Document;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SignatureInfo$$ExternalSyntheticLambda3 implements EventListener {
    public final /* synthetic */ SignatureInfo f$0;
    public final /* synthetic */ EventTarget f$1;
    public final /* synthetic */ EventListener[] f$2;
    public final /* synthetic */ SignatureMarshalListener f$3;
    public final /* synthetic */ Document f$4;

    public /* synthetic */ SignatureInfo$$ExternalSyntheticLambda3(SignatureInfo signatureInfo, EventTarget eventTarget, EventListener[] eventListenerArr, SignatureMarshalListener signatureMarshalListener, Document document) {
        this.f$0 = signatureInfo;
        this.f$1 = eventTarget;
        this.f$2 = eventListenerArr;
        this.f$3 = signatureMarshalListener;
        this.f$4 = document;
    }

    public final void handleEvent(Event event) {
        this.f$0.m2219lambda$registerEventListener$1$orgapachepoipoifscryptdsigSignatureInfo(this.f$1, this.f$2, this.f$3, this.f$4, event);
    }
}
