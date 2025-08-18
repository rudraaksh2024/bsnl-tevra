package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

public interface CTLevelText extends XmlObject {
    public static final DocumentFactory<CTLevelText> Factory;
    public static final SchemaType type;

    Object getNull();

    String getVal();

    boolean isSetNull();

    boolean isSetVal();

    void setNull(Object obj);

    void setVal(String str);

    void unsetNull();

    void unsetVal();

    STOnOff xgetNull();

    STString xgetVal();

    void xsetNull(STOnOff sTOnOff);

    void xsetVal(STString sTString);

    static {
        DocumentFactory<CTLevelText> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctleveltext0621type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
