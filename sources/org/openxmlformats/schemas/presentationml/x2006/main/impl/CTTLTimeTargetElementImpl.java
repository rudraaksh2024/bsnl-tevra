package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLSubShapeId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement;

public class CTTLTimeTargetElementImpl extends XmlComplexContentImpl implements CTTLTimeTargetElement {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldTgt"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sndTgt"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "spTgt"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "inkTgt")};
    private static final long serialVersionUID = 1;

    public CTTLTimeTargetElementImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTEmpty getSldTgt() {
        CTEmpty find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetSldTgt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setSldTgt(CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[0], 0, 1);
    }

    public CTEmpty addNewSldTgt() {
        CTEmpty add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetSldTgt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTEmbeddedWAVAudioFile getSndTgt() {
        CTEmbeddedWAVAudioFile find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetSndTgt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setSndTgt(CTEmbeddedWAVAudioFile cTEmbeddedWAVAudioFile) {
        generatedSetterHelperImpl(cTEmbeddedWAVAudioFile, PROPERTY_QNAME[1], 0, 1);
    }

    public CTEmbeddedWAVAudioFile addNewSndTgt() {
        CTEmbeddedWAVAudioFile add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void unsetSndTgt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTTLShapeTargetElement getSpTgt() {
        CTTLShapeTargetElement cTTLShapeTargetElement;
        synchronized (monitor()) {
            check_orphaned();
            cTTLShapeTargetElement = (CTTLShapeTargetElement) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTTLShapeTargetElement == null) {
                cTTLShapeTargetElement = null;
            }
        }
        return cTTLShapeTargetElement;
    }

    public boolean isSetSpTgt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setSpTgt(CTTLShapeTargetElement cTTLShapeTargetElement) {
        generatedSetterHelperImpl(cTTLShapeTargetElement, PROPERTY_QNAME[2], 0, 1);
    }

    public CTTLShapeTargetElement addNewSpTgt() {
        CTTLShapeTargetElement cTTLShapeTargetElement;
        synchronized (monitor()) {
            check_orphaned();
            cTTLShapeTargetElement = (CTTLShapeTargetElement) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTTLShapeTargetElement;
    }

    public void unsetSpTgt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTTLSubShapeId getInkTgt() {
        CTTLSubShapeId find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetInkTgt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setInkTgt(CTTLSubShapeId cTTLSubShapeId) {
        generatedSetterHelperImpl(cTTLSubShapeId, PROPERTY_QNAME[3], 0, 1);
    }

    public CTTLSubShapeId addNewInkTgt() {
        CTTLSubShapeId add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void unsetInkTgt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
