package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTBookmarkRange extends CTMarkupRange {
    public static final DocumentFactory<CTBookmarkRange> Factory;
    public static final SchemaType type;

    BigInteger getColFirst();

    BigInteger getColLast();

    boolean isSetColFirst();

    boolean isSetColLast();

    void setColFirst(BigInteger bigInteger);

    void setColLast(BigInteger bigInteger);

    void unsetColFirst();

    void unsetColLast();

    STDecimalNumber xgetColFirst();

    STDecimalNumber xgetColLast();

    void xsetColFirst(STDecimalNumber sTDecimalNumber);

    void xsetColLast(STDecimalNumber sTDecimalNumber);

    static {
        DocumentFactory<CTBookmarkRange> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbookmarkranged88btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
