package org.apache.poi.xssf.model;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.FontScheme;
import org.apache.poi.ss.usermodel.TableStyle;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.CustomIndexedColorMap;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFTableStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorders;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFills;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFonts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyles;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument;

public class StylesTable extends POIXMLDocumentPart implements Styles {
    public static final int FIRST_CUSTOM_STYLE_ID = 165;
    private static final short FIRST_USER_DEFINED_NUMBER_FORMAT_ID = 164;
    private static final int MAXIMUM_STYLE_ID = SpreadsheetVersion.EXCEL2007.getMaxCellStyles();
    private int MAXIMUM_NUMBER_OF_DATA_FORMATS = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    private final List<XSSFCellBorder> borders = new ArrayList();
    private StyleSheetDocument doc;
    private final List<CTDxf> dxfs = new ArrayList();
    private final List<XSSFCellFill> fills = new ArrayList();
    private final List<XSSFFont> fonts = new ArrayList();
    private IndexedColorMap indexedColors = new DefaultIndexedColorMap();
    private final SortedMap<Short, String> numberFormats = new TreeMap();
    private final List<CTXf> styleXfs = new ArrayList();
    private final Map<String, TableStyle> tableStyles = new HashMap();
    private ThemesTable theme;
    private XSSFWorkbook workbook;
    private final List<CTXf> xfs = new ArrayList();

    public void setMaxNumberOfDataFormats(int i) {
        if (i >= getNumDataFormats()) {
            this.MAXIMUM_NUMBER_OF_DATA_FORMATS = i;
        } else if (i < 0) {
            throw new IllegalArgumentException("Maximum Number of Data Formats must be greater than or equal to 0");
        } else {
            throw new IllegalStateException("Cannot set the maximum number of data formats less than the current quantity. Data formats must be explicitly removed (via StylesTable.removeNumberFormat) before the limit can be decreased.");
        }
    }

    public int getMaxNumberOfDataFormats() {
        return this.MAXIMUM_NUMBER_OF_DATA_FORMATS;
    }

