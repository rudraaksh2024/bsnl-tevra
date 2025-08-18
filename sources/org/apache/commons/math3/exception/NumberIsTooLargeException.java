package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NumberIsTooLargeException extends MathIllegalNumberException {
    private static final long serialVersionUID = 4330003017885151975L;
    private final boolean boundIsAllowed;
    private final Number max;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NumberIsTooLargeException(Number number, Number number2, boolean z) {
        this(z ? LocalizedFormats.NUMBER_TOO_LARGE : LocalizedFormats.NUMBER_TOO_LARGE_BOUND_EXCLUDED, number, number2, z);
    }

    public NumberIsTooLargeException(Localizable localizable, Number number, Number number2, boolean z) {
        super(localizable, number, number2);
        this.max = number2;
        this.boundIsAllowed = z;
    }

    public boolean getBoundIsAllowed() {
        return this.boundIsAllowed;
    }

    public Number getMax() {
        return this.max;
    }
}
