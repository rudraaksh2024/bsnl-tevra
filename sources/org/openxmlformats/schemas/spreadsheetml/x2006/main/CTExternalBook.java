package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface CTExternalBook extends XmlObject {
    public static final DocumentFactory<CTExternalBook> Factory;
    public static final SchemaType type;

    CTExternalDefinedNames addNewDefinedNames();

    CTExternalSheetDataSet addNewSheetDataSet();

    CTExternalSheetNames addNewSheetNames();

    CTExternalDefinedNames getDefinedNames();

    String getId();

    CTExternalSheetDataSet getSheetDataSet();

    CTExternalSheetNames getSheetNames();

    boolean isSetDefinedNames();

    boolean isSetSheetDataSet();

    boolean isSetSheetNames();

    void setDefinedNames(CTExternalDefinedNames cTExternalDefinedNames);

    void setId(String str);

    void setSheetDataSet(CTExternalSheetDataSet cTExternalSheetDataSet);

    void setSheetNames(CTExternalSheetNames cTExternalSheetNames);

    void unsetDefinedNames();

    void unsetSheetDataSet();

    void unsetSheetNames();

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);

    static {
        DocumentFactory<CTExternalBook> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternalbookc89dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
