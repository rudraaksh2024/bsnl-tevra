package org.apache.poi.extractor;

import java.util.List;
import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtractorFactory$$ExternalSyntheticLambda8 implements Consumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ ExtractorFactory$$ExternalSyntheticLambda8(List list) {
        this.f$0 = list;
    }

    public final void accept(Object obj) {
        this.f$0.add((ExtractorProvider) obj);
    }
}
