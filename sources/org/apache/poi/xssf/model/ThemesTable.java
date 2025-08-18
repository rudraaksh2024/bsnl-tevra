package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;

public class ThemesTable extends POIXMLDocumentPart implements Themes {
    private IndexedColorMap colorMap;
    private ThemeDocument theme;

    public enum ThemeElement {
        LT1(0, "Lt1"),
        DK1(1, "Dk1"),
        LT2(2, "Lt2"),
        DK2(3, "Dk2"),
        ACCENT1(4, "Accent1"),
        ACCENT2(5, "Accent2"),
        ACCENT3(6, "Accent3"),
        ACCENT4(7, "Accent4"),
        ACCENT5(8, "Accent5"),
        ACCENT6(9, "Accent6"),
        HLINK(10, "Hlink"),
        FOLHLINK(11, "FolHlink"),
        UNKNOWN(-1, (int) null);
        
        public final int idx;
        public final String name;

        public static ThemeElement byId(int i) {
            if (i >= values().length || i < 0) {
                return UNKNOWN;
            }
            return values()[i];
        }

        private ThemeElement(int i, String str) {
            this.idx = i;
            this.name = str;
        }
    }

    public ThemesTable() {
        ThemeDocument newInstance = ThemeDocument.Factory.newInstance();
        this.theme = newInstance;
        newInstance.addNewTheme().addNewThemeElements();
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
    public ThemesTable(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.ThemesTable.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public ThemesTable(InputStream inputStream) throws IOException {
        readFrom(inputStream);
    }

    public ThemesTable(ThemeDocument themeDocument) {
        this.theme = themeDocument;
    }

    public void readFrom(InputStream inputStream) throws IOException {
        try {
            this.theme = ThemeDocument.Factory.parse(inputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public void setColorMap(IndexedColorMap indexedColorMap) {
        this.colorMap = indexedColorMap;
    }

    public XSSFColor getThemeColor(int i) {
        CTColor cTColor;
        byte[] bArr;
        CTColorScheme clrScheme = this.theme.getTheme().getThemeElements().getClrScheme();
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement[ThemeElement.byId(i).ordinal()]) {
            case 1:
                cTColor = clrScheme.getLt1();
                break;
            case 2:
                cTColor = clrScheme.getDk1();
                break;
            case 3:
                cTColor = clrScheme.getLt2();
                break;
            case 4:
                cTColor = clrScheme.getDk2();
                break;
            case 5:
                cTColor = clrScheme.getAccent1();
                break;
            case 6:
                cTColor = clrScheme.getAccent2();
                break;
            case 7:
                cTColor = clrScheme.getAccent3();
                break;
            case 8:
                cTColor = clrScheme.getAccent4();
                break;
            case 9:
                cTColor = clrScheme.getAccent5();
                break;
            case 10:
                cTColor = clrScheme.getAccent6();
                break;
            case 11:
                cTColor = clrScheme.getHlink();
                break;
            case 12:
                cTColor = clrScheme.getFolHlink();
                break;
            default:
                return null;
        }
        if (cTColor.isSetSrgbClr()) {
            bArr = cTColor.getSrgbClr().getVal();
        } else if (!cTColor.isSetSysClr()) {
            return null;
        } else {
            bArr = cTColor.getSysClr().getLastClr();
        }
        return new XSSFColor(bArr, this.colorMap);
    }

    /* renamed from: org.apache.poi.xssf.model.ThemesTable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.xssf.model.ThemesTable$ThemeElement[] r0 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement = r0
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.LT1     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.DK1     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.LT2     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.DK2     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.ACCENT1     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.ACCENT2     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.ACCENT3     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.ACCENT4     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.ACCENT5     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.ACCENT6     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.HLINK     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$model$ThemesTable$ThemeElement     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.apache.poi.xssf.model.ThemesTable$ThemeElement r1 = org.apache.poi.xssf.model.ThemesTable.ThemeElement.FOLHLINK     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.ThemesTable.AnonymousClass1.<clinit>():void");
        }
    }

    public void inheritFromThemeAsRequired(XSSFColor xSSFColor) {
        if (xSSFColor != null && xSSFColor.getCTColor().isSetTheme()) {
            xSSFColor.getCTColor().setRgb(getThemeColor(xSSFColor.getTheme()).getCTColor().getRgb());
        }
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.theme.save(outputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.ThemesTable.commit():void");
    }
}
