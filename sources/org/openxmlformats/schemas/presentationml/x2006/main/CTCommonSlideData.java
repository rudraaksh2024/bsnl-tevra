package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCommonSlideData extends XmlObject {
    public static final DocumentFactory<CTCommonSlideData> Factory;
    public static final SchemaType type;

    CTBackground addNewBg();

    CTControlList addNewControls();

    CTCustomerDataList addNewCustDataLst();

    CTExtensionList addNewExtLst();

    CTGroupShape addNewSpTree();

    CTBackground getBg();

    CTControlList getControls();

    CTCustomerDataList getCustDataLst();

    CTExtensionList getExtLst();

    String getName();

    CTGroupShape getSpTree();

    boolean isSetBg();

    boolean isSetControls();

    boolean isSetCustDataLst();

    boolean isSetExtLst();

    boolean isSetName();

    void setBg(CTBackground cTBackground);

    void setControls(CTControlList cTControlList);

    void setCustDataLst(CTCustomerDataList cTCustomerDataList);

    void setExtLst(CTExtensionList cTExtensionList);

    void setName(String str);

    void setSpTree(CTGroupShape cTGroupShape);

    void unsetBg();

    void unsetControls();

    void unsetCustDataLst();

    void unsetExtLst();

    void unsetName();

    XmlString xgetName();

    void xsetName(XmlString xmlString);

    static {
        DocumentFactory<CTCommonSlideData> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcommonslidedata8c7ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
