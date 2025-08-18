package org.apache.poi.xslf.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.regex.Pattern;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PDFFontMapper$$ExternalSyntheticLambda3 implements FilenameFilter {
    public final /* synthetic */ LinkedList f$0;
    public final /* synthetic */ Pattern f$1;

    public /* synthetic */ PDFFontMapper$$ExternalSyntheticLambda3(LinkedList linkedList, Pattern pattern) {
        this.f$0 = linkedList;
        this.f$1 = pattern;
    }

    public final boolean accept(File file, String str) {
        return PDFFontMapper.lambda$registerFonts$0(this.f$0, this.f$1, file, str);
    }
}
