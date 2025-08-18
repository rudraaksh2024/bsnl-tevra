package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface TextType extends XmlObject {
    public static final DocumentFactory<TextType> Factory;
    public static final SchemaType type;

    CpType addNewCp();

    FldType addNewFld();

    PpType addNewPp();

    TpType addNewTp();

    CpType getCpArray(int i);

    CpType[] getCpArray();

    List<CpType> getCpList();

    FldType getFldArray(int i);

    FldType[] getFldArray();

    List<FldType> getFldList();

    PpType getPpArray(int i);

    PpType[] getPpArray();

    List<PpType> getPpList();

    TpType getTpArray(int i);

    TpType[] getTpArray();

    List<TpType> getTpList();

    CpType insertNewCp(int i);

    FldType insertNewFld(int i);

    PpType insertNewPp(int i);

    TpType insertNewTp(int i);

    void removeCp(int i);

    void removeFld(int i);

    void removePp(int i);

    void removeTp(int i);

    void setCpArray(int i, CpType cpType);

    void setCpArray(CpType[] cpTypeArr);

    void setFldArray(int i, FldType fldType);

    void setFldArray(FldType[] fldTypeArr);

    void setPpArray(int i, PpType ppType);

    void setPpArray(PpType[] ppTypeArr);

    void setTpArray(int i, TpType tpType);

    void setTpArray(TpType[] tpTypeArr);

    int sizeOfCpArray();

    int sizeOfFldArray();

    int sizeOfPpArray();

    int sizeOfTpArray();

    static {
        DocumentFactory<TextType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "texttyped2ectype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
