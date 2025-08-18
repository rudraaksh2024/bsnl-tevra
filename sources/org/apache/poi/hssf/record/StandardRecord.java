package org.apache.poi.hssf.record;

import org.apache.poi.util.LittleEndianOutput;

public abstract class StandardRecord extends Record {
    public abstract StandardRecord copy();

    /* access modifiers changed from: protected */
    public abstract int getDataSize();

    /* access modifiers changed from: protected */
    public abstract void serialize(LittleEndianOutput littleEndianOutput);

    protected StandardRecord() {
    }

    protected StandardRecord(StandardRecord standardRecord) {
    }

    public final int getRecordSize() {
        return getDataSize() + 4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005b, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0064, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int serialize(int r5, byte[] r6) {
        /*
            r4 = this;
            java.lang.String r0 = "Error in serialization of ("
            int r1 = r4.getDataSize()
            int r2 = r1 + 4
            org.apache.poi.util.LittleEndianByteArrayOutputStream r3 = new org.apache.poi.util.LittleEndianByteArrayOutputStream     // Catch:{ IOException -> 0x0065 }
            r3.<init>(r6, r5, r2)     // Catch:{ IOException -> 0x0065 }
            short r6 = r4.getSid()     // Catch:{ all -> 0x0059 }
            r3.writeShort(r6)     // Catch:{ all -> 0x0059 }
            r3.writeShort(r1)     // Catch:{ all -> 0x0059 }
            r4.serialize(r3)     // Catch:{ all -> 0x0059 }
            int r6 = r3.getWriteIndex()     // Catch:{ all -> 0x0059 }
            int r6 = r6 - r5
            if (r6 != r2) goto L_0x0025
            r3.close()     // Catch:{ IOException -> 0x0065 }
            return r2
        L_0x0025:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r1.<init>(r0)     // Catch:{ all -> 0x0059 }
            java.lang.Class r4 = r4.getClass()     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r4 = r1.append(r4)     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = "): Incorrect number of bytes written - expected "
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x0059 }
            java.lang.StringBuilder r4 = r4.append(r2)     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = " but got "
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x0059 }
            int r0 = r3.getWriteIndex()     // Catch:{ all -> 0x0059 }
            int r0 = r0 - r5
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0059 }
            r6.<init>(r4)     // Catch:{ all -> 0x0059 }
            throw r6     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x005b }
        L_0x005b:
            r5 = move-exception
            r3.close()     // Catch:{ all -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r6 = move-exception
            r4.addSuppressed(r6)     // Catch:{ IOException -> 0x0065 }
        L_0x0064:
            throw r5     // Catch:{ IOException -> 0x0065 }
        L_0x0065:
            r4 = move-exception
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.StandardRecord.serialize(int, byte[]):int");
    }
}
