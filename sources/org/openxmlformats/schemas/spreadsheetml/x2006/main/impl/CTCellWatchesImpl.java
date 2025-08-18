package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellWatch;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellWatches;

public class CTCellWatchesImpl extends XmlComplexContentImpl implements CTCellWatches {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "cellWatch")};
    private static final long serialVersionUID = 1;

    public CTCellWatchesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCellWatch> getCellWatchList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTCellWatchesImpl$$ExternalSyntheticLambda0(this), new CTCellWatchesImpl$$ExternalSyntheticLambda1(this), new CTCellWatchesImpl$$ExternalSyntheticLambda2(this), new CTCellWatchesImpl$$ExternalSyntheticLambda3(this), new CTCellWatchesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTCellWatch[] getCellWatchArray() {
        return (CTCellWatch[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCellWatch[0]);
    }

    public CTCellWatch getCellWatchArray(int i) {
        CTCellWatch cTCellWatch;
        synchronized (monitor()) {
            check_orphaned();
            cTCellWatch = (CTCellWatch) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTCellWatch == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCellWatch;
    }

    public int sizeOfCellWatchArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCellWatchArray(CTCellWatch[] cTCellWatchArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCellWatchArr, PROPERTY_QNAME[0]);
    }

    public void setCellWatchArray(int i, CTCellWatch cTCellWatch) {
        generatedSetterHelperImpl(cTCellWatch, PROPERTY_QNAME[0], i, 2);
    }

    public CTCellWatch insertNewCellWatch(int i) {
        CTCellWatch cTCellWatch;
        synchronized (monitor()) {
            check_orphaned();
            cTCellWatch = (CTCellWatch) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTCellWatch;
    }

    public CTCellWatch addNewCellWatch() {
        CTCellWatch cTCellWatch;
        synchronized (monitor()) {
            check_orphaned();
            cTCellWatch = (CTCellWatch) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCellWatch;
    }

    public void removeCellWatch(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
