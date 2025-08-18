package org.apache.poi.ddf;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndian;

public final class EscherContainerRecord extends EscherRecord implements Iterable<EscherRecord> {
    public static final short BSTORE_CONTAINER = EscherRecordTypes.BSTORE_CONTAINER.typeID;
    public static final short DGG_CONTAINER = EscherRecordTypes.DGG_CONTAINER.typeID;
    public static final short DG_CONTAINER = EscherRecordTypes.DG_CONTAINER.typeID;
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) EscherContainerRecord.class);
    public static final short SOLVER_CONTAINER = EscherRecordTypes.SOLVER_CONTAINER.typeID;
    public static final short SPGR_CONTAINER = EscherRecordTypes.SPGR_CONTAINER.typeID;
    public static final short SP_CONTAINER = EscherRecordTypes.SP_CONTAINER.typeID;
    private final List<EscherRecord> _childRecords;
    private int _remainingLength;

    public EscherContainerRecord() {
        this._childRecords = new ArrayList();
    }

    public EscherContainerRecord(EscherContainerRecord escherContainerRecord) {
        super(escherContainerRecord);
        ArrayList arrayList = new ArrayList();
        this._childRecords = arrayList;
        this._remainingLength = escherContainerRecord._remainingLength;
        escherContainerRecord._childRecords.stream().map(new EscherContainerRecord$$ExternalSyntheticLambda1()).forEach(new EscherContainerRecord$$ExternalSyntheticLambda2(arrayList));
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        int i2 = 8;
        int i3 = i + 8;
        while (readHeader > 0 && i3 < bArr.length) {
            EscherRecord createRecord = escherRecordFactory.createRecord(bArr, i3);
            int fillFields = createRecord.fillFields(bArr, i3, escherRecordFactory);
            i2 += fillFields;
            i3 += fillFields;
            readHeader -= fillFields;
            addChildRecord(createRecord);
            if (i3 >= bArr.length && readHeader > 0) {
                this._remainingLength = readHeader;
                LOGGER.atWarn().log("Not enough Escher data: {} bytes remaining but no space left", (Object) Unbox.box(readHeader));
            }
        }
        return i2;
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        Iterator<EscherRecord> it = iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += it.next().getRecordSize();
        }
        LittleEndian.putInt(bArr, i + 4, i2 + this._remainingLength);
        int i3 = i + 8;
        Iterator<EscherRecord> it2 = iterator();
        while (it2.hasNext()) {
            i3 += it2.next().serialize(i3, bArr, escherSerializationListener);
        }
        int i4 = i3 - i;
        escherSerializationListener.afterRecordSerialize(i3, getRecordId(), i4, this);
        return i4;
    }

    public int getRecordSize() {
        Iterator<EscherRecord> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().getRecordSize();
        }
        return i + 8;
    }

    static /* synthetic */ boolean lambda$hasChildOfType$0(short s, EscherRecord escherRecord) {
        return escherRecord.getRecordId() == s;
    }

    public boolean hasChildOfType(short s) {
        return this._childRecords.stream().anyMatch(new EscherContainerRecord$$ExternalSyntheticLambda0(s));
    }

    public EscherRecord getChild(int i) {
        return this._childRecords.get(i);
    }

    public List<EscherRecord> getChildRecords() {
        return new ArrayList(this._childRecords);
    }

    public int getChildCount() {
        return this._childRecords.size();
    }

    public Iterator<EscherRecord> iterator() {
        return Collections.unmodifiableList(this._childRecords).iterator();
    }

    public Spliterator<EscherRecord> spliterator() {
        return this._childRecords.spliterator();
    }

    public void setChildRecords(List<EscherRecord> list) {
        List<EscherRecord> list2 = this._childRecords;
        if (list != list2) {
            list2.clear();
            this._childRecords.addAll(list);
            return;
        }
        throw new IllegalStateException("Child records private data member has escaped");
    }

    public boolean removeChildRecord(EscherRecord escherRecord) {
        return this._childRecords.remove(escherRecord);
    }

    public List<EscherContainerRecord> getChildContainers() {
        ArrayList arrayList = new ArrayList();
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            EscherRecord next = it.next();
            if (next instanceof EscherContainerRecord) {
                arrayList.add((EscherContainerRecord) next);
            }
        }
        return arrayList;
    }

    public String getRecordName() {
        short recordId = getRecordId();
        EscherRecordTypes forTypeID = EscherRecordTypes.forTypeID(recordId);
        return forTypeID != EscherRecordTypes.UNKNOWN ? forTypeID.recordName : "Container 0x" + HexDump.toHex(recordId);
    }

    public void display(PrintWriter printWriter, int i) {
        super.display(printWriter, i);
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            it.next().display(printWriter, i + 1);
        }
    }

    public void addChildRecord(EscherRecord escherRecord) {
        this._childRecords.add(escherRecord);
    }

    public void addChildBefore(EscherRecord escherRecord, int i) {
        Iterator<EscherRecord> it = iterator();
        int i2 = 0;
        while (it.hasNext() && it.next().getRecordId() != ((short) i)) {
            i2++;
        }
        this._childRecords.add(i2, escherRecord);
    }

    public <T extends EscherRecord> T getChildById(short s) {
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            T t = (EscherRecord) it.next();
            if (t.getRecordId() == s) {
                return t;
            }
        }
        return null;
    }

    public void getRecordsById(short s, List<EscherRecord> list) {
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            EscherRecord next = it.next();
            if (next instanceof EscherContainerRecord) {
                ((EscherContainerRecord) next).getRecordsById(s, list);
            } else if (next.getRecordId() == s) {
                list.add(next);
            }
        }
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherContainerRecord$$ExternalSyntheticLambda3(this), "isContainer", new EscherContainerRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ddf-EscherContainerRecord  reason: not valid java name */
    public /* synthetic */ Object m1956lambda$getGenericProperties$1$orgapachepoiddfEscherContainerRecord() {
        return super.getGenericProperties();
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.forTypeID(getRecordId());
    }

    public EscherContainerRecord copy() {
        return new EscherContainerRecord(this);
    }
}
