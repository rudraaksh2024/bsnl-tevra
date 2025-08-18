package org.openxmlformats.schemas.officeDocument.x2006.math;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTOMathPara extends XmlObject {
    public static final DocumentFactory<CTOMathPara> Factory;
    public static final SchemaType type;

    CTOMath addNewOMath();

    CTOMathParaPr addNewOMathParaPr();

    CTOMath getOMathArray(int i);

    CTOMath[] getOMathArray();

    List<CTOMath> getOMathList();

    CTOMathParaPr getOMathParaPr();

    CTOMath insertNewOMath(int i);

    boolean isSetOMathParaPr();

    void removeOMath(int i);

    void setOMathArray(int i, CTOMath cTOMath);

    void setOMathArray(CTOMath[] cTOMathArr);

    void setOMathParaPr(CTOMathParaPr cTOMathParaPr);

    int sizeOfOMathArray();

    void unsetOMathParaPr();

    static {
        DocumentFactory<CTOMathPara> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctomathpara8825type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
