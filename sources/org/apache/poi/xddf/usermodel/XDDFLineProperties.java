package org.apache.poi.xddf.usermodel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;

public class XDDFLineProperties {
    private CTLineProperties props;

    public XDDFLineProperties() {
        this(CTLineProperties.Factory.newInstance());
    }

    public XDDFLineProperties(XDDFFillProperties xDDFFillProperties) {
        this();
        setFillProperties(xDDFFillProperties);
    }

    @Internal
    public XDDFLineProperties(CTLineProperties cTLineProperties) {
        this.props = cTLineProperties;
    }

    @Internal
    public CTLineProperties getXmlObject() {
        return this.props;
    }

    public PenAlignment getPenAlignment() {
        if (this.props.isSetAlgn()) {
            return PenAlignment.valueOf(this.props.getAlgn());
        }
        return null;
    }

    public void setPenAlignment(PenAlignment penAlignment) {
        if (penAlignment != null) {
            this.props.setAlgn(penAlignment.underlying);
        } else if (this.props.isSetAlgn()) {
            this.props.unsetAlgn();
        }
    }

    public LineCap getLineCap() {
        if (this.props.isSetCap()) {
            return LineCap.valueOf(this.props.getCap());
        }
        return null;
    }

    public void setLineCap(LineCap lineCap) {
        if (lineCap != null) {
            this.props.setCap(lineCap.underlying);
        } else if (this.props.isSetCap()) {
            this.props.unsetCap();
        }
    }

    public CompoundLine getCompoundLine() {
        if (this.props.isSetCmpd()) {
            return CompoundLine.valueOf(this.props.getCmpd());
        }
        return null;
    }

    public void setCompoundLine(CompoundLine compoundLine) {
        if (compoundLine != null) {
            this.props.setCmpd(compoundLine.underlying);
        } else if (this.props.isSetCmpd()) {
            this.props.unsetCmpd();
        }
    }

    public XDDFDashStop addDashStop() {
        if (!this.props.isSetCustDash()) {
            this.props.addNewCustDash();
        }
        return new XDDFDashStop(this.props.getCustDash().addNewDs());
    }

    public XDDFDashStop insertDashStop(int i) {
        if (!this.props.isSetCustDash()) {
            this.props.addNewCustDash();
        }
        return new XDDFDashStop(this.props.getCustDash().insertNewDs(i));
    }

    public void removeDashStop(int i) {
        if (this.props.isSetCustDash()) {
            this.props.getCustDash().removeDs(i);
        }
    }

    public XDDFDashStop getDashStop(int i) {
        if (this.props.isSetCustDash()) {
            return new XDDFDashStop(this.props.getCustDash().getDsArray(i));
        }
        return null;
    }

