package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSSub extends XmlObject {
    public static final DocumentFactory<CTSSub> Factory;
    public static final SchemaType type;

    CTOMathArg addNewE();

    CTSSubPr addNewSSubPr();

    CTOMathArg addNewSub();

    CTOMathArg getE();

    CTSSubPr getSSubPr();

    CTOMathArg getSub();

    boolean isSetSSubPr();

    void setE(CTOMathArg cTOMathArg);

    void setSSubPr(CTSSubPr cTSSubPr);

    void setSub(CTOMathArg cTOMathArg);

    void unsetSSubPr();

    static {
        DocumentFactory<CTSSub> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctssubfdc5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
