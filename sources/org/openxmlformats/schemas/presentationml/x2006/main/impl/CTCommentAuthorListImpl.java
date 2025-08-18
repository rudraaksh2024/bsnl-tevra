package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthor;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;

public class CTCommentAuthorListImpl extends XmlComplexContentImpl implements CTCommentAuthorList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cmAuthor")};
    private static final long serialVersionUID = 1;

    public CTCommentAuthorListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCommentAuthor> getCmAuthorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTCommentAuthorListImpl$$ExternalSyntheticLambda0(this), new CTCommentAuthorListImpl$$ExternalSyntheticLambda1(this), new CTCommentAuthorListImpl$$ExternalSyntheticLambda2(this), new CTCommentAuthorListImpl$$ExternalSyntheticLambda3(this), new CTCommentAuthorListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTCommentAuthor[] getCmAuthorArray() {
        return (CTCommentAuthor[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCommentAuthor[0]);
    }

    public CTCommentAuthor getCmAuthorArray(int i) {
        CTCommentAuthor cTCommentAuthor;
        synchronized (monitor()) {
            check_orphaned();
            cTCommentAuthor = (CTCommentAuthor) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTCommentAuthor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCommentAuthor;
    }

    public int sizeOfCmAuthorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCmAuthorArray(CTCommentAuthor[] cTCommentAuthorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCommentAuthorArr, PROPERTY_QNAME[0]);
    }

    public void setCmAuthorArray(int i, CTCommentAuthor cTCommentAuthor) {
        generatedSetterHelperImpl(cTCommentAuthor, PROPERTY_QNAME[0], i, 2);
    }

    public CTCommentAuthor insertNewCmAuthor(int i) {
        CTCommentAuthor cTCommentAuthor;
        synchronized (monitor()) {
            check_orphaned();
            cTCommentAuthor = (CTCommentAuthor) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTCommentAuthor;
    }

    public CTCommentAuthor addNewCmAuthor() {
        CTCommentAuthor cTCommentAuthor;
        synchronized (monitor()) {
            check_orphaned();
            cTCommentAuthor = (CTCommentAuthor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCommentAuthor;
    }

    public void removeCmAuthor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
