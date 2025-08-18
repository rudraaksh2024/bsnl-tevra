package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;

public interface CTFtnEdnRef extends XmlObject {
    public static final DocumentFactory<CTFtnEdnRef> Factory;
    public static final SchemaType type;

    Object getCustomMarkFollows();

    BigInteger getId();

    boolean isSetCustomMarkFollows();

    void setCustomMarkFollows(Object obj);

    void setId(BigInteger bigInteger);

    void unsetCustomMarkFollows();

    STOnOff xgetCustomMarkFollows();

    STDecimalNumber xgetId();

    void xsetCustomMarkFollows(STOnOff sTOnOff);

    void xsetId(STDecimalNumber sTDecimalNumber);

    static {
        DocumentFactory<CTFtnEdnRef> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctftnednref89eetype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
