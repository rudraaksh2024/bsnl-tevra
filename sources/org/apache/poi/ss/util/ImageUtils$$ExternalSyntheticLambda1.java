package org.apache.poi.ss.util;

import java.util.function.Function;
import org.apache.poi.ss.usermodel.Sheet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageUtils$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ Sheet f$0;

    public /* synthetic */ ImageUtils$$ExternalSyntheticLambda1(Sheet sheet) {
        this.f$0 = sheet;
    }

    public final Object apply(Object obj) {
        return Double.valueOf(ImageUtils.getRowHeightInPixels(this.f$0, ((Integer) obj).intValue()));
    }
}
