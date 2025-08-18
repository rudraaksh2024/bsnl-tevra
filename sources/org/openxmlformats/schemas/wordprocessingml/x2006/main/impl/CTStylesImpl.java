package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLatentStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;

public class CTStylesImpl extends XmlComplexContentImpl implements CTStyles {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docDefaults"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "latentStyles"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "style")};
    private static final long serialVersionUID = 1;

    public CTStylesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTDocDefaults getDocDefaults() {
        CTDocDefaults cTDocDefaults;
        synchronized (monitor()) {
            check_orphaned();
            cTDocDefaults = (CTDocDefaults) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTDocDefaults == null) {
                cTDocDefaults = null;
            }
        }
        return cTDocDefaults;
    }

    public boolean isSetDocDefaults() {
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

    public void setDocDefaults(CTDocDefaults cTDocDefaults) {
        generatedSetterHelperImpl(cTDocDefaults, PROPERTY_QNAME[0], 0, 1);
    }

    public CTDocDefaults addNewDocDefaults() {
        CTDocDefaults cTDocDefaults;
        synchronized (monitor()) {
            check_orphaned();
            cTDocDefaults = (CTDocDefaults) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTDocDefaults;
    }

    public void unsetDocDefaults() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTLatentStyles getLatentStyles() {
        CTLatentStyles cTLatentStyles;
        synchronized (monitor()) {
            check_orphaned();
            cTLatentStyles = (CTLatentStyles) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTLatentStyles == null) {
                cTLatentStyles = null;
            }
        }
        return cTLatentStyles;
    }

    public boolean isSetLatentStyles() {
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

    public void setLatentStyles(CTLatentStyles cTLatentStyles) {
        generatedSetterHelperImpl(cTLatentStyles, PROPERTY_QNAME[1], 0, 1);
    }

    public CTLatentStyles addNewLatentStyles() {
        CTLatentStyles cTLatentStyles;
        synchronized (monitor()) {
            check_orphaned();
            cTLatentStyles = (CTLatentStyles) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTLatentStyles;
    }

    public void unsetLatentStyles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public List<CTStyle> getStyleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTStylesImpl$$ExternalSyntheticLambda0(this), new CTStylesImpl$$ExternalSyntheticLambda1(this), new CTStylesImpl$$ExternalSyntheticLambda2(this), new CTStylesImpl$$ExternalSyntheticLambda3(this), new CTStylesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTStyle[] getStyleArray() {
        return (CTStyle[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTStyle[0]);
    }

    public CTStyle getStyleArray(int i) {
        CTStyle cTStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTStyle = (CTStyle) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTStyle == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTStyle;
    }

    public int sizeOfStyleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setStyleArray(CTStyle[] cTStyleArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTStyleArr, PROPERTY_QNAME[2]);
    }

    public void setStyleArray(int i, CTStyle cTStyle) {
        generatedSetterHelperImpl(cTStyle, PROPERTY_QNAME[2], i, 2);
    }

    public CTStyle insertNewStyle(int i) {
        CTStyle cTStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTStyle = (CTStyle) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTStyle;
    }

    public CTStyle addNewStyle() {
        CTStyle cTStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTStyle = (CTStyle) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTStyle;
    }

    public void removeStyle(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }
}
