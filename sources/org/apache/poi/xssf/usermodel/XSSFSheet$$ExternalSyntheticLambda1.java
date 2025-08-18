package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSSFSheet$$ExternalSyntheticLambda1 implements XSSFPivotTable.PivotTableReferenceConfigurator {
    public final /* synthetic */ Table f$0;

    public /* synthetic */ XSSFSheet$$ExternalSyntheticLambda1(Table table) {
        this.f$0 = table;
    }

    public final void configureReference(CTWorksheetSource cTWorksheetSource) {
        cTWorksheetSource.setName(this.f$0.getName());
    }
}
