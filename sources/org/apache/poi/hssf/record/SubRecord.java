package org.apache.poi.hssf.record;

import com.google.firebase.messaging.Constants;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.LittleEndianOutputStream;

public abstract class SubRecord implements Duplicatable, GenericRecord {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 1000000;
    /* access modifiers changed from: private */
    public static int MAX_RECORD_LENGTH = 1000000;

    public abstract SubRecord copy();

    /* access modifiers changed from: protected */
    public abstract int getDataSize();

    public abstract SubRecordTypes getGenericRecordType();

    public boolean isTerminating() {
        return false;
    }

    public abstract void serialize(LittleEndianOutput littleEndianOutput);

    public enum SubRecordTypes {
        UNKNOWN(-1, new SubRecord$SubRecordTypes$$ExternalSyntheticLambda0()),
        END(0, new SubRecord$SubRecordTypes$$ExternalSyntheticLambda3()),
        GROUP_MARKER(6, new SubRecord$SubRecordTypes$$ExternalSyntheticLambda4()),
        FT_CF(7, new SubRecord$SubRecordTypes$$ExternalSyntheticLambda5()),
        FT_PIO_GRBIT(8, new SubRecord$SubRecordTypes$$ExternalSyntheticLambda6()),
        EMBEDDED_OBJECT_REF(9, new SubRecord$SubRecordTypes$$ExternalSyntheticLambda7()),
        FT_CBLS(12, new SubRecord$SubRecordTypes$$ExternalSyntheticLambda8()),
        NOTE_STRUCTURE(13, new SubRecord$SubRecordTypes$$ExternalSyntheticLambda9()),
        LBS_DATA(19, new SubRecord$SubRecordTypes$$ExternalSyntheticLambda10()),
        COMMON_OBJECT_DATA(21, new SubRecord$SubRecordTypes$$ExternalSyntheticLambda1());
        
        private static final Map<Short, SubRecordTypes> LOOKUP = null;
        public final RecordConstructor<?> recordConstructor;
        public final short sid;

        @FunctionalInterface
        public interface RecordConstructor<T extends SubRecord> {
            T apply(LittleEndianInput littleEndianInput, int i, int i2);
        }

        static {
            LOOKUP = (Map) Arrays.stream(values()).collect(Collectors.toMap(new SubRecord$SubRecordTypes$$ExternalSyntheticLambda2(), Function.identity()));
        }

        private SubRecordTypes(int i, RecordConstructor<?> recordConstructor2) {
            this.sid = (short) i;
            this.recordConstructor = recordConstructor2;
        }

        public static SubRecordTypes forSID(int i) {
            return LOOKUP.getOrDefault(Short.valueOf((short) i), UNKNOWN);
        }

        public short getSid() {
            return this.sid;
        }
    }

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    protected SubRecord() {
    }

    protected SubRecord(SubRecord subRecord) {
    }

    public static SubRecord createSubRecord(LittleEndianInput littleEndianInput, int i) {
        int readUShort = littleEndianInput.readUShort();
        int readUShort2 = littleEndianInput.readUShort();
        SubRecordTypes forSID = SubRecordTypes.forSID(readUShort);
        SubRecordTypes.RecordConstructor recordConstructor = forSID.recordConstructor;
        if (forSID == SubRecordTypes.UNKNOWN) {
            i = readUShort;
        }
        return recordConstructor.apply(littleEndianInput, readUShort2, i);
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public byte[] serialize() {
        int dataSize = getDataSize() + 4;
        UnsynchronizedByteArrayOutputStream unsynchronizedByteArrayOutputStream = new UnsynchronizedByteArrayOutputStream(dataSize);
        serialize(new LittleEndianOutputStream(unsynchronizedByteArrayOutputStream));
        if (unsynchronizedByteArrayOutputStream.size() == dataSize) {
            return unsynchronizedByteArrayOutputStream.toByteArray();
        }
        throw new RuntimeException("write size mismatch");
    }

    private static final class UnknownSubRecord extends SubRecord {
        private final byte[] _data;
        private final int _sid;

        public UnknownSubRecord copy() {
            return this;
        }

        public UnknownSubRecord(LittleEndianInput littleEndianInput, int i, int i2) {
            this._sid = i2;
            byte[] safelyAllocate = IOUtils.safelyAllocate((long) i, SubRecord.MAX_RECORD_LENGTH);
            littleEndianInput.readFully(safelyAllocate);
            this._data = safelyAllocate;
        }

        /* access modifiers changed from: protected */
        public int getDataSize() {
            return this._data.length;
        }

        public void serialize(LittleEndianOutput littleEndianOutput) {
            littleEndianOutput.writeShort(this._sid);
            littleEndianOutput.writeShort(this._data.length);
            littleEndianOutput.write(this._data);
        }

        public SubRecordTypes getGenericRecordType() {
            return SubRecordTypes.UNKNOWN;
        }

        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("sid", new SubRecord$UnknownSubRecord$$ExternalSyntheticLambda0(this), Constants.ScionAnalytics.MessageType.DATA_MESSAGE, new SubRecord$UnknownSubRecord$$ExternalSyntheticLambda1(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-SubRecord$UnknownSubRecord  reason: not valid java name */
        public /* synthetic */ Object m2093lambda$getGenericProperties$0$orgapachepoihssfrecordSubRecord$UnknownSubRecord() {
            return Integer.valueOf(this._sid);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-SubRecord$UnknownSubRecord  reason: not valid java name */
        public /* synthetic */ Object m2094lambda$getGenericProperties$1$orgapachepoihssfrecordSubRecord$UnknownSubRecord() {
            return this._data;
        }
    }
}
