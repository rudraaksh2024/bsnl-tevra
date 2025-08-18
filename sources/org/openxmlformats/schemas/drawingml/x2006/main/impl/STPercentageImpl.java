package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentageDecimal;

public class STPercentageImpl extends XmlUnionImpl implements STPercentage, STPercentageDecimal, org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPercentage {
    private static final long serialVersionUID = 1;

    public STPercentageImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STPercentageImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
