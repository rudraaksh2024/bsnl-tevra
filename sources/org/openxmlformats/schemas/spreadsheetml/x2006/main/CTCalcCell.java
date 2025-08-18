package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCalcCell extends XmlObject {
    public static final DocumentFactory<CTCalcCell> Factory;
    public static final SchemaType type;

    boolean getA();

    int getI();

    boolean getL();

    String getR();

    String getRef();

    boolean getS();

    boolean getT();

    boolean isSetA();

    boolean isSetI();

    boolean isSetL();

    boolean isSetR();

    boolean isSetRef();

    boolean isSetS();

    boolean isSetT();

    void setA(boolean z);

    void setI(int i);

    void setL(boolean z);

    void setR(String str);

    void setRef(String str);

    void setS(boolean z);

    void setT(boolean z);

    void unsetA();

    void unsetI();

    void unsetL();

    void unsetR();

    void unsetRef();

    void unsetS();

    void unsetT();

    XmlBoolean xgetA();

    XmlInt xgetI();

    XmlBoolean xgetL();

    STCellRef xgetR();

    STCellRef xgetRef();

    XmlBoolean xgetS();

    XmlBoolean xgetT();

    void xsetA(XmlBoolean xmlBoolean);

    void xsetI(XmlInt xmlInt);

    void xsetL(XmlBoolean xmlBoolean);

    void xsetR(STCellRef sTCellRef);

    void xsetRef(STCellRef sTCellRef);

    void xsetS(XmlBoolean xmlBoolean);

    void xsetT(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTCalcCell> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcalccellb960type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
