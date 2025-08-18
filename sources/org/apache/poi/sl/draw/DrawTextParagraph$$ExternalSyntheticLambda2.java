package org.apache.poi.sl.draw;

import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DrawTextParagraph$$ExternalSyntheticLambda2 implements BiConsumer {
    public final /* synthetic */ AttributedString f$0;

    public /* synthetic */ DrawTextParagraph$$ExternalSyntheticLambda2(AttributedString attributedString) {
        this.f$0 = attributedString;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.addAttribute((TextAttribute) obj, obj2);
    }
}
