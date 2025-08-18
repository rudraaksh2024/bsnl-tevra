package org.apache.xmlbeans.impl.schema;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SchemaDependencies$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ Set f$0;

    public /* synthetic */ SchemaDependencies$$ExternalSyntheticLambda1(Set set) {
        this.f$0 = set;
    }

    public final boolean test(Object obj) {
        return SchemaDependencies.lambda$getNamespacesTouched$3(this.f$0, (Map.Entry) obj);
    }
}
