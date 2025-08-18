package org.apache.poi.hssf.usermodel;

import androidx.core.net.MailTo;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.record.HyperlinkRecord;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.util.Internal;

public class HSSFHyperlink implements Hyperlink, Duplicatable {
    protected final HyperlinkType link_type;
    protected final HyperlinkRecord record;

    @Internal(since = "3.15 beta 3")
    protected HSSFHyperlink(HyperlinkType hyperlinkType) {
        this.link_type = hyperlinkType;
        HyperlinkRecord hyperlinkRecord = new HyperlinkRecord();
        this.record = hyperlinkRecord;
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$HyperlinkType[hyperlinkType.ordinal()];
        if (i == 1 || i == 2) {
            hyperlinkRecord.newUrlLink();
        } else if (i == 3) {
            hyperlinkRecord.newFileLink();
        } else if (i == 4) {
            hyperlinkRecord.newDocumentLink();
        } else {
            throw new IllegalArgumentException("Invalid type: " + hyperlinkType);
        }
    }

    /* renamed from: org.apache.poi.hssf.usermodel.HSSFHyperlink$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.common.usermodel.HyperlinkType[] r0 = org.apache.poi.common.usermodel.HyperlinkType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType = r0
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.URL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.EMAIL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.FILE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.DOCUMENT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFHyperlink.AnonymousClass1.<clinit>():void");
        }
    }

    protected HSSFHyperlink(HyperlinkRecord hyperlinkRecord) {
        this.record = hyperlinkRecord;
        this.link_type = getType(hyperlinkRecord);
    }

    private static HyperlinkType getType(HyperlinkRecord hyperlinkRecord) {
        if (hyperlinkRecord.isFileLink()) {
            return HyperlinkType.FILE;
        }
        if (hyperlinkRecord.isDocumentLink()) {
            return HyperlinkType.DOCUMENT;
        }
        if (hyperlinkRecord.getAddress() == null || !hyperlinkRecord.getAddress().startsWith(MailTo.MAILTO_SCHEME)) {
            return HyperlinkType.URL;
        }
        return HyperlinkType.EMAIL;
    }

    protected HSSFHyperlink(Hyperlink hyperlink) {
        if (hyperlink instanceof HSSFHyperlink) {
            HyperlinkRecord copy = ((HSSFHyperlink) hyperlink).record.copy();
            this.record = copy;
            this.link_type = getType(copy);
            return;
        }
        this.link_type = hyperlink.getType();
        this.record = new HyperlinkRecord();
        setFirstRow(hyperlink.getFirstRow());
        setFirstColumn(hyperlink.getFirstColumn());
        setLastRow(hyperlink.getLastRow());
        setLastColumn(hyperlink.getLastColumn());
    }

    public int getFirstRow() {
        return this.record.getFirstRow();
    }

    public void setFirstRow(int i) {
        this.record.setFirstRow(i);
    }

    public int getLastRow() {
        return this.record.getLastRow();
    }

    public void setLastRow(int i) {
        this.record.setLastRow(i);
    }

    public int getFirstColumn() {
        return this.record.getFirstColumn();
    }

    public void setFirstColumn(int i) {
        this.record.setFirstColumn((short) i);
    }

    public int getLastColumn() {
        return this.record.getLastColumn();
    }

    public void setLastColumn(int i) {
        this.record.setLastColumn((short) i);
    }

    public String getAddress() {
        return this.record.getAddress();
    }

    public String getTextMark() {
        return this.record.getTextMark();
    }

    public void setTextMark(String str) {
        this.record.setTextMark(str);
    }

    public String getShortFilename() {
        return this.record.getShortFilename();
    }

    public void setShortFilename(String str) {
        this.record.setShortFilename(str);
    }

    public void setAddress(String str) {
        this.record.setAddress(str);
    }

    public String getLabel() {
        return this.record.getLabel();
    }

    public void setLabel(String str) {
        this.record.setLabel(str);
    }

    public HyperlinkType getType() {
        return this.link_type;
    }

    public Duplicatable copy() {
        return new HSSFHyperlink((Hyperlink) this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HSSFHyperlink)) {
            return false;
        }
        if (this.record == ((HSSFHyperlink) obj).record) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.record.hashCode();
    }
}
