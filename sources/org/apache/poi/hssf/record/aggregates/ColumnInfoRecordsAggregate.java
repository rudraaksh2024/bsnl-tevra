package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.ColumnInfoRecord;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;

public final class ColumnInfoRecordsAggregate extends RecordAggregate implements Duplicatable {
    private final List<ColumnInfoRecord> records;

    public ColumnInfoRecordsAggregate() {
        this.records = new ArrayList();
    }

    public ColumnInfoRecordsAggregate(ColumnInfoRecordsAggregate columnInfoRecordsAggregate) {
        ArrayList arrayList = new ArrayList();
        this.records = arrayList;
        columnInfoRecordsAggregate.records.stream().map(new ColumnInfoRecordsAggregate$$ExternalSyntheticLambda0()).forEach(new ColumnInfoRecordsAggregate$$ExternalSyntheticLambda1(arrayList));
    }

    public ColumnInfoRecordsAggregate(RecordStream recordStream) {
        this();
        ColumnInfoRecord columnInfoRecord = null;
        boolean z = true;
        while (recordStream.peekNextClass() == ColumnInfoRecord.class) {
            ColumnInfoRecord columnInfoRecord2 = (ColumnInfoRecord) recordStream.getNext();
            this.records.add(columnInfoRecord2);
            if (columnInfoRecord != null && compareColInfos(columnInfoRecord, columnInfoRecord2) > 0) {
                z = false;
            }
            columnInfoRecord = columnInfoRecord2;
        }
        if (this.records.size() < 1) {
            throw new RuntimeException("No column info records found");
        } else if (!z) {
            this.records.sort(new ColumnInfoRecordsAggregate$$ExternalSyntheticLambda2());
        }
    }

    public ColumnInfoRecordsAggregate copy() {
        return new ColumnInfoRecordsAggregate(this);
    }

    public void insertColumn(ColumnInfoRecord columnInfoRecord) {
        this.records.add(columnInfoRecord);
        this.records.sort(new ColumnInfoRecordsAggregate$$ExternalSyntheticLambda2());
    }

    private void insertColumn(int i, ColumnInfoRecord columnInfoRecord) {
        this.records.add(i, columnInfoRecord);
    }

    /* access modifiers changed from: package-private */
    public int getNumColumns() {
        return this.records.size();
    }

    public void visitContainedRecords(RecordAggregate.RecordVisitor recordVisitor) {
        if (this.records.size() >= 1) {
            ColumnInfoRecord columnInfoRecord = null;
            for (ColumnInfoRecord next : this.records) {
                recordVisitor.visitRecord(next);
                if (columnInfoRecord == null || compareColInfos(columnInfoRecord, next) <= 0) {
                    columnInfoRecord = next;
                } else {
                    throw new RuntimeException("Column info records are out of order");
                }
            }
        }
    }

    private int findStartOfColumnOutlineGroup(int i) {
        ColumnInfoRecord columnInfoRecord = this.records.get(i);
        int outlineLevel = columnInfoRecord.getOutlineLevel();
        while (i != 0) {
            ColumnInfoRecord columnInfoRecord2 = this.records.get(i - 1);
            if (!columnInfoRecord2.isAdjacentBefore(columnInfoRecord) || columnInfoRecord2.getOutlineLevel() < outlineLevel) {
                break;
            }
            i--;
            columnInfoRecord = columnInfoRecord2;
        }
        return i;
    }

    private int findEndOfColumnOutlineGroup(int i) {
        ColumnInfoRecord columnInfoRecord = this.records.get(i);
        int outlineLevel = columnInfoRecord.getOutlineLevel();
        while (i < this.records.size() - 1) {
            int i2 = i + 1;
            ColumnInfoRecord columnInfoRecord2 = this.records.get(i2);
            if (!columnInfoRecord.isAdjacentBefore(columnInfoRecord2) || columnInfoRecord2.getOutlineLevel() < outlineLevel) {
                break;
            }
            columnInfoRecord = columnInfoRecord2;
            i = i2;
        }
        return i;
    }

    private ColumnInfoRecord getColInfo(int i) {
        return this.records.get(i);
    }

