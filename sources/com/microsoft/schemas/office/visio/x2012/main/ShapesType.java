package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ShapesType extends XmlObject {
    public static final DocumentFactory<ShapesType> Factory;
    public static final SchemaType type;

    ShapeSheetType addNewShape();

    ShapeSheetType getShapeArray(int i);

    ShapeSheetType[] getShapeArray();

    List<ShapeSheetType> getShapeList();

    ShapeSheetType insertNewShape(int i);

    void removeShape(int i);

    void setShapeArray(int i, ShapeSheetType shapeSheetType);

    void setShapeArray(ShapeSheetType[] shapeSheetTypeArr);

    int sizeOfShapeArray();

    static {
        DocumentFactory<ShapesType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "shapestypef507type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
