package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

public interface CTVMerge extends XmlObject {
    public static final DocumentFactory<CTVMerge> Factory;
    public static final SchemaType type;

    STMerge.Enum getVal();

    boolean isSetVal();

    void setVal(STMerge.Enum enumR);

    void unsetVal();

    STMerge xgetVal();

    void xsetVal(STMerge sTMerge);

    static {
        DocumentFactory<CTVMerge> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctvmergee086type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
