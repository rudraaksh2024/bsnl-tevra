package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTGradientStopList extends XmlObject {
    public static final DocumentFactory<CTGradientStopList> Factory;
    public static final SchemaType type;

    CTGradientStop addNewGs();

    CTGradientStop getGsArray(int i);

    CTGradientStop[] getGsArray();

    List<CTGradientStop> getGsList();

    CTGradientStop insertNewGs(int i);

    void removeGs(int i);

    void setGsArray(int i, CTGradientStop cTGradientStop);

    void setGsArray(CTGradientStop[] cTGradientStopArr);

    int sizeOfGsArray();

    static {
        DocumentFactory<CTGradientStopList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgradientstoplist7eabtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
