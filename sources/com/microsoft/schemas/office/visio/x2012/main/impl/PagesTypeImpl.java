package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.PageType;
import com.microsoft.schemas.office.visio.x2012.main.PagesType;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class PagesTypeImpl extends XmlComplexContentImpl implements PagesType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Page")};
    private static final long serialVersionUID = 1;

    public PagesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<PageType> getPageList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new PagesTypeImpl$$ExternalSyntheticLambda0(this), new PagesTypeImpl$$ExternalSyntheticLambda1(this), new PagesTypeImpl$$ExternalSyntheticLambda2(this), new PagesTypeImpl$$ExternalSyntheticLambda3(this), new PagesTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public PageType[] getPageArray() {
        return (PageType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new PageType[0]);
    }

    public PageType getPageArray(int i) {
        PageType pageType;
        synchronized (monitor()) {
            check_orphaned();
            pageType = (PageType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (pageType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return pageType;
    }

    public int sizeOfPageArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setPageArray(PageType[] pageTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) pageTypeArr, PROPERTY_QNAME[0]);
    }

    public void setPageArray(int i, PageType pageType) {
        generatedSetterHelperImpl(pageType, PROPERTY_QNAME[0], i, 2);
    }

    public PageType insertNewPage(int i) {
        PageType pageType;
        synchronized (monitor()) {
            check_orphaned();
            pageType = (PageType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return pageType;
    }

    public PageType addNewPage() {
        PageType pageType;
        synchronized (monitor()) {
            check_orphaned();
            pageType = (PageType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return pageType;
    }

    public void removePage(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
