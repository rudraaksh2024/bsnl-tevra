package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import kotlin.jvm.internal.ShortCompanionObject;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.constant.ConstantValueParser;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class ExternalNameRecord extends StandardRecord {
    private static final int[] OPTION_FLAGS = {1, 2, 4, 8, 16, 32768};
    private static final String[] OPTION_NAMES = {"BUILTIN_NAME", "AUTOMATIC_LINK", "PICTURE_LINK", "STD_DOCUMENT_NAME", "OLE_LINK", "ICONIFIED_PICTURE_LINK"};
    private static final int OPT_AUTOMATIC_LINK = 2;
    private static final int OPT_BUILTIN_NAME = 1;
    private static final int OPT_ICONIFIED_PICTURE_LINK = 32768;
    private static final int OPT_OLE_LINK = 16;
    private static final int OPT_PICTURE_LINK = 4;
    private static final int OPT_STD_DOCUMENT_NAME = 8;
    public static final short sid = 35;
    private Object[] _ddeValues;
    private int _nColumns;
    private int _nRows;
    private short field_1_option_flag;
    private short field_2_ixals;
    private short field_3_not_used;
    private String field_4_name;
    private Formula field_5_name_definition;

    static /* synthetic */ Object lambda$getGenericProperties$1() {
        return null;
    }

    public short getSid() {
        return 35;
    }

    public ExternalNameRecord() {
        this.field_2_ixals = 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExternalNameRecord(org.apache.poi.hssf.record.ExternalNameRecord r3) {
        /*
            r2 = this;
            r2.<init>(r3)
            short r0 = r3.field_1_option_flag
            r2.field_1_option_flag = r0
            short r0 = r3.field_2_ixals
            r2.field_2_ixals = r0
            short r0 = r3.field_3_not_used
            r2.field_3_not_used = r0
            java.lang.String r0 = r3.field_4_name
            r2.field_4_name = r0
            org.apache.poi.ss.formula.Formula r0 = r3.field_5_name_definition
            r1 = 0
            if (r0 != 0) goto L_0x001a
            r0 = r1
            goto L_0x001e
        L_0x001a:
            org.apache.poi.ss.formula.Formula r0 = r0.copy()
        L_0x001e:
            r2.field_5_name_definition = r0
            java.lang.Object[] r0 = r3._ddeValues
            if (r0 != 0) goto L_0x0025
            goto L_0x002c
        L_0x0025:
            java.lang.Object r0 = r0.clone()
            r1 = r0
            java.lang.Object[] r1 = (java.lang.Object[]) r1
        L_0x002c:
            r2._ddeValues = r1
            int r0 = r3._nColumns
            r2._nColumns = r0
            int r3 = r3._nRows
            r2._nRows = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.ExternalNameRecord.<init>(org.apache.poi.hssf.record.ExternalNameRecord):void");
    }

    public ExternalNameRecord(RecordInputStream recordInputStream) {
        this.field_1_option_flag = recordInputStream.readShort();
        this.field_2_ixals = recordInputStream.readShort();
        this.field_3_not_used = recordInputStream.readShort();
        this.field_4_name = StringUtil.readUnicodeString(recordInputStream, recordInputStream.readUByte());
        if (!isOLELink() && !isStdDocumentNameIdentifier()) {
            if (!isAutomaticLink()) {
                this.field_5_name_definition = Formula.read(recordInputStream.readUShort(), recordInputStream);
            } else if (recordInputStream.available() > 0) {
                int readUByte = recordInputStream.readUByte() + 1;
                int readShort = recordInputStream.readShort() + 1;
                this._ddeValues = ConstantValueParser.parse(recordInputStream, readShort * readUByte);
                this._nColumns = readUByte;
                this._nRows = readShort;
            }
        }
    }

    public boolean isBuiltInName() {
        return (this.field_1_option_flag & 1) != 0;
    }

    public boolean isAutomaticLink() {
        return (this.field_1_option_flag & 2) != 0;
    }

    public boolean isPicureLink() {
        return (this.field_1_option_flag & 4) != 0;
    }

    public boolean isStdDocumentNameIdentifier() {
        return (this.field_1_option_flag & 8) != 0;
    }

    public boolean isOLELink() {
        return (this.field_1_option_flag & 16) != 0;
    }

    public boolean isIconifiedPictureLink() {
        return (this.field_1_option_flag & ShortCompanionObject.MIN_VALUE) != 0;
    }

    public String getText() {
        return this.field_4_name;
    }

    public void setText(String str) {
        this.field_4_name = str;
    }

    public short getIx() {
        return this.field_2_ixals;
    }

    public void setIx(short s) {
        this.field_2_ixals = s;
    }

    public Ptg[] getParsedExpression() {
        return Formula.getTokens(this.field_5_name_definition);
    }

    public void setParsedExpression(Ptg[] ptgArr) {
        this.field_5_name_definition = Formula.create(ptgArr);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        int i;
        int encodedSize = (StringUtil.getEncodedSize(this.field_4_name) - 1) + 6;
        if (isOLELink() || isStdDocumentNameIdentifier()) {
            return encodedSize;
        }
        if (isAutomaticLink()) {
            Object[] objArr = this._ddeValues;
            if (objArr == null) {
                return encodedSize;
            }
            encodedSize += 3;
            i = ConstantValueParser.getEncodedSize(objArr);
        } else {
            i = this.field_5_name_definition.getEncodedSize();
        }
        return encodedSize + i;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_option_flag);
        littleEndianOutput.writeShort(this.field_2_ixals);
        littleEndianOutput.writeShort(this.field_3_not_used);
        littleEndianOutput.writeByte(this.field_4_name.length());
        StringUtil.writeUnicodeStringFlagAndData(littleEndianOutput, this.field_4_name);
        if (!isOLELink() && !isStdDocumentNameIdentifier()) {
            if (!isAutomaticLink()) {
                this.field_5_name_definition.serialize(littleEndianOutput);
            } else if (this._ddeValues != null) {
                littleEndianOutput.writeByte(this._nColumns - 1);
                littleEndianOutput.writeShort(this._nRows - 1);
                ConstantValueParser.encode(littleEndianOutput, this._ddeValues);
            }
        }
    }

    public ExternalNameRecord copy() {
        return new ExternalNameRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.EXTERNAL_NAME;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        Supplier supplier;
        Supplier<GenericRecordUtil.AnnotatedFlag> bitsAsString = GenericRecordUtil.getBitsAsString((Supplier<Number>) new ExternalNameRecord$$ExternalSyntheticLambda0(this), OPTION_FLAGS, OPTION_NAMES);
        ExternalNameRecord$$ExternalSyntheticLambda1 externalNameRecord$$ExternalSyntheticLambda1 = new ExternalNameRecord$$ExternalSyntheticLambda1(this);
        ExternalNameRecord$$ExternalSyntheticLambda2 externalNameRecord$$ExternalSyntheticLambda2 = new ExternalNameRecord$$ExternalSyntheticLambda2(this);
        Formula formula = this.field_5_name_definition;
        if (formula == null) {
            supplier = new ExternalNameRecord$$ExternalSyntheticLambda3();
        } else {
            formula.getClass();
            supplier = new ExternalNameRecord$$ExternalSyntheticLambda4(formula);
        }
        return GenericRecordUtil.getGenericProperties("options", bitsAsString, "ix", externalNameRecord$$ExternalSyntheticLambda1, "name", externalNameRecord$$ExternalSyntheticLambda2, "nameDefinition", supplier);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ExternalNameRecord  reason: not valid java name */
    public /* synthetic */ Number m2013lambda$getGenericProperties$0$orgapachepoihssfrecordExternalNameRecord() {
        return Short.valueOf(this.field_1_option_flag);
    }
}
