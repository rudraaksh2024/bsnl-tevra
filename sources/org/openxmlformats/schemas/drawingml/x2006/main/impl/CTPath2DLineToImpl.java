package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;

public class CTPath2DLineToImpl extends XmlComplexContentImpl implements CTPath2DLineTo {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "pt")};
    private static final long serialVersionUID = 1;

    public CTPath2DLineToImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTAdjPoint2D getPt() {
        CTAdjPoint2D cTAdjPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            cTAdjPoint2D = (CTAdjPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTAdjPoint2D == null) {
                cTAdjPoint2D = null;
            }
        }
        return cTAdjPoint2D;
    }

    public void setPt(CTAdjPoint2D cTAdjPoint2D) {
        generatedSetterHelperImpl(cTAdjPoint2D, PROPERTY_QNAME[0], 0, 1);
    }

    public CTAdjPoint2D addNewPt() {
        CTAdjPoint2D cTAdjPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            cTAdjPoint2D = (CTAdjPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTAdjPoint2D;
    }
}
