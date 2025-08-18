package org.apache.poi.xddf.usermodel.chart;

public interface XDDFCategoryDataSource extends XDDFDataSource<String> {
    int getColIndex() {
        return 0;
    }

    boolean isLiteral() {
        return false;
    }

    boolean isNumeric() {
        return false;
    }

    boolean isReference() {
        return true;
    }
}
