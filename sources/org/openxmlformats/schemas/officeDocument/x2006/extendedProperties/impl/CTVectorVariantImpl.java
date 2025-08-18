package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl;

import javax.xml.namespace.QName;
import org.apache.commons.math3.linear.ConjugateGradient;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTVectorVariant;

public class CTVectorVariantImpl extends XmlComplexContentImpl implements CTVectorVariant {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", ConjugateGradient.VECTOR)};
    private static final long serialVersionUID = 1;

    public CTVectorVariantImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTVector getVector() {
        CTVector cTVector;
        synchronized (monitor()) {
            check_orphaned();
            cTVector = (CTVector) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTVector == null) {
                cTVector = null;
            }
        }
        return cTVector;
    }

    public void setVector(CTVector cTVector) {
        generatedSetterHelperImpl(cTVector, PROPERTY_QNAME[0], 0, 1);
    }

    public CTVector addNewVector() {
        CTVector cTVector;
        synchronized (monitor()) {
            check_orphaned();
            cTVector = (CTVector) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTVector;
    }
}
