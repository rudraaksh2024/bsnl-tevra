package org.apache.poi.hssf.record;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RecordFormatException;

public final class RecordFactory {
    private static final int DEFAULT_MAX_NUMBER_OF_RECORDS = 5000000;
    private static int MAX_NUMBER_OF_RECORDS = 5000000;
    private static final int NUM_RECORDS = 512;

    public static void setMaxNumberOfRecords(int i) {
        MAX_NUMBER_OF_RECORDS = i;
    }

    public static int getMaxNumberOfRecords() {
        return MAX_NUMBER_OF_RECORDS;
    }

    private RecordFactory() {
    }

    public static Class<? extends Record> getRecordClass(int i) {
        return HSSFRecordTypes.forSID(i).clazz;
    }

    public static Record[] createRecord(RecordInputStream recordInputStream) {
        Record createSingleRecord = createSingleRecord(recordInputStream);
        if (createSingleRecord instanceof DBCellRecord) {
            return new Record[]{null};
        } else if (createSingleRecord instanceof RKRecord) {
            return new Record[]{convertToNumberRecord((RKRecord) createSingleRecord)};
        } else if (createSingleRecord instanceof MulRKRecord) {
            return convertRKRecords((MulRKRecord) createSingleRecord);
        } else {
            return new Record[]{createSingleRecord};
        }
    }

    public static Record createSingleRecord(RecordInputStream recordInputStream) {
        HSSFRecordTypes forSID = HSSFRecordTypes.forSID(recordInputStream.getSid());
        if (!forSID.isParseable()) {
            forSID = HSSFRecordTypes.UNKNOWN;
        }
        return forSID.recordConstructor.apply(recordInputStream);
    }

    public static NumberRecord convertToNumberRecord(RKRecord rKRecord) {
        NumberRecord numberRecord = new NumberRecord();
        numberRecord.setColumn(rKRecord.getColumn());
        numberRecord.setRow(rKRecord.getRow());
        numberRecord.setXFIndex(rKRecord.getXFIndex());
        numberRecord.setValue(rKRecord.getRKNumber());
        return numberRecord;
    }

    public static NumberRecord[] convertRKRecords(MulRKRecord mulRKRecord) {
        int numColumns = mulRKRecord.getNumColumns();
        if (numColumns >= 0) {
            NumberRecord[] numberRecordArr = new NumberRecord[numColumns];
            for (int i = 0; i < numColumns; i++) {
                NumberRecord numberRecord = new NumberRecord();
                numberRecord.setColumn((short) (mulRKRecord.getFirstColumn() + i));
                numberRecord.setRow(mulRKRecord.getRow());
                numberRecord.setXFIndex(mulRKRecord.getXFAt(i));
                numberRecord.setValue(mulRKRecord.getRKNumberAt(i));
                numberRecordArr[i] = numberRecord;
            }
            return numberRecordArr;
        }
        throw new RecordFormatException("Cannot create RKRecords with negative number of columns: " + numColumns);
    }

    public static BlankRecord[] convertBlankRecords(MulBlankRecord mulBlankRecord) {
        BlankRecord[] blankRecordArr = new BlankRecord[mulBlankRecord.getNumColumns()];
        for (int i = 0; i < mulBlankRecord.getNumColumns(); i++) {
            BlankRecord blankRecord = new BlankRecord();
            blankRecord.setColumn((short) (mulBlankRecord.getFirstColumn() + i));
            blankRecord.setRow(mulBlankRecord.getRow());
            blankRecord.setXFIndex(mulBlankRecord.getXFAt(i));
            blankRecordArr[i] = blankRecord;
        }
        return blankRecordArr;
    }

    public static short[] getAllKnownRecordSIDs() {
        int[] array = Arrays.stream(HSSFRecordTypes.values()).mapToInt(new RecordFactory$$ExternalSyntheticLambda0()).toArray();
        short[] sArr = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            sArr[i] = (short) array[i];
        }
        return sArr;
    }

    public static List<Record> createRecords(InputStream inputStream) throws RecordFormatException {
        ArrayList arrayList = new ArrayList(512);
        RecordFactoryInputStream recordFactoryInputStream = new RecordFactoryInputStream(inputStream, true);
        while (true) {
            Record nextRecord = recordFactoryInputStream.nextRecord();
            if (nextRecord == null) {
                return arrayList;
            }
            arrayList.add(nextRecord);
            IOUtils.safelyAllocateCheck((long) arrayList.size(), MAX_NUMBER_OF_RECORDS);
        }
    }
}
