package org.apache.commons.io.filefilter;

import java.nio.file.Path;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RegexFileFilter$$ExternalSyntheticLambda0 implements Function {
    public final Object apply(Object obj) {
        return ((Path) obj).getFileName().toString();
    }
}
