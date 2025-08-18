package org.apache.poi.hssf.record.pivottable;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class ViewDefinitionRecord extends StandardRecord {
    public static final short sid = 176;
    private int cCol;
    private int cDim;
    private int cDimCol;
    private int cDimData;
    private int cDimPg;
    private int cDimRw;
    private int cRw;
    private int colFirst;
    private int colFirstData;
    private int colLast;
    private String dataField;
    private int grbit;
    private int iCache;
    private int ipos4Data;
    private int itblAutoFmt;
    private String name;
    private int reserved;
    private int rwFirst;
    private int rwFirstData;
    private int rwFirstHead;
    private int rwLast;
    private int sxaxis4Data;

    public short getSid() {
        return 176;
    }

    public ViewDefinitionRecord(ViewDefinitionRecord viewDefinitionRecord) {
        super(viewDefinitionRecord);
        this.rwFirst = viewDefinitionRecord.rwFirst;
        this.rwLast = viewDefinitionRecord.rwLast;
        this.colFirst = viewDefinitionRecord.colFirst;
        this.colLast = viewDefinitionRecord.colLast;
        this.rwFirstHead = viewDefinitionRecord.rwFirstHead;
        this.rwFirstData = viewDefinitionRecord.rwFirstData;
        this.colFirstData = viewDefinitionRecord.colFirstData;
        this.iCache = viewDefinitionRecord.iCache;
        this.reserved = viewDefinitionRecord.reserved;
        this.sxaxis4Data = viewDefinitionRecord.sxaxis4Data;
        this.ipos4Data = viewDefinitionRecord.ipos4Data;
        this.cDim = viewDefinitionRecord.cDim;
        this.cDimRw = viewDefinitionRecord.cDimRw;
        this.cDimCol = viewDefinitionRecord.cDimCol;
        this.cDimPg = viewDefinitionRecord.cDimPg;
        this.cDimData = viewDefinitionRecord.cDimData;
        this.cRw = viewDefinitionRecord.cRw;
        this.cCol = viewDefinitionRecord.cCol;
        this.grbit = viewDefinitionRecord.grbit;
        this.itblAutoFmt = viewDefinitionRecord.itblAutoFmt;
        this.name = viewDefinitionRecord.name;
        this.dataField = viewDefinitionRecord.dataField;
    }

    public ViewDefinitionRecord(RecordInputStream recordInputStream) {
        this.rwFirst = recordInputStream.readUShort();
        this.rwLast = recordInputStream.readUShort();
        this.colFirst = recordInputStream.readUShort();
        this.colLast = recordInputStream.readUShort();
        this.rwFirstHead = recordInputStream.readUShort();
        this.rwFirstData = recordInputStream.readUShort();
        this.colFirstData = recordInputStream.readUShort();
        this.iCache = recordInputStream.readUShort();
        this.reserved = recordInputStream.readUShort();
        this.sxaxis4Data = recordInputStream.readUShort();
        this.ipos4Data = recordInputStream.readUShort();
        this.cDim = recordInputStream.readUShort();
        this.cDimRw = recordInputStream.readUShort();
        this.cDimCol = recordInputStream.readUShort();
        this.cDimPg = recordInputStream.readUShort();
        this.cDimData = recordInputStream.readUShort();
        this.cRw = recordInputStream.readUShort();
        this.cCol = recordInputStream.readUShort();
        this.grbit = recordInputStream.readUShort();
        this.itblAutoFmt = recordInputStream.readUShort();
        int readUShort = recordInputStream.readUShort();
        int readUShort2 = recordInputStream.readUShort();
        this.name = StringUtil.readUnicodeString(recordInputStream, readUShort);
        this.dataField = StringUtil.readUnicodeString(recordInputStream, readUShort2);
    }

    /* access modifiers changed from: protected */
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.rwFirst);
        littleEndianOutput.writeShort(this.rwLast);
        littleEndianOutput.writeShort(this.colFirst);
        littleEndianOutput.writeShort(this.colLast);
        littleEndianOutput.writeShort(this.rwFirstHead);
        littleEndianOutput.writeShort(this.rwFirstData);
        littleEndianOutput.writeShort(this.colFirstData);
        littleEndianOutput.writeShort(this.iCache);
        littleEndianOutput.writeShort(this.reserved);
        littleEndianOutput.writeShort(this.sxaxis4Data);
        littleEndianOutput.writeShort(this.ipos4Data);
        littleEndianOutput.writeShort(this.cDim);
        littleEndianOutput.writeShort(this.cDimRw);
        littleEndianOutput.writeShort(this.cDimCol);
        littleEndianOutput.writeShort(this.cDimPg);
        littleEndianOutput.writeShort(this.cDimData);
        littleEndianOutput.writeShort(this.cRw);
        littleEndianOutput.writeShort(this.cCol);
        littleEndianOutput.writeShort(this.grbit);
        littleEndianOutput.writeShort(this.itblAutoFmt);
        littleEndianOutput.writeShort(this.name.length());
        littleEndianOutput.writeShort(this.dataField.length());
        StringUtil.writeUnicodeStringFlagAndData(littleEndianOutput, this.name);
        StringUtil.writeUnicodeStringFlagAndData(littleEndianOutput, this.dataField);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return StringUtil.getEncodedSize(this.name) + 40 + StringUtil.getEncodedSize(this.dataField);
    }

    public ViewDefinitionRecord copy() {
        return new ViewDefinitionRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.VIEW_DEFINITION;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("rwFirst", new ViewDefinitionRecord$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("rwLast", new ViewDefinitionRecord$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("colFirst", new ViewDefinitionRecord$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("colLast", new ViewDefinitionRecord$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("rwFirstHead", new ViewDefinitionRecord$$ExternalSyntheticLambda7(this));
        linkedHashMap.put("rwFirstData", new ViewDefinitionRecord$$ExternalSyntheticLambda8(this));
        linkedHashMap.put("colFirstData", new ViewDefinitionRecord$$ExternalSyntheticLambda9(this));
        linkedHashMap.put("iCache", new ViewDefinitionRecord$$ExternalSyntheticLambda10(this));
        linkedHashMap.put("reserved", new ViewDefinitionRecord$$ExternalSyntheticLambda12(this));
        linkedHashMap.put("sxaxis4Data", new ViewDefinitionRecord$$ExternalSyntheticLambda13(this));
        linkedHashMap.put("ipos4Data", new ViewDefinitionRecord$$ExternalSyntheticLambda11(this));
        linkedHashMap.put("cDim", new ViewDefinitionRecord$$ExternalSyntheticLambda14(this));
        linkedHashMap.put("cDimRw", new ViewDefinitionRecord$$ExternalSyntheticLambda15(this));
        linkedHashMap.put("cDimCol", new ViewDefinitionRecord$$ExternalSyntheticLambda16(this));
        linkedHashMap.put("cDimPg", new ViewDefinitionRecord$$ExternalSyntheticLambda17(this));
        linkedHashMap.put("cDimData", new ViewDefinitionRecord$$ExternalSyntheticLambda18(this));
        linkedHashMap.put("cRw", new ViewDefinitionRecord$$ExternalSyntheticLambda19(this));
        linkedHashMap.put("cCol", new ViewDefinitionRecord$$ExternalSyntheticLambda20(this));
        linkedHashMap.put("grbit", new ViewDefinitionRecord$$ExternalSyntheticLambda21(this));
        linkedHashMap.put("itblAutoFmt", new ViewDefinitionRecord$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("name", new ViewDefinitionRecord$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("dataField", new ViewDefinitionRecord$$ExternalSyntheticLambda4(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2183lambda$getGenericProperties$0$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.rwFirst);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2184lambda$getGenericProperties$1$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.rwLast);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2195lambda$getGenericProperties$2$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.colFirst);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2198lambda$getGenericProperties$3$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.colLast);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2199lambda$getGenericProperties$4$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.rwFirstHead);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2200lambda$getGenericProperties$5$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.rwFirstData);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$6$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2201lambda$getGenericProperties$6$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.colFirstData);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$7$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2202lambda$getGenericProperties$7$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.iCache);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$8$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2203lambda$getGenericProperties$8$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.reserved);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$9$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2204lambda$getGenericProperties$9$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.sxaxis4Data);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$10$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2185lambda$getGenericProperties$10$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.ipos4Data);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$11$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2186lambda$getGenericProperties$11$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.cDim);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$12$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2187lambda$getGenericProperties$12$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.cDimRw);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$13$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2188lambda$getGenericProperties$13$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.cDimCol);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$14$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2189lambda$getGenericProperties$14$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.cDimPg);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$15$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2190lambda$getGenericProperties$15$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.cDimData);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$16$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2191lambda$getGenericProperties$16$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.cRw);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$17$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2192lambda$getGenericProperties$17$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.cCol);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$18$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2193lambda$getGenericProperties$18$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.grbit);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$19$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2194lambda$getGenericProperties$19$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return Integer.valueOf(this.itblAutoFmt);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$20$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2196lambda$getGenericProperties$20$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$21$org-apache-poi-hssf-record-pivottable-ViewDefinitionRecord  reason: not valid java name */
    public /* synthetic */ Object m2197lambda$getGenericProperties$21$orgapachepoihssfrecordpivottableViewDefinitionRecord() {
        return this.dataField;
    }
}
