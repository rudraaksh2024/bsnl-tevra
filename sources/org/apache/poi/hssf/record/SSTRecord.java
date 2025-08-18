package org.apache.poi.hssf.record;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IntMapper;

public final class SSTRecord extends ContinuableRecord {
    private static final UnicodeString EMPTY_STRING = new UnicodeString("");
    public static final short sid = 252;
    private int[] bucketAbsoluteOffsets;
    private int[] bucketRelativeOffsets;
    private final SSTDeserializer deserializer;
    private int field_1_num_strings;
    private int field_2_num_unique_strings;
    private final IntMapper<UnicodeString> field_3_strings;

    public short getSid() {
        return sid;
    }

    public SSTRecord() {
        this.field_1_num_strings = 0;
        this.field_2_num_unique_strings = 0;
        IntMapper<UnicodeString> intMapper = new IntMapper<>();
        this.field_3_strings = intMapper;
        this.deserializer = new SSTDeserializer(intMapper);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: int[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SSTRecord(org.apache.poi.hssf.record.SSTRecord r3) {
        /*
            r2 = this;
            r2.<init>(r3)
            int r0 = r3.field_1_num_strings
            r2.field_1_num_strings = r0
            int r0 = r3.field_2_num_unique_strings
            r2.field_2_num_unique_strings = r0
            org.apache.poi.util.IntMapper<org.apache.poi.hssf.record.common.UnicodeString> r0 = r3.field_3_strings
            org.apache.poi.util.IntMapper r0 = r0.copy()
            r2.field_3_strings = r0
            org.apache.poi.hssf.record.SSTDeserializer r1 = new org.apache.poi.hssf.record.SSTDeserializer
            r1.<init>(r0)
            r2.deserializer = r1
            int[] r0 = r3.bucketAbsoluteOffsets
            r1 = 0
            if (r0 != 0) goto L_0x0021
            r0 = r1
            goto L_0x0027
        L_0x0021:
            java.lang.Object r0 = r0.clone()
            int[] r0 = (int[]) r0
        L_0x0027:
            r2.bucketAbsoluteOffsets = r0
            int[] r3 = r3.bucketRelativeOffsets
            if (r3 != 0) goto L_0x002e
            goto L_0x0035
        L_0x002e:
            java.lang.Object r3 = r3.clone()
            r1 = r3
            int[] r1 = (int[]) r1
        L_0x0035:
            r2.bucketRelativeOffsets = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.SSTRecord.<init>(org.apache.poi.hssf.record.SSTRecord):void");
    }

    public int addString(UnicodeString unicodeString) {
        this.field_1_num_strings++;
        if (unicodeString == null) {
            unicodeString = EMPTY_STRING;
        }
        int index = this.field_3_strings.getIndex(unicodeString);
        if (index != -1) {
            return index;
        }
        int size = this.field_3_strings.size();
        this.field_2_num_unique_strings++;
        SSTDeserializer.addToStringTable(this.field_3_strings, unicodeString);
        return size;
    }

    public int getNumStrings() {
        return this.field_1_num_strings;
    }

    public int getNumUniqueStrings() {
        return this.field_2_num_unique_strings;
    }

    public UnicodeString getString(int i) {
        return this.field_3_strings.get(i);
    }

    public SSTRecord(RecordInputStream recordInputStream) {
        this.field_1_num_strings = recordInputStream.readInt();
        this.field_2_num_unique_strings = recordInputStream.readInt();
        IntMapper<UnicodeString> intMapper = new IntMapper<>();
        this.field_3_strings = intMapper;
        SSTDeserializer sSTDeserializer = new SSTDeserializer(intMapper);
        this.deserializer = sSTDeserializer;
        if (this.field_1_num_strings == 0) {
            this.field_2_num_unique_strings = 0;
        } else {
            sSTDeserializer.manufactureStrings(this.field_2_num_unique_strings, recordInputStream);
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<UnicodeString> getStrings() {
        return this.field_3_strings.iterator();
    }

    /* access modifiers changed from: package-private */
    public int countStrings() {
        return this.field_3_strings.size();
    }

    /* access modifiers changed from: protected */
    public void serialize(ContinuableRecordOutput continuableRecordOutput) {
        SSTSerializer sSTSerializer = new SSTSerializer(this.field_3_strings, getNumStrings(), getNumUniqueStrings());
        sSTSerializer.serialize(continuableRecordOutput);
        this.bucketAbsoluteOffsets = sSTSerializer.getBucketAbsoluteOffsets();
        this.bucketRelativeOffsets = sSTSerializer.getBucketRelativeOffsets();
    }

    public ExtSSTRecord createExtSSTRecord(int i) {
        if (this.bucketAbsoluteOffsets == null || this.bucketRelativeOffsets == null) {
            throw new IllegalStateException("SST record has not yet been serialized.");
        }
        ExtSSTRecord extSSTRecord = new ExtSSTRecord();
        extSSTRecord.setNumStringsPerBucket(8);
        int[] iArr = (int[]) this.bucketAbsoluteOffsets.clone();
        int[] iArr2 = (int[]) this.bucketRelativeOffsets.clone();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = iArr[i2] + i;
        }
        extSSTRecord.setBucketOffsets(iArr, iArr2);
        return extSSTRecord;
    }

    public int calcExtSSTRecordSize() {
        return ExtSSTRecord.getRecordSizeForStrings(this.field_3_strings.size());
    }

    public SSTRecord copy() {
        return new SSTRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SST;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        SSTRecord$$ExternalSyntheticLambda0 sSTRecord$$ExternalSyntheticLambda0 = new SSTRecord$$ExternalSyntheticLambda0(this);
        SSTRecord$$ExternalSyntheticLambda1 sSTRecord$$ExternalSyntheticLambda1 = new SSTRecord$$ExternalSyntheticLambda1(this);
        IntMapper<UnicodeString> intMapper = this.field_3_strings;
        intMapper.getClass();
        return GenericRecordUtil.getGenericProperties("numStrings", sSTRecord$$ExternalSyntheticLambda0, "numUniqueStrings", sSTRecord$$ExternalSyntheticLambda1, "strings", new SSTRecord$$ExternalSyntheticLambda2(intMapper), "bucketAbsoluteOffsets", new SSTRecord$$ExternalSyntheticLambda3(this), "bucketRelativeOffsets", new SSTRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-SSTRecord  reason: not valid java name */
    public /* synthetic */ Object m2084lambda$getGenericProperties$0$orgapachepoihssfrecordSSTRecord() {
        return this.bucketAbsoluteOffsets;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-SSTRecord  reason: not valid java name */
    public /* synthetic */ Object m2085lambda$getGenericProperties$1$orgapachepoihssfrecordSSTRecord() {
        return this.bucketRelativeOffsets;
    }
}
