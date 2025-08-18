package org.apache.poi.sl.extractor;

import com.zaxxer.sparsebits.SparseBitSet;
import java.util.function.IntConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SlideShowExtractor$$ExternalSyntheticLambda11 implements IntConsumer {
    public final /* synthetic */ SparseBitSet f$0;

    public /* synthetic */ SlideShowExtractor$$ExternalSyntheticLambda11(SparseBitSet sparseBitSet) {
        this.f$0 = sparseBitSet;
    }

    public final void accept(int i) {
        this.f$0.set(i);
    }
}
