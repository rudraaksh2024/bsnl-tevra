package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;

public class TotalDigitsDocumentImpl extends XmlComplexContentImpl implements TotalDigitsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "totalDigits")};
    private static final long serialVersionUID = 1;

    public TotalDigitsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public TotalDigitsDocument.TotalDigits getTotalDigits() {
        TotalDigitsDocument.TotalDigits totalDigits;
        synchronized (monitor()) {
            check_orphaned();
            totalDigits = (TotalDigitsDocument.TotalDigits) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (totalDigits == null) {
                totalDigits = null;
            }
        }
        return totalDigits;
    }

    public void setTotalDigits(TotalDigitsDocument.TotalDigits totalDigits) {
        generatedSetterHelperImpl(totalDigits, PROPERTY_QNAME[0], 0, 1);
    }

    public TotalDigitsDocument.TotalDigits addNewTotalDigits() {
        TotalDigitsDocument.TotalDigits totalDigits;
        synchronized (monitor()) {
            check_orphaned();
            totalDigits = (TotalDigitsDocument.TotalDigits) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return totalDigits;
    }

    public static class TotalDigitsImpl extends NumFacetImpl implements TotalDigitsDocument.TotalDigits {
        private static final long serialVersionUID = 1;

        public TotalDigitsImpl(SchemaType schemaType) {
            super(schemaType);
        }
    }
}
