package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.WhiteSpaceDocument;

public class WhiteSpaceDocumentImpl extends XmlComplexContentImpl implements WhiteSpaceDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "whiteSpace")};
    private static final long serialVersionUID = 1;

    public WhiteSpaceDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public WhiteSpaceDocument.WhiteSpace getWhiteSpace() {
        WhiteSpaceDocument.WhiteSpace whiteSpace;
        synchronized (monitor()) {
            check_orphaned();
            whiteSpace = (WhiteSpaceDocument.WhiteSpace) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (whiteSpace == null) {
                whiteSpace = null;
            }
        }
        return whiteSpace;
    }

    public void setWhiteSpace(WhiteSpaceDocument.WhiteSpace whiteSpace) {
        generatedSetterHelperImpl(whiteSpace, PROPERTY_QNAME[0], 0, 1);
    }

    public WhiteSpaceDocument.WhiteSpace addNewWhiteSpace() {
        WhiteSpaceDocument.WhiteSpace whiteSpace;
        synchronized (monitor()) {
            check_orphaned();
            whiteSpace = (WhiteSpaceDocument.WhiteSpace) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return whiteSpace;
    }

    public static class WhiteSpaceImpl extends FacetImpl implements WhiteSpaceDocument.WhiteSpace {
        private static final long serialVersionUID = 1;

        public WhiteSpaceImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public static class ValueImpl extends JavaStringEnumerationHolderEx implements WhiteSpaceDocument.WhiteSpace.Value {
            private static final long serialVersionUID = 1;

            public ValueImpl(SchemaType schemaType) {
                super(schemaType, false);
            }

            protected ValueImpl(SchemaType schemaType, boolean z) {
                super(schemaType, z);
            }
        }
    }
}
