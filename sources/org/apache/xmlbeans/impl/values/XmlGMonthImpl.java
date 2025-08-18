package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlGMonth;

public class XmlGMonthImpl extends JavaGDateHolderEx implements XmlGMonth {
    public XmlGMonthImpl() {
        super(XmlGMonth.type, false);
    }

    public XmlGMonthImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
