package org.apache.poi.xssf.usermodel.extensions;

import java.util.Objects;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STBorderStyle;

public class XSSFCellBorder {
    private final IndexedColorMap _indexedColorMap;
    private ThemesTable _theme;
    private final CTBorder border;

    public enum BorderSide {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT,
        DIAGONAL,
        VERTICAL,
        HORIZONTAL
    }

    public XSSFCellBorder(CTBorder cTBorder, ThemesTable themesTable, IndexedColorMap indexedColorMap) {
        this.border = cTBorder;
        this._indexedColorMap = indexedColorMap;
        this._theme = themesTable;
    }

    public XSSFCellBorder(CTBorder cTBorder) {
        this(cTBorder, (ThemesTable) null, (IndexedColorMap) null);
    }

    public XSSFCellBorder(CTBorder cTBorder, IndexedColorMap indexedColorMap) {
        this(cTBorder, (ThemesTable) null, indexedColorMap);
    }

    public XSSFCellBorder() {
        this(CTBorder.Factory.newInstance(), (ThemesTable) null, (IndexedColorMap) null);
    }

    public void setThemesTable(ThemesTable themesTable) {
        this._theme = themesTable;
    }

    @Internal
    public CTBorder getCTBorder() {
        return this.border;
    }

    public BorderStyle getBorderStyle(BorderSide borderSide) {
        CTBorderPr border2 = getBorder(borderSide);
        return BorderStyle.values()[(border2 == null ? STBorderStyle.NONE : border2.getStyle()).intValue() - 1];
    }

    public void setBorderStyle(BorderSide borderSide, BorderStyle borderStyle) {
        getBorder(borderSide, true).setStyle(STBorderStyle.Enum.forInt(borderStyle.ordinal() + 1));
    }

    public XSSFColor getBorderColor(BorderSide borderSide) {
        CTBorderPr border2 = getBorder(borderSide);
        if (border2 == null || !border2.isSetColor()) {
            return null;
        }
        XSSFColor from = XSSFColor.from(border2.getColor(), this._indexedColorMap);
        ThemesTable themesTable = this._theme;
        if (themesTable != null) {
            themesTable.inheritFromThemeAsRequired(from);
        }
        return from;
    }

    public void setBorderColor(BorderSide borderSide, XSSFColor xSSFColor) {
        CTBorderPr border2 = getBorder(borderSide, true);
        if (xSSFColor == null) {
            border2.unsetColor();
        } else {
            border2.setColor(xSSFColor.getCTColor());
        }
    }

    private CTBorderPr getBorder(BorderSide borderSide) {
        return getBorder(borderSide, false);
    }

    /* renamed from: org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide[] r0 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide = r0
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.TOP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.RIGHT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.LEFT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.DIAGONAL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder$BorderSide r1 = org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.AnonymousClass1.<clinit>():void");
        }
    }

    private CTBorderPr getBorder(BorderSide borderSide, boolean z) {
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$xssf$usermodel$extensions$XSSFCellBorder$BorderSide[borderSide.ordinal()]) {
            case 1:
                CTBorderPr top = this.border.getTop();
                return (!z || top != null) ? top : this.border.addNewTop();
            case 2:
                CTBorderPr right = this.border.getRight();
                if (!z || right != null) {
                    return right;
                }
                return this.border.addNewRight();
            case 3:
                CTBorderPr bottom = this.border.getBottom();
                if (!z || bottom != null) {
                    return bottom;
                }
                return this.border.addNewBottom();
            case 4:
                CTBorderPr left = this.border.getLeft();
                if (!z || left != null) {
                    return left;
                }
                return this.border.addNewLeft();
            case 5:
                CTBorderPr diagonal = this.border.getDiagonal();
                if (!z || diagonal != null) {
                    return diagonal;
                }
                return this.border.addNewDiagonal();
            case 6:
                CTBorderPr vertical = this.border.getVertical();
                if (!z || vertical != null) {
                    return vertical;
                }
                return this.border.addNewVertical();
            case 7:
                CTBorderPr horizontal = this.border.getHorizontal();
                if (!z || horizontal != null) {
                    return horizontal;
                }
                return this.border.addNewHorizontal();
            default:
                throw new IllegalArgumentException("No suitable side specified for the border, had " + borderSide);
        }
    }

    public int hashCode() {
        return this.border.toString().hashCode();
    }

    public boolean equals(Object obj) {
        boolean z;
        if (!(obj instanceof XSSFCellBorder)) {
            return false;
        }
        XSSFCellBorder xSSFCellBorder = (XSSFCellBorder) obj;
        BorderSide[] values = BorderSide.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            }
            BorderSide borderSide = values[i];
            if (!Objects.equals(getBorderColor(borderSide), xSSFCellBorder.getBorderColor(borderSide)) || !Objects.equals(getBorderStyle(borderSide), xSSFCellBorder.getBorderStyle(borderSide))) {
                z = false;
            } else {
                i++;
            }
        }
        if (!z || this.border.isSetDiagonalUp() != xSSFCellBorder.border.isSetDiagonalUp() || this.border.isSetDiagonalDown() != xSSFCellBorder.border.isSetDiagonalDown() || this.border.isSetOutline() != xSSFCellBorder.border.isSetOutline()) {
            return false;
        }
        if (this.border.isSetDiagonalUp() && this.border.getDiagonalUp() != xSSFCellBorder.border.getDiagonalUp()) {
            return false;
        }
        if (this.border.isSetDiagonalDown() && this.border.getDiagonalDown() != xSSFCellBorder.border.getDiagonalDown()) {
            return false;
        }
        if (!this.border.isSetOutline() || this.border.getOutline() == xSSFCellBorder.border.getOutline()) {
            return true;
        }
        return false;
    }
}
