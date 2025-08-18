package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument;

public class StylesDocumentImpl extends XmlComplexContentImpl implements StylesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "styles")};
    private static final long serialVersionUID = 1;

    public StylesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTStyles getStyles() {
        CTStyles cTStyles;
        synchronized (monitor()) {
            check_orphaned();
            cTStyles = (CTStyles) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTStyles == null) {
                cTStyles = null;
            }
        }
        return cTStyles;
    }

    public void setStyles(CTStyles cTStyles) {
        generatedSetterHelperImpl(cTStyles, PROPERTY_QNAME[0], 0, 1);
    }

    public CTStyles addNewStyles() {
        CTStyles cTStyles;
        synchronized (monitor()) {
            check_orphaned();
            cTStyles = (CTStyles) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTStyles;
    }
}
