package org.apache.poi.hssf.record;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.cf.ColorGradientFormatting;
import org.apache.poi.hssf.record.cf.ColorGradientThreshold;
import org.apache.poi.hssf.record.cf.DataBarFormatting;
import org.apache.poi.hssf.record.cf.DataBarThreshold;
import org.apache.poi.hssf.record.cf.IconMultiStateFormatting;
import org.apache.poi.hssf.record.cf.IconMultiStateThreshold;
import org.apache.poi.hssf.record.cf.Threshold;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.hssf.record.common.FtrHeader;
import org.apache.poi.hssf.record.common.FutureRecord;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class CFRule12Record extends CFRuleBase implements FutureRecord {
    public static final short sid = 2170;
    private ColorGradientFormatting color_gradient;
    private DataBarFormatting data_bar;
    private byte[] ext_formatting_data;
    private int ext_formatting_length;
    private byte ext_opts;
    private byte[] filter_data;
    private Formula formula_scale;
    private FtrHeader futureHeader;
    private IconMultiStateFormatting multistate;
    private int priority;
    private byte template_param_length;
    private byte[] template_params;
    private int template_type;

    public short getSid() {
        return sid;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CFRule12Record(org.apache.poi.hssf.record.CFRule12Record r4) {
        /*
            r3 = this;
            r3.<init>(r4)
            org.apache.poi.hssf.record.common.FtrHeader r0 = r4.futureHeader
            r1 = 0
            if (r0 != 0) goto L_0x000a
            r0 = r1
            goto L_0x000e
        L_0x000a:
            org.apache.poi.hssf.record.common.FtrHeader r0 = r0.copy()
        L_0x000e:
            r3.futureHeader = r0
            int r0 = r4.ext_formatting_length
            byte[] r2 = r4.ext_formatting_data
            int r2 = r2.length
            int r0 = java.lang.Math.min(r0, r2)
            r3.ext_formatting_length = r0
            byte[] r0 = r4.ext_formatting_data
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
            r3.ext_formatting_data = r0
            org.apache.poi.ss.formula.Formula r0 = r4.formula_scale
            org.apache.poi.ss.formula.Formula r0 = r0.copy()
            r3.formula_scale = r0
            byte r0 = r4.ext_opts
            r3.ext_opts = r0
            int r0 = r4.priority
            r3.priority = r0
            int r0 = r4.template_type
            r3.template_type = r0
            byte r0 = r4.template_param_length
            r3.template_param_length = r0
            byte[] r0 = r4.template_params
            if (r0 != 0) goto L_0x0043
            r0 = r1
            goto L_0x0049
        L_0x0043:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x0049:
            r3.template_params = r0
            org.apache.poi.hssf.record.cf.ColorGradientFormatting r0 = r4.color_gradient
            if (r0 != 0) goto L_0x0051
            r0 = r1
            goto L_0x0055
        L_0x0051:
            org.apache.poi.hssf.record.cf.ColorGradientFormatting r0 = r0.copy()
        L_0x0055:
            r3.color_gradient = r0
            org.apache.poi.hssf.record.cf.IconMultiStateFormatting r0 = r4.multistate
            if (r0 != 0) goto L_0x005d
            r0 = r1
            goto L_0x0061
        L_0x005d:
            org.apache.poi.hssf.record.cf.IconMultiStateFormatting r0 = r0.copy()
        L_0x0061:
            r3.multistate = r0
            org.apache.poi.hssf.record.cf.DataBarFormatting r0 = r4.data_bar
            if (r0 != 0) goto L_0x0069
            r0 = r1
            goto L_0x006d
        L_0x0069:
            org.apache.poi.hssf.record.cf.DataBarFormatting r0 = r0.copy()
        L_0x006d:
            r3.data_bar = r0
            byte[] r4 = r4.filter_data
            if (r4 != 0) goto L_0x0074
            goto L_0x007b
        L_0x0074:
            java.lang.Object r4 = r4.clone()
            r1 = r4
            byte[] r1 = (byte[]) r1
        L_0x007b:
            r3.filter_data = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.CFRule12Record.<init>(org.apache.poi.hssf.record.CFRule12Record):void");
    }

    private CFRule12Record(byte b, byte b2) {
        super(b, b2);
        setDefaults();
    }

    private CFRule12Record(byte b, byte b2, Ptg[] ptgArr, Ptg[] ptgArr2, Ptg[] ptgArr3) {
        super(b, b2, ptgArr, ptgArr2);
        setDefaults();
        this.formula_scale = Formula.create(ptgArr3);
    }

    private void setDefaults() {
        FtrHeader ftrHeader = new FtrHeader();
        this.futureHeader = ftrHeader;
        ftrHeader.setRecordType(sid);
        this.ext_formatting_length = 0;
        this.ext_formatting_data = new byte[4];
        this.formula_scale = Formula.create(Ptg.EMPTY_PTG_ARRAY);
        this.ext_opts = 0;
        this.priority = 0;
        this.template_type = getConditionType();
        this.template_param_length = UnionPtg.sid;
        this.template_params = IOUtils.safelyAllocate((long) 16, HSSFWorkbook.getMaxRecordLength());
    }

    public static CFRule12Record create(HSSFSheet hSSFSheet, String str) {
        return new CFRule12Record((byte) 2, (byte) 0, parseFormula(str, hSSFSheet), (Ptg[]) null, (Ptg[]) null);
    }

    public static CFRule12Record create(HSSFSheet hSSFSheet, byte b, String str, String str2) {
        return new CFRule12Record((byte) 1, b, parseFormula(str, hSSFSheet), parseFormula(str2, hSSFSheet), (Ptg[]) null);
    }

    public static CFRule12Record create(HSSFSheet hSSFSheet, byte b, String str, String str2, String str3) {
        return new CFRule12Record((byte) 1, b, parseFormula(str, hSSFSheet), parseFormula(str2, hSSFSheet), parseFormula(str3, hSSFSheet));
    }

    public static CFRule12Record create(HSSFSheet hSSFSheet, ExtendedColor extendedColor) {
        CFRule12Record cFRule12Record = new CFRule12Record((byte) 4, (byte) 0);
        DataBarFormatting createDataBarFormatting = cFRule12Record.createDataBarFormatting();
        createDataBarFormatting.setColor(extendedColor);
        createDataBarFormatting.setPercentMin((byte) 0);
        createDataBarFormatting.setPercentMax((byte) 100);
        DataBarThreshold dataBarThreshold = new DataBarThreshold();
        dataBarThreshold.setType(ConditionalFormattingThreshold.RangeType.MIN.id);
        createDataBarFormatting.setThresholdMin(dataBarThreshold);
        DataBarThreshold dataBarThreshold2 = new DataBarThreshold();
        dataBarThreshold2.setType(ConditionalFormattingThreshold.RangeType.MAX.id);
        createDataBarFormatting.setThresholdMax(dataBarThreshold2);
        return cFRule12Record;
    }

    public static CFRule12Record create(HSSFSheet hSSFSheet, IconMultiStateFormatting.IconSet iconSet) {
        int i = iconSet.num;
        Threshold[] thresholdArr = new Threshold[i];
        for (int i2 = 0; i2 < i; i2++) {
            thresholdArr[i2] = new IconMultiStateThreshold();
        }
        CFRule12Record cFRule12Record = new CFRule12Record((byte) 6, (byte) 0);
        org.apache.poi.hssf.record.cf.IconMultiStateFormatting createMultiStateFormatting = cFRule12Record.createMultiStateFormatting();
        createMultiStateFormatting.setIconSet(iconSet);
        createMultiStateFormatting.setThresholds(thresholdArr);
        return cFRule12Record;
    }

    public static CFRule12Record createColorScale(HSSFSheet hSSFSheet) {
        ExtendedColor[] extendedColorArr = new ExtendedColor[3];
        ColorGradientThreshold[] colorGradientThresholdArr = new ColorGradientThreshold[3];
        for (int i = 0; i < 3; i++) {
            colorGradientThresholdArr[i] = new ColorGradientThreshold();
            extendedColorArr[i] = new ExtendedColor();
        }
        CFRule12Record cFRule12Record = new CFRule12Record((byte) 3, (byte) 0);
        ColorGradientFormatting createColorGradientFormatting = cFRule12Record.createColorGradientFormatting();
        createColorGradientFormatting.setNumControlPoints(3);
        createColorGradientFormatting.setThresholds(colorGradientThresholdArr);
        createColorGradientFormatting.setColors(extendedColorArr);
        return cFRule12Record;
    }

    public CFRule12Record(RecordInputStream recordInputStream) {
        this.futureHeader = new FtrHeader(recordInputStream);
        setConditionType(recordInputStream.readByte());
        setComparisonOperation(recordInputStream.readByte());
        int readUShort = recordInputStream.readUShort();
        int readUShort2 = recordInputStream.readUShort();
        int readInt = recordInputStream.readInt();
        this.ext_formatting_length = readInt;
        this.ext_formatting_data = new byte[0];
        if (readInt == 0) {
            recordInputStream.readUShort();
        } else {
            long readFormatOptions = (long) readFormatOptions(recordInputStream);
            int i = this.ext_formatting_length;
            if (readFormatOptions < ((long) i)) {
                byte[] safelyAllocate = IOUtils.safelyAllocate(((long) i) - readFormatOptions, HSSFWorkbook.getMaxRecordLength());
                this.ext_formatting_data = safelyAllocate;
                recordInputStream.readFully(safelyAllocate);
            }
        }
        setFormula1(Formula.read(readUShort, recordInputStream));
        setFormula2(Formula.read(readUShort2, recordInputStream));
        this.formula_scale = Formula.read(recordInputStream.readUShort(), recordInputStream);
        this.ext_opts = recordInputStream.readByte();
        this.priority = recordInputStream.readUShort();
        this.template_type = recordInputStream.readUShort();
        byte readByte = recordInputStream.readByte();
        this.template_param_length = readByte;
        if (readByte == 0 || readByte == 16) {
            byte[] safelyAllocate2 = IOUtils.safelyAllocate((long) readByte, HSSFWorkbook.getMaxRecordLength());
            this.template_params = safelyAllocate2;
            recordInputStream.readFully(safelyAllocate2);
        } else {
            LOG.atWarn().log("CF Rule v12 template params length should be 0 or 16, found {}", (Object) Unbox.box(this.template_param_length));
            recordInputStream.readRemainder();
        }
        byte conditionType = getConditionType();
        if (conditionType == 3) {
            this.color_gradient = new ColorGradientFormatting((LittleEndianInput) recordInputStream);
        } else if (conditionType == 4) {
            this.data_bar = new DataBarFormatting((LittleEndianInput) recordInputStream);
        } else if (conditionType == 5) {
            this.filter_data = recordInputStream.readRemainder();
        } else if (conditionType == 6) {
            this.multistate = new org.apache.poi.hssf.record.cf.IconMultiStateFormatting((LittleEndianInput) recordInputStream);
        }
    }

    public boolean containsDataBarBlock() {
        return this.data_bar != null;
    }

    public DataBarFormatting getDataBarFormatting() {
        return this.data_bar;
    }

    public DataBarFormatting createDataBarFormatting() {
        DataBarFormatting dataBarFormatting = this.data_bar;
        if (dataBarFormatting != null) {
            return dataBarFormatting;
        }
        setConditionType((byte) 4);
        DataBarFormatting dataBarFormatting2 = new DataBarFormatting();
        this.data_bar = dataBarFormatting2;
        return dataBarFormatting2;
    }

    public boolean containsMultiStateBlock() {
        return this.multistate != null;
    }

    public org.apache.poi.hssf.record.cf.IconMultiStateFormatting getMultiStateFormatting() {
        return this.multistate;
    }

    public org.apache.poi.hssf.record.cf.IconMultiStateFormatting createMultiStateFormatting() {
        org.apache.poi.hssf.record.cf.IconMultiStateFormatting iconMultiStateFormatting = this.multistate;
        if (iconMultiStateFormatting != null) {
            return iconMultiStateFormatting;
        }
        setConditionType((byte) 6);
        org.apache.poi.hssf.record.cf.IconMultiStateFormatting iconMultiStateFormatting2 = new org.apache.poi.hssf.record.cf.IconMultiStateFormatting();
        this.multistate = iconMultiStateFormatting2;
        return iconMultiStateFormatting2;
    }

    public boolean containsColorGradientBlock() {
        return this.color_gradient != null;
    }

    public ColorGradientFormatting getColorGradientFormatting() {
        return this.color_gradient;
    }

    public ColorGradientFormatting createColorGradientFormatting() {
        ColorGradientFormatting colorGradientFormatting = this.color_gradient;
        if (colorGradientFormatting != null) {
            return colorGradientFormatting;
        }
        setConditionType((byte) 3);
        ColorGradientFormatting colorGradientFormatting2 = new ColorGradientFormatting();
        this.color_gradient = colorGradientFormatting2;
        return colorGradientFormatting2;
    }

    public Ptg[] getParsedExpressionScale() {
        return this.formula_scale.getTokens();
    }

    public void setParsedExpressionScale(Ptg[] ptgArr) {
        this.formula_scale = Formula.create(ptgArr);
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        this.futureHeader.serialize(littleEndianOutput);
        int formulaSize = getFormulaSize(getFormula1());
        int formulaSize2 = getFormulaSize(getFormula2());
        littleEndianOutput.writeByte(getConditionType());
        littleEndianOutput.writeByte(getComparisonOperation());
        littleEndianOutput.writeShort(formulaSize);
        littleEndianOutput.writeShort(formulaSize2);
        int i = this.ext_formatting_length;
        if (i == 0) {
            littleEndianOutput.writeInt(0);
            littleEndianOutput.writeShort(0);
        } else {
            littleEndianOutput.writeInt(i);
            serializeFormattingBlock(littleEndianOutput);
            littleEndianOutput.write(this.ext_formatting_data);
        }
        getFormula1().serializeTokens(littleEndianOutput);
        getFormula2().serializeTokens(littleEndianOutput);
        littleEndianOutput.writeShort(getFormulaSize(this.formula_scale));
        this.formula_scale.serializeTokens(littleEndianOutput);
        littleEndianOutput.writeByte(this.ext_opts);
        littleEndianOutput.writeShort(this.priority);
        littleEndianOutput.writeShort(this.template_type);
        littleEndianOutput.writeByte(this.template_param_length);
        littleEndianOutput.write(this.template_params);
        byte conditionType = getConditionType();
        if (conditionType == 3) {
            this.color_gradient.serialize(littleEndianOutput);
        } else if (conditionType == 4) {
            this.data_bar.serialize(littleEndianOutput);
        } else if (conditionType == 5) {
            littleEndianOutput.write(this.filter_data);
        } else if (conditionType == 6) {
            this.multistate.serialize(littleEndianOutput);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        int i;
        int dataLength;
        int dataSize = FtrHeader.getDataSize() + 6;
        if (this.ext_formatting_length == 0) {
            i = dataSize + 6;
        } else {
            i = dataSize + getFormattingBlockSize() + 4 + this.ext_formatting_data.length;
        }
        int formulaSize = i + getFormulaSize(getFormula1()) + getFormulaSize(getFormula2()) + getFormulaSize(this.formula_scale) + 2 + this.template_params.length + 6;
        byte conditionType = getConditionType();
        if (conditionType == 3) {
            dataLength = this.color_gradient.getDataLength();
        } else if (conditionType == 4) {
            dataLength = this.data_bar.getDataLength();
        } else if (conditionType == 5) {
            dataLength = this.filter_data.length;
        } else if (conditionType != 6) {
            return formulaSize;
        } else {
            dataLength = this.multistate.getDataLength();
        }
        return formulaSize + dataLength;
    }

    public CFRule12Record copy() {
        return new CFRule12Record(this);
    }

    public short getFutureRecordType() {
        return this.futureHeader.getRecordType();
    }

    public FtrHeader getFutureHeader() {
        return this.futureHeader;
    }

    public CellRangeAddress getAssociatedRange() {
        return this.futureHeader.getAssociatedRange();
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CF_RULE_12;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(super.getGenericProperties());
        linkedHashMap.put("dxFn12Length", new CFRule12Record$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("futureHeader", new CFRule12Record$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("dxFn12Ext", new CFRule12Record$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("formulaScale", new CFRule12Record$$ExternalSyntheticLambda7(this));
        linkedHashMap.put("extOptions", new CFRule12Record$$ExternalSyntheticLambda8(this));
        linkedHashMap.put("priority", new CFRule12Record$$ExternalSyntheticLambda9(this));
        linkedHashMap.put("templateType", new CFRule12Record$$ExternalSyntheticLambda10(this));
        linkedHashMap.put("templateParams", new CFRule12Record$$ExternalSyntheticLambda11(this));
        linkedHashMap.put("filterData", new CFRule12Record$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("dataBar", new CFRule12Record$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("multiState", new CFRule12Record$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("colorGradient", new CFRule12Record$$ExternalSyntheticLambda4(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-CFRule12Record  reason: not valid java name */
    public /* synthetic */ Object m1974lambda$getGenericProperties$0$orgapachepoihssfrecordCFRule12Record() {
        return Integer.valueOf(this.ext_formatting_length);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-CFRule12Record  reason: not valid java name */
    public /* synthetic */ Object m1975lambda$getGenericProperties$1$orgapachepoihssfrecordCFRule12Record() {
        return this.ext_formatting_data;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-CFRule12Record  reason: not valid java name */
    public /* synthetic */ Object m1976lambda$getGenericProperties$2$orgapachepoihssfrecordCFRule12Record() {
        return Byte.valueOf(this.ext_opts);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-CFRule12Record  reason: not valid java name */
    public /* synthetic */ Object m1977lambda$getGenericProperties$3$orgapachepoihssfrecordCFRule12Record() {
        return Integer.valueOf(this.template_type);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-CFRule12Record  reason: not valid java name */
    public /* synthetic */ Object m1978lambda$getGenericProperties$4$orgapachepoihssfrecordCFRule12Record() {
        return this.template_params;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-CFRule12Record  reason: not valid java name */
    public /* synthetic */ Object m1979lambda$getGenericProperties$5$orgapachepoihssfrecordCFRule12Record() {
        return this.filter_data;
    }
}
