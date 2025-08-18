package org.apache.poi.xssf.binary;

import java.util.Objects;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;

@Internal
public class XSSFHyperlinkRecord {
    private final CellRangeAddress cellRangeAddress;
    private String display;
    private String location;
    private final String relId;
    private String toolTip;

    XSSFHyperlinkRecord(CellRangeAddress cellRangeAddress2, String str, String str2, String str3, String str4) {
        this.cellRangeAddress = cellRangeAddress2;
        this.relId = str;
        this.location = str2;
        this.toolTip = str3;
        this.display = str4;
    }

    /* access modifiers changed from: package-private */
    public void setLocation(String str) {
        this.location = str;
    }

    /* access modifiers changed from: package-private */
    public void setToolTip(String str) {
        this.toolTip = str;
    }

    /* access modifiers changed from: package-private */
    public void setDisplay(String str) {
        this.display = str;
    }

    /* access modifiers changed from: package-private */
    public CellRangeAddress getCellRangeAddress() {
        return this.cellRangeAddress;
    }

    public String getRelId() {
        return this.relId;
    }

    public String getLocation() {
        return this.location;
    }

    public String getToolTip() {
        return this.toolTip;
    }

    public String getDisplay() {
        return this.display;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        XSSFHyperlinkRecord xSSFHyperlinkRecord = (XSSFHyperlinkRecord) obj;
        CellRangeAddress cellRangeAddress2 = this.cellRangeAddress;
        if (cellRangeAddress2 == null ? xSSFHyperlinkRecord.cellRangeAddress != null : !cellRangeAddress2.equals(xSSFHyperlinkRecord.cellRangeAddress)) {
            return false;
        }
        String str = this.relId;
        if (str == null ? xSSFHyperlinkRecord.relId != null : !str.equals(xSSFHyperlinkRecord.relId)) {
            return false;
        }
        String str2 = this.location;
        if (str2 == null ? xSSFHyperlinkRecord.location != null : !str2.equals(xSSFHyperlinkRecord.location)) {
            return false;
        }
        String str3 = this.toolTip;
        if (str3 == null ? xSSFHyperlinkRecord.toolTip != null : !str3.equals(xSSFHyperlinkRecord.toolTip)) {
            return false;
        }
        String str4 = this.display;
        if (str4 != null) {
            return str4.equals(xSSFHyperlinkRecord.display);
        }
        if (xSSFHyperlinkRecord.display == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.cellRangeAddress, this.relId, this.location, this.toolTip, this.display});
    }

    public String toString() {
        return "XSSFHyperlinkRecord{cellRangeAddress=" + this.cellRangeAddress + ", relId='" + this.relId + "', location='" + this.location + "', toolTip='" + this.toolTip + "', display='" + this.display + "'}";
    }
}
