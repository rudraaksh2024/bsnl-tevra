package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTExternalSheetNames extends XmlObject {
    public static final DocumentFactory<CTExternalSheetNames> Factory;
    public static final SchemaType type;

    CTExternalSheetName addNewSheetName();

    CTExternalSheetName getSheetNameArray(int i);

    CTExternalSheetName[] getSheetNameArray();

    List<CTExternalSheetName> getSheetNameList();

    CTExternalSheetName insertNewSheetName(int i);

    void removeSheetName(int i);

    void setSheetNameArray(int i, CTExternalSheetName cTExternalSheetName);

    void setSheetNameArray(CTExternalSheetName[] cTExternalSheetNameArr);

    int sizeOfSheetNameArray();

    static {
        DocumentFactory<CTExternalSheetNames> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternalsheetnames7eddtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
