package org.apache.poi.xddf.usermodel.text;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.XDDFExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;

public class XDDFParagraphProperties {
    private XDDFParagraphBulletProperties bullet;
    private CTTextParagraphProperties props;

    @Internal
    protected XDDFParagraphProperties(CTTextParagraphProperties cTTextParagraphProperties) {
        this.props = cTTextParagraphProperties;
        this.bullet = new XDDFParagraphBulletProperties(cTTextParagraphProperties);
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextParagraphProperties getXmlObject() {
        return this.props;
    }

    public XDDFParagraphBulletProperties getBulletProperties() {
        return this.bullet;
    }

    public int getLevel() {
        if (this.props.isSetLvl()) {
            return this.props.getLvl() + 1;
        }
        return 0;
    }

    public void setLevel(Integer num) {
        if (num == null) {
            if (this.props.isSetLvl()) {
                this.props.unsetLvl();
            }
        } else if (num.intValue() < 1 || 9 < num.intValue()) {
            throw new IllegalArgumentException("Minimum inclusive: 1. Maximum inclusive: 9.");
        } else {
            this.props.setLvl(num.intValue() - 1);
        }
    }

    public XDDFRunProperties addDefaultRunProperties() {
        if (!this.props.isSetDefRPr()) {
            this.props.addNewDefRPr();
        }
        return getDefaultRunProperties();
    }

    public XDDFRunProperties getDefaultRunProperties() {
        if (this.props.isSetDefRPr()) {
            return new XDDFRunProperties(this.props.getDefRPr());
        }
        return null;
    }

    public void setDefaultRunProperties(XDDFRunProperties xDDFRunProperties) {
        if (xDDFRunProperties != null) {
            this.props.setDefRPr(xDDFRunProperties.getXmlObject());
        } else if (this.props.isSetDefRPr()) {
            this.props.unsetDefRPr();
        }
    }

    public void setEastAsianLineBreak(Boolean bool) {
        if (bool != null) {
            this.props.setEaLnBrk(bool.booleanValue());
        } else if (this.props.isSetEaLnBrk()) {
            this.props.unsetEaLnBrk();
        }
    }

    public void setLatinLineBreak(Boolean bool) {
        if (bool != null) {
            this.props.setLatinLnBrk(bool.booleanValue());
        } else if (this.props.isSetLatinLnBrk()) {
            this.props.unsetLatinLnBrk();
        }
    }

    public void setHangingPunctuation(Boolean bool) {
        if (bool != null) {
            this.props.setHangingPunct(bool.booleanValue());
        } else if (this.props.isSetHangingPunct()) {
            this.props.unsetHangingPunct();
        }
    }

    public void setRightToLeft(Boolean bool) {
        if (bool != null) {
            this.props.setRtl(bool.booleanValue());
        } else if (this.props.isSetRtl()) {
            this.props.unsetRtl();
        }
    }

    public void setFontAlignment(FontAlignment fontAlignment) {
        if (fontAlignment != null) {
            this.props.setFontAlgn(fontAlignment.underlying);
        } else if (this.props.isSetFontAlgn()) {
            this.props.unsetFontAlgn();
        }
    }

    public void setTextAlignment(TextAlignment textAlignment) {
        if (textAlignment != null) {
            this.props.setAlgn(textAlignment.underlying);
        } else if (this.props.isSetAlgn()) {
            this.props.unsetAlgn();
        }
    }

    public void setDefaultTabSize(Double d) {
        if (d != null) {
            this.props.setDefTabSz(Integer.valueOf(Units.toEMU(d.doubleValue())));
        } else if (this.props.isSetDefTabSz()) {
            this.props.unsetDefTabSz();
        }
    }

    public void setIndentation(Double d) {
        if (d == null) {
            if (this.props.isSetIndent()) {
                this.props.unsetIndent();
            }
        } else if (d.doubleValue() < -4032.0d || 4032.0d < d.doubleValue()) {
            throw new IllegalArgumentException("Minimum inclusive = -4032. Maximum inclusive = 4032.");
        } else {
            this.props.setIndent(Units.toEMU(d.doubleValue()));
        }
    }

    public void setMarginLeft(Double d) {
        if (d == null) {
            if (this.props.isSetMarL()) {
                this.props.unsetMarL();
            }
        } else if (d.doubleValue() < 0.0d || 4032.0d < d.doubleValue()) {
            throw new IllegalArgumentException("Minimum inclusive = 0. Maximum inclusive = 4032.");
        } else {
            this.props.setMarL(Units.toEMU(d.doubleValue()));
        }
    }

    public void setMarginRight(Double d) {
        if (d == null) {
            if (this.props.isSetMarR()) {
                this.props.unsetMarR();
            }
        } else if (d.doubleValue() < 0.0d || 4032.0d < d.doubleValue()) {
            throw new IllegalArgumentException("Minimum inclusive = 0. Maximum inclusive = 4032.");
        } else {
            this.props.setMarR(Units.toEMU(d.doubleValue()));
        }
    }

    public void setLineSpacing(XDDFSpacing xDDFSpacing) {
        if (xDDFSpacing != null) {
            this.props.setLnSpc(xDDFSpacing.getXmlObject());
        } else if (this.props.isSetLnSpc()) {
            this.props.unsetLnSpc();
        }
    }

    public void setSpaceAfter(XDDFSpacing xDDFSpacing) {
        if (xDDFSpacing != null) {
            this.props.setSpcAft(xDDFSpacing.getXmlObject());
        } else if (this.props.isSetSpcAft()) {
            this.props.unsetSpcAft();
        }
    }

    public void setSpaceBefore(XDDFSpacing xDDFSpacing) {
        if (xDDFSpacing != null) {
            this.props.setSpcBef(xDDFSpacing.getXmlObject());
        } else if (this.props.isSetSpcBef()) {
            this.props.unsetSpcBef();
        }
    }

    public XDDFTabStop addTabStop() {
        if (!this.props.isSetTabLst()) {
            this.props.addNewTabLst();
        }
        return new XDDFTabStop(this.props.getTabLst().addNewTab());
    }

    public XDDFTabStop insertTabStop(int i) {
        if (!this.props.isSetTabLst()) {
            this.props.addNewTabLst();
        }
        return new XDDFTabStop(this.props.getTabLst().insertNewTab(i));
    }

    public void removeTabStop(int i) {
        if (this.props.isSetTabLst()) {
            this.props.getTabLst().removeTab(i);
        }
    }

    public XDDFTabStop getTabStop(int i) {
        if (this.props.isSetTabLst()) {
            return new XDDFTabStop(this.props.getTabLst().getTabArray(i));
        }
        return null;
    }

    public List<XDDFTabStop> getTabStops() {
        if (this.props.isSetTabLst()) {
            return Collections.unmodifiableList((List) this.props.getTabLst().getTabList().stream().map(new XDDFParagraphProperties$$ExternalSyntheticLambda0()).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    static /* synthetic */ XDDFTabStop lambda$getTabStops$0(CTTextTabStop cTTextTabStop) {
        return new XDDFTabStop(cTTextTabStop);
    }

    public int countTabStops() {
        if (this.props.isSetTabLst()) {
            return this.props.getTabLst().sizeOfTabArray();
        }
        return 0;
    }

    public XDDFExtensionList getExtensionList() {
        if (this.props.isSetExtLst()) {
            return new XDDFExtensionList(this.props.getExtLst());
        }
        return null;
    }

    public void setExtensionList(XDDFExtensionList xDDFExtensionList) {
        if (xDDFExtensionList != null) {
            this.props.setExtLst(xDDFExtensionList.getXmlObject());
        } else if (this.props.isSetExtLst()) {
            this.props.unsetExtLst();
        }
    }
}
