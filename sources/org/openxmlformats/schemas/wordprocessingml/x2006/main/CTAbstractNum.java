package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTAbstractNum extends XmlObject {
    public static final DocumentFactory<CTAbstractNum> Factory;
    public static final SchemaType type;

    CTLvl addNewLvl();

    CTMultiLevelType addNewMultiLevelType();

    CTString addNewName();

    CTLongHexNumber addNewNsid();

    CTString addNewNumStyleLink();

    CTString addNewStyleLink();

    CTLongHexNumber addNewTmpl();

    BigInteger getAbstractNumId();

    CTLvl getLvlArray(int i);

    CTLvl[] getLvlArray();

    List<CTLvl> getLvlList();

    CTMultiLevelType getMultiLevelType();

    CTString getName();

    CTLongHexNumber getNsid();

    CTString getNumStyleLink();

    CTString getStyleLink();

    CTLongHexNumber getTmpl();

    CTLvl insertNewLvl(int i);

    boolean isSetMultiLevelType();

    boolean isSetName();

    boolean isSetNsid();

    boolean isSetNumStyleLink();

    boolean isSetStyleLink();

    boolean isSetTmpl();

    void removeLvl(int i);

    void setAbstractNumId(BigInteger bigInteger);

    void setLvlArray(int i, CTLvl cTLvl);

    void setLvlArray(CTLvl[] cTLvlArr);

    void setMultiLevelType(CTMultiLevelType cTMultiLevelType);

    void setName(CTString cTString);

    void setNsid(CTLongHexNumber cTLongHexNumber);

    void setNumStyleLink(CTString cTString);

    void setStyleLink(CTString cTString);

    void setTmpl(CTLongHexNumber cTLongHexNumber);

    int sizeOfLvlArray();

    void unsetMultiLevelType();

    void unsetName();

    void unsetNsid();

    void unsetNumStyleLink();

    void unsetStyleLink();

    void unsetTmpl();

    STDecimalNumber xgetAbstractNumId();

    void xsetAbstractNumId(STDecimalNumber sTDecimalNumber);

    static {
        DocumentFactory<CTAbstractNum> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctabstractnum588etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
