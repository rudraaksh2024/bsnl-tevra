package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalLink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.ExternalLinkDocument;

public class ExternalLinkDocumentImpl extends XmlComplexContentImpl implements ExternalLinkDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "externalLink")};
    private static final long serialVersionUID = 1;

    public ExternalLinkDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTExternalLink getExternalLink() {
        CTExternalLink cTExternalLink;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalLink = (CTExternalLink) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTExternalLink == null) {
                cTExternalLink = null;
            }
        }
        return cTExternalLink;
    }

    public void setExternalLink(CTExternalLink cTExternalLink) {
        generatedSetterHelperImpl(cTExternalLink, PROPERTY_QNAME[0], 0, 1);
    }

    public CTExternalLink addNewExternalLink() {
        CTExternalLink cTExternalLink;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalLink = (CTExternalLink) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTExternalLink;
    }
}
