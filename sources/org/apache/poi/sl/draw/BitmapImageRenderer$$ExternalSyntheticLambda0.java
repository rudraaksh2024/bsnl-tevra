package org.apache.poi.sl.draw;

import java.util.function.Predicate;
import org.apache.poi.sl.usermodel.PictureData;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BitmapImageRenderer$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ BitmapImageRenderer$$ExternalSyntheticLambda0(String str) {
        this.f$0 = str;
    }

    public final boolean test(Object obj) {
        return ((PictureData.PictureType) obj).contentType.equalsIgnoreCase(this.f$0);
    }
}
