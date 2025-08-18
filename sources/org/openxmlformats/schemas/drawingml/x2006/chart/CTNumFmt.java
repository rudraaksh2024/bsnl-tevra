package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTNumFmt extends XmlObject {
    public static final DocumentFactory<CTNumFmt> Factory;
    public static final SchemaType type;

    String getFormatCode();

    boolean getSourceLinked();

    boolean isSetSourceLinked();

    void setFormatCode(String str);

    void setSourceLinked(boolean z);

    void unsetSourceLinked();

    STXstring xgetFormatCode();

    XmlBoolean xgetSourceLinked();

    void xsetFormatCode(STXstring sTXstring);

    void xsetSourceLinked(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTNumFmt> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnumfmtc0f5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
