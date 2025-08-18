package org.apache.poi.sl.usermodel;

import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SlideShowFactory$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ Class f$0;

    public /* synthetic */ SlideShowFactory$$ExternalSyntheticLambda0(Class cls) {
        this.f$0 = cls;
    }

    public final boolean test(Object obj) {
        return ((SlideShowProvider) obj).getClass().isAssignableFrom(this.f$0);
    }
}
