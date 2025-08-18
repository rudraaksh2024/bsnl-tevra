package org.apache.poi.xddf.usermodel.text;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class XDDFTextBody {
    private CTTextBody _body;
    private TextContainer _parent;

    public XDDFTextBody(TextContainer textContainer) {
        this(textContainer, CTTextBody.Factory.newInstance());
    }

    @Internal
    public XDDFTextBody(TextContainer textContainer, CTTextBody cTTextBody) {
        this._parent = textContainer;
        this._body = cTTextBody;
    }

    @Internal
    public CTTextBody getXmlObject() {
        return this._body;
    }

    public TextContainer getParentShape() {
        return this._parent;
    }

    public XDDFTextParagraph initialize() {
        this._body.addNewLstStyle();
        this._body.addNewBodyPr();
        XDDFBodyProperties bodyProperties = getBodyProperties();
        bodyProperties.setAnchoring(AnchorType.TOP);
        bodyProperties.setRightToLeft(false);
        XDDFTextParagraph addNewParagraph = addNewParagraph();
        addNewParagraph.setTextAlignment(TextAlignment.LEFT);
        addNewParagraph.appendRegularRun("");
        XDDFRunProperties addAfterLastRunProperties = addNewParagraph.addAfterLastRunProperties();
        addAfterLastRunProperties.setLanguage(Locale.US);
        addAfterLastRunProperties.setFontSize(Double.valueOf(11.0d));
        return addNewParagraph;
    }

    public void setText(String str) {
        if (this._body.sizeOfPArray() > 0) {
            for (int sizeOfPArray = this._body.sizeOfPArray() - 1; sizeOfPArray > 0; sizeOfPArray--) {
                this._body.removeP(sizeOfPArray);
            }
            getParagraph(0).setText(str);
            return;
        }
        initialize().setText(str);
    }

    public XDDFTextParagraph addNewParagraph() {
        return new XDDFTextParagraph(this._body.addNewP(), this);
    }

    public XDDFTextParagraph insertNewParagraph(int i) {
        return new XDDFTextParagraph(this._body.insertNewP(i), this);
    }

    public void removeParagraph(int i) {
        this._body.removeP(i);
    }

    public XDDFTextParagraph getParagraph(int i) {
        return new XDDFTextParagraph(this._body.getPArray(i), this);
    }

    public List<XDDFTextParagraph> getParagraphs() {
        return Collections.unmodifiableList((List) this._body.getPList().stream().map(new XDDFTextBody$$ExternalSyntheticLambda0(this)).collect(Collectors.toList()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getParagraphs$0$org-apache-poi-xddf-usermodel-text-XDDFTextBody  reason: not valid java name */
    public /* synthetic */ XDDFTextParagraph m2298lambda$getParagraphs$0$orgapachepoixddfusermodeltextXDDFTextBody(CTTextParagraph cTTextParagraph) {
        return new XDDFTextParagraph(cTTextParagraph, this);
    }

    public XDDFBodyProperties getBodyProperties() {
        return new XDDFBodyProperties(this._body.getBodyPr());
    }

    public void setBodyProperties(XDDFBodyProperties xDDFBodyProperties) {
        if (xDDFBodyProperties == null) {
            this._body.addNewBodyPr();
        } else {
            this._body.setBodyPr(xDDFBodyProperties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getDefaultProperties() {
        if (!this._body.isSetLstStyle() || !this._body.getLstStyle().isSetDefPPr()) {
            return null;
        }
        return new XDDFParagraphProperties(this._body.getLstStyle().getDefPPr());
    }

    public void setDefaultProperties(XDDFParagraphProperties xDDFParagraphProperties) {
        if (xDDFParagraphProperties != null) {
            boolean isSetLstStyle = this._body.isSetLstStyle();
            CTTextBody cTTextBody = this._body;
            (isSetLstStyle ? cTTextBody.getLstStyle() : cTTextBody.addNewLstStyle()).setDefPPr(xDDFParagraphProperties.getXmlObject());
        } else if (this._body.isSetLstStyle()) {
            CTTextListStyle lstStyle = this._body.getLstStyle();
            if (lstStyle.isSetDefPPr()) {
                lstStyle.unsetDefPPr();
            }
        }
    }

    public XDDFParagraphProperties getLevel1Properties() {
        if (!this._body.isSetLstStyle() || !this._body.getLstStyle().isSetLvl1PPr()) {
            return null;
        }
        return new XDDFParagraphProperties(this._body.getLstStyle().getLvl1PPr());
    }

    public void setLevel1Properties(XDDFParagraphProperties xDDFParagraphProperties) {
        if (xDDFParagraphProperties != null) {
            boolean isSetLstStyle = this._body.isSetLstStyle();
            CTTextBody cTTextBody = this._body;
            (isSetLstStyle ? cTTextBody.getLstStyle() : cTTextBody.addNewLstStyle()).setLvl1PPr(xDDFParagraphProperties.getXmlObject());
        } else if (this._body.isSetLstStyle()) {
            CTTextListStyle lstStyle = this._body.getLstStyle();
            if (lstStyle.isSetLvl1PPr()) {
                lstStyle.unsetLvl1PPr();
            }
        }
    }

    public XDDFParagraphProperties getLevel2Properties() {
        if (!this._body.isSetLstStyle() || !this._body.getLstStyle().isSetLvl2PPr()) {
            return null;
        }
        return new XDDFParagraphProperties(this._body.getLstStyle().getLvl2PPr());
    }

    public void setLevel2Properties(XDDFParagraphProperties xDDFParagraphProperties) {
        if (xDDFParagraphProperties != null) {
            boolean isSetLstStyle = this._body.isSetLstStyle();
            CTTextBody cTTextBody = this._body;
            (isSetLstStyle ? cTTextBody.getLstStyle() : cTTextBody.addNewLstStyle()).setLvl2PPr(xDDFParagraphProperties.getXmlObject());
        } else if (this._body.isSetLstStyle()) {
            CTTextListStyle lstStyle = this._body.getLstStyle();
            if (lstStyle.isSetLvl2PPr()) {
                lstStyle.unsetLvl2PPr();
            }
        }
    }

    public XDDFParagraphProperties getLevel3Properties() {
        if (!this._body.isSetLstStyle() || !this._body.getLstStyle().isSetLvl3PPr()) {
            return null;
        }
        return new XDDFParagraphProperties(this._body.getLstStyle().getLvl3PPr());
    }

    public void setLevel3Properties(XDDFParagraphProperties xDDFParagraphProperties) {
        if (xDDFParagraphProperties != null) {
            boolean isSetLstStyle = this._body.isSetLstStyle();
            CTTextBody cTTextBody = this._body;
            (isSetLstStyle ? cTTextBody.getLstStyle() : cTTextBody.addNewLstStyle()).setLvl3PPr(xDDFParagraphProperties.getXmlObject());
        } else if (this._body.isSetLstStyle()) {
            CTTextListStyle lstStyle = this._body.getLstStyle();
            if (lstStyle.isSetLvl3PPr()) {
                lstStyle.unsetLvl3PPr();
            }
        }
    }

    public XDDFParagraphProperties getLevel4Properties() {
        if (!this._body.isSetLstStyle() || !this._body.getLstStyle().isSetLvl4PPr()) {
            return null;
        }
        return new XDDFParagraphProperties(this._body.getLstStyle().getLvl4PPr());
    }

    public void setLevel4Properties(XDDFParagraphProperties xDDFParagraphProperties) {
        if (xDDFParagraphProperties != null) {
            boolean isSetLstStyle = this._body.isSetLstStyle();
            CTTextBody cTTextBody = this._body;
            (isSetLstStyle ? cTTextBody.getLstStyle() : cTTextBody.addNewLstStyle()).setLvl4PPr(xDDFParagraphProperties.getXmlObject());
        } else if (this._body.isSetLstStyle()) {
            CTTextListStyle lstStyle = this._body.getLstStyle();
            if (lstStyle.isSetLvl4PPr()) {
                lstStyle.unsetLvl4PPr();
            }
        }
    }

    public XDDFParagraphProperties getLevel5Properties() {
        if (!this._body.isSetLstStyle() || !this._body.getLstStyle().isSetLvl5PPr()) {
            return null;
        }
        return new XDDFParagraphProperties(this._body.getLstStyle().getLvl5PPr());
    }

    public void setLevel5Properties(XDDFParagraphProperties xDDFParagraphProperties) {
        if (xDDFParagraphProperties != null) {
            boolean isSetLstStyle = this._body.isSetLstStyle();
            CTTextBody cTTextBody = this._body;
            (isSetLstStyle ? cTTextBody.getLstStyle() : cTTextBody.addNewLstStyle()).setLvl5PPr(xDDFParagraphProperties.getXmlObject());
        } else if (this._body.isSetLstStyle()) {
            CTTextListStyle lstStyle = this._body.getLstStyle();
            if (lstStyle.isSetLvl5PPr()) {
                lstStyle.unsetLvl5PPr();
            }
        }
    }

    public XDDFParagraphProperties getLevel6Properties() {
        if (!this._body.isSetLstStyle() || !this._body.getLstStyle().isSetLvl6PPr()) {
            return null;
        }
        return new XDDFParagraphProperties(this._body.getLstStyle().getLvl6PPr());
    }

    public void setLevel6Properties(XDDFParagraphProperties xDDFParagraphProperties) {
        if (xDDFParagraphProperties != null) {
            boolean isSetLstStyle = this._body.isSetLstStyle();
            CTTextBody cTTextBody = this._body;
            (isSetLstStyle ? cTTextBody.getLstStyle() : cTTextBody.addNewLstStyle()).setLvl6PPr(xDDFParagraphProperties.getXmlObject());
        } else if (this._body.isSetLstStyle()) {
            CTTextListStyle lstStyle = this._body.getLstStyle();
            if (lstStyle.isSetLvl6PPr()) {
                lstStyle.unsetLvl6PPr();
            }
        }
    }

    public XDDFParagraphProperties getLevel7Properties() {
        if (!this._body.isSetLstStyle() || !this._body.getLstStyle().isSetLvl7PPr()) {
            return null;
        }
        return new XDDFParagraphProperties(this._body.getLstStyle().getLvl7PPr());
    }

    public void setLevel7Properties(XDDFParagraphProperties xDDFParagraphProperties) {
        if (xDDFParagraphProperties != null) {
            boolean isSetLstStyle = this._body.isSetLstStyle();
            CTTextBody cTTextBody = this._body;
            (isSetLstStyle ? cTTextBody.getLstStyle() : cTTextBody.addNewLstStyle()).setLvl7PPr(xDDFParagraphProperties.getXmlObject());
        } else if (this._body.isSetLstStyle()) {
            CTTextListStyle lstStyle = this._body.getLstStyle();
            if (lstStyle.isSetLvl7PPr()) {
                lstStyle.unsetLvl7PPr();
            }
        }
    }

    public XDDFParagraphProperties getLevel8Properties() {
        if (!this._body.isSetLstStyle() || !this._body.getLstStyle().isSetLvl8PPr()) {
            return null;
        }
        return new XDDFParagraphProperties(this._body.getLstStyle().getLvl8PPr());
    }

    public void setLevel8Properties(XDDFParagraphProperties xDDFParagraphProperties) {
        if (xDDFParagraphProperties != null) {
            boolean isSetLstStyle = this._body.isSetLstStyle();
            CTTextBody cTTextBody = this._body;
            (isSetLstStyle ? cTTextBody.getLstStyle() : cTTextBody.addNewLstStyle()).setLvl8PPr(xDDFParagraphProperties.getXmlObject());
        } else if (this._body.isSetLstStyle()) {
            CTTextListStyle lstStyle = this._body.getLstStyle();
            if (lstStyle.isSetLvl8PPr()) {
                lstStyle.unsetLvl8PPr();
            }
        }
    }

    public XDDFParagraphProperties getLevel9Properties() {
        if (!this._body.isSetLstStyle() || !this._body.getLstStyle().isSetLvl9PPr()) {
            return null;
        }
        return new XDDFParagraphProperties(this._body.getLstStyle().getLvl9PPr());
    }

    public void setLevel9Properties(XDDFParagraphProperties xDDFParagraphProperties) {
        if (xDDFParagraphProperties != null) {
            boolean isSetLstStyle = this._body.isSetLstStyle();
            CTTextBody cTTextBody = this._body;
            (isSetLstStyle ? cTTextBody.getLstStyle() : cTTextBody.addNewLstStyle()).setLvl9PPr(xDDFParagraphProperties.getXmlObject());
        } else if (this._body.isSetLstStyle()) {
            CTTextListStyle lstStyle = this._body.getLstStyle();
            if (lstStyle.isSetLvl9PPr()) {
                lstStyle.unsetLvl9PPr();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Internal
    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> predicate, Function<CTTextParagraphProperties, R> function, int i) {
        if (!this._body.isSetLstStyle() || i < 0) {
            TextContainer textContainer = this._parent;
            if (textContainer != null) {
                return textContainer.findDefinedParagraphProperty(predicate, function);
            }
            return Optional.empty();
        }
        CTTextListStyle lstStyle = this._body.getLstStyle();
        CTTextParagraphProperties defPPr = i == 0 ? lstStyle.getDefPPr() : retrieveProperties(lstStyle, i);
        if (defPPr == null || !predicate.test(defPPr)) {
            return findDefinedParagraphProperty(predicate, function, i - 1);
        }
        return Optional.of(function.apply(defPPr));
    }

    /* access modifiers changed from: protected */
    @Internal
    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> predicate, Function<CTTextCharacterProperties, R> function, int i) {
        if (!this._body.isSetLstStyle() || i < 0) {
            TextContainer textContainer = this._parent;
            if (textContainer != null) {
                return textContainer.findDefinedRunProperty(predicate, function);
            }
            return Optional.empty();
        }
        CTTextListStyle lstStyle = this._body.getLstStyle();
        CTTextParagraphProperties defPPr = i == 0 ? lstStyle.getDefPPr() : retrieveProperties(lstStyle, i);
        if (defPPr == null || !defPPr.isSetDefRPr() || !predicate.test(defPPr.getDefRPr())) {
            return findDefinedRunProperty(predicate, function, i - 1);
        }
        return Optional.of(function.apply(defPPr.getDefRPr()));
    }

    private CTTextParagraphProperties retrieveProperties(CTTextListStyle cTTextListStyle, int i) {
        switch (i) {
            case 1:
                if (cTTextListStyle.isSetLvl1PPr()) {
                    return cTTextListStyle.getLvl1PPr();
                }
                return null;
            case 2:
                if (cTTextListStyle.isSetLvl2PPr()) {
                    return cTTextListStyle.getLvl2PPr();
                }
                return null;
            case 3:
                if (cTTextListStyle.isSetLvl3PPr()) {
                    return cTTextListStyle.getLvl3PPr();
                }
                return null;
            case 4:
                if (cTTextListStyle.isSetLvl4PPr()) {
                    return cTTextListStyle.getLvl4PPr();
                }
                return null;
            case 5:
                if (cTTextListStyle.isSetLvl5PPr()) {
                    return cTTextListStyle.getLvl5PPr();
                }
                return null;
            case 6:
                if (cTTextListStyle.isSetLvl6PPr()) {
                    return cTTextListStyle.getLvl6PPr();
                }
                return null;
            case 7:
                if (cTTextListStyle.isSetLvl7PPr()) {
                    return cTTextListStyle.getLvl7PPr();
                }
                return null;
            case 8:
                if (cTTextListStyle.isSetLvl8PPr()) {
                    return cTTextListStyle.getLvl8PPr();
                }
                return null;
            case 9:
                if (cTTextListStyle.isSetLvl9PPr()) {
                    return cTTextListStyle.getLvl9PPr();
                }
                return null;
            default:
                return null;
        }
    }
}
