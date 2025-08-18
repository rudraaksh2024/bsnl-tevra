package org.apache.xmlbeans.impl.config;

import java.util.Map;
import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BindingConfigImpl$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ Map f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ BindingConfigImpl$$ExternalSyntheticLambda2(Map map, String str) {
        this.f$0 = map;
        this.f$1 = str;
    }

    public final void accept(Object obj) {
        BindingConfigImpl.lambda$recordNamespaceSetting$0(this.f$0, this.f$1, obj);
    }
}
