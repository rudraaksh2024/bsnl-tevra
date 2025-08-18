package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DClose;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;

public class CTPath2DImpl extends XmlComplexContentImpl implements CTPath2D {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "close"), new QName(XSSFRelation.NS_DRAWINGML, "moveTo"), new QName(XSSFRelation.NS_DRAWINGML, "lnTo"), new QName(XSSFRelation.NS_DRAWINGML, "arcTo"), new QName(XSSFRelation.NS_DRAWINGML, "quadBezTo"), new QName(XSSFRelation.NS_DRAWINGML, "cubicBezTo"), new QName("", "w"), new QName("", "h"), new QName("", "fill"), new QName("", "stroke"), new QName("", "extrusionOk")};
    private static final long serialVersionUID = 1;

    public CTPath2DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTPath2DClose> getCloseList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPath2DImpl$$ExternalSyntheticLambda17(this), new CTPath2DImpl$$ExternalSyntheticLambda18(this), new CTPath2DImpl$$ExternalSyntheticLambda19(this), new CTPath2DImpl$$ExternalSyntheticLambda20(this), new CTPath2DImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTPath2DClose[] getCloseArray() {
        return (CTPath2DClose[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTPath2DClose[0]);
    }

    public CTPath2DClose getCloseArray(int i) {
        CTPath2DClose cTPath2DClose;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DClose = (CTPath2DClose) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTPath2DClose == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPath2DClose;
    }

    public int sizeOfCloseArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCloseArray(CTPath2DClose[] cTPath2DCloseArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPath2DCloseArr, PROPERTY_QNAME[0]);
    }

    public void setCloseArray(int i, CTPath2DClose cTPath2DClose) {
        generatedSetterHelperImpl(cTPath2DClose, PROPERTY_QNAME[0], i, 2);
    }

    public CTPath2DClose insertNewClose(int i) {
        CTPath2DClose cTPath2DClose;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DClose = (CTPath2DClose) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTPath2DClose;
    }

    public CTPath2DClose addNewClose() {
        CTPath2DClose cTPath2DClose;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DClose = (CTPath2DClose) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTPath2DClose;
    }

    public void removeClose(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTPath2DMoveTo> getMoveToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPath2DImpl$$ExternalSyntheticLambda1(this), new CTPath2DImpl$$ExternalSyntheticLambda2(this), new CTPath2DImpl$$ExternalSyntheticLambda3(this), new CTPath2DImpl$$ExternalSyntheticLambda4(this), new CTPath2DImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTPath2DMoveTo[] getMoveToArray() {
        return (CTPath2DMoveTo[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTPath2DMoveTo[0]);
    }

    public CTPath2DMoveTo getMoveToArray(int i) {
        CTPath2DMoveTo cTPath2DMoveTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DMoveTo = (CTPath2DMoveTo) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTPath2DMoveTo == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPath2DMoveTo;
    }

    public int sizeOfMoveToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setMoveToArray(CTPath2DMoveTo[] cTPath2DMoveToArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPath2DMoveToArr, PROPERTY_QNAME[1]);
    }

    public void setMoveToArray(int i, CTPath2DMoveTo cTPath2DMoveTo) {
        generatedSetterHelperImpl(cTPath2DMoveTo, PROPERTY_QNAME[1], i, 2);
    }

    public CTPath2DMoveTo insertNewMoveTo(int i) {
        CTPath2DMoveTo cTPath2DMoveTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DMoveTo = (CTPath2DMoveTo) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTPath2DMoveTo;
    }

    public CTPath2DMoveTo addNewMoveTo() {
        CTPath2DMoveTo cTPath2DMoveTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DMoveTo = (CTPath2DMoveTo) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTPath2DMoveTo;
    }

    public void removeMoveTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTPath2DLineTo> getLnToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPath2DImpl$$ExternalSyntheticLambda6(this), new CTPath2DImpl$$ExternalSyntheticLambda7(this), new CTPath2DImpl$$ExternalSyntheticLambda8(this), new CTPath2DImpl$$ExternalSyntheticLambda9(this), new CTPath2DImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTPath2DLineTo[] getLnToArray() {
        return (CTPath2DLineTo[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTPath2DLineTo[0]);
    }

    public CTPath2DLineTo getLnToArray(int i) {
        CTPath2DLineTo cTPath2DLineTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DLineTo = (CTPath2DLineTo) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTPath2DLineTo == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPath2DLineTo;
    }

    public int sizeOfLnToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setLnToArray(CTPath2DLineTo[] cTPath2DLineToArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPath2DLineToArr, PROPERTY_QNAME[2]);
    }

    public void setLnToArray(int i, CTPath2DLineTo cTPath2DLineTo) {
        generatedSetterHelperImpl(cTPath2DLineTo, PROPERTY_QNAME[2], i, 2);
    }

    public CTPath2DLineTo insertNewLnTo(int i) {
        CTPath2DLineTo cTPath2DLineTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DLineTo = (CTPath2DLineTo) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTPath2DLineTo;
    }

    public CTPath2DLineTo addNewLnTo() {
        CTPath2DLineTo cTPath2DLineTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DLineTo = (CTPath2DLineTo) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTPath2DLineTo;
    }

    public void removeLnTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTPath2DArcTo> getArcToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPath2DImpl$$ExternalSyntheticLambda25(this), new CTPath2DImpl$$ExternalSyntheticLambda26(this), new CTPath2DImpl$$ExternalSyntheticLambda27(this), new CTPath2DImpl$$ExternalSyntheticLambda28(this), new CTPath2DImpl$$ExternalSyntheticLambda29(this));
        }
        return javaListXmlObject;
    }

    public CTPath2DArcTo[] getArcToArray() {
        return (CTPath2DArcTo[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTPath2DArcTo[0]);
    }

    public CTPath2DArcTo getArcToArray(int i) {
        CTPath2DArcTo cTPath2DArcTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DArcTo = (CTPath2DArcTo) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTPath2DArcTo == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPath2DArcTo;
    }

    public int sizeOfArcToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setArcToArray(CTPath2DArcTo[] cTPath2DArcToArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPath2DArcToArr, PROPERTY_QNAME[3]);
    }

    public void setArcToArray(int i, CTPath2DArcTo cTPath2DArcTo) {
        generatedSetterHelperImpl(cTPath2DArcTo, PROPERTY_QNAME[3], i, 2);
    }

    public CTPath2DArcTo insertNewArcTo(int i) {
        CTPath2DArcTo cTPath2DArcTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DArcTo = (CTPath2DArcTo) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTPath2DArcTo;
    }

    public CTPath2DArcTo addNewArcTo() {
        CTPath2DArcTo cTPath2DArcTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DArcTo = (CTPath2DArcTo) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTPath2DArcTo;
    }

    public void removeArcTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTPath2DQuadBezierTo> getQuadBezToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPath2DImpl$$ExternalSyntheticLambda12(this), new CTPath2DImpl$$ExternalSyntheticLambda13(this), new CTPath2DImpl$$ExternalSyntheticLambda14(this), new CTPath2DImpl$$ExternalSyntheticLambda15(this), new CTPath2DImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTPath2DQuadBezierTo[] getQuadBezToArray() {
        return (CTPath2DQuadBezierTo[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTPath2DQuadBezierTo[0]);
    }

    public CTPath2DQuadBezierTo getQuadBezToArray(int i) {
        CTPath2DQuadBezierTo cTPath2DQuadBezierTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DQuadBezierTo = (CTPath2DQuadBezierTo) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTPath2DQuadBezierTo == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPath2DQuadBezierTo;
    }

    public int sizeOfQuadBezToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setQuadBezToArray(CTPath2DQuadBezierTo[] cTPath2DQuadBezierToArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPath2DQuadBezierToArr, PROPERTY_QNAME[4]);
    }

    public void setQuadBezToArray(int i, CTPath2DQuadBezierTo cTPath2DQuadBezierTo) {
        generatedSetterHelperImpl(cTPath2DQuadBezierTo, PROPERTY_QNAME[4], i, 2);
    }

    public CTPath2DQuadBezierTo insertNewQuadBezTo(int i) {
        CTPath2DQuadBezierTo cTPath2DQuadBezierTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DQuadBezierTo = (CTPath2DQuadBezierTo) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTPath2DQuadBezierTo;
    }

    public CTPath2DQuadBezierTo addNewQuadBezTo() {
        CTPath2DQuadBezierTo cTPath2DQuadBezierTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DQuadBezierTo = (CTPath2DQuadBezierTo) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTPath2DQuadBezierTo;
    }

    public void removeQuadBezTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTPath2DCubicBezierTo> getCubicBezToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPath2DImpl$$ExternalSyntheticLambda0(this), new CTPath2DImpl$$ExternalSyntheticLambda11(this), new CTPath2DImpl$$ExternalSyntheticLambda22(this), new CTPath2DImpl$$ExternalSyntheticLambda23(this), new CTPath2DImpl$$ExternalSyntheticLambda24(this));
        }
        return javaListXmlObject;
    }

    public CTPath2DCubicBezierTo[] getCubicBezToArray() {
        return (CTPath2DCubicBezierTo[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTPath2DCubicBezierTo[0]);
    }

    public CTPath2DCubicBezierTo getCubicBezToArray(int i) {
        CTPath2DCubicBezierTo cTPath2DCubicBezierTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DCubicBezierTo = (CTPath2DCubicBezierTo) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTPath2DCubicBezierTo == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPath2DCubicBezierTo;
    }

    public int sizeOfCubicBezToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setCubicBezToArray(CTPath2DCubicBezierTo[] cTPath2DCubicBezierToArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPath2DCubicBezierToArr, PROPERTY_QNAME[5]);
    }

    public void setCubicBezToArray(int i, CTPath2DCubicBezierTo cTPath2DCubicBezierTo) {
        generatedSetterHelperImpl(cTPath2DCubicBezierTo, PROPERTY_QNAME[5], i, 2);
    }

    public CTPath2DCubicBezierTo insertNewCubicBezTo(int i) {
        CTPath2DCubicBezierTo cTPath2DCubicBezierTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DCubicBezierTo = (CTPath2DCubicBezierTo) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTPath2DCubicBezierTo;
    }

    public CTPath2DCubicBezierTo addNewCubicBezTo() {
        CTPath2DCubicBezierTo cTPath2DCubicBezierTo;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DCubicBezierTo = (CTPath2DCubicBezierTo) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTPath2DCubicBezierTo;
    }

    public void removeCubicBezTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getW() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 6
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002d }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0022:
            if (r1 != 0) goto L_0x0027
            r1 = 0
            goto L_0x002b
        L_0x0027:
            long r1 = r1.getLongValue()     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r1
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.getW():long");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate xgetW() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0024 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0024 }
            r3 = 6
            r4 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0024 }
            org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate) r1     // Catch:{ all -> 0x0024 }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0024 }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate) r1     // Catch:{ all -> 0x0024 }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return r1
        L_0x0024:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.xgetW():org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate");
    }

    public boolean isSetW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setW(long r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 6
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.setLongValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.setW(long):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetW(org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 6
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.xsetW(org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate):void");
    }

    public void unsetW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getH() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 7
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002d }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0022:
            if (r1 != 0) goto L_0x0027
            r1 = 0
            goto L_0x002b
        L_0x0027:
            long r1 = r1.getLongValue()     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r1
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.getH():long");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate xgetH() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0024 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0024 }
            r3 = 7
            r4 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0024 }
            org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate) r1     // Catch:{ all -> 0x0024 }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0024 }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate) r1     // Catch:{ all -> 0x0024 }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return r1
        L_0x0024:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.xgetH():org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate");
    }

    public boolean isSetH() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setH(long r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 7
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.setLongValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.setH(long):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetH(org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 7
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.xsetH(org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate):void");
    }

    public void unsetH() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    /* JADX WARNING: type inference failed for: r5v6, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode.Enum getFill() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002f }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002f }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002f }
            r3 = 8
            r4 = r2[r3]     // Catch:{ all -> 0x002f }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002f }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002f }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x002f }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002f }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002f }
        L_0x0023:
            if (r1 != 0) goto L_0x0027
            r5 = 0
            goto L_0x002d
        L_0x0027:
            org.apache.xmlbeans.StringEnumAbstractBase r5 = r1.getEnumValue()     // Catch:{ all -> 0x002f }
            org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode$Enum r5 = (org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode.Enum) r5     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r5
        L_0x002f:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.getFill():org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode$Enum");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode xgetFill() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
            r3 = 8
            r4 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
            org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode) r1     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode) r1     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0025:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.xgetFill():org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode");
    }

    public boolean isSetFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setFill(org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode.Enum r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 8
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setEnumValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.setFill(org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode$Enum):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetFill(org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 8
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.xsetFill(org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode):void");
    }

    public void unsetFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getStroke() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 9
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002d }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0023:
            if (r1 != 0) goto L_0x0027
            r5 = 0
            goto L_0x002b
        L_0x0027:
            boolean r5 = r1.getBooleanValue()     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r5
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.getStroke():boolean");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.XmlBoolean xgetStroke() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
            r3 = 9
            r4 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0025:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.xgetStroke():org.apache.xmlbeans.XmlBoolean");
    }

    public boolean isSetStroke() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStroke(boolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 9
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setBooleanValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.setStroke(boolean):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetStroke(org.apache.xmlbeans.XmlBoolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 9
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.xsetStroke(org.apache.xmlbeans.XmlBoolean):void");
    }

    public void unsetStroke() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getExtrusionOk() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 10
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002d }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0023:
            if (r1 != 0) goto L_0x0027
            r5 = 0
            goto L_0x002b
        L_0x0027:
            boolean r5 = r1.getBooleanValue()     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r5
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.getExtrusionOk():boolean");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.XmlBoolean xgetExtrusionOk() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
            r3 = 10
            r4 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0025:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.xgetExtrusionOk():org.apache.xmlbeans.XmlBoolean");
    }

    public boolean isSetExtrusionOk() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setExtrusionOk(boolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 10
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setBooleanValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.setExtrusionOk(boolean):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetExtrusionOk(org.apache.xmlbeans.XmlBoolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 10
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTPath2DImpl.xsetExtrusionOk(org.apache.xmlbeans.XmlBoolean):void");
    }

    public void unsetExtrusionOk() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }
}
