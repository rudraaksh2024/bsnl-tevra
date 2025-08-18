package org.apache.poi.poifs.crypt.agile;

import org.apache.poi.poifs.crypt.agile.AgileEncryptor;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AgileEncryptor$AgileCipherOutputStream$$ExternalSyntheticLambda0 implements EncryptionRecord {
    public final /* synthetic */ AgileEncryptor.AgileCipherOutputStream f$0;

    public /* synthetic */ AgileEncryptor$AgileCipherOutputStream$$ExternalSyntheticLambda0(AgileEncryptor.AgileCipherOutputStream agileCipherOutputStream) {
        this.f$0 = agileCipherOutputStream;
    }

    public final void write(LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
        this.f$0.marshallEncryptionRecord(littleEndianByteArrayOutputStream);
    }
}
