package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCache;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches;

public class CTPivotCachesImpl extends XmlComplexContentImpl implements CTPivotCaches {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "pivotCache")};
    private static final long serialVersionUID = 1;

    public CTPivotCachesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTPivotCache> getPivotCacheList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPivotCachesImpl$$ExternalSyntheticLambda0(this), new CTPivotCachesImpl$$ExternalSyntheticLambda1(this), new CTPivotCachesImpl$$ExternalSyntheticLambda2(this), new CTPivotCachesImpl$$ExternalSyntheticLambda3(this), new CTPivotCachesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTPivotCache[] getPivotCacheArray() {
        return (CTPivotCache[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTPivotCache[0]);
    }

    public CTPivotCache getPivotCacheArray(int i) {
        CTPivotCache cTPivotCache;
        synchronized (monitor()) {
            check_orphaned();
            cTPivotCache = (CTPivotCache) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTPivotCache == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPivotCache;
    }

    public int sizeOfPivotCacheArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setPivotCacheArray(CTPivotCache[] cTPivotCacheArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPivotCacheArr, PROPERTY_QNAME[0]);
    }

    public void setPivotCacheArray(int i, CTPivotCache cTPivotCache) {
        generatedSetterHelperImpl(cTPivotCache, PROPERTY_QNAME[0], i, 2);
    }

    public CTPivotCache insertNewPivotCache(int i) {
        CTPivotCache cTPivotCache;
        synchronized (monitor()) {
            check_orphaned();
            cTPivotCache = (CTPivotCache) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTPivotCache;
    }

    public CTPivotCache addNewPivotCache() {
        CTPivotCache cTPivotCache;
        synchronized (monitor()) {
            check_orphaned();
            cTPivotCache = (CTPivotCache) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTPivotCache;
    }

    public void removePivotCache(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
