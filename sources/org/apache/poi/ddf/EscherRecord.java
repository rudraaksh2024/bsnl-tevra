package org.apache.poi.ddf;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.GenericRecordXmlWriter;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;

public abstract class EscherRecord implements Duplicatable, GenericRecord {
    private static final BitField fInstance = BitFieldFactory.getInstance(65520);
    private static final BitField fVersion = BitFieldFactory.getInstance(15);
    private short _options;
    private short _recordId;

    public abstract EscherRecord copy();

    public abstract int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory);

    public abstract String getRecordName();

    public abstract int getRecordSize();

    public abstract int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener);

    public EscherRecord() {
    }

    protected EscherRecord(EscherRecord escherRecord) {
        this._options = escherRecord._options;
        this._recordId = escherRecord._recordId;
    }

    /* access modifiers changed from: protected */
    public int fillFields(byte[] bArr, EscherRecordFactory escherRecordFactory) {
        return fillFields(bArr, 0, escherRecordFactory);
    }

    /* access modifiers changed from: protected */
    public int readHeader(byte[] bArr, int i) {
        this._options = LittleEndian.getShort(bArr, i);
        this._recordId = LittleEndian.getShort(bArr, i + 2);
        return LittleEndian.getInt(bArr, i + 4);
    }

    protected static short readInstance(byte[] bArr, int i) {
        return fInstance.getShortValue(LittleEndian.getShort(bArr, i));
    }

    public boolean isContainerRecord() {
        return getVersion() == 15;
    }

    @Internal
    public short getOptions() {
        return this._options;
    }

    @Internal
    public void setOptions(short s) {
        setVersion(fVersion.getShortValue(s));
        setInstance(fInstance.getShortValue(s));
        this._options = s;
    }

    public byte[] serialize() {
        byte[] bArr = new byte[getRecordSize()];
        serialize(0, bArr);
        return bArr;
    }

    public int serialize(int i, byte[] bArr) {
        return serialize(i, bArr, new NullEscherSerializationListener());
    }

    public short getRecordId() {
        return this._recordId;
    }

    public void setRecordId(short s) {
        this._recordId = s;
    }

    public List<EscherRecord> getChildRecords() {
        return Collections.emptyList();
    }

    public void setChildRecords(List<EscherRecord> list) {
        throw new UnsupportedOperationException("This record does not support child records.");
    }

    public EscherRecord getChild(int i) {
        return getChildRecords().get(i);
    }

    public void display(PrintWriter printWriter, int i) {
        for (int i2 = 0; i2 < i * 4; i2++) {
            printWriter.print(Chars.SPACE);
        }
        printWriter.println(getRecordName());
    }

    public short getInstance() {
        return fInstance.getShortValue(this._options);
    }

    public void setInstance(short s) {
        this._options = fInstance.setShortValue(this._options, s);
    }

    public short getVersion() {
        return fVersion.getShortValue(this._options);
    }

    public void setVersion(short s) {
        this._options = fVersion.setShortValue(this._options, s);
    }

    public String toXml() {
        return toXml("");
    }

    public final String toXml(String str) {
        return GenericRecordXmlWriter.marshal(this);
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public List<? extends GenericRecord> getGenericChildren() {
        return getChildRecords();
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("recordId", new EscherRecord$$ExternalSyntheticLambda0(this), "version", new EscherRecord$$ExternalSyntheticLambda1(this), "instance", new EscherRecord$$ExternalSyntheticLambda2(this), "options", new EscherRecord$$ExternalSyntheticLambda3(this), "recordSize", new EscherRecord$$ExternalSyntheticLambda4(this));
    }
}
