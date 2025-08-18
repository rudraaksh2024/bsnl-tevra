package org.apache.poi.xslf.usermodel;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xslf.model.TextBodyPropertyFetcher;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextWrappingType;

public abstract class XSLFTextShape extends XSLFSimpleShape implements TextContainer, TextShape<XSLFShape, XSLFTextParagraph> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final List<XSLFTextParagraph> _paragraphs = new ArrayList();

    /* access modifiers changed from: protected */
    public abstract CTTextBody getTextBody(boolean z);

    XSLFTextShape(XmlObject xmlObject, XSLFSheet xSLFSheet) {
        super(xmlObject, xSLFSheet);
        CTTextBody textBody = getTextBody(false);
        if (textBody != null) {
            for (CTTextParagraph newTextParagraph : textBody.getPArray()) {
                this._paragraphs.add(newTextParagraph(newTextParagraph));
            }
        }
    }

    public XDDFTextBody getTextBody() {
        CTTextBody textBody = getTextBody(false);
        if (textBody == null) {
            return null;
        }
        return new XDDFTextBody(this, textBody);
    }

    public Iterator<XSLFTextParagraph> iterator() {
        return getTextParagraphs().iterator();
    }

    public Spliterator<XSLFTextParagraph> spliterator() {
        return getTextParagraphs().spliterator();
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (XSLFTextParagraph next : this._paragraphs) {
            if (sb.length() > 0) {
                sb.append(10);
            }
            sb.append(next.getText());
        }
        return sb.toString();
    }

    public void clearText() {
        this._paragraphs.clear();
        getTextBody(true).setPArray((CTTextParagraph[]) null);
    }

    public XSLFTextRun setText(String str) {
        if (!this._paragraphs.isEmpty()) {
            CTTextBody textBody = getTextBody(false);
            for (int sizeOfPArray = textBody.sizeOfPArray(); sizeOfPArray > 1; sizeOfPArray--) {
                int i = sizeOfPArray - 1;
                textBody.removeP(i);
                this._paragraphs.remove(i);
            }
            this._paragraphs.get(0).clearButKeepProperties();
        }
        return appendText(str, false);
    }

    public XSLFTextRun appendText(String str, boolean z) {
        boolean z2;
        CTTextCharacterProperties cTTextCharacterProperties;
        CTTextParagraphProperties cTTextParagraphProperties;
        XSLFTextParagraph xSLFTextParagraph;
        CTTextParagraph xmlObject;
        CTTextCharacterProperties endParaRPr;
        XSLFTextRun xSLFTextRun = null;
        if (str == null) {
            return null;
        }
        if (this._paragraphs.isEmpty()) {
            xSLFTextParagraph = null;
            cTTextParagraphProperties = null;
            cTTextCharacterProperties = null;
            z2 = false;
        } else {
            z2 = !z;
            List<XSLFTextParagraph> list = this._paragraphs;
            xSLFTextParagraph = list.get(list.size() - 1);
            CTTextParagraph xmlObject2 = xSLFTextParagraph.getXmlObject();
            cTTextParagraphProperties = xmlObject2.getPPr();
            List<XSLFTextRun> textRuns = xSLFTextParagraph.getTextRuns();
            if (!textRuns.isEmpty()) {
                cTTextCharacterProperties = textRuns.get(textRuns.size() - 1).getRPr(false);
                if (cTTextCharacterProperties == null) {
                    cTTextCharacterProperties = xmlObject2.getEndParaRPr();
                }
            } else {
                cTTextCharacterProperties = null;
            }
        }
        String[] split = str.split("\\r\\n?|\\n");
        int length = split.length;
        int i = 0;
        while (i < length) {
            String str2 = split[i];
            if (!z2) {
                if (!(xSLFTextParagraph == null || (endParaRPr = xmlObject.getEndParaRPr()) == null || endParaRPr == cTTextCharacterProperties)) {
                    (xmlObject = xSLFTextParagraph.getXmlObject()).unsetEndParaRPr();
                }
                XSLFTextParagraph addNewTextParagraph = addNewTextParagraph();
                if (cTTextParagraphProperties != null) {
                    addNewTextParagraph.getXmlObject().setPPr(cTTextParagraphProperties);
                }
                xSLFTextParagraph = addNewTextParagraph;
            }
            String[] split2 = str2.split("[\u000b]");
            int length2 = split2.length;
            int i2 = 0;
            boolean z3 = true;
            while (i2 < length2) {
                String str3 = split2[i2];
                if (!z3) {
                    xSLFTextParagraph.addLineBreak();
                }
                XSLFTextRun addNewTextRun = xSLFTextParagraph.addNewTextRun();
                addNewTextRun.setText(str3);
                if (cTTextCharacterProperties != null) {
                    addNewTextRun.getRPr(true).set(cTTextCharacterProperties);
                }
                i2++;
                xSLFTextRun = addNewTextRun;
                z3 = false;
            }
            i++;
            z2 = false;
        }
        return xSLFTextRun;
    }

    public List<XSLFTextParagraph> getTextParagraphs() {
        return Collections.unmodifiableList(this._paragraphs);
    }

    public XSLFTextParagraph addNewTextParagraph() {
        CTTextParagraph cTTextParagraph;
        CTTextBody textBody = getTextBody(false);
        if (textBody == null) {
            CTTextBody textBody2 = getTextBody(true);
            new XDDFTextBody(this, textBody2).initialize();
            cTTextParagraph = textBody2.getPArray(0);
            cTTextParagraph.removeR(0);
        } else {
            cTTextParagraph = textBody.addNewP();
        }
        XSLFTextParagraph newTextParagraph = newTextParagraph(cTTextParagraph);
        this._paragraphs.add(newTextParagraph);
        return newTextParagraph;
    }

    public boolean removeTextParagraph(XSLFTextParagraph xSLFTextParagraph) {
        CTTextParagraph xmlObject = xSLFTextParagraph.getXmlObject();
        CTTextBody textBody = getTextBody(false);
        if (textBody != null && this._paragraphs.remove(xSLFTextParagraph)) {
            for (int i = 0; i < textBody.sizeOfPArray(); i++) {
                if (textBody.getPArray(i).equals(xmlObject)) {
                    textBody.removeP(i);
                    return true;
                }
            }
        }
        return false;
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        CTTextBodyProperties textBodyPr = getTextBodyPr(true);
        if (textBodyPr == null) {
            return;
        }
        if (verticalAlignment != null) {
            textBodyPr.setAnchor(STTextAnchoringType.Enum.forInt(verticalAlignment.ordinal() + 1));
        } else if (textBodyPr.isSetAnchor()) {
            textBodyPr.unsetAnchor();
        }
    }

    public VerticalAlignment getVerticalAlignment() {
        AnonymousClass1 r0 = new TextBodyPropertyFetcher<VerticalAlignment>() {
            public boolean fetch(CTTextBodyProperties cTTextBodyProperties) {
                if (!cTTextBodyProperties.isSetAnchor()) {
                    return false;
                }
                setValue(VerticalAlignment.values()[cTTextBodyProperties.getAnchor().intValue() - 1]);
                return true;
            }
        };
        fetchShapeProperty(r0);
        return r0.getValue() == null ? VerticalAlignment.TOP : (VerticalAlignment) r0.getValue();
    }

    public void setHorizontalCentered(Boolean bool) {
        CTTextBodyProperties textBodyPr = getTextBodyPr(true);
        if (textBodyPr == null) {
            return;
        }
        if (bool != null) {
            textBodyPr.setAnchorCtr(bool.booleanValue());
        } else if (textBodyPr.isSetAnchorCtr()) {
            textBodyPr.unsetAnchorCtr();
        }
    }

    public boolean isHorizontalCentered() {
        AnonymousClass2 r0 = new TextBodyPropertyFetcher<Boolean>() {
            public boolean fetch(CTTextBodyProperties cTTextBodyProperties) {
                if (!cTTextBodyProperties.isSetAnchorCtr()) {
                    return false;
                }
                setValue(Boolean.valueOf(cTTextBodyProperties.getAnchorCtr()));
                return true;
            }
        };
        fetchShapeProperty(r0);
        return r0.getValue() != null && ((Boolean) r0.getValue()).booleanValue();
    }

    public void setTextDirection(TextShape.TextDirection textDirection) {
        CTTextBodyProperties textBodyPr = getTextBodyPr(true);
        if (textBodyPr == null) {
            return;
        }
        if (textDirection != null) {
            textBodyPr.setVert(STTextVerticalType.Enum.forInt(textDirection.ordinal() + 1));
        } else if (textBodyPr.isSetVert()) {
            textBodyPr.unsetVert();
        }
    }

    public TextShape.TextDirection getTextDirection() {
        STTextVerticalType.Enum vert;
        CTTextBodyProperties textBodyPr = getTextBodyPr();
        if (textBodyPr == null || (vert = textBodyPr.getVert()) == null) {
            return TextShape.TextDirection.HORIZONTAL;
        }
        switch (vert.intValue()) {
            case 2:
            case 5:
            case 6:
                return TextShape.TextDirection.VERTICAL;
            case 3:
                return TextShape.TextDirection.VERTICAL_270;
            case 4:
            case 7:
                return TextShape.TextDirection.STACKED;
            default:
                return TextShape.TextDirection.HORIZONTAL;
        }
    }

    public Double getTextRotation() {
        CTTextBodyProperties textBodyPr = getTextBodyPr();
        if (textBodyPr == null || !textBodyPr.isSetRot()) {
            return null;
        }
        return Double.valueOf(((double) textBodyPr.getRot()) / 60000.0d);
    }

    public void setTextRotation(Double d) {
        CTTextBodyProperties textBodyPr = getTextBodyPr(true);
        if (textBodyPr != null) {
            textBodyPr.setRot((int) (d.doubleValue() * 60000.0d));
        }
    }

    public double getBottomInset() {
        AnonymousClass3 r0 = new TextBodyPropertyFetcher<Double>() {
            public boolean fetch(CTTextBodyProperties cTTextBodyProperties) {
                if (!cTTextBodyProperties.isSetBIns()) {
                    return false;
                }
                setValue(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(cTTextBodyProperties.xgetBIns()))));
                return true;
            }
        };
        fetchShapeProperty(r0);
        if (r0.getValue() == null) {
            return 3.6d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public double getLeftInset() {
        AnonymousClass4 r0 = new TextBodyPropertyFetcher<Double>() {
            public boolean fetch(CTTextBodyProperties cTTextBodyProperties) {
                if (!cTTextBodyProperties.isSetLIns()) {
                    return false;
                }
                setValue(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(cTTextBodyProperties.xgetLIns()))));
                return true;
            }
        };
        fetchShapeProperty(r0);
        if (r0.getValue() == null) {
            return 7.2d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public double getRightInset() {
        AnonymousClass5 r0 = new TextBodyPropertyFetcher<Double>() {
            public boolean fetch(CTTextBodyProperties cTTextBodyProperties) {
                if (!cTTextBodyProperties.isSetRIns()) {
                    return false;
                }
                setValue(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(cTTextBodyProperties.xgetRIns()))));
                return true;
            }
        };
        fetchShapeProperty(r0);
        if (r0.getValue() == null) {
            return 7.2d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public double getTopInset() {
        AnonymousClass6 r0 = new TextBodyPropertyFetcher<Double>() {
            public boolean fetch(CTTextBodyProperties cTTextBodyProperties) {
                if (!cTTextBodyProperties.isSetTIns()) {
                    return false;
                }
                setValue(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(cTTextBodyProperties.xgetTIns()))));
                return true;
            }
        };
        fetchShapeProperty(r0);
        if (r0.getValue() == null) {
            return 3.6d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public void setBottomInset(double d) {
        CTTextBodyProperties textBodyPr = getTextBodyPr(true);
        if (textBodyPr == null) {
            return;
        }
        if (d == -1.0d) {
            textBodyPr.unsetBIns();
        } else {
            textBodyPr.setBIns(Integer.valueOf(Units.toEMU(d)));
        }
    }

    public void setLeftInset(double d) {
        CTTextBodyProperties textBodyPr = getTextBodyPr(true);
        if (textBodyPr == null) {
            return;
        }
        if (d == -1.0d) {
            textBodyPr.unsetLIns();
        } else {
            textBodyPr.setLIns(Integer.valueOf(Units.toEMU(d)));
        }
    }

    public void setRightInset(double d) {
        CTTextBodyProperties textBodyPr = getTextBodyPr(true);
        if (textBodyPr == null) {
            return;
        }
        if (d == -1.0d) {
            textBodyPr.unsetRIns();
        } else {
            textBodyPr.setRIns(Integer.valueOf(Units.toEMU(d)));
        }
    }

    public void setTopInset(double d) {
        CTTextBodyProperties textBodyPr = getTextBodyPr(true);
        if (textBodyPr == null) {
            return;
        }
        if (d == -1.0d) {
            textBodyPr.unsetTIns();
        } else {
            textBodyPr.setTIns(Integer.valueOf(Units.toEMU(d)));
        }
    }

    public Insets2D getInsets() {
        return new Insets2D(getTopInset(), getLeftInset(), getBottomInset(), getRightInset());
    }

    public void setInsets(Insets2D insets2D) {
        setTopInset(insets2D.top);
        setLeftInset(insets2D.left);
        setBottomInset(insets2D.bottom);
        setRightInset(insets2D.right);
    }

    public boolean getWordWrap() {
        AnonymousClass7 r0 = new TextBodyPropertyFetcher<Boolean>() {
            public boolean fetch(CTTextBodyProperties cTTextBodyProperties) {
                boolean z = false;
                if (!cTTextBodyProperties.isSetWrap()) {
                    return false;
                }
                if (cTTextBodyProperties.getWrap() == STTextWrappingType.SQUARE) {
                    z = true;
                }
                setValue(Boolean.valueOf(z));
                return true;
            }
        };
        fetchShapeProperty(r0);
        return r0.getValue() == null || ((Boolean) r0.getValue()).booleanValue();
    }

    public void setWordWrap(boolean z) {
        CTTextBodyProperties textBodyPr = getTextBodyPr(true);
        if (textBodyPr != null) {
            textBodyPr.setWrap(z ? STTextWrappingType.SQUARE : STTextWrappingType.NONE);
        }
    }

    public void setTextAutofit(TextShape.TextAutofit textAutofit) {
        CTTextBodyProperties textBodyPr = getTextBodyPr(true);
        if (textBodyPr != null) {
            if (textBodyPr.isSetSpAutoFit()) {
                textBodyPr.unsetSpAutoFit();
            }
            if (textBodyPr.isSetNoAutofit()) {
                textBodyPr.unsetNoAutofit();
            }
            if (textBodyPr.isSetNormAutofit()) {
                textBodyPr.unsetNormAutofit();
            }
            int i = AnonymousClass8.$SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextAutofit[textAutofit.ordinal()];
            if (i == 1) {
                textBodyPr.addNewNoAutofit();
            } else if (i == 2) {
                textBodyPr.addNewNormAutofit();
            } else if (i == 3) {
                textBodyPr.addNewSpAutoFit();
            }
        }
    }

    public TextShape.TextAutofit getTextAutofit() {
        CTTextBodyProperties textBodyPr = getTextBodyPr();
        if (textBodyPr != null) {
            if (textBodyPr.isSetNoAutofit()) {
                return TextShape.TextAutofit.NONE;
            }
            if (textBodyPr.isSetNormAutofit()) {
                return TextShape.TextAutofit.NORMAL;
            }
            if (textBodyPr.isSetSpAutoFit()) {
                return TextShape.TextAutofit.SHAPE;
            }
        }
        return TextShape.TextAutofit.NORMAL;
    }

    /* access modifiers changed from: protected */
    public CTTextBodyProperties getTextBodyPr() {
        return getTextBodyPr(false);
    }

    /* access modifiers changed from: protected */
    public CTTextBodyProperties getTextBodyPr(boolean z) {
        CTTextBody textBody = getTextBody(z);
        if (textBody == null) {
            return null;
        }
        CTTextBodyProperties bodyPr = textBody.getBodyPr();
        return (bodyPr != null || !z) ? bodyPr : textBody.addNewBodyPr();
    }

    public void setPlaceholder(Placeholder placeholder) {
        super.setPlaceholder(placeholder);
    }

    public Placeholder getTextType() {
        return getPlaceholder();
    }

    public double getTextHeight() {
        return getTextHeight((Graphics2D) null);
    }

    public double getTextHeight(Graphics2D graphics2D) {
        return DrawFactory.getInstance(graphics2D).getDrawable((TextShape<?, ?>) this).getTextHeight(graphics2D);
    }

    public Rectangle2D resizeToFitText() {
        return resizeToFitText((Graphics2D) null);
    }

    public Rectangle2D resizeToFitText(Graphics2D graphics2D) {
        Rectangle2D anchor = getAnchor();
        if (anchor.getWidth() != 0.0d) {
            Insets2D insets = getInsets();
            anchor.setRect(anchor.getX(), anchor.getY(), anchor.getWidth(), insets.bottom + getTextHeight(graphics2D) + 1.0d + insets.top);
            setAnchor(anchor);
            return anchor;
        }
        throw new POIXMLException("Anchor of the shape was not set.");
    }

    /* access modifiers changed from: package-private */
    public void copy(XSLFShape xSLFShape) {
        super.copy(xSLFShape);
        XSLFTextShape xSLFTextShape = (XSLFTextShape) xSLFShape;
        CTTextBody textBody = xSLFTextShape.getTextBody(false);
        if (textBody != null) {
            CTTextBody textBody2 = getTextBody(true);
            textBody2.setBodyPr((CTTextBodyProperties) textBody.getBodyPr().copy());
            if (textBody2.isSetLstStyle()) {
                textBody2.unsetLstStyle();
            }
            if (textBody.isSetLstStyle()) {
                textBody2.setLstStyle((CTTextListStyle) textBody.getLstStyle().copy());
            }
            boolean wordWrap = xSLFTextShape.getWordWrap();
            if (wordWrap != getWordWrap()) {
                setWordWrap(wordWrap);
            }
            double leftInset = xSLFTextShape.getLeftInset();
            if (leftInset != getLeftInset()) {
                setLeftInset(leftInset);
            }
            double rightInset = xSLFTextShape.getRightInset();
            if (rightInset != getRightInset()) {
                setRightInset(rightInset);
            }
            double topInset = xSLFTextShape.getTopInset();
            if (topInset != getTopInset()) {
                setTopInset(topInset);
            }
            double bottomInset = xSLFTextShape.getBottomInset();
            if (bottomInset != getBottomInset()) {
                setBottomInset(bottomInset);
            }
            VerticalAlignment verticalAlignment = xSLFTextShape.getVerticalAlignment();
            if (verticalAlignment != getVerticalAlignment()) {
                setVerticalAlignment(verticalAlignment);
            }
            clearText();
            for (XSLFTextParagraph copy : xSLFTextShape.getTextParagraphs()) {
                addNewTextParagraph().copy(copy);
            }
        }
    }

    public void setTextPlaceholder(TextShape.TextPlaceholder textPlaceholder) {
        int i = AnonymousClass8.$SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextPlaceholder[textPlaceholder.ordinal()];
        if (i == 5) {
            setPlaceholder(Placeholder.TITLE);
        } else if (i == 6) {
            setPlaceholder(Placeholder.BODY);
            setHorizontalCentered(true);
        } else if (i == 7) {
            setPlaceholder(Placeholder.CENTERED_TITLE);
        } else if (i != 8) {
            setPlaceholder(Placeholder.BODY);
        } else {
            setPlaceholder(Placeholder.CONTENT);
        }
    }

    public TextShape.TextPlaceholder getTextPlaceholder() {
        Placeholder textType = getTextType();
        if (textType == null) {
            return TextShape.TextPlaceholder.BODY;
        }
        int i = AnonymousClass8.$SwitchMap$org$apache$poi$sl$usermodel$Placeholder[textType.ordinal()];
        if (i == 1) {
            return TextShape.TextPlaceholder.BODY;
        }
        if (i == 2) {
            return TextShape.TextPlaceholder.TITLE;
        }
        if (i != 3) {
            return TextShape.TextPlaceholder.OTHER;
        }
        return TextShape.TextPlaceholder.CENTER_TITLE;
    }

    /* renamed from: org.apache.poi.xslf.usermodel.XSLFTextShape$8  reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$Placeholder;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextAutofit;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextPlaceholder;

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|35|36|37|38|(3:39|40|42)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(33:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|35|36|37|38|(3:39|40|42)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(37:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|35|36|37|38|39|40|42) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0083 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00a0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00aa */
        static {
            /*
                org.apache.poi.sl.usermodel.Placeholder[] r0 = org.apache.poi.sl.usermodel.Placeholder.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$Placeholder = r0
                r1 = 1
                org.apache.poi.sl.usermodel.Placeholder r2 = org.apache.poi.sl.usermodel.Placeholder.BODY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.Placeholder r3 = org.apache.poi.sl.usermodel.Placeholder.TITLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.Placeholder r4 = org.apache.poi.sl.usermodel.Placeholder.CENTERED_TITLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.Placeholder r5 = org.apache.poi.sl.usermodel.Placeholder.CONTENT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                org.apache.poi.sl.usermodel.TextShape$TextPlaceholder[] r4 = org.apache.poi.sl.usermodel.TextShape.TextPlaceholder.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextPlaceholder = r4
                org.apache.poi.sl.usermodel.TextShape$TextPlaceholder r5 = org.apache.poi.sl.usermodel.TextShape.TextPlaceholder.NOTES     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r4 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextPlaceholder     // Catch:{ NoSuchFieldError -> 0x004e }
                org.apache.poi.sl.usermodel.TextShape$TextPlaceholder r5 = org.apache.poi.sl.usermodel.TextShape.TextPlaceholder.HALF_BODY     // Catch:{ NoSuchFieldError -> 0x004e }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r4 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextPlaceholder     // Catch:{ NoSuchFieldError -> 0x0058 }
                org.apache.poi.sl.usermodel.TextShape$TextPlaceholder r5 = org.apache.poi.sl.usermodel.TextShape.TextPlaceholder.QUARTER_BODY     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r4 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextPlaceholder     // Catch:{ NoSuchFieldError -> 0x0062 }
                org.apache.poi.sl.usermodel.TextShape$TextPlaceholder r5 = org.apache.poi.sl.usermodel.TextShape.TextPlaceholder.BODY     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r3 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextPlaceholder     // Catch:{ NoSuchFieldError -> 0x006d }
                org.apache.poi.sl.usermodel.TextShape$TextPlaceholder r4 = org.apache.poi.sl.usermodel.TextShape.TextPlaceholder.TITLE     // Catch:{ NoSuchFieldError -> 0x006d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r3 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextPlaceholder     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.sl.usermodel.TextShape$TextPlaceholder r4 = org.apache.poi.sl.usermodel.TextShape.TextPlaceholder.CENTER_BODY     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r3 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextPlaceholder     // Catch:{ NoSuchFieldError -> 0x0083 }
                org.apache.poi.sl.usermodel.TextShape$TextPlaceholder r4 = org.apache.poi.sl.usermodel.TextShape.TextPlaceholder.CENTER_TITLE     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                int[] r3 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextPlaceholder     // Catch:{ NoSuchFieldError -> 0x008f }
                org.apache.poi.sl.usermodel.TextShape$TextPlaceholder r4 = org.apache.poi.sl.usermodel.TextShape.TextPlaceholder.OTHER     // Catch:{ NoSuchFieldError -> 0x008f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                org.apache.poi.sl.usermodel.TextShape$TextAutofit[] r3 = org.apache.poi.sl.usermodel.TextShape.TextAutofit.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextAutofit = r3
                org.apache.poi.sl.usermodel.TextShape$TextAutofit r4 = org.apache.poi.sl.usermodel.TextShape.TextAutofit.NONE     // Catch:{ NoSuchFieldError -> 0x00a0 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a0 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x00a0 }
            L_0x00a0:
                int[] r1 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextAutofit     // Catch:{ NoSuchFieldError -> 0x00aa }
                org.apache.poi.sl.usermodel.TextShape$TextAutofit r3 = org.apache.poi.sl.usermodel.TextShape.TextAutofit.NORMAL     // Catch:{ NoSuchFieldError -> 0x00aa }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00aa }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x00aa }
            L_0x00aa:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextAutofit     // Catch:{ NoSuchFieldError -> 0x00b4 }
                org.apache.poi.sl.usermodel.TextShape$TextAutofit r1 = org.apache.poi.sl.usermodel.TextShape.TextAutofit.SHAPE     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFTextShape.AnonymousClass8.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public XSLFTextParagraph newTextParagraph(CTTextParagraph cTTextParagraph) {
        return new XSLFTextParagraph(cTTextParagraph, this);
    }

    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> predicate, Function<CTTextParagraphProperties, R> function) {
        return Optional.empty();
    }

    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> predicate, Function<CTTextCharacterProperties, R> function) {
        return Optional.empty();
    }
}
