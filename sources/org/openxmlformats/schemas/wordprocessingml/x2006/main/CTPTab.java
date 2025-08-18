package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPTab extends XmlObject {
    public static final DocumentFactory<CTPTab> Factory;
    public static final SchemaType type;

    STPTabAlignment$Enum getAlignment();

    STPTabLeader$Enum getLeader();

    STPTabRelativeTo$Enum getRelativeTo();

    void setAlignment(STPTabAlignment$Enum sTPTabAlignment$Enum);

    void setLeader(STPTabLeader$Enum sTPTabLeader$Enum);

    void setRelativeTo(STPTabRelativeTo$Enum sTPTabRelativeTo$Enum);

    STPTabAlignment xgetAlignment();

    STPTabLeader xgetLeader();

    STPTabRelativeTo xgetRelativeTo();

    void xsetAlignment(STPTabAlignment sTPTabAlignment);

    void xsetLeader(STPTabLeader sTPTabLeader);

    void xsetRelativeTo(STPTabRelativeTo sTPTabRelativeTo);

    static {
        DocumentFactory<CTPTab> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctptaba283type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
