package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComments;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument;

public class CommentsDocumentImpl extends XmlComplexContentImpl implements CommentsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "comments")};
    private static final long serialVersionUID = 1;

    public CommentsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTComments getComments() {
        CTComments cTComments;
        synchronized (monitor()) {
            check_orphaned();
            cTComments = (CTComments) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTComments == null) {
                cTComments = null;
            }
        }
        return cTComments;
    }

    public void setComments(CTComments cTComments) {
        generatedSetterHelperImpl(cTComments, PROPERTY_QNAME[0], 0, 1);
    }

    public CTComments addNewComments() {
        CTComments cTComments;
        synchronized (monitor()) {
            check_orphaned();
            cTComments = (CTComments) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTComments;
    }
}
