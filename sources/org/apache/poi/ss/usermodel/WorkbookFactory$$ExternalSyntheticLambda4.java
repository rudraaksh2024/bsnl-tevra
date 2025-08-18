package org.apache.poi.ss.usermodel;

import java.io.InputStream;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WorkbookFactory$$ExternalSyntheticLambda4 implements WorkbookFactory.ProviderMethod {
    public final /* synthetic */ InputStream f$0;

    public /* synthetic */ WorkbookFactory$$ExternalSyntheticLambda4(InputStream inputStream) {
        this.f$0 = inputStream;
    }

    public final Workbook create(WorkbookProvider workbookProvider) {
        return workbookProvider.create(this.f$0);
    }
}
