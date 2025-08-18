package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.BottomMarginRecord;
import org.apache.poi.hssf.record.ContinueRecord;
import org.apache.poi.hssf.record.FooterRecord;
import org.apache.poi.hssf.record.HCenterRecord;
import org.apache.poi.hssf.record.HeaderFooterRecord;
import org.apache.poi.hssf.record.HeaderRecord;
import org.apache.poi.hssf.record.HorizontalPageBreakRecord;
import org.apache.poi.hssf.record.LeftMarginRecord;
import org.apache.poi.hssf.record.Margin;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.apache.poi.hssf.record.PrintSetupRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.RightMarginRecord;
import org.apache.poi.hssf.record.TopMarginRecord;
import org.apache.poi.hssf.record.UserSViewBegin;
import org.apache.poi.hssf.record.VCenterRecord;
import org.apache.poi.hssf.record.VerticalPageBreakRecord;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.RecordFormatException;

public final class PageSettingsBlock extends RecordAggregate {
    private Record _bitmap;
    private BottomMarginRecord _bottomMargin;
    private PageBreakRecord _columnBreaksRecord;
    private FooterRecord _footer;
    private HCenterRecord _hCenter;
    private HeaderRecord _header;
    private HeaderFooterRecord _headerFooter;
    private LeftMarginRecord _leftMargin;
    private final List<PLSAggregate> _plsRecords;
    private PrintSetupRecord _printSetup;
    private Record _printSize;
    private RightMarginRecord _rightMargin;
    private PageBreakRecord _rowBreaksRecord;
    private final List<HeaderFooterRecord> _sviewHeaderFooters;
    private TopMarginRecord _topMargin;
    private VCenterRecord _vCenter;

    public static boolean isComponentRecord(int i) {
        if (i == 20 || i == 21 || i == 26 || i == 27 || i == 51 || i == 77 || i == 161 || i == 233 || i == 2204 || i == 131 || i == 132) {
            return true;
        }
        switch (i) {
            case 38:
            case 39:
            case 40:
            case 41:
                return true;
            default:
                return false;
        }
    }

    private static final class PLSAggregate extends RecordAggregate {
        private static final ContinueRecord[] EMPTY_CONTINUE_RECORD_ARRAY = new ContinueRecord[0];
        private final Record _pls;
        private ContinueRecord[] _plsContinues;

        public PLSAggregate(RecordStream recordStream) {
            this._pls = recordStream.getNext();
            if (recordStream.peekNextSid() == 60) {
                ArrayList arrayList = new ArrayList();
                while (recordStream.peekNextSid() == 60) {
                    arrayList.add((ContinueRecord) recordStream.getNext());
                }
                ContinueRecord[] continueRecordArr = new ContinueRecord[arrayList.size()];
                this._plsContinues = continueRecordArr;
                arrayList.toArray(continueRecordArr);
                return;
            }
            this._plsContinues = EMPTY_CONTINUE_RECORD_ARRAY;
        }

        public void visitContainedRecords(RecordAggregate.RecordVisitor recordVisitor) {
            recordVisitor.visitRecord(this._pls);
            for (ContinueRecord visitRecord : this._plsContinues) {
                recordVisitor.visitRecord(visitRecord);
            }
        }
    }

    public PageSettingsBlock(RecordStream recordStream) {
        this._sviewHeaderFooters = new ArrayList();
        this._plsRecords = new ArrayList();
        do {
        } while (readARecord(recordStream));
    }

    public PageSettingsBlock() {
        this._sviewHeaderFooters = new ArrayList();
        this._plsRecords = new ArrayList();
        this._rowBreaksRecord = new HorizontalPageBreakRecord();
        this._columnBreaksRecord = new VerticalPageBreakRecord();
        this._header = new HeaderRecord("");
        this._footer = new FooterRecord("");
        this._hCenter = createHCenter();
        this._vCenter = createVCenter();
        this._printSetup = createPrintSetup();
    }

