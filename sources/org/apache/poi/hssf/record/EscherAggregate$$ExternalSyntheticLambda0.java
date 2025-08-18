package org.apache.poi.hssf.record;

import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EscherAggregate$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ EscherAggregate f$0;
    public final /* synthetic */ Iterator f$1;

    public /* synthetic */ EscherAggregate$$ExternalSyntheticLambda0(EscherAggregate escherAggregate, Iterator it) {
        this.f$0 = escherAggregate;
        this.f$1 = it;
    }

    public final void accept(Object obj) {
        this.f$0.shapeToObj.put(this.f$1.next(), (Record) obj);
    }
}
