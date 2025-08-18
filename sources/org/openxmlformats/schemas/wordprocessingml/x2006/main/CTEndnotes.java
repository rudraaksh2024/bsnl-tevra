package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTEndnotes extends XmlObject {
    public static final DocumentFactory<CTEndnotes> Factory;
    public static final SchemaType type;

    CTFtnEdn addNewEndnote();

    CTFtnEdn getEndnoteArray(int i);

    CTFtnEdn[] getEndnoteArray();

    List<CTFtnEdn> getEndnoteList();

    CTFtnEdn insertNewEndnote(int i);

    void removeEndnote(int i);

    void setEndnoteArray(int i, CTFtnEdn cTFtnEdn);

    void setEndnoteArray(CTFtnEdn[] cTFtnEdnArr);

    int sizeOfEndnoteArray();

    static {
        DocumentFactory<CTEndnotes> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctendnotescee2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
