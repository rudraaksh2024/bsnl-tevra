package org.apache.poi.hssf.usermodel;

import java.awt.Dimension;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherBSERecord;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherComplexProperty;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.ddf.EscherTextboxRecord;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.util.ImageUtils;
import org.apache.poi.util.StringUtil;

public class HSSFPicture extends HSSFSimpleShape implements Picture {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public HSSFPicture(EscherContainerRecord escherContainerRecord, ObjRecord objRecord) {
        super(escherContainerRecord, objRecord);
    }

    public HSSFPicture(HSSFShape hSSFShape, HSSFAnchor hSSFAnchor) {
        super(hSSFShape, hSSFAnchor);
        super.setShapeType(75);
        ((CommonObjectDataSubRecord) getObjRecord().getSubRecords().get(0)).setObjectType(8);
    }

    public int getPictureIndex() {
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.BLIP__BLIPTODISPLAY);
        if (escherSimpleProperty == null) {
            return -1;
        }
        return escherSimpleProperty.getPropertyValue();
    }

    public void setPictureIndex(int i) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.BLIP__BLIPTODISPLAY, false, true, i));
    }

    /* access modifiers changed from: protected */
    public EscherContainerRecord createSpContainer() {
        EscherContainerRecord createSpContainer = super.createSpContainer();
        EscherOptRecord escherOptRecord = (EscherOptRecord) createSpContainer.getChildById(EscherOptRecord.RECORD_ID);
        escherOptRecord.removeEscherProperty(EscherPropertyTypes.LINESTYLE__LINEDASHING);
        escherOptRecord.removeEscherProperty(EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH);
        createSpContainer.removeChildRecord(createSpContainer.getChildById(EscherTextboxRecord.RECORD_ID));
        return createSpContainer;
    }

    public void resize() {
        resize(Double.MAX_VALUE);
    }

    public void resize(double d) {
        resize(d, d);
    }

    public void resize(double d, double d2) {
        HSSFClientAnchor clientAnchor = getClientAnchor();
        clientAnchor.setAnchorType(ClientAnchor.AnchorType.MOVE_DONT_RESIZE);
        HSSFClientAnchor preferredSize = getPreferredSize(d, d2);
        int row1 = clientAnchor.getRow1() + (preferredSize.getRow2() - preferredSize.getRow1());
        clientAnchor.setCol2((short) (clientAnchor.getCol1() + (preferredSize.getCol2() - preferredSize.getCol1())));
        clientAnchor.setDx2(preferredSize.getDx2());
        clientAnchor.setRow2(row1);
        clientAnchor.setDy2(preferredSize.getDy2());
    }

    public HSSFClientAnchor getPreferredSize() {
        return getPreferredSize(1.0d);
    }

    public HSSFClientAnchor getPreferredSize(double d) {
        return getPreferredSize(d, d);
    }

    public HSSFClientAnchor getPreferredSize(double d, double d2) {
        ImageUtils.setPreferredSize(this, d, d2);
        return getClientAnchor();
    }

    public Dimension getImageDimension() {
        EscherBSERecord bSERecord = getPatriarch().getSheet().getWorkbook().getWorkbook().getBSERecord(getPictureIndex());
        byte[] picturedata = bSERecord.getBlipRecord().getPicturedata();
        return ImageUtils.getImageDimension(new UnsynchronizedByteArrayInputStream(picturedata), bSERecord.getBlipTypeWin32());
    }

    public HSSFPictureData getPictureData() {
        int pictureIndex = getPictureIndex();
        if (pictureIndex == -1) {
            return null;
        }
        HSSFPatriarch patriarch = getPatriarch();
        HSSFShape parent = getParent();
        while (patriarch == null && parent != null) {
            patriarch = parent.getPatriarch();
            parent = parent.getParent();
        }
        if (patriarch != null) {
            return new HSSFPictureData(patriarch.getSheet().getWorkbook().getWorkbook().getBSERecord(pictureIndex).getBlipRecord());
        }
        throw new IllegalStateException("Could not find a patriarch for a HSSPicture");
    }

    /* access modifiers changed from: package-private */
    public void afterInsert(HSSFPatriarch hSSFPatriarch) {
        hSSFPatriarch.getBoundAggregate().associateShapeToObjRecord(getEscherContainer().getChildById(EscherClientDataRecord.RECORD_ID), getObjRecord());
        if (getPictureIndex() != -1) {
            EscherBSERecord bSERecord = hSSFPatriarch.getSheet().getWorkbook().getWorkbook().getBSERecord(getPictureIndex());
            bSERecord.setRef(bSERecord.getRef() + 1);
        }
    }

    public String getFileName() {
        EscherComplexProperty escherComplexProperty = (EscherComplexProperty) getOptRecord().lookup(EscherPropertyTypes.BLIP__BLIPFILENAME);
        if (escherComplexProperty == null) {
            return "";
        }
        return StringUtil.getFromUnicodeLE(escherComplexProperty.getComplexData()).trim();
    }

    public void setFileName(String str) {
        byte[] toUnicodeLE = StringUtil.getToUnicodeLE(str);
        EscherComplexProperty escherComplexProperty = new EscherComplexProperty(EscherPropertyTypes.BLIP__BLIPFILENAME, true, toUnicodeLE.length);
        escherComplexProperty.setComplexData(toUnicodeLE);
        setPropertyValue(escherComplexProperty);
    }

    public void setShapeType(int i) {
        throw new IllegalStateException("Shape type can not be changed in " + getClass().getSimpleName());
    }

    /* access modifiers changed from: protected */
    public HSSFShape cloneShape() {
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        escherContainerRecord.fillFields(getEscherContainer().serialize(), 0, new DefaultEscherRecordFactory());
        return new HSSFPicture(escherContainerRecord, (ObjRecord) getObjRecord().cloneViaReserialise());
    }

    public HSSFClientAnchor getClientAnchor() {
        HSSFAnchor anchor = getAnchor();
        if (anchor instanceof HSSFClientAnchor) {
            return (HSSFClientAnchor) anchor;
        }
        return null;
    }

    public HSSFSheet getSheet() {
        return getPatriarch().getSheet();
    }
}
