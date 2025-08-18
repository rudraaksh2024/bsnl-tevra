package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;

public interface CTHeight extends XmlObject {
    public static final DocumentFactory<CTHeight> Factory;
    public static final SchemaType type;

    STHeightRule.Enum getHRule();

    Object getVal();

    boolean isSetHRule();

    boolean isSetVal();

    void setHRule(STHeightRule.Enum enumR);

    void setVal(Object obj);

    void unsetHRule();

    void unsetVal();

    STHeightRule xgetHRule();

    STTwipsMeasure xgetVal();

    void xsetHRule(STHeightRule sTHeightRule);

    void xsetVal(STTwipsMeasure sTTwipsMeasure);

    static {
        DocumentFactory<CTHeight> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctheighta2e1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
