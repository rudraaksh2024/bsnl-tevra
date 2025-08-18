package org.apache.commons.io.file;

import java.nio.file.Path;
import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PathUtils$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ PathFilter f$0;

    public /* synthetic */ PathUtils$$ExternalSyntheticLambda1(PathFilter pathFilter) {
        this.f$0 = pathFilter;
    }

    public final boolean test(Object obj) {
        return PathUtils.lambda$filterPaths$0(this.f$0, (Path) obj);
    }
}
