package org.apache.poi.hssf.eventusermodel;

import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingRowDummyRecord;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.MulBlankRecord;
import org.apache.poi.hssf.record.MulRKRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactory;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.StringRecord;

public final class MissingRecordAwareHSSFListener implements HSSFListener {
    private HSSFListener childListener;
    private int lastCellColumn;
    private int lastCellRow;
    private int lastRowRow;

    public MissingRecordAwareHSSFListener(HSSFListener hSSFListener) {
        resetCounts();
        this.childListener = hSSFListener;
    }

    public void processRecord(Record record) {
        int i;
        int i2;
        CellValueRecordInterface[] cellValueRecordInterfaceArr = null;
        if (record instanceof CellValueRecordInterface) {
            CellValueRecordInterface cellValueRecordInterface = (CellValueRecordInterface) record;
            i = cellValueRecordInterface.getRow();
            i2 = cellValueRecordInterface.getColumn();
        } else if (record instanceof StringRecord) {
            this.childListener.processRecord(record);
            return;
        } else {
            short sid = record.getSid();
            if (sid != 28) {
                if (sid == 520) {
                    RowRecord rowRecord = (RowRecord) record;
                    if (this.lastRowRow + 1 < rowRecord.getRowNumber()) {
                        int i3 = this.lastRowRow;
                        while (true) {
                            i3++;
                            if (i3 >= rowRecord.getRowNumber()) {
                                break;
                            }
                            this.childListener.processRecord(new MissingRowDummyRecord(i3));
                        }
                    }
                    this.lastRowRow = rowRecord.getRowNumber();
                    this.lastCellColumn = -1;
                } else if (sid == 1212) {
                    this.childListener.processRecord(record);
                    return;
                } else if (sid == 2057) {
                    BOFRecord bOFRecord = (BOFRecord) record;
                    if (bOFRecord.getType() == 5 || bOFRecord.getType() == 16) {
                        resetCounts();
                    }
                } else if (sid == 189) {
                    cellValueRecordInterfaceArr = RecordFactory.convertRKRecords((MulRKRecord) record);
                } else if (sid == 190) {
                    cellValueRecordInterfaceArr = RecordFactory.convertBlankRecords((MulBlankRecord) record);
                }
                i2 = -1;
                i = -1;
            } else {
                NoteRecord noteRecord = (NoteRecord) record;
                i = noteRecord.getRow();
                i2 = noteRecord.getColumn();
            }
        }
        if (cellValueRecordInterfaceArr != null && cellValueRecordInterfaceArr.length > 0) {
            i = cellValueRecordInterfaceArr[0].getRow();
            i2 = cellValueRecordInterfaceArr[0].getColumn();
        }
        int i4 = this.lastCellRow;
        if (i != i4 && i > 0) {
            if (i4 == -1) {
                this.lastCellRow = 0;
            }
            int i5 = this.lastCellRow;
            while (i5 < i) {
                this.childListener.processRecord(new LastCellOfRowDummyRecord(i5, i5 == this.lastCellRow ? this.lastCellColumn : -1));
                i5++;
            }
        }
        if (!(this.lastCellRow == -1 || this.lastCellColumn == -1 || i != -1)) {
            this.childListener.processRecord(new LastCellOfRowDummyRecord(this.lastCellRow, this.lastCellColumn));
            this.lastCellRow = -1;
            this.lastCellColumn = -1;
        }
        if (i != this.lastCellRow) {
            this.lastCellColumn = -1;
        }
        int i6 = this.lastCellColumn;
        if (i6 != i2 - 1) {
            while (true) {
                i6++;
                if (i6 >= i2) {
                    break;
                }
                this.childListener.processRecord(new MissingCellDummyRecord(i, i6));
            }
        }
        if (cellValueRecordInterfaceArr != null && cellValueRecordInterfaceArr.length > 0) {
            i2 = cellValueRecordInterfaceArr[cellValueRecordInterfaceArr.length - 1].getColumn();
        }
        if (i2 != -1) {
            this.lastCellColumn = i2;
            this.lastCellRow = i;
        }
        if (cellValueRecordInterfaceArr == null || cellValueRecordInterfaceArr.length <= 0) {
            this.childListener.processRecord(record);
            return;
        }
        for (CellValueRecordInterface cellValueRecordInterface2 : cellValueRecordInterfaceArr) {
            this.childListener.processRecord((Record) cellValueRecordInterface2);
        }
    }

    private void resetCounts() {
        this.lastRowRow = -1;
        this.lastCellRow = -1;
        this.lastCellColumn = -1;
    }
}
