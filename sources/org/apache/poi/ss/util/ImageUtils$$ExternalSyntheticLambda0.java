package org.apache.poi.ss.util;

import java.util.function.Function;
import org.apache.poi.ss.usermodel.Sheet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageUtils$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Sheet f$0;

    public /* synthetic */ ImageUtils$$ExternalSyntheticLambda0(Sheet sheet) {
        this.f$0 = sheet;
    }

    public final Object apply(Object obj) {
        return Float.valueOf(this.f$0.getColumnWidthInPixels(((Integer) obj).intValue()));
    }
}
