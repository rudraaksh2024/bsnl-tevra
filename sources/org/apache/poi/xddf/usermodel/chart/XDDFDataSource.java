package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumVal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrVal;

public interface XDDFDataSource<T> {
    int getColIndex();

    String getDataRangeReference();

    String getFormatCode();

    T getPointAt(int i);

    int getPointCount();

    boolean isCellRange();

    boolean isLiteral();

    boolean isNumeric();

    boolean isReference();

    String getFormula() {
        return getDataRangeReference();
    }

    @Internal
    void fillNumericalCache(CTNumData cTNumData) {
        String formatCode = getFormatCode();
        if (formatCode != null) {
            cTNumData.setFormatCode(formatCode);
        } else if (cTNumData.isSetFormatCode()) {
            cTNumData.unsetFormatCode();
        }
        cTNumData.setPtArray((CTNumVal[]) null);
        int pointCount = getPointCount();
        int i = 0;
        for (int i2 = 0; i2 < pointCount; i2++) {
            Object pointAt = getPointAt(i2);
            if (pointAt != null) {
                CTNumVal addNewPt = cTNumData.addNewPt();
                addNewPt.setIdx((long) i2);
                addNewPt.setV(pointAt.toString());
                i++;
            }
        }
        if (i == 0) {
            if (cTNumData.isSetPtCount()) {
                cTNumData.unsetPtCount();
            }
        } else if (cTNumData.isSetPtCount()) {
            cTNumData.getPtCount().setVal((long) pointCount);
        } else {
            cTNumData.addNewPtCount().setVal((long) pointCount);
        }
    }

    @Internal
    void fillStringCache(CTStrData cTStrData) {
        cTStrData.setPtArray((CTStrVal[]) null);
        int pointCount = getPointCount();
        int i = 0;
        for (int i2 = 0; i2 < pointCount; i2++) {
            Object pointAt = getPointAt(i2);
            if (pointAt != null) {
                CTStrVal addNewPt = cTStrData.addNewPt();
                addNewPt.setIdx((long) i2);
                addNewPt.setV(pointAt.toString());
                i++;
            }
        }
        if (i == 0) {
            if (cTStrData.isSetPtCount()) {
                cTStrData.unsetPtCount();
            }
        } else if (cTStrData.isSetPtCount()) {
            cTStrData.getPtCount().setVal((long) pointCount);
        } else {
            cTStrData.addNewPtCount().setVal((long) pointCount);
        }
    }
}
