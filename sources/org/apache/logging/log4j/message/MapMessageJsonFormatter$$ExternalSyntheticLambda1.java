package org.apache.logging.log4j.message;

import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MapMessageJsonFormatter$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ StringBuilder f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MapMessageJsonFormatter$$ExternalSyntheticLambda1(boolean[] zArr, StringBuilder sb, int i) {
        this.f$0 = zArr;
        this.f$1 = sb;
        this.f$2 = i;
    }

    public final void accept(Object obj, Object obj2) {
        MapMessageJsonFormatter.lambda$formatMap$0(this.f$0, this.f$1, this.f$2, obj, obj2);
    }
}