    private boolean readARecord(RecordStream recordStream) {
        int peekNextSid = recordStream.peekNextSid();
        if (peekNextSid == 20) {
            checkNotPresent(this._header);
            this._header = (HeaderRecord) recordStream.getNext();
            return true;
        } else if (peekNextSid == 21) {
            checkNotPresent(this._footer);
            this._footer = (FooterRecord) recordStream.getNext();
            return true;
        } else if (peekNextSid == 26) {
            checkNotPresent(this._columnBreaksRecord);
            this._columnBreaksRecord = (PageBreakRecord) recordStream.getNext();
            return true;
        } else if (peekNextSid == 27) {
            checkNotPresent(this._rowBreaksRecord);
            this._rowBreaksRecord = (PageBreakRecord) recordStream.getNext();
            return true;
        } else if (peekNextSid == 51) {
            checkNotPresent(this._printSize);
            this._printSize = recordStream.getNext();
            return true;
        } else if (peekNextSid == 77) {
            this._plsRecords.add(new PLSAggregate(recordStream));
            return true;
        } else if (peekNextSid == 161) {
            checkNotPresent(this._printSetup);
            this._printSetup = (PrintSetupRecord) recordStream.getNext();
            return true;
        } else if (peekNextSid == 233) {
            checkNotPresent(this._bitmap);
            this._bitmap = recordStream.getNext();
            return true;
        } else if (peekNextSid == 2204) {
            HeaderFooterRecord headerFooterRecord = (HeaderFooterRecord) recordStream.getNext();
            if (headerFooterRecord.isCurrentSheet()) {
                this._headerFooter = headerFooterRecord;
                return true;
            }
            this._sviewHeaderFooters.add(headerFooterRecord);
            return true;
        } else if (peekNextSid == 131) {
            checkNotPresent(this._hCenter);
            this._hCenter = (HCenterRecord) recordStream.getNext();
            return true;
        } else if (peekNextSid != 132) {
            switch (peekNextSid) {
                case 38:
                    checkNotPresent(this._leftMargin);
                    this._leftMargin = (LeftMarginRecord) recordStream.getNext();
                    return true;
                case 39:
                    checkNotPresent(this._rightMargin);
                    this._rightMargin = (RightMarginRecord) recordStream.getNext();
                    return true;
                case 40:
                    checkNotPresent(this._topMargin);
                    this._topMargin = (TopMarginRecord) recordStream.getNext();
                    return true;
                case 41:
                    checkNotPresent(this._bottomMargin);
                    this._bottomMargin = (BottomMarginRecord) recordStream.getNext();
                    return true;
                default:
                    return false;
            }
        } else {
            checkNotPresent(this._vCenter);
            this._vCenter = (VCenterRecord) recordStream.getNext();
            return true;
        }
    }

    private void checkNotPresent(Record record) {
        if (record != null) {
            throw new RecordFormatException("Duplicate PageSettingsBlock record (sid=0x" + Integer.toHexString(record.getSid()) + ")");
        }
    }

    private PageBreakRecord getRowBreaksRecord() {
        if (this._rowBreaksRecord == null) {
            this._rowBreaksRecord = new HorizontalPageBreakRecord();
        }
        return this._rowBreaksRecord;
    }

    private PageBreakRecord getColumnBreaksRecord() {
        if (this._columnBreaksRecord == null) {
            this._columnBreaksRecord = new VerticalPageBreakRecord();
        }
        return this._columnBreaksRecord;
    }

    public void setColumnBreak(short s, short s2, short s3) {
        getColumnBreaksRecord().addBreak(s, s2, s3);
    }

    public void removeColumnBreak(int i) {
        getColumnBreaksRecord().removeBreak(i);
    }

    public void visitContainedRecords(RecordAggregate.RecordVisitor recordVisitor) {
        visitIfPresent(this._rowBreaksRecord, recordVisitor);
        visitIfPresent(this._columnBreaksRecord, recordVisitor);
        HeaderRecord headerRecord = this._header;
        if (headerRecord == null) {
            recordVisitor.visitRecord(new HeaderRecord(""));
        } else {
            recordVisitor.visitRecord(headerRecord);
        }
        FooterRecord footerRecord = this._footer;
        if (footerRecord == null) {
            recordVisitor.visitRecord(new FooterRecord(""));
        } else {
            recordVisitor.visitRecord(footerRecord);
        }
        visitIfPresent((Record) this._hCenter, recordVisitor);
        visitIfPresent((Record) this._vCenter, recordVisitor);
        visitIfPresent((Record) this._leftMargin, recordVisitor);
        visitIfPresent((Record) this._rightMargin, recordVisitor);
        visitIfPresent((Record) this._topMargin, recordVisitor);
        visitIfPresent((Record) this._bottomMargin, recordVisitor);
        for (PLSAggregate visitContainedRecords : this._plsRecords) {
            visitContainedRecords.visitContainedRecords(recordVisitor);
        }
        visitIfPresent((Record) this._printSetup, recordVisitor);
        visitIfPresent(this._printSize, recordVisitor);
        visitIfPresent((Record) this._headerFooter, recordVisitor);
        visitIfPresent(this._bitmap, recordVisitor);
    }

