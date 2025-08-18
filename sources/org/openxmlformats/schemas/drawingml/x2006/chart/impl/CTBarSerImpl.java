package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTrendline;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public class CTBarSerImpl extends XmlComplexContentImpl implements CTBarSer {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "idx"), new QName(XSSFRelation.NS_CHART, "order"), new QName(XSSFRelation.NS_CHART, "tx"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "invertIfNegative"), new QName(XSSFRelation.NS_CHART, "pictureOptions"), new QName(XSSFRelation.NS_CHART, "dPt"), new QName(XSSFRelation.NS_CHART, "dLbls"), new QName(XSSFRelation.NS_CHART, "trendline"), new QName(XSSFRelation.NS_CHART, "errBars"), new QName(XSSFRelation.NS_CHART, "cat"), new QName(XSSFRelation.NS_CHART, "val"), new QName(XSSFRelation.NS_CHART, "shape"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTBarSerImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTUnsignedInt getIdx() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTUnsignedInt == null) {
                cTUnsignedInt = null;
            }
        }
        return cTUnsignedInt;
    }

    public void setIdx(CTUnsignedInt cTUnsignedInt) {
        generatedSetterHelperImpl(cTUnsignedInt, PROPERTY_QNAME[0], 0, 1);
    }

    public CTUnsignedInt addNewIdx() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTUnsignedInt;
    }

    public CTUnsignedInt getOrder() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTUnsignedInt == null) {
                cTUnsignedInt = null;
            }
        }
        return cTUnsignedInt;
    }

    public void setOrder(CTUnsignedInt cTUnsignedInt) {
        generatedSetterHelperImpl(cTUnsignedInt, PROPERTY_QNAME[1], 0, 1);
    }

    public CTUnsignedInt addNewOrder() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTUnsignedInt;
    }

    public CTSerTx getTx() {
        CTSerTx cTSerTx;
        synchronized (monitor()) {
            check_orphaned();
            cTSerTx = (CTSerTx) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTSerTx == null) {
                cTSerTx = null;
            }
        }
        return cTSerTx;
    }

    public boolean isSetTx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setTx(CTSerTx cTSerTx) {
        generatedSetterHelperImpl(cTSerTx, PROPERTY_QNAME[2], 0, 1);
    }

    public CTSerTx addNewTx() {
        CTSerTx cTSerTx;
        synchronized (monitor()) {
            check_orphaned();
            cTSerTx = (CTSerTx) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTSerTx;
    }

    public void unsetTx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTShapeProperties == null) {
                cTShapeProperties = null;
            }
        }
        return cTShapeProperties;
    }

    public boolean isSetSpPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setSpPr(CTShapeProperties cTShapeProperties) {
        generatedSetterHelperImpl(cTShapeProperties, PROPERTY_QNAME[3], 0, 1);
    }

    public CTShapeProperties addNewSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTShapeProperties;
    }

    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTBoolean getInvertIfNegative() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetInvertIfNegative() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setInvertIfNegative(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[4], 0, 1);
    }

    public CTBoolean addNewInvertIfNegative() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTBoolean;
    }

    public void unsetInvertIfNegative() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTPictureOptions getPictureOptions() {
        CTPictureOptions find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetPictureOptions() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setPictureOptions(CTPictureOptions cTPictureOptions) {
        generatedSetterHelperImpl(cTPictureOptions, PROPERTY_QNAME[5], 0, 1);
    }

    public CTPictureOptions addNewPictureOptions() {
        CTPictureOptions add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return add_element_user;
    }

    public void unsetPictureOptions() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public List<CTDPt> getDPtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBarSerImpl$$ExternalSyntheticLambda5(this), new CTBarSerImpl$$ExternalSyntheticLambda6(this), new CTBarSerImpl$$ExternalSyntheticLambda7(this), new CTBarSerImpl$$ExternalSyntheticLambda8(this), new CTBarSerImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTDPt[] getDPtArray() {
        return (CTDPt[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTDPt[0]);
    }

    public CTDPt getDPtArray(int i) {
        CTDPt cTDPt;
        synchronized (monitor()) {
            check_orphaned();
            cTDPt = (CTDPt) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (cTDPt == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDPt;
    }

    public int sizeOfDPtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setDPtArray(CTDPt[] cTDPtArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDPtArr, PROPERTY_QNAME[6]);
    }

    public void setDPtArray(int i, CTDPt cTDPt) {
        generatedSetterHelperImpl(cTDPt, PROPERTY_QNAME[6], i, 2);
    }

    public CTDPt insertNewDPt(int i) {
        CTDPt cTDPt;
        synchronized (monitor()) {
            check_orphaned();
            cTDPt = (CTDPt) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTDPt;
    }

    public CTDPt addNewDPt() {
        CTDPt cTDPt;
        synchronized (monitor()) {
            check_orphaned();
            cTDPt = (CTDPt) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTDPt;
    }

    public void removeDPt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public CTDLbls getDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            cTDLbls = (CTDLbls) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTDLbls == null) {
                cTDLbls = null;
            }
        }
        return cTDLbls;
    }

    public boolean isSetDLbls() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setDLbls(CTDLbls cTDLbls) {
        generatedSetterHelperImpl(cTDLbls, PROPERTY_QNAME[7], 0, 1);
    }

    public CTDLbls addNewDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            cTDLbls = (CTDLbls) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTDLbls;
    }

    public void unsetDLbls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public List<CTTrendline> getTrendlineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBarSerImpl$$ExternalSyntheticLambda0(this), new CTBarSerImpl$$ExternalSyntheticLambda1(this), new CTBarSerImpl$$ExternalSyntheticLambda2(this), new CTBarSerImpl$$ExternalSyntheticLambda3(this), new CTBarSerImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTTrendline[] getTrendlineArray() {
        return getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTTrendline[0]);
    }

    public CTTrendline getTrendlineArray(int i) {
        CTTrendline find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfTrendlineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setTrendlineArray(CTTrendline[] cTTrendlineArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrendlineArr, PROPERTY_QNAME[8]);
    }

    public void setTrendlineArray(int i, CTTrendline cTTrendline) {
        generatedSetterHelperImpl(cTTrendline, PROPERTY_QNAME[8], i, 2);
    }

    public CTTrendline insertNewTrendline(int i) {
        CTTrendline insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return insert_element_user;
    }

    public CTTrendline addNewTrendline() {
        CTTrendline add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return add_element_user;
    }

    public void removeTrendline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public CTErrBars getErrBars() {
        CTErrBars cTErrBars;
        synchronized (monitor()) {
            check_orphaned();
            cTErrBars = (CTErrBars) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTErrBars == null) {
                cTErrBars = null;
            }
        }
        return cTErrBars;
    }

    public boolean isSetErrBars() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setErrBars(CTErrBars cTErrBars) {
        generatedSetterHelperImpl(cTErrBars, PROPERTY_QNAME[9], 0, 1);
    }

    public CTErrBars addNewErrBars() {
        CTErrBars cTErrBars;
        synchronized (monitor()) {
            check_orphaned();
            cTErrBars = (CTErrBars) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTErrBars;
    }

    public void unsetErrBars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTAxDataSource getCat() {
        CTAxDataSource cTAxDataSource;
        synchronized (monitor()) {
            check_orphaned();
            cTAxDataSource = (CTAxDataSource) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTAxDataSource == null) {
                cTAxDataSource = null;
            }
        }
        return cTAxDataSource;
    }

    public boolean isSetCat() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setCat(CTAxDataSource cTAxDataSource) {
        generatedSetterHelperImpl(cTAxDataSource, PROPERTY_QNAME[10], 0, 1);
    }

    public CTAxDataSource addNewCat() {
        CTAxDataSource cTAxDataSource;
        synchronized (monitor()) {
            check_orphaned();
            cTAxDataSource = (CTAxDataSource) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTAxDataSource;
    }

    public void unsetCat() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTNumDataSource getVal() {
        CTNumDataSource cTNumDataSource;
        synchronized (monitor()) {
            check_orphaned();
            cTNumDataSource = (CTNumDataSource) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTNumDataSource == null) {
                cTNumDataSource = null;
            }
        }
        return cTNumDataSource;
    }

    public boolean isSetVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setVal(CTNumDataSource cTNumDataSource) {
        generatedSetterHelperImpl(cTNumDataSource, PROPERTY_QNAME[11], 0, 1);
    }

    public CTNumDataSource addNewVal() {
        CTNumDataSource cTNumDataSource;
        synchronized (monitor()) {
            check_orphaned();
            cTNumDataSource = (CTNumDataSource) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTNumDataSource;
    }

    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTShape getShape() {
        CTShape cTShape;
        synchronized (monitor()) {
            check_orphaned();
            cTShape = (CTShape) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTShape == null) {
                cTShape = null;
            }
        }
        return cTShape;
    }

    public boolean isSetShape() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setShape(CTShape cTShape) {
        generatedSetterHelperImpl(cTShape, PROPERTY_QNAME[12], 0, 1);
    }

    public CTShape addNewShape() {
        CTShape cTShape;
        synchronized (monitor()) {
            check_orphaned();
            cTShape = (CTShape) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTShape;
    }

    public void unsetShape() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[13], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[13], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }
}
