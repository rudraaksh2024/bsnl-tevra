package org.apache.poi.ss.formula;

import org.apache.logging.log4j.util.Chars;

public class SheetIdentifier {
    private final String _bookName;
    private final NameIdentifier _sheetIdentifier;

    public SheetIdentifier(String str, NameIdentifier nameIdentifier) {
        this._bookName = str;
        this._sheetIdentifier = nameIdentifier;
    }

    public String getBookName() {
        return this._bookName;
    }

    public NameIdentifier getSheetIdentifier() {
        return this._sheetIdentifier;
    }

    /* access modifiers changed from: protected */
    public void asFormulaString(StringBuilder sb) {
        if (this._bookName != null) {
            sb.append(" [").append(this._sheetIdentifier.getName()).append("]");
        }
        if (this._sheetIdentifier.isQuoted()) {
            sb.append(Chars.QUOTE).append(this._sheetIdentifier.getName()).append("'");
        } else {
            sb.append(this._sheetIdentifier.getName());
        }
    }

    public String asFormulaString() {
        StringBuilder sb = new StringBuilder(32);
        asFormulaString(sb);
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + " [" + asFormulaString() + "]";
    }
}
