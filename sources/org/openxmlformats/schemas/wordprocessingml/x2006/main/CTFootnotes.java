package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFootnotes extends XmlObject {
    public static final DocumentFactory<CTFootnotes> Factory;
    public static final SchemaType type;

    CTFtnEdn addNewFootnote();

    CTFtnEdn getFootnoteArray(int i);

    CTFtnEdn[] getFootnoteArray();

    List<CTFtnEdn> getFootnoteList();

    CTFtnEdn insertNewFootnote(int i);

    void removeFootnote(int i);

    void setFootnoteArray(int i, CTFtnEdn cTFtnEdn);

    void setFootnoteArray(CTFtnEdn[] cTFtnEdnArr);

    int sizeOfFootnoteArray();

    static {
        DocumentFactory<CTFootnotes> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfootnotes691ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
