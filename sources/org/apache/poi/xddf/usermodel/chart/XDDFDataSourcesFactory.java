package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;

public class XDDFDataSourcesFactory {
    private XDDFDataSourcesFactory() {
    }

    public static XDDFCategoryDataSource fromDataSource(CTAxDataSource cTAxDataSource) {
        if (cTAxDataSource == null) {
            return null;
        }
        if (cTAxDataSource.getNumRef() != null && cTAxDataSource.getNumRef().getNumCache() != null) {
            return new XDDFCategoryDataSource(cTAxDataSource) {
                private final CTNumData category;
                private final String formatCode;
                final /* synthetic */ CTAxDataSource val$categoryDS;

                public boolean isCellRange() {
                    return true;
                }

                public boolean isNumeric() {
                    return true;
                }

                {
                    this.val$categoryDS = r2;
                    CTNumData cTNumData = (CTNumData) r2.getNumRef().getNumCache().copy();
                    this.category = cTNumData;
                    this.formatCode = cTNumData.isSetFormatCode() ? cTNumData.getFormatCode() : null;
                }

                public String getDataRangeReference() {
                    return this.val$categoryDS.getNumRef().getF();
                }

                public int getPointCount() {
                    return (int) this.category.getPtCount().getVal();
                }

                public String getPointAt(int i) {
                    if (this.category.sizeOfPtArray() > i) {
                        return this.category.getPtArray(i).getV();
                    }
                    throw new IllegalArgumentException("Cannot access 0-based index " + i + " in point-array with " + this.category.sizeOfPtArray() + " items");
                }

                public String getFormatCode() {
                    return this.formatCode;
                }
            };
        }
        if (cTAxDataSource.getStrRef() != null && cTAxDataSource.getStrRef().getStrCache() != null) {
            return new XDDFCategoryDataSource(cTAxDataSource) {
                private final CTStrData category;
                final /* synthetic */ CTAxDataSource val$categoryDS;

                public String getFormatCode() {
                    return null;
                }

                public boolean isCellRange() {
                    return true;
                }

                {
                    this.val$categoryDS = r1;
                    this.category = (CTStrData) r1.getStrRef().getStrCache().copy();
                }

                public String getDataRangeReference() {
                    return this.val$categoryDS.getStrRef().getF();
                }

                public int getPointCount() {
                    return (int) this.category.getPtCount().getVal();
                }

                public String getPointAt(int i) {
                    return this.category.getPtArray(i).getV();
                }
            };
        }
        if (cTAxDataSource.getNumLit() != null) {
            return new XDDFCategoryDataSource(cTAxDataSource) {
                private final CTNumData category;
                private final String formatCode;
                final /* synthetic */ CTAxDataSource val$categoryDS;

                public String getDataRangeReference() {
                    return null;
                }

                public boolean isCellRange() {
                    return false;
                }

                public boolean isLiteral() {
                    return true;
                }

                public boolean isNumeric() {
                    return true;
                }

                public boolean isReference() {
                    return false;
                }

                {
                    this.val$categoryDS = r2;
                    CTNumData cTNumData = (CTNumData) r2.getNumLit().copy();
                    this.category = cTNumData;
                    this.formatCode = cTNumData.isSetFormatCode() ? cTNumData.getFormatCode() : null;
                }

                public int getPointCount() {
                    return (int) this.category.getPtCount().getVal();
                }

                public String getPointAt(int i) {
                    return this.category.getPtArray(i).getV();
                }

                public String getFormatCode() {
                    return this.formatCode;
                }
            };
        }
        if (cTAxDataSource.getStrLit() != null) {
            return new XDDFCategoryDataSource(cTAxDataSource) {
                private final CTStrData category;
                final /* synthetic */ CTAxDataSource val$categoryDS;

                public String getDataRangeReference() {
                    return null;
                }

                public String getFormatCode() {
                    return null;
                }

                public boolean isCellRange() {
                    return false;
                }

                public boolean isLiteral() {
                    return true;
                }

                public boolean isReference() {
                    return false;
                }

                {
                    this.val$categoryDS = r1;
                    this.category = (CTStrData) r1.getStrLit().copy();
                }

                public int getPointCount() {
                    return (int) this.category.getPtCount().getVal();
                }

                public String getPointAt(int i) {
                    return this.category.getPtArray(i).getV();
                }
            };
        }
        return null;
    }

