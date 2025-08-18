package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import java.util.function.Function;
import org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda17 implements Function {
    public final /* synthetic */ ConfigDocumentImpl.ConfigImpl f$0;

    public /* synthetic */ ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda17(ConfigDocumentImpl.ConfigImpl configImpl) {
        this.f$0 = configImpl;
    }

    public final Object apply(Object obj) {
        return this.f$0.insertNewNamespace(((Integer) obj).intValue());
    }
}
