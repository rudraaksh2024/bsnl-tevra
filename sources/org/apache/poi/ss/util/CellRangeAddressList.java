package org.apache.poi.ss.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.LittleEndianOutput;

public class CellRangeAddressList implements GenericRecord {
    protected final List<CellRangeAddress> _list = new ArrayList();

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    public CellRangeAddressList() {
    }

    public CellRangeAddressList(int i, int i2, int i3, int i4) {
        addCellRangeAddress(i, i3, i2, i4);
    }

    public CellRangeAddressList(RecordInputStream recordInputStream) {
        int readUShort = recordInputStream.readUShort();
        for (int i = 0; i < readUShort; i++) {
            this._list.add(new CellRangeAddress(recordInputStream));
        }
    }

    public int countRanges() {
        return this._list.size();
    }

    public void addCellRangeAddress(int i, int i2, int i3, int i4) {
        addCellRangeAddress(new CellRangeAddress(i, i3, i2, i4));
    }

    public void addCellRangeAddress(CellRangeAddress cellRangeAddress) {
        this._list.add(cellRangeAddress);
    }

    public CellRangeAddress remove(int i) {
        if (this._list.isEmpty()) {
            throw new RuntimeException("List is empty");
        } else if (i >= 0 && i < this._list.size()) {
            return this._list.remove(i);
        } else {
            throw new RuntimeException("Range index (" + i + ") is outside allowable range (0.." + (this._list.size() - 1) + ")");
        }
    }

    public CellRangeAddress getCellRangeAddress(int i) {
        return this._list.get(i);
    }

    public int getSize() {
        return getEncodedSize(this._list.size());
    }

    public static int getEncodedSize(int i) {
        return CellRangeAddress.getEncodedSize(i) + 2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001b, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int serialize(int r3, byte[] r4) {
        /*
            r2 = this;
            int r0 = r2.getSize()
            org.apache.poi.util.LittleEndianByteArrayOutputStream r1 = new org.apache.poi.util.LittleEndianByteArrayOutputStream     // Catch:{ IOException -> 0x001c }
            r1.<init>(r4, r3, r0)     // Catch:{ IOException -> 0x001c }
            r2.serialize(r1)     // Catch:{ all -> 0x0010 }
            r1.close()     // Catch:{ IOException -> 0x001c }
            return r0
        L_0x0010:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ IOException -> 0x001c }
        L_0x001b:
            throw r3     // Catch:{ IOException -> 0x001c }
        L_0x001c:
            r2 = move-exception
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.util.CellRangeAddressList.serialize(int, byte[]):int");
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._list.size());
        for (CellRangeAddress serialize : this._list) {
            serialize.serialize(littleEndianOutput);
        }
    }

    public CellRangeAddressList copy() {
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList();
        for (CellRangeAddress copy : this._list) {
            cellRangeAddressList.addCellRangeAddress(copy.copy());
        }
        return cellRangeAddressList;
    }

    public CellRangeAddress[] getCellRangeAddresses() {
        CellRangeAddress[] cellRangeAddressArr = new CellRangeAddress[this._list.size()];
        this._list.toArray(cellRangeAddressArr);
        return cellRangeAddressArr;
    }

    public List<CellRangeAddress> getGenericChildren() {
        return this._list;
    }
}
