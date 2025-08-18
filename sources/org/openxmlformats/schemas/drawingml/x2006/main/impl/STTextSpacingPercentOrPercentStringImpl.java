package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextSpacingPercent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextSpacingPercentOrPercentString;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPercentage;

public class STTextSpacingPercentOrPercentStringImpl extends XmlUnionImpl implements STTextSpacingPercentOrPercentString, STTextSpacingPercent, STPercentage {
    private static final long serialVersionUID = 1;

    public STTextSpacingPercentOrPercentStringImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STTextSpacingPercentOrPercentStringImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
