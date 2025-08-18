package org.apache.poi.sl.draw;

import java.text.AttributedCharacterIterator;
import java.util.List;
import java.util.function.BiConsumer;
import org.apache.poi.sl.draw.DrawTextParagraph;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DrawTextParagraph$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ DrawTextParagraph$$ExternalSyntheticLambda1(List list) {
        this.f$0 = list;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.add(new DrawTextParagraph.AttributedStringData((AttributedCharacterIterator.Attribute) obj, obj2, 0, 1));
    }
}
