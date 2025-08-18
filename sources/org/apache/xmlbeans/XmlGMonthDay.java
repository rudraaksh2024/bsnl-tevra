package org.apache.xmlbeans;

import java.util.Calendar;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlGMonthDay extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlGMonthDay> Factory;
    public static final SchemaType type;

    Calendar getCalendarValue();

    GDate getGDateValue();

    void setCalendarValue(Calendar calendar);

    void setGDateValue(GDate gDate);

    static {
        XmlObjectFactory<XmlGMonthDay> xmlObjectFactory = new XmlObjectFactory<>("_BI_gMonthDay");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
