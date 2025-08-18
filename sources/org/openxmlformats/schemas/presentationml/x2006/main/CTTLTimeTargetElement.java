package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile;

public interface CTTLTimeTargetElement extends XmlObject {
    public static final DocumentFactory<CTTLTimeTargetElement> Factory;
    public static final SchemaType type;

    CTTLSubShapeId addNewInkTgt();

    CTEmpty addNewSldTgt();

    CTEmbeddedWAVAudioFile addNewSndTgt();

    CTTLShapeTargetElement addNewSpTgt();

    CTTLSubShapeId getInkTgt();

    CTEmpty getSldTgt();

    CTEmbeddedWAVAudioFile getSndTgt();

    CTTLShapeTargetElement getSpTgt();

    boolean isSetInkTgt();

    boolean isSetSldTgt();

    boolean isSetSndTgt();

    boolean isSetSpTgt();

    void setInkTgt(CTTLSubShapeId cTTLSubShapeId);

    void setSldTgt(CTEmpty cTEmpty);

    void setSndTgt(CTEmbeddedWAVAudioFile cTEmbeddedWAVAudioFile);

    void setSpTgt(CTTLShapeTargetElement cTTLShapeTargetElement);

    void unsetInkTgt();

    void unsetSldTgt();

    void unsetSndTgt();

    void unsetSpTgt();

    static {
        DocumentFactory<CTTLTimeTargetElement> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttltimetargetelementdca9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
