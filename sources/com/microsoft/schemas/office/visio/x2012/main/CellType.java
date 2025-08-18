package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CellType extends XmlObject {
    public static final DocumentFactory<CellType> Factory;
    public static final SchemaType type;

    RefByType addNewRefBy();

    String getE();

    String getF();

    String getN();

    RefByType getRefByArray(int i);

    RefByType[] getRefByArray();

    List<RefByType> getRefByList();

    String getU();

    String getV();

    RefByType insertNewRefBy(int i);

    boolean isSetE();

    boolean isSetF();

    boolean isSetU();

    boolean isSetV();

    void removeRefBy(int i);

    void setE(String str);

    void setF(String str);

    void setN(String str);

    void setRefByArray(int i, RefByType refByType);

    void setRefByArray(RefByType[] refByTypeArr);

    void setU(String str);

    void setV(String str);

    int sizeOfRefByArray();

    void unsetE();

    void unsetF();

    void unsetU();

    void unsetV();

    XmlString xgetE();

    XmlString xgetF();

    XmlString xgetN();

    XmlString xgetU();

    XmlString xgetV();

    void xsetE(XmlString xmlString);

    void xsetF(XmlString xmlString);

    void xsetN(XmlString xmlString);

    void xsetU(XmlString xmlString);

    void xsetV(XmlString xmlString);

    static {
        DocumentFactory<CellType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "celltyped857type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
