package org.apache.poi.xssf.streaming;

public interface RowGeneratorFunction {
    void generateRows(SXSSFSheet sXSSFSheet) throws Exception;
}
