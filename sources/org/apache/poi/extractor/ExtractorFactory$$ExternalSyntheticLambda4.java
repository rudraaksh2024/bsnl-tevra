package org.apache.poi.extractor;

import java.util.List;
import java.util.function.Consumer;
import org.apache.poi.poifs.filesystem.Entry;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtractorFactory$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ ExtractorFactory$$ExternalSyntheticLambda4(List list) {
        this.f$0 = list;
    }

    public final void accept(Object obj) {
        this.f$0.add((Entry) obj);
    }
}
