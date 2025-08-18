package org.apache.xmlbeans.impl.config;

import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BindingConfigImpl$$ExternalSyntheticLambda3 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ BindingConfigImpl$$ExternalSyntheticLambda3(String str) {
        this.f$0 = str;
    }

    public final boolean test(Object obj) {
        return ((PrePostExtensionImpl) obj).contains(this.f$0);
    }
}
