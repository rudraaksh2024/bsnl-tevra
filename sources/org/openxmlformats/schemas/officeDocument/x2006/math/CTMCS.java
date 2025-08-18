package org.openxmlformats.schemas.officeDocument.x2006.math;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTMCS extends XmlObject {
    public static final DocumentFactory<CTMCS> Factory;
    public static final SchemaType type;

    CTMC addNewMc();

    CTMC getMcArray(int i);

    CTMC[] getMcArray();

    List<CTMC> getMcList();

    CTMC insertNewMc(int i);

    void removeMc(int i);

    void setMcArray(int i, CTMC ctmc);

    void setMcArray(CTMC[] ctmcArr);

    int sizeOfMcArray();

    static {
        DocumentFactory<CTMCS> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmcs4b1ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
