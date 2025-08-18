package org.openxmlformats.schemas.drawingml.x2006.picture.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.picture.PicDocument;

public class PicDocumentImpl extends XmlComplexContentImpl implements PicDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "pic")};
    private static final long serialVersionUID = 1;

    public PicDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTPicture getPic() {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTPicture = (CTPicture) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTPicture == null) {
                cTPicture = null;
            }
        }
        return cTPicture;
    }

    public void setPic(CTPicture cTPicture) {
        generatedSetterHelperImpl(cTPicture, PROPERTY_QNAME[0], 0, 1);
    }

    public CTPicture addNewPic() {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTPicture = (CTPicture) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTPicture;
    }
}
