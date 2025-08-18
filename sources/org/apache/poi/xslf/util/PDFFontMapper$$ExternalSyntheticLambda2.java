package org.apache.poi.xslf.util;

import java.io.File;
import java.util.LinkedList;
import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PDFFontMapper$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ LinkedList f$0;

    public /* synthetic */ PDFFontMapper$$ExternalSyntheticLambda2(LinkedList linkedList) {
        this.f$0 = linkedList;
    }

    public final void accept(Object obj) {
        boolean unused = this.f$0.add((File) obj);
    }
}
