package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBandFmts;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTSurface3DChartImpl extends XmlComplexContentImpl implements CTSurface3DChart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "wireframe"), new QName(XSSFRelation.NS_CHART, "ser"), new QName(XSSFRelation.NS_CHART, "bandFmts"), new QName(XSSFRelation.NS_CHART, "axId"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTSurface3DChartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTBoolean getWireframe() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetWireframe() {
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

    public void setWireframe(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[0], 0, 1);
    }

    public CTBoolean addNewWireframe() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTBoolean;
    }

    public void unsetWireframe() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTSurfaceSer> getSerList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSurface3DChartImpl$$ExternalSyntheticLambda5(this), new CTSurface3DChartImpl$$ExternalSyntheticLambda6(this), new CTSurface3DChartImpl$$ExternalSyntheticLambda7(this), new CTSurface3DChartImpl$$ExternalSyntheticLambda8(this), new CTSurface3DChartImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTSurfaceSer[] getSerArray() {
        return (CTSurfaceSer[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTSurfaceSer[0]);
    }

    public CTSurfaceSer getSerArray(int i) {
        CTSurfaceSer cTSurfaceSer;
        synchronized (monitor()) {
            check_orphaned();
            cTSurfaceSer = (CTSurfaceSer) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTSurfaceSer == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSurfaceSer;
    }

    public int sizeOfSerArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setSerArray(CTSurfaceSer[] cTSurfaceSerArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSurfaceSerArr, PROPERTY_QNAME[1]);
    }

    public void setSerArray(int i, CTSurfaceSer cTSurfaceSer) {
        generatedSetterHelperImpl(cTSurfaceSer, PROPERTY_QNAME[1], i, 2);
    }

    public CTSurfaceSer insertNewSer(int i) {
        CTSurfaceSer cTSurfaceSer;
        synchronized (monitor()) {
            check_orphaned();
            cTSurfaceSer = (CTSurfaceSer) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTSurfaceSer;
    }

    public CTSurfaceSer addNewSer() {
        CTSurfaceSer cTSurfaceSer;
        synchronized (monitor()) {
            check_orphaned();
            cTSurfaceSer = (CTSurfaceSer) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTSurfaceSer;
    }

    public void removeSer(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public CTBandFmts getBandFmts() {
        CTBandFmts find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetBandFmts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setBandFmts(CTBandFmts cTBandFmts) {
        generatedSetterHelperImpl(cTBandFmts, PROPERTY_QNAME[2], 0, 1);
    }

    public CTBandFmts addNewBandFmts() {
        CTBandFmts add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetBandFmts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public List<CTUnsignedInt> getAxIdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSurface3DChartImpl$$ExternalSyntheticLambda0(this), new CTSurface3DChartImpl$$ExternalSyntheticLambda1(this), new CTSurface3DChartImpl$$ExternalSyntheticLambda2(this), new CTSurface3DChartImpl$$ExternalSyntheticLambda3(this), new CTSurface3DChartImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTUnsignedInt[] getAxIdArray() {
        return (CTUnsignedInt[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTUnsignedInt[0]);
    }

    public CTUnsignedInt getAxIdArray(int i) {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTUnsignedInt == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTUnsignedInt;
    }

    public int sizeOfAxIdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTUnsignedIntArr, PROPERTY_QNAME[3]);
    }

    public void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt) {
        generatedSetterHelperImpl(cTUnsignedInt, PROPERTY_QNAME[3], i, 2);
    }

    public CTUnsignedInt insertNewAxId(int i) {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTUnsignedInt;
    }

    public CTUnsignedInt addNewAxId() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTUnsignedInt;
    }

    public void removeAxId(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[4], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[4], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
