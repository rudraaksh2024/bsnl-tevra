package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingShapeProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShapeNonVisual;

public class CTShapeNonVisualImpl extends XmlComplexContentImpl implements CTShapeNonVisual {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPr")};
    private static final long serialVersionUID = 1;

    public CTShapeNonVisualImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTNonVisualDrawingProps getCNvPr() {
        CTNonVisualDrawingProps cTNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            cTNonVisualDrawingProps = (CTNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTNonVisualDrawingProps == null) {
                cTNonVisualDrawingProps = null;
            }
        }
        return cTNonVisualDrawingProps;
    }

    public void setCNvPr(CTNonVisualDrawingProps cTNonVisualDrawingProps) {
        generatedSetterHelperImpl(cTNonVisualDrawingProps, PROPERTY_QNAME[0], 0, 1);
    }

    public CTNonVisualDrawingProps addNewCNvPr() {
        CTNonVisualDrawingProps cTNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            cTNonVisualDrawingProps = (CTNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTNonVisualDrawingProps;
    }

    public CTNonVisualDrawingShapeProps getCNvSpPr() {
        CTNonVisualDrawingShapeProps cTNonVisualDrawingShapeProps;
        synchronized (monitor()) {
            check_orphaned();
            cTNonVisualDrawingShapeProps = (CTNonVisualDrawingShapeProps) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTNonVisualDrawingShapeProps == null) {
                cTNonVisualDrawingShapeProps = null;
            }
        }
        return cTNonVisualDrawingShapeProps;
    }

    public void setCNvSpPr(CTNonVisualDrawingShapeProps cTNonVisualDrawingShapeProps) {
        generatedSetterHelperImpl(cTNonVisualDrawingShapeProps, PROPERTY_QNAME[1], 0, 1);
    }

    public CTNonVisualDrawingShapeProps addNewCNvSpPr() {
        CTNonVisualDrawingShapeProps cTNonVisualDrawingShapeProps;
        synchronized (monitor()) {
            check_orphaned();
            cTNonVisualDrawingShapeProps = (CTNonVisualDrawingShapeProps) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTNonVisualDrawingShapeProps;
    }

    public CTApplicationNonVisualDrawingProps getNvPr() {
        CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            cTApplicationNonVisualDrawingProps = (CTApplicationNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTApplicationNonVisualDrawingProps == null) {
                cTApplicationNonVisualDrawingProps = null;
            }
        }
        return cTApplicationNonVisualDrawingProps;
    }

    public void setNvPr(CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps) {
        generatedSetterHelperImpl(cTApplicationNonVisualDrawingProps, PROPERTY_QNAME[2], 0, 1);
    }

    public CTApplicationNonVisualDrawingProps addNewNvPr() {
        CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            cTApplicationNonVisualDrawingProps = (CTApplicationNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTApplicationNonVisualDrawingProps;
    }
}
