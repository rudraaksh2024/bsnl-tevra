package org.apache.poi.hssf.record;

import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.util.RecordFormatException;

public final class RecordFactoryInputStream {
    private int _bofDepth;
    private DrawingRecord _lastDrawingRecord = new DrawingRecord();
    private Record _lastRecord;
    private boolean _lastRecordWasEOFLevelZero;
    private final RecordInputStream _recStream;
    private final boolean _shouldIncludeContinueRecords;
    private Record[] _unreadRecordBuffer;
    private int _unreadRecordIndex = -1;

    private static final class StreamEncryptionInfo {
        private final FilePassRecord _filePassRec;
        private final boolean _hasBOFRecord;
        private final int _initialRecordsSize;
        private final Record _lastRecord;

        public StreamEncryptionInfo(RecordInputStream recordInputStream, List<Record> list) {
            recordInputStream.nextRecord();
            int remaining = recordInputStream.remaining() + 4;
            Record createSingleRecord = RecordFactory.createSingleRecord(recordInputStream);
            list.add(createSingleRecord);
            FilePassRecord filePassRecord = null;
            if (createSingleRecord instanceof BOFRecord) {
                this._hasBOFRecord = true;
                if (recordInputStream.hasNextRecord()) {
                    recordInputStream.nextRecord();
                    createSingleRecord = RecordFactory.createSingleRecord(recordInputStream);
                    remaining += createSingleRecord.getRecordSize();
                    list.add(createSingleRecord);
                    if ((createSingleRecord instanceof WriteProtectRecord) && recordInputStream.hasNextRecord()) {
                        recordInputStream.nextRecord();
                        Record createSingleRecord2 = RecordFactory.createSingleRecord(recordInputStream);
                        remaining += createSingleRecord2.getRecordSize();
                        list.add(createSingleRecord2);
                        createSingleRecord = createSingleRecord2;
                    }
                    filePassRecord = createSingleRecord instanceof FilePassRecord ? (FilePassRecord) createSingleRecord : filePassRecord;
                    if (createSingleRecord instanceof EOFRecord) {
                        throw new IllegalStateException("Nothing between BOF and EOF");
                    }
                }
            } else {
                this._hasBOFRecord = false;
            }
            this._initialRecordsSize = remaining;
            this._filePassRec = filePassRecord;
            this._lastRecord = createSingleRecord;
        }

        public RecordInputStream createDecryptingStream(InputStream inputStream) {
            String currentUserPassword = Biff8EncryptionKey.getCurrentUserPassword();
            if (currentUserPassword == null) {
                currentUserPassword = Decryptor.DEFAULT_PASSWORD;
            }
            EncryptionInfo encryptionInfo = this._filePassRec.getEncryptionInfo();
            try {
                if (encryptionInfo.getDecryptor().verifyPassword(currentUserPassword)) {
                    return new RecordInputStream(inputStream, encryptionInfo, this._initialRecordsSize);
                }
                throw new EncryptedDocumentException((Decryptor.DEFAULT_PASSWORD.equals(currentUserPassword) ? "Default" : "Supplied") + " password is invalid for salt/verifier/verifierHash");
            } catch (GeneralSecurityException e) {
                throw new EncryptedDocumentException((Throwable) e);
            }
        }

        public boolean hasEncryption() {
            return this._filePassRec != null;
        }

        public Record getLastRecord() {
            return this._lastRecord;
        }

        public boolean hasBOFRecord() {
            return this._hasBOFRecord;
        }
    }

    public RecordFactoryInputStream(InputStream inputStream, boolean z) {
        RecordInputStream recordInputStream = new RecordInputStream(inputStream);
        ArrayList arrayList = new ArrayList();
        StreamEncryptionInfo streamEncryptionInfo = new StreamEncryptionInfo(recordInputStream, arrayList);
        recordInputStream = streamEncryptionInfo.hasEncryption() ? streamEncryptionInfo.createDecryptingStream(inputStream) : recordInputStream;
        if (!arrayList.isEmpty()) {
            Record[] recordArr = new Record[arrayList.size()];
            this._unreadRecordBuffer = recordArr;
            arrayList.toArray(recordArr);
            this._unreadRecordIndex = 0;
        }
        this._recStream = recordInputStream;
        this._shouldIncludeContinueRecords = z;
        this._lastRecord = streamEncryptionInfo.getLastRecord();
        this._bofDepth = streamEncryptionInfo.hasBOFRecord() ? 1 : 0;
        this._lastRecordWasEOFLevelZero = false;
    }

