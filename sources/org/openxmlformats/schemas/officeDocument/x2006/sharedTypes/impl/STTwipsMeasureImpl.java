package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPositiveUniversalMeasure;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STUnsignedDecimalNumber;

public class STTwipsMeasureImpl extends XmlUnionImpl implements STTwipsMeasure, STUnsignedDecimalNumber, STPositiveUniversalMeasure {
    private static final long serialVersionUID = 1;

    public STTwipsMeasureImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STTwipsMeasureImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
