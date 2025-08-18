package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MavenPlugin$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ File f$0;

    public /* synthetic */ MavenPlugin$$ExternalSyntheticLambda2(File file) {
        this.f$0 = file;
    }

    public final Object apply(Object obj) {
        return Stream.of(new File[]{new File((String) obj), new File(this.f$0, (String) obj)}).filter(new MavenPlugin$$ExternalSyntheticLambda0());
    }
}
