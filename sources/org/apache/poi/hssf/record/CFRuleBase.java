package org.apache.poi.hssf.record;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.cf.BorderFormatting;
import org.apache.poi.hssf.record.cf.FontFormatting;
import org.apache.poi.hssf.record.cf.PatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public abstract class CFRuleBase extends StandardRecord {
    public static final byte CONDITION_TYPE_CELL_VALUE_IS = 1;
    public static final byte CONDITION_TYPE_COLOR_SCALE = 3;
    public static final byte CONDITION_TYPE_DATA_BAR = 4;
    public static final byte CONDITION_TYPE_FILTER = 5;
    public static final byte CONDITION_TYPE_FORMULA = 2;
    public static final byte CONDITION_TYPE_ICON_SET = 6;
    protected static final Logger LOG = LogManager.getLogger((Class<?>) CFRuleBase.class);
    public static final int TEMPLATE_ABOVE_AVERAGE = 25;
    public static final int TEMPLATE_ABOVE_OR_EQUAL_TO_AVERAGE = 29;
    public static final int TEMPLATE_BELOW_AVERAGE = 26;
    public static final int TEMPLATE_BELOW_OR_EQUAL_TO_AVERAGE = 30;
    public static final int TEMPLATE_CELL_VALUE = 0;
    public static final int TEMPLATE_COLOR_SCALE_FORMATTING = 2;
    public static final int TEMPLATE_CONTAINS_BLANKS = 9;
    public static final int TEMPLATE_CONTAINS_ERRORS = 11;
    public static final int TEMPLATE_CONTAINS_NO_BLANKS = 10;
    public static final int TEMPLATE_CONTAINS_NO_ERRORS = 12;
    public static final int TEMPLATE_CONTAINS_TEXT = 8;
    public static final int TEMPLATE_DATA_BAR_FORMATTING = 3;
    public static final int TEMPLATE_DUPLICATE_VALUES = 27;
    public static final int TEMPLATE_FILTER = 5;
    public static final int TEMPLATE_FORMULA = 1;
    public static final int TEMPLATE_ICON_SET_FORMATTING = 4;
    public static final int TEMPLATE_LAST_7_DAYS = 18;
    public static final int TEMPLATE_LAST_MONTH = 19;
    public static final int TEMPLATE_LAST_WEEK = 23;
    public static final int TEMPLATE_NEXT_MONTH = 20;
    public static final int TEMPLATE_NEXT_WEEK = 22;
    public static final int TEMPLATE_THIS_MONTH = 24;
    public static final int TEMPLATE_THIS_WEEK = 21;
    public static final int TEMPLATE_TODAY = 15;
    public static final int TEMPLATE_TOMORROW = 16;
    public static final int TEMPLATE_UNIQUE_VALUES = 7;
    public static final int TEMPLATE_YESTERDAY = 17;
    static final BitField align = bf(134217728);
    static final BitField alignHor = bf(1);
    static final BitField alignIndent = bf(32);
    static final BitField alignJustLast = bf(16);
    static final BitField alignRot = bf(8);
    static final BitField alignShrin = bf(64);
    static final BitField alignTextDir = bf(Integer.MIN_VALUE);
    static final BitField alignVer = bf(2);
    static final BitField alignWrap = bf(4);
    static final BitField bord = bf(268435456);
    static final BitField bordBlTr = bf(32768);
    static final BitField bordBot = bf(8192);
    static final BitField bordLeft = bf(1024);
    static final BitField bordRight = bf(2048);
    static final BitField bordTlBr = bf(16384);
    static final BitField bordTop = bf(4096);
    static final BitField fmtBlockBits = bf(2080374784);
    static final BitField font = bf(67108864);
    static final BitField mergeCell = bf(128);
    static final BitField modificationBits = bf(4194303);
    static final BitField notUsed2 = bf(3670016);
    static final BitField patt = bf(536870912);
    static final BitField pattBgCol = bf(262144);
    static final BitField pattCol = bf(131072);
    static final BitField pattStyle = bf(65536);
    static final BitField prot = bf(BasicMeasure.EXACTLY);
    static final BitField protHidden = bf(512);
    static final BitField protLocked = bf(256);
    static final BitField undocumented = bf(62914560);
    protected BorderFormatting _borderFormatting;
    protected FontFormatting _fontFormatting;
    protected PatternFormatting _patternFormatting;
    private byte comparison_operator;
    private byte condition_type;
    protected short formatting_not_used;
    protected int formatting_options;
    private Formula formula1;
    private Formula formula2;

    public interface ComparisonOperator {
        public static final byte BETWEEN = 1;
        public static final byte EQUAL = 3;
        public static final byte GE = 7;
        public static final byte GT = 5;
        public static final byte LE = 8;
        public static final byte LT = 6;
        public static final byte NOT_BETWEEN = 2;
        public static final byte NOT_EQUAL = 4;
        public static final byte NO_COMPARISON = 0;
        public static final byte max_operator = 8;
    }

    public abstract CFRuleBase copy();

    private static BitField bf(int i) {
        return BitFieldFactory.getInstance(i);
    }

    protected CFRuleBase(byte b, byte b2) {
        setConditionType(b);
        setComparisonOperation(b2);
        this.formula1 = Formula.create(Ptg.EMPTY_PTG_ARRAY);
        this.formula2 = Formula.create(Ptg.EMPTY_PTG_ARRAY);
    }

    protected CFRuleBase(byte b, byte b2, Ptg[] ptgArr, Ptg[] ptgArr2) {
        this(b, b2);
        this.formula1 = Formula.create(ptgArr);
        this.formula2 = Formula.create(ptgArr2);
    }

    protected CFRuleBase() {
    }

    protected CFRuleBase(CFRuleBase cFRuleBase) {
        super(cFRuleBase);
        setConditionType(cFRuleBase.getConditionType());
        setComparisonOperation(cFRuleBase.getComparisonOperation());
        this.formatting_options = cFRuleBase.formatting_options;
        this.formatting_not_used = cFRuleBase.formatting_not_used;
        PatternFormatting patternFormatting = null;
        this._fontFormatting = !cFRuleBase.containsFontFormattingBlock() ? null : cFRuleBase.getFontFormatting().copy();
        this._borderFormatting = !cFRuleBase.containsBorderFormattingBlock() ? null : cFRuleBase.getBorderFormatting().copy();
        this._patternFormatting = cFRuleBase.containsPatternFormattingBlock() ? cFRuleBase.getPatternFormatting().copy() : patternFormatting;
        this.formula1 = cFRuleBase.getFormula1().copy();
        this.formula2 = cFRuleBase.getFormula2().copy();
    }

    /* access modifiers changed from: protected */
    public int readFormatOptions(RecordInputStream recordInputStream) {
        this.formatting_options = recordInputStream.readInt();
        this.formatting_not_used = recordInputStream.readShort();
        int i = 6;
        if (containsFontFormattingBlock()) {
            FontFormatting fontFormatting = new FontFormatting(recordInputStream);
            this._fontFormatting = fontFormatting;
            i = 6 + fontFormatting.getDataLength();
        }
        if (containsBorderFormattingBlock()) {
            BorderFormatting borderFormatting = new BorderFormatting((LittleEndianInput) recordInputStream);
            this._borderFormatting = borderFormatting;
            i += borderFormatting.getDataLength();
        }
        if (!containsPatternFormattingBlock()) {
            return i;
        }
        PatternFormatting patternFormatting = new PatternFormatting((LittleEndianInput) recordInputStream);
        this._patternFormatting = patternFormatting;
        return i + patternFormatting.getDataLength();
    }

    public byte getConditionType() {
        return this.condition_type;
    }

    /* access modifiers changed from: protected */
    public void setConditionType(byte b) {
        if (!(this instanceof CFRuleRecord) || b == 1 || b == 2) {
            this.condition_type = b;
            return;
        }
        throw new IllegalArgumentException("CFRuleRecord only accepts Value-Is and Formula types");
    }

    public void setComparisonOperation(byte b) {
        if (b < 0 || b > 8) {
            throw new IllegalArgumentException("Valid operators are only in the range 0 to 8");
        }
        this.comparison_operator = b;
    }

    public byte getComparisonOperation() {
        return this.comparison_operator;
    }

    public boolean containsFontFormattingBlock() {
        return getOptionFlag(font);
    }

    public void setFontFormatting(FontFormatting fontFormatting) {
        this._fontFormatting = fontFormatting;
        setOptionFlag(fontFormatting != null, font);
    }

    public FontFormatting getFontFormatting() {
        if (containsFontFormattingBlock()) {
            return this._fontFormatting;
        }
        return null;
    }

    public boolean containsAlignFormattingBlock() {
        return getOptionFlag(align);
    }

    public void setAlignFormattingUnchanged() {
        setOptionFlag(false, align);
    }

    public boolean containsBorderFormattingBlock() {
        return getOptionFlag(bord);
    }

    public void setBorderFormatting(BorderFormatting borderFormatting) {
        this._borderFormatting = borderFormatting;
        setOptionFlag(borderFormatting != null, bord);
    }

    public BorderFormatting getBorderFormatting() {
        if (containsBorderFormattingBlock()) {
            return this._borderFormatting;
        }
        return null;
    }

    public boolean containsPatternFormattingBlock() {
        return getOptionFlag(patt);
    }

    public void setPatternFormatting(PatternFormatting patternFormatting) {
        this._patternFormatting = patternFormatting;
        setOptionFlag(patternFormatting != null, patt);
    }

    public PatternFormatting getPatternFormatting() {
        if (containsPatternFormattingBlock()) {
            return this._patternFormatting;
        }
        return null;
    }

    public boolean containsProtectionFormattingBlock() {
        return getOptionFlag(prot);
    }

    public void setProtectionFormattingUnchanged() {
        setOptionFlag(false, prot);
    }

    public int getOptions() {
        return this.formatting_options;
    }

    private boolean isModified(BitField bitField) {
        return !bitField.isSet(this.formatting_options);
    }

    private void setModified(boolean z, BitField bitField) {
        this.formatting_options = bitField.setBoolean(this.formatting_options, !z);
    }

    public boolean isLeftBorderModified() {
        return isModified(bordLeft);
    }

    public void setLeftBorderModified(boolean z) {
        setModified(z, bordLeft);
    }

    public boolean isRightBorderModified() {
        return isModified(bordRight);
    }

    public void setRightBorderModified(boolean z) {
        setModified(z, bordRight);
    }

    public boolean isTopBorderModified() {
        return isModified(bordTop);
    }

    public void setTopBorderModified(boolean z) {
        setModified(z, bordTop);
    }

    public boolean isBottomBorderModified() {
        return isModified(bordBot);
    }

    public void setBottomBorderModified(boolean z) {
        setModified(z, bordBot);
    }

    public boolean isTopLeftBottomRightBorderModified() {
        return isModified(bordTlBr);
    }

    public void setTopLeftBottomRightBorderModified(boolean z) {
        setModified(z, bordTlBr);
    }

    public boolean isBottomLeftTopRightBorderModified() {
        return isModified(bordBlTr);
    }

    public void setBottomLeftTopRightBorderModified(boolean z) {
        setModified(z, bordBlTr);
    }

    public boolean isPatternStyleModified() {
        return isModified(pattStyle);
    }

    public void setPatternStyleModified(boolean z) {
        setModified(z, pattStyle);
    }

    public boolean isPatternColorModified() {
        return isModified(pattCol);
    }

    public void setPatternColorModified(boolean z) {
        setModified(z, pattCol);
    }

    public boolean isPatternBackgroundColorModified() {
        return isModified(pattBgCol);
    }

    public void setPatternBackgroundColorModified(boolean z) {
        setModified(z, pattBgCol);
    }

    private boolean getOptionFlag(BitField bitField) {
        return bitField.isSet(this.formatting_options);
    }

    private void setOptionFlag(boolean z, BitField bitField) {
        this.formatting_options = bitField.setBoolean(this.formatting_options, z);
    }

    /* access modifiers changed from: protected */
    public int getFormattingBlockSize() {
        int i = 0;
        int length = (containsFontFormattingBlock() ? this._fontFormatting.getRawRecord().length : 0) + 6 + (containsBorderFormattingBlock() ? 8 : 0);
        if (containsPatternFormattingBlock()) {
            i = 4;
        }
        return length + i;
    }

    /* access modifiers changed from: protected */
    public void serializeFormattingBlock(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this.formatting_options);
        littleEndianOutput.writeShort(this.formatting_not_used);
        if (containsFontFormattingBlock()) {
            littleEndianOutput.write(this._fontFormatting.getRawRecord());
        }
        if (containsBorderFormattingBlock()) {
            this._borderFormatting.serialize(littleEndianOutput);
        }
        if (containsPatternFormattingBlock()) {
            this._patternFormatting.serialize(littleEndianOutput);
        }
    }

    public Ptg[] getParsedExpression1() {
        return this.formula1.getTokens();
    }

    public void setParsedExpression1(Ptg[] ptgArr) {
        this.formula1 = Formula.create(ptgArr);
    }

    /* access modifiers changed from: protected */
    public Formula getFormula1() {
        return this.formula1;
    }

    /* access modifiers changed from: protected */
    public void setFormula1(Formula formula) {
        this.formula1 = formula;
    }

    public Ptg[] getParsedExpression2() {
        return Formula.getTokens(this.formula2);
    }

    public void setParsedExpression2(Ptg[] ptgArr) {
        this.formula2 = Formula.create(ptgArr);
    }

    /* access modifiers changed from: protected */
    public Formula getFormula2() {
        return this.formula2;
    }

    /* access modifiers changed from: protected */
    public void setFormula2(Formula formula) {
        this.formula2 = formula;
    }

    protected static int getFormulaSize(Formula formula) {
        return formula.getEncodedTokenSize();
    }

    public static Ptg[] parseFormula(String str, HSSFSheet hSSFSheet) {
        if (str == null) {
            return null;
        }
        return HSSFFormulaParser.parse(str, hSSFSheet.getWorkbook(), FormulaType.CELL, hSSFSheet.getWorkbook().getSheetIndex((Sheet) hSSFSheet));
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        CFRuleBase$$ExternalSyntheticLambda0 cFRuleBase$$ExternalSyntheticLambda0 = r3;
        CFRuleBase$$ExternalSyntheticLambda0 cFRuleBase$$ExternalSyntheticLambda02 = new CFRuleBase$$ExternalSyntheticLambda0(this);
        CFRuleBase$$ExternalSyntheticLambda1 cFRuleBase$$ExternalSyntheticLambda1 = r5;
        CFRuleBase$$ExternalSyntheticLambda1 cFRuleBase$$ExternalSyntheticLambda12 = new CFRuleBase$$ExternalSyntheticLambda1(this);
        CFRuleBase$$ExternalSyntheticLambda2 cFRuleBase$$ExternalSyntheticLambda2 = r7;
        CFRuleBase$$ExternalSyntheticLambda2 cFRuleBase$$ExternalSyntheticLambda22 = new CFRuleBase$$ExternalSyntheticLambda2(this);
        CFRuleBase$$ExternalSyntheticLambda3 cFRuleBase$$ExternalSyntheticLambda3 = r9;
        CFRuleBase$$ExternalSyntheticLambda3 cFRuleBase$$ExternalSyntheticLambda32 = new CFRuleBase$$ExternalSyntheticLambda3(this);
        CFRuleBase$$ExternalSyntheticLambda4 cFRuleBase$$ExternalSyntheticLambda4 = r11;
        CFRuleBase$$ExternalSyntheticLambda4 cFRuleBase$$ExternalSyntheticLambda42 = new CFRuleBase$$ExternalSyntheticLambda4(this);
        CFRuleBase$$ExternalSyntheticLambda5 cFRuleBase$$ExternalSyntheticLambda5 = r13;
        CFRuleBase$$ExternalSyntheticLambda5 cFRuleBase$$ExternalSyntheticLambda52 = new CFRuleBase$$ExternalSyntheticLambda5(this);
        CFRuleBase$$ExternalSyntheticLambda6 cFRuleBase$$ExternalSyntheticLambda6 = r15;
        CFRuleBase$$ExternalSyntheticLambda6 cFRuleBase$$ExternalSyntheticLambda62 = new CFRuleBase$$ExternalSyntheticLambda6(this);
        CFRuleBase$$ExternalSyntheticLambda7 cFRuleBase$$ExternalSyntheticLambda7 = r1;
        CFRuleBase$$ExternalSyntheticLambda7 cFRuleBase$$ExternalSyntheticLambda72 = new CFRuleBase$$ExternalSyntheticLambda7(this);
        CFRuleBase$$ExternalSyntheticLambda8 cFRuleBase$$ExternalSyntheticLambda8 = r1;
        CFRuleBase$$ExternalSyntheticLambda8 cFRuleBase$$ExternalSyntheticLambda82 = new CFRuleBase$$ExternalSyntheticLambda8(this);
        return GenericRecordUtil.getGenericProperties("conditionType", cFRuleBase$$ExternalSyntheticLambda0, "comparisonOperation", cFRuleBase$$ExternalSyntheticLambda1, "formattingOptions", cFRuleBase$$ExternalSyntheticLambda2, "formattingNotUsed", cFRuleBase$$ExternalSyntheticLambda3, "fontFormatting", cFRuleBase$$ExternalSyntheticLambda4, "borderFormatting", cFRuleBase$$ExternalSyntheticLambda5, "patternFormatting", cFRuleBase$$ExternalSyntheticLambda6, "formula1", cFRuleBase$$ExternalSyntheticLambda7, "formula2", cFRuleBase$$ExternalSyntheticLambda8);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-CFRuleBase  reason: not valid java name */
    public /* synthetic */ Object m1980lambda$getGenericProperties$0$orgapachepoihssfrecordCFRuleBase() {
        return Short.valueOf(this.formatting_not_used);
    }
}
