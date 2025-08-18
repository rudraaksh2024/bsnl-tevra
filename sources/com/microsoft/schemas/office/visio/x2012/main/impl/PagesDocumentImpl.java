package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.PagesDocument;
import com.microsoft.schemas.office.visio.x2012.main.PagesType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class PagesDocumentImpl extends XmlComplexContentImpl implements PagesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Pages")};
    private static final long serialVersionUID = 1;

    public PagesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public PagesType getPages() {
        PagesType pagesType;
        synchronized (monitor()) {
            check_orphaned();
            pagesType = (PagesType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (pagesType == null) {
                pagesType = null;
            }
        }
        return pagesType;
    }

    public void setPages(PagesType pagesType) {
        generatedSetterHelperImpl(pagesType, PROPERTY_QNAME[0], 0, 1);
    }

    public PagesType addNewPages() {
        PagesType pagesType;
        synchronized (monitor()) {
            check_orphaned();
            pagesType = (PagesType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return pagesType;
    }
}
