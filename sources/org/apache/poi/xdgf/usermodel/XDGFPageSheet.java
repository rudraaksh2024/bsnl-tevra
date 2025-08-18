package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.PageSheetType;

public class XDGFPageSheet extends XDGFSheet {
    PageSheetType _pageSheet;

    public XDGFPageSheet(PageSheetType pageSheetType, XDGFDocument xDGFDocument) {
        super(pageSheetType, xDGFDocument);
        this._pageSheet = pageSheetType;
    }

    /* access modifiers changed from: package-private */
    public PageSheetType getXmlObject() {
        return this._pageSheet;
    }
}
