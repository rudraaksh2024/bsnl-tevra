package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.Calendar;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSdtDate extends XmlObject {
    public static final DocumentFactory<CTSdtDate> Factory;
    public static final SchemaType type;

    CTCalendarType addNewCalendar();

    CTString addNewDateFormat();

    CTLang addNewLid();

    CTSdtDateMappingType addNewStoreMappedDataAs();

    CTCalendarType getCalendar();

    CTString getDateFormat();

    Calendar getFullDate();

    CTLang getLid();

    CTSdtDateMappingType getStoreMappedDataAs();

    boolean isSetCalendar();

    boolean isSetDateFormat();

    boolean isSetFullDate();

    boolean isSetLid();

    boolean isSetStoreMappedDataAs();

    void setCalendar(CTCalendarType cTCalendarType);

    void setDateFormat(CTString cTString);

    void setFullDate(Calendar calendar);

    void setLid(CTLang cTLang);

    void setStoreMappedDataAs(CTSdtDateMappingType cTSdtDateMappingType);

    void unsetCalendar();

    void unsetDateFormat();

    void unsetFullDate();

    void unsetLid();

    void unsetStoreMappedDataAs();

    STDateTime xgetFullDate();

    void xsetFullDate(STDateTime sTDateTime);

    static {
        DocumentFactory<CTSdtDate> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdtdatedfa1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
