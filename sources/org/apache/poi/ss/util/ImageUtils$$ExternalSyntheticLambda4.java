package org.apache.poi.ss.util;

import java.util.function.Consumer;
import org.apache.poi.ss.usermodel.ClientAnchor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageUtils$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ ClientAnchor f$0;

    public /* synthetic */ ImageUtils$$ExternalSyntheticLambda4(ClientAnchor clientAnchor) {
        this.f$0 = clientAnchor;
    }

    public final void accept(Object obj) {
        this.f$0.setRow2(((Integer) obj).intValue());
    }
}