    public static XDDFNumericalDataSource<Double> fromDataSource(CTNumDataSource cTNumDataSource) {
        if (cTNumDataSource == null) {
            return null;
        }
        if (cTNumDataSource.getNumRef() != null && cTNumDataSource.getNumRef().getNumCache() != null) {
            return new XDDFNumericalDataSource<Double>(cTNumDataSource) {
                private String formatCode;
                final /* synthetic */ CTNumDataSource val$valuesDS;
                private final CTNumData values;

                public int getColIndex() {
                    return 0;
                }

                public boolean isCellRange() {
                    return true;
                }

                public boolean isNumeric() {
                    return true;
                }

                public boolean isReference() {
                    return true;
                }

                {
                    this.val$valuesDS = r2;
                    CTNumData cTNumData = (CTNumData) r2.getNumRef().getNumCache().copy();
                    this.values = cTNumData;
                    this.formatCode = cTNumData.isSetFormatCode() ? cTNumData.getFormatCode() : null;
                }

                public String getFormatCode() {
                    return this.formatCode;
                }

                public void setFormatCode(String str) {
                    this.formatCode = str;
                }

                public int getPointCount() {
                    return (int) this.values.getPtCount().getVal();
                }

                public Double getPointAt(int i) {
                    return Double.valueOf(this.values.getPtArray(i).getV());
                }

                public String getDataRangeReference() {
                    return this.val$valuesDS.getNumRef().getF();
                }
            };
        }
        if (cTNumDataSource.getNumLit() != null) {
            return new XDDFNumericalDataSource<Double>(cTNumDataSource) {
                private String formatCode;
                final /* synthetic */ CTNumDataSource val$valuesDS;
                private final CTNumData values;

                public int getColIndex() {
                    return 0;
                }

                public String getDataRangeReference() {
                    return null;
                }

                public boolean isCellRange() {
                    return false;
                }

                public boolean isLiteral() {
                    return true;
                }

                public boolean isNumeric() {
                    return true;
                }

                public boolean isReference() {
                    return false;
                }

                {
                    this.val$valuesDS = r2;
                    CTNumData cTNumData = (CTNumData) r2.getNumLit().copy();
                    this.values = cTNumData;
                    this.formatCode = cTNumData.isSetFormatCode() ? cTNumData.getFormatCode() : null;
                }

                public String getFormatCode() {
                    return this.formatCode;
                }

                public void setFormatCode(String str) {
                    this.formatCode = str;
                }

                public int getPointCount() {
                    return (int) this.values.getPtCount().getVal();
                }

                public Double getPointAt(int i) {
                    return Double.valueOf(this.values.getPtArray(i).getV());
                }
            };
        }
        return null;
    }

    public static <T extends Number> XDDFNumericalDataSource<T> fromArray(T[] tArr) {
        return new LiteralNumericalArrayDataSource(tArr);
    }

    public static XDDFCategoryDataSource fromArray(String[] strArr) {
        return new LiteralStringArrayDataSource(strArr);
    }

    public static <T extends Number> XDDFNumericalDataSource<T> fromArray(T[] tArr, String str) {
        return new NumericalArrayDataSource(tArr, str);
    }

    public static XDDFCategoryDataSource fromArray(String[] strArr, String str) {
        return new StringArrayDataSource(strArr, str);
    }

    public static <T extends Number> XDDFNumericalDataSource<T> fromArray(T[] tArr, String str, int i) {
        return new NumericalArrayDataSource(tArr, str, i);
    }

    public static XDDFCategoryDataSource fromArray(String[] strArr, String str, int i) {
        return new StringArrayDataSource(strArr, str, i);
    }

    public static XDDFNumericalDataSource<Double> fromNumericCellRange(XSSFSheet xSSFSheet, CellRangeAddress cellRangeAddress) {
        return new NumericalCellRangeDataSource(xSSFSheet, cellRangeAddress);
    }

    public static XDDFCategoryDataSource fromStringCellRange(XSSFSheet xSSFSheet, CellRangeAddress cellRangeAddress) {
        return new StringCellRangeDataSource(xSSFSheet, cellRangeAddress);
    }

    private static abstract class AbstractArrayDataSource<T> implements XDDFDataSource<T> {
        private int col = 0;
        private final String dataRange;
        private final T[] elements;

        public boolean isCellRange() {
            return false;
        }

        public AbstractArrayDataSource(T[] tArr, String str) {
            this.elements = (Object[]) tArr.clone();
            this.dataRange = str;
        }

        public AbstractArrayDataSource(T[] tArr, String str, int i) {
            this.elements = (Object[]) tArr.clone();
            this.dataRange = str;
            this.col = i;
        }

        public int getPointCount() {
            return this.elements.length;
        }

        public T getPointAt(int i) {
            return this.elements[i];
        }

        public boolean isReference() {
            return this.dataRange != null;
        }

        public boolean isNumeric() {
            return Number.class.isAssignableFrom(this.elements.getClass().getComponentType());
        }

        public String getDataRangeReference() {
            String str = this.dataRange;
            if (str != null) {
                return str;
            }
            throw new UnsupportedOperationException("Literal data source can not be expressed by reference.");
        }

