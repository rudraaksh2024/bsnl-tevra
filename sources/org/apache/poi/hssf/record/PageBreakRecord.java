package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public abstract class PageBreakRecord extends StandardRecord {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private final Map<Integer, Break> _breakMap;
    private final ArrayList<Break> _breaks;

    public abstract PageBreakRecord copy();

    public static final class Break implements GenericRecord {
        public static final int ENCODED_SIZE = 6;
        /* access modifiers changed from: private */
        public int main;
        /* access modifiers changed from: private */
        public int subFrom;
        /* access modifiers changed from: private */
        public int subTo;

        public Break(Break breakR) {
            this.main = breakR.main;
            this.subFrom = breakR.subFrom;
            this.subTo = breakR.subTo;
        }

        public Break(int i, int i2, int i3) {
            this.main = i;
            this.subFrom = i2;
            this.subTo = i3;
        }

        public Break(RecordInputStream recordInputStream) {
            this.main = recordInputStream.readUShort() - 1;
            this.subFrom = recordInputStream.readUShort();
            this.subTo = recordInputStream.readUShort();
        }

        public int getMain() {
            return this.main;
        }

        public int getSubFrom() {
            return this.subFrom;
        }

        public int getSubTo() {
            return this.subTo;
        }

        public void serialize(LittleEndianOutput littleEndianOutput) {
            littleEndianOutput.writeShort(this.main + 1);
            littleEndianOutput.writeShort(this.subFrom);
            littleEndianOutput.writeShort(this.subTo);
        }

        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("main", new PageBreakRecord$Break$$ExternalSyntheticLambda0(this), "subFrom", new PageBreakRecord$Break$$ExternalSyntheticLambda1(this), "subTo", new PageBreakRecord$Break$$ExternalSyntheticLambda2(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-PageBreakRecord$Break  reason: not valid java name */
        public /* synthetic */ Object m2070lambda$getGenericProperties$0$orgapachepoihssfrecordPageBreakRecord$Break() {
            return Integer.valueOf(this.main);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-PageBreakRecord$Break  reason: not valid java name */
        public /* synthetic */ Object m2071lambda$getGenericProperties$1$orgapachepoihssfrecordPageBreakRecord$Break() {
            return Integer.valueOf(this.subFrom);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-PageBreakRecord$Break  reason: not valid java name */
        public /* synthetic */ Object m2072lambda$getGenericProperties$2$orgapachepoihssfrecordPageBreakRecord$Break() {
            return Integer.valueOf(this.subTo);
        }
    }

    protected PageBreakRecord() {
        this._breaks = new ArrayList<>();
        this._breakMap = new HashMap();
    }

    protected PageBreakRecord(PageBreakRecord pageBreakRecord) {
        ArrayList<Break> arrayList = new ArrayList<>();
        this._breaks = arrayList;
        this._breakMap = new HashMap();
        arrayList.addAll(pageBreakRecord._breaks);
        initMap();
    }

    protected PageBreakRecord(RecordInputStream recordInputStream) {
        ArrayList<Break> arrayList = new ArrayList<>();
        this._breaks = arrayList;
        this._breakMap = new HashMap();
        short readShort = recordInputStream.readShort();
        arrayList.ensureCapacity(readShort + 2);
        for (int i = 0; i < readShort; i++) {
            this._breaks.add(new Break(recordInputStream));
        }
        initMap();
    }

    private void initMap() {
        this._breaks.forEach(new PageBreakRecord$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initMap$0$org-apache-poi-hssf-record-PageBreakRecord  reason: not valid java name */
    public /* synthetic */ void m2069lambda$initMap$0$orgapachepoihssfrecordPageBreakRecord(Break breakR) {
        Break put = this._breakMap.put(Integer.valueOf(breakR.main), breakR);
    }

    public boolean isEmpty() {
        return this._breaks.isEmpty();
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this._breaks.size() * 6) + 2;
    }

    public final void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._breaks.size());
        Iterator<Break> it = this._breaks.iterator();
        while (it.hasNext()) {
            it.next().serialize(littleEndianOutput);
        }
    }

    public int getNumBreaks() {
        return this._breaks.size();
    }

    public final Iterator<Break> getBreaksIterator() {
        return this._breaks.iterator();
    }

    public final Spliterator<Break> getBreaksSpliterator() {
        return this._breaks.spliterator();
    }

    public void addBreak(int i, int i2, int i3) {
        Integer valueOf = Integer.valueOf(i);
        Break breakR = this._breakMap.get(valueOf);
        if (breakR == null) {
            Break breakR2 = new Break(i, i2, i3);
            this._breakMap.put(valueOf, breakR2);
            this._breaks.add(breakR2);
            return;
        }
        int unused = breakR.main = i;
        int unused2 = breakR.subFrom = i2;
        int unused3 = breakR.subTo = i3;
    }

    public final void removeBreak(int i) {
        Integer valueOf = Integer.valueOf(i);
        this._breaks.remove(this._breakMap.get(valueOf));
        this._breakMap.remove(valueOf);
    }

    public final Break getBreak(int i) {
        return this._breakMap.get(Integer.valueOf(i));
    }

    public final int[] getBreaks() {
        int numBreaks = getNumBreaks();
        if (numBreaks < 1) {
            return EMPTY_INT_ARRAY;
        }
        int[] iArr = new int[numBreaks];
        for (int i = 0; i < numBreaks; i++) {
            iArr[i] = this._breaks.get(i).main;
        }
        return iArr;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numBreaks", new PageBreakRecord$$ExternalSyntheticLambda0(this), "breaks", new PageBreakRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-PageBreakRecord  reason: not valid java name */
    public /* synthetic */ Object m2068lambda$getGenericProperties$1$orgapachepoihssfrecordPageBreakRecord() {
        return this._breaks;
    }
}
