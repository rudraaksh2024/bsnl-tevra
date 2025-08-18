package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import java.util.function.Consumer;
import org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda13 implements Consumer {
    public final /* synthetic */ ConfigDocumentImpl.ConfigImpl f$0;

    public /* synthetic */ ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda13(ConfigDocumentImpl.ConfigImpl configImpl) {
        this.f$0 = configImpl;
    }

    public final void accept(Object obj) {
        this.f$0.removeExtension(((Integer) obj).intValue());
    }
}
