package org.apache.poi.sl.draw;

import java.text.AttributedCharacterIterator;
import java.util.List;
import java.util.function.BiConsumer;
import org.apache.poi.sl.draw.DrawTextParagraph;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DrawTextParagraph$$ExternalSyntheticLambda0 implements BiConsumer {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DrawTextParagraph$$ExternalSyntheticLambda0(List list, int i, int i2) {
        this.f$0 = list;
        this.f$1 = i;
        this.f$2 = i2;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.add(new DrawTextParagraph.AttributedStringData((AttributedCharacterIterator.Attribute) obj, obj2, this.f$1, this.f$2));
    }
}
