package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;

public class CTCommentsImpl extends XmlComplexContentImpl implements CTComments {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "authors"), new QName(XSSFRelation.NS_SPREADSHEETML, "commentList"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTCommentsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTAuthors getAuthors() {
        CTAuthors cTAuthors;
        synchronized (monitor()) {
            check_orphaned();
            cTAuthors = (CTAuthors) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTAuthors == null) {
                cTAuthors = null;
            }
        }
        return cTAuthors;
    }

    public void setAuthors(CTAuthors cTAuthors) {
        generatedSetterHelperImpl(cTAuthors, PROPERTY_QNAME[0], 0, 1);
    }

    public CTAuthors addNewAuthors() {
        CTAuthors cTAuthors;
        synchronized (monitor()) {
            check_orphaned();
            cTAuthors = (CTAuthors) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTAuthors;
    }

    public CTCommentList getCommentList() {
        CTCommentList cTCommentList;
        synchronized (monitor()) {
            check_orphaned();
            cTCommentList = (CTCommentList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTCommentList == null) {
                cTCommentList = null;
            }
        }
        return cTCommentList;
    }

    public void setCommentList(CTCommentList cTCommentList) {
        generatedSetterHelperImpl(cTCommentList, PROPERTY_QNAME[1], 0, 1);
    }

    public CTCommentList addNewCommentList() {
        CTCommentList cTCommentList;
        synchronized (monitor()) {
            check_orphaned();
            cTCommentList = (CTCommentList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTCommentList;
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTExtensionList == null) {
                cTExtensionList = null;
            }
        }
        return cTExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[2], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
