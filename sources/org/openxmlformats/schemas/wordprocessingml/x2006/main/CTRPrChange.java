package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTRPrChange extends CTTrackChange {
    public static final DocumentFactory<CTRPrChange> Factory;
    public static final SchemaType type;

    CTRPrOriginal addNewRPr();

    CTRPrOriginal getRPr();

    void setRPr(CTRPrOriginal cTRPrOriginal);

    static {
        DocumentFactory<CTRPrChange> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrprchangeeaeetype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
