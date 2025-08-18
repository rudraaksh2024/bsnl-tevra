package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTWorkbookProtection extends XmlObject {
    public static final DocumentFactory<CTWorkbookProtection> Factory;
    public static final SchemaType type;

    boolean getLockRevision();

    boolean getLockStructure();

    boolean getLockWindows();

    String getRevisionsAlgorithmName();

    byte[] getRevisionsHashValue();

    byte[] getRevisionsPassword();

    String getRevisionsPasswordCharacterSet();

    byte[] getRevisionsSaltValue();

    long getRevisionsSpinCount();

    String getWorkbookAlgorithmName();

    byte[] getWorkbookHashValue();

    byte[] getWorkbookPassword();

    String getWorkbookPasswordCharacterSet();

    byte[] getWorkbookSaltValue();

    long getWorkbookSpinCount();

    boolean isSetLockRevision();

    boolean isSetLockStructure();

    boolean isSetLockWindows();

    boolean isSetRevisionsAlgorithmName();

    boolean isSetRevisionsHashValue();

    boolean isSetRevisionsPassword();

    boolean isSetRevisionsPasswordCharacterSet();

    boolean isSetRevisionsSaltValue();

    boolean isSetRevisionsSpinCount();

    boolean isSetWorkbookAlgorithmName();

    boolean isSetWorkbookHashValue();

    boolean isSetWorkbookPassword();

    boolean isSetWorkbookPasswordCharacterSet();

    boolean isSetWorkbookSaltValue();

    boolean isSetWorkbookSpinCount();

    void setLockRevision(boolean z);

    void setLockStructure(boolean z);

    void setLockWindows(boolean z);

    void setRevisionsAlgorithmName(String str);

    void setRevisionsHashValue(byte[] bArr);

    void setRevisionsPassword(byte[] bArr);

    void setRevisionsPasswordCharacterSet(String str);

    void setRevisionsSaltValue(byte[] bArr);

    void setRevisionsSpinCount(long j);

    void setWorkbookAlgorithmName(String str);

    void setWorkbookHashValue(byte[] bArr);

    void setWorkbookPassword(byte[] bArr);

    void setWorkbookPasswordCharacterSet(String str);

    void setWorkbookSaltValue(byte[] bArr);

    void setWorkbookSpinCount(long j);

    void unsetLockRevision();

    void unsetLockStructure();

    void unsetLockWindows();

    void unsetRevisionsAlgorithmName();

    void unsetRevisionsHashValue();

    void unsetRevisionsPassword();

    void unsetRevisionsPasswordCharacterSet();

    void unsetRevisionsSaltValue();

    void unsetRevisionsSpinCount();

    void unsetWorkbookAlgorithmName();

    void unsetWorkbookHashValue();

    void unsetWorkbookPassword();

    void unsetWorkbookPasswordCharacterSet();

    void unsetWorkbookSaltValue();

    void unsetWorkbookSpinCount();

    XmlBoolean xgetLockRevision();

    XmlBoolean xgetLockStructure();

    XmlBoolean xgetLockWindows();

    STXstring xgetRevisionsAlgorithmName();

    XmlBase64Binary xgetRevisionsHashValue();

    STUnsignedShortHex xgetRevisionsPassword();

    XmlString xgetRevisionsPasswordCharacterSet();

    XmlBase64Binary xgetRevisionsSaltValue();

    XmlUnsignedInt xgetRevisionsSpinCount();

    STXstring xgetWorkbookAlgorithmName();

    XmlBase64Binary xgetWorkbookHashValue();

    STUnsignedShortHex xgetWorkbookPassword();

    XmlString xgetWorkbookPasswordCharacterSet();

    XmlBase64Binary xgetWorkbookSaltValue();

    XmlUnsignedInt xgetWorkbookSpinCount();

    void xsetLockRevision(XmlBoolean xmlBoolean);

    void xsetLockStructure(XmlBoolean xmlBoolean);

    void xsetLockWindows(XmlBoolean xmlBoolean);

    void xsetRevisionsAlgorithmName(STXstring sTXstring);

    void xsetRevisionsHashValue(XmlBase64Binary xmlBase64Binary);

    void xsetRevisionsPassword(STUnsignedShortHex sTUnsignedShortHex);

    void xsetRevisionsPasswordCharacterSet(XmlString xmlString);

    void xsetRevisionsSaltValue(XmlBase64Binary xmlBase64Binary);

    void xsetRevisionsSpinCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetWorkbookAlgorithmName(STXstring sTXstring);

    void xsetWorkbookHashValue(XmlBase64Binary xmlBase64Binary);

    void xsetWorkbookPassword(STUnsignedShortHex sTUnsignedShortHex);

    void xsetWorkbookPasswordCharacterSet(XmlString xmlString);

    void xsetWorkbookSaltValue(XmlBase64Binary xmlBase64Binary);

    void xsetWorkbookSpinCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTWorkbookProtection> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctworkbookprotection56bctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
