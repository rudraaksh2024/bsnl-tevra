package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTParaRPrChange extends CTTrackChange {
    public static final DocumentFactory<CTParaRPrChange> Factory;
    public static final SchemaType type;

    CTParaRPrOriginal addNewRPr();

    CTParaRPrOriginal getRPr();

    void setRPr(CTParaRPrOriginal cTParaRPrOriginal);

    static {
        DocumentFactory<CTParaRPrChange> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpararprchange986etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
