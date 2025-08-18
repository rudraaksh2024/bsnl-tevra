package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ColorsType;
import com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.DocumentSheetType;
import com.microsoft.schemas.office.visio.x2012.main.EventListType;
import com.microsoft.schemas.office.visio.x2012.main.FaceNamesType;
import com.microsoft.schemas.office.visio.x2012.main.HeaderFooterType;
import com.microsoft.schemas.office.visio.x2012.main.PublishSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.StyleSheetsType;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class VisioDocumentTypeImpl extends XmlComplexContentImpl implements VisioDocumentType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "DocumentSettings"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Colors"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "FaceNames"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "StyleSheets"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "DocumentSheet"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "EventList"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "HeaderFooter"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "PublishSettings")};
    private static final long serialVersionUID = 1;

    public VisioDocumentTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public DocumentSettingsType getDocumentSettings() {
        DocumentSettingsType documentSettingsType;
        synchronized (monitor()) {
            check_orphaned();
            documentSettingsType = (DocumentSettingsType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (documentSettingsType == null) {
                documentSettingsType = null;
            }
        }
        return documentSettingsType;
    }

    public boolean isSetDocumentSettings() {
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

    public void setDocumentSettings(DocumentSettingsType documentSettingsType) {
        generatedSetterHelperImpl(documentSettingsType, PROPERTY_QNAME[0], 0, 1);
    }

    public DocumentSettingsType addNewDocumentSettings() {
        DocumentSettingsType documentSettingsType;
        synchronized (monitor()) {
            check_orphaned();
            documentSettingsType = (DocumentSettingsType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return documentSettingsType;
    }

    public void unsetDocumentSettings() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public ColorsType getColors() {
        ColorsType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetColors() {
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

    public void setColors(ColorsType colorsType) {
        generatedSetterHelperImpl(colorsType, PROPERTY_QNAME[1], 0, 1);
    }

    public ColorsType addNewColors() {
        ColorsType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void unsetColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public FaceNamesType getFaceNames() {
        FaceNamesType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetFaceNames() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setFaceNames(FaceNamesType faceNamesType) {
        generatedSetterHelperImpl(faceNamesType, PROPERTY_QNAME[2], 0, 1);
    }

    public FaceNamesType addNewFaceNames() {
        FaceNamesType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetFaceNames() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public StyleSheetsType getStyleSheets() {
        StyleSheetsType styleSheetsType;
        synchronized (monitor()) {
            check_orphaned();
            styleSheetsType = (StyleSheetsType) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (styleSheetsType == null) {
                styleSheetsType = null;
            }
        }
        return styleSheetsType;
    }

    public boolean isSetStyleSheets() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setStyleSheets(StyleSheetsType styleSheetsType) {
        generatedSetterHelperImpl(styleSheetsType, PROPERTY_QNAME[3], 0, 1);
    }

    public StyleSheetsType addNewStyleSheets() {
        StyleSheetsType styleSheetsType;
        synchronized (monitor()) {
            check_orphaned();
            styleSheetsType = (StyleSheetsType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return styleSheetsType;
    }

    public void unsetStyleSheets() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public DocumentSheetType getDocumentSheet() {
        DocumentSheetType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetDocumentSheet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setDocumentSheet(DocumentSheetType documentSheetType) {
        generatedSetterHelperImpl(documentSheetType, PROPERTY_QNAME[4], 0, 1);
    }

    public DocumentSheetType addNewDocumentSheet() {
        DocumentSheetType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return add_element_user;
    }

    public void unsetDocumentSheet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public EventListType getEventList() {
        EventListType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetEventList() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setEventList(EventListType eventListType) {
        generatedSetterHelperImpl(eventListType, PROPERTY_QNAME[5], 0, 1);
    }

    public EventListType addNewEventList() {
        EventListType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return add_element_user;
    }

    public void unsetEventList() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public HeaderFooterType getHeaderFooter() {
        HeaderFooterType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetHeaderFooter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setHeaderFooter(HeaderFooterType headerFooterType) {
        generatedSetterHelperImpl(headerFooterType, PROPERTY_QNAME[6], 0, 1);
    }

    public HeaderFooterType addNewHeaderFooter() {
        HeaderFooterType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return add_element_user;
    }

    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public PublishSettingsType getPublishSettings() {
        PublishSettingsType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetPublishSettings() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setPublishSettings(PublishSettingsType publishSettingsType) {
        generatedSetterHelperImpl(publishSettingsType, PROPERTY_QNAME[7], 0, 1);
    }

    public PublishSettingsType addNewPublishSettings() {
        PublishSettingsType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return add_element_user;
    }

    public void unsetPublishSettings() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }
}
