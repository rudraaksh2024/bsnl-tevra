package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.STHPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.STHPercentUShort;
import org.openxmlformats.schemas.drawingml.x2006.chart.STHPercentWithSymbol;

public class STHPercentImpl extends XmlUnionImpl implements STHPercent, STHPercentWithSymbol, STHPercentUShort {
    private static final long serialVersionUID = 1;

    public STHPercentImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STHPercentImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
