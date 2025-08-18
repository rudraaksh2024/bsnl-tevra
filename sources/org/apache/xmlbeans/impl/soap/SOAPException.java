package org.apache.xmlbeans.impl.soap;

public class SOAPException extends Exception {
    private Throwable cause;

    public SOAPException() {
        this.cause = null;
    }

    public SOAPException(String str) {
        super(str);
        this.cause = null;
    }

    public SOAPException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public SOAPException(Throwable th) {
        super(th.toString());
        initCause(th);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r1.cause;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getMessage() {
        /*
            r1 = this;
            java.lang.String r0 = super.getMessage()
            if (r0 != 0) goto L_0x000f
            java.lang.Throwable r1 = r1.cause
            if (r1 == 0) goto L_0x000f
            java.lang.String r1 = r1.getMessage()
            return r1
        L_0x000f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.soap.SOAPException.getMessage():java.lang.String");
    }

    public Throwable getCause() {
        return this.cause;
    }

    public synchronized Throwable initCause(Throwable th) {
        if (this.cause != null) {
            throw new IllegalStateException("Can't override cause");
        } else if (th != this) {
            this.cause = th;
        } else {
            throw new IllegalArgumentException("Self-causation not permitted");
        }
        return this;
    }
}
