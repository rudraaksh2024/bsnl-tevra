package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;

public interface CTFileVersion extends XmlObject {
    public static final DocumentFactory<CTFileVersion> Factory;
    public static final SchemaType type;

    String getAppName();

    String getCodeName();

    String getLastEdited();

    String getLowestEdited();

    String getRupBuild();

    boolean isSetAppName();

    boolean isSetCodeName();

    boolean isSetLastEdited();

    boolean isSetLowestEdited();

    boolean isSetRupBuild();

    void setAppName(String str);

    void setCodeName(String str);

    void setLastEdited(String str);

    void setLowestEdited(String str);

    void setRupBuild(String str);

    void unsetAppName();

    void unsetCodeName();

    void unsetLastEdited();

    void unsetLowestEdited();

    void unsetRupBuild();

    XmlString xgetAppName();

    STGuid xgetCodeName();

    XmlString xgetLastEdited();

    XmlString xgetLowestEdited();

    XmlString xgetRupBuild();

    void xsetAppName(XmlString xmlString);

    void xsetCodeName(STGuid sTGuid);

    void xsetLastEdited(XmlString xmlString);

    void xsetLowestEdited(XmlString xmlString);

    void xsetRupBuild(XmlString xmlString);

    static {
        DocumentFactory<CTFileVersion> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfileversion559btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
