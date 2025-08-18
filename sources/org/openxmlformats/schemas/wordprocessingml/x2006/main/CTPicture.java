package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPicture extends XmlObject {
    public static final DocumentFactory<CTPicture> Factory;
    public static final SchemaType type;

    CTControl addNewControl();

    CTRel addNewMovie();

    CTControl getControl();

    CTRel getMovie();

    boolean isSetControl();

    boolean isSetMovie();

    void setControl(CTControl cTControl);

    void setMovie(CTRel cTRel);

    void unsetControl();

    void unsetMovie();

    static {
        DocumentFactory<CTPicture> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpicture1054type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
