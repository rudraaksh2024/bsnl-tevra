package org.apache.poi.ss.usermodel;

import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WorkbookFactory$$ExternalSyntheticLambda5 implements WorkbookFactory.ProviderMethod {
    public final /* synthetic */ DirectoryNode f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ WorkbookFactory$$ExternalSyntheticLambda5(DirectoryNode directoryNode, String str) {
        this.f$0 = directoryNode;
        this.f$1 = str;
    }

    public final Workbook create(WorkbookProvider workbookProvider) {
        return workbookProvider.create(this.f$0, this.f$1);
    }
}
