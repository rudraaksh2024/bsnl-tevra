package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STUniversalMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedHpsMeasure;

public class STSignedHpsMeasureImpl extends XmlUnionImpl implements STSignedHpsMeasure, XmlInteger, STUniversalMeasure {
    private static final long serialVersionUID = 1;

    public STSignedHpsMeasureImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STSignedHpsMeasureImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }
}