    public Record nextRecord() {
        Record nextUnreadRecord = getNextUnreadRecord();
        if (nextUnreadRecord != null) {
            return nextUnreadRecord;
        }
        while (this._recStream.hasNextRecord()) {
            if (this._lastRecordWasEOFLevelZero && this._recStream.getNextSid() != 2057) {
                return null;
            }
            this._recStream.nextRecord();
            Record readNextRecord = readNextRecord();
            if (readNextRecord != null) {
                return readNextRecord;
            }
        }
        return null;
    }

    private Record getNextUnreadRecord() {
        Record[] recordArr = this._unreadRecordBuffer;
        if (recordArr != null) {
            int i = this._unreadRecordIndex;
            if (i < recordArr.length) {
                Record record = recordArr[i];
                this._unreadRecordIndex = i + 1;
                return record;
            }
            this._unreadRecordIndex = -1;
            this._unreadRecordBuffer = null;
        }
        return null;
    }

    private Record readNextRecord() {
        Record createSingleRecord = RecordFactory.createSingleRecord(this._recStream);
        this._lastRecordWasEOFLevelZero = false;
        if (createSingleRecord instanceof BOFRecord) {
            this._bofDepth++;
            return createSingleRecord;
        } else if (createSingleRecord instanceof EOFRecord) {
            int i = this._bofDepth - 1;
            this._bofDepth = i;
            if (i < 1) {
                this._lastRecordWasEOFLevelZero = true;
            }
            return createSingleRecord;
        } else if (createSingleRecord instanceof DBCellRecord) {
            return null;
        } else {
            if (createSingleRecord instanceof RKRecord) {
                return RecordFactory.convertToNumberRecord((RKRecord) createSingleRecord);
            }
            if (createSingleRecord instanceof MulRKRecord) {
                NumberRecord[] convertRKRecords = RecordFactory.convertRKRecords((MulRKRecord) createSingleRecord);
                this._unreadRecordBuffer = convertRKRecords;
                this._unreadRecordIndex = 1;
                return convertRKRecords[0];
            }
            if (createSingleRecord.getSid() == 235) {
                Record record = this._lastRecord;
                if (record instanceof DrawingGroupRecord) {
                    ((DrawingGroupRecord) record).join((AbstractEscherHolderRecord) createSingleRecord);
                    return null;
                }
            }
            if (createSingleRecord.getSid() == 60) {
                ContinueRecord continueRecord = (ContinueRecord) createSingleRecord;
                Record record2 = this._lastRecord;
                if ((record2 instanceof ObjRecord) || (record2 instanceof TextObjectRecord)) {
                    this._lastDrawingRecord.processContinueRecord(continueRecord.getData());
                    if (this._shouldIncludeContinueRecords) {
                        return createSingleRecord;
                    }
                    return null;
                } else if (record2 instanceof DrawingGroupRecord) {
                    ((DrawingGroupRecord) record2).processContinueRecord(continueRecord.getData());
                    return null;
                } else if (record2 instanceof DrawingRecord) {
                    return continueRecord;
                } else {
                    if ((record2 instanceof UnknownRecord) || (record2 instanceof EOFRecord)) {
                        return createSingleRecord;
                    }
                    throw new RecordFormatException("Unhandled Continue Record followining " + this._lastRecord.getClass());
                }
            } else {
                this._lastRecord = createSingleRecord;
                if (createSingleRecord instanceof DrawingRecord) {
                    this._lastDrawingRecord = (DrawingRecord) createSingleRecord;
                }
                return createSingleRecord;
            }
        }
    }
}
