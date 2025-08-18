package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.MasterType;
import org.apache.poi.util.Internal;

public class XDGFMaster {
    protected XDGFMasterContents _content;
    private MasterType _master;
    protected XDGFSheet _pageSheet;

    public XDGFMaster(MasterType masterType, XDGFMasterContents xDGFMasterContents, XDGFDocument xDGFDocument) {
        this._master = masterType;
        this._content = xDGFMasterContents;
        xDGFMasterContents.setMaster(this);
        if (masterType.isSetPageSheet()) {
            this._pageSheet = new XDGFPageSheet(masterType.getPageSheet(), xDGFDocument);
        }
    }

    /* access modifiers changed from: protected */
    @Internal
    public MasterType getXmlObject() {
        return this._master;
    }

    public String toString() {
        return "<Master ID=\"" + getID() + "\" " + this._content + ">";
    }

    public long getID() {
        return this._master.getID();
    }

    public String getName() {
        return this._master.getName();
    }

    public XDGFMasterContents getContent() {
        return this._content;
    }

    public XDGFSheet getPageSheet() {
        return this._pageSheet;
    }
}
