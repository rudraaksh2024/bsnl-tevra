package org.apache.commons.io.filefilter;

import java.io.File;
import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FileFilterUtils$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ IOFileFilter f$0;

    public /* synthetic */ FileFilterUtils$$ExternalSyntheticLambda0(IOFileFilter iOFileFilter) {
        this.f$0 = iOFileFilter;
    }

    public final boolean test(Object obj) {
        return this.f$0.accept((File) obj);
    }
}
