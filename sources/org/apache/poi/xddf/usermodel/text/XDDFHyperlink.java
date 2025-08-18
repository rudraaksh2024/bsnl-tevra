package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;

public class XDDFHyperlink {
    private CTHyperlink link;

    public XDDFHyperlink(String str) {
        this(CTHyperlink.Factory.newInstance());
        this.link.setId(str);
    }

    public XDDFHyperlink(String str, String str2) {
        this(str);
        this.link.setAction(str2);
    }

    @Internal
    protected XDDFHyperlink(CTHyperlink cTHyperlink) {
        this.link = cTHyperlink;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTHyperlink getXmlObject() {
        return this.link;
    }

    public String getAction() {
        if (this.link.isSetAction()) {
            return this.link.getAction();
        }
        return null;
    }

    public String getId() {
        if (this.link.isSetId()) {
            return this.link.getId();
        }
        return null;
    }

    public Boolean getEndSound() {
        if (this.link.isSetEndSnd()) {
            return Boolean.valueOf(this.link.getEndSnd());
        }
        return null;
    }

    public void setEndSound(Boolean bool) {
        if (bool != null) {
            this.link.setEndSnd(bool.booleanValue());
        } else if (this.link.isSetEndSnd()) {
            this.link.unsetEndSnd();
        }
    }

    public Boolean getHighlightClick() {
        if (this.link.isSetHighlightClick()) {
            return Boolean.valueOf(this.link.getHighlightClick());
        }
        return null;
    }

    public void setHighlightClick(Boolean bool) {
        if (bool != null) {
            this.link.setHighlightClick(bool.booleanValue());
        } else if (this.link.isSetHighlightClick()) {
            this.link.unsetHighlightClick();
        }
    }

    public Boolean getHistory() {
        if (this.link.isSetHistory()) {
            return Boolean.valueOf(this.link.getHistory());
        }
        return null;
    }

    public void setHistory(Boolean bool) {
        if (bool != null) {
            this.link.setHistory(bool.booleanValue());
        } else if (this.link.isSetHistory()) {
            this.link.unsetHistory();
        }
    }

    public String getInvalidURL() {
        if (this.link.isSetInvalidUrl()) {
            return this.link.getInvalidUrl();
        }
        return null;
    }

    public void setInvalidURL(String str) {
        if (str != null) {
            this.link.setInvalidUrl(str);
        } else if (this.link.isSetInvalidUrl()) {
            this.link.unsetInvalidUrl();
        }
    }

    public String getTargetFrame() {
        if (this.link.isSetTgtFrame()) {
            return this.link.getTgtFrame();
        }
        return null;
    }

    public void setTargetFrame(String str) {
        if (str != null) {
            this.link.setTgtFrame(str);
        } else if (this.link.isSetTgtFrame()) {
            this.link.unsetTgtFrame();
        }
    }

    public String getTooltip() {
        if (this.link.isSetTooltip()) {
            return this.link.getTooltip();
        }
        return null;
    }

    public void setTooltip(String str) {
        if (str != null) {
            this.link.setTooltip(str);
        } else if (this.link.isSetTooltip()) {
            this.link.unsetTooltip();
        }
    }

    public XDDFExtensionList getExtensionList() {
        if (this.link.isSetExtLst()) {
            return new XDDFExtensionList(this.link.getExtLst());
        }
        return null;
    }

    public void setExtensionList(XDDFExtensionList xDDFExtensionList) {
        if (xDDFExtensionList != null) {
            this.link.setExtLst(xDDFExtensionList.getXmlObject());
        } else if (this.link.isSetExtLst()) {
            this.link.unsetExtLst();
        }
    }
}
