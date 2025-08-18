package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.MasterContentsDocument;
import com.microsoft.schemas.office.visio.x2012.main.PageContentsType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class MasterContentsDocumentImpl extends XmlComplexContentImpl implements MasterContentsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "MasterContents")};
    private static final long serialVersionUID = 1;

    public MasterContentsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public PageContentsType getMasterContents() {
        PageContentsType pageContentsType;
        synchronized (monitor()) {
            check_orphaned();
            pageContentsType = (PageContentsType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (pageContentsType == null) {
                pageContentsType = null;
            }
        }
        return pageContentsType;
    }

    public void setMasterContents(PageContentsType pageContentsType) {
        generatedSetterHelperImpl(pageContentsType, PROPERTY_QNAME[0], 0, 1);
    }

    public PageContentsType addNewMasterContents() {
        PageContentsType pageContentsType;
        synchronized (monitor()) {
            check_orphaned();
            pageContentsType = (PageContentsType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return pageContentsType;
    }
}
