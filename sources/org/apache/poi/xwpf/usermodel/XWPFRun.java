package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.namespace.QName;
import kotlinx.coroutines.DebugKt;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.CharacterRun;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STHexColorRGB;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STVerticalAlignRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFCheckBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalAlignRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrClear;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColorAuto;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XWPFRun implements ISDTContents, IRunElement, CharacterRun {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final IRunBody parent;
    private final String pictureText;
    private final List<XWPFPicture> pictures;
    private final CTR run;

    public enum FontCharRange {
        ascii,
        cs,
        eastAsia,
        hAnsi
    }

    public void removeBreak() {
    }

    public void removeCarriageReturn() {
    }

    public void removeTab() {
    }

    public XWPFRun(CTR ctr, IRunBody iRunBody) {
        this.run = ctr;
        this.parent = iRunBody;
        for (CTDrawing cTDrawing : ctr.getDrawingArray()) {
            for (CTAnchor cTAnchor : cTDrawing.getAnchorArray()) {
                if (cTAnchor.getDocPr() != null) {
                    getDocument().getDrawingIdManager().reserve(cTAnchor.getDocPr().getId());
                }
            }
            for (CTInline cTInline : cTDrawing.getInlineArray()) {
                if (cTInline.getDocPr() != null) {
                    getDocument().getDrawingIdManager().reserve(cTInline.getDocPr().getId());
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<XmlObject> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(ctr.getPictArray()));
        arrayList.addAll(Arrays.asList(ctr.getDrawingArray()));
        for (XmlObject selectPath : arrayList) {
            for (XmlObject domNode : selectPath.selectPath("declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//w:t")) {
                NodeList childNodes = domNode.getDomNode().getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    if (childNodes.item(i) instanceof Text) {
                        if (sb.length() > 0) {
                            sb.append("\n");
                        }
                        sb.append(childNodes.item(i).getNodeValue());
                    }
                }
            }
        }
        this.pictureText = sb.toString();
        this.pictures = new ArrayList();
        for (XmlObject cTPictures : arrayList) {
            for (CTPicture xWPFPicture : getCTPictures(cTPictures)) {
                this.pictures.add(new XWPFPicture(xWPFPicture, this));
            }
        }
    }

    @Deprecated
    public XWPFRun(CTR ctr, XWPFParagraph xWPFParagraph) {
        this(ctr, (IRunBody) xWPFParagraph);
    }

    static void preserveSpaces(XmlString xmlString) {
        String stringValue = xmlString.getStringValue();
        if (stringValue != null && stringValue.length() >= 1) {
            if (Character.isWhitespace(stringValue.charAt(0)) || Character.isWhitespace(stringValue.charAt(stringValue.length() - 1))) {
                XmlCursor newCursor = xmlString.newCursor();
                newCursor.toNextToken();
                newCursor.insertAttributeWithValue(new QName("http://www.w3.org/XML/1998/namespace", "space"), "preserve");
                newCursor.dispose();
            }
        }
    }

    private List<CTPicture> getCTPictures(XmlObject xmlObject) {
        ArrayList arrayList = new ArrayList();
        XmlObject[] selectPath = xmlObject.selectPath("declare namespace pic='" + CTPicture.type.getName().getNamespaceURI() + "' .//pic:pic");
        int length = selectPath.length;
        for (int i = 0; i < length; i++) {
            XmlObject xmlObject2 = selectPath[i];
            if (xmlObject2 instanceof XmlAnyTypeImpl) {
                try {
                    xmlObject2 = CTPicture.Factory.parse(xmlObject2.toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                } catch (XmlException e) {
                    throw new POIXMLException((Throwable) e);
                }
            }
            if (xmlObject2 instanceof CTPicture) {
                arrayList.add((CTPicture) xmlObject2);
            }
        }
        return arrayList;
    }

    @Internal
    public CTR getCTR() {
        return this.run;
    }

    public IRunBody getParent() {
        return this.parent;
    }

    @Deprecated
    public XWPFParagraph getParagraph() {
        IRunBody iRunBody = this.parent;
        if (iRunBody instanceof XWPFParagraph) {
            return (XWPFParagraph) iRunBody;
        }
        return null;
    }

    public XWPFDocument getDocument() {
        IRunBody iRunBody = this.parent;
        if (iRunBody != null) {
            return iRunBody.getDocument();
        }
        return null;
    }

    private static boolean isCTOnOff(CTOnOff cTOnOff) {
        return !cTOnOff.isSetVal() || POIXMLUnits.parseOnOff(cTOnOff);
    }

    public String getLang() {
        CTRPr runProperties = getRunProperties(false);
        return (runProperties == null || runProperties.sizeOfLangArray() == 0) ? null : runProperties.getLangArray(0).getVal();
    }

    public void setLang(String str) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfLangArray() > 0 ? runProperties.getLangArray(0) : runProperties.addNewLang()).setVal(str);
    }

    public boolean isBold() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfBArray() <= 0 || !isCTOnOff(runProperties.getBArray(0))) {
            return false;
        }
        return true;
    }

    public void setBold(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfBArray() > 0 ? runProperties.getBArray(0) : runProperties.addNewB()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    public String getColor() {
        CTRPr runProperties;
        if (!this.run.isSetRPr() || (runProperties = getRunProperties(false)) == null || runProperties.sizeOfColorArray() <= 0) {
            return null;
        }
        return runProperties.getColorArray(0).xgetVal().getStringValue();
    }

    public void setColor(String str) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfColorArray() > 0 ? runProperties.getColorArray(0) : runProperties.addNewColor()).setVal(str);
    }

    public String getText(int i) {
        if (this.run.sizeOfTArray() == 0) {
            return null;
        }
        return this.run.getTArray(i).getStringValue();
    }

    public String getPictureText() {
        return this.pictureText;
    }

    public void setText(String str) {
        setText(str, this.run.sizeOfTArray());
    }

    public void setText(String str, int i) {
        if (i <= this.run.sizeOfTArray()) {
            CTText addNewT = (i >= this.run.sizeOfTArray() || i < 0) ? this.run.addNewT() : this.run.getTArray(i);
            addNewT.setStringValue(str);
            preserveSpaces(addNewT);
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Value too large for the parameter position in XWPFRun.setText(String value,int pos)");
    }

    public boolean isItalic() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfIArray() <= 0 || !isCTOnOff(runProperties.getIArray(0))) {
            return false;
        }
        return true;
    }

    public void setItalic(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfIArray() > 0 ? runProperties.getIArray(0) : runProperties.addNewI()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r2 = r2.getVal();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xwpf.usermodel.UnderlinePatterns getUnderline() {
        /*
            r2 = this;
            org.apache.poi.xwpf.usermodel.UnderlinePatterns r0 = org.apache.poi.xwpf.usermodel.UnderlinePatterns.NONE
            r1 = 0
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline r2 = r2.getCTUnderline(r1)
            if (r2 == 0) goto L_0x0017
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline$Enum r2 = r2.getVal()
            if (r2 == 0) goto L_0x0017
            int r2 = r2.intValue()
            org.apache.poi.xwpf.usermodel.UnderlinePatterns r0 = org.apache.poi.xwpf.usermodel.UnderlinePatterns.valueOf((int) r2)
        L_0x0017:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFRun.getUnderline():org.apache.poi.xwpf.usermodel.UnderlinePatterns");
    }

    public void setUnderline(UnderlinePatterns underlinePatterns) {
        getCTUnderline(true).setVal(STUnderline.Enum.forInt(underlinePatterns.getValue()));
    }

    private CTUnderline getCTUnderline(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        if (runProperties.sizeOfUArray() > 0) {
            return runProperties.getUArray(0);
        }
        if (z) {
            return runProperties.addNewU();
        }
        return null;
    }

    public void setUnderlineColor(String str) {
        SimpleValue simpleValue;
        CTUnderline cTUnderline = getCTUnderline(true);
        if (str.equals(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
            STHexColorAuto newInstance = STHexColorAuto.Factory.newInstance();
            newInstance.setEnumValue(STHexColorAuto.Enum.forString(str));
            simpleValue = (SimpleValue) newInstance;
        } else {
            STHexColorRGB newInstance2 = STHexColorRGB.Factory.newInstance();
            newInstance2.setStringValue(str);
            simpleValue = (SimpleValue) newInstance2;
        }
        cTUnderline.setColor(simpleValue);
    }

    public void setUnderlineThemeColor(String str) {
        CTUnderline cTUnderline = getCTUnderline(true);
        STThemeColor.Enum forString = STThemeColor.Enum.forString(str);
        if (forString != null) {
            cTUnderline.setThemeColor(forString);
        }
    }

    public STThemeColor.Enum getUnderlineThemeColor() {
        CTUnderline cTUnderline = getCTUnderline(false);
        return cTUnderline != null ? cTUnderline.getThemeColor() : STThemeColor.NONE;
    }

    public String getUnderlineColor() {
        Object color = getCTUnderline(true).getColor();
        if (color == null) {
            return DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
        }
        if (color instanceof String) {
            return (String) color;
        }
        byte[] bArr = (byte[]) color;
        return HexDump.toHex(bArr[0]) + HexDump.toHex(bArr[1]) + HexDump.toHex(bArr[2]);
    }

    public boolean isStrikeThrough() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfStrikeArray() <= 0 || !isCTOnOff(runProperties.getStrikeArray(0))) {
            return false;
        }
        return true;
    }

    public void setStrikeThrough(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfStrikeArray() > 0 ? runProperties.getStrikeArray(0) : runProperties.addNewStrike()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    @Deprecated
    public boolean isStrike() {
        return isStrikeThrough();
    }

    @Deprecated
    public void setStrike(boolean z) {
        setStrikeThrough(z);
    }

    public boolean isDoubleStrikeThrough() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfDstrikeArray() <= 0 || !isCTOnOff(runProperties.getDstrikeArray(0))) {
            return false;
        }
        return true;
    }

    public void setDoubleStrikethrough(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfDstrikeArray() > 0 ? runProperties.getDstrikeArray(0) : runProperties.addNewDstrike()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    public boolean isSmallCaps() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfSmallCapsArray() <= 0 || !isCTOnOff(runProperties.getSmallCapsArray(0))) {
            return false;
        }
        return true;
    }

    public void setSmallCaps(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfSmallCapsArray() > 0 ? runProperties.getSmallCapsArray(0) : runProperties.addNewSmallCaps()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    public boolean isCapitalized() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfCapsArray() <= 0 || !isCTOnOff(runProperties.getCapsArray(0))) {
            return false;
        }
        return true;
    }

    public void setCapitalized(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfCapsArray() > 0 ? runProperties.getCapsArray(0) : runProperties.addNewCaps()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    public boolean isShadowed() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfShadowArray() <= 0 || !isCTOnOff(runProperties.getShadowArray(0))) {
            return false;
        }
        return true;
    }

    public void setShadow(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfShadowArray() > 0 ? runProperties.getShadowArray(0) : runProperties.addNewShadow()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    public boolean isImprinted() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfImprintArray() <= 0 || !isCTOnOff(runProperties.getImprintArray(0))) {
            return false;
        }
        return true;
    }

    public void setImprinted(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfImprintArray() > 0 ? runProperties.getImprintArray(0) : runProperties.addNewImprint()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    public boolean isEmbossed() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfEmbossArray() <= 0 || !isCTOnOff(runProperties.getEmbossArray(0))) {
            return false;
        }
        return true;
    }

    public void setEmbossed(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfEmbossArray() > 0 ? runProperties.getEmbossArray(0) : runProperties.addNewEmboss()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    public void setSubscript(VerticalAlign verticalAlign) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfVertAlignArray() > 0 ? runProperties.getVertAlignArray(0) : runProperties.addNewVertAlign()).setVal(STVerticalAlignRun.Enum.forInt(verticalAlign.getValue()));
    }

    public int getKerning() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfKernArray() == 0) {
            return 0;
        }
        return (int) POIXMLUnits.parseLength(runProperties.getKernArray(0).xgetVal());
    }

    public void setKerning(int i) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfKernArray() > 0 ? runProperties.getKernArray(0) : runProperties.addNewKern()).setVal(BigInteger.valueOf((long) i));
    }

    public boolean isHighlighted() {
        STHighlightColor.Enum val;
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfHighlightArray() == 0 || (val = runProperties.getHighlightArray(0).getVal()) == null || val == STHighlightColor.NONE) {
            return false;
        }
        return true;
    }

    public int getCharacterSpacing() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfSpacingArray() == 0) {
            return 0;
        }
        return (int) Units.toDXA(POIXMLUnits.parseLength(runProperties.getSpacingArray(0).xgetVal()));
    }

    public void setCharacterSpacing(int i) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfSpacingArray() > 0 ? runProperties.getSpacingArray(0) : runProperties.addNewSpacing()).setVal(BigInteger.valueOf((long) i));
    }

    public String getFontFamily() {
        return getFontFamily((FontCharRange) null);
    }

    public void setFontFamily(String str) {
        setFontFamily(str, (FontCharRange) null);
    }

    public String getFontName() {
        return getFontFamily();
    }

    public String getFontFamily(FontCharRange fontCharRange) {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfRFontsArray() == 0) {
            return null;
        }
        CTFonts rFontsArray = runProperties.getRFontsArray(0);
        int[] iArr = AnonymousClass1.$SwitchMap$org$apache$poi$xwpf$usermodel$XWPFRun$FontCharRange;
        if (fontCharRange == null) {
            fontCharRange = FontCharRange.ascii;
        }
        int i = iArr[fontCharRange.ordinal()];
        if (i == 2) {
            return rFontsArray.getCs();
        }
        if (i == 3) {
            return rFontsArray.getEastAsia();
        }
        if (i != 4) {
            return rFontsArray.getAscii();
        }
        return rFontsArray.getHAnsi();
    }

    /* renamed from: org.apache.poi.xwpf.usermodel.XWPFRun$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFRun$FontCharRange;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.xwpf.usermodel.XWPFRun$FontCharRange[] r0 = org.apache.poi.xwpf.usermodel.XWPFRun.FontCharRange.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFRun$FontCharRange = r0
                org.apache.poi.xwpf.usermodel.XWPFRun$FontCharRange r1 = org.apache.poi.xwpf.usermodel.XWPFRun.FontCharRange.ascii     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFRun$FontCharRange     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xwpf.usermodel.XWPFRun$FontCharRange r1 = org.apache.poi.xwpf.usermodel.XWPFRun.FontCharRange.cs     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFRun$FontCharRange     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xwpf.usermodel.XWPFRun$FontCharRange r1 = org.apache.poi.xwpf.usermodel.XWPFRun.FontCharRange.eastAsia     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFRun$FontCharRange     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xwpf.usermodel.XWPFRun$FontCharRange r1 = org.apache.poi.xwpf.usermodel.XWPFRun.FontCharRange.hAnsi     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFRun.AnonymousClass1.<clinit>():void");
        }
    }

    public void setFontFamily(String str, FontCharRange fontCharRange) {
        CTRPr runProperties = getRunProperties(true);
        CTFonts rFontsArray = runProperties.sizeOfRFontsArray() > 0 ? runProperties.getRFontsArray(0) : runProperties.addNewRFonts();
        if (fontCharRange == null) {
            rFontsArray.setAscii(str);
            if (!rFontsArray.isSetHAnsi()) {
                rFontsArray.setHAnsi(str);
            }
            if (!rFontsArray.isSetCs()) {
                rFontsArray.setCs(str);
            }
            if (!rFontsArray.isSetEastAsia()) {
                rFontsArray.setEastAsia(str);
                return;
            }
            return;
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$xwpf$usermodel$XWPFRun$FontCharRange[fontCharRange.ordinal()];
        if (i == 1) {
            rFontsArray.setAscii(str);
        } else if (i == 2) {
            rFontsArray.setCs(str);
        } else if (i == 3) {
            rFontsArray.setEastAsia(str);
        } else if (i == 4) {
            rFontsArray.setHAnsi(str);
        }
    }

    @Deprecated
    @Removal(version = "6.0.0")
    public int getFontSize() {
        BigDecimal fontSizeAsBigDecimal = getFontSizeAsBigDecimal(0);
        if (fontSizeAsBigDecimal == null) {
            return -1;
        }
        return fontSizeAsBigDecimal.intValue();
    }

    public Double getFontSizeAsDouble() {
        BigDecimal fontSizeAsBigDecimal = getFontSizeAsBigDecimal(1);
        if (fontSizeAsBigDecimal == null) {
            return null;
        }
        return Double.valueOf(fontSizeAsBigDecimal.doubleValue());
    }

    private BigDecimal getFontSizeAsBigDecimal(int i) {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfSzArray() <= 0) {
            return null;
        }
        return BigDecimal.valueOf(Units.toPoints(POIXMLUnits.parseLength(runProperties.getSzArray(0).xgetVal()))).divide(BigDecimal.valueOf(4), i, RoundingMode.HALF_UP);
    }

    public void setFontSize(int i) {
        BigInteger valueOf = BigInteger.valueOf((long) i);
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfSzArray() > 0 ? runProperties.getSzArray(0) : runProperties.addNewSz()).setVal(valueOf.multiply(BigInteger.valueOf(2)));
    }

    public void setFontSize(double d) {
        BigDecimal valueOf = BigDecimal.valueOf(d);
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfSzArray() > 0 ? runProperties.getSzArray(0) : runProperties.addNewSz()).setVal(valueOf.multiply(BigDecimal.valueOf(2)).setScale(0, RoundingMode.HALF_UP).toBigInteger());
    }

    public int getTextPosition() {
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfPositionArray() <= 0) {
            return -1;
        }
        return (int) (Units.toPoints(POIXMLUnits.parseLength(runProperties.getPositionArray(0).xgetVal())) / 2.0d);
    }

    public void setTextPosition(int i) {
        BigInteger bigInteger = new BigInteger(Integer.toString(i));
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfPositionArray() > 0 ? runProperties.getPositionArray(0) : runProperties.addNewPosition()).setVal(bigInteger);
    }

    public void addBreak() {
        this.run.addNewBr();
    }

    public void addBreak(BreakType breakType) {
        this.run.addNewBr().setType(STBrType.Enum.forInt(breakType.getValue()));
    }

    public void addBreak(BreakClear breakClear) {
        CTBr addNewBr = this.run.addNewBr();
        addNewBr.setType(STBrType.Enum.forInt(BreakType.TEXT_WRAPPING.getValue()));
        addNewBr.setClear(STBrClear.Enum.forInt(breakClear.getValue()));
    }

    public void addTab() {
        this.run.addNewTab();
    }

    public void addCarriageReturn() {
        this.run.addNewCr();
    }

    public XWPFPicture addPicture(InputStream inputStream, int i, String str, int i2, int i3) throws InvalidFormatException, IOException {
        XWPFPictureData xWPFPictureData;
        if (this.parent.getPart() instanceof XWPFHeaderFooter) {
            XWPFHeaderFooter xWPFHeaderFooter = (XWPFHeaderFooter) this.parent.getPart();
            xWPFPictureData = (XWPFPictureData) xWPFHeaderFooter.getRelationById(xWPFHeaderFooter.addPictureData(inputStream, i));
        } else if (this.parent.getPart() instanceof XWPFComments) {
            XWPFComments xWPFComments = (XWPFComments) this.parent.getPart();
            xWPFPictureData = (XWPFPictureData) xWPFComments.getRelationById(xWPFComments.addPictureData(inputStream, i));
        } else {
            XWPFDocument document = this.parent.getDocument();
            xWPFPictureData = (XWPFPictureData) document.getRelationById(document.addPictureData(inputStream, i));
        }
        try {
            CTInline addNewInline = this.run.addNewDrawing().addNewInline();
            addNewInline.set(XmlToken.Factory.parse((Node) DocumentHelper.readDocument(new InputSource(new StringReader("<a:graphic xmlns:a=\"" + CTGraphicalObject.type.getName().getNamespaceURI() + "\"><a:graphicData uri=\"" + CTPicture.type.getName().getNamespaceURI() + "\"><pic:pic xmlns:pic=\"" + CTPicture.type.getName().getNamespaceURI() + "\" /></a:graphicData></a:graphic>"))).getDocumentElement(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS));
            addNewInline.setDistT(0);
            addNewInline.setDistR(0);
            addNewInline.setDistB(0);
            addNewInline.setDistL(0);
            CTNonVisualDrawingProps addNewDocPr = addNewInline.addNewDocPr();
            long reserveNew = getParent().getDocument().getDrawingIdManager().reserveNew();
            addNewDocPr.setId(reserveNew);
            addNewDocPr.setName("Drawing " + reserveNew);
            addNewDocPr.setDescr(str);
            CTPositiveSize2D addNewExtent = addNewInline.addNewExtent();
            long j = (long) i2;
            addNewExtent.setCx(j);
            long j2 = (long) i3;
            addNewExtent.setCy(j2);
            CTPicture cTPicture = getCTPictures(addNewInline.getGraphic().getGraphicData()).get(0);
            CTPictureNonVisual addNewNvPicPr = cTPicture.addNewNvPicPr();
            CTNonVisualDrawingProps addNewCNvPr = addNewNvPicPr.addNewCNvPr();
            addNewCNvPr.setId(0);
            addNewCNvPr.setName("Picture " + reserveNew);
            addNewCNvPr.setDescr(str);
            addNewNvPicPr.addNewCNvPicPr().addNewPicLocks().setNoChangeAspect(true);
            CTBlipFillProperties addNewBlipFill = cTPicture.addNewBlipFill();
            addNewBlipFill.addNewBlip().setEmbed(this.parent.getPart().getRelationId(xWPFPictureData));
            addNewBlipFill.addNewStretch().addNewFillRect();
            CTShapeProperties addNewSpPr = cTPicture.addNewSpPr();
            CTTransform2D addNewXfrm = addNewSpPr.addNewXfrm();
            CTPoint2D addNewOff = addNewXfrm.addNewOff();
            addNewOff.setX(0);
            addNewOff.setY(0);
            CTPositiveSize2D addNewExt = addNewXfrm.addNewExt();
            addNewExt.setCx(j);
            addNewExt.setCy(j2);
            CTPresetGeometry2D addNewPrstGeom = addNewSpPr.addNewPrstGeom();
            addNewPrstGeom.setPrst(STShapeType.RECT);
            addNewPrstGeom.addNewAvLst();
            XWPFPicture xWPFPicture = new XWPFPicture(cTPicture, this);
            this.pictures.add(xWPFPicture);
            return xWPFPicture;
        } catch (XmlException | SAXException e) {
            throw new IllegalStateException(e);
        }
    }

    @Internal
    public CTInline addChart(String str) throws InvalidFormatException, IOException {
        try {
            CTInline addNewInline = this.run.addNewDrawing().addNewInline();
            addNewInline.set(XmlToken.Factory.parse((Node) DocumentHelper.readDocument(new InputSource(new StringReader("<a:graphic xmlns:a=\"" + CTGraphicalObject.type.getName().getNamespaceURI() + "\"><a:graphicData uri=\"" + CTChart.type.getName().getNamespaceURI() + "\"><c:chart xmlns:c=\"" + CTChart.type.getName().getNamespaceURI() + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" r:id=\"" + str + "\" /></a:graphicData></a:graphic>"))).getDocumentElement(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS));
            addNewInline.setDistT(0);
            addNewInline.setDistR(0);
            addNewInline.setDistB(0);
            addNewInline.setDistL(0);
            CTNonVisualDrawingProps addNewDocPr = addNewInline.addNewDocPr();
            long reserveNew = getParent().getDocument().getDrawingIdManager().reserveNew();
            addNewDocPr.setId(reserveNew);
            addNewDocPr.setName("chart " + reserveNew);
            return addNewInline;
        } catch (XmlException | SAXException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<XWPFPicture> getEmbeddedPictures() {
        return this.pictures;
    }

    public void setStyle(String str) {
        CTRPr rPr = getCTR().getRPr();
        if (rPr == null) {
            rPr = getCTR().addNewRPr();
        }
        (rPr.sizeOfRStyleArray() > 0 ? rPr.getRStyleArray(0) : rPr.addNewRStyle()).setVal(str);
    }

    public String getStyle() {
        CTString rStyleArray;
        CTRPr rPr = getCTR().getRPr();
        if (rPr == null || rPr.sizeOfRStyleArray() <= 0 || (rStyleArray = rPr.getRStyleArray(0)) == null) {
            return "";
        }
        return rStyleArray.getVal();
    }

    public String toString() {
        String phonetic = getPhonetic();
        if (phonetic.length() > 0) {
            return text() + " (" + phonetic + ")";
        }
        return text();
    }

    public String text() {
        StringBuilder sb = new StringBuilder(64);
        XmlCursor newCursor = this.run.newCursor();
        newCursor.selectPath("./*");
        while (newCursor.toNextSelection()) {
            XmlObject object = newCursor.getObject();
            if (object instanceof CTRuby) {
                handleRuby(object, sb, false);
            } else {
                _getText(object, sb);
            }
        }
        newCursor.dispose();
        return sb.toString();
    }

    public String getPhonetic() {
        StringBuilder sb = new StringBuilder(64);
        XmlCursor newCursor = this.run.newCursor();
        newCursor.selectPath("./*");
        while (newCursor.toNextSelection()) {
            XmlObject object = newCursor.getObject();
            if (object instanceof CTRuby) {
                handleRuby(object, sb, true);
            }
        }
        String str = this.pictureText;
        if (str != null && str.length() > 0) {
            sb.append("\n").append(this.pictureText).append("\n");
        }
        newCursor.dispose();
        return sb.toString();
    }

    private void handleRuby(XmlObject xmlObject, StringBuilder sb, boolean z) {
        XmlCursor newCursor = xmlObject.newCursor();
        newCursor.selectPath(".//*");
        boolean z2 = false;
        boolean z3 = false;
        while (newCursor.toNextSelection()) {
            XmlObject object = newCursor.getObject();
            if (object instanceof CTRubyContent) {
                String nodeName = object.getDomNode().getNodeName();
                if ("w:rt".equals(nodeName)) {
                    z2 = true;
                } else if ("w:rubyBase".equals(nodeName)) {
                    z2 = false;
                    z3 = true;
                }
            } else if (z && z2) {
                _getText(object, sb);
            } else if (!z && z3) {
                _getText(object, sb);
            }
        }
        newCursor.dispose();
    }

    private void _getText(XmlObject xmlObject, StringBuilder sb) {
        StringBuilder sb2;
        if ((xmlObject instanceof CTText) && !"w:instrText".equals(xmlObject.getDomNode().getNodeName())) {
            sb.append(((CTText) xmlObject).getStringValue());
        }
        if (xmlObject instanceof CTFldChar) {
            CTFldChar cTFldChar = (CTFldChar) xmlObject;
            if (cTFldChar.getFldCharType() == STFldCharType.BEGIN && cTFldChar.getFfData() != null) {
                for (CTFFCheckBox next : cTFldChar.getFfData().getCheckBoxList()) {
                    sb.append((next.getDefault() == null || !POIXMLUnits.parseOnOff(next.getDefault().xgetVal())) ? "|_|" : "|X|");
                }
            }
        }
        if (xmlObject instanceof CTPTab) {
            sb.append(9);
        }
        if (xmlObject instanceof CTBr) {
            sb.append(10);
        }
        if (xmlObject instanceof CTEmpty) {
            String nodeName = xmlObject.getDomNode().getNodeName();
            if ("w:tab".equals(nodeName) || "tab".equals(nodeName)) {
                sb.append(9);
            }
            if ("w:br".equals(nodeName) || CompressorStreamFactory.BROTLI.equals(nodeName)) {
                sb.append(10);
            }
            if ("w:cr".equals(nodeName) || "cr".equals(nodeName)) {
                sb.append(10);
            }
        }
        if (xmlObject instanceof CTFtnEdnRef) {
            CTFtnEdnRef cTFtnEdnRef = (CTFtnEdnRef) xmlObject;
            if (cTFtnEdnRef.getDomNode().getLocalName().equals("footnoteReference")) {
                sb2 = new StringBuilder("[footnoteRef:");
            } else {
                sb2 = new StringBuilder("[endnoteRef:");
            }
            sb.append(sb2.append(cTFtnEdnRef.getId().intValue()).append("]").toString());
        }
    }

    public void setTextScale(int i) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfWArray() > 0 ? runProperties.getWArray(0) : runProperties.addNewW()).setVal(Integer.valueOf(i));
    }

    public int getTextScale() {
        int parsePercent;
        CTRPr runProperties = getRunProperties(false);
        if (runProperties == null || runProperties.sizeOfWArray() == 0 || (parsePercent = POIXMLUnits.parsePercent(runProperties.getWArray(0).xgetVal())) == 0) {
            return 100;
        }
        return parsePercent / 1000;
    }

    public void setTextHighlightColor(String str) {
        CTRPr runProperties = getRunProperties(true);
        CTHighlight highlightArray = runProperties.sizeOfHighlightArray() > 0 ? runProperties.getHighlightArray(0) : runProperties.addNewHighlight();
        STHighlightColor xgetVal = highlightArray.xgetVal();
        if (xgetVal == null) {
            xgetVal = STHighlightColor.Factory.newInstance();
        }
        STHighlightColor.Enum forString = STHighlightColor.Enum.forString(str);
        if (forString != null) {
            xgetVal.setStringValue(forString.toString());
            highlightArray.xsetVal(xgetVal);
        }
    }

    public STHighlightColor.Enum getTextHightlightColor() {
        CTRPr runProperties = getRunProperties(true);
        STHighlightColor xgetVal = (runProperties.sizeOfHighlightArray() > 0 ? runProperties.getHighlightArray(0) : runProperties.addNewHighlight()).xgetVal();
        if (xgetVal == null) {
            xgetVal = STHighlightColor.Factory.newInstance();
            xgetVal.setEnumValue(STHighlightColor.NONE);
        }
        STHighlightColor.Enum enumR = (STHighlightColor.Enum) xgetVal.getEnumValue();
        STHighlightColor.Enum enumR2 = enumR;
        return enumR;
    }

    public boolean isVanish() {
        CTRPr runProperties = getRunProperties(true);
        if (runProperties == null || runProperties.sizeOfVanishArray() <= 0 || !isCTOnOff(runProperties.getVanishArray(0))) {
            return false;
        }
        return true;
    }

    public void setVanish(boolean z) {
        CTRPr runProperties = getRunProperties(true);
        (runProperties.sizeOfVanishArray() > 0 ? runProperties.getVanishArray(0) : runProperties.addNewVanish()).setVal(z ? STOnOff1.ON : STOnOff1.OFF);
    }

    public STVerticalAlignRun.Enum getVerticalAlignment() {
        CTRPr runProperties = getRunProperties(true);
        STVerticalAlignRun.Enum val = (runProperties.sizeOfVertAlignArray() > 0 ? runProperties.getVertAlignArray(0) : runProperties.addNewVertAlign()).getVal();
        return val == null ? STVerticalAlignRun.BASELINE : val;
    }

    public void setVerticalAlignment(String str) {
        CTRPr runProperties = getRunProperties(true);
        CTVerticalAlignRun vertAlignArray = runProperties.sizeOfVertAlignArray() > 0 ? runProperties.getVertAlignArray(0) : runProperties.addNewVertAlign();
        STVerticalAlignRun xgetVal = vertAlignArray.xgetVal();
        if (xgetVal == null) {
            xgetVal = STVerticalAlignRun.Factory.newInstance();
        }
        STVerticalAlignRun.Enum forString = STVerticalAlignRun.Enum.forString(str);
        if (forString != null) {
            xgetVal.setStringValue(forString.toString());
            vertAlignArray.xsetVal(xgetVal);
        }
    }

    public STEm.Enum getEmphasisMark() {
        CTRPr runProperties = getRunProperties(true);
        STEm.Enum val = (runProperties.sizeOfEmArray() > 0 ? runProperties.getEmArray(0) : runProperties.addNewEm()).getVal();
        return val == null ? STEm.NONE : val;
    }

    public void setEmphasisMark(String str) {
        CTRPr runProperties = getRunProperties(true);
        CTEm emArray = runProperties.sizeOfEmArray() > 0 ? runProperties.getEmArray(0) : runProperties.addNewEm();
        STEm xgetVal = emArray.xgetVal();
        if (xgetVal == null) {
            xgetVal = STEm.Factory.newInstance();
        }
        STEm.Enum forString = STEm.Enum.forString(str);
        if (forString != null) {
            xgetVal.setStringValue(forString.toString());
            emArray.xsetVal(xgetVal);
        }
    }

    /* access modifiers changed from: protected */
    public CTRPr getRunProperties(boolean z) {
        CTRPr rPr = this.run.isSetRPr() ? this.run.getRPr() : null;
        return (!z || rPr != null) ? rPr : this.run.addNewRPr();
    }
}
