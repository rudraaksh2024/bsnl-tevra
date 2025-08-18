package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ConnectsType;
import com.microsoft.schemas.office.visio.x2012.main.PageContentsType;
import com.microsoft.schemas.office.visio.x2012.main.ShapesType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class PageContentsTypeImpl extends XmlComplexContentImpl implements PageContentsType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Shapes"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Connects")};
    private static final long serialVersionUID = 1;

    public PageContentsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public ShapesType getShapes() {
        ShapesType shapesType;
        synchronized (monitor()) {
            check_orphaned();
            shapesType = (ShapesType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (shapesType == null) {
                shapesType = null;
            }
        }
        return shapesType;
    }

    public boolean isSetShapes() {
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

    public void setShapes(ShapesType shapesType) {
        generatedSetterHelperImpl(shapesType, PROPERTY_QNAME[0], 0, 1);
    }

    public ShapesType addNewShapes() {
        ShapesType shapesType;
        synchronized (monitor()) {
            check_orphaned();
            shapesType = (ShapesType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return shapesType;
    }

    public void unsetShapes() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public ConnectsType getConnects() {
        ConnectsType connectsType;
        synchronized (monitor()) {
            check_orphaned();
            connectsType = (ConnectsType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (connectsType == null) {
                connectsType = null;
            }
        }
        return connectsType;
    }

    public boolean isSetConnects() {
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

    public void setConnects(ConnectsType connectsType) {
        generatedSetterHelperImpl(connectsType, PROPERTY_QNAME[1], 0, 1);
    }

    public ConnectsType addNewConnects() {
        ConnectsType connectsType;
        synchronized (monitor()) {
            check_orphaned();
            connectsType = (ConnectsType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return connectsType;
    }

    public void unsetConnects() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
