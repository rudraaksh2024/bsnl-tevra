package org.apache.poi.hssf.eventusermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactoryInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class HSSFEventFactory {
    public void processWorkbookEvents(HSSFRequest hSSFRequest, POIFSFileSystem pOIFSFileSystem) throws IOException {
        processWorkbookEvents(hSSFRequest, pOIFSFileSystem.getRoot());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        if (r6 != null) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0041, code lost:
        r4.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processWorkbookEvents(org.apache.poi.hssf.eventusermodel.HSSFRequest r5, org.apache.poi.poifs.filesystem.DirectoryNode r6) throws java.io.IOException {
        /*
            r4 = this;
            java.util.Set r0 = r6.getEntryNames()
            java.util.List<java.lang.String> r1 = org.apache.poi.hssf.model.InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES
            java.util.Iterator r1 = r1.iterator()
        L_0x000a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x001d
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = r0.contains(r2)
            if (r3 == 0) goto L_0x000a
            goto L_0x001e
        L_0x001d:
            r2 = 0
        L_0x001e:
            if (r2 != 0) goto L_0x002a
            java.util.List<java.lang.String> r0 = org.apache.poi.hssf.model.InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2
        L_0x002a:
            org.apache.poi.poifs.filesystem.DocumentInputStream r6 = r6.createDocumentInputStream((java.lang.String) r2)
            r4.processEvents(r5, r6)     // Catch:{ all -> 0x0037 }
            if (r6 == 0) goto L_0x0036
            r6.close()
        L_0x0036:
            return
        L_0x0037:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r5 = move-exception
            if (r6 == 0) goto L_0x0044
            r6.close()     // Catch:{ all -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r6 = move-exception
            r4.addSuppressed(r6)
        L_0x0044:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.eventusermodel.HSSFEventFactory.processWorkbookEvents(org.apache.poi.hssf.eventusermodel.HSSFRequest, org.apache.poi.poifs.filesystem.DirectoryNode):void");
    }

    public short abortableProcessWorkbookEvents(HSSFRequest hSSFRequest, POIFSFileSystem pOIFSFileSystem) throws IOException, HSSFUserException {
        return abortableProcessWorkbookEvents(hSSFRequest, pOIFSFileSystem.getRoot());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r3 != null) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001d, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public short abortableProcessWorkbookEvents(org.apache.poi.hssf.eventusermodel.HSSFRequest r2, org.apache.poi.poifs.filesystem.DirectoryNode r3) throws java.io.IOException, org.apache.poi.hssf.eventusermodel.HSSFUserException {
        /*
            r1 = this;
            java.lang.String r0 = "Workbook"
            org.apache.poi.poifs.filesystem.DocumentInputStream r3 = r3.createDocumentInputStream((java.lang.String) r0)
            short r1 = r1.abortableProcessEvents(r2, r3)     // Catch:{ all -> 0x0010 }
            if (r3 == 0) goto L_0x000f
            r3.close()
        L_0x000f:
            return r1
        L_0x0010:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r2 = move-exception
            if (r3 == 0) goto L_0x001d
            r3.close()     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x001d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.eventusermodel.HSSFEventFactory.abortableProcessWorkbookEvents(org.apache.poi.hssf.eventusermodel.HSSFRequest, org.apache.poi.poifs.filesystem.DirectoryNode):short");
    }

    public void processEvents(HSSFRequest hSSFRequest, InputStream inputStream) {
        try {
            genericProcessEvents(hSSFRequest, inputStream);
        } catch (HSSFUserException unused) {
        }
    }

    public short abortableProcessEvents(HSSFRequest hSSFRequest, InputStream inputStream) throws HSSFUserException {
        return genericProcessEvents(hSSFRequest, inputStream);
    }

    private short genericProcessEvents(HSSFRequest hSSFRequest, InputStream inputStream) throws HSSFUserException {
        Record nextRecord;
        short s = 0;
        RecordFactoryInputStream recordFactoryInputStream = new RecordFactoryInputStream(inputStream, false);
        do {
            nextRecord = recordFactoryInputStream.nextRecord();
            if (nextRecord == null || (s = hSSFRequest.processRecord(nextRecord)) != 0) {
                return s;
            }
            nextRecord = recordFactoryInputStream.nextRecord();
            break;
        } while ((s = hSSFRequest.processRecord(nextRecord)) != 0);
        return s;
    }
}
