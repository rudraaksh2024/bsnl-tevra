package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSectionMark;

public interface CTSectType extends XmlObject {
    public static final DocumentFactory<CTSectType> Factory;
    public static final SchemaType type;

    STSectionMark.Enum getVal();

    boolean isSetVal();

    void setVal(STSectionMark.Enum enumR);

    void unsetVal();

    STSectionMark xgetVal();

    void xsetVal(STSectionMark sTSectionMark);

    static {
        DocumentFactory<CTSectType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsecttype7cebtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