    public StylesTable() {
        StyleSheetDocument newInstance = StyleSheetDocument.Factory.newInstance();
        this.doc = newInstance;
        newInstance.addNewStyleSheet();
        initialize();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005d, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0060, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0055, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0056, code lost:
        if (r2 != null) goto L_0x0058;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StylesTable(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
        /*
            r1 = this;
            r1.<init>((org.apache.poi.openxml4j.opc.PackagePart) r2)
            java.util.TreeMap r0 = new java.util.TreeMap
            r0.<init>()
            r1.numberFormats = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.fonts = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.fills = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.borders = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.styleXfs = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.xfs = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.dxfs = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1.tableStyles = r0
            org.apache.poi.xssf.usermodel.DefaultIndexedColorMap r0 = new org.apache.poi.xssf.usermodel.DefaultIndexedColorMap
            r0.<init>()
            r1.indexedColors = r0
            r0 = 250(0xfa, float:3.5E-43)
            r1.MAXIMUM_NUMBER_OF_DATA_FORMATS = r0
            java.io.InputStream r2 = r2.getInputStream()
            r1.readFrom(r2)     // Catch:{ all -> 0x0053 }
            if (r2 == 0) goto L_0x0052
            r2.close()
        L_0x0052:
            return
        L_0x0053:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r0 = move-exception
            if (r2 == 0) goto L_0x0060
            r2.close()     // Catch:{ all -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x0060:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.StylesTable.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public StylesTable(InputStream inputStream) throws IOException {
        readFrom(inputStream);
    }

    public void setWorkbook(XSSFWorkbook xSSFWorkbook) {
        this.workbook = xSSFWorkbook;
    }

    public ThemesTable getTheme() {
        return this.theme;
    }

    public void setTheme(ThemesTable themesTable) {
        this.theme = themesTable;
        if (themesTable != null) {
            themesTable.setColorMap(getIndexedColors());
        }
        for (XSSFFont themesTable2 : this.fonts) {
            themesTable2.setThemesTable(themesTable);
        }
        for (XSSFCellBorder themesTable3 : this.borders) {
            themesTable3.setThemesTable(themesTable);
        }
    }

    public void ensureThemesTable() {
        XSSFWorkbook xSSFWorkbook;
        if (this.theme == null && (xSSFWorkbook = this.workbook) != null) {
            setTheme((ThemesTable) xSSFWorkbook.createRelationship(XSSFRelation.THEME, this.workbook.getXssfFactory()));
        }
    }

    public void readFrom(InputStream inputStream) throws IOException {
        try {
            StyleSheetDocument parse = StyleSheetDocument.Factory.parse(inputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.doc = parse;
            CTStylesheet styleSheet = parse.getStyleSheet();
            CustomIndexedColorMap fromColors = CustomIndexedColorMap.fromColors(styleSheet.getColors());
            if (fromColors != null) {
                this.indexedColors = fromColors;
            }
            CTNumFmts numFmts = styleSheet.getNumFmts();
            if (numFmts != null) {
                for (CTNumFmt cTNumFmt : numFmts.getNumFmtArray()) {
                    this.numberFormats.put(Short.valueOf((short) ((int) cTNumFmt.getNumFmtId())), cTNumFmt.getFormatCode());
                }
            }
            CTFonts fonts2 = styleSheet.getFonts();
            if (fonts2 != null) {
                int i = 0;
                for (CTFont xSSFFont : fonts2.getFontArray()) {
                    this.fonts.add(new XSSFFont(xSSFFont, i, this.indexedColors));
                    i++;
                }
            }
            CTFills fills2 = styleSheet.getFills();
            if (fills2 != null) {
                for (CTFill xSSFCellFill : fills2.getFillArray()) {
                    this.fills.add(new XSSFCellFill(xSSFCellFill, this.indexedColors));
                }
            }
            CTBorders borders2 = styleSheet.getBorders();
            if (borders2 != null) {
                for (CTBorder xSSFCellBorder : borders2.getBorderArray()) {
                    this.borders.add(new XSSFCellBorder(xSSFCellBorder, this.indexedColors));
                }
            }
            CTCellXfs cellXfs = styleSheet.getCellXfs();
            if (cellXfs != null) {
                this.xfs.addAll(Arrays.asList(cellXfs.getXfArray()));
            }
            CTCellStyleXfs cellStyleXfs = styleSheet.getCellStyleXfs();
            if (cellStyleXfs != null) {
                this.styleXfs.addAll(Arrays.asList(cellStyleXfs.getXfArray()));
            }
            CTDxfs dxfs2 = styleSheet.getDxfs();
            if (dxfs2 != null) {
                this.dxfs.addAll(Arrays.asList(dxfs2.getDxfArray()));
            }
            CTTableStyles tableStyles2 = styleSheet.getTableStyles();
            if (tableStyles2 != null && dxfs2 != null) {
                int i2 = 0;
                for (CTTableStyle cTTableStyle : tableStyles2.getTableStyleArray()) {
                    this.tableStyles.put(cTTableStyle.getName(), new XSSFTableStyle(i2, dxfs2, cTTableStyle, this.indexedColors));
                    i2++;
                }
            }
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public String getNumberFormatAt(short s) {
        return (String) this.numberFormats.get(Short.valueOf(s));
    }

    private short getNumberFormatId(String str) {
        for (Map.Entry next : this.numberFormats.entrySet()) {
            if (((String) next.getValue()).equals(str)) {
                return ((Short) next.getKey()).shortValue();
            }
        }
        throw new IllegalStateException("Number format not in style table: " + str);
    }

    public int putNumberFormat(String str) {
        if (this.numberFormats.containsValue(str)) {
            try {
                return getNumberFormatId(str);
            } catch (IllegalStateException unused) {
                throw new IllegalStateException("Found the format, but couldn't figure out where - should never happen!");
            }
        } else if (this.numberFormats.size() < this.MAXIMUM_NUMBER_OF_DATA_FORMATS) {
            short s = 164;
            if (!this.numberFormats.isEmpty()) {
                short shortValue = (short) (this.numberFormats.lastKey().shortValue() + 1);
                if (shortValue >= 0) {
                    s = (short) Math.max(shortValue, 164);
                } else {
                    throw new IllegalStateException("Cowardly avoiding creating a number format with a negative id. This is probably due to arithmetic overflow.");
                }
            }
            this.numberFormats.put(Short.valueOf(s), str);
            return s;
        } else {
            throw new IllegalStateException("The maximum number of Data Formats was exceeded. You can define up to " + this.MAXIMUM_NUMBER_OF_DATA_FORMATS + " formats in a .xlsx Workbook.");
        }
    }

    public void putNumberFormat(short s, String str) {
        this.numberFormats.put(Short.valueOf(s), str);
    }

    public boolean removeNumberFormat(short s) {
        boolean z = ((String) this.numberFormats.remove(Short.valueOf(s))) != null;
        if (z) {
            for (CTXf next : this.xfs) {
                if (next.isSetNumFmtId() && next.getNumFmtId() == ((long) s)) {
                    next.unsetApplyNumberFormat();
                    next.unsetNumFmtId();
                }
            }
        }
        return z;
    }

    public boolean removeNumberFormat(String str) {
        return removeNumberFormat(getNumberFormatId(str));
    }

    public XSSFFont getFontAt(int i) {
        return this.fonts.get(i);
    }

    public int putFont(XSSFFont xSSFFont, boolean z) {
        int indexOf = !z ? this.fonts.indexOf(xSSFFont) : -1;
        if (indexOf != -1) {
            return indexOf;
        }
        int size = this.fonts.size();
        this.fonts.add(xSSFFont);
        return size;
    }

    public int putFont(XSSFFont xSSFFont) {
        return putFont(xSSFFont, false);
    }

    public XSSFCellStyle getStyleAt(int i) {
        if (i < 0 || i >= this.xfs.size()) {
            return null;
        }
        return new XSSFCellStyle(i, this.xfs.get(i).getXfId() > 0 ? (int) this.xfs.get(i).getXfId() : 0, this, this.theme);
    }

    public int putStyle(XSSFCellStyle xSSFCellStyle) {
        CTXf coreXf = xSSFCellStyle.getCoreXf();
        if (!this.xfs.contains(coreXf)) {
            this.xfs.add(coreXf);
        }
        return this.xfs.indexOf(coreXf);
    }

    public XSSFCellBorder getBorderAt(int i) {
        return this.borders.get(i);
    }

    public int putBorder(XSSFCellBorder xSSFCellBorder) {
        int indexOf = this.borders.indexOf(xSSFCellBorder);
        if (indexOf != -1) {
            return indexOf;
        }
        this.borders.add(xSSFCellBorder);
        xSSFCellBorder.setThemesTable(this.theme);
        return this.borders.size() - 1;
    }

    public XSSFCellFill getFillAt(int i) {
        return this.fills.get(i);
    }

    public List<XSSFCellBorder> getBorders() {
        return Collections.unmodifiableList(this.borders);
    }

    public List<XSSFCellFill> getFills() {
        return Collections.unmodifiableList(this.fills);
    }

    public List<XSSFFont> getFonts() {
        return Collections.unmodifiableList(this.fonts);
    }

    public Map<Short, String> getNumberFormats() {
        return Collections.unmodifiableMap(this.numberFormats);
    }

    public int putFill(XSSFCellFill xSSFCellFill) {
        int indexOf = this.fills.indexOf(xSSFCellFill);
        if (indexOf != -1) {
            return indexOf;
        }
        this.fills.add(xSSFCellFill);
        return this.fills.size() - 1;
    }

    @Internal
    public CTXf getCellXfAt(int i) {
        return this.xfs.get(i);
    }

    @Internal
    public int putCellXf(CTXf cTXf) {
        this.xfs.add(cTXf);
        return this.xfs.size();
    }

    @Internal
    public void replaceCellXfAt(int i, CTXf cTXf) {
        this.xfs.set(i, cTXf);
    }

    @Internal
    public CTXf getCellStyleXfAt(int i) {
        try {
            return this.styleXfs.get(i);
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    @Internal
    public int putCellStyleXf(CTXf cTXf) {
        this.styleXfs.add(cTXf);
        return this.styleXfs.size();
    }

    /* access modifiers changed from: protected */
    @Internal
    public void replaceCellStyleXfAt(int i, CTXf cTXf) {
        this.styleXfs.set(i, cTXf);
    }

    public int getNumCellStyles() {
        return this.xfs.size();
    }

    public int getNumDataFormats() {
        return this.numberFormats.size();
    }

    /* access modifiers changed from: package-private */
    @Internal
    public int _getXfsSize() {
        return this.xfs.size();
    }

    @Internal
    public int _getStyleXfsSize() {
        return this.styleXfs.size();
    }

    @Internal
    public CTStylesheet getCTStylesheet() {
        return this.doc.getStyleSheet();
    }

    @Internal
    public int _getDXfsSize() {
        return this.dxfs.size();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        CTStylesheet styleSheet = this.doc.getStyleSheet();
        CTNumFmts newInstance = CTNumFmts.Factory.newInstance();
        newInstance.setCount((long) this.numberFormats.size());
        for (Map.Entry next : this.numberFormats.entrySet()) {
            CTNumFmt addNewNumFmt = newInstance.addNewNumFmt();
            addNewNumFmt.setNumFmtId((long) ((Short) next.getKey()).shortValue());
            addNewNumFmt.setFormatCode((String) next.getValue());
        }
        styleSheet.setNumFmts(newInstance);
        CTFonts fonts2 = styleSheet.getFonts();
        if (fonts2 == null) {
            fonts2 = CTFonts.Factory.newInstance();
        }
        fonts2.setCount((long) this.fonts.size());
        CTFont[] cTFontArr = new CTFont[this.fonts.size()];
        int i = 0;
        for (XSSFFont cTFont : this.fonts) {
            cTFontArr[i] = cTFont.getCTFont();
            i++;
        }
        fonts2.setFontArray(cTFontArr);
        styleSheet.setFonts(fonts2);
        CTFills fills2 = styleSheet.getFills();
        if (fills2 == null) {
            fills2 = CTFills.Factory.newInstance();
        }
        fills2.setCount((long) this.fills.size());
        CTFill[] cTFillArr = new CTFill[this.fills.size()];
        int i2 = 0;
        for (XSSFCellFill cTFill : this.fills) {
            cTFillArr[i2] = cTFill.getCTFill();
            i2++;
        }
        fills2.setFillArray(cTFillArr);
        styleSheet.setFills(fills2);
        CTBorders borders2 = styleSheet.getBorders();
        if (borders2 == null) {
            borders2 = CTBorders.Factory.newInstance();
        }
        borders2.setCount((long) this.borders.size());
        CTBorder[] cTBorderArr = new CTBorder[this.borders.size()];
        int i3 = 0;
        for (XSSFCellBorder cTBorder : this.borders) {
            cTBorderArr[i3] = cTBorder.getCTBorder();
            i3++;
        }
        borders2.setBorderArray(cTBorderArr);
        styleSheet.setBorders(borders2);
        if (!this.xfs.isEmpty()) {
            CTCellXfs cellXfs = styleSheet.getCellXfs();
            if (cellXfs == null) {
                cellXfs = CTCellXfs.Factory.newInstance();
            }
            cellXfs.setCount((long) this.xfs.size());
            cellXfs.setXfArray((CTXf[]) this.xfs.toArray(new CTXf[0]));
            styleSheet.setCellXfs(cellXfs);
        }
        if (!this.styleXfs.isEmpty()) {
            CTCellStyleXfs cellStyleXfs = styleSheet.getCellStyleXfs();
            if (cellStyleXfs == null) {
                cellStyleXfs = CTCellStyleXfs.Factory.newInstance();
            }
            cellStyleXfs.setCount((long) this.styleXfs.size());
            cellStyleXfs.setXfArray((CTXf[]) this.styleXfs.toArray(new CTXf[0]));
            styleSheet.setCellStyleXfs(cellStyleXfs);
        }
        if (!this.dxfs.isEmpty()) {
            CTDxfs dxfs2 = styleSheet.getDxfs();
            if (dxfs2 == null) {
                dxfs2 = CTDxfs.Factory.newInstance();
            }
            dxfs2.setCount((long) this.dxfs.size());
            dxfs2.setDxfArray((CTDxf[]) this.dxfs.toArray(new CTDxf[0]));
            styleSheet.setDxfs(dxfs2);
        }
        this.doc.save(outputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.StylesTable.commit():void");
    }

    private void initialize() {
        this.fonts.add(createDefaultFont());
        CTFill[] createDefaultFills = createDefaultFills();
        this.fills.add(new XSSFCellFill(createDefaultFills[0], this.indexedColors));
        this.fills.add(new XSSFCellFill(createDefaultFills[1], this.indexedColors));
        this.borders.add(new XSSFCellBorder(createDefaultBorder()));
        this.styleXfs.add(createDefaultXf());
        CTXf createDefaultXf = createDefaultXf();
        createDefaultXf.setXfId(0);
        this.xfs.add(createDefaultXf);
    }

    private static CTXf createDefaultXf() {
        CTXf newInstance = CTXf.Factory.newInstance();
        newInstance.setNumFmtId(0);
        newInstance.setFontId(0);
        newInstance.setFillId(0);
        newInstance.setBorderId(0);
        return newInstance;
    }

    private static CTBorder createDefaultBorder() {
        CTBorder newInstance = CTBorder.Factory.newInstance();
        newInstance.addNewBottom();
        newInstance.addNewTop();
        newInstance.addNewLeft();
        newInstance.addNewRight();
        newInstance.addNewDiagonal();
        return newInstance;
    }

    private static CTFill[] createDefaultFills() {
        CTFill[] cTFillArr = {CTFill.Factory.newInstance(), CTFill.Factory.newInstance()};
        cTFillArr[0].addNewPatternFill().setPatternType(STPatternType.NONE);
        cTFillArr[1].addNewPatternFill().setPatternType(STPatternType.DARK_GRAY);
        return cTFillArr;
    }

    private static XSSFFont createDefaultFont() {
        XSSFFont xSSFFont = new XSSFFont(CTFont.Factory.newInstance(), 0, (IndexedColorMap) null);
        xSSFFont.setFontHeightInPoints(11);
        xSSFFont.setColor(XSSFFont.DEFAULT_FONT_COLOR);
        xSSFFont.setFontName(XSSFFont.DEFAULT_FONT_NAME);
        xSSFFont.setFamily(FontFamily.SWISS);
        xSSFFont.setScheme(FontScheme.MINOR);
        return xSSFFont;
    }

    @Internal
    public CTDxf getDxfAt(int i) {
        return this.dxfs.get(i);
    }

    @Internal
    public int putDxf(CTDxf cTDxf) {
        this.dxfs.add(cTDxf);
        return this.dxfs.size();
    }

    public TableStyle getExplicitTableStyle(String str) {
        return this.tableStyles.get(str);
    }

    public Set<String> getExplicitTableStyleNames() {
        return this.tableStyles.keySet();
    }

    public TableStyle getTableStyle(String str) {
        if (str == null) {
            return null;
        }
        try {
            return XSSFBuiltinTableStyle.valueOf(str).getStyle();
        } catch (IllegalArgumentException unused) {
            return getExplicitTableStyle(str);
        }
    }

    public XSSFCellStyle createCellStyle() {
        int numCellStyles = getNumCellStyles();
        int i = MAXIMUM_STYLE_ID;
        if (numCellStyles <= i) {
            int size = this.styleXfs.size();
            CTXf newInstance = CTXf.Factory.newInstance();
            newInstance.setNumFmtId(0);
            newInstance.setFontId(0);
            newInstance.setFillId(0);
            newInstance.setBorderId(0);
            newInstance.setXfId(0);
            return new XSSFCellStyle(putCellXf(newInstance) - 1, size - 1, this, this.theme);
        }
        throw new IllegalStateException("The maximum number of Cell Styles was exceeded. You can define up to " + i + " style in a .xlsx Workbook");
    }

    public XSSFFont findFont(boolean z, short s, short s2, String str, boolean z2, boolean z3, short s3, byte b) {
        for (XSSFFont next : this.fonts) {
            if (next.getBold() == z && next.getColor() == s && next.getFontHeight() == s2 && next.getFontName().equals(str) && next.getItalic() == z2 && next.getStrikeout() == z3 && next.getTypeOffset() == s3 && next.getUnderline() == b) {
                return next;
            }
        }
        return null;
    }

    public XSSFFont findFont(boolean z, Color color, short s, String str, boolean z2, boolean z3, short s2, byte b) {
        for (XSSFFont next : this.fonts) {
            if (next.getBold() == z && next.getXSSFColor().equals(color) && next.getFontHeight() == s && next.getFontName().equals(str) && next.getItalic() == z2 && next.getStrikeout() == z3 && next.getTypeOffset() == s2 && next.getUnderline() == b) {
                return next;
            }
        }
        return null;
    }

    public IndexedColorMap getIndexedColors() {
        return this.indexedColors;
    }
}
