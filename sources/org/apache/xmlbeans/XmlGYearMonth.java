package org.apache.xmlbeans;

import java.util.Calendar;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlGYearMonth extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlGYearMonth> Factory;
    public static final SchemaType type;

    Calendar getCalendarValue();

    GDate getGDateValue();

    void setCalendarValue(Calendar calendar);

    void setGDateValue(GDate gDate);

    static {
        XmlObjectFactory<XmlGYearMonth> xmlObjectFactory = new XmlObjectFactory<>("_BI_gYearMonth");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
