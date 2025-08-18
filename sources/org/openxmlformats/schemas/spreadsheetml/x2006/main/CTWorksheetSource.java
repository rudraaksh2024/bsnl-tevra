package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTWorksheetSource extends XmlObject {
    public static final DocumentFactory<CTWorksheetSource> Factory;
    public static final SchemaType type;

    String getId();

    String getName();

    String getRef();

    String getSheet();

    boolean isSetId();

    boolean isSetName();

    boolean isSetRef();

    boolean isSetSheet();

    void setId(String str);

    void setName(String str);

    void setRef(String str);

    void setSheet(String str);

    void unsetId();

    void unsetName();

    void unsetRef();

    void unsetSheet();

    STRelationshipId xgetId();

    STXstring xgetName();

    STRef xgetRef();

    STXstring xgetSheet();

    void xsetId(STRelationshipId sTRelationshipId);

    void xsetName(STXstring sTXstring);

    void xsetRef(STRef sTRef);

    void xsetSheet(STXstring sTXstring);

    static {
        DocumentFactory<CTWorksheetSource> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctworksheetsourced4c8type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
