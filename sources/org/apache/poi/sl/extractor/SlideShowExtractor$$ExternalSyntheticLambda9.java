package org.apache.poi.sl.extractor;

import com.zaxxer.sparsebits.SparseBitSet;
import java.util.function.Consumer;
import org.apache.poi.sl.usermodel.Slide;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SlideShowExtractor$$ExternalSyntheticLambda9 implements Consumer {
    public final /* synthetic */ SlideShowExtractor f$0;
    public final /* synthetic */ SparseBitSet f$1;

    public /* synthetic */ SlideShowExtractor$$ExternalSyntheticLambda9(SlideShowExtractor slideShowExtractor, SparseBitSet sparseBitSet) {
        this.f$0 = slideShowExtractor;
        this.f$1 = sparseBitSet;
    }

    public final void accept(Object obj) {
        this.f$0.m2240lambda$getCodepointsInSparseBitSet$7$orgapachepoislextractorSlideShowExtractor(this.f$1, (Slide) obj);
    }
}
