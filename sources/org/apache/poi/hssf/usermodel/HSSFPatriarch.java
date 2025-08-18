package org.apache.poi.hssf.usermodel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import org.apache.poi.ddf.EscherComplexProperty;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherDgRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherProperty;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSpgrRecord;
import org.apache.poi.hssf.model.DrawingManager2;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.FtCfSubRecord;
import org.apache.poi.hssf.record.FtPioGrbitSubRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.Internal;
import org.apache.poi.util.StringUtil;

public final class HSSFPatriarch implements HSSFShapeContainer, Drawing<HSSFShape> {
    private EscherAggregate _boundAggregate;
    private final EscherContainerRecord _mainSpgrContainer;
    private final List<HSSFShape> _shapes = new ArrayList();
    private final HSSFSheet _sheet;
    private final EscherSpgrRecord _spgrRecord;

    HSSFPatriarch(HSSFSheet hSSFSheet, EscherAggregate escherAggregate) {
        this._sheet = hSSFSheet;
        this._boundAggregate = escherAggregate;
        this._mainSpgrContainer = escherAggregate.getEscherContainer().getChildContainers().get(0);
        this._spgrRecord = (EscherSpgrRecord) ((EscherContainerRecord) this._boundAggregate.getEscherContainer().getChildContainers().get(0).getChild(0)).getChildById(EscherSpgrRecord.RECORD_ID);
        buildShapeTree();
    }

    static HSSFPatriarch createPatriarch(HSSFPatriarch hSSFPatriarch, HSSFSheet hSSFSheet) {
        HSSFShape hSSFShape;
        HSSFPatriarch hSSFPatriarch2 = new HSSFPatriarch(hSSFSheet, new EscherAggregate(true));
        hSSFPatriarch2.afterCreate();
        for (HSSFShape next : hSSFPatriarch.getChildren()) {
            if (next instanceof HSSFShapeGroup) {
                hSSFShape = ((HSSFShapeGroup) next).cloneShape(hSSFPatriarch2);
            } else {
                hSSFShape = next.cloneShape();
            }
            hSSFPatriarch2.onCreate(hSSFShape);
            hSSFPatriarch2.addShape(hSSFShape);
        }
        return hSSFPatriarch2;
    }

    /* access modifiers changed from: protected */
    public void preSerialize() {
        Map<Integer, NoteRecord> tailRecords = this._boundAggregate.getTailRecords();
        HashSet hashSet = new HashSet(tailRecords.size());
        for (NoteRecord next : tailRecords.values()) {
            String formatAsString = new CellReference(next.getRow(), next.getColumn(), true, true).formatAsString();
            if (!hashSet.contains(formatAsString)) {
                hashSet.add(formatAsString);
            } else {
                throw new IllegalStateException("found multiple cell comments for cell " + formatAsString);
            }
        }
    }

    public boolean removeShape(HSSFShape hSSFShape) {
        boolean removeChildRecord = this._mainSpgrContainer.removeChildRecord(hSSFShape.getEscherContainer());
        if (removeChildRecord) {
            hSSFShape.afterRemove(this);
            this._shapes.remove(hSSFShape);
        }
        return removeChildRecord;
    }

    /* access modifiers changed from: package-private */
    public void afterCreate() {
        DrawingManager2 drawingManager = this._sheet.getWorkbook().getWorkbook().getDrawingManager();
        this._boundAggregate.setDgId(drawingManager.findNewDrawingGroupId());
        this._boundAggregate.setMainSpRecordId(newShapeId());
        drawingManager.incrementDrawingsSaved();
    }

    public HSSFShapeGroup createGroup(HSSFClientAnchor hSSFClientAnchor) {
        HSSFShapeGroup hSSFShapeGroup = new HSSFShapeGroup((HSSFShape) null, (HSSFAnchor) hSSFClientAnchor);
        addShape(hSSFShapeGroup);
        onCreate(hSSFShapeGroup);
        return hSSFShapeGroup;
    }

    public HSSFSimpleShape createSimpleShape(HSSFClientAnchor hSSFClientAnchor) {
        HSSFSimpleShape hSSFSimpleShape = new HSSFSimpleShape((HSSFShape) null, (HSSFAnchor) hSSFClientAnchor);
        addShape(hSSFSimpleShape);
        onCreate(hSSFSimpleShape);
        return hSSFSimpleShape;
    }

    public HSSFPicture createPicture(HSSFClientAnchor hSSFClientAnchor, int i) {
        HSSFPicture hSSFPicture = new HSSFPicture((HSSFShape) null, (HSSFAnchor) hSSFClientAnchor);
        hSSFPicture.setPictureIndex(i);
        addShape(hSSFPicture);
        onCreate(hSSFPicture);
        return hSSFPicture;
    }

