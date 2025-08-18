package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMapInfo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.MapInfoDocument;

public class MapInfoDocumentImpl extends XmlComplexContentImpl implements MapInfoDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "MapInfo")};
    private static final long serialVersionUID = 1;

    public MapInfoDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTMapInfo getMapInfo() {
        CTMapInfo cTMapInfo;
        synchronized (monitor()) {
            check_orphaned();
            cTMapInfo = (CTMapInfo) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTMapInfo == null) {
                cTMapInfo = null;
            }
        }
        return cTMapInfo;
    }

    public void setMapInfo(CTMapInfo cTMapInfo) {
        generatedSetterHelperImpl(cTMapInfo, PROPERTY_QNAME[0], 0, 1);
    }

    public CTMapInfo addNewMapInfo() {
        CTMapInfo cTMapInfo;
        synchronized (monitor()) {
            check_orphaned();
            cTMapInfo = (CTMapInfo) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTMapInfo;
    }
}
