package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.SettingsDocument;

public class SettingsDocumentImpl extends XmlComplexContentImpl implements SettingsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "settings")};
    private static final long serialVersionUID = 1;

    public SettingsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSettings getSettings() {
        CTSettings cTSettings;
        synchronized (monitor()) {
            check_orphaned();
            cTSettings = (CTSettings) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSettings == null) {
                cTSettings = null;
            }
        }
        return cTSettings;
    }

    public void setSettings(CTSettings cTSettings) {
        generatedSetterHelperImpl(cTSettings, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSettings addNewSettings() {
        CTSettings cTSettings;
        synchronized (monitor()) {
            check_orphaned();
            cTSettings = (CTSettings) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSettings;
    }
}
