package org.apache.poi.ss.usermodel;

import java.io.File;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WorkbookFactory$$ExternalSyntheticLambda3 implements WorkbookFactory.ProviderMethod {
    public final /* synthetic */ File f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ WorkbookFactory$$ExternalSyntheticLambda3(File file, String str, boolean z) {
        this.f$0 = file;
        this.f$1 = str;
        this.f$2 = z;
    }

    public final Workbook create(WorkbookProvider workbookProvider) {
        return workbookProvider.create(this.f$0, this.f$1, this.f$2);
    }
}
