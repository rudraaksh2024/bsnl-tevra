package org.apache.poi.schemas.vmldrawing.impl;

import org.apache.poi.schemas.vmldrawing.CTXML;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTXMLImpl extends XmlComplexContentImpl implements CTXML {
    private static final long serialVersionUID = 1;

    public CTXMLImpl(SchemaType schemaType) {
        super(schemaType);
    }
}
