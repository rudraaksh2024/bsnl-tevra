package org.apache.poi.ss.formula;

import org.apache.logging.log4j.util.Chars;
import org.apache.xmlbeans.impl.common.NameUtil;

public class SheetRangeIdentifier extends SheetIdentifier {
    private final NameIdentifier _lastSheetIdentifier;

    public SheetRangeIdentifier(String str, NameIdentifier nameIdentifier, NameIdentifier nameIdentifier2) {
        super(str, nameIdentifier);
        this._lastSheetIdentifier = nameIdentifier2;
    }

    public NameIdentifier getFirstSheetIdentifier() {
        return super.getSheetIdentifier();
    }

    public NameIdentifier getLastSheetIdentifier() {
        return this._lastSheetIdentifier;
    }

    /* access modifiers changed from: protected */
    public void asFormulaString(StringBuilder sb) {
        super.asFormulaString(sb);
        sb.append(NameUtil.COLON);
        if (this._lastSheetIdentifier.isQuoted()) {
            sb.append(Chars.QUOTE).append(this._lastSheetIdentifier.getName()).append("'");
        } else {
            sb.append(this._lastSheetIdentifier.getName());
        }
    }
}
