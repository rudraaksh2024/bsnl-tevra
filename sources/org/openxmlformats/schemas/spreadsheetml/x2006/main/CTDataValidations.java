package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDataValidations extends XmlObject {
    public static final DocumentFactory<CTDataValidations> Factory;
    public static final SchemaType type;

    CTDataValidation addNewDataValidation();

    long getCount();

    CTDataValidation getDataValidationArray(int i);

    CTDataValidation[] getDataValidationArray();

    List<CTDataValidation> getDataValidationList();

    boolean getDisablePrompts();

    long getXWindow();

    long getYWindow();

    CTDataValidation insertNewDataValidation(int i);

    boolean isSetCount();

    boolean isSetDisablePrompts();

    boolean isSetXWindow();

    boolean isSetYWindow();

    void removeDataValidation(int i);

    void setCount(long j);

    void setDataValidationArray(int i, CTDataValidation cTDataValidation);

    void setDataValidationArray(CTDataValidation[] cTDataValidationArr);

    void setDisablePrompts(boolean z);

    void setXWindow(long j);

    void setYWindow(long j);

    int sizeOfDataValidationArray();

    void unsetCount();

    void unsetDisablePrompts();

    void unsetXWindow();

    void unsetYWindow();

    XmlUnsignedInt xgetCount();

    XmlBoolean xgetDisablePrompts();

    XmlUnsignedInt xgetXWindow();

    XmlUnsignedInt xgetYWindow();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetDisablePrompts(XmlBoolean xmlBoolean);

    void xsetXWindow(XmlUnsignedInt xmlUnsignedInt);

    void xsetYWindow(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTDataValidations> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdatavalidationse46ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
