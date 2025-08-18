package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFFCheckBox extends XmlObject {
    public static final DocumentFactory<CTFFCheckBox> Factory;
    public static final SchemaType type;

    CTOnOff addNewChecked();

    CTOnOff addNewDefault();

    CTHpsMeasure addNewSize();

    CTOnOff addNewSizeAuto();

    CTOnOff getChecked();

    CTOnOff getDefault();

    CTHpsMeasure getSize();

    CTOnOff getSizeAuto();

    boolean isSetChecked();

    boolean isSetDefault();

    boolean isSetSize();

    boolean isSetSizeAuto();

    void setChecked(CTOnOff cTOnOff);

    void setDefault(CTOnOff cTOnOff);

    void setSize(CTHpsMeasure cTHpsMeasure);

    void setSizeAuto(CTOnOff cTOnOff);

    void unsetChecked();

    void unsetDefault();

    void unsetSize();

    void unsetSizeAuto();

    static {
        DocumentFactory<CTFFCheckBox> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctffcheckboxf3a5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
