package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ColumnInfoRecord extends StandardRecord {
    private static final BitField collapsed = BitFieldFactory.getInstance(4096);
    private static final BitField hidden = BitFieldFactory.getInstance(1);
    private static final BitField outlevel = BitFieldFactory.getInstance(1792);
    public static final short sid = 125;
    private int _colWidth;
    private int _firstCol;
    private int _lastCol;
    private int _options;
    private int _xfIndex;
    private int field_6_reserved;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 12;
    }

    public short getSid() {
        return 125;
    }

    public ColumnInfoRecord() {
        setColumnWidth(2275);
        this._options = 2;
        this._xfIndex = 15;
        this.field_6_reserved = 2;
    }

    public ColumnInfoRecord(ColumnInfoRecord columnInfoRecord) {
        super(columnInfoRecord);
        this._firstCol = columnInfoRecord._firstCol;
        this._lastCol = columnInfoRecord._lastCol;
        this._colWidth = columnInfoRecord._colWidth;
        this._xfIndex = columnInfoRecord._xfIndex;
        this._options = columnInfoRecord._options;
        this.field_6_reserved = columnInfoRecord.field_6_reserved;
    }

    public ColumnInfoRecord(RecordInputStream recordInputStream) {
        this._firstCol = recordInputStream.readUShort();
        this._lastCol = recordInputStream.readUShort();
        this._colWidth = recordInputStream.readUShort();
        this._xfIndex = recordInputStream.readUShort();
        this._options = recordInputStream.readUShort();
        int remaining = recordInputStream.remaining();
        if (remaining == 0) {
            this.field_6_reserved = 0;
        } else if (remaining == 1) {
            this.field_6_reserved = recordInputStream.readByte();
        } else if (remaining == 2) {
            this.field_6_reserved = recordInputStream.readUShort();
        } else {
            throw new RuntimeException("Unusual record size remaining=(" + recordInputStream.remaining() + ")");
        }
    }

    public void setFirstColumn(int i) {
        this._firstCol = i;
    }

    public void setLastColumn(int i) {
        this._lastCol = i;
    }

    public void setColumnWidth(int i) {
        this._colWidth = i;
    }

    public void setXFIndex(int i) {
        this._xfIndex = i;
    }

    public void setHidden(boolean z) {
        this._options = hidden.setBoolean(this._options, z);
    }

    public void setOutlineLevel(int i) {
        this._options = outlevel.setValue(this._options, i);
    }

    public void setCollapsed(boolean z) {
        this._options = collapsed.setBoolean(this._options, z);
    }

    public int getFirstColumn() {
        return this._firstCol;
    }

    public int getLastColumn() {
        return this._lastCol;
    }

    public int getColumnWidth() {
        return this._colWidth;
    }

    public int getXFIndex() {
        return this._xfIndex;
    }

    public boolean getHidden() {
        return hidden.isSet(this._options);
    }

    public int getOutlineLevel() {
        return outlevel.getValue(this._options);
    }

    public boolean getCollapsed() {
        return collapsed.isSet(this._options);
    }

    public boolean containsColumn(int i) {
        return this._firstCol <= i && i <= this._lastCol;
    }

    public boolean isAdjacentBefore(ColumnInfoRecord columnInfoRecord) {
        return this._lastCol == columnInfoRecord._firstCol - 1;
    }

    public boolean formatMatches(ColumnInfoRecord columnInfoRecord) {
        if (this._xfIndex == columnInfoRecord._xfIndex && this._options == columnInfoRecord._options && this._colWidth == columnInfoRecord._colWidth) {
            return true;
        }
        return false;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getFirstColumn());
        littleEndianOutput.writeShort(getLastColumn());
        littleEndianOutput.writeShort(getColumnWidth());
        littleEndianOutput.writeShort(getXFIndex());
        littleEndianOutput.writeShort(this._options);
        littleEndianOutput.writeShort(this.field_6_reserved);
    }

    public ColumnInfoRecord copy() {
        return new ColumnInfoRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.COLUMN_INFO;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("firstColumn", new ColumnInfoRecord$$ExternalSyntheticLambda0(this), "lastColumn", new ColumnInfoRecord$$ExternalSyntheticLambda1(this), "columnWidth", new ColumnInfoRecord$$ExternalSyntheticLambda2(this), "xfIndex", new ColumnInfoRecord$$ExternalSyntheticLambda3(this), "options", new ColumnInfoRecord$$ExternalSyntheticLambda4(this), CellUtil.HIDDEN, new ColumnInfoRecord$$ExternalSyntheticLambda5(this), "outlineLevel", new ColumnInfoRecord$$ExternalSyntheticLambda6(this), "collapsed", new ColumnInfoRecord$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ColumnInfoRecord  reason: not valid java name */
    public /* synthetic */ Object m1986lambda$getGenericProperties$0$orgapachepoihssfrecordColumnInfoRecord() {
        return Integer.valueOf(this._options);
    }
}
