package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSSFSheet$$ExternalSyntheticLambda4 implements XSSFPivotTable.PivotTableReferenceConfigurator {
    public final /* synthetic */ AreaReference f$0;

    public /* synthetic */ XSSFSheet$$ExternalSyntheticLambda4(AreaReference areaReference) {
        this.f$0 = areaReference;
    }

    public final void configureReference(CTWorksheetSource cTWorksheetSource) {
        XSSFSheet.lambda$createPivotTable$2(this.f$0, cTWorksheetSource);
    }
}
