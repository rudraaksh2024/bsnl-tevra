package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeParallel;

public class CTTLTimeNodeParallelImpl extends XmlComplexContentImpl implements CTTLTimeNodeParallel {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cTn")};
    private static final long serialVersionUID = 1;

    public CTTLTimeNodeParallelImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTLCommonTimeNodeData getCTn() {
        CTTLCommonTimeNodeData cTTLCommonTimeNodeData;
        synchronized (monitor()) {
            check_orphaned();
            cTTLCommonTimeNodeData = (CTTLCommonTimeNodeData) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTLCommonTimeNodeData == null) {
                cTTLCommonTimeNodeData = null;
            }
        }
        return cTTLCommonTimeNodeData;
    }

    public void setCTn(CTTLCommonTimeNodeData cTTLCommonTimeNodeData) {
        generatedSetterHelperImpl(cTTLCommonTimeNodeData, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTLCommonTimeNodeData addNewCTn() {
        CTTLCommonTimeNodeData cTTLCommonTimeNodeData;
        synchronized (monitor()) {
            check_orphaned();
            cTTLCommonTimeNodeData = (CTTLCommonTimeNodeData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTLCommonTimeNodeData;
    }
}
