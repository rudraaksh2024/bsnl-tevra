package org.apache.poi.ss.usermodel;

public interface TableStyleInfo {
    String getName();

    TableStyle getStyle();

    boolean isShowColumnStripes();

    boolean isShowFirstColumn();

    boolean isShowLastColumn();

    boolean isShowRowStripes();
}