    private static void visitIfPresent(Record record, RecordAggregate.RecordVisitor recordVisitor) {
        if (record != null) {
            recordVisitor.visitRecord(record);
        }
    }

    private static void visitIfPresent(PageBreakRecord pageBreakRecord, RecordAggregate.RecordVisitor recordVisitor) {
        if (pageBreakRecord != null && !pageBreakRecord.isEmpty()) {
            recordVisitor.visitRecord(pageBreakRecord);
        }
    }

    private static HCenterRecord createHCenter() {
        HCenterRecord hCenterRecord = new HCenterRecord();
        hCenterRecord.setHCenter(false);
        return hCenterRecord;
    }

    private static VCenterRecord createVCenter() {
        VCenterRecord vCenterRecord = new VCenterRecord();
        vCenterRecord.setVCenter(false);
        return vCenterRecord;
    }

    private static PrintSetupRecord createPrintSetup() {
        PrintSetupRecord printSetupRecord = new PrintSetupRecord();
        printSetupRecord.setPaperSize(1);
        printSetupRecord.setScale(100);
        printSetupRecord.setPageStart(1);
        printSetupRecord.setFitWidth(1);
        printSetupRecord.setFitHeight(1);
        printSetupRecord.setOptions(2);
        printSetupRecord.setHResolution(300);
        printSetupRecord.setVResolution(300);
        printSetupRecord.setHeaderMargin(0.5d);
        printSetupRecord.setFooterMargin(0.5d);
        printSetupRecord.setCopies(1);
        return printSetupRecord;
    }

    public HeaderRecord getHeader() {
        return this._header;
    }

    public void setHeader(HeaderRecord headerRecord) {
        this._header = headerRecord;
    }

    public FooterRecord getFooter() {
        return this._footer;
    }

    public void setFooter(FooterRecord footerRecord) {
        this._footer = footerRecord;
    }

    public PrintSetupRecord getPrintSetup() {
        return this._printSetup;
    }

    public void setPrintSetup(PrintSetupRecord printSetupRecord) {
        this._printSetup = printSetupRecord;
    }

    private Margin getMarginRec(int i) {
        if (i == 0) {
            return this._leftMargin;
        }
        if (i == 1) {
            return this._rightMargin;
        }
        if (i == 2) {
            return this._topMargin;
        }
        if (i == 3) {
            return this._bottomMargin;
        }
        throw new IllegalArgumentException("Unknown margin constant:  " + i);
    }

    public double getMargin(short s) {
        Margin marginRec = getMarginRec(s);
        if (marginRec != null) {
            return marginRec.getMargin();
        }
        double d = 0.75d;
        if (!(s == 0 || s == 1)) {
            d = 1.0d;
            if (s != 2) {
                if (s == 3) {
                    return 1.0d;
                }
                throw new IllegalArgumentException("Unknown margin constant:  " + s);
            }
        }
        return d;
    }

    public void setMargin(short s, double d) {
        Margin marginRec = getMarginRec(s);
        BottomMarginRecord bottomMarginRecord = marginRec;
        if (marginRec == null) {
            if (s == 0) {
                LeftMarginRecord leftMarginRecord = new LeftMarginRecord();
                this._leftMargin = leftMarginRecord;
                bottomMarginRecord = leftMarginRecord;
            } else if (s == 1) {
                RightMarginRecord rightMarginRecord = new RightMarginRecord();
                this._rightMargin = rightMarginRecord;
                bottomMarginRecord = rightMarginRecord;
            } else if (s == 2) {
                TopMarginRecord topMarginRecord = new TopMarginRecord();
                this._topMargin = topMarginRecord;
                bottomMarginRecord = topMarginRecord;
            } else if (s == 3) {
                BottomMarginRecord bottomMarginRecord2 = new BottomMarginRecord();
                this._bottomMargin = bottomMarginRecord2;
                bottomMarginRecord = bottomMarginRecord2;
            } else {
                throw new IllegalArgumentException("Unknown margin constant:  " + s);
            }
        }
        bottomMarginRecord.setMargin(d);
    }

