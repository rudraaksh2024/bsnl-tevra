package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

public interface CTLsdException extends XmlObject {
    public static final DocumentFactory<CTLsdException> Factory;
    public static final SchemaType type;

    Object getLocked();

    String getName();

    Object getQFormat();

    Object getSemiHidden();

    BigInteger getUiPriority();

    Object getUnhideWhenUsed();

    boolean isSetLocked();

    boolean isSetQFormat();

    boolean isSetSemiHidden();

    boolean isSetUiPriority();

    boolean isSetUnhideWhenUsed();

    void setLocked(Object obj);

    void setName(String str);

    void setQFormat(Object obj);

    void setSemiHidden(Object obj);

    void setUiPriority(BigInteger bigInteger);

    void setUnhideWhenUsed(Object obj);

    void unsetLocked();

    void unsetQFormat();

    void unsetSemiHidden();

    void unsetUiPriority();

    void unsetUnhideWhenUsed();

    STOnOff xgetLocked();

    STString xgetName();

    STOnOff xgetQFormat();

    STOnOff xgetSemiHidden();

    STDecimalNumber xgetUiPriority();

    STOnOff xgetUnhideWhenUsed();

    void xsetLocked(STOnOff sTOnOff);

    void xsetName(STString sTString);

    void xsetQFormat(STOnOff sTOnOff);

    void xsetSemiHidden(STOnOff sTOnOff);

    void xsetUiPriority(STDecimalNumber sTDecimalNumber);

    void xsetUnhideWhenUsed(STOnOff sTOnOff);

    static {
        DocumentFactory<CTLsdException> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlsdexceptiona296type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
