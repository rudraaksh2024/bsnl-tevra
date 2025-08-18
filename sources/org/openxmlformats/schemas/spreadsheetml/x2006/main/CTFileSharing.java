package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTFileSharing extends XmlObject {
    public static final DocumentFactory<CTFileSharing> Factory;
    public static final SchemaType type;

    String getAlgorithmName();

    byte[] getHashValue();

    boolean getReadOnlyRecommended();

    byte[] getReservationPassword();

    byte[] getSaltValue();

    long getSpinCount();

    String getUserName();

    boolean isSetAlgorithmName();

    boolean isSetHashValue();

    boolean isSetReadOnlyRecommended();

    boolean isSetReservationPassword();

    boolean isSetSaltValue();

    boolean isSetSpinCount();

    boolean isSetUserName();

    void setAlgorithmName(String str);

    void setHashValue(byte[] bArr);

    void setReadOnlyRecommended(boolean z);

    void setReservationPassword(byte[] bArr);

    void setSaltValue(byte[] bArr);

    void setSpinCount(long j);

    void setUserName(String str);

    void unsetAlgorithmName();

    void unsetHashValue();

    void unsetReadOnlyRecommended();

    void unsetReservationPassword();

    void unsetSaltValue();

    void unsetSpinCount();

    void unsetUserName();

    STXstring xgetAlgorithmName();

    XmlBase64Binary xgetHashValue();

    XmlBoolean xgetReadOnlyRecommended();

    STUnsignedShortHex xgetReservationPassword();

    XmlBase64Binary xgetSaltValue();

    XmlUnsignedInt xgetSpinCount();

    STXstring xgetUserName();

    void xsetAlgorithmName(STXstring sTXstring);

    void xsetHashValue(XmlBase64Binary xmlBase64Binary);

    void xsetReadOnlyRecommended(XmlBoolean xmlBoolean);

    void xsetReservationPassword(STUnsignedShortHex sTUnsignedShortHex);

    void xsetSaltValue(XmlBase64Binary xmlBase64Binary);

    void xsetSpinCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetUserName(STXstring sTXstring);

    static {
        DocumentFactory<CTFileSharing> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfilesharing5c9ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