    public HSSFPicture createPicture(ClientAnchor clientAnchor, int i) {
        return createPicture((HSSFClientAnchor) clientAnchor, i);
    }

    public HSSFObjectData createObjectData(ClientAnchor clientAnchor, int i, int i2) {
        ObjRecord objRecord = new ObjRecord();
        CommonObjectDataSubRecord commonObjectDataSubRecord = new CommonObjectDataSubRecord();
        commonObjectDataSubRecord.setObjectType(8);
        commonObjectDataSubRecord.setLocked(true);
        commonObjectDataSubRecord.setPrintable(true);
        commonObjectDataSubRecord.setAutofill(true);
        commonObjectDataSubRecord.setAutoline(true);
        commonObjectDataSubRecord.setReserved1(0);
        commonObjectDataSubRecord.setReserved2(0);
        commonObjectDataSubRecord.setReserved3(0);
        objRecord.addSubRecord(commonObjectDataSubRecord);
        FtCfSubRecord ftCfSubRecord = new FtCfSubRecord();
        HSSFPictureData hSSFPictureData = getSheet().getWorkbook().getAllPictures().get(i2 - 1);
        switch (hSSFPictureData.getFormat()) {
            case 2:
            case 3:
                ftCfSubRecord.setFlags(2);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                ftCfSubRecord.setFlags(9);
                break;
            default:
                throw new IllegalStateException("Invalid picture type: " + hSSFPictureData.getFormat());
        }
        objRecord.addSubRecord(ftCfSubRecord);
        FtPioGrbitSubRecord ftPioGrbitSubRecord = new FtPioGrbitSubRecord();
        ftPioGrbitSubRecord.setFlagByBit(1, true);
        objRecord.addSubRecord(ftPioGrbitSubRecord);
        EmbeddedObjectRefSubRecord embeddedObjectRefSubRecord = new EmbeddedObjectRefSubRecord();
        embeddedObjectRefSubRecord.setUnknownFormulaData(new byte[]{2, 0, 0, 0, 0});
        embeddedObjectRefSubRecord.setOleClassname("Paket");
        embeddedObjectRefSubRecord.setStorageId(i);
        objRecord.addSubRecord(embeddedObjectRefSubRecord);
        objRecord.addSubRecord(new EndSubRecord());
        String str = "MBD" + HexDump.toHex(i);
        try {
            DirectoryNode directory = this._sheet.getWorkbook().getDirectory();
            if (directory != null) {
                DirectoryEntry directoryEntry = (DirectoryEntry) directory.getEntry(str);
                HSSFPicture hSSFPicture = new HSSFPicture((HSSFShape) null, (HSSFAnchor) (HSSFClientAnchor) clientAnchor);
                hSSFPicture.setPictureIndex(i2);
                EscherContainerRecord escherContainer = hSSFPicture.getEscherContainer();
                EscherSpRecord escherSpRecord = (EscherSpRecord) escherContainer.getChildById(EscherSpRecord.RECORD_ID);
                escherSpRecord.setFlags(escherSpRecord.getFlags() | 16);
                HSSFObjectData hSSFObjectData = new HSSFObjectData(escherContainer, objRecord, directoryEntry);
                addShape(hSSFObjectData);
                onCreate(hSSFObjectData);
                return hSSFObjectData;
            }
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("trying to add ole shape without actually adding data first - use HSSFWorkbook.addOlePackage first", e);
        }
    }

    public HSSFPolygon createPolygon(HSSFClientAnchor hSSFClientAnchor) {
        HSSFPolygon hSSFPolygon = new HSSFPolygon((HSSFShape) null, (HSSFAnchor) hSSFClientAnchor);
        addShape(hSSFPolygon);
        onCreate(hSSFPolygon);
        return hSSFPolygon;
    }

    public HSSFTextbox createTextbox(HSSFClientAnchor hSSFClientAnchor) {
        HSSFTextbox hSSFTextbox = new HSSFTextbox((HSSFShape) null, hSSFClientAnchor);
        addShape(hSSFTextbox);
        onCreate(hSSFTextbox);
        return hSSFTextbox;
    }

    public HSSFComment createComment(HSSFAnchor hSSFAnchor) {
        HSSFComment hSSFComment = new HSSFComment((HSSFShape) null, hSSFAnchor);
        addShape(hSSFComment);
        onCreate(hSSFComment);
        return hSSFComment;
    }

