package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;

public class CTGraphicalObjectImpl extends XmlComplexContentImpl implements CTGraphicalObject {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "graphicData")};
    private static final long serialVersionUID = 1;

    public CTGraphicalObjectImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTGraphicalObjectData getGraphicData() {
        CTGraphicalObjectData cTGraphicalObjectData;
        synchronized (monitor()) {
            check_orphaned();
            cTGraphicalObjectData = (CTGraphicalObjectData) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTGraphicalObjectData == null) {
                cTGraphicalObjectData = null;
            }
        }
        return cTGraphicalObjectData;
    }

    public void setGraphicData(CTGraphicalObjectData cTGraphicalObjectData) {
        generatedSetterHelperImpl(cTGraphicalObjectData, PROPERTY_QNAME[0], 0, 1);
    }

    public CTGraphicalObjectData addNewGraphicData() {
        CTGraphicalObjectData cTGraphicalObjectData;
        synchronized (monitor()) {
            check_orphaned();
            cTGraphicalObjectData = (CTGraphicalObjectData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTGraphicalObjectData;
    }
}
