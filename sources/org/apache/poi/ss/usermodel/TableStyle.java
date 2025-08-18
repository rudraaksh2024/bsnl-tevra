package org.apache.poi.ss.usermodel;

public interface TableStyle {
    int getIndex();

    String getName();

    DifferentialStyleProvider getStyle(TableStyleType tableStyleType);

    boolean isBuiltin();
}
