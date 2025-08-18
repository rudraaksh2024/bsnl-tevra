package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;

public class XmlStringRestriction extends JavaStringHolderEx implements XmlString {
    public XmlStringRestriction(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
