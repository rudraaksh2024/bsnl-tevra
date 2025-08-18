package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MavenPlugin$$ExternalSyntheticLambda1 implements FilenameFilter {
    public final /* synthetic */ Pattern f$0;

    public /* synthetic */ MavenPlugin$$ExternalSyntheticLambda1(Pattern pattern) {
        this.f$0 = pattern;
    }

    public final boolean accept(File file, String str) {
        return MavenPlugin.lambda$execute$0(this.f$0, file, str);
    }
}
