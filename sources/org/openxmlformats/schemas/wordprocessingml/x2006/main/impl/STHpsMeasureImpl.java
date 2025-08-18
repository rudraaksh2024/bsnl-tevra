package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPositiveUniversalMeasure;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STUnsignedDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHpsMeasure;

public class STHpsMeasureImpl extends XmlUnionImpl implements STHpsMeasure, STUnsignedDecimalNumber, STPositiveUniversalMeasure {
    private static final long serialVersionUID = 1;

    public STHpsMeasureImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STHpsMeasureImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
