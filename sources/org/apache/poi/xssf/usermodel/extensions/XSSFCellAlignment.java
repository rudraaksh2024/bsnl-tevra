package org.apache.poi.xssf.usermodel.extensions;

import java.math.BigInteger;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.ReadingOrder;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STHorizontalAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STVerticalAlignment;

public class XSSFCellAlignment {
    private final CTCellAlignment cellAlignement;

    public XSSFCellAlignment(CTCellAlignment cTCellAlignment) {
        this.cellAlignement = cTCellAlignment;
    }

    public VerticalAlignment getVertical() {
        STVerticalAlignment.Enum vertical = this.cellAlignement.getVertical();
        if (vertical == null) {
            vertical = STVerticalAlignment.BOTTOM;
        }
        return VerticalAlignment.values()[vertical.intValue() - 1];
    }

    public void setVertical(VerticalAlignment verticalAlignment) {
        this.cellAlignement.setVertical(STVerticalAlignment.Enum.forInt(verticalAlignment.ordinal() + 1));
    }

    public HorizontalAlignment getHorizontal() {
        STHorizontalAlignment.Enum horizontal = this.cellAlignement.getHorizontal();
        if (horizontal == null) {
            horizontal = STHorizontalAlignment.GENERAL;
        }
        return HorizontalAlignment.values()[horizontal.intValue() - 1];
    }

    public void setHorizontal(HorizontalAlignment horizontalAlignment) {
        this.cellAlignement.setHorizontal(STHorizontalAlignment.Enum.forInt(horizontalAlignment.ordinal() + 1));
    }

    public void setReadingOrder(ReadingOrder readingOrder) {
        this.cellAlignement.setReadingOrder((long) readingOrder.getCode());
    }

    public ReadingOrder getReadingOrder() {
        CTCellAlignment cTCellAlignment = this.cellAlignement;
        if (cTCellAlignment == null || !cTCellAlignment.isSetReadingOrder()) {
            return ReadingOrder.CONTEXT;
        }
        return ReadingOrder.forLong(this.cellAlignement.getReadingOrder());
    }

    public long getIndent() {
        return this.cellAlignement.getIndent();
    }

    public void setIndent(long j) {
        this.cellAlignement.setIndent(j);
    }

    public long getTextRotation() {
        if (this.cellAlignement.isSetTextRotation()) {
            return this.cellAlignement.getTextRotation().longValue();
        }
        return 0;
    }

    public void setTextRotation(long j) {
        if (j < 0 && j >= -90) {
            j = (j * -1) + 90;
        }
        this.cellAlignement.setTextRotation(BigInteger.valueOf(j));
    }

    public boolean getWrapText() {
        return this.cellAlignement.getWrapText();
    }

    public void setWrapText(boolean z) {
        this.cellAlignement.setWrapText(z);
    }

    public boolean getShrinkToFit() {
        return this.cellAlignement.getShrinkToFit();
    }

    public void setShrinkToFit(boolean z) {
        this.cellAlignement.setShrinkToFit(z);
    }

    @Internal
    public CTCellAlignment getCTCellAlignment() {
        return this.cellAlignement;
    }
}
