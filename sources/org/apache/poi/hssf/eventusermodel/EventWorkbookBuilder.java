package org.apache.poi.hssf.eventusermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.ExternSheetRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.SupBookRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class EventWorkbookBuilder {
    public static InternalWorkbook createStubWorkbook(ExternSheetRecord[] externSheetRecordArr, BoundSheetRecord[] boundSheetRecordArr, SSTRecord sSTRecord) {
        ArrayList arrayList = new ArrayList();
        if (boundSheetRecordArr != null) {
            Collections.addAll(arrayList, boundSheetRecordArr);
        }
        if (sSTRecord != null) {
            arrayList.add(sSTRecord);
        }
        if (externSheetRecordArr != null) {
            arrayList.add(SupBookRecord.createInternalReferences((short) externSheetRecordArr.length));
            Collections.addAll(arrayList, externSheetRecordArr);
        }
        arrayList.add(EOFRecord.instance);
        return InternalWorkbook.createWorkbook(arrayList);
    }

    public static InternalWorkbook createStubWorkbook(ExternSheetRecord[] externSheetRecordArr, BoundSheetRecord[] boundSheetRecordArr) {
        return createStubWorkbook(externSheetRecordArr, boundSheetRecordArr, (SSTRecord) null);
    }

    public static class SheetRecordCollectingListener implements HSSFListener {
        private final List<BoundSheetRecord> boundSheetRecords = new ArrayList();
        private final HSSFListener childListener;
        private final List<ExternSheetRecord> externSheetRecords = new ArrayList();
        private SSTRecord sstRecord;

        public SheetRecordCollectingListener(HSSFListener hSSFListener) {
            this.childListener = hSSFListener;
        }

        public BoundSheetRecord[] getBoundSheetRecords() {
            return (BoundSheetRecord[]) this.boundSheetRecords.toArray(new BoundSheetRecord[0]);
        }

        public ExternSheetRecord[] getExternSheetRecords() {
            return (ExternSheetRecord[]) this.externSheetRecords.toArray(new ExternSheetRecord[0]);
        }

        public SSTRecord getSSTRecord() {
            return this.sstRecord;
        }

        public HSSFWorkbook getStubHSSFWorkbook() {
            HSSFWorkbook create = HSSFWorkbook.create(getStubWorkbook());
            for (BoundSheetRecord sheetname : this.boundSheetRecords) {
                create.createSheet(sheetname.getSheetname());
            }
            return create;
        }

        public InternalWorkbook getStubWorkbook() {
            return EventWorkbookBuilder.createStubWorkbook(getExternSheetRecords(), getBoundSheetRecords(), getSSTRecord());
        }

        public void processRecord(Record record) {
            processRecordInternally(record);
            this.childListener.processRecord(record);
        }

        public void processRecordInternally(Record record) {
            if (record instanceof BoundSheetRecord) {
                this.boundSheetRecords.add((BoundSheetRecord) record);
            } else if (record instanceof ExternSheetRecord) {
                this.externSheetRecords.add((ExternSheetRecord) record);
            } else if (record instanceof SSTRecord) {
                this.sstRecord = (SSTRecord) record;
            }
        }
    }
}
