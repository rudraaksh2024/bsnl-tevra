package org.apache.poi.poifs.crypt.xor;

import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;

public class XOREncryptionHeader extends EncryptionHeader implements EncryptionRecord {
    public void write(LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
    }

    protected XOREncryptionHeader() {
    }

    protected XOREncryptionHeader(XOREncryptionHeader xOREncryptionHeader) {
        super(xOREncryptionHeader);
    }

    public XOREncryptionHeader copy() {
        return new XOREncryptionHeader(this);
    }
}
