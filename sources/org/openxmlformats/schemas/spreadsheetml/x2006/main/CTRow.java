package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTRow extends XmlObject {
    public static final DocumentFactory<CTRow> Factory;
    public static final SchemaType type;

    CTCell addNewC();

    CTExtensionList addNewExtLst();

    CTCell getCArray(int i);

    CTCell[] getCArray();

    List<CTCell> getCList();

    boolean getCollapsed();

    boolean getCustomFormat();

    boolean getCustomHeight();

    CTExtensionList getExtLst();

    boolean getHidden();

    double getHt();

    short getOutlineLevel();

    boolean getPh();

    long getR();

    long getS();

    List getSpans();

    boolean getThickBot();

    boolean getThickTop();

    CTCell insertNewC(int i);

    boolean isSetCollapsed();

    boolean isSetCustomFormat();

    boolean isSetCustomHeight();

    boolean isSetExtLst();

    boolean isSetHidden();

    boolean isSetHt();

    boolean isSetOutlineLevel();

    boolean isSetPh();

    boolean isSetR();

    boolean isSetS();

    boolean isSetSpans();

    boolean isSetThickBot();

    boolean isSetThickTop();

    void removeC(int i);

    void setCArray(int i, CTCell cTCell);

    void setCArray(CTCell[] cTCellArr);

    void setCollapsed(boolean z);

    void setCustomFormat(boolean z);

    void setCustomHeight(boolean z);

    void setExtLst(CTExtensionList cTExtensionList);

    void setHidden(boolean z);

    void setHt(double d);

    void setOutlineLevel(short s);

    void setPh(boolean z);

    void setR(long j);

    void setS(long j);

    void setSpans(List list);

    void setThickBot(boolean z);

    void setThickTop(boolean z);

    int sizeOfCArray();

    void unsetCollapsed();

    void unsetCustomFormat();

    void unsetCustomHeight();

    void unsetExtLst();

    void unsetHidden();

    void unsetHt();

    void unsetOutlineLevel();

    void unsetPh();

    void unsetR();

    void unsetS();

    void unsetSpans();

    void unsetThickBot();

    void unsetThickTop();

    XmlBoolean xgetCollapsed();

    XmlBoolean xgetCustomFormat();

    XmlBoolean xgetCustomHeight();

    XmlBoolean xgetHidden();

    XmlDouble xgetHt();

    XmlUnsignedByte xgetOutlineLevel();

    XmlBoolean xgetPh();

    XmlUnsignedInt xgetR();

    XmlUnsignedInt xgetS();

    STCellSpans xgetSpans();

    XmlBoolean xgetThickBot();

    XmlBoolean xgetThickTop();

    void xsetCollapsed(XmlBoolean xmlBoolean);

    void xsetCustomFormat(XmlBoolean xmlBoolean);

    void xsetCustomHeight(XmlBoolean xmlBoolean);

    void xsetHidden(XmlBoolean xmlBoolean);

    void xsetHt(XmlDouble xmlDouble);

    void xsetOutlineLevel(XmlUnsignedByte xmlUnsignedByte);

    void xsetPh(XmlBoolean xmlBoolean);

    void xsetR(XmlUnsignedInt xmlUnsignedInt);

    void xsetS(XmlUnsignedInt xmlUnsignedInt);

    void xsetSpans(STCellSpans sTCellSpans);

    void xsetThickBot(XmlBoolean xmlBoolean);

    void xsetThickTop(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTRow> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrowdd39type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
