package org.apache.poi.openxml4j.opc;

import java.util.function.ToIntFunction;
import java.util.regex.Pattern;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PackagePartCollection$$ExternalSyntheticLambda1 implements ToIntFunction {
    public final /* synthetic */ Pattern f$0;

    public /* synthetic */ PackagePartCollection$$ExternalSyntheticLambda1(Pattern pattern) {
        this.f$0 = pattern;
    }

    public final int applyAsInt(Object obj) {
        return PackagePartCollection.lambda$getUnusedPartIndex$0(this.f$0, (String) obj);
    }
}
