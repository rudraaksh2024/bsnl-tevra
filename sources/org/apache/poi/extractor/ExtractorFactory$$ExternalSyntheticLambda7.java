package org.apache.poi.extractor;

import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtractorFactory$$ExternalSyntheticLambda7 implements Predicate {
    public final /* synthetic */ Class f$0;

    public /* synthetic */ ExtractorFactory$$ExternalSyntheticLambda7(Class cls) {
        this.f$0 = cls;
    }

    public final boolean test(Object obj) {
        return ((ExtractorProvider) obj).getClass().isAssignableFrom(this.f$0);
    }
}
