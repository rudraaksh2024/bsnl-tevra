package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSSFSheet$$ExternalSyntheticLambda0 implements XSSFPivotTable.PivotTableReferenceConfigurator {
    public final /* synthetic */ Name f$0;

    public /* synthetic */ XSSFSheet$$ExternalSyntheticLambda0(Name name) {
        this.f$0 = name;
    }

    public final void configureReference(CTWorksheetSource cTWorksheetSource) {
        cTWorksheetSource.setName(this.f$0.getNameName());
    }
}
