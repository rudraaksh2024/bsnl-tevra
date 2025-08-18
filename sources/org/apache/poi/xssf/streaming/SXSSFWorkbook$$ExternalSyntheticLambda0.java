package org.apache.poi.xssf.streaming;

import java.io.OutputStream;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SXSSFWorkbook$$ExternalSyntheticLambda0 implements SXSSFWorkbook.ISheetInjector {
    public final /* synthetic */ SXSSFSheet f$0;

    public /* synthetic */ SXSSFWorkbook$$ExternalSyntheticLambda0(SXSSFSheet sXSSFSheet) {
        this.f$0 = sXSSFSheet;
    }

    public final void writeSheetData(OutputStream outputStream) {
        SXSSFWorkbook.lambda$createSheetInjector$0(this.f$0, outputStream);
    }
}
