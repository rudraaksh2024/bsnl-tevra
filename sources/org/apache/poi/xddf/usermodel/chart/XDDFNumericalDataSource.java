package org.apache.poi.xddf.usermodel.chart;

import java.lang.Number;

public interface XDDFNumericalDataSource<T extends Number> extends XDDFDataSource<T> {
    boolean isLiteral() {
        return false;
    }

    void setFormatCode(String str);
}