        public int getColIndex() {
            return this.col;
        }
    }

    private static class NumericalArrayDataSource<T extends Number> extends AbstractArrayDataSource<T> implements XDDFNumericalDataSource<T> {
        private String formatCode;

        public NumericalArrayDataSource(T[] tArr, String str) {
            super(tArr, str);
        }

        public NumericalArrayDataSource(T[] tArr, String str, int i) {
            super(tArr, str, i);
        }

        public String getFormatCode() {
            return this.formatCode;
        }

        public void setFormatCode(String str) {
            this.formatCode = str;
        }
    }

    private static class StringArrayDataSource extends AbstractArrayDataSource<String> implements XDDFCategoryDataSource {
        public String getFormatCode() {
            return null;
        }

        public StringArrayDataSource(String[] strArr, String str) {
            super(strArr, str);
        }

        public StringArrayDataSource(String[] strArr, String str, int i) {
            super(strArr, str, i);
        }
    }

    private static class LiteralNumericalArrayDataSource<T extends Number> extends NumericalArrayDataSource<T> {
        public boolean isLiteral() {
            return true;
        }

        public LiteralNumericalArrayDataSource(T[] tArr) {
            super(tArr, (String) null, 0);
        }
    }

    private static class LiteralStringArrayDataSource extends StringArrayDataSource {
        public boolean isLiteral() {
            return true;
        }

        public LiteralStringArrayDataSource(String[] strArr) {
            super(strArr, (String) null, 0);
        }
    }

    private static abstract class AbstractCellRangeDataSource<T> implements XDDFDataSource<T> {
        private final CellRangeAddress cellRangeAddress;
        private final XSSFFormulaEvaluator evaluator;
        private final int numOfCells;
        private final XSSFSheet sheet;

        public boolean isCellRange() {
            return true;
        }

        public boolean isReference() {
            return true;
        }

        protected AbstractCellRangeDataSource(XSSFSheet xSSFSheet, CellRangeAddress cellRangeAddress2) {
            this.sheet = xSSFSheet;
            CellRangeAddress copy = cellRangeAddress2.copy();
            this.cellRangeAddress = copy;
            this.numOfCells = copy.getNumberOfCells();
            this.evaluator = xSSFSheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
        }

        public int getPointCount() {
            return this.numOfCells;
        }

        public int getColIndex() {
            return this.cellRangeAddress.getFirstColumn();
        }

        public String getDataRangeReference() {
            return this.cellRangeAddress.formatAsString(this.sheet.getSheetName(), true);
        }

        /* access modifiers changed from: protected */
        public CellValue getCellValueAt(int i) {
            if (i < 0 || i >= this.numOfCells) {
                throw new IndexOutOfBoundsException("Index must be between 0 and " + (this.numOfCells - 1) + " (inclusive), given: " + i);
            }
            int firstRow = this.cellRangeAddress.getFirstRow();
            int firstColumn = this.cellRangeAddress.getFirstColumn();
            int lastColumn = (this.cellRangeAddress.getLastColumn() - firstColumn) + 1;
            int i2 = firstColumn + (i % lastColumn);
            XSSFRow row = this.sheet.getRow(firstRow + (i / lastColumn));
            if (row == null) {
                return null;
            }
            return this.evaluator.evaluate(row.getCell(i2));
        }
    }

    private static class NumericalCellRangeDataSource extends AbstractCellRangeDataSource<Double> implements XDDFNumericalDataSource<Double> {
        private String formatCode;

        public boolean isNumeric() {
            return true;
        }

        protected NumericalCellRangeDataSource(XSSFSheet xSSFSheet, CellRangeAddress cellRangeAddress) {
            super(xSSFSheet, cellRangeAddress);
        }

        public String getFormatCode() {
            return this.formatCode;
        }

        public void setFormatCode(String str) {
            this.formatCode = str;
        }

        public Double getPointAt(int i) {
            CellValue cellValueAt = getCellValueAt(i);
            if (cellValueAt == null || cellValueAt.getCellType() != CellType.NUMERIC) {
                return null;
            }
            return Double.valueOf(cellValueAt.getNumberValue());
        }
    }

    private static class StringCellRangeDataSource extends AbstractCellRangeDataSource<String> implements XDDFCategoryDataSource {
        public String getFormatCode() {
            return null;
        }

        public boolean isNumeric() {
            return false;
        }

        protected StringCellRangeDataSource(XSSFSheet xSSFSheet, CellRangeAddress cellRangeAddress) {
            super(xSSFSheet, cellRangeAddress);
        }

        public String getPointAt(int i) {
            CellValue cellValueAt = getCellValueAt(i);
            if (cellValueAt == null || cellValueAt.getCellType() != CellType.STRING) {
                return null;
            }
            return cellValueAt.getStringValue();
        }
    }
}
