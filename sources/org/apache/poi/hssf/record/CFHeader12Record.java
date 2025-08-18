package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.common.FtrHeader;
import org.apache.poi.hssf.record.common.FutureRecord;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class CFHeader12Record extends CFHeaderBase implements FutureRecord {
    public static final short sid = 2169;
    private FtrHeader futureHeader;

    /* access modifiers changed from: protected */
    public String getRecordName() {
        return "CFHEADER12";
    }

    public short getSid() {
        return sid;
    }

    public CFHeader12Record() {
        createEmpty();
        FtrHeader ftrHeader = new FtrHeader();
        this.futureHeader = ftrHeader;
        ftrHeader.setRecordType(sid);
    }

    public CFHeader12Record(CFHeader12Record cFHeader12Record) {
        super(cFHeader12Record);
        this.futureHeader = cFHeader12Record.futureHeader.copy();
    }

    public CFHeader12Record(CellRangeAddress[] cellRangeAddressArr, int i) {
        super(cellRangeAddressArr, i);
        FtrHeader ftrHeader = new FtrHeader();
        this.futureHeader = ftrHeader;
        ftrHeader.setRecordType(sid);
    }

    public CFHeader12Record(RecordInputStream recordInputStream) {
        this.futureHeader = new FtrHeader(recordInputStream);
        read(recordInputStream);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return FtrHeader.getDataSize() + super.getDataSize();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        this.futureHeader.setAssociatedRange(getEnclosingCellRange());
        this.futureHeader.serialize(littleEndianOutput);
        super.serialize(littleEndianOutput);
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

    public CFHeader12Record copy() {
        return new CFHeader12Record(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CF_HEADER_12;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new CFHeader12Record$$ExternalSyntheticLambda0(this), "futureHeader", new CFHeader12Record$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-CFHeader12Record  reason: not valid java name */
    public /* synthetic */ Object m1973lambda$getGenericProperties$0$orgapachepoihssfrecordCFHeader12Record() {
        return super.getGenericProperties();
    }
}
