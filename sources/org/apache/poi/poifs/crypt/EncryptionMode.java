package org.apache.poi.poifs.crypt;

import java.util.function.Supplier;

public enum EncryptionMode {
    binaryRC4(new EncryptionMode$$ExternalSyntheticLambda0(), 1, 1, 0),
    cryptoAPI(new EncryptionMode$$ExternalSyntheticLambda1(), 4, 2, 4),
    standard(new EncryptionMode$$ExternalSyntheticLambda2(), 4, 2, 36),
    agile(new EncryptionMode$$ExternalSyntheticLambda3(), 4, 4, 64),
    xor(new EncryptionMode$$ExternalSyntheticLambda4(), 0, 0, 0);
    
    public final Supplier<EncryptionInfoBuilder> builder;
    public final int encryptionFlags;
    public final int versionMajor;
    public final int versionMinor;

    private EncryptionMode(Supplier<EncryptionInfoBuilder> supplier, int i, int i2, int i3) {
        this.builder = supplier;
        this.versionMajor = i;
        this.versionMinor = i2;
        this.encryptionFlags = i3;
    }
}
