package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NumberIsTooSmallException extends MathIllegalNumberException {
    private static final long serialVersionUID = -6100997100383932834L;
    private final boolean boundIsAllowed;
    private final Number min;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NumberIsTooSmallException(Number number, Number number2, boolean z) {
        this(z ? LocalizedFormats.NUMBER_TOO_SMALL : LocalizedFormats.NUMBER_TOO_SMALL_BOUND_EXCLUDED, number, number2, z);
    }

    public NumberIsTooSmallException(Localizable localizable, Number number, Number number2, boolean z) {
        super(localizable, number, number2);
        this.min = number2;
        this.boundIsAllowed = z;
    }

    public boolean getBoundIsAllowed() {
        return this.boundIsAllowed;
    }

    public Number getMin() {
        return this.min;
    }
}
