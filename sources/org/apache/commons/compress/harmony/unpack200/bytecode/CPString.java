package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CPString extends CPConstant {
    private int cachedHashCode;
    private boolean hashcodeComputed;
    private final CPUTF8 name;
    private transient int nameIndex;

    public CPString(CPUTF8 cputf8, int i) {
        super((byte) 8, cputf8, i);
        this.name = cputf8;
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.nameIndex);
    }

    public String toString() {
        return "String: " + getValue();
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.nameIndex = classConstantPool.indexOf(this.name);
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.name};
    }

    private void generateHashCode() {
        this.hashcodeComputed = true;
        this.cachedHashCode = 31 + this.name.hashCode();
    }

    public int hashCode() {
        if (!this.hashcodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }
}
