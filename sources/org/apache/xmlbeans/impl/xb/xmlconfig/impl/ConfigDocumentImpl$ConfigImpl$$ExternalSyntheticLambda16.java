package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import java.util.function.BiConsumer;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda16 implements BiConsumer {
    public final /* synthetic */ ConfigDocumentImpl.ConfigImpl f$0;

    public /* synthetic */ ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda16(ConfigDocumentImpl.ConfigImpl configImpl) {
        this.f$0 = configImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setNamespaceArray(((Integer) obj).intValue(), (Nsconfig) obj2);
    }
}
