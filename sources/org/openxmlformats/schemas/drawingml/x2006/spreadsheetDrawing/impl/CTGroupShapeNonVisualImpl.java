package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShapeNonVisual;

public class CTGroupShapeNonVisualImpl extends XmlComplexContentImpl implements CTGroupShapeNonVisual {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cNvPr"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cNvGrpSpPr")};
    private static final long serialVersionUID = 1;

    public CTGroupShapeNonVisualImpl(SchemaType schemaType) {
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

    public CTNonVisualGroupDrawingShapeProps getCNvGrpSpPr() {
        CTNonVisualGroupDrawingShapeProps cTNonVisualGroupDrawingShapeProps;
        synchronized (monitor()) {
            check_orphaned();
            cTNonVisualGroupDrawingShapeProps = (CTNonVisualGroupDrawingShapeProps) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTNonVisualGroupDrawingShapeProps == null) {
                cTNonVisualGroupDrawingShapeProps = null;
            }
        }
        return cTNonVisualGroupDrawingShapeProps;
    }

    public void setCNvGrpSpPr(CTNonVisualGroupDrawingShapeProps cTNonVisualGroupDrawingShapeProps) {
        generatedSetterHelperImpl(cTNonVisualGroupDrawingShapeProps, PROPERTY_QNAME[1], 0, 1);
    }

    public CTNonVisualGroupDrawingShapeProps addNewCNvGrpSpPr() {
        CTNonVisualGroupDrawingShapeProps cTNonVisualGroupDrawingShapeProps;
        synchronized (monitor()) {
            check_orphaned();
            cTNonVisualGroupDrawingShapeProps = (CTNonVisualGroupDrawingShapeProps) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTNonVisualGroupDrawingShapeProps;
    }
}
