package org.openxmlformats.schemas.officeDocument.x2006.math;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTM extends XmlObject {
    public static final DocumentFactory<CTM> Factory;
    public static final SchemaType type;

    CTMPr addNewMPr();

    CTMR addNewMr();

    CTMPr getMPr();

    CTMR getMrArray(int i);

    CTMR[] getMrArray();

    List<CTMR> getMrList();

    CTMR insertNewMr(int i);

    boolean isSetMPr();

    void removeMr(int i);

    void setMPr(CTMPr cTMPr);

    void setMrArray(int i, CTMR ctmr);

    void setMrArray(CTMR[] ctmrArr);

    int sizeOfMrArray();

    void unsetMPr();

    static {
        DocumentFactory<CTM> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctm3f8ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
