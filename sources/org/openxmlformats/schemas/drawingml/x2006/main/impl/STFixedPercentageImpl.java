package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STFixedPercentageDecimal;

public class STFixedPercentageImpl extends XmlUnionImpl implements STFixedPercentage, STFixedPercentageDecimal, org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STFixedPercentage {
    private static final long serialVersionUID = 1;

    public STFixedPercentageImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STFixedPercentageImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
