package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTScaling extends XmlObject {
    public static final DocumentFactory<CTScaling> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTLogBase addNewLogBase();

    CTDouble addNewMax();

    CTDouble addNewMin();

    CTOrientation addNewOrientation();

    CTExtensionList getExtLst();

    CTLogBase getLogBase();

    CTDouble getMax();

    CTDouble getMin();

    CTOrientation getOrientation();

    boolean isSetExtLst();

    boolean isSetLogBase();

    boolean isSetMax();

    boolean isSetMin();

    boolean isSetOrientation();

    void setExtLst(CTExtensionList cTExtensionList);

    void setLogBase(CTLogBase cTLogBase);

    void setMax(CTDouble cTDouble);

    void setMin(CTDouble cTDouble);

    void setOrientation(CTOrientation cTOrientation);

    void unsetExtLst();

    void unsetLogBase();

    void unsetMax();

    void unsetMin();

    void unsetOrientation();

    static {
        DocumentFactory<CTScaling> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctscaling1dfftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
