package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ShapeSheetType extends SheetType {
    public static final DocumentFactory<ShapeSheetType> Factory;
    public static final SchemaType type;

    DataType addNewData1();

    DataType addNewData2();

    DataType addNewData3();

    ForeignDataType addNewForeignData();

    ShapesType addNewShapes();

    TextType addNewText();

    DataType getData1();

    DataType getData2();

    DataType getData3();

    boolean getDel();

    ForeignDataType getForeignData();

    long getID();

    boolean getIsCustomName();

    boolean getIsCustomNameU();

    long getMaster();

    long getMasterShape();

    String getName();

    String getNameU();

    long getOriginalID();

    ShapesType getShapes();

    TextType getText();

    String getType();

    String getUniqueID();

    boolean isSetData1();

    boolean isSetData2();

    boolean isSetData3();

    boolean isSetDel();

    boolean isSetForeignData();

    boolean isSetIsCustomName();

    boolean isSetIsCustomNameU();

    boolean isSetMaster();

    boolean isSetMasterShape();

    boolean isSetName();

    boolean isSetNameU();

    boolean isSetOriginalID();

    boolean isSetShapes();

    boolean isSetText();

    boolean isSetType();

    boolean isSetUniqueID();

    void setData1(DataType dataType);

    void setData2(DataType dataType);

    void setData3(DataType dataType);

    void setDel(boolean z);

    void setForeignData(ForeignDataType foreignDataType);

    void setID(long j);

    void setIsCustomName(boolean z);

    void setIsCustomNameU(boolean z);

    void setMaster(long j);

    void setMasterShape(long j);

    void setName(String str);

    void setNameU(String str);

    void setOriginalID(long j);

    void setShapes(ShapesType shapesType);

    void setText(TextType textType);

    void setType(String str);

    void setUniqueID(String str);

    void unsetData1();

    void unsetData2();

    void unsetData3();

    void unsetDel();

    void unsetForeignData();

    void unsetIsCustomName();

    void unsetIsCustomNameU();

    void unsetMaster();

    void unsetMasterShape();

    void unsetName();

    void unsetNameU();

    void unsetOriginalID();

    void unsetShapes();

    void unsetText();

    void unsetType();

    void unsetUniqueID();

    XmlBoolean xgetDel();

    XmlUnsignedInt xgetID();

    XmlBoolean xgetIsCustomName();

    XmlBoolean xgetIsCustomNameU();

    XmlUnsignedInt xgetMaster();

    XmlUnsignedInt xgetMasterShape();

    XmlString xgetName();

    XmlString xgetNameU();

    XmlUnsignedInt xgetOriginalID();

    XmlToken xgetType();

    XmlString xgetUniqueID();

    void xsetDel(XmlBoolean xmlBoolean);

    void xsetID(XmlUnsignedInt xmlUnsignedInt);

    void xsetIsCustomName(XmlBoolean xmlBoolean);

    void xsetIsCustomNameU(XmlBoolean xmlBoolean);

    void xsetMaster(XmlUnsignedInt xmlUnsignedInt);

    void xsetMasterShape(XmlUnsignedInt xmlUnsignedInt);

    void xsetName(XmlString xmlString);

    void xsetNameU(XmlString xmlString);

    void xsetOriginalID(XmlUnsignedInt xmlUnsignedInt);

    void xsetType(XmlToken xmlToken);

    void xsetUniqueID(XmlString xmlString);

    static {
        DocumentFactory<ShapeSheetType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "shapesheettype59bbtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
