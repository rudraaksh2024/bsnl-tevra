package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;

public interface CTColumns extends XmlObject {
    public static final DocumentFactory<CTColumns> Factory;
    public static final SchemaType type;

    CTColumn addNewCol();

    CTColumn getColArray(int i);

    CTColumn[] getColArray();

    List<CTColumn> getColList();

    Object getEqualWidth();

    BigInteger getNum();

    Object getSep();

    Object getSpace();

    CTColumn insertNewCol(int i);

    boolean isSetEqualWidth();

    boolean isSetNum();

    boolean isSetSep();

    boolean isSetSpace();

    void removeCol(int i);

    void setColArray(int i, CTColumn cTColumn);

    void setColArray(CTColumn[] cTColumnArr);

    void setEqualWidth(Object obj);

    void setNum(BigInteger bigInteger);

    void setSep(Object obj);

    void setSpace(Object obj);

    int sizeOfColArray();

    void unsetEqualWidth();

    void unsetNum();

    void unsetSep();

    void unsetSpace();

    STOnOff xgetEqualWidth();

    STDecimalNumber xgetNum();

    STOnOff xgetSep();

    STTwipsMeasure xgetSpace();

    void xsetEqualWidth(STOnOff sTOnOff);

    void xsetNum(STDecimalNumber sTDecimalNumber);

    void xsetSep(STOnOff sTOnOff);

    void xsetSpace(STTwipsMeasure sTTwipsMeasure);

    static {
        DocumentFactory<CTColumns> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcolumns51d5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
