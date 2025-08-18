package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositivePercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositivePercentageDecimal;

public class STPositivePercentageImpl extends XmlUnionImpl implements STPositivePercentage, STPositivePercentageDecimal, org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPositivePercentage {
    private static final long serialVersionUID = 1;

    public STPositivePercentageImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STPositivePercentageImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
