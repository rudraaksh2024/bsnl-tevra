package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPath2DList extends XmlObject {
    public static final DocumentFactory<CTPath2DList> Factory;
    public static final SchemaType type;

    CTPath2D addNewPath();

    CTPath2D getPathArray(int i);

    CTPath2D[] getPathArray();

    List<CTPath2D> getPathList();

    CTPath2D insertNewPath(int i);

    void removePath(int i);

    void setPathArray(int i, CTPath2D cTPath2D);

    void setPathArray(CTPath2D[] cTPath2DArr);

    int sizeOfPathArray();

    static {
        DocumentFactory<CTPath2DList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpath2dlistb010type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
