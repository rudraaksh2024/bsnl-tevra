package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGapAmountPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGapAmountUShort;

public class STGapAmountImpl extends XmlUnionImpl implements STGapAmount, STGapAmountPercent, STGapAmountUShort {
    private static final long serialVersionUID = 1;

    public STGapAmountImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STGapAmountImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
