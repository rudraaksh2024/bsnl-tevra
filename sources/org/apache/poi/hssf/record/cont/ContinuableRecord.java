package org.apache.poi.hssf.record.cont;

import org.apache.poi.hssf.record.Record;

public abstract class ContinuableRecord extends Record {
    /* access modifiers changed from: protected */
    public abstract void serialize(ContinuableRecordOutput continuableRecordOutput);

    protected ContinuableRecord() {
    }

    protected ContinuableRecord(ContinuableRecord continuableRecord) {
        super(continuableRecord);
    }

    public final int getRecordSize() {
        ContinuableRecordOutput createForCountingOnly = ContinuableRecordOutput.createForCountingOnly();
        serialize(createForCountingOnly);
        createForCountingOnly.terminate();
        return createForCountingOnly.getTotalSize();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int serialize(int r2, byte[] r3) {
        /*
            r1 = this;
            org.apache.poi.util.LittleEndianByteArrayOutputStream r0 = new org.apache.poi.util.LittleEndianByteArrayOutputStream     // Catch:{ IOException -> 0x0028 }
            r0.<init>(r3, r2)     // Catch:{ IOException -> 0x0028 }
            org.apache.poi.hssf.record.cont.ContinuableRecordOutput r2 = new org.apache.poi.hssf.record.cont.ContinuableRecordOutput     // Catch:{ all -> 0x001c }
            short r3 = r1.getSid()     // Catch:{ all -> 0x001c }
            r2.<init>(r0, r3)     // Catch:{ all -> 0x001c }
            r1.serialize(r2)     // Catch:{ all -> 0x001c }
            r2.terminate()     // Catch:{ all -> 0x001c }
            int r1 = r2.getTotalSize()     // Catch:{ all -> 0x001c }
            r0.close()     // Catch:{ IOException -> 0x0028 }
            return r1
        L_0x001c:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001e }
        L_0x001e:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r3 = move-exception
            r1.addSuppressed(r3)     // Catch:{ IOException -> 0x0028 }
        L_0x0027:
            throw r2     // Catch:{ IOException -> 0x0028 }
        L_0x0028:
            r1 = move-exception
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.cont.ContinuableRecord.serialize(int, byte[]):int");
    }
}
