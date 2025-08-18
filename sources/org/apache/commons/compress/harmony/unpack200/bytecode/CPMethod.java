package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.util.List;

public class CPMethod extends CPMember {
    private int cachedHashCode;
    private boolean hashcodeComputed;

    public CPMethod(CPUTF8 cputf8, CPUTF8 cputf82, long j, List list) {
        super(cputf8, cputf82, j, list);
    }

    public String toString() {
        return "Method: " + this.name + "(" + this.descriptor + ")";
    }

    private void generateHashCode() {
        this.hashcodeComputed = true;
        this.cachedHashCode = ((this.name.hashCode() + 31) * 31) + this.descriptor.hashCode();
    }

    public int hashCode() {
        if (!this.hashcodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }
}
