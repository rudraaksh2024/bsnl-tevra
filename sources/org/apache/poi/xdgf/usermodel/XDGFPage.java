package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.PageType;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.Internal;

public class XDGFPage {
    protected XDGFPageContents _content;
    private PageType _page;
    protected XDGFSheet _pageSheet;
    protected XDGFPages _pages;

    public XDGFPage(PageType pageType, XDGFPageContents xDGFPageContents, XDGFDocument xDGFDocument, XDGFPages xDGFPages) {
        this._page = pageType;
        this._content = xDGFPageContents;
        this._pages = xDGFPages;
        xDGFPageContents.setPage(this);
        if (pageType.isSetPageSheet()) {
            this._pageSheet = new XDGFPageSheet(pageType.getPageSheet(), xDGFDocument);
        }
    }

    /* access modifiers changed from: protected */
    @Internal
    public PageType getXmlObject() {
        return this._page;
    }

    public long getID() {
        return this._page.getID();
    }

    public String getName() {
        return this._page.getName();
    }

    public XDGFPageContents getContent() {
        return this._content;
    }

    public XDGFSheet getPageSheet() {
        return this._pageSheet;
    }

    public long getPageNumber() {
        return ((long) this._pages.getPageList().indexOf(this)) + 1;
    }

    public Dimension2DDouble getPageSize() {
        XDGFCell cell = this._pageSheet.getCell("PageWidth");
        XDGFCell cell2 = this._pageSheet.getCell("PageHeight");
        if (cell != null && cell2 != null) {
            return new Dimension2DDouble(Double.parseDouble(cell.getValue()), Double.parseDouble(cell2.getValue()));
        }
        throw new POIXMLException("Cannot determine page size");
    }

    public Point2D.Double getPageOffset() {
        XDGFCell cell = this._pageSheet.getCell("XRulerOrigin");
        XDGFCell cell2 = this._pageSheet.getCell("YRulerOrigin");
        double d = 0.0d;
        double parseDouble = cell != null ? Double.parseDouble(cell.getValue()) : 0.0d;
        if (cell2 != null) {
            d = Double.parseDouble(cell2.getValue());
        }
        return new Point2D.Double(parseDouble, d);
    }

    public Rectangle2D getBoundingBox() {
        Dimension2DDouble pageSize = getPageSize();
        Point2D.Double pageOffset = getPageOffset();
        return new Rectangle2D.Double(-pageOffset.getX(), -pageOffset.getY(), pageSize.getWidth(), pageSize.getHeight());
    }
}
