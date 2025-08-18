package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;

public interface CTLatentStyles extends XmlObject {
    public static final DocumentFactory<CTLatentStyles> Factory;
    public static final SchemaType type;

    CTLsdException addNewLsdException();

    BigInteger getCount();

    Object getDefLockedState();

    Object getDefQFormat();

    Object getDefSemiHidden();

    BigInteger getDefUIPriority();

    Object getDefUnhideWhenUsed();

    CTLsdException getLsdExceptionArray(int i);

    CTLsdException[] getLsdExceptionArray();

    List<CTLsdException> getLsdExceptionList();

    CTLsdException insertNewLsdException(int i);

    boolean isSetCount();

    boolean isSetDefLockedState();

    boolean isSetDefQFormat();

    boolean isSetDefSemiHidden();

    boolean isSetDefUIPriority();

    boolean isSetDefUnhideWhenUsed();

    void removeLsdException(int i);

    void setCount(BigInteger bigInteger);

    void setDefLockedState(Object obj);

    void setDefQFormat(Object obj);

    void setDefSemiHidden(Object obj);

    void setDefUIPriority(BigInteger bigInteger);

    void setDefUnhideWhenUsed(Object obj);

    void setLsdExceptionArray(int i, CTLsdException cTLsdException);

    void setLsdExceptionArray(CTLsdException[] cTLsdExceptionArr);

    int sizeOfLsdExceptionArray();

    void unsetCount();

    void unsetDefLockedState();

    void unsetDefQFormat();

    void unsetDefSemiHidden();

    void unsetDefUIPriority();

    void unsetDefUnhideWhenUsed();

    STDecimalNumber xgetCount();

    STOnOff xgetDefLockedState();

    STOnOff xgetDefQFormat();

    STOnOff xgetDefSemiHidden();

    STDecimalNumber xgetDefUIPriority();

    STOnOff xgetDefUnhideWhenUsed();

    void xsetCount(STDecimalNumber sTDecimalNumber);

    void xsetDefLockedState(STOnOff sTOnOff);

    void xsetDefQFormat(STOnOff sTOnOff);

    void xsetDefSemiHidden(STOnOff sTOnOff);

    void xsetDefUIPriority(STDecimalNumber sTDecimalNumber);

    void xsetDefUnhideWhenUsed(STOnOff sTOnOff);

    static {
        DocumentFactory<CTLatentStyles> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlatentstyles2e3atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
