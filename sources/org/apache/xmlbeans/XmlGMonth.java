package org.apache.xmlbeans;

import java.util.Calendar;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlGMonth extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlGMonth> Factory;
    public static final SchemaType type;

    Calendar getCalendarValue();

    GDate getGDateValue();

    int getIntValue();

    void setCalendarValue(Calendar calendar);

    void setGDateValue(GDate gDate);

    void setIntValue(int i);

    static {
        XmlObjectFactory<XmlGMonth> xmlObjectFactory = new XmlObjectFactory<>("_BI_gMonth");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