    private static void shiftBreaks(PageBreakRecord pageBreakRecord, int i, int i2, int i3) {
        Iterator<PageBreakRecord.Break> breaksIterator = pageBreakRecord.getBreaksIterator();
        ArrayList<PageBreakRecord.Break> arrayList = new ArrayList<>();
        while (breaksIterator.hasNext()) {
            PageBreakRecord.Break next = breaksIterator.next();
            int main = next.getMain();
            boolean z = true;
            boolean z2 = main >= i;
            if (main > i2) {
                z = false;
            }
            if (z2 && z) {
                arrayList.add(next);
            }
        }
        for (PageBreakRecord.Break breakR : arrayList) {
            pageBreakRecord.removeBreak(breakR.getMain());
            pageBreakRecord.addBreak((short) (breakR.getMain() + i3), breakR.getSubFrom(), breakR.getSubTo());
        }
    }

    public void setRowBreak(int i, short s, short s2) {
        getRowBreaksRecord().addBreak((short) i, s, s2);
    }

    public void removeRowBreak(int i) {
        if (getRowBreaksRecord().getBreaks().length >= 1) {
            getRowBreaksRecord().removeBreak((short) i);
            return;
        }
        throw new IllegalArgumentException("Sheet does not define any row breaks");
    }

    public boolean isRowBroken(int i) {
        return getRowBreaksRecord().getBreak(i) != null;
    }

    public boolean isColumnBroken(int i) {
        return getColumnBreaksRecord().getBreak(i) != null;
    }

    public void shiftRowBreaks(int i, int i2, int i3) {
        shiftBreaks(getRowBreaksRecord(), i, i2, i3);
    }

    public void shiftColumnBreaks(short s, short s2, short s3) {
        shiftBreaks(getColumnBreaksRecord(), s, s2, s3);
    }

    public int[] getRowBreaks() {
        return getRowBreaksRecord().getBreaks();
    }

    public int getNumRowBreaks() {
        return getRowBreaksRecord().getNumBreaks();
    }

    public int[] getColumnBreaks() {
        return getColumnBreaksRecord().getBreaks();
    }

    public int getNumColumnBreaks() {
        return getColumnBreaksRecord().getNumBreaks();
    }

    public VCenterRecord getVCenter() {
        return this._vCenter;
    }

    public HCenterRecord getHCenter() {
        return this._hCenter;
    }

    public void addLateHeaderFooter(HeaderFooterRecord headerFooterRecord) {
        if (this._headerFooter != null) {
            throw new IllegalStateException("This page settings block already has a header/footer record");
        } else if (headerFooterRecord.getSid() == 2204) {
            this._headerFooter = headerFooterRecord;
        } else {
            throw new RecordFormatException("Unexpected header-footer record sid: 0x" + Integer.toHexString(headerFooterRecord.getSid()));
        }
    }

    public void addLateRecords(RecordStream recordStream) {
        do {
        } while (readARecord(recordStream));
    }

    public void positionRecords(List<RecordBase> list) {
        ArrayList<HeaderFooterRecord> arrayList = new ArrayList<>(this._sviewHeaderFooters);
        HashMap hashMap = new HashMap();
        for (HeaderFooterRecord headerFooterRecord : arrayList) {
            hashMap.put(HexDump.toHex(headerFooterRecord.getGuid()), headerFooterRecord);
        }
        for (RecordBase next : list) {
            if (next instanceof CustomViewSettingsRecordAggregate) {
                CustomViewSettingsRecordAggregate customViewSettingsRecordAggregate = (CustomViewSettingsRecordAggregate) next;
                customViewSettingsRecordAggregate.visitContainedRecords(new PageSettingsBlock$$ExternalSyntheticLambda0(this, hashMap, customViewSettingsRecordAggregate));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$positionRecords$0$org-apache-poi-hssf-record-aggregates-PageSettingsBlock  reason: not valid java name */
    public /* synthetic */ void m2118lambda$positionRecords$0$orgapachepoihssfrecordaggregatesPageSettingsBlock(Map map, CustomViewSettingsRecordAggregate customViewSettingsRecordAggregate, Record record) {
        HeaderFooterRecord headerFooterRecord;
        if (record.getSid() == 426 && (headerFooterRecord = (HeaderFooterRecord) map.get(HexDump.toHex(((UserSViewBegin) record).getGuid()))) != null) {
            customViewSettingsRecordAggregate.append(headerFooterRecord);
            this._sviewHeaderFooters.remove(headerFooterRecord);
        }
    }
}
