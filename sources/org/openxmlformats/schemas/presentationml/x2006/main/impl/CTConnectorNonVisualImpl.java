package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual;

public class CTConnectorNonVisualImpl extends XmlComplexContentImpl implements CTConnectorNonVisual {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvCxnSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPr")};
    private static final long serialVersionUID = 1;

    public CTConnectorNonVisualImpl(SchemaType schemaType) {
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

    public CTNonVisualConnectorProperties getCNvCxnSpPr() {
        CTNonVisualConnectorProperties cTNonVisualConnectorProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTNonVisualConnectorProperties = (CTNonVisualConnectorProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTNonVisualConnectorProperties == null) {
                cTNonVisualConnectorProperties = null;
            }
        }
        return cTNonVisualConnectorProperties;
    }

    public void setCNvCxnSpPr(CTNonVisualConnectorProperties cTNonVisualConnectorProperties) {
        generatedSetterHelperImpl(cTNonVisualConnectorProperties, PROPERTY_QNAME[1], 0, 1);
    }

    public CTNonVisualConnectorProperties addNewCNvCxnSpPr() {
        CTNonVisualConnectorProperties cTNonVisualConnectorProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTNonVisualConnectorProperties = (CTNonVisualConnectorProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTNonVisualConnectorProperties;
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
