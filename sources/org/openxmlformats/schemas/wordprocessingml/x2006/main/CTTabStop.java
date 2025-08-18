package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabTlc;

public interface CTTabStop extends XmlObject {
    public static final DocumentFactory<CTTabStop> Factory;
    public static final SchemaType type;

    STTabTlc.Enum getLeader();

    Object getPos();

    STTabJc.Enum getVal();

    boolean isSetLeader();

    void setLeader(STTabTlc.Enum enumR);

    void setPos(Object obj);

    void setVal(STTabJc.Enum enumR);

    void unsetLeader();

    STTabTlc xgetLeader();

    STSignedTwipsMeasure xgetPos();

    STTabJc xgetVal();

    void xsetLeader(STTabTlc sTTabTlc);

    void xsetPos(STSignedTwipsMeasure sTSignedTwipsMeasure);

    void xsetVal(STTabJc sTTabJc);

    static {
        DocumentFactory<CTTabStop> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttabstop5ebbtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
