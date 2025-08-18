package org.apache.logging.log4j.message;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MapMessageJsonFormatter$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ StringBuilder f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MapMessageJsonFormatter$$ExternalSyntheticLambda0(boolean[] zArr, StringBuilder sb, int i) {
        this.f$0 = zArr;
        this.f$1 = sb;
        this.f$2 = i;
    }

    public final void accept(Object obj) {
        MapMessageJsonFormatter.lambda$formatCollection$1(this.f$0, this.f$1, this.f$2, obj);
    }
}
