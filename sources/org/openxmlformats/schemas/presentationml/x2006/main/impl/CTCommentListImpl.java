package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList;

public class CTCommentListImpl extends XmlComplexContentImpl implements CTCommentList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cm")};
    private static final long serialVersionUID = 1;

    public CTCommentListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTComment> getCmList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTCommentListImpl$$ExternalSyntheticLambda0(this), new CTCommentListImpl$$ExternalSyntheticLambda1(this), new CTCommentListImpl$$ExternalSyntheticLambda2(this), new CTCommentListImpl$$ExternalSyntheticLambda3(this), new CTCommentListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTComment[] getCmArray() {
        return (CTComment[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTComment[0]);
    }

    public CTComment getCmArray(int i) {
        CTComment cTComment;
        synchronized (monitor()) {
            check_orphaned();
            cTComment = (CTComment) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTComment == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTComment;
    }

    public int sizeOfCmArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCmArray(CTComment[] cTCommentArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCommentArr, PROPERTY_QNAME[0]);
    }

    public void setCmArray(int i, CTComment cTComment) {
        generatedSetterHelperImpl(cTComment, PROPERTY_QNAME[0], i, 2);
    }

    public CTComment insertNewCm(int i) {
        CTComment cTComment;
        synchronized (monitor()) {
            check_orphaned();
            cTComment = (CTComment) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTComment;
    }

    public CTComment addNewCm() {
        CTComment cTComment;
        synchronized (monitor()) {
            check_orphaned();
            cTComment = (CTComment) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTComment;
    }

    public void removeCm(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
