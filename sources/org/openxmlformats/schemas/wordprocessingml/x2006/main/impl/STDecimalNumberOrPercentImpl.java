package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPercentage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumberOrPercent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnqualifiedPercentage;

public class STDecimalNumberOrPercentImpl extends XmlUnionImpl implements STDecimalNumberOrPercent, STUnqualifiedPercentage, STPercentage {
    private static final long serialVersionUID = 1;

    public STDecimalNumberOrPercentImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STDecimalNumberOrPercentImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
