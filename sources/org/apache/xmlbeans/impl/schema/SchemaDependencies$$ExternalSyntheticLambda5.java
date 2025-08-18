package org.apache.xmlbeans.impl.schema;

import java.util.List;
import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SchemaDependencies$$ExternalSyntheticLambda5 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ SchemaDependencies$$ExternalSyntheticLambda5(String str) {
        this.f$0 = str;
    }

    public final boolean test(Object obj) {
        return ((List) obj).contains(this.f$0);
    }
}
