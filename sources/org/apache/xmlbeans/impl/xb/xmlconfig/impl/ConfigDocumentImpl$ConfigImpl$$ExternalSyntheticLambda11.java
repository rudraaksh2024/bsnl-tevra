package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda11 implements BiConsumer {
    public final /* synthetic */ ConfigDocumentImpl.ConfigImpl f$0;

    public /* synthetic */ ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda11(ConfigDocumentImpl.ConfigImpl configImpl) {
        this.f$0 = configImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setExtensionArray(((Integer) obj).intValue(), (Extensionconfig) obj2);
    }
}
