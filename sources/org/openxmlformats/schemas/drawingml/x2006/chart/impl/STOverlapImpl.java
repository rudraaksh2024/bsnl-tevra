package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOverlap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOverlapByte;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOverlapPercent;

public class STOverlapImpl extends XmlUnionImpl implements STOverlap, STOverlapPercent, STOverlapByte {
    private static final long serialVersionUID = 1;

    public STOverlapImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STOverlapImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
