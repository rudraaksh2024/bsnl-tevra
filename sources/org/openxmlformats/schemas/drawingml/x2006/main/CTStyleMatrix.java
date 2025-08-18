package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTStyleMatrix extends XmlObject {
    public static final DocumentFactory<CTStyleMatrix> Factory;
    public static final SchemaType type;

    CTBackgroundFillStyleList addNewBgFillStyleLst();

    CTEffectStyleList addNewEffectStyleLst();

    CTFillStyleList addNewFillStyleLst();

    CTLineStyleList addNewLnStyleLst();

    CTBackgroundFillStyleList getBgFillStyleLst();

    CTEffectStyleList getEffectStyleLst();

    CTFillStyleList getFillStyleLst();

    CTLineStyleList getLnStyleLst();

    String getName();

    boolean isSetName();

    void setBgFillStyleLst(CTBackgroundFillStyleList cTBackgroundFillStyleList);

    void setEffectStyleLst(CTEffectStyleList cTEffectStyleList);

    void setFillStyleLst(CTFillStyleList cTFillStyleList);

    void setLnStyleLst(CTLineStyleList cTLineStyleList);

    void setName(String str);

    void unsetName();

    XmlString xgetName();

    void xsetName(XmlString xmlString);

    static {
        DocumentFactory<CTStyleMatrix> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstylematrix1903type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
