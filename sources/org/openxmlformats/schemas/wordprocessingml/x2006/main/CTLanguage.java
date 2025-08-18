package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STLang;

public interface CTLanguage extends XmlObject {
    public static final DocumentFactory<CTLanguage> Factory;
    public static final SchemaType type;

    String getBidi();

    String getEastAsia();

    String getVal();

    boolean isSetBidi();

    boolean isSetEastAsia();

    boolean isSetVal();

    void setBidi(String str);

    void setEastAsia(String str);

    void setVal(String str);

    void unsetBidi();

    void unsetEastAsia();

    void unsetVal();

    STLang xgetBidi();

    STLang xgetEastAsia();

    STLang xgetVal();

    void xsetBidi(STLang sTLang);

    void xsetEastAsia(STLang sTLang);

    void xsetVal(STLang sTLang);

    static {
        DocumentFactory<CTLanguage> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlanguage7b90type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
