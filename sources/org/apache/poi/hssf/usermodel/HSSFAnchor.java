package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherChildAnchorRecord;
import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ss.usermodel.ChildAnchor;

public abstract class HSSFAnchor implements ChildAnchor {
    protected boolean _isHorizontallyFlipped;
    protected boolean _isVerticallyFlipped;

    /* access modifiers changed from: protected */
    public abstract void createEscherAnchor();

    /* access modifiers changed from: protected */
    public abstract EscherRecord getEscherAnchor();

    public abstract boolean isHorizontallyFlipped();

    public abstract boolean isVerticallyFlipped();

    public HSSFAnchor() {
        createEscherAnchor();
    }

    public HSSFAnchor(int i, int i2, int i3, int i4) {
        createEscherAnchor();
        setDx1(i);
        setDy1(i2);
        setDx2(i3);
        setDy2(i4);
    }

    public static HSSFAnchor createAnchorFromEscher(EscherContainerRecord escherContainerRecord) {
        if (escherContainerRecord.getChildById(EscherChildAnchorRecord.RECORD_ID) != null) {
            return new HSSFChildAnchor((EscherChildAnchorRecord) escherContainerRecord.getChildById(EscherChildAnchorRecord.RECORD_ID));
        }
        if (escherContainerRecord.getChildById(EscherClientAnchorRecord.RECORD_ID) != null) {
            return new HSSFClientAnchor((EscherClientAnchorRecord) escherContainerRecord.getChildById(EscherClientAnchorRecord.RECORD_ID));
        }
        return null;
    }
}
