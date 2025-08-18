package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.exception.util.ExceptionContextProvider;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class MathUnsupportedOperationException extends UnsupportedOperationException implements ExceptionContextProvider {
    private static final long serialVersionUID = -6024911025449780478L;
    private final ExceptionContext context;

    public MathUnsupportedOperationException() {
        this(LocalizedFormats.UNSUPPORTED_OPERATION, new Object[0]);
    }

    public MathUnsupportedOperationException(Localizable localizable, Object... objArr) {
        ExceptionContext exceptionContext = new ExceptionContext(this);
        this.context = exceptionContext;
        exceptionContext.addMessage(localizable, objArr);
    }

    public ExceptionContext getContext() {
        return this.context;
    }

    public String getMessage() {
        return this.context.getMessage();
    }

    public String getLocalizedMessage() {
        return this.context.getLocalizedMessage();
    }
}