    /* access modifiers changed from: package-private */
    public HSSFSimpleShape createComboBox(HSSFAnchor hSSFAnchor) {
        HSSFCombobox hSSFCombobox = new HSSFCombobox((HSSFShape) null, hSSFAnchor);
        addShape(hSSFCombobox);
        onCreate(hSSFCombobox);
        return hSSFCombobox;
    }

    public HSSFComment createCellComment(ClientAnchor clientAnchor) {
        return createComment((HSSFAnchor) clientAnchor);
    }

    public List<HSSFShape> getChildren() {
        return Collections.unmodifiableList(this._shapes);
    }

    @Internal
    public void addShape(HSSFShape hSSFShape) {
        hSSFShape.setPatriarch(this);
        this._shapes.add(hSSFShape);
    }

    private void onCreate(HSSFShape hSSFShape) {
        EscherContainerRecord escherContainer = hSSFShape.getEscherContainer();
        hSSFShape.setShapeId(newShapeId());
        this._boundAggregate.getEscherContainer().getChildContainers().get(0).addChildRecord(escherContainer);
        hSSFShape.afterInsert(this);
        setFlipFlags(hSSFShape);
    }

    public int countOfAllChildren() {
        int size = this._shapes.size();
        for (HSSFShape countOfAllChildren : this._shapes) {
            size += countOfAllChildren.countOfAllChildren();
        }
        return size;
    }

    public void setCoordinates(int i, int i2, int i3, int i4) {
        this._spgrRecord.setRectY1(i2);
        this._spgrRecord.setRectY2(i4);
        this._spgrRecord.setRectX1(i);
        this._spgrRecord.setRectX2(i3);
    }

    public void clear() {
        Iterator it = new ArrayList(this._shapes).iterator();
        while (it.hasNext()) {
            removeShape((HSSFShape) it.next());
        }
    }

    /* access modifiers changed from: package-private */
    public int newShapeId() {
        return this._sheet.getWorkbook().getWorkbook().getDrawingManager().allocateShapeId((EscherDgRecord) this._boundAggregate.getEscherContainer().getChildById(EscherDgRecord.RECORD_ID));
    }

    public boolean containsChart() {
        EscherOptRecord escherOptRecord = (EscherOptRecord) this._boundAggregate.findFirstWithId(EscherOptRecord.RECORD_ID);
        if (escherOptRecord == null) {
            return false;
        }
        for (EscherProperty next : escherOptRecord.getEscherProperties()) {
            if (next.getPropertyNumber() == 896 && next.isComplex() && StringUtil.getFromUnicodeLE(((EscherComplexProperty) next).getComplexData()).equals("Chart 1\u0000")) {
                return true;
            }
        }
        return false;
    }

    public int getX1() {
        return this._spgrRecord.getRectX1();
    }

    public int getY1() {
        return this._spgrRecord.getRectY1();
    }

    public int getX2() {
        return this._spgrRecord.getRectX2();
    }

    public int getY2() {
        return this._spgrRecord.getRectY2();
    }

    @Internal
    public EscherAggregate getBoundAggregate() {
        return this._boundAggregate;
    }

    public HSSFClientAnchor createAnchor(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return new HSSFClientAnchor(i, i2, i3, i4, (short) i5, i6, (short) i7, i8);
    }

    /* access modifiers changed from: package-private */
    public void buildShapeTree() {
        EscherContainerRecord escherContainer = this._boundAggregate.getEscherContainer();
        if (escherContainer != null) {
            List<EscherContainerRecord> childContainers = escherContainer.getChildContainers().get(0).getChildContainers();
            for (int i = 0; i < childContainers.size(); i++) {
                EscherContainerRecord escherContainerRecord = childContainers.get(i);
                if (i != 0) {
                    HSSFShapeFactory.createShapeTree(escherContainerRecord, this._boundAggregate, this, this._sheet.getWorkbook().getDirectory());
                }
            }
        }
    }

    private void setFlipFlags(HSSFShape hSSFShape) {
        EscherSpRecord escherSpRecord = (EscherSpRecord) hSSFShape.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        if (hSSFShape.getAnchor().isHorizontallyFlipped()) {
            escherSpRecord.setFlags(escherSpRecord.getFlags() | 64);
        }
        if (hSSFShape.getAnchor().isVerticallyFlipped()) {
            escherSpRecord.setFlags(escherSpRecord.getFlags() | 128);
        }
    }

    public Iterator<HSSFShape> iterator() {
        return this._shapes.iterator();
    }

    public Spliterator<HSSFShape> spliterator() {
        return this._shapes.spliterator();
    }

    /* access modifiers changed from: protected */
    public HSSFSheet getSheet() {
        return this._sheet;
    }
}
