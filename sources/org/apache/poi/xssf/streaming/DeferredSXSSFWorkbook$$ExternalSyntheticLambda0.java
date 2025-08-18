package org.apache.poi.xssf.streaming;

import java.io.OutputStream;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeferredSXSSFWorkbook$$ExternalSyntheticLambda0 implements SXSSFWorkbook.ISheetInjector {
    public final /* synthetic */ DeferredSXSSFSheet f$0;

    public /* synthetic */ DeferredSXSSFWorkbook$$ExternalSyntheticLambda0(DeferredSXSSFSheet deferredSXSSFSheet) {
        this.f$0 = deferredSXSSFSheet;
    }

    public final void writeSheetData(OutputStream outputStream) {
        this.f$0.writeRows(outputStream);
    }
}
