package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.StyleSheetType;
import com.microsoft.schemas.office.visio.x2012.main.StyleSheetsType;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class StyleSheetsTypeImpl extends XmlComplexContentImpl implements StyleSheetsType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "StyleSheet")};
    private static final long serialVersionUID = 1;

    public StyleSheetsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<StyleSheetType> getStyleSheetList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new StyleSheetsTypeImpl$$ExternalSyntheticLambda0(this), new StyleSheetsTypeImpl$$ExternalSyntheticLambda1(this), new StyleSheetsTypeImpl$$ExternalSyntheticLambda2(this), new StyleSheetsTypeImpl$$ExternalSyntheticLambda3(this), new StyleSheetsTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public StyleSheetType[] getStyleSheetArray() {
        return (StyleSheetType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new StyleSheetType[0]);
    }

    public StyleSheetType getStyleSheetArray(int i) {
        StyleSheetType styleSheetType;
        synchronized (monitor()) {
            check_orphaned();
            styleSheetType = (StyleSheetType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (styleSheetType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return styleSheetType;
    }

    public int sizeOfStyleSheetArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setStyleSheetArray(StyleSheetType[] styleSheetTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) styleSheetTypeArr, PROPERTY_QNAME[0]);
    }

    public void setStyleSheetArray(int i, StyleSheetType styleSheetType) {
        generatedSetterHelperImpl(styleSheetType, PROPERTY_QNAME[0], i, 2);
    }

    public StyleSheetType insertNewStyleSheet(int i) {
        StyleSheetType styleSheetType;
        synchronized (monitor()) {
            check_orphaned();
            styleSheetType = (StyleSheetType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return styleSheetType;
    }

    public StyleSheetType addNewStyleSheet() {
        StyleSheetType styleSheetType;
        synchronized (monitor()) {
            check_orphaned();
            styleSheetType = (StyleSheetType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return styleSheetType;
    }

    public void removeStyleSheet(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
