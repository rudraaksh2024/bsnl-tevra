package org.openxmlformats.schemas.presentationml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCustomerDataList extends XmlObject {
    public static final DocumentFactory<CTCustomerDataList> Factory;
    public static final SchemaType type;

    CTCustomerData addNewCustData();

    CTTagsData addNewTags();

    CTCustomerData getCustDataArray(int i);

    CTCustomerData[] getCustDataArray();

    List<CTCustomerData> getCustDataList();

    CTTagsData getTags();

    CTCustomerData insertNewCustData(int i);

    boolean isSetTags();

    void removeCustData(int i);

    void setCustDataArray(int i, CTCustomerData cTCustomerData);

    void setCustDataArray(CTCustomerData[] cTCustomerDataArr);

    void setTags(CTTagsData cTTagsData);

    int sizeOfCustDataArray();

    void unsetTags();

    static {
        DocumentFactory<CTCustomerDataList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcustomerdatalist8b7ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
