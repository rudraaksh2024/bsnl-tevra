package org.openxmlformats.schemas.officeDocument.x2006.math;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTD extends XmlObject {
    public static final DocumentFactory<CTD> Factory;
    public static final SchemaType type;

    CTDPr addNewDPr();

    CTOMathArg addNewE();

    CTDPr getDPr();

    CTOMathArg getEArray(int i);

    CTOMathArg[] getEArray();

    List<CTOMathArg> getEList();

    CTOMathArg insertNewE(int i);

    boolean isSetDPr();

    void removeE(int i);

    void setDPr(CTDPr cTDPr);

    void setEArray(int i, CTOMathArg cTOMathArg);

    void setEArray(CTOMathArg[] cTOMathArgArr);

    int sizeOfEArray();

    void unsetDPr();

    static {
        DocumentFactory<CTD> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctd1938type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
