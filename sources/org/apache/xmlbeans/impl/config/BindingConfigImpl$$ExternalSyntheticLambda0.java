package org.apache.xmlbeans.impl.config;

import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BindingConfigImpl$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ BindingConfigImpl$$ExternalSyntheticLambda0(String str) {
        this.f$0 = str;
    }

    public final boolean test(Object obj) {
        return ((InterfaceExtensionImpl) obj).contains(this.f$0);
    }
}
