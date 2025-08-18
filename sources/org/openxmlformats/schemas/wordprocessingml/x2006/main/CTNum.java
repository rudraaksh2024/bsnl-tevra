package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTNum extends XmlObject {
    public static final DocumentFactory<CTNum> Factory;
    public static final SchemaType type;

    CTDecimalNumber addNewAbstractNumId();

    CTNumLvl addNewLvlOverride();

    CTDecimalNumber getAbstractNumId();

    CTNumLvl getLvlOverrideArray(int i);

    CTNumLvl[] getLvlOverrideArray();

    List<CTNumLvl> getLvlOverrideList();

    BigInteger getNumId();

    CTNumLvl insertNewLvlOverride(int i);

    void removeLvlOverride(int i);

    void setAbstractNumId(CTDecimalNumber cTDecimalNumber);

    void setLvlOverrideArray(int i, CTNumLvl cTNumLvl);

    void setLvlOverrideArray(CTNumLvl[] cTNumLvlArr);

    void setNumId(BigInteger bigInteger);

    int sizeOfLvlOverrideArray();

    STDecimalNumber xgetNumId();

    void xsetNumId(STDecimalNumber sTDecimalNumber);

    static {
        DocumentFactory<CTNum> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnume94ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
