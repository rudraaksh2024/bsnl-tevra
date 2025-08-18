package org.apache.xmlbeans;

import java.util.Calendar;
import java.util.Date;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlDate extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlDate> Factory;
    public static final SchemaType type;

    Calendar getCalendarValue();

    Date getDateValue();

    GDate getGDateValue();

    void setCalendarValue(Calendar calendar);

    void setDateValue(Date date);

    void setGDateValue(GDate gDate);

    static {
        XmlObjectFactory<XmlDate> xmlObjectFactory = new XmlObjectFactory<>("_BI_date");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
