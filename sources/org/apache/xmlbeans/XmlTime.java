package org.apache.xmlbeans;

import java.util.Calendar;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlTime extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlTime> Factory;
    public static final SchemaType type;

    Calendar getCalendarValue();

    GDate getGDateValue();

    void setCalendarValue(Calendar calendar);

    void setGDateValue(GDate gDate);

    static {
        XmlObjectFactory<XmlTime> xmlObjectFactory = new XmlObjectFactory<>("_BI_time");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
