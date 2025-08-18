package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTConnectionSiteList extends XmlObject {
    public static final DocumentFactory<CTConnectionSiteList> Factory;
    public static final SchemaType type;

    CTConnectionSite addNewCxn();

    CTConnectionSite getCxnArray(int i);

    CTConnectionSite[] getCxnArray();

    List<CTConnectionSite> getCxnList();

    CTConnectionSite insertNewCxn(int i);

    void removeCxn(int i);

    void setCxnArray(int i, CTConnectionSite cTConnectionSite);

    void setCxnArray(CTConnectionSite[] cTConnectionSiteArr);

    int sizeOfCxnArray();

    static {
        DocumentFactory<CTConnectionSiteList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctconnectionsitelistab9etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
