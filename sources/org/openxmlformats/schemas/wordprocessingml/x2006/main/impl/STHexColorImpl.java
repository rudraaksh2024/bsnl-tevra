package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STHexColorRGB;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColorAuto;

public class STHexColorImpl extends XmlUnionImpl implements STHexColor, STHexColorAuto, STHexColorRGB {
    private static final long serialVersionUID = 1;

    public STHexColorImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STHexColorImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
