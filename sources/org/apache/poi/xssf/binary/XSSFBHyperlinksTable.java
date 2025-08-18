package org.apache.poi.xssf.binary;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFRelation;

@Internal
public class XSSFBHyperlinksTable {
    /* access modifiers changed from: private */
    public static final SparseBitSet RECORDS;
    /* access modifiers changed from: private */
    public final List<XSSFHyperlinkRecord> hyperlinkRecords = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, String> relIdToHyperlink = new HashMap();

    static {
        SparseBitSet sparseBitSet = new SparseBitSet();
        RECORDS = sparseBitSet;
        sparseBitSet.set(XSSFBRecordType.BrtHLink.getId());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XSSFBHyperlinksTable(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
        /*
            r1 = this;
            r1.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.hyperlinkRecords = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1.relIdToHyperlink = r0
            r1.loadUrlsFromSheetRels(r2)
            java.io.InputStream r2 = r2.getInputStream()
            org.apache.poi.xssf.binary.XSSFBHyperlinksTable$HyperlinkSheetScraper r0 = new org.apache.poi.xssf.binary.XSSFBHyperlinksTable$HyperlinkSheetScraper     // Catch:{ all -> 0x0026 }
            r0.<init>(r2)     // Catch:{ all -> 0x0026 }
            r0.parse()     // Catch:{ all -> 0x0026 }
            if (r2 == 0) goto L_0x0025
            r2.close()
        L_0x0025:
            return
        L_0x0026:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r0 = move-exception
            if (r2 == 0) goto L_0x0033
            r2.close()     // Catch:{ all -> 0x002f }
            goto L_0x0033
        L_0x002f:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x0033:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.binary.XSSFBHyperlinksTable.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public Map<CellAddress, List<XSSFHyperlinkRecord>> getHyperLinks() {
        TreeMap treeMap = new TreeMap(new TopLeftCellAddressComparator());
        for (XSSFHyperlinkRecord next : this.hyperlinkRecords) {
            CellAddress cellAddress = new CellAddress(next.getCellRangeAddress().getFirstRow(), next.getCellRangeAddress().getFirstColumn());
            List list = (List) treeMap.get(cellAddress);
            if (list == null) {
                list = new ArrayList();
            }
            list.add(next);
            treeMap.put(cellAddress, list);
        }
        return treeMap;
    }

    public List<XSSFHyperlinkRecord> findHyperlinkRecord(CellAddress cellAddress) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(cellAddress.getRow(), cellAddress.getRow(), cellAddress.getColumn(), cellAddress.getColumn());
        ArrayList arrayList = null;
        for (XSSFHyperlinkRecord next : this.hyperlinkRecords) {
            if (CellRangeUtil.intersect(cellRangeAddress, next.getCellRangeAddress()) != 1) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private void loadUrlsFromSheetRels(PackagePart packagePart) {
        try {
            Iterator<PackageRelationship> it = packagePart.getRelationshipsByType(XSSFRelation.SHEET_HYPERLINKS.getRelation()).iterator();
            while (it.hasNext()) {
                PackageRelationship next = it.next();
                this.relIdToHyperlink.put(next.getId(), next.getTargetURI().toString());
            }
        } catch (InvalidFormatException unused) {
        }
    }

    private class HyperlinkSheetScraper extends XSSFBParser {
        private XSSFBCellRange hyperlinkCellRange = new XSSFBCellRange();
        private final StringBuilder xlWideStringBuffer = new StringBuilder();

        HyperlinkSheetScraper(InputStream inputStream) {
            super(inputStream, XSSFBHyperlinksTable.RECORDS);
        }

        public void handleRecord(int i, byte[] bArr) throws XSSFBParseException {
            if (i == XSSFBRecordType.BrtHLink.getId()) {
                this.hyperlinkCellRange = XSSFBCellRange.parse(bArr, 0, this.hyperlinkCellRange);
                this.xlWideStringBuffer.setLength(0);
                int readXLNullableWideString = 16 + XSSFBUtils.readXLNullableWideString(bArr, 16, this.xlWideStringBuffer);
                String sb = this.xlWideStringBuffer.toString();
                this.xlWideStringBuffer.setLength(0);
                int readXLWideString = readXLNullableWideString + XSSFBUtils.readXLWideString(bArr, readXLNullableWideString, this.xlWideStringBuffer);
                String sb2 = this.xlWideStringBuffer.toString();
                this.xlWideStringBuffer.setLength(0);
                int readXLWideString2 = readXLWideString + XSSFBUtils.readXLWideString(bArr, readXLWideString, this.xlWideStringBuffer);
                String sb3 = this.xlWideStringBuffer.toString();
                this.xlWideStringBuffer.setLength(0);
                XSSFBUtils.readXLWideString(bArr, readXLWideString2, this.xlWideStringBuffer);
                XSSFBHyperlinksTable.this.hyperlinkRecords.add(new XSSFHyperlinkRecord(new CellRangeAddress(this.hyperlinkCellRange.firstRow, this.hyperlinkCellRange.lastRow, this.hyperlinkCellRange.firstCol, this.hyperlinkCellRange.lastCol), sb, sb2.length() == 0 ? (String) XSSFBHyperlinksTable.this.relIdToHyperlink.get(sb) : sb2, sb3, this.xlWideStringBuffer.toString()));
            }
        }
    }

    private static class TopLeftCellAddressComparator implements Comparator<CellAddress>, Serializable {
        private static final long serialVersionUID = 1;

        private TopLeftCellAddressComparator() {
        }

        public int compare(CellAddress cellAddress, CellAddress cellAddress2) {
            if (cellAddress.getRow() < cellAddress2.getRow()) {
                return -1;
            }
            if (cellAddress.getRow() > cellAddress2.getRow()) {
                return 1;
            }
            if (cellAddress.getColumn() < cellAddress2.getColumn()) {
                return -1;
            }
            if (cellAddress.getColumn() > cellAddress2.getColumn()) {
                return 1;
            }
            return 0;
        }
    }
}
