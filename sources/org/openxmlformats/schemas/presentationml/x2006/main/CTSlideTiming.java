package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSlideTiming extends XmlObject {
    public static final DocumentFactory<CTSlideTiming> Factory;
    public static final SchemaType type;

    CTBuildList addNewBldLst();

    CTExtensionListModify addNewExtLst();

    CTTimeNodeList addNewTnLst();

    CTBuildList getBldLst();

    CTExtensionListModify getExtLst();

    CTTimeNodeList getTnLst();

    boolean isSetBldLst();

    boolean isSetExtLst();

    boolean isSetTnLst();

    void setBldLst(CTBuildList cTBuildList);

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setTnLst(CTTimeNodeList cTTimeNodeList);

    void unsetBldLst();

    void unsetExtLst();

    void unsetTnLst();

    static {
        DocumentFactory<CTSlideTiming> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctslidetiming4214type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
