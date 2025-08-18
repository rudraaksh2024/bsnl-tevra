package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocGrid;

public interface CTDocGrid extends XmlObject {
    public static final DocumentFactory<CTDocGrid> Factory;
    public static final SchemaType type;

    BigInteger getCharSpace();

    BigInteger getLinePitch();

    STDocGrid.Enum getType();

    boolean isSetCharSpace();

    boolean isSetLinePitch();

    boolean isSetType();

    void setCharSpace(BigInteger bigInteger);

    void setLinePitch(BigInteger bigInteger);

    void setType(STDocGrid.Enum enumR);

    void unsetCharSpace();

    void unsetLinePitch();

    void unsetType();

    STDecimalNumber xgetCharSpace();

    STDecimalNumber xgetLinePitch();

    STDocGrid xgetType();

    void xsetCharSpace(STDecimalNumber sTDecimalNumber);

    void xsetLinePitch(STDecimalNumber sTDecimalNumber);

    void xsetType(STDocGrid sTDocGrid);

    static {
        DocumentFactory<CTDocGrid> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdocgride8b4type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
