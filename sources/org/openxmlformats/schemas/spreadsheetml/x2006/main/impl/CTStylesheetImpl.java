package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorders;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyles;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFills;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFonts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyles;

public class CTStylesheetImpl extends XmlComplexContentImpl implements CTStylesheet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "numFmts"), new QName(XSSFRelation.NS_SPREADSHEETML, "fonts"), new QName(XSSFRelation.NS_SPREADSHEETML, "fills"), new QName(XSSFRelation.NS_SPREADSHEETML, "borders"), new QName(XSSFRelation.NS_SPREADSHEETML, "cellStyleXfs"), new QName(XSSFRelation.NS_SPREADSHEETML, "cellXfs"), new QName(XSSFRelation.NS_SPREADSHEETML, "cellStyles"), new QName(XSSFRelation.NS_SPREADSHEETML, "dxfs"), new QName(XSSFRelation.NS_SPREADSHEETML, "tableStyles"), new QName(XSSFRelation.NS_SPREADSHEETML, "colors"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTStylesheetImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTNumFmts getNumFmts() {
        CTNumFmts cTNumFmts;
        synchronized (monitor()) {
            check_orphaned();
            cTNumFmts = (CTNumFmts) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTNumFmts == null) {
                cTNumFmts = null;
            }
        }
        return cTNumFmts;
    }

    public boolean isSetNumFmts() {
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

    public void setNumFmts(CTNumFmts cTNumFmts) {
        generatedSetterHelperImpl(cTNumFmts, PROPERTY_QNAME[0], 0, 1);
    }

    public CTNumFmts addNewNumFmts() {
        CTNumFmts cTNumFmts;
        synchronized (monitor()) {
            check_orphaned();
            cTNumFmts = (CTNumFmts) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTNumFmts;
    }

    public void unsetNumFmts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTFonts getFonts() {
        CTFonts cTFonts;
        synchronized (monitor()) {
            check_orphaned();
            cTFonts = (CTFonts) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTFonts == null) {
                cTFonts = null;
            }
        }
        return cTFonts;
    }

    public boolean isSetFonts() {
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

    public void setFonts(CTFonts cTFonts) {
        generatedSetterHelperImpl(cTFonts, PROPERTY_QNAME[1], 0, 1);
    }

    public CTFonts addNewFonts() {
        CTFonts cTFonts;
        synchronized (monitor()) {
            check_orphaned();
            cTFonts = (CTFonts) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTFonts;
    }

    public void unsetFonts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTFills getFills() {
        CTFills cTFills;
        synchronized (monitor()) {
            check_orphaned();
            cTFills = (CTFills) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTFills == null) {
                cTFills = null;
            }
        }
        return cTFills;
    }

    public boolean isSetFills() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setFills(CTFills cTFills) {
        generatedSetterHelperImpl(cTFills, PROPERTY_QNAME[2], 0, 1);
    }

    public CTFills addNewFills() {
        CTFills cTFills;
        synchronized (monitor()) {
            check_orphaned();
            cTFills = (CTFills) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTFills;
    }

    public void unsetFills() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTBorders getBorders() {
        CTBorders cTBorders;
        synchronized (monitor()) {
            check_orphaned();
            cTBorders = (CTBorders) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTBorders == null) {
                cTBorders = null;
            }
        }
        return cTBorders;
    }

    public boolean isSetBorders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setBorders(CTBorders cTBorders) {
        generatedSetterHelperImpl(cTBorders, PROPERTY_QNAME[3], 0, 1);
    }

    public CTBorders addNewBorders() {
        CTBorders cTBorders;
        synchronized (monitor()) {
            check_orphaned();
            cTBorders = (CTBorders) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTBorders;
    }

    public void unsetBorders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTCellStyleXfs getCellStyleXfs() {
        CTCellStyleXfs cTCellStyleXfs;
        synchronized (monitor()) {
            check_orphaned();
            cTCellStyleXfs = (CTCellStyleXfs) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTCellStyleXfs == null) {
                cTCellStyleXfs = null;
            }
        }
        return cTCellStyleXfs;
    }

    public boolean isSetCellStyleXfs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setCellStyleXfs(CTCellStyleXfs cTCellStyleXfs) {
        generatedSetterHelperImpl(cTCellStyleXfs, PROPERTY_QNAME[4], 0, 1);
    }

    public CTCellStyleXfs addNewCellStyleXfs() {
        CTCellStyleXfs cTCellStyleXfs;
        synchronized (monitor()) {
            check_orphaned();
            cTCellStyleXfs = (CTCellStyleXfs) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTCellStyleXfs;
    }

    public void unsetCellStyleXfs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTCellXfs getCellXfs() {
        CTCellXfs cTCellXfs;
        synchronized (monitor()) {
            check_orphaned();
            cTCellXfs = (CTCellXfs) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTCellXfs == null) {
                cTCellXfs = null;
            }
        }
        return cTCellXfs;
    }

    public boolean isSetCellXfs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setCellXfs(CTCellXfs cTCellXfs) {
        generatedSetterHelperImpl(cTCellXfs, PROPERTY_QNAME[5], 0, 1);
    }

    public CTCellXfs addNewCellXfs() {
        CTCellXfs cTCellXfs;
        synchronized (monitor()) {
            check_orphaned();
            cTCellXfs = (CTCellXfs) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTCellXfs;
    }

    public void unsetCellXfs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTCellStyles getCellStyles() {
        CTCellStyles find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetCellStyles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setCellStyles(CTCellStyles cTCellStyles) {
        generatedSetterHelperImpl(cTCellStyles, PROPERTY_QNAME[6], 0, 1);
    }

    public CTCellStyles addNewCellStyles() {
        CTCellStyles add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return add_element_user;
    }

    public void unsetCellStyles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTDxfs getDxfs() {
        CTDxfs cTDxfs;
        synchronized (monitor()) {
            check_orphaned();
            cTDxfs = (CTDxfs) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTDxfs == null) {
                cTDxfs = null;
            }
        }
        return cTDxfs;
    }

    public boolean isSetDxfs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setDxfs(CTDxfs cTDxfs) {
        generatedSetterHelperImpl(cTDxfs, PROPERTY_QNAME[7], 0, 1);
    }

    public CTDxfs addNewDxfs() {
        CTDxfs cTDxfs;
        synchronized (monitor()) {
            check_orphaned();
            cTDxfs = (CTDxfs) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTDxfs;
    }

    public void unsetDxfs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTTableStyles getTableStyles() {
        CTTableStyles cTTableStyles;
        synchronized (monitor()) {
            check_orphaned();
            cTTableStyles = (CTTableStyles) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTTableStyles == null) {
                cTTableStyles = null;
            }
        }
        return cTTableStyles;
    }

    public boolean isSetTableStyles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setTableStyles(CTTableStyles cTTableStyles) {
        generatedSetterHelperImpl(cTTableStyles, PROPERTY_QNAME[8], 0, 1);
    }

    public CTTableStyles addNewTableStyles() {
        CTTableStyles cTTableStyles;
        synchronized (monitor()) {
            check_orphaned();
            cTTableStyles = (CTTableStyles) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTTableStyles;
    }

    public void unsetTableStyles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTColors getColors() {
        CTColors cTColors;
        synchronized (monitor()) {
            check_orphaned();
            cTColors = (CTColors) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTColors == null) {
                cTColors = null;
            }
        }
        return cTColors;
    }

    public boolean isSetColors() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setColors(CTColors cTColors) {
        generatedSetterHelperImpl(cTColors, PROPERTY_QNAME[9], 0, 1);
    }

    public CTColors addNewColors() {
        CTColors cTColors;
        synchronized (monitor()) {
            check_orphaned();
            cTColors = (CTColors) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTColors;
    }

    public void unsetColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[10], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[10], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }
}
