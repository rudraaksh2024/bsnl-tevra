package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.util.LittleEndianInputStream;

public class PropertySetFactory {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r1 != null) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.hpsf.PropertySet create(org.apache.poi.poifs.filesystem.DirectoryEntry r1, java.lang.String r2) throws java.io.FileNotFoundException, org.apache.poi.hpsf.NoPropertySetStreamException, java.io.IOException, java.io.UnsupportedEncodingException {
        /*
            org.apache.poi.poifs.filesystem.DirectoryNode r1 = (org.apache.poi.poifs.filesystem.DirectoryNode) r1
            org.apache.poi.poifs.filesystem.DocumentInputStream r1 = r1.createDocumentInputStream((java.lang.String) r2)
            org.apache.poi.hpsf.PropertySet r2 = create(r1)     // Catch:{ all -> 0x0010 }
            if (r1 == 0) goto L_0x000f
            r1.close()
        L_0x000f:
            return r2
        L_0x0010:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r0 = move-exception
            if (r1 == 0) goto L_0x001d
            r1.close()     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x001d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hpsf.PropertySetFactory.create(org.apache.poi.poifs.filesystem.DirectoryEntry, java.lang.String):org.apache.poi.hpsf.PropertySet");
    }

    public static PropertySet create(InputStream inputStream) throws NoPropertySetStreamException, IOException {
        inputStream.mark(45);
        LittleEndianInputStream littleEndianInputStream = new LittleEndianInputStream(inputStream);
        int readUShort = littleEndianInputStream.readUShort();
        int readUShort2 = littleEndianInputStream.readUShort();
        littleEndianInputStream.readUInt();
        byte[] bArr = new byte[16];
        littleEndianInputStream.readFully(bArr);
        int readUInt = (int) littleEndianInputStream.readUInt();
        if (readUShort == 65534 && readUShort2 == 0 && readUInt >= 0) {
            if (readUInt > 0) {
                littleEndianInputStream.readFully(bArr);
            }
            inputStream.reset();
            ClassID classID = new ClassID(bArr, 0);
            if (readUInt > 0) {
                if (PropertySet.matchesSummary(classID, SummaryInformation.FORMAT_ID)) {
                    return new SummaryInformation(inputStream);
                }
            }
            if (readUInt <= 0 || !PropertySet.matchesSummary(classID, DocumentSummaryInformation.FORMAT_ID)) {
                return new PropertySet(inputStream);
            }
            return new DocumentSummaryInformation(inputStream);
        }
        throw new NoPropertySetStreamException("ByteOrder: " + readUShort + ", format: " + readUShort2 + ", sectionCount: " + readUInt);
    }

    public static SummaryInformation newSummaryInformation() {
        return new SummaryInformation();
    }

    public static DocumentSummaryInformation newDocumentSummaryInformation() {
        return new DocumentSummaryInformation();
    }
}
