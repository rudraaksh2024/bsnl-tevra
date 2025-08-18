package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherContainerRecord$$ExternalSyntheticLambda1;
import org.apache.poi.ddf.EscherContainerRecord$$ExternalSyntheticLambda2;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.NullEscherSerializationListener;
import org.apache.poi.hssf.util.LazilyConcatenatedByteArray;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

public abstract class AbstractEscherHolderRecord extends Record {
    private static boolean DESERIALIZE;
    private final List<EscherRecord> escherRecords;
    private final LazilyConcatenatedByteArray rawDataContainer;

    public abstract AbstractEscherHolderRecord copy();

    /* access modifiers changed from: protected */
    public abstract String getRecordName();

    public abstract short getSid();

    static {
        try {
            DESERIALIZE = System.getProperty("poi.deserialize.escher") != null;
        } catch (SecurityException unused) {
            DESERIALIZE = false;
        }
    }

    public AbstractEscherHolderRecord() {
        this.escherRecords = new ArrayList();
        this.rawDataContainer = new LazilyConcatenatedByteArray();
    }

    public AbstractEscherHolderRecord(AbstractEscherHolderRecord abstractEscherHolderRecord) {
        ArrayList arrayList = new ArrayList();
        this.escherRecords = arrayList;
        LazilyConcatenatedByteArray lazilyConcatenatedByteArray = new LazilyConcatenatedByteArray();
        this.rawDataContainer = lazilyConcatenatedByteArray;
        abstractEscherHolderRecord.escherRecords.stream().map(new EscherContainerRecord$$ExternalSyntheticLambda1()).forEach(new EscherContainerRecord$$ExternalSyntheticLambda2(arrayList));
        lazilyConcatenatedByteArray.concatenate(abstractEscherHolderRecord.rawDataContainer);
    }

    public AbstractEscherHolderRecord(RecordInputStream recordInputStream) {
        this.escherRecords = new ArrayList();
        LazilyConcatenatedByteArray lazilyConcatenatedByteArray = new LazilyConcatenatedByteArray();
        this.rawDataContainer = lazilyConcatenatedByteArray;
        if (!DESERIALIZE) {
            lazilyConcatenatedByteArray.concatenate(recordInputStream.readRemainder());
            return;
        }
        byte[] readAllContinuedRemainder = recordInputStream.readAllContinuedRemainder();
        convertToEscherRecords(0, readAllContinuedRemainder.length, readAllContinuedRemainder);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    @Removal(version = "5.3")
    public void convertRawBytesToEscherRecords() {
        if (!DESERIALIZE) {
            decode();
        }
    }

    private void convertToEscherRecords(int i, int i2, byte[] bArr) {
        this.escherRecords.clear();
        DefaultEscherRecordFactory defaultEscherRecordFactory = new DefaultEscherRecordFactory();
        int i3 = i;
        while (i3 < i + i2) {
            EscherRecord createRecord = defaultEscherRecordFactory.createRecord(bArr, i3);
            int fillFields = createRecord.fillFields(bArr, i3, defaultEscherRecordFactory);
            this.escherRecords.add(createRecord);
            i3 += fillFields;
        }
    }

    public int serialize(int i, byte[] bArr) {
        byte[] rawData = getRawData();
        LittleEndian.putShort(bArr, i, getSid());
        int i2 = i + 2;
        LittleEndian.putShort(bArr, i2, (short) (getRecordSize() - 4));
        int i3 = i2 + 2;
        if (!this.escherRecords.isEmpty() || rawData == null) {
            NullEscherSerializationListener nullEscherSerializationListener = new NullEscherSerializationListener();
            for (EscherRecord serialize : this.escherRecords) {
                i3 += serialize.serialize(i3, bArr, nullEscherSerializationListener);
            }
            return getRecordSize();
        }
        System.arraycopy(rawData, 0, bArr, i3, rawData.length);
        return rawData.length + 4;
    }

    public int getRecordSize() {
        byte[] rawData = getRawData();
        if (this.escherRecords.isEmpty() && rawData != null) {
            return rawData.length;
        }
        int i = 0;
        for (EscherRecord recordSize : this.escherRecords) {
            i += recordSize.getRecordSize();
        }
        return i;
    }

    public void addEscherRecord(int i, EscherRecord escherRecord) {
        this.escherRecords.add(i, escherRecord);
    }

    public boolean addEscherRecord(EscherRecord escherRecord) {
        return this.escherRecords.add(escherRecord);
    }

    public List<EscherRecord> getEscherRecords() {
        return this.escherRecords;
    }

    public void clearEscherRecords() {
        this.escherRecords.clear();
    }

    public EscherContainerRecord getEscherContainer() {
        for (EscherRecord next : this.escherRecords) {
            if (next instanceof EscherContainerRecord) {
                return (EscherContainerRecord) next;
            }
        }
        return null;
    }

    public EscherRecord findFirstWithId(short s) {
        return findFirstWithId(s, getEscherRecords());
    }

    private EscherRecord findFirstWithId(short s, List<EscherRecord> list) {
        EscherRecord findFirstWithId;
        for (EscherRecord next : list) {
            if (next.getRecordId() == s) {
                return next;
            }
        }
        for (EscherRecord next2 : list) {
            if (next2.isContainerRecord() && (findFirstWithId = findFirstWithId(s, next2.getChildRecords())) != null) {
                return findFirstWithId;
            }
        }
        return null;
    }

    public EscherRecord getEscherRecord(int i) {
        return this.escherRecords.get(i);
    }

    public void join(AbstractEscherHolderRecord abstractEscherHolderRecord) {
        this.rawDataContainer.concatenate(abstractEscherHolderRecord.getRawData());
    }

    public void processContinueRecord(byte[] bArr) {
        this.rawDataContainer.concatenate(bArr);
    }

    public byte[] getRawData() {
        return this.rawDataContainer.toArray();
    }

    public void setRawData(byte[] bArr) {
        this.rawDataContainer.clear();
        this.rawDataContainer.concatenate(bArr);
    }

    public void decode() {
        if (this.escherRecords.isEmpty()) {
            byte[] rawData = getRawData();
            convertToEscherRecords(0, rawData.length, rawData);
        }
    }

    public List<EscherRecord> getGenericChildren() {
        return this.escherRecords;
    }
}
