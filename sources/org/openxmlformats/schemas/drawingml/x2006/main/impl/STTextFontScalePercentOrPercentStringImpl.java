package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontScalePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontScalePercentOrPercentString;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPercentage;

public class STTextFontScalePercentOrPercentStringImpl extends XmlUnionImpl implements STTextFontScalePercentOrPercentString, STTextFontScalePercent, STPercentage {
    private static final long serialVersionUID = 1;

    public STTextFontScalePercentOrPercentStringImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STTextFontScalePercentOrPercentStringImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
