package org.apache.commons.io.file;

import java.nio.file.Path;
import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PathUtils$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ PathFilter f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ PathUtils$$ExternalSyntheticLambda0(PathFilter pathFilter, boolean z) {
        this.f$0 = pathFilter;
        this.f$1 = z;
    }

    public final boolean test(Object obj) {
        return PathUtils.lambda$walk$2(this.f$0, this.f$1, (Path) obj);
    }
}
