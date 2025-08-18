package org.apache.poi.xssf.usermodel;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Predicate;
import kotlin.text.Typography;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.SimpleShape;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFColorRgbBinary;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVertOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextWrappingType;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShapeNonVisual;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRPrElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnderlineValues;

public class XSSFSimpleShape extends XSSFShape implements Iterable<XSSFTextParagraph>, SimpleShape, TextContainer {
    private static int[] _romanAlphaValues = {1000, TypedValues.Custom.TYPE_INT, 500, FontHeader.REGULAR_WEIGHT, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] _romanChars = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "IV", "I"};
    private static CTShape prototype;
    private final List<XSSFTextParagraph> _paragraphs = new ArrayList();
    private final XDDFTextBody _textBody;
    private CTShape ctShape;

    protected XSSFSimpleShape(XSSFDrawing xSSFDrawing, CTShape cTShape) {
        this.drawing = xSSFDrawing;
        this.ctShape = cTShape;
        CTTextBody txBody = cTShape.getTxBody();
        if (txBody == null) {
            this._textBody = null;
            return;
        }
        this._textBody = new XDDFTextBody(this, txBody);
        for (int i = 0; i < txBody.sizeOfPArray(); i++) {
            this._paragraphs.add(new XSSFTextParagraph(txBody.getPArray(i), cTShape));
        }
    }

    protected static CTShape prototype() {
        if (prototype == null) {
            CTShape newInstance = CTShape.Factory.newInstance();
            CTShapeNonVisual addNewNvSpPr = newInstance.addNewNvSpPr();
            CTNonVisualDrawingProps addNewCNvPr = addNewNvSpPr.addNewCNvPr();
            addNewCNvPr.setId(1);
            addNewCNvPr.setName("Shape 1");
            addNewNvSpPr.addNewCNvSpPr();
            CTShapeProperties addNewSpPr = newInstance.addNewSpPr();
            CTTransform2D addNewXfrm = addNewSpPr.addNewXfrm();
            CTPositiveSize2D addNewExt = addNewXfrm.addNewExt();
            addNewExt.setCx(0);
            addNewExt.setCy(0);
            CTPoint2D addNewOff = addNewXfrm.addNewOff();
            addNewOff.setX(0);
            addNewOff.setY(0);
            CTPresetGeometry2D addNewPrstGeom = addNewSpPr.addNewPrstGeom();
            addNewPrstGeom.setPrst(STShapeType.RECT);
            addNewPrstGeom.addNewAvLst();
            new XDDFTextBody((TextContainer) null, newInstance.addNewTxBody()).initialize().getAfterLastRunProperties().setFillProperties(new XDDFSolidFillProperties((XDDFColor) new XDDFColorRgbBinary(new byte[]{0, 0, 0})));
            prototype = newInstance;
        }
        return prototype;
    }

    @Internal
    public CTShape getCTShape() {
        return this.ctShape;
    }

    public XDDFTextBody getTextBody() {
        return this._textBody;
    }

    /* access modifiers changed from: protected */
    public void setXfrm(CTTransform2D cTTransform2D) {
        this.ctShape.getSpPr().setXfrm(cTTransform2D);
    }

    public Iterator<XSSFTextParagraph> iterator() {
        return this._paragraphs.iterator();
    }

    public Spliterator<XSSFTextParagraph> spliterator() {
        return this._paragraphs.spliterator();
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList(9);
        for (int i = 0; i < 9; i++) {
            arrayList.add(0);
        }
        int i2 = 0;
        while (i2 < this._paragraphs.size()) {
            if (sb.length() > 0) {
                sb.append(10);
            }
            XSSFTextParagraph xSSFTextParagraph = this._paragraphs.get(i2);
            if (!xSSFTextParagraph.isBullet() || xSSFTextParagraph.getText().length() <= 0) {
                sb.append(xSSFTextParagraph.getText());
                for (int i3 = 0; i3 < 9; i3++) {
                    arrayList.set(i3, 0);
                }
            } else {
                int min = Math.min(xSSFTextParagraph.getLevel(), 8);
                if (xSSFTextParagraph.isBulletAutoNumber()) {
                    i2 = processAutoNumGroup(i2, min, arrayList, sb);
                } else {
                    for (int i4 = 0; i4 < min; i4++) {
                        sb.append(9);
                    }
                    String bulletCharacter = xSSFTextParagraph.getBulletCharacter();
                    sb.append(bulletCharacter.length() > 0 ? bulletCharacter + " " : "- ");
                    sb.append(xSSFTextParagraph.getText());
                }
            }
            i2++;
        }
        return sb.toString();
    }

    private int processAutoNumGroup(int i, int i2, List<Integer> list, StringBuilder sb) {
        XSSFTextParagraph xSSFTextParagraph = this._paragraphs.get(i);
        int bulletAutoNumberStart = xSSFTextParagraph.getBulletAutoNumberStart();
        ListAutoNumber bulletAutoNumberScheme = xSSFTextParagraph.getBulletAutoNumberScheme();
        if (list.get(i2).intValue() == 0) {
            list.set(i2, Integer.valueOf(bulletAutoNumberStart == 0 ? 1 : bulletAutoNumberStart));
        }
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(9);
        }
        if (xSSFTextParagraph.getText().length() > 0) {
            sb.append(getBulletPrefix(bulletAutoNumberScheme, list.get(i2).intValue()));
            sb.append(xSSFTextParagraph.getText());
        }
        while (true) {
            int i4 = i + 1;
            XSSFTextParagraph xSSFTextParagraph2 = i4 == this._paragraphs.size() ? null : this._paragraphs.get(i4);
            if (xSSFTextParagraph2 != null && xSSFTextParagraph2.isBullet() && xSSFTextParagraph.isBulletAutoNumber()) {
                if (xSSFTextParagraph2.getLevel() <= i2) {
                    if (xSSFTextParagraph2.getLevel() >= i2) {
                        ListAutoNumber bulletAutoNumberScheme2 = xSSFTextParagraph2.getBulletAutoNumberScheme();
                        int bulletAutoNumberStart2 = xSSFTextParagraph2.getBulletAutoNumberStart();
                        if (bulletAutoNumberScheme2 != bulletAutoNumberScheme || bulletAutoNumberStart2 != bulletAutoNumberStart) {
                            break;
                        }
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        for (int i5 = 0; i5 < i2; i5++) {
                            sb.append(9);
                        }
                        if (xSSFTextParagraph2.getText().length() > 0) {
                            list.set(i2, Integer.valueOf(list.get(i2).intValue() + 1));
                            sb.append(getBulletPrefix(bulletAutoNumberScheme2, list.get(i2).intValue()));
                            sb.append(xSSFTextParagraph2.getText());
                        }
                        i = i4;
                    } else {
                        break;
                    }
                } else {
                    if (sb.length() > 0) {
                        sb.append(10);
                    }
                    i = processAutoNumGroup(i4, xSSFTextParagraph2.getLevel(), list, sb);
                }
            } else {
                break;
            }
        }
        list.set(i2, 0);
        return i;
    }

    private String getBulletPrefix(ListAutoNumber listAutoNumber, int i) {
        StringBuilder sb = new StringBuilder();
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber[listAutoNumber.ordinal()]) {
            case 1:
            case 2:
                if (listAutoNumber == ListAutoNumber.ALPHA_LC_PARENT_BOTH) {
                    sb.append('(');
                }
                sb.append(valueToAlpha(i).toLowerCase(Locale.ROOT));
                sb.append(')');
                break;
            case 3:
            case 4:
                if (listAutoNumber == ListAutoNumber.ALPHA_UC_PARENT_BOTH) {
                    sb.append('(');
                }
                sb.append(valueToAlpha(i));
                sb.append(')');
                break;
            case 5:
                sb.append(valueToAlpha(i).toLowerCase(Locale.ROOT));
                sb.append('.');
                break;
            case 6:
                sb.append(valueToAlpha(i));
                sb.append('.');
                break;
            case 7:
            case 8:
                if (listAutoNumber == ListAutoNumber.ARABIC_PARENT_BOTH) {
                    sb.append('(');
                }
                sb.append(i);
                sb.append(')');
                break;
            case 9:
                sb.append(i);
                sb.append('.');
                break;
            case 10:
                sb.append(i);
                break;
            case 11:
            case 12:
                if (listAutoNumber == ListAutoNumber.ROMAN_LC_PARENT_BOTH) {
                    sb.append('(');
                }
                sb.append(valueToRoman(i).toLowerCase(Locale.ROOT));
                sb.append(')');
                break;
            case 13:
            case 14:
                if (listAutoNumber == ListAutoNumber.ROMAN_UC_PARENT_BOTH) {
                    sb.append('(');
                }
                sb.append(valueToRoman(i));
                sb.append(')');
                break;
            case 15:
                sb.append(valueToRoman(i).toLowerCase(Locale.ROOT));
                sb.append('.');
                break;
            case 16:
                sb.append(valueToRoman(i));
                sb.append('.');
                break;
            default:
                sb.append(Typography.bullet);
                break;
        }
        sb.append(" ");
        return sb.toString();
    }

    private String valueToAlpha(int i) {
        String str = "";
        while (i > 0) {
            int i2 = (i - 1) % 26;
            str = ((char) (i2 + 65)) + str;
            i = (i - i2) / 26;
        }
        return str;
    }

    private String valueToRoman(int i) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i > 0 && i2 < _romanChars.length) {
            while (_romanAlphaValues[i2] <= i) {
                sb.append(_romanChars[i2]);
                i -= _romanAlphaValues[i2];
            }
            i2++;
        }
        return sb.toString();
    }

    public void clearText() {
        this._paragraphs.clear();
        this.ctShape.getTxBody().setPArray((CTTextParagraph[]) null);
    }

    public void setText(String str) {
        clearText();
        addNewTextParagraph().addNewTextRun().setText(str);
    }

    public void setText(XSSFRichTextString xSSFRichTextString) {
        xSSFRichTextString.setStylesTableReference(((XSSFWorkbook) getDrawing().getParent().getParent()).getStylesSource());
        CTTextParagraph newInstance = CTTextParagraph.Factory.newInstance();
        if (xSSFRichTextString.numFormattingRuns() == 0) {
            CTRegularTextRun addNewR = newInstance.addNewR();
            CTTextCharacterProperties addNewRPr = addNewR.addNewRPr();
            addNewRPr.setLang("en-US");
            addNewRPr.setSz(1100);
            addNewR.setT(xSSFRichTextString.getString());
        } else {
            for (int i = 0; i < xSSFRichTextString.getCTRst().sizeOfRArray(); i++) {
                CTRElt rArray = xSSFRichTextString.getCTRst().getRArray(i);
                CTRPrElt rPr = rArray.getRPr();
                if (rPr == null) {
                    rPr = rArray.addNewRPr();
                }
                CTRegularTextRun addNewR2 = newInstance.addNewR();
                CTTextCharacterProperties addNewRPr2 = addNewR2.addNewRPr();
                addNewRPr2.setLang("en-US");
                applyAttributes(rPr, addNewRPr2);
                addNewR2.setT(rArray.getT());
            }
        }
        clearText();
        this.ctShape.getTxBody().setPArray(new CTTextParagraph[]{newInstance});
        this._paragraphs.add(new XSSFTextParagraph(this.ctShape.getTxBody().getPArray(0), this.ctShape));
    }

    public List<XSSFTextParagraph> getTextParagraphs() {
        return this._paragraphs;
    }

    public XSSFTextParagraph addNewTextParagraph() {
        XSSFTextParagraph xSSFTextParagraph = new XSSFTextParagraph(this.ctShape.getTxBody().addNewP(), this.ctShape);
        this._paragraphs.add(xSSFTextParagraph);
        return xSSFTextParagraph;
    }

    public XSSFTextParagraph addNewTextParagraph(String str) {
        XSSFTextParagraph addNewTextParagraph = addNewTextParagraph();
        addNewTextParagraph.addNewTextRun().setText(str);
        return addNewTextParagraph;
    }

    public XSSFTextParagraph addNewTextParagraph(XSSFRichTextString xSSFRichTextString) {
        CTTextParagraph addNewP = this.ctShape.getTxBody().addNewP();
        if (xSSFRichTextString.numFormattingRuns() == 0) {
            CTRegularTextRun addNewR = addNewP.addNewR();
            CTTextCharacterProperties addNewRPr = addNewR.addNewRPr();
            addNewRPr.setLang("en-US");
            addNewRPr.setSz(1100);
            addNewR.setT(xSSFRichTextString.getString());
        } else {
            for (int i = 0; i < xSSFRichTextString.getCTRst().sizeOfRArray(); i++) {
                CTRElt rArray = xSSFRichTextString.getCTRst().getRArray(i);
                CTRPrElt rPr = rArray.getRPr();
                if (rPr == null) {
                    rPr = rArray.addNewRPr();
                }
                CTRegularTextRun addNewR2 = addNewP.addNewR();
                CTTextCharacterProperties addNewRPr2 = addNewR2.addNewRPr();
                addNewRPr2.setLang("en-US");
                applyAttributes(rPr, addNewRPr2);
                addNewR2.setT(rArray.getT());
            }
        }
        XSSFTextParagraph xSSFTextParagraph = new XSSFTextParagraph(addNewP, this.ctShape);
        this._paragraphs.add(xSSFTextParagraph);
        return xSSFTextParagraph;
    }

    public void setTextHorizontalOverflow(TextHorizontalOverflow textHorizontalOverflow) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr == null) {
            return;
        }
        if (textHorizontalOverflow != null) {
            bodyPr.setHorzOverflow(STTextHorzOverflowType.Enum.forInt(textHorizontalOverflow.ordinal() + 1));
        } else if (bodyPr.isSetHorzOverflow()) {
            bodyPr.unsetHorzOverflow();
        }
    }

    public TextHorizontalOverflow getTextHorizontalOverflow() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr == null || !bodyPr.isSetHorzOverflow()) {
            return TextHorizontalOverflow.OVERFLOW;
        }
        return TextHorizontalOverflow.values()[bodyPr.getHorzOverflow().intValue() - 1];
    }

    public void setTextVerticalOverflow(TextVerticalOverflow textVerticalOverflow) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr == null) {
            return;
        }
        if (textVerticalOverflow != null) {
            bodyPr.setVertOverflow(STTextVertOverflowType.Enum.forInt(textVerticalOverflow.ordinal() + 1));
        } else if (bodyPr.isSetVertOverflow()) {
            bodyPr.unsetVertOverflow();
        }
    }

    public TextVerticalOverflow getTextVerticalOverflow() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr == null || !bodyPr.isSetVertOverflow()) {
            return TextVerticalOverflow.OVERFLOW;
        }
        return TextVerticalOverflow.values()[bodyPr.getVertOverflow().intValue() - 1];
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr == null) {
            return;
        }
        if (verticalAlignment != null) {
            bodyPr.setAnchor(STTextAnchoringType.Enum.forInt(verticalAlignment.ordinal() + 1));
        } else if (bodyPr.isSetAnchor()) {
            bodyPr.unsetAnchor();
        }
    }

    public VerticalAlignment getVerticalAlignment() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr == null || !bodyPr.isSetAnchor()) {
            return VerticalAlignment.TOP;
        }
        return VerticalAlignment.values()[bodyPr.getAnchor().intValue() - 1];
    }

    public void setTextDirection(TextDirection textDirection) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr == null) {
            return;
        }
        if (textDirection != null) {
            bodyPr.setVert(STTextVerticalType.Enum.forInt(textDirection.ordinal() + 1));
        } else if (bodyPr.isSetVert()) {
            bodyPr.unsetVert();
        }
    }

    public TextDirection getTextDirection() {
        STTextVerticalType.Enum vert;
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr == null || (vert = bodyPr.getVert()) == null) {
            return TextDirection.HORIZONTAL;
        }
        return TextDirection.values()[vert.intValue() - 1];
    }

    public double getBottomInset() {
        Double bottomInset = this._textBody.getBodyProperties().getBottomInset();
        if (bottomInset == null) {
            return 3.6d;
        }
        return bottomInset.doubleValue();
    }

    public double getLeftInset() {
        Double leftInset = this._textBody.getBodyProperties().getLeftInset();
        if (leftInset == null) {
            return 3.6d;
        }
        return leftInset.doubleValue();
    }

    public double getRightInset() {
        Double rightInset = this._textBody.getBodyProperties().getRightInset();
        if (rightInset == null) {
            return 3.6d;
        }
        return rightInset.doubleValue();
    }

    public double getTopInset() {
        Double topInset = this._textBody.getBodyProperties().getTopInset();
        if (topInset == null) {
            return 3.6d;
        }
        return topInset.doubleValue();
    }

    public void setBottomInset(double d) {
        if (d == -1.0d) {
            this._textBody.getBodyProperties().setBottomInset((Double) null);
        } else {
            this._textBody.getBodyProperties().setBottomInset(Double.valueOf(d));
        }
    }

    public void setLeftInset(double d) {
        if (d == -1.0d) {
            this._textBody.getBodyProperties().setLeftInset((Double) null);
        } else {
            this._textBody.getBodyProperties().setLeftInset(Double.valueOf(d));
        }
    }

    public void setRightInset(double d) {
        if (d == -1.0d) {
            this._textBody.getBodyProperties().setRightInset((Double) null);
        } else {
            this._textBody.getBodyProperties().setRightInset(Double.valueOf(d));
        }
    }

    public void setTopInset(double d) {
        if (d == -1.0d) {
            this._textBody.getBodyProperties().setTopInset((Double) null);
        } else {
            this._textBody.getBodyProperties().setTopInset(Double.valueOf(d));
        }
    }

    public boolean getWordWrap() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr == null || !bodyPr.isSetWrap() || bodyPr.getWrap() == STTextWrappingType.SQUARE) {
            return true;
        }
        return false;
    }

    public void setWordWrap(boolean z) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            bodyPr.setWrap(z ? STTextWrappingType.SQUARE : STTextWrappingType.NONE);
        }
    }

    public void setTextAutofit(TextAutofit textAutofit) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (bodyPr.isSetSpAutoFit()) {
                bodyPr.unsetSpAutoFit();
            }
            if (bodyPr.isSetNoAutofit()) {
                bodyPr.unsetNoAutofit();
            }
            if (bodyPr.isSetNormAutofit()) {
                bodyPr.unsetNormAutofit();
            }
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$xssf$usermodel$TextAutofit[textAutofit.ordinal()];
            if (i == 1) {
                bodyPr.addNewNoAutofit();
            } else if (i == 2) {
                bodyPr.addNewNormAutofit();
            } else if (i == 3) {
                bodyPr.addNewSpAutoFit();
            }
        }
    }

    /* renamed from: org.apache.poi.xssf.usermodel.XSSFSimpleShape$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$usermodel$TextAutofit;

        /* JADX WARNING: Can't wrap try/catch for region: R(39:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|(3:43|44|46)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(41:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Can't wrap try/catch for region: R(42:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Can't wrap try/catch for region: R(43:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0063 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0079 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0091 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x009d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00a9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00b5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00c1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00cd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00d9 */
        static {
            /*
                org.apache.poi.xssf.usermodel.TextAutofit[] r0 = org.apache.poi.xssf.usermodel.TextAutofit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xssf$usermodel$TextAutofit = r0
                r1 = 1
                org.apache.poi.xssf.usermodel.TextAutofit r2 = org.apache.poi.xssf.usermodel.TextAutofit.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$poi$xssf$usermodel$TextAutofit     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xssf.usermodel.TextAutofit r3 = org.apache.poi.xssf.usermodel.TextAutofit.NORMAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$poi$xssf$usermodel$TextAutofit     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xssf.usermodel.TextAutofit r4 = org.apache.poi.xssf.usermodel.TextAutofit.SHAPE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                org.apache.poi.xssf.usermodel.ListAutoNumber[] r3 = org.apache.poi.xssf.usermodel.ListAutoNumber.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber = r3
                org.apache.poi.xssf.usermodel.ListAutoNumber r4 = org.apache.poi.xssf.usermodel.ListAutoNumber.ALPHA_LC_PARENT_BOTH     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x0043 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r3 = org.apache.poi.xssf.usermodel.ListAutoNumber.ALPHA_LC_PARENT_R     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x004d }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ALPHA_UC_PARENT_BOTH     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x0058 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ALPHA_UC_PARENT_R     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x0063 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ALPHA_LC_PERIOD     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x006e }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ALPHA_UC_PERIOD     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x0079 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ARABIC_PARENT_BOTH     // Catch:{ NoSuchFieldError -> 0x0079 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x0085 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ARABIC_PARENT_R     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x0091 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ARABIC_PERIOD     // Catch:{ NoSuchFieldError -> 0x0091 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0091 }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0091 }
            L_0x0091:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x009d }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ARABIC_PLAIN     // Catch:{ NoSuchFieldError -> 0x009d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009d }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009d }
            L_0x009d:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x00a9 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ROMAN_LC_PARENT_BOTH     // Catch:{ NoSuchFieldError -> 0x00a9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a9 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a9 }
            L_0x00a9:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x00b5 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ROMAN_LC_PARENT_R     // Catch:{ NoSuchFieldError -> 0x00b5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b5 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b5 }
            L_0x00b5:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x00c1 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ROMAN_UC_PARENT_BOTH     // Catch:{ NoSuchFieldError -> 0x00c1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c1 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c1 }
            L_0x00c1:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x00cd }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ROMAN_UC_PARENT_R     // Catch:{ NoSuchFieldError -> 0x00cd }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cd }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cd }
            L_0x00cd:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x00d9 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ROMAN_LC_PERIOD     // Catch:{ NoSuchFieldError -> 0x00d9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d9 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d9 }
            L_0x00d9:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$usermodel$ListAutoNumber     // Catch:{ NoSuchFieldError -> 0x00e5 }
                org.apache.poi.xssf.usermodel.ListAutoNumber r1 = org.apache.poi.xssf.usermodel.ListAutoNumber.ROMAN_UC_PERIOD     // Catch:{ NoSuchFieldError -> 0x00e5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e5 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e5 }
            L_0x00e5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFSimpleShape.AnonymousClass1.<clinit>():void");
        }
    }

    public TextAutofit getTextAutofit() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (bodyPr.isSetNoAutofit()) {
                return TextAutofit.NONE;
            }
            if (bodyPr.isSetNormAutofit()) {
                return TextAutofit.NORMAL;
            }
            if (bodyPr.isSetSpAutoFit()) {
                return TextAutofit.SHAPE;
            }
        }
        return TextAutofit.NORMAL;
    }

    public int getShapeType() {
        return this.ctShape.getSpPr().getPrstGeom().getPrst().intValue();
    }

    public void setShapeType(int i) {
        this.ctShape.getSpPr().getPrstGeom().setPrst(STShapeType.Enum.forInt(i));
    }

    /* access modifiers changed from: protected */
    public CTShapeProperties getShapeProperties() {
        return this.ctShape.getSpPr();
    }

    private static void applyAttributes(CTRPrElt cTRPrElt, CTTextCharacterProperties cTTextCharacterProperties) {
        HSSFColor hSSFColor;
        if (cTRPrElt.sizeOfBArray() > 0) {
            cTTextCharacterProperties.setB(cTRPrElt.getBArray(0).getVal());
        }
        if (cTRPrElt.sizeOfUArray() > 0) {
            STUnderlineValues.Enum val = cTRPrElt.getUArray(0).getVal();
            if (val == STUnderlineValues.SINGLE) {
                cTTextCharacterProperties.setU(STTextUnderlineType.SNG);
            } else if (val == STUnderlineValues.DOUBLE) {
                cTTextCharacterProperties.setU(STTextUnderlineType.DBL);
            } else if (val == STUnderlineValues.NONE) {
                cTTextCharacterProperties.setU(STTextUnderlineType.NONE);
            }
        }
        if (cTRPrElt.sizeOfIArray() > 0) {
            cTTextCharacterProperties.setI(cTRPrElt.getIArray(0).getVal());
        }
        if (cTRPrElt.sizeOfRFontArray() > 0) {
            (cTTextCharacterProperties.isSetLatin() ? cTTextCharacterProperties.getLatin() : cTTextCharacterProperties.addNewLatin()).setTypeface(cTRPrElt.getRFontArray(0).getVal());
        }
        if (cTRPrElt.sizeOfSzArray() > 0) {
            cTTextCharacterProperties.setSz((int) (cTRPrElt.getSzArray(0).getVal() * 100.0d));
        }
        if (cTRPrElt.sizeOfColorArray() > 0) {
            CTSolidColorFillProperties solidFill = cTTextCharacterProperties.isSetSolidFill() ? cTTextCharacterProperties.getSolidFill() : cTTextCharacterProperties.addNewSolidFill();
            CTColor colorArray = cTRPrElt.getColorArray(0);
            if (colorArray.isSetRgb()) {
                (solidFill.isSetSrgbClr() ? solidFill.getSrgbClr() : solidFill.addNewSrgbClr()).setVal(colorArray.getRgb());
            } else if (colorArray.isSetIndexed() && (hSSFColor = HSSFColor.getIndexHash().get(Integer.valueOf((int) colorArray.getIndexed()))) != null) {
                (solidFill.isSetSrgbClr() ? solidFill.getSrgbClr() : solidFill.addNewSrgbClr()).setVal(new byte[]{(byte) hSSFColor.getTriplet()[0], (byte) hSSFColor.getTriplet()[1], (byte) hSSFColor.getTriplet()[2]});
            }
        }
    }

    public String getShapeName() {
        return this.ctShape.getNvSpPr().getCNvPr().getName();
    }

    public int getShapeId() {
        return (int) this.ctShape.getNvSpPr().getCNvPr().getId();
    }

    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> predicate, Function<CTTextParagraphProperties, R> function) {
        return Optional.empty();
    }

    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> predicate, Function<CTTextCharacterProperties, R> function) {
        return Optional.empty();
    }
}
