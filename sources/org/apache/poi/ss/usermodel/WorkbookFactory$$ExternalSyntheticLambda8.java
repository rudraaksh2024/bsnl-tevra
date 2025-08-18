package org.apache.poi.ss.usermodel;

import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WorkbookFactory$$ExternalSyntheticLambda8 implements Predicate {
    public final /* synthetic */ Class f$0;

    public /* synthetic */ WorkbookFactory$$ExternalSyntheticLambda8(Class cls) {
        this.f$0 = cls;
    }

    public final boolean test(Object obj) {
        return ((WorkbookProvider) obj).getClass().isAssignableFrom(this.f$0);
    }
}
