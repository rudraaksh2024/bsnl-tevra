package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument;

public class CmAuthorLstDocumentImpl extends XmlComplexContentImpl implements CmAuthorLstDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cmAuthorLst")};
    private static final long serialVersionUID = 1;

    public CmAuthorLstDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTCommentAuthorList getCmAuthorLst() {
        CTCommentAuthorList cTCommentAuthorList;
        synchronized (monitor()) {
            check_orphaned();
            cTCommentAuthorList = (CTCommentAuthorList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTCommentAuthorList == null) {
                cTCommentAuthorList = null;
            }
        }
        return cTCommentAuthorList;
    }

    public void setCmAuthorLst(CTCommentAuthorList cTCommentAuthorList) {
        generatedSetterHelperImpl(cTCommentAuthorList, PROPERTY_QNAME[0], 0, 1);
    }

    public CTCommentAuthorList addNewCmAuthorLst() {
        CTCommentAuthorList cTCommentAuthorList;
        synchronized (monitor()) {
            check_orphaned();
            cTCommentAuthorList = (CTCommentAuthorList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCommentAuthorList;
    }
}
