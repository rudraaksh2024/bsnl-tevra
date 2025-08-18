package org.apache.commons.compress.harmony.unpack200.bytecode;

public class CPInterfaceMethodRef extends CPRef {
    private int cachedHashCode;
    private boolean hashcodeComputed;

    public CPInterfaceMethodRef(CPClass cPClass, CPNameAndType cPNameAndType, int i) {
        super((byte) 11, cPClass, cPNameAndType, i);
    }

    public int invokeInterfaceCount() {
        return this.nameAndType.invokeInterfaceCount();
    }

    private void generateHashCode() {
        this.hashcodeComputed = true;
        this.cachedHashCode = ((this.className.hashCode() + 31) * 31) + this.nameAndType.hashCode();
    }

    public int hashCode() {
        if (!this.hashcodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }
}
