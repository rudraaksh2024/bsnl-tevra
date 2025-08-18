package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

public interface CTNumFmt extends XmlObject {
    public static final DocumentFactory<CTNumFmt> Factory;
    public static final SchemaType type;

    String getFormat();

    STNumberFormat.Enum getVal();

    boolean isSetFormat();

    void setFormat(String str);

    void setVal(STNumberFormat.Enum enumR);

    void unsetFormat();

    STString xgetFormat();

    STNumberFormat xgetVal();

    void xsetFormat(STString sTString);

    void xsetVal(STNumberFormat sTNumberFormat);

    static {
        DocumentFactory<CTNumFmt> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnumfmt00e1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
