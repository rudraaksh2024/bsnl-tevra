package org.apache.poi.xssf.usermodel;

import java.util.Comparator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSSFSheet$$ExternalSyntheticLambda2 implements Comparator {
    public final /* synthetic */ int f$0;

    public /* synthetic */ XSSFSheet$$ExternalSyntheticLambda2(int i) {
        this.f$0 = i;
    }

    public final int compare(Object obj, Object obj2) {
        return XSSFSheet.lambda$shiftCommentsAndRows$0(this.f$0, (XSSFComment) obj, (XSSFComment) obj2);
    }
}