    public List<XDDFDashStop> getDashStops() {
        if (this.props.isSetCustDash()) {
            return Collections.unmodifiableList((List) this.props.getCustDash().getDsList().stream().map(new XDDFLineProperties$$ExternalSyntheticLambda0()).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    static /* synthetic */ XDDFDashStop lambda$getDashStops$0(CTDashStop cTDashStop) {
        return new XDDFDashStop(cTDashStop);
    }

    public int countDashStops() {
        if (this.props.isSetCustDash()) {
            return this.props.getCustDash().sizeOfDsArray();
        }
        return 0;
    }

    public XDDFPresetLineDash getPresetDash() {
        if (this.props.isSetPrstDash()) {
            return new XDDFPresetLineDash(this.props.getPrstDash());
        }
        return null;
    }

    public void setPresetDash(XDDFPresetLineDash xDDFPresetLineDash) {
        if (xDDFPresetLineDash != null) {
            this.props.setPrstDash(xDDFPresetLineDash.getXmlObject());
        } else if (this.props.isSetPrstDash()) {
            this.props.unsetPrstDash();
        }
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

    public XDDFFillProperties getFillProperties() {
        if (this.props.isSetGradFill()) {
            return new XDDFGradientFillProperties(this.props.getGradFill());
        }
        if (this.props.isSetNoFill()) {
            return new XDDFNoFillProperties(this.props.getNoFill());
        }
        if (this.props.isSetPattFill()) {
            return new XDDFPatternFillProperties(this.props.getPattFill());
        }
        if (this.props.isSetSolidFill()) {
            return new XDDFSolidFillProperties(this.props.getSolidFill());
        }
        return null;
    }

    public void setFillProperties(XDDFFillProperties xDDFFillProperties) {
        if (this.props.isSetGradFill()) {
            this.props.unsetGradFill();
        }
        if (this.props.isSetNoFill()) {
            this.props.unsetNoFill();
        }
        if (this.props.isSetPattFill()) {
            this.props.unsetPattFill();
        }
        if (this.props.isSetSolidFill()) {
            this.props.unsetSolidFill();
        }
        if (xDDFFillProperties != null) {
            if (xDDFFillProperties instanceof XDDFGradientFillProperties) {
                this.props.setGradFill(((XDDFGradientFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFNoFillProperties) {
                this.props.setNoFill(((XDDFNoFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFPatternFillProperties) {
                this.props.setPattFill(((XDDFPatternFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFSolidFillProperties) {
                this.props.setSolidFill(((XDDFSolidFillProperties) xDDFFillProperties).getXmlObject());
            }
        }
    }

    public XDDFLineJoinProperties getLineJoinProperties() {
        if (this.props.isSetBevel()) {
            return new XDDFLineJoinBevelProperties(this.props.getBevel());
        }
        if (this.props.isSetMiter()) {
            return new XDDFLineJoinMiterProperties(this.props.getMiter());
        }
        if (this.props.isSetRound()) {
            return new XDDFLineJoinRoundProperties(this.props.getRound());
        }
        return null;
    }

    public void setLineJoinProperties(XDDFLineJoinProperties xDDFLineJoinProperties) {
        if (this.props.isSetBevel()) {
            this.props.unsetBevel();
        }
        if (this.props.isSetMiter()) {
            this.props.unsetMiter();
        }
        if (this.props.isSetRound()) {
            this.props.unsetRound();
        }
        if (xDDFLineJoinProperties != null) {
            if (xDDFLineJoinProperties instanceof XDDFLineJoinBevelProperties) {
                this.props.setBevel(((XDDFLineJoinBevelProperties) xDDFLineJoinProperties).getXmlObject());
            } else if (xDDFLineJoinProperties instanceof XDDFLineJoinMiterProperties) {
                this.props.setMiter(((XDDFLineJoinMiterProperties) xDDFLineJoinProperties).getXmlObject());
            } else if (xDDFLineJoinProperties instanceof XDDFLineJoinRoundProperties) {
                this.props.setRound(((XDDFLineJoinRoundProperties) xDDFLineJoinProperties).getXmlObject());
            }
        }
    }

    public XDDFLineEndProperties getHeadEnd() {
        if (this.props.isSetHeadEnd()) {
            return new XDDFLineEndProperties(this.props.getHeadEnd());
        }
        return null;
    }

    public void setHeadEnd(XDDFLineEndProperties xDDFLineEndProperties) {
        if (xDDFLineEndProperties != null) {
            this.props.setHeadEnd(xDDFLineEndProperties.getXmlObject());
        } else if (this.props.isSetHeadEnd()) {
            this.props.unsetHeadEnd();
        }
    }

    public XDDFLineEndProperties getTailEnd() {
        if (this.props.isSetTailEnd()) {
            return new XDDFLineEndProperties(this.props.getTailEnd());
        }
        return null;
    }

    public void setTailEnd(XDDFLineEndProperties xDDFLineEndProperties) {
        if (xDDFLineEndProperties != null) {
            this.props.setTailEnd(xDDFLineEndProperties.getXmlObject());
        } else if (this.props.isSetTailEnd()) {
            this.props.unsetTailEnd();
        }
    }

    public Double getWidth() {
        if (this.props.isSetW()) {
            return Double.valueOf(Units.toPoints((long) this.props.getW()));
        }
        return null;
    }

    public void setWidth(Double d) {
        if (d != null) {
            this.props.setW(Units.toEMU(d.doubleValue()));
        } else if (this.props.isSetW()) {
            this.props.unsetW();
        }
    }
}
