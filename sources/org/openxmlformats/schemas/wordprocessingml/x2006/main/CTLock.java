package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLock;

public interface CTLock extends XmlObject {
    public static final DocumentFactory<CTLock> Factory;
    public static final SchemaType type;

    STLock.Enum getVal();

    boolean isSetVal();

    void setVal(STLock.Enum enumR);

    void unsetVal();

    STLock xgetVal();

    void xsetVal(STLock sTLock);

    static {
        DocumentFactory<CTLock> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlock201dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
