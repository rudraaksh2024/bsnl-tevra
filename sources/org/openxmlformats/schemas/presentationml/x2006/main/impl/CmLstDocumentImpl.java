package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument;

public class CmLstDocumentImpl extends XmlComplexContentImpl implements CmLstDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cmLst")};
    private static final long serialVersionUID = 1;

    public CmLstDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTCommentList getCmLst() {
        CTCommentList cTCommentList;
        synchronized (monitor()) {
            check_orphaned();
            cTCommentList = (CTCommentList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTCommentList == null) {
                cTCommentList = null;
            }
        }
        return cTCommentList;
    }

    public void setCmLst(CTCommentList cTCommentList) {
        generatedSetterHelperImpl(cTCommentList, PROPERTY_QNAME[0], 0, 1);
    }

    public CTCommentList addNewCmLst() {
        CTCommentList cTCommentList;
        synchronized (monitor()) {
            check_orphaned();
            cTCommentList = (CTCommentList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCommentList;
    }
}