    private boolean isColumnGroupCollapsed(int i) {
        int findEndOfColumnOutlineGroup = findEndOfColumnOutlineGroup(i);
        int i2 = findEndOfColumnOutlineGroup + 1;
        if (i2 >= this.records.size()) {
            return false;
        }
        ColumnInfoRecord colInfo = getColInfo(i2);
        if (!getColInfo(findEndOfColumnOutlineGroup).isAdjacentBefore(colInfo)) {
            return false;
        }
        return colInfo.getCollapsed();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isColumnGroupHiddenByParent(int r5) {
        /*
            r4 = this;
            int r0 = r4.findEndOfColumnOutlineGroup(r5)
            java.util.List<org.apache.poi.hssf.record.ColumnInfoRecord> r1 = r4.records
            int r1 = r1.size()
            r2 = 0
            if (r0 >= r1) goto L_0x0026
            int r1 = r0 + 1
            org.apache.poi.hssf.record.ColumnInfoRecord r1 = r4.getColInfo(r1)
            org.apache.poi.hssf.record.ColumnInfoRecord r0 = r4.getColInfo(r0)
            boolean r0 = r0.isAdjacentBefore(r1)
            if (r0 == 0) goto L_0x0026
            int r0 = r1.getOutlineLevel()
            boolean r1 = r1.getHidden()
            goto L_0x0028
        L_0x0026:
            r0 = r2
            r1 = r0
        L_0x0028:
            int r5 = r4.findStartOfColumnOutlineGroup(r5)
            if (r5 <= 0) goto L_0x0047
            int r3 = r5 + -1
            org.apache.poi.hssf.record.ColumnInfoRecord r3 = r4.getColInfo(r3)
            org.apache.poi.hssf.record.ColumnInfoRecord r4 = r4.getColInfo(r5)
            boolean r4 = r3.isAdjacentBefore(r4)
            if (r4 == 0) goto L_0x0047
            int r2 = r3.getOutlineLevel()
            boolean r4 = r3.getHidden()
            goto L_0x0048
        L_0x0047:
            r4 = r2
        L_0x0048:
            if (r0 <= r2) goto L_0x004b
            return r1
        L_0x004b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.aggregates.ColumnInfoRecordsAggregate.isColumnGroupHiddenByParent(int):boolean");
    }

    public void collapseColumn(int i) {
        int findColInfoIdx = findColInfoIdx(i, 0);
        if (findColInfoIdx != -1) {
            int findStartOfColumnOutlineGroup = findStartOfColumnOutlineGroup(findColInfoIdx);
            setColumn(setGroupHidden(findStartOfColumnOutlineGroup, getColInfo(findStartOfColumnOutlineGroup).getOutlineLevel(), true) + 1, (Short) null, (Integer) null, (Integer) null, (Boolean) null, Boolean.TRUE);
        }
    }

    private int setGroupHidden(int i, int i2, boolean z) {
        ColumnInfoRecord colInfo = getColInfo(i);
        while (i < this.records.size()) {
            colInfo.setHidden(z);
            i++;
            if (i < this.records.size()) {
                ColumnInfoRecord colInfo2 = getColInfo(i);
                if (!colInfo.isAdjacentBefore(colInfo2) || colInfo2.getOutlineLevel() < i2) {
                    break;
                }
                colInfo = colInfo2;
            }
        }
        return colInfo.getLastColumn();
    }

    public void expandColumn(int i) {
        int findColInfoIdx = findColInfoIdx(i, 0);
        if (findColInfoIdx != -1 && isColumnGroupCollapsed(findColInfoIdx)) {
            int findEndOfColumnOutlineGroup = findEndOfColumnOutlineGroup(findColInfoIdx);
            ColumnInfoRecord colInfo = getColInfo(findEndOfColumnOutlineGroup);
            if (!isColumnGroupHiddenByParent(findColInfoIdx)) {
                int outlineLevel = colInfo.getOutlineLevel();
                for (int findStartOfColumnOutlineGroup = findStartOfColumnOutlineGroup(findColInfoIdx); findStartOfColumnOutlineGroup <= findEndOfColumnOutlineGroup; findStartOfColumnOutlineGroup++) {
                    ColumnInfoRecord colInfo2 = getColInfo(findStartOfColumnOutlineGroup);
                    if (outlineLevel == colInfo2.getOutlineLevel()) {
                        colInfo2.setHidden(false);
                    }
                }
            }
            setColumn(colInfo.getLastColumn() + 1, (Short) null, (Integer) null, (Integer) null, (Boolean) null, Boolean.FALSE);
        }
    }

    private static ColumnInfoRecord copyColInfo(ColumnInfoRecord columnInfoRecord) {
        return columnInfoRecord.copy();
    }

    public void setColumn(int i, Short sh, Integer num, Integer num2, Boolean bool, Boolean bool2) {
        ColumnInfoRecord columnInfoRecord;
        int i2 = i;
        boolean z = false;
        int i3 = 0;
        while (true) {
            if (i3 >= this.records.size()) {
                break;
            }
            columnInfoRecord = this.records.get(i3);
            if (columnInfoRecord.containsColumn(i)) {
                break;
            } else if (columnInfoRecord.getFirstColumn() > i2) {
                break;
            } else {
                i3++;
            }
        }
        columnInfoRecord = null;
        ColumnInfoRecord columnInfoRecord2 = columnInfoRecord;
        if (columnInfoRecord2 == null) {
            ColumnInfoRecord columnInfoRecord3 = new ColumnInfoRecord();
            columnInfoRecord3.setFirstColumn(i);
            columnInfoRecord3.setLastColumn(i);
            setColumnInfoFields(columnInfoRecord3, sh, num, num2, bool, bool2);
            insertColumn(i3, columnInfoRecord3);
            attemptMergeColInfoRecords(i3);
            return;
        }
        boolean z2 = (sh == null || columnInfoRecord2.getXFIndex() == sh.shortValue()) ? false : true;
        boolean z3 = (num == null || columnInfoRecord2.getColumnWidth() == num.shortValue()) ? false : true;
        boolean z4 = (num2 == null || columnInfoRecord2.getOutlineLevel() == num2.intValue()) ? false : true;
        boolean z5 = (bool == null || columnInfoRecord2.getHidden() == bool.booleanValue()) ? false : true;
        boolean z6 = (bool2 == null || columnInfoRecord2.getCollapsed() == bool2.booleanValue()) ? false : true;
        if (z2 || z3 || z4 || z5 || z6) {
            z = true;
        }
        if (z) {
            if (columnInfoRecord2.getFirstColumn() == i2 && columnInfoRecord2.getLastColumn() == i2) {
                setColumnInfoFields(columnInfoRecord2, sh, num, num2, bool, bool2);
                attemptMergeColInfoRecords(i3);
            } else if (columnInfoRecord2.getFirstColumn() == i2 || columnInfoRecord2.getLastColumn() == i2) {
                if (columnInfoRecord2.getFirstColumn() == i2) {
                    columnInfoRecord2.setFirstColumn(i2 + 1);
                } else {
                    columnInfoRecord2.setLastColumn(i2 - 1);
                    i3++;
                }
                ColumnInfoRecord copyColInfo = copyColInfo(columnInfoRecord2);
                copyColInfo.setFirstColumn(i);
                copyColInfo.setLastColumn(i);
                setColumnInfoFields(copyColInfo, sh, num, num2, bool, bool2);
                insertColumn(i3, copyColInfo);
                attemptMergeColInfoRecords(i3);
            } else {
                ColumnInfoRecord copyColInfo2 = copyColInfo(columnInfoRecord2);
                ColumnInfoRecord copyColInfo3 = copyColInfo(columnInfoRecord2);
                int lastColumn = columnInfoRecord2.getLastColumn();
                columnInfoRecord2.setLastColumn(i2 - 1);
                copyColInfo2.setFirstColumn(i);
                copyColInfo2.setLastColumn(i);
                setColumnInfoFields(copyColInfo2, sh, num, num2, bool, bool2);
                int i4 = i3 + 1;
                insertColumn(i4, copyColInfo2);
                copyColInfo3.setFirstColumn(i2 + 1);
                copyColInfo3.setLastColumn(lastColumn);
                insertColumn(i4 + 1, copyColInfo3);
            }
        }
    }

    private static void setColumnInfoFields(ColumnInfoRecord columnInfoRecord, Short sh, Integer num, Integer num2, Boolean bool, Boolean bool2) {
        if (sh != null) {
            columnInfoRecord.setXFIndex(sh.shortValue());
        }
        if (num != null) {
            columnInfoRecord.setColumnWidth(num.intValue());
        }
        if (num2 != null) {
            columnInfoRecord.setOutlineLevel(num2.shortValue());
        }
        if (bool != null) {
            columnInfoRecord.setHidden(bool.booleanValue());
        }
        if (bool2 != null) {
            columnInfoRecord.setCollapsed(bool2.booleanValue());
        }
    }

    private int findColInfoIdx(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("column parameter out of range: " + i);
        } else if (i2 >= 0) {
            while (i2 < this.records.size()) {
                ColumnInfoRecord colInfo = getColInfo(i2);
                if (colInfo.containsColumn(i)) {
                    return i2;
                }
                if (colInfo.getFirstColumn() > i) {
                    return -1;
                }
                i2++;
            }
            return -1;
        } else {
            throw new IllegalArgumentException("fromIdx parameter out of range: " + i2);
        }
    }

    private void attemptMergeColInfoRecords(int i) {
        int size = this.records.size();
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("colInfoIx " + i + " is out of range (0.." + (size - 1) + ")");
        }
        ColumnInfoRecord colInfo = getColInfo(i);
        int i2 = i + 1;
        if (i2 < size && mergeColInfoRecords(colInfo, getColInfo(i2))) {
            this.records.remove(i2);
        }
        if (i > 0 && mergeColInfoRecords(getColInfo(i - 1), colInfo)) {
            this.records.remove(i);
        }
    }

    private static boolean mergeColInfoRecords(ColumnInfoRecord columnInfoRecord, ColumnInfoRecord columnInfoRecord2) {
        if (!columnInfoRecord.isAdjacentBefore(columnInfoRecord2) || !columnInfoRecord.formatMatches(columnInfoRecord2)) {
            return false;
        }
        columnInfoRecord.setLastColumn(columnInfoRecord2.getLastColumn());
        return true;
    }

    public void groupColumnRange(int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5 = 0;
        while (i <= i2) {
            int findColInfoIdx = findColInfoIdx(i, i5);
            if (findColInfoIdx != -1) {
                int outlineLevel = getColInfo(findColInfoIdx).getOutlineLevel();
                i4 = Math.min(7, Math.max(0, z ? outlineLevel + 1 : outlineLevel - 1));
                i3 = Math.max(0, findColInfoIdx - 1);
            } else {
                i3 = i5;
                i4 = 1;
            }
            setColumn(i, (Short) null, (Integer) null, Integer.valueOf(i4), (Boolean) null, (Boolean) null);
            i++;
            i5 = i3;
        }
    }

    public ColumnInfoRecord findColumnInfo(int i) {
        int size = this.records.size();
        for (int i2 = 0; i2 < size; i2++) {
            ColumnInfoRecord colInfo = getColInfo(i2);
            if (colInfo.containsColumn(i)) {
                return colInfo;
            }
        }
        return null;
    }

    public int getMaxOutlineLevel() {
        int size = this.records.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(getColInfo(i2).getOutlineLevel(), i);
        }
        return i;
    }

    public int getOutlineLevel(int i) {
        ColumnInfoRecord findColumnInfo = findColumnInfo(i);
        if (findColumnInfo != null) {
            return findColumnInfo.getOutlineLevel();
        }
        return 0;
    }

    public int getMinColumnIndex() {
        if (this.records.isEmpty()) {
            return 0;
        }
        int size = this.records.size();
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.min(i, getColInfo(i2).getFirstColumn());
        }
        return i;
    }

    public int getMaxColumnIndex() {
        if (this.records.isEmpty()) {
            return 0;
        }
        int size = this.records.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, getColInfo(i2).getLastColumn());
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static int compareColInfos(ColumnInfoRecord columnInfoRecord, ColumnInfoRecord columnInfoRecord2) {
        return columnInfoRecord.getFirstColumn() - columnInfoRecord2.getFirstColumn();
    }
}
