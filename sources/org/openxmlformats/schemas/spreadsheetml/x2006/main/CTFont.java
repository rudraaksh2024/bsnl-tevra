package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFont extends XmlObject {
    public static final DocumentFactory<CTFont> Factory;
    public static final SchemaType type;

    CTBooleanProperty addNewB();

    CTIntProperty addNewCharset();

    CTColor addNewColor();

    CTBooleanProperty addNewCondense();

    CTBooleanProperty addNewExtend();

    CTFontFamily addNewFamily();

    CTBooleanProperty addNewI();

    CTFontName addNewName();

    CTBooleanProperty addNewOutline();

    CTFontScheme addNewScheme();

    CTBooleanProperty addNewShadow();

    CTBooleanProperty addNewStrike();

    CTFontSize addNewSz();

    CTUnderlineProperty addNewU();

    CTVerticalAlignFontProperty addNewVertAlign();

    CTBooleanProperty getBArray(int i);

    CTBooleanProperty[] getBArray();

    List<CTBooleanProperty> getBList();

    CTIntProperty getCharsetArray(int i);

    CTIntProperty[] getCharsetArray();

    List<CTIntProperty> getCharsetList();

    CTColor getColorArray(int i);

    CTColor[] getColorArray();

    List<CTColor> getColorList();

    CTBooleanProperty getCondenseArray(int i);

    CTBooleanProperty[] getCondenseArray();

    List<CTBooleanProperty> getCondenseList();

    CTBooleanProperty getExtendArray(int i);

    CTBooleanProperty[] getExtendArray();

    List<CTBooleanProperty> getExtendList();

    CTFontFamily getFamilyArray(int i);

    CTFontFamily[] getFamilyArray();

    List<CTFontFamily> getFamilyList();

    CTBooleanProperty getIArray(int i);

    CTBooleanProperty[] getIArray();

    List<CTBooleanProperty> getIList();

    CTFontName getNameArray(int i);

    CTFontName[] getNameArray();

    List<CTFontName> getNameList();

    CTBooleanProperty getOutlineArray(int i);

    CTBooleanProperty[] getOutlineArray();

    List<CTBooleanProperty> getOutlineList();

    CTFontScheme getSchemeArray(int i);

    CTFontScheme[] getSchemeArray();

    List<CTFontScheme> getSchemeList();

    CTBooleanProperty getShadowArray(int i);

    CTBooleanProperty[] getShadowArray();

    List<CTBooleanProperty> getShadowList();

    CTBooleanProperty getStrikeArray(int i);

    CTBooleanProperty[] getStrikeArray();

    List<CTBooleanProperty> getStrikeList();

    CTFontSize getSzArray(int i);

    CTFontSize[] getSzArray();

    List<CTFontSize> getSzList();

    CTUnderlineProperty getUArray(int i);

    CTUnderlineProperty[] getUArray();

    List<CTUnderlineProperty> getUList();

    CTVerticalAlignFontProperty getVertAlignArray(int i);

    CTVerticalAlignFontProperty[] getVertAlignArray();

    List<CTVerticalAlignFontProperty> getVertAlignList();

    CTBooleanProperty insertNewB(int i);

    CTIntProperty insertNewCharset(int i);

    CTColor insertNewColor(int i);

    CTBooleanProperty insertNewCondense(int i);

    CTBooleanProperty insertNewExtend(int i);

    CTFontFamily insertNewFamily(int i);

    CTBooleanProperty insertNewI(int i);

    CTFontName insertNewName(int i);

    CTBooleanProperty insertNewOutline(int i);

    CTFontScheme insertNewScheme(int i);

    CTBooleanProperty insertNewShadow(int i);

    CTBooleanProperty insertNewStrike(int i);

    CTFontSize insertNewSz(int i);

    CTUnderlineProperty insertNewU(int i);

    CTVerticalAlignFontProperty insertNewVertAlign(int i);

    void removeB(int i);

    void removeCharset(int i);

    void removeColor(int i);

    void removeCondense(int i);

    void removeExtend(int i);

    void removeFamily(int i);

    void removeI(int i);

    void removeName(int i);

    void removeOutline(int i);

    void removeScheme(int i);

    void removeShadow(int i);

    void removeStrike(int i);

    void removeSz(int i);

    void removeU(int i);

    void removeVertAlign(int i);

    void setBArray(int i, CTBooleanProperty cTBooleanProperty);

    void setBArray(CTBooleanProperty[] cTBooleanPropertyArr);

    void setCharsetArray(int i, CTIntProperty cTIntProperty);

    void setCharsetArray(CTIntProperty[] cTIntPropertyArr);

    void setColorArray(int i, CTColor cTColor);

    void setColorArray(CTColor[] cTColorArr);

    void setCondenseArray(int i, CTBooleanProperty cTBooleanProperty);

    void setCondenseArray(CTBooleanProperty[] cTBooleanPropertyArr);

    void setExtendArray(int i, CTBooleanProperty cTBooleanProperty);

    void setExtendArray(CTBooleanProperty[] cTBooleanPropertyArr);

    void setFamilyArray(int i, CTFontFamily cTFontFamily);

    void setFamilyArray(CTFontFamily[] cTFontFamilyArr);

    void setIArray(int i, CTBooleanProperty cTBooleanProperty);

    void setIArray(CTBooleanProperty[] cTBooleanPropertyArr);

    void setNameArray(int i, CTFontName cTFontName);

    void setNameArray(CTFontName[] cTFontNameArr);

    void setOutlineArray(int i, CTBooleanProperty cTBooleanProperty);

    void setOutlineArray(CTBooleanProperty[] cTBooleanPropertyArr);

    void setSchemeArray(int i, CTFontScheme cTFontScheme);

    void setSchemeArray(CTFontScheme[] cTFontSchemeArr);

    void setShadowArray(int i, CTBooleanProperty cTBooleanProperty);

    void setShadowArray(CTBooleanProperty[] cTBooleanPropertyArr);

    void setStrikeArray(int i, CTBooleanProperty cTBooleanProperty);

    void setStrikeArray(CTBooleanProperty[] cTBooleanPropertyArr);

    void setSzArray(int i, CTFontSize cTFontSize);

    void setSzArray(CTFontSize[] cTFontSizeArr);

    void setUArray(int i, CTUnderlineProperty cTUnderlineProperty);

    void setUArray(CTUnderlineProperty[] cTUnderlinePropertyArr);

    void setVertAlignArray(int i, CTVerticalAlignFontProperty cTVerticalAlignFontProperty);

    void setVertAlignArray(CTVerticalAlignFontProperty[] cTVerticalAlignFontPropertyArr);

    int sizeOfBArray();

    int sizeOfCharsetArray();

    int sizeOfColorArray();

    int sizeOfCondenseArray();

    int sizeOfExtendArray();

    int sizeOfFamilyArray();

    int sizeOfIArray();

    int sizeOfNameArray();

    int sizeOfOutlineArray();

    int sizeOfSchemeArray();

    int sizeOfShadowArray();

    int sizeOfStrikeArray();

    int sizeOfSzArray();

    int sizeOfUArray();

    int sizeOfVertAlignArray();

    static {
        DocumentFactory<CTFont> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfont14d8type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
