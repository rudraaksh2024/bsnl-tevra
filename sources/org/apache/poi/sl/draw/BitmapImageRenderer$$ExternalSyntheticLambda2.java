package org.apache.poi.sl.draw;

import java.util.function.Consumer;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageTypeSpecifier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BitmapImageRenderer$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ ImageReadParam f$0;

    public /* synthetic */ BitmapImageRenderer$$ExternalSyntheticLambda2(ImageReadParam imageReadParam) {
        this.f$0 = imageReadParam;
    }

    public final void accept(Object obj) {
        this.f$0.setDestinationType((ImageTypeSpecifier) obj);
    }
}
