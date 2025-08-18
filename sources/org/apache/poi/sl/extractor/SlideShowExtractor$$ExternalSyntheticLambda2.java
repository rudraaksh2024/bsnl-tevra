package org.apache.poi.sl.extractor;

import java.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SlideShowExtractor$$ExternalSyntheticLambda2 implements Predicate {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Boolean f$1;
    public final /* synthetic */ Boolean f$2;

    public /* synthetic */ SlideShowExtractor$$ExternalSyntheticLambda2(String str, Boolean bool, Boolean bool2) {
        this.f$0 = str;
        this.f$1 = bool;
        this.f$2 = bool2;
    }

    public final boolean test(Object obj) {
        return SlideShowExtractor.filterFonts(obj, this.f$0, this.f$1, this.f$2);
    }
}
