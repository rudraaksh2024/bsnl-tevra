package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.Calendar;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

public interface CTTrackChange extends CTMarkup {
    public static final DocumentFactory<CTTrackChange> Factory;
    public static final SchemaType type;

    String getAuthor();

    Calendar getDate();

    boolean isSetDate();

    void setAuthor(String str);

    void setDate(Calendar calendar);

    void unsetDate();

    STString xgetAuthor();

    STDateTime xgetDate();

    void xsetAuthor(STString sTString);

    void xsetDate(STDateTime sTDateTime);

    static {
        DocumentFactory<CTTrackChange> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttrackchangec317type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
