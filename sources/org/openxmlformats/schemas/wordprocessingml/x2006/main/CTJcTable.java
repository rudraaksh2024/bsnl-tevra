package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJcTable;

public interface CTJcTable extends XmlObject {
    public static final DocumentFactory<CTJcTable> Factory;
    public static final SchemaType type;

    STJcTable.Enum getVal();

    void setVal(STJcTable.Enum enumR);

    STJcTable xgetVal();

    void xsetVal(STJcTable sTJcTable);

    static {
        DocumentFactory<CTJcTable> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctjctablefa9dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
