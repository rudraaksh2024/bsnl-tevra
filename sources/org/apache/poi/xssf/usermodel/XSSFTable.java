package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.usermodel.TableStyleInfo;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.TableDocument;

public class XSSFTable extends POIXMLDocumentPart implements Table {
    private transient ConcurrentSkipListMap<String, Integer> columnMap;
    private transient String commonXPath;
    private CTTable ctTable;
    private transient CellReference endCellReference;
    private transient String name;
    private transient CellReference startCellReference;
    private transient String styleName;
    private transient List<XSSFTableColumn> tableColumns;
    private transient List<XSSFXmlColumnPr> xmlColumnPrs;

    public XSSFTable() {
        this.ctTable = CTTable.Factory.newInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r2 != null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XSSFTable(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
        /*
            r1 = this;
            r1.<init>((org.apache.poi.openxml4j.opc.PackagePart) r2)
            java.io.InputStream r2 = r2.getInputStream()
            r1.readFrom(r2)     // Catch:{ all -> 0x0010 }
            if (r2 == 0) goto L_0x000f
            r2.close()
        L_0x000f:
            return
        L_0x0010:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r0 = move-exception
            if (r2 == 0) goto L_0x001d
            r2.close()     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x001d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFTable.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public void readFrom(InputStream inputStream) throws IOException {
        try {
            this.ctTable = TableDocument.Factory.parse(inputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getTable();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public XSSFSheet getXSSFSheet() {
        return (XSSFSheet) getParent();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        updateHeaders();
        TableDocument newInstance = TableDocument.Factory.newInstance();
        newInstance.setTable(this.ctTable);
        newInstance.save(outputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        if (r0 != null) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r2.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()
            r2.writeTo(r0)     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x0010
            r0.close()
        L_0x0010:
            return
        L_0x0011:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r1 = move-exception
            if (r0 == 0) goto L_0x001e
            r0.close()     // Catch:{ all -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x001e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFTable.commit():void");
    }

    @Internal(since = "POI 3.15 beta 3")
    public CTTable getCTTable() {
        return this.ctTable;
    }

    public boolean mapsTo(long j) {
        for (XSSFXmlColumnPr mapId : getXmlColumnPrs()) {
            if (mapId.getMapId() == j) {
                return true;
            }
        }
        return false;
    }

    public String getCommonXpath() {
        if (this.commonXPath == null) {
            String[] strArr = new String[0];
            for (XSSFTableColumn next : getColumns()) {
                if (next.getXmlColumnPr() != null) {
                    String[] split = next.getXmlColumnPr().getXPath().split(PackagingURIHelper.FORWARD_SLASH_STRING);
                    if (strArr.length == 0) {
                        strArr = split;
                    } else {
                        int min = Math.min(strArr.length, split.length);
                        int i = 0;
                        while (true) {
                            if (i >= min) {
                                break;
                            } else if (!strArr[i].equals(split[i])) {
                                strArr = (String[]) Arrays.asList(strArr).subList(0, i).toArray(new String[0]);
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                }
            }
            strArr[0] = "";
            this.commonXPath = StringUtil.join((Object[]) strArr, PackagingURIHelper.FORWARD_SLASH_STRING);
        }
        return this.commonXPath;
    }

    public List<XSSFTableColumn> getColumns() {
        if (this.tableColumns == null) {
            ArrayList arrayList = new ArrayList();
            CTTableColumns tableColumns2 = this.ctTable.getTableColumns();
            if (tableColumns2 != null) {
                for (CTTableColumn xSSFTableColumn : tableColumns2.getTableColumnList()) {
                    arrayList.add(new XSSFTableColumn(this, xSSFTableColumn));
                }
            }
            this.tableColumns = Collections.unmodifiableList(arrayList);
        }
        return this.tableColumns;
    }

    private List<XSSFXmlColumnPr> getXmlColumnPrs() {
        if (this.xmlColumnPrs == null) {
            this.xmlColumnPrs = new ArrayList();
            for (XSSFTableColumn xmlColumnPr : getColumns()) {
                XSSFXmlColumnPr xmlColumnPr2 = xmlColumnPr.getXmlColumnPr();
                if (xmlColumnPr2 != null) {
                    this.xmlColumnPrs.add(xmlColumnPr2);
                }
            }
        }
        return this.xmlColumnPrs;
    }

    public XSSFTableColumn createColumn(String str) {
        return createColumn(str, getColumnCount());
    }

    public XSSFTableColumn createColumn(String str, int i) {
        int columnCount = getColumnCount();
        if (i < 0 || i > columnCount) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        CTTableColumns tableColumns2 = this.ctTable.getTableColumns();
        if (tableColumns2 == null) {
            tableColumns2 = this.ctTable.addNewTableColumns();
        }
        long j = 0;
        for (XSSFTableColumn next : getColumns()) {
            if (str == null || !str.equalsIgnoreCase(next.getName())) {
                j = Math.max(j, next.getId());
            } else {
                throw new IllegalArgumentException("Column '" + str + "' already exists. Column names must be unique per table.");
            }
        }
        long j2 = j + 1;
        CTTableColumn insertNewTableColumn = tableColumns2.insertNewTableColumn(i);
        tableColumns2.setCount((long) tableColumns2.sizeOfTableColumnArray());
        insertNewTableColumn.setId(j2);
        if (str != null) {
            insertNewTableColumn.setName(str);
        } else {
            insertNewTableColumn.setName("Column " + j2);
        }
        if (this.ctTable.getRef() != null) {
            CellReference startCellReference2 = getStartCellReference();
            CellReference endCellReference2 = getEndCellReference();
            setCellRef(new AreaReference(startCellReference2, new CellReference(endCellReference2.getRow(), (startCellReference2.getCol() + (columnCount + 1)) - 1), getXSSFSheet().getWorkbook().getSpreadsheetVersion()));
        }
        updateHeaders();
        return getColumns().get(i);
    }

    public void removeColumn(XSSFTableColumn xSSFTableColumn) {
        int indexOf = getColumns().indexOf(xSSFTableColumn);
        if (indexOf >= 0) {
            this.ctTable.getTableColumns().removeTableColumn(indexOf);
            updateReferences();
            updateHeaders();
        }
    }

    public void removeColumn(int i) {
        if (i < 0 || i > getColumnCount() - 1) {
            throw new IllegalArgumentException("Column index out of bounds");
        } else if (getColumnCount() != 1) {
            CTTableColumns tableColumns2 = this.ctTable.getTableColumns();
            tableColumns2.removeTableColumn(i);
            tableColumns2.setCount((long) tableColumns2.getTableColumnList().size());
            updateReferences();
            updateHeaders();
        } else {
            throw new IllegalArgumentException("Table must have at least one column");
        }
    }

    public String getName() {
        if (this.name == null && this.ctTable.getName() != null) {
            setName(this.ctTable.getName());
        }
        return this.name;
    }

    public void setName(String str) {
        if (str == null) {
            this.ctTable.unsetName();
            this.name = null;
            return;
        }
        this.ctTable.setName(str);
        this.name = str;
    }

    public String getStyleName() {
        if (this.styleName == null && this.ctTable.isSetTableStyleInfo()) {
            setStyleName(this.ctTable.getTableStyleInfo().getName());
        }
        return this.styleName;
    }

    public void setStyleName(String str) {
        if (str == null) {
            if (this.ctTable.isSetTableStyleInfo()) {
                this.ctTable.getTableStyleInfo().unsetName();
            }
            this.styleName = null;
            return;
        }
        if (!this.ctTable.isSetTableStyleInfo()) {
            this.ctTable.addNewTableStyleInfo();
        }
        this.ctTable.getTableStyleInfo().setName(str);
        this.styleName = str;
    }

    public String getDisplayName() {
        return this.ctTable.getDisplayName();
    }

    public void setDisplayName(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Display name must not be null or empty");
        }
        this.ctTable.setDisplayName(str);
    }

    public AreaReference getCellReferences() {
        return new AreaReference(getStartCellReference(), getEndCellReference(), SpreadsheetVersion.EXCEL2007);
    }

    public void setCellReferences(AreaReference areaReference) {
        setCellRef(areaReference);
    }

    /* access modifiers changed from: protected */
    @Internal
    public void setCellRef(AreaReference areaReference) {
        String formatAsString = areaReference.formatAsString();
        if (formatAsString.indexOf(33) != -1) {
            formatAsString = formatAsString.substring(formatAsString.indexOf(33) + 1);
        }
        this.ctTable.setRef(formatAsString);
        if (this.ctTable.isSetAutoFilter()) {
            int totalsRowCount = getTotalsRowCount();
            if (totalsRowCount != 0) {
                formatAsString = new AreaReference(new CellReference(areaReference.getFirstCell().getRow(), areaReference.getFirstCell().getCol()), new CellReference(areaReference.getLastCell().getRow() - totalsRowCount, areaReference.getLastCell().getCol()), SpreadsheetVersion.EXCEL2007).formatAsString();
            }
            this.ctTable.getAutoFilter().setRef(formatAsString);
        }
        updateReferences();
        updateHeaders();
    }

    public void setArea(AreaReference areaReference) {
        if (areaReference != null) {
            String sheetName = areaReference.getFirstCell().getSheetName();
            if (sheetName == null || sheetName.equals(getXSSFSheet().getSheetName())) {
                int row = (areaReference.getLastCell().getRow() - areaReference.getFirstCell().getRow()) + 1;
                int headerRowCount = getHeaderRowCount() + 1 + getTotalsRowCount();
                if (row >= headerRowCount) {
                    String formatAsString = areaReference.formatAsString();
                    if (formatAsString.indexOf(33) != -1) {
                        formatAsString = formatAsString.substring(formatAsString.indexOf(33) + 1);
                    }
                    this.ctTable.setRef(formatAsString);
                    if (this.ctTable.isSetAutoFilter()) {
                        this.ctTable.getAutoFilter().setRef(formatAsString);
                    }
                    updateReferences();
                    int columnCount = getColumnCount();
                    int col = (areaReference.getLastCell().getCol() - areaReference.getFirstCell().getCol()) + 1;
                    if (col > columnCount) {
                        while (columnCount < col) {
                            createColumn((String) null, columnCount);
                            columnCount++;
                        }
                    } else if (col < columnCount) {
                        while (columnCount > col) {
                            removeColumn(columnCount - 1);
                            columnCount--;
                        }
                    }
                    updateHeaders();
                    return;
                }
                throw new IllegalArgumentException("AreaReference needs at least " + headerRowCount + " rows, to cover at least one data row and all header rows and totals rows");
            }
            throw new IllegalArgumentException("The AreaReference must not reference a different sheet");
        }
        throw new IllegalArgumentException("AreaReference must not be null");
    }

    public AreaReference getArea() {
        if (this.ctTable.getRef() == null) {
            return null;
        }
        return new AreaReference(this.ctTable.getRef(), getXSSFSheet().getWorkbook().getSpreadsheetVersion());
    }

    public CellReference getStartCellReference() {
        if (this.startCellReference == null) {
            setCellReferences();
        }
        return this.startCellReference;
    }

    public CellReference getEndCellReference() {
        if (this.endCellReference == null) {
            setCellReferences();
        }
        return this.endCellReference;
    }

    private void setCellReferences() {
        String ref = this.ctTable.getRef();
        if (ref != null) {
            String[] split = ref.split(ParameterizedMessage.ERROR_MSG_SEPARATOR, 2);
            String str = split[0];
            String str2 = split.length == 2 ? split[1] : str;
            this.startCellReference = new CellReference(str);
            this.endCellReference = new CellReference(str2);
        }
    }

    public void updateReferences() {
        this.startCellReference = null;
        this.endCellReference = null;
    }

    public int getRowCount() {
        CellReference startCellReference2 = getStartCellReference();
        CellReference endCellReference2 = getEndCellReference();
        if (startCellReference2 == null || endCellReference2 == null) {
            return 0;
        }
        return (endCellReference2.getRow() - startCellReference2.getRow()) + 1;
    }

    public int getDataRowCount() {
        CellReference startCellReference2 = getStartCellReference();
        CellReference endCellReference2 = getEndCellReference();
        if (startCellReference2 == null || endCellReference2 == null) {
            return 0;
        }
        return (((endCellReference2.getRow() - startCellReference2.getRow()) + 1) - getHeaderRowCount()) - getTotalsRowCount();
    }

    public void setDataRowCount(int i) {
        CellReference cellReference;
        XSSFCell cell;
        if (i >= 1) {
            updateReferences();
            int dataRowCount = getDataRowCount();
            if (dataRowCount != i) {
                CellReference startCellReference2 = getStartCellReference();
                CellReference endCellReference2 = getEndCellReference();
                SpreadsheetVersion spreadsheetVersion = getXSSFSheet().getWorkbook().getSpreadsheetVersion();
                CellReference cellReference2 = new CellReference((startCellReference2.getRow() + ((getHeaderRowCount() + i) + getTotalsRowCount())) - 1, endCellReference2.getCol());
                AreaReference areaReference = new AreaReference(startCellReference2, cellReference2, spreadsheetVersion);
                if (i < dataRowCount) {
                    cellReference = new CellReference(areaReference.getLastCell().getRow() + 1, areaReference.getFirstCell().getCol());
                } else {
                    cellReference = new CellReference(endCellReference2.getRow() + 1, areaReference.getFirstCell().getCol());
                    endCellReference2 = cellReference2;
                }
                for (CellReference cellReference3 : new AreaReference(cellReference, endCellReference2, spreadsheetVersion).getAllReferencedCells()) {
                    XSSFRow row = getXSSFSheet().getRow(cellReference3.getRow());
                    if (!(row == null || (cell = row.getCell((int) cellReference3.getCol())) == null)) {
                        cell.setBlank();
                        cell.setCellStyle((CellStyle) null);
                    }
                }
                setCellRef(areaReference);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Table must have at least one data row");
    }

    public int getColumnCount() {
        CTTableColumns tableColumns2 = this.ctTable.getTableColumns();
        if (tableColumns2 == null) {
            return 0;
        }
        return (int) tableColumns2.getCount();
    }

    public void updateHeaders() {
        CTTableColumns tableColumns2;
        XSSFSheet xSSFSheet = (XSSFSheet) getParent();
        CellReference startCellReference2 = getStartCellReference();
        if (startCellReference2 != null) {
            int row = startCellReference2.getRow();
            int col = startCellReference2.getCol();
            XSSFRow row2 = xSSFSheet.getRow(row);
            DataFormatter dataFormatter = new DataFormatter();
            if (!(row2 == null || !row2.getCTRow().validate() || (tableColumns2 = getCTTable().getTableColumns()) == null)) {
                for (CTTableColumn next : tableColumns2.getTableColumnList()) {
                    XSSFCell cell = row2.getCell(col);
                    if (cell != null) {
                        next.setName(dataFormatter.formatCellValue(cell).replace("\n", "_x000a_").replace("\r", "_x000d_"));
                    }
                    col++;
                }
            }
            this.tableColumns = null;
            this.columnMap = null;
            this.xmlColumnPrs = null;
            this.commonXPath = null;
        }
    }

    public int findColumnIndex(String str) {
        if (str == null) {
            return -1;
        }
        if (this.columnMap == null) {
            this.columnMap = new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER);
            int i = 0;
            for (XSSFTableColumn name2 : getColumns()) {
                this.columnMap.put(name2.getName(), Integer.valueOf(i));
                i++;
            }
        }
        Integer num = this.columnMap.get(str.replace("''", "'").replace("'#", "#"));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public String getSheetName() {
        return getXSSFSheet().getSheetName();
    }

    public boolean isHasTotalsRow() {
        return this.ctTable.getTotalsRowShown();
    }

    public int getTotalsRowCount() {
        return (int) this.ctTable.getTotalsRowCount();
    }

    public int getHeaderRowCount() {
        return (int) this.ctTable.getHeaderRowCount();
    }

    public int getStartColIndex() {
        return getStartCellReference().getCol();
    }

    public int getStartRowIndex() {
        return getStartCellReference().getRow();
    }

    public int getEndColIndex() {
        return getEndCellReference().getCol();
    }

    public int getEndRowIndex() {
        return getEndCellReference().getRow();
    }

    public TableStyleInfo getStyle() {
        if (!this.ctTable.isSetTableStyleInfo()) {
            return null;
        }
        return new XSSFTableStyleInfo(((XSSFSheet) getParent()).getWorkbook().getStylesSource(), this.ctTable.getTableStyleInfo());
    }

    public boolean contains(CellReference cellReference) {
        if (cellReference != null && getSheetName().equals(cellReference.getSheetName()) && cellReference.getRow() >= getStartRowIndex() && cellReference.getRow() <= getEndRowIndex() && cellReference.getCol() >= getStartColIndex() && cellReference.getCol() <= getEndColIndex()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onTableDelete() {
        for (POIXMLDocumentPart.RelationPart documentPart : getRelationParts()) {
            removeRelation(documentPart.getDocumentPart(), true);
        }
    }
}
