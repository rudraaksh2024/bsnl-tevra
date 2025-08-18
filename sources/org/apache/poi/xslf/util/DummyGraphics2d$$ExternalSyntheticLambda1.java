package org.apache.poi.xslf.util;

import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DummyGraphics2d$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ Map f$0;
    public final /* synthetic */ AttributedCharacterIterator f$1;

    public /* synthetic */ DummyGraphics2d$$ExternalSyntheticLambda1(Map map, AttributedCharacterIterator attributedCharacterIterator) {
        this.f$0 = map;
        this.f$1 = attributedCharacterIterator;
    }

    public final void accept(Object obj, Object obj2) {
        ((Map) this.f$0.computeIfAbsent((AttributedCharacterIterator.Attribute) obj, new DummyGraphics2d$$ExternalSyntheticLambda0())).put(Integer.valueOf(this.f$1.getIndex()), obj2);
    }
}
