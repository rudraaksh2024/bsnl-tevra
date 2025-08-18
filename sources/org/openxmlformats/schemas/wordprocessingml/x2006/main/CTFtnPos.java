package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFtnPos;

public interface CTFtnPos extends XmlObject {
    public static final DocumentFactory<CTFtnPos> Factory;
    public static final SchemaType type;

    STFtnPos.Enum getVal();

    void setVal(STFtnPos.Enum enumR);

    STFtnPos xgetVal();

    void xsetVal(STFtnPos sTFtnPos);

    static {
        DocumentFactory<CTFtnPos> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctftnposd254type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
